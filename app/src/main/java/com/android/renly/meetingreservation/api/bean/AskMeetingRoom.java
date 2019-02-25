package com.android.renly.meetingreservation.api.bean;

public class AskMeetingRoom {
    private int id;
    private String name;
    private int buildingId;
    private String detail;
    private int volume;
    private String price;
    private String order;

    public AskMeetingRoom(int id) {
        this.id = id;
    }

    public AskMeetingRoom(int id, String name, int buildingId, String detail) {
        this.id = id;
        this.name = name;
        this.buildingId = buildingId;
        this.detail = detail;
    }

    public AskMeetingRoom(int volume, String price, String name, String order) {
        this.volume = volume;
        this.price = price;
        this.name = name;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
