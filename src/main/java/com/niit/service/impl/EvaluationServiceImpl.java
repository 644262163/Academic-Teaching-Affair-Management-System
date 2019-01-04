package com.niit.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.niit.bean.TeacherCourse;
import com.niit.dao.*;
import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Evaluation;
import com.niit.service.EvaluationService;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService{

    @Resource
    private EvaluationDao evaluationDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private CourseDao courseDao;
    @Resource
    private TeacherCourseDao teacherCourseDao;
    
    @Override
    public Evaluation selectEvaluationByStudentId(String studentId) {
        // TODO 自动生成的方法存根
        Evaluation evaluation = evaluationDao.selectEvaluationByStudentId(studentId);
        return evaluation;
    }

    @Override
    public Evaluation selectEvaluationByCourseId(String courseId) {
        // TODO 自动生成的方法存根
        Evaluation evaluation = evaluationDao.selectEvaluationByCourseId(courseId);
        return evaluation;
    }

    @Override
    public List<Evaluation> selectEvaluationList() {
        // TODO 自动生成的方法存根
        List<Evaluation> list = evaluationDao.selectEvaluationList();
        return list;
    }

    @Override
    public PageBean<Evaluation> selectEvaluationListByPage(Evaluation evaluation, PageBean<Evaluation> pageBean) {
        // TODO 自动生成的方法存根
        //查询分页结果
        pageBean.setResult(evaluationDao.selectEvaluationListByPage(evaluation, pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(evaluationDao.selectTotal(evaluation));
        return pageBean;
    }

    @Override
    public Integer updateEvaluation(Evaluation evaluation) {
        // TODO 自动生成的方法存根
        Integer i = evaluationDao.updateEvaluation(evaluation);
        return i;
    }

    @Override
    public Integer deleteEvaluationById(String studentId, String courseId) {
        // TODO 自动生成的方法存根
        Integer i = evaluationDao.deleteEvaluationById(studentId, courseId);
        return i;
    }

    @Override
    public Integer insertEvaluation(Evaluation evaluation) {
        // TODO 自动生成的方法存根
        Integer i = evaluationDao.insertEvaluation(evaluation);
        return i;
    }

    @Override
    public PageBean<Map<String, Object>> selectEvaluationListByStudent(Evaluation evaluation, PageBean<Map<String, Object>> pageBean) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Evaluation> list = evaluationDao.selectEvaluationListByPage(evaluation, pageBean.getStart(), pageBean.getEnd());
        for(Evaluation o: list) {
            Map<String, Object> objs = new HashMap<String, Object>();
            objs.put("studentId", o.getStudentId());
            objs.put("courseId", o.getCourseId());
            objs.put("term", o.getTerm());
            objs.put("score", o.getScore());
            objs.put("studentName", studentDao.selectStudentById(o.getStudentId()).getName());
            objs.put("courseName", courseDao.selectCourseById(o.getCourseId()).getName());
            resultList.add(objs);
        }
        pageBean.setResult(resultList);
        //查询记录总数
        pageBean.setTotal(evaluationDao.selectTotal(evaluation));
        return pageBean;
    }

    @Override
    public PageBean<Map<String, Object>> selectEvaluationListByTeacher(String teacherId, Evaluation evaluation, PageBean<Map<String, Object>> pageBean) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<TeacherCourse> list = teacherCourseDao.selectTeacherCourseListByTeacherId(teacherId);
        Set<String> set = new HashSet<String>();
        for(TeacherCourse o: list) {
            if(set.contains(o.getCourseId())) continue;
            set.add(o.getCourseId());
            evaluation.setCourseId(o.getCourseId());
            List<Evaluation> scoreList = evaluationDao.selectEvaluationListByPage(evaluation, pageBean.getStart(), pageBean.getEnd());
            for(Evaluation s: scoreList) {
                Map<String, Object> objs = new HashMap<String, Object>();
                objs.put("studentId", s.getStudentId());
                objs.put("courseId", s.getCourseId());
                objs.put("term", s.getTerm());
                objs.put("score", s.getScore());
                objs.put("studentName", studentDao.selectStudentById(s.getStudentId()).getName());
                objs.put("courseName", courseDao.selectCourseById(o.getCourseId()).getName());
                objs.put("teacherId", teacherId);
                objs.put("teacherName", teacherDao.selectTeacherById(teacherId).getName());
                resultList.add(objs);
            }
        }
        pageBean.setResult(resultList);
        //查询记录总数
        pageBean.setTotal(evaluationDao.selectTotal(evaluation));
        return pageBean;
    }

}
