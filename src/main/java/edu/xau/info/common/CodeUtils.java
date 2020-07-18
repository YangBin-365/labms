package edu.xau.info.common;

import com.aliyuncs.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 11:59
 */
@Slf4j
@Component
public class CodeUtils {

    @Autowired
    StringRedisTemplate redisTemplate;

    public static String creatinvitecode() {
        String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(str.charAt(random.nextInt(length)));
        }
        return sb.toString();
    }


    //生成验证码
    public static String getCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    public boolean check(String code, String loginacct) {
        try {
            log.info("you code = {}", code);
            log.info("you loginacct = {}", loginacct);
            if (StringUtils.isEmpty(code) || StringUtils.isEmpty(loginacct)) {
                log.error("code 或 loginacct为空");
                return false;
            }
            log.info("开始从redis获取  {} 的验证码", loginacct);
            String redisCode = null;
            try {
                redisCode = redisTemplate.opsForValue().get(loginacct);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error = {}", e.getMessage());
            }
            log.info("redis code = {}", redisCode);
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
