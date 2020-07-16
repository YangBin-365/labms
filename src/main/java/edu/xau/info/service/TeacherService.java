package edu.xau.info.service;

import edu.xau.info.Dto.EchartDto;
import edu.xau.info.Vo.StuTaskVo;
import edu.xau.info.Vo.TeacherVo;
import edu.xau.info.bean.Task;
import edu.xau.info.bean.Teacher;

import java.util.List;

public interface TeacherService {
    void register(TeacherVo teacher);

    void createinvitecode(String teacherno );

    void createtask(Task task);


    List<String> findmystu(int teaid);

    void mark(String sno, int taskid, float score);

    Teacher getMyInfo(int teaid);

    EchartDto findTaskView(int teaid);

    long getReadNum(String taskid);

    long getTotal(String taskid);

    List<StuTaskVo> getSubList(int taskid);
}
