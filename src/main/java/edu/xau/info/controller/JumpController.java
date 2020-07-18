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
@Api(tags = "跳转模块")
public class JumpController {

    @GetMapping("/test")
    @ResponseBody
    public String test(String name) {
        return name;
    }

    @GetMapping("/login-success")
    public String success() {
        return "login-success";
    }

}
