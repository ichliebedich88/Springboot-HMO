package com.scheduler.csheduler.bean;

import java.sql.Timestamp;

public class Edge {
    private Integer id;
    private Integer dag_id;
    private Integer start_node;
    private Integer end_node;
    private Timestamp create_time;
    private Timestamp last_update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDag_id() {
        return dag_id;
    }

    public void setDag_id(Integer dag_id) {
        this.dag_id = dag_id;
    }

    public Integer getStart_node() {
        return start_node;
    }

    public void setStart_node(Integer start_node) {
        this.start_node = start_node;
    }

    public Integer getEnd_node() {
        return end_node;
    }

    public void setEnd_node(Integer end_node) {
        this.end_node = end_node;
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
