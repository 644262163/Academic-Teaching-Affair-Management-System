package com.niit.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.log.Log;

@Controller
@RequestMapping()
public class LoginController {
    
    @Log(module = "ǰ̨", method = "��½ҳ��")  
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        request.getSession().removeAttribute("msg");
        return "login";
    }
    
    @Log(module = "��̨", method = "�ǳ�")
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().removeAttribute("id");
        subject.getSession().removeAttribute("no");
        try {
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/index.jsp";
    }
    
    @Log(module = "��̨", method = "��½")
    @RequestMapping("/index")
    public String index(ModelMap resultMap, String id, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        String no = (String) subject.getSession().getAttribute("no");
        if(no != null && (id == null || "".equals(id))) {
            switch (no) {
            case "0":
                return "admin/admin_index";
            case "1":
                return "teacher/teacher_index";
            case "2":
                return "student/student_index";
            default:
            }
        }
        UsernamePasswordToken token = new UsernamePasswordToken(id, password);
        try {
            subject.login(token);
            no = (String) subject.getSession().getAttribute("no");
            switch (no) {
            case "0":
                return "admin/admin_index";
            case "1":
                return "teacher/teacher_index";
            case "2":
                return "student/student_index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("msg", "�˺Ż��������");
        return "login";
    }
    
}