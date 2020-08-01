package com.scheduler.csheduler.dao;

import com.scheduler.csheduler.bean.Dag;
import com.scheduler.csheduler.bean.DagInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DagDao {
        @Select("select * from dag where id =#{id}")
        Dag getDagById(@Param("id") Integer id);

        @Select("select * from dag")
        List<Dag> getAllDags();

        @Delete("delete from dag where id=#{id}")
        int deleteDagById(@Param("id") Integer id);

        @Insert("insert into dag(describ,owner) values(#{describ},#{owner})")
        int addDag(@Param("describ") String describ,@Param("owner")String owner);

        @Select("select * from dag where describ =#{describ} and owner =#{owner} order by create_time desc limit 1")
        Dag getNewDag(@Param("describ") String describ,@Param("owner")String owner);

//        @Delete("delete from dag where id=#{id}")
//        int deleteDag(@Param("id") Integer id);
}
