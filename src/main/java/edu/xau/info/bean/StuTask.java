package edu.xau.info.bean;

public class StuTask extends StuTaskKey {
    private Float score;

    private Integer subflag;

    private Integer readflag;

    private Integer remindflag;

    private String answer;
    public StuTask() {
    }

    public StuTask(String sno, Integer taskid) {
        super(sno,taskid);
    }

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

    public Integer getRemindflag() {
        return remindflag;
    }

    public void setRemindflag(Integer remindflag) {
        this.remindflag = remindflag;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}