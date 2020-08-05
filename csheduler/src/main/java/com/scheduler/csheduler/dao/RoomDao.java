package com.scheduler.csheduler.dao;

import com.scheduler.csheduler.bean.Dag;
import com.scheduler.csheduler.bean.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoomDao {
    @Select("select * from room where status =0")
    List<Room> getRoomsFree();

    @Update("update room set status=1 where id=#{id}")
    int bindRoom(Integer id);

    @Select("select * from room where status =1")
    List<Room> getRoomsBound();

    @Update("update room set status=0 where id=#{id}")
    int unboundRoom(Integer id);

    @Select("select * from room")
    List<Room> getAllRoom();

    @Select("select * from dag where room_id=#{roomId}")
    Dag getDagByRoomId(@Param("roomId")Integer roomId);
}
