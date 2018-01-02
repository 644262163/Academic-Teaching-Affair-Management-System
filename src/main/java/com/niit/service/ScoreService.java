package com.niit.service;

import java.util.List;
import java.util.Map;

import com.niit.bean.PageBean;
import com.niit.bean.Score;

public interface ScoreService {
    public Score selectScoreByStudentId(String studentId);
    public Score selectScoreByCourseId(String courseId);
    public List<Score> selectScoreList();
    public PageBean<Score> selectScoreListByPage(Score score, PageBean<Score> pageBean);
    
    public Integer updateScore(Score score);
    
    public Integer deleteScoreById(String studentId, String courseId);
    
    public Integer insertScore(Score score);

    public PageBean<Map<String, Object>> selectScoreListByStudent(Score score, PageBean<Map<String, Object>> pageBean);
    public PageBean<Map<String, Object>> selectScoreListByTeacher(String teacherId, Score score, PageBean<Map<String, Object>> pageBean);
}
