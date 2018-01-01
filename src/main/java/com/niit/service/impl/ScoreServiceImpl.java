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
import com.niit.bean.Score;
import com.niit.dao.ScoreDao;
import com.niit.service.ScoreService;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{

    @Resource
    private ScoreDao scoreDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private CourseDao courseDao;
    
    @Override
    public Score selectScoreByStudentId(String studentId) {
        // TODO �Զ����ɵķ������
        Score score = scoreDao.selectScoreByStudentId(studentId);
        return score;
    }

    @Override
    public Score selectScoreByCourseId(String courseId) {
        // TODO �Զ����ɵķ������
        Score score = scoreDao.selectScoreByCourseId(courseId);
        return score;
    }

    @Override
    public List<Score> selectScoreList() {
        // TODO �Զ����ɵķ������
        List<Score> list = scoreDao.selectScoreList();
        return list;
    }

    @Override
    public PageBean<Score> selectScoreListByPage(Score score, PageBean<Score> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(scoreDao.selectScoreListByPage(score, pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(scoreDao.selectTotal(score));
        return pageBean;
    }

    @Override
    public Integer updateScore(Score score) {
        // TODO �Զ����ɵķ������
        Integer i = scoreDao.updateScore(score);
        return i;
    }

    @Override
    public Integer deleteScoreById(String studentId, String courseId) {
        // TODO �Զ����ɵķ������
        Integer i = scoreDao.deleteScoreById(studentId, courseId);
        return i;
    }

    @Override
    public Integer insertScore(Score score) {
        // TODO �Զ����ɵķ������
        Integer i = scoreDao.insertScore(score);
        return i;
    }

    @Override
    public PageBean<Map<String, Object>> selectStudentScoreList(Score score, PageBean<Map<String, Object>> pageBean) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<Score> list = scoreDao.selectScoreListByPage(score, pageBean.getStart(), pageBean.getEnd());
        for(Score o: list) {
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
        //��ѯ��¼����
        pageBean.setTotal(scoreDao.selectTotal(score));
        return pageBean;
    }

}
