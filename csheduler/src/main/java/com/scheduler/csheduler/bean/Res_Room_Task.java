package com.scheduler.csheduler.bean;

public class Res_Room_Task {
    private Integer id;
    private String name;
    private String type;
    private String brand;
    private Integer roomId;
    private String roomName;
    private Integer status;
    private Float occupyTime;

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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Float getOccupyTime() {
        return occupyTime;
    }

    public void setOccupyTime(Float occupyTime) {
        this.occupyTime = occupyTime;
    }
}
