package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Evaluation;
import com.niit.dao.EvaluationDao;
import com.niit.service.EvaluationService;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService{

    @Resource
    private EvaluationDao evaluationDao;
    
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
    public PageBean<Evaluation> selectEvaluationListByPage(PageBean<Evaluation> pageBean) {
        // TODO 自动生成的方法存根
        //查询分页结果
        pageBean.setResult(evaluationDao.selectEvaluationListByPage(pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(evaluationDao.selectTotal());
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

}
