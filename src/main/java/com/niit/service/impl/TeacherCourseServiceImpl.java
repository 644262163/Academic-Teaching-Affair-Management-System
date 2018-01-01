package com.niit.service.impl;

import com.niit.bean.PageBean;
import com.niit.bean.TeacherCourse;
import com.niit.dao.TeacherCourseDao;
import com.niit.service.TeacherCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService{

    @Resource
    private TeacherCourseDao teacherCourseDao;

    @Override
    public List<TeacherCourse> selectTeacherCourseList() {
        List<TeacherCourse> list = teacherCourseDao.selectTeacherCourseList();
        return list;
    }

    @Override
    public PageBean<TeacherCourse> selectTeacherCourseListByPage(TeacherCourse teacherCourse, PageBean<TeacherCourse> pageBean) {
        //查询分页结果
        pageBean.setResult(teacherCourseDao.selectTeacherCourseListByPage(teacherCourse, pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(teacherCourseDao.selectTotal(teacherCourse));
        return pageBean;
    }

    @Override
    public Integer updateTeacherCourse(TeacherCourse teacherCourse) {
        Integer i = teacherCourseDao.updateTeacherCourse(teacherCourse);
        return i;
    }

    @Override
    public Integer deleteTeacherCourseById(String id) {
        Integer i = teacherCourseDao.deleteTeacherCourseById(id);
        return i;
    }

    @Override
    public Integer insertTeacherCourse(TeacherCourse teacherCourse) {
        Integer i = teacherCourseDao.insertTeacherCourse(teacherCourse);
        return i;
    }
}
