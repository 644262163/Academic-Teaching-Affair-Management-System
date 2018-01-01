package com.niit.service.impl;

import com.niit.bean.PageBean;
import com.niit.bean.StudentCourse;
import com.niit.dao.CourseDao;
import com.niit.dao.StudentCourseDao;
import com.niit.dao.StudentDao;
import com.niit.service.StudentCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.lucene.queries.function.valuesource.LiteralValueSource.hash;

@Service("studentCourseService")
public class StudentCourseServiceImpl implements StudentCourseService{

    @Resource
    private StudentCourseDao studentCourseDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseDao courseDao;

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

    @Override
    public PageBean<Map<String, Object>> selectStudentCourseList(StudentCourse studentCourse, PageBean<Map<String, Object>> pageBean) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<StudentCourse> list = studentCourseDao.selectStudentCourseListByPage(studentCourse, pageBean.getStart(), pageBean.getEnd());
        for(StudentCourse o: list) {
            Map<String, Object> objs = new HashMap<String, Object>();
            objs.put("id", o.getId());
            objs.put("studentId", o.getStudentId());
            objs.put("courseId", o.getCourseId());
            objs.put("term", o.getTerm());
            objs.put("information", o.getInformation());
            objs.put("studentName", studentDao.selectStudentById(o.getStudentId()).getName());
            objs.put("courseName", courseDao.selectCourseById(o.getCourseId()).getName());
            resultList.add(objs);
        }
        pageBean.setResult(resultList);
        //查询记录总数
        pageBean.setTotal(studentCourseDao.selectTotal(studentCourse));
        return pageBean;
    }
}
