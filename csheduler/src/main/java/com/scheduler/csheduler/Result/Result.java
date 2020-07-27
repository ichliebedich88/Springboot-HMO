package com.scheduler.csheduler.Result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回的Json数据封装
 */
@NoArgsConstructor
public class Result {
    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<String, Object>();
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 成功时调用
     * 默认状态码:200
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("处理成功");
        return result;
    }

    /**
     * 失败时调用
     * 默认状态码:500
     */
    public static Result error() {
        Result result = new Result();
        result.setCode(500);
        result.setMsg("处理失败");
        return result;
    }

    /**
     * 设置数据
     * @param key
     * @param value
     * @return
     */
    public Result add(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}