package com.android.renly.meetingreservation.api.bean;

import java.util.List;

public class Room {
    // eg:B2楼4032
    private String area;
    // eg:多功能会议室
    private String name;
    // 评分
    private float score;
    // 热度
    private int hot;
    // 推荐时间
    private long recommondTime;
    // 是否需要审核
    private boolean needVerify;
    // 关键词
    private String[] keyWords;
    // 背景图
    private String img;

    public Room() {
        super();
    }

    public Room(String area, String name, float score, int hot, long recommondTime, boolean needVerify, String[] keyWords, String img) {
        this.area = area;
        this.name = name;
        this.score = score;
        this.hot = hot;
        this.recommondTime = recommondTime;
        this.needVerify = needVerify;
        this.keyWords = keyWords;
        this.img = img;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public long getRecommondTime() {
        return recommondTime;
    }

    public void setRecommondTime(long recommondTime) {
        this.recommondTime = recommondTime;
    }

    public boolean isNeedVerify() {
        return needVerify;
    }

    public void setNeedVerify(boolean needVerify) {
        this.needVerify = needVerify;
    }

    public String[] getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
