package com.android.renly.meetingreservation.api.bean;

public class Folder {
    private String name;
    private long create_time;
    /**
     * 文件类型
     * 1.文件夹
     * 2.文件
     */
    private int type;
    /**
     * 文档格式
     */
    private String format;

    public Folder(String name, long create_time, int type) {
        this.name = name;
        this.create_time = create_time;
        this.type = type;
    }

    public Folder(String name, long create_time, int type, String format) {
        this.name = name;
        this.create_time = create_time;
        this.type = type;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
