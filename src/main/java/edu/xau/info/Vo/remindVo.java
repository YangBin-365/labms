package edu.xau.info.Vo;

import lombok.Data;
import lombok.ToString;

import javax.annotation.security.DenyAll;

/**
 * @Author: 杨斌
 * @Date: 2020/7/16 0016 22:51
 */
@ToString
@Data
public class remindVo {
    String stuno;
    int taskid;
}
