package com.scheduler.csheduler.bean;

import java.sql.Timestamp;

public class Res_Room_Task {
    private Integer id;
    private String name;
    private String type;
    private String brand;
    private Integer room_id;
    private Integer status;
    private String roomName;
    private Float occupy_time;

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

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
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

    public Float getOccupy_time() {
        return occupy_time;
    }

    public void setOccupy_time(Float occupy_time) {
        this.occupy_time = occupy_time;
    }
}
