package com.scheduler.csheduler.bean;

import java.util.List;

public class DagDetail {
    Integer id;
    String task;
    String resource_type;
    Float occupy_time;
    Integer top;
    Integer left;
    List<Object> input;
    List<Object> output;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public Float getOccupy_time() {
        return occupy_time;
    }

    public void setOccupy_time(Float occupy_time) {
        this.occupy_time = occupy_time;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public List<Object> getInput() {
        return input;
    }

    public void setInput(List<Object> input) {
        this.input = input;
    }

    public List<Object> getOutput() {
        return output;
    }

    public void setOutput(List<Object> output) {
        this.output = output;
    }
}
