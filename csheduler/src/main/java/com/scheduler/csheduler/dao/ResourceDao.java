package com.scheduler.csheduler.dao;

import com.scheduler.csheduler.bean.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourceDao {
    @Select("select * from resource")
    List<Resource> getAllResource();

    @Select("select * from room where id = #{id}")
    Room getRoomById(@Param("id") Integer id);

    @Select("select * from room where name = #{name}")
    Room getRoomByName(@Param("name") String name);

    @Select("select * from resource where id = #{id}")
    Resource getResourceById(@Param("id") Integer id);

    @Update("update resource set type=#{type},brand=#{brand},room_id=#{roomId},name=#{name} where id=#{id}")
    int edit(Resource resource);//编辑resource记录

    @Delete("delete from resource where id=#{id}")
    int delete(@Param("id") Integer id);

    @Insert("insert into resource(id,type,brand,room_id,name) values(#{id},#{type},#{brand},#{roomId},#{name})")
    int add_res(Resource resource);

    //jinming
    @Select("select * from resource where room_id = #{id}")
    List<Resource> getResourcesInRoom(@Param("id") Integer id);

    //jinming
    @Select("select * from resource where status = 1")
    List<Resource> getResourceUsed();

    //jinmiing
    @Update("update resource set status=0,dag_id=null,task_id=null where id=#{id}")
    int unboundRes(Integer id);

    @Select("select * from resource where room_id = #{roomId}")
    List<Resource> getResByRoomId(@Param("roomId") Integer roomId);

    //绑定资源
    @Update("update resource set dag_id=#{dagId},task_id=#{taskId},status=1 where id=#{id}")
    int bindResource(@Param("id")Integer id,@Param("taskId")Integer taskId,@Param("dagId")Integer dagId);

    //解绑资源
    @Update("update resource set dag_id=null,task_id=null,status=0 where id=#{id}")
    int unbindResource(@Param("id") Integer id);

    //通过类型得到资源
    @Select("select * from resource where type=#{type} and status=0")
    List<Resource> getResByType(@Param("type") String type);

    @Select("select * from task where id = #{id}")
    Task getTaskById(@Param("id") Integer id);

    @Insert("insert into log " +
            "(dag_id,task_id,res_id,owner,task_name,res_name,res_room,status) " +
            "values" +
            "(#{dagId},#{taskId},#{resId},#{owner},#{taskName},#{resName},#{resRoom},#{status})")
    int updateLog(Log log);
}
