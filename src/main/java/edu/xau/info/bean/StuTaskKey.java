package edu.xau.info.bean;

public class StuTaskKey {

    public StuTaskKey() {
    }

    public StuTaskKey(String stuno, Integer taskid) {
        this.stuno = stuno;
        this.taskid = taskid;
    }

    private String stuno;

    private Integer taskid;

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno == null ? null : stuno.trim();
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }
}