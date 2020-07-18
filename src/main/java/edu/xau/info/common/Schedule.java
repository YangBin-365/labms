package edu.xau.info.common;

import edu.xau.info.Vo.grastuVo;
import edu.xau.info.Vo.TaskVo;
import edu.xau.info.Vo.remindVo;
import edu.xau.info.bean.*;

import edu.xau.info.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: 杨斌
 * @Date: 2020/7/16 0016 20:39
 */
@Component
@Slf4j
public class Schedule {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    StuTaskMapper stutaskMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    RemindMapper remindMapper;
    @Autowired
    RoleUserMapper roleUserMapper;
    @Autowired
    UserMapper userMapper;


    /**
     * 删除该年毕业学生的所有信息(除本人信息)
      */
//    @Scheduled(cron = "s min h day mon week")
//    @Scheduled(fixedRate = 10000)
    @Scheduled(cron = "0 0 0 1 8 * ")//即每年秋季开学
    public void leaveSchool() {
        String cuuentyear = new SimpleDateFormat("yyyy").format(new Date());
        //查询学生的学号的id列表
        List<grastuVo> stuvos = studentMapper.findgraduatestu(Integer.parseInt(cuuentyear));
        List<Integer> ids = new ArrayList();
        List<String> nos =  new ArrayList();
        for (grastuVo stuvo : stuvos) {
            ids.add(stuvo.getStuid());
            nos.add(stuvo.getStuno());
        }
  /*      try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        log.info("ids = {} ", ids);
        log.info("nos = {} ", nos);
        //修改毕业标记
        studentMapper.updategraduatestu(ids);
        //用学号删除消息
        RemindExample remindExample = new RemindExample();
        remindExample.createCriteria().andStunoIn(nos);
        remindMapper.deleteByExample(remindExample);
        //用学号删除自己完成的任务
        StuTaskExample stuTaskExample = new StuTaskExample();
        stuTaskExample.createCriteria().andStunoIn(nos);
        stutaskMapper.deleteByExample(stuTaskExample);
        //用id删除自己的角色
        RoleUserExample roleUserExample = new RoleUserExample();
        roleUserExample.createCriteria().andUseridIn(ids);
        roleUserMapper.deleteByExample(roleUserExample);
        //用id删除自己的用户信息
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUseridIn(ids);
        userMapper.deleteByExample(userExample);
    }


    /**
     * 定时任务
     * cron
     */
    @Scheduled(cron = "0 0 8,14,20 * * * ")
//    @Scheduled(fixedRate = 10000)
    public void sendwarm() {
        //查询当前未结束的任务
        List<remindVo> reminds = taskMapper.findRemind(new Date(), new Date(new Date().getTime() + 8640_0000L));
        Set<Integer> taskids = new HashSet();

        for (remindVo remindVo : reminds) {
            taskids.add(remindVo.getTaskid());
        }
        log.info("taskids = {}", taskids);

        List<TaskVo> taskVos = taskMapper.findTitle(taskids);
        log.info("taskVos = {}", taskVos);

        HashMap<Integer, String> map = new HashMap<>();
        for (TaskVo taskVo : taskVos) {
            map.put(taskVo.getTaskid(), taskVo.getTitile());
        }
       /* try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        // map = {1=简要叙述Filter过滤器程序和Servlet程序的特点。, 4=好好学习哦}
        log.info("map = {}", map);

        for (remindVo remind : reminds) {
            remindMapper.insert(new Remind(remind.getStuno(), "您的任务：《" + map.get(remind.getTaskid()) + "》将于24小时内截止，请尽快完成！", new Date()));
            StuTask stuTask = new StuTask();
            stuTask.setRemindflag(1);
            StuTaskExample stuTaskExample = new StuTaskExample();
            stuTaskExample.createCriteria().andStunoEqualTo(remind.getStuno()).andTaskidEqualTo(remind.getTaskid());
            stutaskMapper.updateByExampleSelective(stuTask, stuTaskExample);
        }
    }


}
