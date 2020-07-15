package edu.xau.info.bean;

public class StuTask extends StuTaskKey {
    public StuTask(String stuno, Integer taskid) {
        super(stuno, taskid);
    }

    public StuTask() {
    }

    private Float score;

    private Integer subflag;

    private Integer readflag;

    private String answer;

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getSubflag() {
        return subflag;
    }

    public void setSubflag(Integer subflag) {
        this.subflag = subflag;
    }

    public Integer getReadflag() {
        return readflag;
    }

    public void setReadflag(Integer readflag) {
        this.readflag = readflag;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}