package edu.xau.info.mapper;

import edu.xau.info.Vo.TaskVo;
import edu.xau.info.bean.StuTask;
import edu.xau.info.bean.StuTaskExample;
import edu.xau.info.bean.StuTaskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuTaskMapper {
    long countByExample(StuTaskExample example);

    int deleteByExample(StuTaskExample example);

    int deleteByPrimaryKey(StuTaskKey key);

    int insert(StuTask record);

    int insertSelective(StuTask record);

    List<StuTask> selectByExampleWithBLOBs(StuTaskExample example);

    List<StuTask> selectByExample(StuTaskExample example);

    StuTask selectByPrimaryKey(StuTaskKey key);

    int updateByExampleSelective(@Param("record") StuTask record, @Param("example") StuTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") StuTask record, @Param("example") StuTaskExample example);

    int updateByExample(@Param("record") StuTask record, @Param("example") StuTaskExample example);

    int updateByPrimaryKeySelective(StuTask record);

    int updateByPrimaryKeyWithBLOBs(StuTask record);

    int updateByPrimaryKey(StuTask record);

    List<TaskVo> getAllSub(int taskid);
}