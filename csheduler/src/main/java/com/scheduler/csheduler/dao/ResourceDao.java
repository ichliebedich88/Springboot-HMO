package com.scheduler.csheduler.dao;

import com.scheduler.csheduler.bean.Res_Room_Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourceDao {
    @Select("select resource.id,resource.name,resource.type,resource.brand,resource.room_id," +
            "room.name roomName,resource.status,task.occupy_time from resource left join room" +
            " on resource.room_id = room.id left join task on resource.task_id=task.id")
    List<Res_Room_Task> getResources();


}
