package edu.xau.info.mapper;

import edu.xau.info.Vo.StudentInfo;
import edu.xau.info.Vo.grastuVo;
import edu.xau.info.bean.Student;
import edu.xau.info.bean.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    long countByExample(StudentExample example);

    int deleteByExample(StudentExample example);

    int deleteByPrimaryKey(Integer stuid);

    int insert(Student record);

    int insertSelective(Student record);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(Integer stuid);

    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);


    List<grastuVo> findgraduatestu(int parseInt);

    void updategraduatestu(List<Integer> ids);

    List<StudentInfo> findpartner(int stuid);

}