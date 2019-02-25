package com.android.renly.meetingreservation.api.bean;

public class AskMeeting {
    // 与会人员ID
    private int userId;
    private int id;
    private int status;
    private String beginTime;
    private String endTime;
    private int attendance;
    private String order;
    // 申请人ID
    private int workerId;
    private String topic;
    private String intro;
    private int flexible;

    public AskMeeting(int userId) {
        this.userId = userId;
    }

    public AskMeeting(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public AskMeeting(int status, String beginTime, int attendance, String order) {
        this.status = status;
        this.beginTime = beginTime;
        this.attendance = attendance;
        this.order = order;
    }

    public AskMeeting(int workerId, String topic, String intro, String beginTime, String endTime,
                      int attendance, int flexible) {
        this.workerId = workerId;
        this.topic = topic;
        this.intro = intro;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.attendance = attendance;
        this.flexible = flexible;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
