package com.scheduler.csheduler.bean;

import java.util.List;

public class addDagParam {
    List<TaskStr> node_list;
    List<String> edge_list;
    String dag_name;

    public List<TaskStr> getNode_list() {
        return node_list;
    }

    public void setNode_list(List<TaskStr> node_list) {
        this.node_list = node_list;
    }

    public List<String> getEdge_list() {
        return edge_list;
    }

    public void setEdge_list(List<String> edge_list) {
        this.edge_list = edge_list;
    }

    public String getDag_name() {
        return dag_name;
    }

    public void setDag_name(String dag_name) {
        this.dag_name = dag_name;
    }
}
