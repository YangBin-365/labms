package edu.xau.info.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import edu.xau.info.Vo.StudentInfo;
import edu.xau.info.Vo.StudentVo;
import edu.xau.info.bean.*;
import edu.xau.info.common.CodeUtils;
import edu.xau.info.mapper.StuTaskMapper;
import edu.xau.info.mapper.StudentMapper;
import edu.xau.info.mapper.TeacherMapper;
import edu.xau.info.mapper.UserMapper;
import edu.xau.info.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    UserMapper userMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;


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

    @Override
    public void register(StudentVo vo, String invitecode) throws Exception {
        TeacherExample example1 = new TeacherExample();
        example1.createCriteria().andInvitecodeEqualTo(invitecode);
        Teacher teacher1 = teacherMapper.selectByExample(example1).get(0);
        if(teacher1 == null) throw  new Exception("未查到该验证码");

        Integer teacherid = teacher1.getUserid();
        log.info("teacherid = {}", teacherid);
        vo.setPassword(new BCryptPasswordEncoder().encode(vo.getPassword()));

        Student student = new Student();
        User user = new User();
        BeanUtils.copyProperties(vo,student);
        student.setTeaid(teacherid);
        BeanUtils.copyProperties(vo,user);

        userMapper.insert(user);
        student.setStuid(user.getUserid());
        log.info("\nstudent = {}", student);
        log.info("user = {}\n", user);
        studentMapper.insert(student);

        TeacherExample example2 = new TeacherExample();
        example2.createCriteria().andUseridEqualTo(teacherid);
        Teacher teacher2 = new Teacher();
        teacher2.setInvitecode(CodeUtils.creatinvitecode());
        teacherMapper.updateByExampleSelective(teacher2,example2);
    }
}
