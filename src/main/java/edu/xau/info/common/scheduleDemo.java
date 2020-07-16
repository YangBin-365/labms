package edu.xau.info.common;

import edu.xau.info.Vo.TaskVo;
import edu.xau.info.Vo.remindVo;
import edu.xau.info.bean.Remind;

import edu.xau.info.bean.StuTask;
import edu.xau.info.bean.StuTaskExample;
import edu.xau.info.mapper.RemindMapper;
import edu.xau.info.mapper.StuTaskMapper;
import edu.xau.info.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @Author: 杨斌
 * @Date: 2020/7/16 0016 20:39
 */
@Component
@Slf4j
public class scheduleDemo {

    @Autowired
    TaskMapper taskMapper;
    @Autowired
    StuTaskMapper stutaskMapper;

    @Autowired
    RemindMapper remindMapper;

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
   /*     try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        // map = {1=简要叙述Filter过滤器程序和Servlet程序的特点。, 4=好好学习哦}
        log.info("map = {}", map);

        for (remindVo remind : reminds) {
            remindMapper.insert(new Remind(remind.getStuno(), "您的任务：《" + map.get(remind.getTaskid())+"》将于24小时内截止，请尽快完成！", new Date()));
            StuTask stuTask = new StuTask();
            stuTask.setRemindflag(1);
            StuTaskExample stuTaskExample = new StuTaskExample();
            stuTaskExample.createCriteria().andStunoEqualTo(remind.getStuno()).andTaskidEqualTo(remind.getTaskid());
            stutaskMapper.updateByExampleSelective(stuTask,stuTaskExample);
        }
    }


}
