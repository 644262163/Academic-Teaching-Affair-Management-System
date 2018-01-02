package com.niit.service.impl;

import com.niit.bean.PageBean;
import com.niit.bean.TeacherCourse;
import com.niit.dao.CourseDao;
import com.niit.dao.TeacherCourseDao;
import com.niit.dao.TeacherDao;
import com.niit.service.TeacherCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("teacherCourseService")
public class TeacherCourseServiceImpl implements TeacherCourseService{

    @Resource
    private TeacherCourseDao teacherCourseDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private CourseDao courseDao;

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

    @Override
    public PageBean<Map<String, Object>> selectTeacherCourseList(TeacherCourse teacherCourse, PageBean<Map<String, Object>> pageBean) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<TeacherCourse> list = teacherCourseDao.selectTeacherCourseListByPage(teacherCourse, pageBean.getStart(), pageBean.getEnd());
        for(TeacherCourse o: list) {
            Map<String, Object> objs = new HashMap<String, Object>();
            objs.put("id", o.getId());
            objs.put("teacherId", o.getTeacherId());
            objs.put("courseId", o.getCourseId());
            objs.put("term", o.getTerm());
            objs.put("information", o.getInformation());
            objs.put("teacherName", teacherDao.selectTeacherById(o.getTeacherId()).getName());
            objs.put("courseName", courseDao.selectCourseById(o.getCourseId()).getName());
            resultList.add(objs);
        }
        pageBean.setResult(resultList);
        //查询记录总数
        pageBean.setTotal(teacherCourseDao.selectTotal(teacherCourse));
        return pageBean;
    }
}
