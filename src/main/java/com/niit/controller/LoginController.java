package com.niit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.bean.Administrator;
import com.niit.bean.Student;
import com.niit.bean.Teacher;
import com.niit.service.AdministratorService;
import com.niit.service.StudentService;
import com.niit.service.TeacherService;

@Controller
@RequestMapping()
public class LoginController {
	
	@Resource
	private AdministratorService administratorService;
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	
	@RequestMapping("/admin_login")
	public String adminLogin(ModelMap resultMap, String user, HttpServletRequest request) {
		Administrator administrator = administratorService.selectAdministratorByUser(user);
		if(administrator != null) {
			request.getSession().setAttribute("user", user);
			return "admin/admin";
		}
		return null;
	}
	
	@RequestMapping("/student_login")
	public String studentLogin(ModelMap resultMap, String id) {
		Student student = studentService.selectStudentById(id);
		resultMap.addAttribute("student", student);
		return "student/student";
	}
	
	@RequestMapping("/teacher_login")
	public String teacherLogin(ModelMap resultMap, String id) {
		Teacher teacher = teacherService.selectTeacherById(id);
		resultMap.addAttribute("teacher", teacher);
		return "teacher/teacher";
	}
}