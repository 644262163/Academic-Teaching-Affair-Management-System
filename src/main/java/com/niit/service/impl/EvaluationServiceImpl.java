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
        // TODO �Զ����ɵķ������
        Evaluation evaluation = evaluationDao.selectEvaluationByStudentId(studentId);
        return evaluation;
    }

    @Override
    public Evaluation selectEvaluationByCourseId(String courseId) {
        // TODO �Զ����ɵķ������
        Evaluation evaluation = evaluationDao.selectEvaluationByCourseId(courseId);
        return evaluation;
    }

    @Override
    public List<Evaluation> selectEvaluationList() {
        // TODO �Զ����ɵķ������
        List<Evaluation> list = evaluationDao.selectEvaluationList();
        return list;
    }

    @Override
    public PageBean<Evaluation> selectEvaluationListByPage(PageBean<Evaluation> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(evaluationDao.selectEvaluationListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(evaluationDao.selectTotal());
        return pageBean;
    }

    @Override
    public Integer updateEvaluation(Evaluation evaluation) {
        // TODO �Զ����ɵķ������
        Integer i = evaluationDao.updateEvaluation(evaluation);
        return i;
    }

    @Override
    public Integer deleteEvaluationById(String studentId, String courseId) {
        // TODO �Զ����ɵķ������
        Integer i = evaluationDao.deleteEvaluationById(studentId, courseId);
        return i;
    }

    @Override
    public Integer insertEvaluation(Evaluation evaluation) {
        // TODO �Զ����ɵķ������
        Integer i = evaluationDao.insertEvaluation(evaluation);
        return i;
    }

}
