package com.niit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niit.bean.Score;

public interface ScoreDao {
    public Score selectScoreByStudentId(String studentId);
    public Score selectScoreByCourseId(String courseId);
    public List<Score> selectScoreList();
    public List<Score> selectScoreListByPage(@Param("score") Score score, @Param("start") Integer start, @Param("end") Integer end);
    public Long selectTotal(Score score);
    
    public Integer updateScore(Score score);
    
    public Integer deleteScoreById(@Param("studentId") String studentId, @Param("courseId") String courseId);
    
    public Integer insertScore(Score score);
}
