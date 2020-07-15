package edu.xau.info.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 20:14
 */
@Data
@ToString
public class Series {
    String name;
    String type = "bar";
    Number[] data;
}
