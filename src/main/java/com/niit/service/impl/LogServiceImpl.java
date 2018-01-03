package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.Log;
import com.niit.bean.PageBean;
import com.niit.dao.LogDao;
import com.niit.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;
    
    @Override
    public List<Log> selectLogList() {
        // TODO 自动生成的方法存根
        List<Log> list = logDao.selectLogList();
        return list;
    }

    @Override
    public List<Log> selectLogListByLog(Log log) {
        List<Log> list = logDao.selectLogListByLog(log);
        return list;
    }

    @Override
    public PageBean<Log> selectLogListByPage(Log log, PageBean<Log> pageBean) {
        // TODO 自动生成的方法存根
        //查询分页结果
        pageBean.setResult(logDao.selectLogListByPage(log, pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(logDao.selectTotal(log));
        return pageBean;
    }

    @Override
    public Integer insertLog(Log log) {
        // TODO 自动生成的方法存根
        Integer i = logDao.insertLog(log);
        return i;
    }

}
