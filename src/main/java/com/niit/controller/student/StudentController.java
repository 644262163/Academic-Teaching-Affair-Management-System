package com.niit.controller.student;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Student;
import com.niit.log.Log;
import com.niit.service.LogService;
import com.niit.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private LogService logService;
    @Resource
    private StudentService studentService;

    @Log(module = "ѧ����̨", method = "ѧ����̨")
    @RequestMapping("/student_index")
    public String studentIndex() {

        return "student/student_index";
    }

    @Log(module = "ѧ����̨", method = "�޸�����ҳ��")
    @RequestMapping("/modify_password")
    public String modifyPassword() {

        return "student/modify_password";
    }

    @Log(module = "ѧ����̨", method = "�޸�����")
    @RequestMapping("/update_password")
    public String updatePassword(
            @RequestParam(value = "studentId", required = false) String studentId,
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            HttpServletResponse response) throws Exception {

        Student student = studentService.selectStudentById(studentId);
        if(student.getPassword().equals(oldPassword)) {
            student.setPassword(newPassword);
            studentService.updateStudent(student);

            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);

            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }

        return null;
    }


    @Log(module = "ѧ����̨", method = "��ӭҳ��")
    @RequestMapping("/welcome")
    public String welcome() {

        return "student/welcome";
    }

    @Log(module = "ѧ����̨", method = "��ȡ��ӭ����")
    @RequestMapping("/select_welcome")
    public String selectWelcome(
            @RequestParam(value = "studentId", required = false) String studentId,
            HttpServletResponse response) throws Exception {

        Student student = studentService.selectStudentById(studentId);
        com.niit.bean.Log log = new com.niit.bean.Log();
        log.setUserId(studentId);
        log.setMethod("��½�ɹ�");
        List<com.niit.bean.Log> list = logService.selectLogListByLog(log);

        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //�����л��������json������
        result.put("name", student.getName());
        result.put("cnt", list.size());
        if(list.size() > 1) {
            Map<String, Object> time = new HashMap<>();
            Date date = list.get(1).getTime();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            // y, M, d, H, m, s
            time.put("y", c.get(Calendar.YEAR));
            time.put("M", c.get(Calendar.MONTH));
            time.put("d", c.get(Calendar.DAY_OF_MONTH));
            time.put("H", c.get(Calendar.HOUR));
            time.put("m", c.get(Calendar.MINUTE));
            time.put("s", c.get(Calendar.SECOND));
            result.put("time", time);
        }

        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
        return null;
    }


}
