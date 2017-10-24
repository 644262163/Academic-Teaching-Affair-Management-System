package com.niit.service;

import java.util.List;

import com.niit.bean.PageBean;
import com.niit.bean.Teacher;

public interface TeacherService {
    public Teacher selectTeacherById(String id);
    public List<Teacher> selectTeacherList();
    public PageBean<Teacher> selectTeacherListByPage(PageBean<Teacher> pageBean);
    
    public Integer updateTeacher(Teacher teacher);
    
    public Integer deleteTeacherById(String id);
    
    public Integer insertTeacher(Teacher teacher);
}