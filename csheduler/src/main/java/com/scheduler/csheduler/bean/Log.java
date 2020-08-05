package com.scheduler.csheduler.bean;

import java.sql.Timestamp;

public class Log {
    private Integer id;
    private Integer dagId;
    private Integer taskId;
    private Integer resId;
    private String owner;
    private String taskName;
    private String resName;
    private Integer resRoom;
    private Timestamp curTime;
    private Integer status;
    private Timestamp lastUpdateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDagId() {
        return dagId;
    }

    public void setDagId(Integer dagId) {
        this.dagId = dagId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Integer getResRoom() {
        return resRoom;
    }

    public void setResRoom(Integer resRoom) {
        this.resRoom = resRoom;
    }

    public Timestamp getCurTime() {
        return curTime;
    }

    public void setCurTime(Timestamp curTime) {
        this.curTime = curTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}