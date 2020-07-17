package edu.xau.info.controller;

import edu.xau.info.Dto.Dto;
import edu.xau.info.Dto.EchartDto;
import edu.xau.info.common.AppResponse;
import edu.xau.info.service.AdminService;
import edu.xau.info.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 10:39
 */
@Slf4j
@Api(tags = "管理员模块")
@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    AdminService adminService;

    @ApiOperation("审核老师的加入申请")
    @PostMapping("/checkteaapply")
    public AppResponse checkteaapply(int teaid) {
        try {
            teacherService.updateflagbyid(teaid);
            return AppResponse.ok("ok");
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }



    @ApiOperation("查询各个老师发布任务数和指导学生数")
    @PostMapping("/findteaechart")
    public AppResponse<EchartDto> findteaechart(){
        try {
            EchartDto echartDtos = teacherService.findteaechart();
            return AppResponse.ok(echartDtos);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }


    @ApiOperation("给所有老师发送信息")
    @PostMapping("/sendmsgtotea")
    public AppResponse sendmsgtotea(String title) {
        log.info("title = ",title);
        try {
            adminService.sendmsgtotea(title);
            return AppResponse.ok("ok");
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }

    }

}
