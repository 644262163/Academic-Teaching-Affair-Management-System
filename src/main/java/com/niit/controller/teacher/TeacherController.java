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

    @Log(module = "老师后台", method = "老师后台")
    @RequestMapping("/teacher_index")
    public String teacherIndex() {

        return "teacher/teacher_index";
    }

    @Log(module = "老师后台", method = "修改密码页面")
    @RequestMapping("/modify_password")
    public String modifyPassword() {

        return "teacher/modify_password";
    }

    @Log(module = "老师后台", method = "修改密码")
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

            //使用阿里巴巴的fastJson创建JSONObject
            JSONObject result = new JSONObject();
            //将序列化结果放入json对象中
            result.put("success", true);

            //使用自定义工具类向response中写入数据
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "老师后台", method = "欢迎页面")
    @RequestMapping("/welcome")
    public String welcome() {

        return "teacher/welcome";
    }

    @Log(module = "老师后台", method = "获取欢迎数据")
    @RequestMapping("/select_welcome")
    public String selectWelcome(
            @RequestParam(value = "teacherId", required = false) String teacherId,
            HttpServletResponse response) throws Exception {

        Teacher teacher = teacherService.selectTeacherById(teacherId);
        com.niit.bean.Log log = new com.niit.bean.Log();
        log.setUserId(teacherId);
        log.setMethod("登陆成功");
        List<com.niit.bean.Log> list = logService.selectLogListByLog(log);

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //将序列化结果放入json对象中
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

        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }

}
