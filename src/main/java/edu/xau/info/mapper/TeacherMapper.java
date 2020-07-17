package edu.xau.info.mapper;

import edu.xau.info.Dto.Dto;
import edu.xau.info.Dto.TaskDto;
import edu.xau.info.bean.Teacher;
import edu.xau.info.bean.TeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    List<TaskDto>  findTaskView(int teaid);

    List<String> findAllNos();

    List<Dto> findStuCountDto();

    List<Dto> findTaskCountDto();
}