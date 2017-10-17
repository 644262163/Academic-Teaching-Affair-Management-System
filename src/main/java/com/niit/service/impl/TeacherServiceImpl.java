package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Teacher;
import com.niit.dao.TeacherDao;
import com.niit.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

    @Resource
    private TeacherDao teacherDao;
    
    @Override
    public Teacher selectTeacherById(String id) {
        // TODO �Զ����ɵķ������
        Teacher teacher = teacherDao.selectTeacherById(id);
        return teacher;
    }

    @Override
    public List<Teacher> selectTeacherList() {
        // TODO �Զ����ɵķ������
        List<Teacher> list = teacherDao.selectTeacherList();
        return list;
    }

    @Override
    public PageBean<Teacher> selectTeacherListByPage(PageBean<Teacher> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(teacherDao.selectTeacherListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(teacherDao.selectTotal());
        return pageBean;
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        // TODO �Զ����ɵķ������
        Integer i = teacherDao.updateTeacher(teacher);
        return i;
    }

    @Override
    public Integer deleteTeacherById(String id) {
        // TODO �Զ����ɵķ������
        Integer i = teacherDao.deleteTeacherById(id);
        return i;
    }

    @Override
    public Integer insertTeacher(Teacher teacher) {
        // TODO �Զ����ɵķ������
        Integer i = teacherDao.insertTeacher(teacher);
        return i;
    }

}
