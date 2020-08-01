package com.scheduler.csheduler.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scheduler.csheduler.Result.Result;
import com.scheduler.csheduler.SchService.*;
import com.scheduler.csheduler.bean.*;
import com.scheduler.csheduler.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class DagController {
    @Autowired
    private DagService dagService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private EdgeService edgeService;

    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @RequestMapping(value = "/getDagById")
    public Result getDagById(@RequestParam("id")Integer id) {
        Dag dag = dagService.getDagById(id);
        return Result.success().add("dag",dag);
    }
//    @RequestMapping(value = "/getDagById")
//    public Result getDagById() {
//        Dag dag = dagService.getDagById(2);
//        return Result.success().add("dag",dag);
//    }

//    @RequestMapping(value = "/getTasksByDagId/{id}")
//    public Result getTasksByDagId(@PathVariable("id")Integer id) {
//        List<Task> tasks  = taskService.getTasksByDagId(id);
//        return Result.success().add("tasks",tasks);
//    }

    @RequestMapping(value = "/dag/list")
    public Result getDags(){
        if (dagService.getAllDags()!=null) {
            List<Dag> allDags = dagService.getAllDags();
            List<Object> data = new ArrayList();
            for(Dag dag:allDags)
            {
                DagInfo info = new DagInfo();
                Integer id = dag.getId();
                info.setId(id);
                info.setDescribe(dag.getDescrib());
                info.setOwner(dag.getOwner());
                String tsStr = "";
                tsStr = sdf.format(dag.getCreateTime());
                info.setCreateTime(tsStr);
                //查tasks
                if (taskService.getTasksByDagId(id)!=null){
                    List<Object> arrayList = new ArrayList();
                    List<Task> tasks  = taskService.getTasksByDagId(id);
                    for(int i=0;i<tasks.size();i++){
                        arrayList.add(tasks.get(i).getId());
                    }
                    info.setTask(arrayList.toString());
                }
                else
                    info.setTask(null);
                if (edgeService.getEdgesByDagId(id)!=null){
                    //查edges
                    List<Edge> edges =edgeService.getEdgesByDagId(id);
                    StringBuffer aList = new StringBuffer();
                    int edgeLen = edges.size();
                    for(int i =0;i<edgeLen-1;i++)
                    {
//                        System.out.println("index:"+i);
//                        System.out.println("startNode:"+edges.get(i).getStartNode()+"    endNode:"+edges.get(i).getEndNode());
                        aList.append("("+edges.get(i).getStartNode().toString()+","+edges.get(i).getEndNode().toString()+"),");

                    }
                    if (edgeLen>0)
                        aList.append("("+edges.get(edgeLen-1).getStartNode().toString()+","+edges.get(edgeLen-1).getEndNode().toString()+")");
                    info.setEdge(aList.toString());
                }
                else
                    info.setEdge(null);
                data.add(info);
            }
            return Result.success().add("dag_list",data);
        }
        return Result.success();
    }

    @RequestMapping(value = "/dag/delete")
    public Result deleteDagInfo(@RequestParam("id") Integer id) {
        taskService.deleteTaskByDagId(id);
        edgeService.deleteEdgeByDagId(id);
        dagService.deleteDagById(id);
        return Result.success();
    }

    @RequestMapping(value = "/dag/add")
//    public Result addDagInfo(@RequestParam("node_list") List<TaskStr> node_list,@RequestParam("edge_list") List<String> edge_list,@RequestParam("dag_name") String dag_name){
//    public Result addDagInfo(@RequestBody List<TaskStr> node_list,@RequestBody List<String> edge_list,@RequestParam String dag_name){
      public Result addDagInfo(@RequestBody addDagParam params){
        String owner = "asministrator";
        String dag_name = params.getDag_name();
        List<TaskStr> node_list = params.getNode_list();
        List<String> edge_list = params.getEdge_list();
        if(dagService.addDag(dag_name,owner)<=0)
            return Result.error();
        Integer dagId = dagService.getNewDag(dag_name,owner).getId();
        Map<String,Integer> node_id_dict = new HashMap<String,Integer>();
        for(TaskStr node:node_list)
        {
            if(taskService.addTask(node.getTask(),dagId,node.getType(),Float.parseFloat(node.getOccupy()))<=0){
                dagService.deleteDagById(dagId);
                return Result.error();
            }
            Integer laTaskId = taskService.getNewTask(node.getTask(),dagId,Float.parseFloat(node.getOccupy()),node.getType()).getId();
            node_id_dict.put(node.getId(),laTaskId);
        }
        for(String edgeInfo:edge_list)
        {
            String [] edges = edgeInfo.split("\\s+");
            String n1_name = edges[2];
            String n2_name = edges[1];
            Integer startNode = node_id_dict.get(n1_name.substring(9));
            Integer endNode = node_id_dict.get(n2_name.substring(8));
            if(edgeService.addEdge(dagId,startNode,endNode)<=0)
            {
                edgeService.deleteTheEdge(dagId,startNode,endNode);
                dagService.deleteDagById(dagId);
                return Result.error();
            }
        }
        return Result.success();
    }


    @RequestMapping(value = "/dag/detail")
    public Result getDagDetail(@RequestParam("id")Integer id){
        if(taskService.getTasksByDagId(id)!=null&&edgeService.getEdgesByDagId(id)!=null){
            //查tasks
            List<Task> tasks  = taskService.getTasksByDagId(id);
            //查edges
            List<Edge> edges =edgeService.getEdgesByDagId(id);
            List<Object> edgeList = new ArrayList();
            for(Edge edge:edges)
            {
                int startAndEnd[] =  new int[2];
                startAndEnd[0]=(edge.getStartNode());
                startAndEnd[1]=(edge.getEndNode());
                edgeList.add(startAndEnd);
            }

            //节点的id
            ArrayList<Integer> tmpTasks = new ArrayList<Integer>();
            for(int i=0;i<tasks.size();i++) {
//                System.out.print(tasks.get(i).getId()+"   ");
                tmpTasks.add(tasks.get(i).getId());//开始复制一个list的内容到另外一个list
            }

            //存放分层后的结果
            List<ArrayList<Integer>> levelArr = new ArrayList<ArrayList<Integer>>();

            //存放节点的入度
            Map<Integer,Integer> indegreeMap = new HashMap<Integer,Integer>();
            for (Integer task:tmpTasks)
            {
                int indegree = 0;
                for (Edge edge:edges)
                {
                    if(edge.getEndNode()==task)
                        indegree++;
                }
                indegreeMap.put(task,indegree);
            }

            //计算分层结果
            while(!indegreeMap.isEmpty())
            {
                //当前入度为0的节点
                ArrayList<Integer> curList = new ArrayList<Integer>();
                Constant constant = new Constant();
                curList.addAll(constant.getKey(indegreeMap,0));
                //加入分层list
                levelArr.add(curList);
                //将入度为0的节点从indegreeMap移除
                Collection<Integer> col = indegreeMap.values();
                while(true == col.contains(0)) {
                    col.remove(0);
                }
                //重新计算入度
                for(Integer cur:curList){
                    for (Edge edge:edges)
                    {
                        if(edge.getStartNode()==cur)
                        {
                            if(indegreeMap.containsKey(edge.getEndNode())){

                                Integer newIndegree = indegreeMap.get(edge.getEndNode())-1;
                                indegreeMap.put(edge.getEndNode(), newIndegree);}
                        }
                    }
                }
            }

            List<DagDetail> node_list = new ArrayList<DagDetail>();
            for (Task task:tasks) {
                DagDetail dagDetail = new DagDetail();
                dagDetail.setId(task.getId());
                dagDetail.setTask(task.getName());
                dagDetail.setResource_type(task.getType());
                dagDetail.setOccupy_time(task.getOccupyTime());

                int len = levelArr.size();
                int x = 0, y = 0;
                boolean flagNow = false;
                //计算节点对应的i,j
                for (int i = 0; i < len; i++) {
                    ArrayList<Integer> listNow = new ArrayList<Integer>();
                    listNow.addAll(levelArr.get(i));
                    int curLen = listNow.size();
                    for (int j = 0; j < curLen; j++) {
                        if (levelArr.get(i).get(j) == task.getId()) {
                            x = i;
                            y = j;
                            flagNow = true;
                            break;
                        }
                    }
                    if (flagNow)
                        break;
                }
                dagDetail.setLeft(320*x);
                dagDetail.setTop(80 + 400*y);
                //当前节点的入度节点
                List<Integer> inTask = new ArrayList<Integer>();
                for (Edge edge : edges) {
                    if (edge.getEndNode() == task.getId()) {
                        inTask.add(edge.getStartNode());
//                        System.out.print("入度:" + edge.getStartNode());
                    }
                }
//                System.out.println();
                //当前节点的出度节点
                List<Integer> outTask = new ArrayList<Integer>();
                for (Edge edge : edges) {
                    if (edge.getStartNode() == task.getId()) {
                        outTask.add(edge.getEndNode());
//                        System.out.print("出度:" + edge.getEndNode());
                    }
                }
                //input json
                JSONArray injsonArray = new JSONArray();
                for (int i = 0; i < inTask.size(); i++) {
                    JSONObject object = new JSONObject();
                    object.put("node_id", inTask.get(i));
//                    System.out.println("input:" + object);
                    injsonArray.add(object);
                }
                //output json
                JSONArray outjsonArray = new JSONArray();
                for (int i = 0; i < outTask.size(); i++) {
                    JSONObject object = new JSONObject();
                    object.put("node_id", outTask.get(i));
//                    System.out.println("output:" + object);
                    outjsonArray.add(object);
                }
                dagDetail.setInput(injsonArray);
                dagDetail.setOutput(outjsonArray);
                node_list.add(dagDetail);
            }
            JSONObject returnJson = new JSONObject();
            returnJson.put("task_list",node_list);
            returnJson.put("edges",edgeList);
 //           return Result.success().add("data",returnJson);
            return Result.success().add("task_list",node_list).add("edges",edgeList);
        }
        return Result.success();
    }
}
