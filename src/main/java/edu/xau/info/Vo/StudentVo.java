package edu.xau.info.Vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 12:25
 */
@Data
@ToString
public class StudentVo extends infoVo {
    /*String name;
    String sex;
    String mobile;
    String password;
    String email;*/
    String grade;
    String stuno;
    Integer graduationtime;
}
