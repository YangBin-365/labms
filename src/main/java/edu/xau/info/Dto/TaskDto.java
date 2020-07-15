package edu.xau.info.Dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 19:07
 */
@Data
@ToString
public class TaskDto extends Dto {
    int min;
    int max;
}
