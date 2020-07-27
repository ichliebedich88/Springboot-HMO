package com.scheduler.csheduler.bean;

import java.sql.Timestamp;

public class Task {
    private Integer id;
    private String name;
    private Integer dag_id;
    private String type;
    private Float occupy_time;
    private Timestamp create_time;
    private Timestamp last_update_time;

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

    public Integer getDag_id() {
        return dag_id;
    }

    public void setDag_id(Integer dag_id) {
        this.dag_id = dag_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getOccupy_time() {
        return occupy_time;
    }

    public void setOccupy_time(Float occupt_time) {
        this.occupy_time = occupy_time;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(Timestamp last_update_time) {
        this.last_update_time = last_update_time;
    }
}
