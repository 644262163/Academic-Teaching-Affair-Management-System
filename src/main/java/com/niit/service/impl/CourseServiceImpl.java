package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.Course;
import com.niit.bean.PageBean;
import com.niit.dao.CourseDao;
import com.niit.service.CourseService;

@Service("courseService")
public class CourseServiceImpl implements CourseService{

    @Resource
    private CourseDao courseDao;
    
    @Override
    public Course selectCourseById(String id) {
        // TODO �Զ����ɵķ������
        Course course = courseDao.selectCourseById(id);
        return course;
    }

    @Override
    public List<Course> selectCourseList() {
        // TODO �Զ����ɵķ������
        List<Course> list = courseDao.selectCourseList();
        return list;
    }

    @Override
    public PageBean<Course> selectCourseListByPage(PageBean<Course> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(courseDao.selectCourseListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(courseDao.selectTotal());
        return pageBean;
    }

    @Override
    public Integer updateCourse(Course course) {
        // TODO �Զ����ɵķ������
        Integer i = courseDao.updateCourse(course);
        return i;
    }

    @Override
    public Integer deleteCourseById(String id) {
        // TODO �Զ����ɵķ������
        Integer i = courseDao.deleteCourseById(id);
        return i;
    }

    @Override
    public Integer insertCourse(Course course) {
        // TODO �Զ����ɵķ������
        Integer i = courseDao.insertCourse(course);
        return i;
    }

}
