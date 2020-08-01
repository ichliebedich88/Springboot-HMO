package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.bean.Edge;
import com.scheduler.csheduler.dao.EdgeDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService implements EdgeDao {
    @Autowired
    EdgeDao edgeDao;

    @Override
    public List<Edge> getEdgesByDagId(Integer id) {
        return edgeDao.getEdgesByDagId(id);
    }

    @Override
    public int deleteEdgeByDagId(Integer id) {
        return edgeDao.deleteEdgeByDagId(id);
    }

    @Override
    public int addEdge(Integer dagId,Integer satrtNode,Integer endNode){
        return edgeDao.addEdge(dagId,satrtNode,endNode);
    }

    @Override
    public int deleteTheEdge(Integer id,Integer startNode,Integer endNode){
        return edgeDao.deleteTheEdge(id,startNode,endNode);
    }
}
