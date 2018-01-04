package com.niit.controller.teacher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Teacher;
import com.niit.log.Log;
import com.niit.service.LogService;
import com.niit.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.service.TeacherService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private LogService logService;
    @Resource
    private TeacherService teacherService;

    @Log(module = "��ʦ��̨", method = "��ʦ��̨")
    @RequestMapping("/teacher_index")
    public String teacherIndex() {

        return "teacher/teacher_index";
    }

    @Log(module = "��ʦ��̨", method = "�޸�����ҳ��")
    @RequestMapping("/modify_password")
    public String modifyPassword() {

        return "teacher/modify_password";
    }

    @Log(module = "��ʦ��̨", method = "�޸�����")
    @RequestMapping("/update_password")
    public String updatePassword(
            @RequestParam(value = "teacherId", required = false) String teacherId,
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            HttpServletResponse response) throws Exception {

        Teacher teacher = teacherService.selectTeacherById(teacherId);
        if(teacher.getPassword().equals(oldPassword)) {
            teacher.setPassword(newPassword);
            teacherService.updateTeacher(teacher);

            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);

            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "��ʦ��̨", method = "��ӭҳ��")
    @RequestMapping("/welcome")
    public String welcome() {

        return "teacher/welcome";
    }

    @Log(module = "��ʦ��̨", method = "��ȡ��ӭ����")
    @RequestMapping("/select_welcome")
    public String selectWelcome(
            @RequestParam(value = "teacherId", required = false) String teacherId,
            HttpServletResponse response) throws Exception {

        Teacher teacher = teacherService.selectTeacherById(teacherId);
        com.niit.bean.Log log = new com.niit.bean.Log();
        log.setUserId(teacherId);
        log.setMethod("��½�ɹ�");
        List<com.niit.bean.Log> list = logService.selectLogListByLog(log);

        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //�����л��������json������
        result.put("name", teacher.getName());
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
            time.put("H", c.get(Calendar.HOUR_OF_DAY));
            time.put("m", c.get(Calendar.MINUTE));
            time.put("s", c.get(Calendar.SECOND));
            result.put("time", time);
        }

        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
        return null;
    }

}
