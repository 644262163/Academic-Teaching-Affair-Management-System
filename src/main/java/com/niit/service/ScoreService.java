package com.niit.service;

import java.util.List;

import com.niit.bean.PageBean;
import com.niit.bean.Score;

public interface ScoreService {
    public Score selectScoreByStudentId(String studentId);
    public Score selectScoreByCourseId(String courseId);
    public List<Score> selectScoreList();
    public PageBean<Score> selectScoreListByPage(PageBean<Score> pageBean);
    
    public Integer updateScore(Score score);
    
    public Integer deleteScoreById(String studentId, String courseId);
    
    public Integer insertScore(Score score);
}
