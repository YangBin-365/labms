package edu.xau.info.controller;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;
import edu.xau.info.bean.Menu;
import edu.xau.info.common.AppResponse;
import edu.xau.info.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.util.Collection;
import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 10:39
 */
@Slf4j
@Api(tags = "公共模块")
@RequestMapping("/common")
@RestController
public class CommonController {

    @Autowired
    CommonService commonService;

    @ApiOperation("获取菜单")
    @PostMapping("/getMenu")
    public AppResponse<List> getMenu(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("当前authentication = {}",authentication);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            log.info("authorities = {}",authorities);
            List<String> menus =  commonService.findMenuByRole(authorities);
            log.info("menus = {}",menus);
            return AppResponse.ok(menus);
        } catch (Exception e) {
            log.info(e.getMessage());
            return AppResponse.fail(null);
        }
    }
}
