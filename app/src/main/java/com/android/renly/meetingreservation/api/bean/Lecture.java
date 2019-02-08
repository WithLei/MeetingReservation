package com.android.renly.meetingreservation.api.bean;

public class Lecture {
    private String img;
    private String title;
    private String speaker;
    private long time;
    private String totalTime;

    public Lecture(String img, String title, String speaker, long time, String totalTime) {
        this.img = img;
        this.title = title;
        this.speaker = speaker;
        this.time = time;
        this.totalTime = totalTime;
    }

    public Lecture() {
        super();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
