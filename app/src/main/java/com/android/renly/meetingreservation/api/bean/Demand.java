package com.android.renly.meetingreservation.api.bean;

public class Demand {
    /**
     * 需求更新时间
     */
    private String updateTime;
    /**
     * 日期
     */
    private String date;
    /**
     * 时间
     */
    private String time;
    /**
     * 人数
     */
    private int people;
    /**
     * 预算
     */
    private int budget;

    public Demand() {
        super();
    }

    public Demand(String updateTime, String date, String time, int people, int budget) {
        this.updateTime = updateTime;
        this.date = date;
        this.time = time;
        this.people = people;
        this.budget = budget;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
