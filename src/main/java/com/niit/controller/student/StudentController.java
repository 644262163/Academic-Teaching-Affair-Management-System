package com.niit.controller.student;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
    
    @Resource
    private StudentService studentService;
    
}
