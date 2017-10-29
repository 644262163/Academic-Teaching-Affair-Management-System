package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Evaluation;

public interface EvaluationDao {
    public Evaluation selectEvaluationByStudentId(String studentId);
    public Evaluation selectEvaluationByCourseId(String courseId);
    public List<Evaluation> selectEvaluationList();
    public List<Evaluation> selectEvaluationListByPage(@Param("evaluation") Evaluation evaluation, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(Evaluation evaluation);
    
    public Integer updateEvaluation(Evaluation evaluation);
    
    public Integer deleteEvaluationById(@Param("studentId") String studentId, @Param("courseId") String courseId);
    
    public Integer insertEvaluation(Evaluation evaluation);
}
