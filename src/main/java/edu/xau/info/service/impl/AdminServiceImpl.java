package edu.xau.info.service.impl;

import edu.xau.info.bean.*;
import edu.xau.info.mapper.RemindMapper;
import edu.xau.info.mapper.StudentMapper;
import edu.xau.info.mapper.TeacherMapper;
import edu.xau.info.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 12:15
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    RemindMapper remindMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public void sendmsgtotea(String title) {

        List<String> nos = teacherMapper.findAllNos();
        log.info("nos = ",nos);
        Date date = new Date();
        for (String no : nos) {
            remindMapper.insert(new Remind(no, title, date));
        }
    }

    @Override
    public List<Student> findallstu() {
        StudentExample example = new StudentExample();
        example.createCriteria().andFlagIsNull();
        return studentMapper.selectByExample(example);
    }

    @Override
    public List<Teacher> findalltea() {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andFlagEqualTo(1);
        return teacherMapper.selectByExample(example);
    }

    @Override
    public List<Teacher> getCheckList() {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andFlagIsNull();
        return teacherMapper.selectByExample(teacherExample);
    }
}
