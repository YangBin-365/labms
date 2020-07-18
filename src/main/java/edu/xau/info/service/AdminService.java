package edu.xau.info.service;

import edu.xau.info.bean.Student;
import edu.xau.info.bean.Teacher;

import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 12:15
 */
public interface AdminService {
    void sendmsgtotea(String title);

    List<Student> findallstu();

    List<Teacher> findalltea();

    List<Teacher> getCheckList();
}
