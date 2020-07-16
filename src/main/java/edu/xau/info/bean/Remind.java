package edu.xau.info.bean;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Remind {
    private String stuno;

    private String title;

    private Date time;

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno == null ? null : stuno.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}