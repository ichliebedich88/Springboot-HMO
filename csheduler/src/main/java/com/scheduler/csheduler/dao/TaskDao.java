package com.scheduler.csheduler.dao;

import com.scheduler.csheduler.bean.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TaskDao {

    @Select("select * from task where task.dag_id =#{id}")
    List<Task> getTasksByDagId(@Param("id") Integer id);

    @Delete("delete from task where dag_id=#{id}")
    int deleteTaskByDagId(@Param("id") Integer id);

    @Insert("insert into Task(name,dag_id,type,occupy_time) values(#{name},#{dag_id},#{type},#{occupy_time})")
    int addTask(@Param("name") String name,@Param("dag_id") Integer dag_id,@Param("type") String type,@Param("occupy_time") Float occupy_time);

    @Select("select * from task where name =#{name} and dag_id =#{dagId} and type = #{type} and occupy_time = #{occupyTime} order by create_time desc limit 1")
    Task getNewTask(@Param("name") String name, @Param("dagId")Integer dagId, @Param("occupyTime")Float occupyTime, @Param("type")String type);
}

