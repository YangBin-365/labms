package edu.xau.info.Vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 19:07
 */
@Data
@ToString
public class EchartTaskVo extends EchartSmallVo {
    int min;
    int max;
}
