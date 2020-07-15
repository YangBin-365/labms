package edu.xau.info.service.impl;

import edu.xau.info.Dto.EchartDto;
import edu.xau.info.Dto.TaskDto;
import edu.xau.info.Vo.TaskVo;
import edu.xau.info.Vo.TeacherVo;
import edu.xau.info.bean.*;
import edu.xau.info.mapper.*;
import edu.xau.info.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 12:16
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StuTaskMapper stuTaskMapper;

    @Override
    public void register(TeacherVo teachervo) {
        String newpswd = new BCryptPasswordEncoder().encode(teachervo.getPassword());
        teachervo.setPassword(newpswd);
        User user = new User();
        BeanUtils.copyProperties(teachervo, user);
        userMapper.insertSelective(user);
        log.info("user = {}  ", user);
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teachervo, teacher);
        teacher.setUserid(user.getUserid());
        log.info("teacher = {}  ", teacher);
        teacherMapper.insertSelective(teacher);
    }

    @Override
    public void createinvitecode(String teacherno) {
        String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(str.charAt(random.nextInt(length)));
        }
        TeacherExample example = new TeacherExample();
        example.createCriteria().andTeachernoEqualTo(teacherno);
        Teacher teacher = new Teacher();
        teacher.setInvitecode(sb.toString());
        teacherMapper.updateByExampleSelective(teacher, example);
    }

    @Override
    public void createtask(Task task) {
        taskMapper.insertSelective(task);

        Integer teaid = task.getTeaid();
        ArrayList<String> stuno = userMapper.findstus(teaid);
        log.info("将被添加任务的学生是  {}", stuno);
        for (String sno : stuno) {
            stuTaskMapper.insert(new StuTask(sno, task.getTaskid()));
        }
    }

    @Override
    public List<String> findmystu(int teaid) {
        return userMapper.findstus(teaid);
    }

    @Override
    public void mark(String sno, int taskid, float score) {
        StuTaskExample example = new StuTaskExample();
        example.createCriteria().andTaskidEqualTo(taskid).andStunoEqualTo(sno);
        StuTask task = new StuTask();
        task.setScore(score);
        stuTaskMapper.updateByExampleSelective(task, example);
    }

    @Override
    public Teacher getMyInfo(int teaid) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andUseridEqualTo(teaid);
        return teacherMapper.selectByExample(teacherExample).get(0);

    }

    @Override
    public EchartDto findTaskView(int teaid) {
        List<TaskDto> view = teacherMapper.findTaskView(teaid);
        EchartDto echartDto = new EchartDto();
        List<String> xaxis = new ArrayList<>();
        List<Number> min = new ArrayList<>();
        List<Number> avg = new ArrayList<>();
        List<Number> max = new ArrayList<>();
        String name = null;
        for (TaskDto dto : view) {
            name = dto.getXdata().length() > 5?dto.getXdata().substring(0, 5)+"……":dto.getXdata();
            xaxis.add(name);
            min.add(dto.getMin());
            avg.add(dto.getY());
            max.add(dto.getMax());
        }
       String[] legend =  new String[]{"最低分","平均分","最高分"};
        echartDto.setLegend(legend);
        echartDto.setXAxis(toStrArray(xaxis));

        Series minser = new Series();
        minser.setName(legend[0]);
        minser.setData(toNumArray(min));

        Series avgser = new Series();
        avgser.setName(legend[1]);
        avgser.setData(toNumArray(avg));

        Series maxser = new Series();
        maxser.setName(legend[2]);
        maxser.setData(toNumArray(max));
        echartDto.setSeries(new Series[]{minser,avgser,maxser});
        return echartDto;
    }

    @Override
    public long getReadNum(String taskid) {
        StuTaskExample example = new StuTaskExample();

        example.createCriteria().andReadflagEqualTo(1);
        return  (stuTaskMapper.countByExample(example));
    }

    @Override
    public long getTotal(String taskid) {
        StuTaskExample example = new StuTaskExample();
        example.createCriteria().andSubflagEqualTo(1);
        return  (stuTaskMapper.countByExample(example));
    }

    @Override
    public List<TaskVo> getSubList(int taskid) {
        return stuTaskMapper.getAllSub(taskid);
    }

    private Number[] toNumArray(List<Number> list){
        Number[] ints = new Number[list.size()];
        for (int i = 0;i < list.size();i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    private String[] toStrArray(List<String> list){
        String[] arr = new String[list.size()];
        for (int i = 0;i < list.size();i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


}
