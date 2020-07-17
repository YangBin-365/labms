package edu.xau.info.service.impl;

import edu.xau.info.Dto.Dto;
import edu.xau.info.Dto.EchartDto;
import edu.xau.info.Dto.TaskDto;
import edu.xau.info.Vo.StuTaskVo;
import edu.xau.info.Vo.TeacherVo;
import edu.xau.info.bean.*;
import edu.xau.info.mapper.*;
import edu.xau.info.service.TeacherService;
import io.swagger.models.auth.In;
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

        Series minser = new Series(legend[0],toNumArray(min));
        Series avgser = new Series(legend[1],toNumArray(avg));
        Series maxser = new Series(legend[2],toNumArray(max));

        echartDto.setLegend(legend);
        echartDto.setXAxis(toStrArray(xaxis));
        echartDto.setSeries(new Series[]{minser,avgser,maxser});
        return echartDto;
    }

    @Override
    public EchartDto findteaechart() {
        String[] legend =  new String[]{"指导学生数","发布任务数"};
        List<Dto> stucount = teacherMapper.findStuCountDto();
        List<Dto> taskcount = teacherMapper.findTaskCountDto();
        log.info("stucount = {}", stucount);
        log.info("taskcount = {}", taskcount);

        if(stucount.size() != taskcount.size()) throw new IllegalArgumentException("查询数据异常");
        //指导学生数
        Integer[] stucountArr = new Integer[stucount.size()];
//        发布任务数
        Integer[] taskcountArr = new Integer[taskcount.size()];
        //名字
        String[] xAxis = new String[stucount.size()];

        for (int i = 0; i < stucount.size(); i++) {
            stucountArr[i] = stucount.get(i).getY();
            xAxis[i] = stucount.get(i).getXdata();
        }

        for (int i = 0; i < taskcount.size(); i++) {
            taskcountArr[i] = taskcount.get(i).getY();
        }
        Series stuseries = new Series(legend[0], stucountArr);
        Series taskseries = new Series(legend[1], taskcountArr);
        EchartDto echartDto = new EchartDto(legend, xAxis, new Series[]{stuseries, taskseries});
        log.info("echartDto = {}", echartDto);
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
    public List<StuTaskVo> getSubList(int taskid) {
        return stuTaskMapper.getAllSub(taskid);
    }

    @Override
    public void updateflagbyid(int teaid) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andUseridEqualTo(teaid);
        Teacher teacher = new Teacher();
        teacher.setFlag(1);
        teacherMapper.updateByExampleSelective(teacher,teacherExample);
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
