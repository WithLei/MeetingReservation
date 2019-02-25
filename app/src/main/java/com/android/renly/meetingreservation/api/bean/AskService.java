package com.android.renly.meetingreservation.api.bean;

public class AskService {
    // 会议编号
    private int applyId;
    // 员工编号
    private int workerId;
    // 内容
    private String content;

    public AskService(int applyId, int workerId, String content) {
        this.applyId = applyId;
        this.workerId = workerId;
        this.content = content;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
