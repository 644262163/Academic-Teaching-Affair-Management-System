package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Score;
import com.niit.dao.ScoreDao;
import com.niit.service.ScoreService;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{

    @Resource
    private ScoreDao scoreDao;
    
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
    public PageBean<Score> selectScoreListByPage(PageBean<Score> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(scoreDao.selectScoreListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(scoreDao.selectTotal());
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

}
