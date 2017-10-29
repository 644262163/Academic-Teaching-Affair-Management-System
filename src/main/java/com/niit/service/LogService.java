package com.niit.service;

import java.util.List;

import com.niit.bean.Log;
import com.niit.bean.PageBean;

public interface LogService {
    public List<Log> selectLogList();
    public PageBean<Log> selectLogListByPage(Log log, PageBean<Log> pageBean);
    
    public Integer insertLog(Log log);
}
