package edu.xau.info.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.util.locale.provider.LocaleServiceProviderPool;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 10:39
 */
@Slf4j
@Api(tags = "公共模块")
@RequestMapping("/common")
@Controller
public class CommonController {
    @ApiOperation("获取菜单")
    @PostMapping("/getMenu")
    public String getMenu(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("当前authentication = {}",authentication);
        return "/";
    }
}
