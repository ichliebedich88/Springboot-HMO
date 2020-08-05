package com.scheduler.csheduler.dao;


import com.scheduler.csheduler.bean.Edge;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EdgeDao {
    @Select("select * from edge where edge.dag_id =#{id}")
    List<Edge> getEdgesByDagId(@Param("id") Integer id);

    @Delete("delete from edge where dag_id=#{id}")
    int deleteEdgeByDagId(@Param("id") Integer id);

    @Insert("insert into edge(dag_id,start_node,end_node) values(#{dagId},#{satrtNode},#{endNode})")
    int addEdge(@Param("dagId") Integer dagId,@Param("satrtNode") Integer satrtNode,@Param("endNode") Integer endNode);

    @Delete("delete from edge where dag_id=#{id} and start_node=#{startNode} and end_note=#{endNode} order by create_time desc limit 1")
    int deleteTheEdge(@Param("id") Integer id,@Param("startNode") Integer startNode,@Param("endNode") Integer endNode);
}
