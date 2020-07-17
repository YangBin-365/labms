package edu.xau.info.common;

import com.aliyuncs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 11:59
 */
@Component
public class CodeUtils {

    @Autowired
    private static StringRedisTemplate redisTemplate;

    //生成验证码
    public static String getCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }


    public static boolean check(String code, String loginacct) {
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
