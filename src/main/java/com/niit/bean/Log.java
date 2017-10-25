package com.niit.bean;

import java.util.Date;

public class Log {
    private String id;
    private String userId;
    private String ip;
    private String url;
    private String parameter;
    private Date time;
    private Long length;
    private String module;
    private String method;
    private String result;
    private String message;
    
    public Log() {
        super();
    }
    
    public Log(String userId, String ip, String url, String parameter, Date time, Long length, String module,
            String method, String result, String message) {
        super();
        this.userId = userId;
        this.ip = ip;
        this.url = url;
        this.parameter = parameter;
        this.time = time;
        this.length = length;
        this.module = module;
        this.method = method;
        this.result = result;
        this.message = message;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getParameter() {
        return parameter;
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public Long getLength() {
        return length;
    }
    public void setLength(Long length) {
        this.length = length;
    }
    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
