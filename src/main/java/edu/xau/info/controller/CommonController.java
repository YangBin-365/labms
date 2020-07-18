package edu.xau.info.controller;

import edu.xau.info.bean.Remind;
import edu.xau.info.bean.RemindExample;
import edu.xau.info.common.AppResponse;
import edu.xau.info.common.CodeUtils;
import edu.xau.info.common.SendMsgTemplate;
import edu.xau.info.mapper.RemindMapper;
import edu.xau.info.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private SendMsgTemplate msgTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    RemindMapper remindMapper;
    @Autowired
    CodeUtils codeUtils;


    @ApiOperation("获取菜单")
    @PostMapping("/getMenu")
    public AppResponse<List> getMenu() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("当前authentication = {}", authentication);
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            log.info("authorities = {}", authorities);
            List<String> menus = commonService.findMenuByRole(authorities);
            log.info("menus = {}", menus);
            return AppResponse.ok(menus);
        } catch (Exception e) {
            log.info(e.getMessage());
            return AppResponse.fail(null);
        }
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatepswd")
    public AppResponse updatepswd(String oldpswd, String newpswd, String code, String mobile) {
        if (oldpswd.equals(newpswd)) return AppResponse.fail("新旧密码相同！");
        try {
            if (codeUtils.check(code, mobile)) {
                commonService.updatepswd(mobile, oldpswd, newpswd);
                return AppResponse.ok("ok");
            } else {
                return AppResponse.fail("验证码错误");
            }
        } catch (Exception e) {
            log.error("Error = {}", e.getMessage());
            return AppResponse.fail("修改密码失败");
        }
    }


    @ApiOperation(value = "获取我的消息")
    @PostMapping("/getmsg")
    public AppResponse<List> getMyMsg(String no) {
        try {
            RemindExample example = new RemindExample();
            example.createCriteria().andStunoEqualTo(no);
            List<Remind> reminds = remindMapper.selectByExample(example);
            log.info("reminds = {}", reminds);
            return AppResponse.ok(reminds);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return AppResponse.fail(null);
        }
    }


    @ApiOperation(value = "发送短信验证码")
    @PostMapping("/sendsms")
    public AppResponse<String> sendsms(String loginacct) {
        Map<String, String> map = new HashMap();
        map.put("PhoneNumbers", loginacct);
//        设置短信模板
        map.put("TemplateCode", "SMS_191801995");
        String code = CodeUtils.getCode(6);
//        设置短信参数
        map.put("TemplateParam", "{code:" + code + "}");
        msgTemplate.sendCheckCode(map);
        log.info("发送短信成功");
//        将手机号及其验证码存于redis中，有效时间五分钟
        redisTemplate.opsForValue().set(loginacct, code, 5, TimeUnit.MINUTES);
        log.info("将验证码: {} 成功存于redis", code);
        return AppResponse.ok(code);
    }

}
