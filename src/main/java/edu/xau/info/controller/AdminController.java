package edu.xau.info.controller;

import edu.xau.info.Vo.EchartVo;
import edu.xau.info.bean.Student;
import edu.xau.info.bean.Teacher;
import edu.xau.info.common.AppResponse;
import edu.xau.info.service.AdminService;
import edu.xau.info.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("获取需要审核的申请列表")
    @PostMapping("/checklist")
    public AppResponse<List> checklist(){
        try {
            List<Teacher> teachers = adminService.getCheckList();
            return AppResponse.ok(teachers);
        } catch (Exception e) {
            log.error("Error = {}",e.getMessage());
            return AppResponse.fail(null);
        }
    }


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


    @ApiOperation("查询当前所有学生")
    @PostMapping("/findallstu")
    public AppResponse<List<Student>> findallstu() {
        try {
            List<Student> students = adminService.findallstu();
            return AppResponse.ok(students);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("查询当前所有老师")
    @PostMapping("/findalltea")
    public AppResponse<List<Teacher>> findalltea() {
        try {
            List<Teacher> teachers = adminService.findalltea();
            return AppResponse.ok(teachers);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("查询各个老师发布任务数和指导学生数")
    @PostMapping("/findteaechart")
    public AppResponse<EchartVo> findteaechart() {
        try {
            EchartVo echartDtos = teacherService.findteaechart();
            return AppResponse.ok(echartDtos);
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }
    }


    @ApiOperation("给所有老师发送信息")
    @PostMapping("/sendmsgtotea")
    public AppResponse sendmsgtotea(String title) {
        log.info("title = ", title);
        try {
            adminService.sendmsgtotea(title);
            return AppResponse.ok("ok");
        } catch (Exception e) {
            log.error(e.getMessage());
            return AppResponse.fail(null);
        }

    }

}
