package com.android.renly.meetingreservation.api.bean;

public class AskUser {
    private int id;
    private String name;
    private String phone;
    private String email;
    private int role;
    private String company;
    private String password;
    private String type;
    private int page;
    private int size;
    private String order;

    public AskUser(int id) {
        this.id = id;
    }

    public AskUser(String phone) {
        this.phone = phone;
    }

    public AskUser(String type, String phone, String password) {
        this.type = type;
        this.phone = phone;
        this.password = password;
    }

    public AskUser(int id, String name, String phone, String email, int role, String company, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.company = company;
        this.password = password;
    }

    public AskUser(int page, int size, String order, String phone) {
        this.page = page;
        this.size = size;
        this.order = order;
        this.phone = phone;
    }

    public AskUser(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
