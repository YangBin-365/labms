package edu.xau.info.Vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 21:27
 */
@Data
@ToString
public class StuTaskVo extends TaskVo {

    String stuno;
    String answer;
}
