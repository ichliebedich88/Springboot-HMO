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

}
