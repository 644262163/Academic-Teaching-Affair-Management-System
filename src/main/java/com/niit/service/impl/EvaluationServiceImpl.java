package com.niit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.niit.dao.CourseDao;
import com.niit.dao.StudentDao;
import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Evaluation;
import com.niit.dao.EvaluationDao;
import com.niit.service.EvaluationService;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService{

    @Resource
    private EvaluationDao evaluationDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseDao courseDao;
    
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
    public PageBean<Map<String, Object>> selectEvaluationList(Evaluation evaluation, PageBean<Map<String, Object>> pageBean) {
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

}
