package edu.xau.info.service.impl;

import edu.xau.info.bean.Remind;
import edu.xau.info.bean.RemindExample;
import edu.xau.info.mapper.RemindMapper;
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

    @Override
    public void sendmsgtotea(String title) {

        List<String> nos = teacherMapper.findAllNos();
        log.info("nos = ",nos);
        Date date = new Date();
        for (String no : nos) {
            remindMapper.insert(new Remind(no, title, date));
        }
    }
}
