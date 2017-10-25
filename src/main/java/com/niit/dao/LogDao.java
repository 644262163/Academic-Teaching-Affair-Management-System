package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Log;

public interface LogDao {
    public List<Log> selectLogList();
    public List<Log> selectLogListByPage(@Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal();
    
    public Integer insertLog(Log log);
}
