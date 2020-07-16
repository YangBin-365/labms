package edu.xau.info.mapper;

import edu.xau.info.bean.Remind;
import edu.xau.info.bean.RemindExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemindMapper {
    long countByExample(RemindExample example);

    int deleteByExample(RemindExample example);

    int deleteByPrimaryKey(String stuno);

    int insert(Remind record);

    int insertSelective(Remind record);

    List<Remind> selectByExample(RemindExample example);

    Remind selectByPrimaryKey(String stuno);

    int updateByExampleSelective(@Param("record") Remind record, @Param("example") RemindExample example);

    int updateByExample(@Param("record") Remind record, @Param("example") RemindExample example);

    int updateByPrimaryKeySelective(Remind record);

    int updateByPrimaryKey(Remind record);
}