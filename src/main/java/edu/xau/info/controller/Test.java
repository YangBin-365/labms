package edu.xau.info.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 杨斌
 * @Date: 2020/7/14 0014 17:47
 */
@Controller
@Api(tags = "测试模块")
public class Test {

    @ResponseBody
    @GetMapping("/path")
    public String fun(String name) {
        return name;
    }

    @GetMapping("/student/s1")
    public String stu() {
        return "student";
    }

    @RequestMapping("/login-success")
    public String success() {
        return "login-success";
    }

    @GetMapping("/teacher/t1")
    public String tec() {
        return "teacher";
    }
}
