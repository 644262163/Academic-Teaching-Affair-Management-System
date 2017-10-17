package com.niit.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niit.bean.PageBean;
import com.niit.bean.Student;
import com.niit.dao.StudentDao;
import com.niit.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;
    
    @Override
    public Student selectStudentById(String id) {
        // TODO �Զ����ɵķ������
        Student student = studentDao.selectStudentById(id);
        return student;
    }

    @Override
    public List<Student> selectStudentList() {
        // TODO �Զ����ɵķ������
        List<Student> list = studentDao.selectStudentList();
        return list;
    }

    @Override
    public PageBean<Student> selectStudentListByPage(PageBean<Student> pageBean) {
        // TODO �Զ����ɵķ������
        //��ѯ��ҳ���
        pageBean.setResult(studentDao.selectStudentListByPage(pageBean.getStart(), pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(studentDao.selectTotal());
        return pageBean;
    }

}
