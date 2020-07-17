package edu.xau.info.service.impl;

import edu.xau.info.Vo.StudentInfo;
import edu.xau.info.bean.StuTask;
import edu.xau.info.bean.StuTaskExample;
import edu.xau.info.mapper.StuTaskMapper;
import edu.xau.info.mapper.StudentMapper;
import edu.xau.info.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 杨斌
 * @Date: 2020/7/15 0015 12:15
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
   StuTaskMapper stuTaskMapper;

    @Autowired
    StudentMapper studentMapper;
    @Override
    public void answerStuTask(StuTask stuTask) {
        StuTaskExample example = new StuTaskExample();
        example.createCriteria().andTaskidEqualTo(stuTask.getTaskid()).andStunoEqualTo(stuTask.getStuno());
        stuTaskMapper.updateByExampleSelective(stuTask,example);
    }

    @Override
    public List<StudentInfo> findpartner(int stuid) {
return studentMapper.findpartner(stuid);
    }
}
