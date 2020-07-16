package edu.xau.info.controller;

import com.aliyuncs.utils.StringUtils;
import edu.xau.info.Dto.EchartDto;
import edu.xau.info.Vo.StuTaskVo;
import edu.xau.info.Vo.TeacherVo;
import edu.xau.info.bean.Task;
import edu.xau.info.bean.Teacher;
import edu.xau.info.common.AppResponse;
import edu.xau.info.common.Code;
import edu.xau.info.common.SendMsgTemplate;
import edu.xau.info.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 10:40
 */
@Slf4j
@Api(tags = "教师模块")
@RequestMapping("/teacher")
@RestController
public class TeacherController {
    @Autowired
    private SendMsgTemplate msgTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TeacherService service;

    @ApiOperation("批改作业")
    @PostMapping("/mark")
    public AppResponse<String> mark(String sno, int taskid, float score) {
        try {
            service.mark(sno, taskid, score);
            return AppResponse.ok("ok");
        } catch (Exception e) {
            return AppResponse.fail("评价失败");
        }
    }

    @ApiOperation(value = "发送短信验证码")
    @PostMapping("/sendsms")
    public AppResponse<String> sendsms(String loginacct) {
        Map<String, String> map = new HashMap();
        map.put("PhoneNumbers", loginacct);
//        设置短信模板
        map.put("TemplateCode", "SMS_191801995");
        String code = Code.getCode(6);
//        设置短信参数
        map.put("TemplateParam", "{code:" + code + "}");
        msgTemplate.sendCheckCode(map);
        log.info("发送短信成功");
//        将手机号及其验证码存于redis中，有效时间五分钟
        redisTemplate.opsForValue().set(loginacct, code, 5, TimeUnit.MINUTES);
        log.info("将验证码: {} 成功存于redis", code);
        return AppResponse.ok(code);
    }


    @ApiOperation(value = "教师注册")
    @PostMapping("/valide")
    public AppResponse register(TeacherVo teacher, String code) {
        try {
            if (check(code, teacher.getMobile())) {
                service.register(teacher);
                return AppResponse.ok("ok");
            }
        } catch (Exception e) {
            return AppResponse.fail("注册失败");
        }
        return AppResponse.fail("注册失败");
    }

    @ApiOperation("生成邀请码")
    @GetMapping("/create")
    public AppResponse createinvitecode(String teacherno) {
        try {
            service.createinvitecode(teacherno);
        } catch (Exception e) {
            return AppResponse.fail(null);
        }
        return AppResponse.ok("ok");
    }

    @ApiOperation("发布任务")
    @PostMapping("/task")
    public AppResponse postTask(String title,String body,int teaid,int time) {
        log.info("开始发布任务");
        try {
            Task task = new Task();
            task.setStarttime(new Date());
            task.setBody(body);
            task.setTeaid(teaid);
            task.setEndtime(new Date(new Date().getTime() + time * 8640_0000L));
            task.setTitile(title);

            log.info("任务：{}", task);
            service.createtask(task);
        } catch (Exception e) {
            return AppResponse.fail(null);
        }
        return AppResponse.ok("ok");
    }

    @ApiOperation("查询自己的学生")
    @GetMapping("/mystudent")
    public AppResponse<List> findmystudent(int teaid) {
        List<String> list = service.findmystu(teaid);
        return AppResponse.ok(list);
    }

    @ApiOperation("获取自己的信息")
    @GetMapping("/getmyinfo")
    public AppResponse<Teacher> getmyinfo(int teaid) {
        try {
            Teacher teacher = service.getMyInfo(teaid);
            return AppResponse.ok(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("各任务的反馈情况")
    @PostMapping("/getTaskoverview")
    public AppResponse<EchartDto> getTaskView(int teaid) {
        try {
            log.info("开始查询 TeacherId = {}", teaid);
            EchartDto echartDto= service.findTaskView(teaid);
            return AppResponse.ok(echartDto);
        } catch (Exception e) {
            log.error("查询失败" + e.getMessage());
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("获取已读人数")
    @GetMapping("/getReadNum")
    public AppResponse<Long> getRead(String taskid){
        try {
            long count = service.getReadNum(taskid);
            return AppResponse.ok(count);
        } catch (Exception e) {
            log.info(e.getMessage() );
            return AppResponse.fail(null);
        }

    }

    @ApiOperation("获取已提交列表")
    @PostMapping("/getSubList")
    public AppResponse<List<StuTaskVo>> getSubList(int taskid){
        try {
            List<StuTaskVo> list =   service.getSubList(taskid);
            return AppResponse.ok(list);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }

    }


    @ApiOperation("获取总人数")
    @GetMapping("/getSubdNum")
    public AppResponse<Long> getTotal(String taskid){
        try {
            long count = service.getTotal(taskid);
            return AppResponse.ok(count);
        } catch (Exception e) {
            log.info(e.getMessage() );
            return AppResponse.fail(null);
        }
    }

    public boolean check(String code, String loginacct) {
        try {
            if (StringUtils.isEmpty(code) || StringUtils.isEmpty(loginacct)) {
                return false;
            }
            String redisCode = redisTemplate.opsForValue().get(loginacct);
            if (code.equals(redisCode)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
