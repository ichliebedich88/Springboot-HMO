package com.scheduler.csheduler.bean;

import java.sql.Timestamp;

public class Task {
    private Integer id;
    private String name;
    private Integer dagId;
    private String type;
    private Float occupyTime;
    private Timestamp createTime;
    private Timestamp lastUpdateTime;

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

    public Integer getDagId() {
        return dagId;
    }

    public void setDagId(Integer dagId) {
        this.dagId = dagId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getOccupyTime() {
        return occupyTime;
    }

    public void setOccupyTime(Float occupyTime) {
        this.occupyTime = occupyTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
