package edu.xau.info.Vo;

import edu.xau.info.bean.StuTask;
import edu.xau.info.bean.Student;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/17 0017 19:56
 */
@Data
@ToString
public class StudentInfo  {
    String name;
    String stuno;
    String sex;
    String grade;
    Integer graduationtime;
    Float avgscore;

}
