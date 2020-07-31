package com.scheduler.csheduler.config;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Constant {
    public static Timestamp getTimestamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date;
        Timestamp tt = null;
        try {
            date = sdf.parse(time);
            tt = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tt;
    }

    //根据map的value获取map的key  
    public List<Integer> getKey(Map<Integer,Integer> map, Integer value){
        List<Integer> key = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(value.equals(entry.getValue())){
                key.add(entry.getKey());
                System.out.println("key:"+entry.getKey());
            }
        }
        return key;
    }
}
