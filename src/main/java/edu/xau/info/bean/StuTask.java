package edu.xau.info.bean;

import lombok.ToString;


public class StuTask extends StuTaskKey {
    @Override
    public String toString() {
        return "StuTask{" +
                super.toString() +
                "url='" + url + '\'' +
                ", score=" + score +
                ", subflag=" + subflag +
                ", readflag=" + readflag +
                ", remindflag=" + remindflag +
                ", answer='" + answer + '\'' +
                '}';
    }

    public StuTask() {
    }

    public StuTask(String stuno, Integer taskid, String url, String answer,Integer subflag) {
        super(stuno, taskid);
        this.url = url;
        this.answer = answer;
        this.subflag = subflag;
    }

    public StuTask(String sno, Integer taskid) {
        super(sno,taskid);
    }
    private String url;

    private Float score;

    private Integer subflag;

    private Integer readflag;

    private Integer remindflag;

    private String answer;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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