package edu.xau.info.service;

import edu.xau.info.Vo.StudentInfo;
import edu.xau.info.bean.StuTask;
import edu.xau.info.bean.Student;

import java.util.List;

public interface StudentService {

    void answerStuTask(StuTask stuTask);

    List<StudentInfo> findpartner(int stuid);

}
