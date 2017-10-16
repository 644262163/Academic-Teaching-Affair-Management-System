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
	public Student getStudentById(String id) {
		// TODO 自动生成的方法存根
		Student student = studentDao.getStudentById(id);
		return student;
	}

	@Override
	public List<Student> getStudentList() {
		// TODO 自动生成的方法存根
		List<Student> list = studentDao.getStudentList();
		return list;
	}

	@Override
	public PageBean<Student> getStudentListByPage(PageBean<Student> pageBean) {
		// TODO 自动生成的方法存根
		//查询分页结果
        pageBean.setResult(studentDao.getStudentListByPage(pageBean.getStart(), pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(studentDao.getTotal());
		return pageBean;
	}

}
