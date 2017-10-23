package com.niit.controller.teacher;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    
    @Resource
    private TeacherService teacherService;
    
    
}
