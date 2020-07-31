package com.scheduler.csheduler.SchService;

import com.scheduler.csheduler.dao.DagDao;
import com.scheduler.csheduler.bean.Dag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DagService implements DagDao {
    @Autowired
    private DagDao dagDao;

    @Override
    public Dag getDagById(Integer id) {
        return dagDao.getDagById(id);
    }
}
