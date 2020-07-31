package com.scheduler.csheduler.dao;

import com.scheduler.csheduler.bean.Dag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DagDao {
        @Select("select * from dag where id =#{id}")
        Dag getDagById(@Param("id") Integer id);
}
