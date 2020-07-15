package edu.xau.info.common;

import java.util.Random;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 11:59
 */
public class Code {
    //生成验证码
    public static String getCode(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
