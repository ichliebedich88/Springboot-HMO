package com.scheduler.csheduler.bean;

import java.sql.Timestamp;

public class Dag {
    private Integer id;
    private String describ;
    private Integer roomId;
    private String owner;
    private Timestamp createTime;
    private Timestamp lasUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLasUpdateTime() {
        return lasUpdateTime;
    }

    public void setLasUpdateTime(Timestamp lasUpdateTime) {
        this.lasUpdateTime = lasUpdateTime;
    }
}
