package edu.xau.info.controller;

import edu.xau.info.Vo.StudentInfo;
import edu.xau.info.Vo.StudentVo;
import edu.xau.info.bean.StuTask;
import edu.xau.info.bean.Student;
import edu.xau.info.common.AppResponse;
import edu.xau.info.common.CodeUtils;
import edu.xau.info.common.OSSTemplate;
import edu.xau.info.mapper.StuTaskMapper;
import edu.xau.info.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 10:39
 */
@Slf4j
@Api(tags = "学生模块")
@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    OSSTemplate ossTemplate;
    @Autowired
    StudentService studentService;
    @Autowired
    CodeUtils codeUtils;

    @ApiOperation(value = "回复任务")
    @PostMapping("/answer")
    public AppResponse<Object> upload(@RequestParam(value = "uploadfile", required = false) MultipartFile[] files, String answer, String stuno, int taskid,/* @ApiParam(value = "是否提交？(提交-1/草稿-0)",type = "integer($int32)") */Integer subflag) {//可不指定
        try {
            log.info("开始上传");
            String filePath = null;
            if (files != null) {
                MultipartFile file = files[0];
                String filename = stuno+"_" + taskid + "_" + file.getOriginalFilename();
                filePath = ossTemplate.upload(filename, file.getInputStream());
                log.info("上传成功，路径 = {}", filePath);
            }

            StuTask stuTask = new StuTask(stuno, taskid, filePath, answer, subflag/*== 0?null:1*/);

            log.info("stuTask = {}", stuTask);
            studentService.answerStuTask(stuTask);
            log.info("任务回复  url : {}  \nanswer = {}", filePath, answer);
            return AppResponse.ok(filePath);
        } catch (IOException e) {
            log.info("上传失败");
            return AppResponse.fail(null);
        }

    }


    @ApiOperation(value = "我的伙伴")
    @PostMapping("/findpartner")
    public AppResponse<List<StudentInfo>> findpartner(int stuid){
        try {
            List<StudentInfo> stus = studentService.findpartner(stuid);
            log.info("stus = {}",stus);
            return AppResponse.ok(stus);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }

    @ApiOperation(value = "学生注册")
    @PostMapping("/register")
    public AppResponse register(StudentVo vo,String invitecode,String code){
        if (!codeUtils.check(code, vo.getMobile())) {
            return AppResponse.fail("验证码错误");
        }
        try {
            studentService.register(vo,invitecode);
            return AppResponse.ok("ok");
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }


}
