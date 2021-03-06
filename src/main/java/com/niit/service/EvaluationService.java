package com.niit.service;

import java.util.List;
import java.util.Map;

import com.niit.bean.Evaluation;
import com.niit.bean.PageBean;

public interface EvaluationService {
    public Evaluation selectEvaluationByStudentId(String studentId);
    public Evaluation selectEvaluationByCourseId(String courseId);
    public List<Evaluation> selectEvaluationList();
    public PageBean<Evaluation> selectEvaluationListByPage(Evaluation evaluation, PageBean<Evaluation> pageBean);
    
    public Integer updateEvaluation(Evaluation evaluation);
    
    public Integer deleteEvaluationById(String studentId, String courseId);
    
    public Integer insertEvaluation(Evaluation evaluation);

    public PageBean<Map<String, Object>> selectEvaluationListByStudent(Evaluation evaluation, PageBean<Map<String, Object>> pageBean);
    public PageBean<Map<String, Object>> selectEvaluationListByTeacher(String teacherId, Evaluation evaluation, PageBean<Map<String, Object>> pageBean);
}
