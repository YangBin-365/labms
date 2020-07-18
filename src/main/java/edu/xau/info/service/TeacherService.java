package edu.xau.info.service;

import edu.xau.info.Vo.EchartVo;
import edu.xau.info.Vo.ReadtaskVo;
import edu.xau.info.Vo.StuTaskVo;
import edu.xau.info.Vo.TeacherVo;
import edu.xau.info.bean.Task;
import edu.xau.info.bean.Teacher;

import java.util.List;

public interface TeacherService {
    void register(TeacherVo teacher);


    void createtask(Task task);


    List<String> findmystu(int teaid);

    void mark(String sno, int taskid, float score);

    Teacher getMyInfo(int teaid);

    EchartVo findTaskView(int teaid);

    long getReadNum(String taskid);

    long getTotal(String taskid);

    List<StuTaskVo> getSubList(int taskid);

    void updateflagbyid(int teaid);

    EchartVo findteaechart();

    ReadtaskVo getTaskByNoAndId(String sno, int taskid);




}
