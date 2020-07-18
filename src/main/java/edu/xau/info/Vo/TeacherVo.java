package edu.xau.info.Vo;

import lombok.Data;
import lombok.ToString;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 12:25
 */
@Data
public class TeacherVo extends infoVo {
    String teacherno;

    @Override
    public String toString() {
        return "TeacherVo{" +
                "teacherno='" + teacherno + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
