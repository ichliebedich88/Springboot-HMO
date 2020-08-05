package com.scheduler.csheduler.bean;

public class EditResource {
    private Integer id;
    private String name;
    private String type;
    private String brand;
    //private String room;
    private Integer room;

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer roomId) {
        this.room = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
