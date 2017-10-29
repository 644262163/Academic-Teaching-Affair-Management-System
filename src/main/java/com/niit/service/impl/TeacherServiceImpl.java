package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Teacher;
import com.niit.dao.TeacherDao;
import com.niit.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

    @Resource
    private TeacherDao teacherDao;
    
    @Override
    public Teacher selectTeacherById(String id) {
        // TODO 自动生成的方法存根
        Teacher teacher = teacherDao.selectTeacherById(id);
        return teacher;
    }

    @Override
    public List<Teacher> selectTeacherList() {
        // TODO 自动生成的方法存根
        List<Teacher> list = teacherDao.selectTeacherList();
        return list;
    }

    @Override
    public PageBean<Teacher> selectTeacherListByPage(Teacher teacher, PageBean<Teacher> pageBean) {
        // TODO 自动生成的方法存根
        //查询分页结果
        pageBean.setResult(teacherDao.selectTeacherListByPage(teacher, pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(teacherDao.selectTotal(teacher));
        return pageBean;
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        // TODO 自动生成的方法存根
        Integer i = teacherDao.updateTeacher(teacher);
        return i;
    }

    @Override
    public Integer deleteTeacherById(String id) {
        // TODO 自动生成的方法存根
        Integer i = teacherDao.deleteTeacherById(id);
        return i;
    }

    @Override
    public Integer insertTeacher(Teacher teacher) {
        // TODO 自动生成的方法存根
        Integer i = teacherDao.insertTeacher(teacher);
        return i;
    }

}
