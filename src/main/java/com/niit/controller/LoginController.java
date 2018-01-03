package com.niit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niit.service.LogService;
import com.niit.util.IPUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.log.Log;

import java.util.Date;

@Controller
@RequestMapping()
public class LoginController {

    @Resource
    private LogService logService;
    
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
    public String index(ModelMap resultMap, String id, String password, HttpServletRequest request, HttpServletResponse response)  throws Throwable {
        Subject subject = SecurityUtils.getSubject();
        String no = (String) subject.getSession().getAttribute("no");
        if(no != null && (id == null || "".equals(id))) {
            switch (no) {
            case "0":
                response.sendRedirect("admin/admin_index.do");
                break;
            case "1":
                response.sendRedirect("teacher/teacher_index.do");
                break;
            case "2":
                response.sendRedirect("student/student_index.do");
                break;
            default:
            }
        }
        UsernamePasswordToken token = new UsernamePasswordToken(id, password);
        try {
            subject.login(token);
            no = (String) subject.getSession().getAttribute("no");
            switch (no) {
            case "0":
                response.sendRedirect("admin/admin_index.do");
                break;
            case "1":
                response.sendRedirect("teacher/teacher_index.do");
                break;
            case "2":
                response.sendRedirect("student/student_index.do");
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.niit.bean.Log log = new com.niit.bean.Log(
                    id,
                    "0:0:0:0:0:0:0:1".equals(IPUtil.getIpAddr(request)) ? "127.0.0.1" : IPUtil.getIpAddr(request),
                    request.getServletPath(),
                    "",
                    new Date(),
                    0L,
                    "��̨",
                    "��½�ɹ�",
                    "success",
                    "");
            Integer i = logService.insertLog(log);
        }

        com.niit.bean.Log log = new com.niit.bean.Log(
                id,
                "0:0:0:0:0:0:0:1".equals(IPUtil.getIpAddr(request)) ? "127.0.0.1" : IPUtil.getIpAddr(request),
                request.getServletPath(),
                "",
                new Date(),
                0L,
                "��̨",
                "��½ʧ��",
                "success",
                "");
        Integer i = logService.insertLog(log);

        request.getSession().setAttribute("msg", "�˺Ż��������");
        return "login";
    }
    
}