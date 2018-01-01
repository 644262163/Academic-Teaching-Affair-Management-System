package com.niit.service.impl;

import com.niit.bean.PageBean;
import com.niit.bean.StudentCourse;
import com.niit.dao.StudentCourseDao;
import com.niit.service.StudentCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentCourseService")
public class StudentCourseServiceImpl implements StudentCourseService{

    @Resource
    private StudentCourseDao studentCourseDao;

    @Override
    public List<StudentCourse> selectStudentCourseList() {
        List<StudentCourse> list = studentCourseDao.selectStudentCourseList();
        return list;
    }

    @Override
    public PageBean<StudentCourse> selectStudentCourseListByPage(StudentCourse studentCourse, PageBean<StudentCourse> pageBean) {
        //查询分页结果
        pageBean.setResult(studentCourseDao.selectStudentCourseListByPage(studentCourse, pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(studentCourseDao.selectTotal(studentCourse));
        return pageBean;
    }

    @Override
    public Integer updateStudentCourse(StudentCourse studentCourse) {
        Integer i = studentCourseDao.updateStudentCourse(studentCourse);
        return i;
    }

    @Override
    public Integer deleteStudentCourseById(String id) {
        Integer i = studentCourseDao.deleteStudentCourseById(id);
        return i;
    }

    @Override
    public Integer insertStudentCourse(StudentCourse studentCourse) {
        Integer i = studentCourseDao.insertStudentCourse(studentCourse);
        return i;
    }
}
