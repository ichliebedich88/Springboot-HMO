package com.scheduler.csheduler.controller;

import com.scheduler.csheduler.Result.Result;
import com.scheduler.csheduler.bean.Dag;
import com.scheduler.csheduler.SchService.DagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DagController {
    @Autowired
    private DagService dagService;

    @RequestMapping(value = "/getDagById/{id}")
    public Result getDagById(@PathVariable("id")Integer id) {
        Dag dag = dagService.getDagById(id);
        return Result.success().add("dag",dag);
    }
}
