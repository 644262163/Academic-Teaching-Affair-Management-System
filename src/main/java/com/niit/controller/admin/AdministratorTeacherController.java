package com.niit.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.PageBean;
import com.niit.bean.Teacher;
import com.niit.log.Log;
import com.niit.service.TeacherService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorTeacherController {
    
    @Resource
    private TeacherService teacherService;
    
    @Log(module = "����Ա��̨", method = "��ʦ�б�ҳ��")
    @RequestMapping("/teacher_list")
    public String teacherList() {

        return "admin/teacher_list";
    }
    
    @Log(module = "����Ա��̨", method = "��ȡ��ʦ�б�")
    @RequestMapping("/select_teacher_list")
    public String selectTeacherList(
            Teacher teacher,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Teacher> pageBean = new PageBean<Teacher>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = teacherService.selectTeacherListByPage(teacher, pageBean);

        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //ͨ��fastJson���л�listΪjsonArray
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        //�����л��������json������
        result.put("data", array);
        result.put("count", pageBean.getTotal());
        result.put("code", 0);

        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
        return null;
    }
    
    @Log(module = "����Ա��̨", method = "�޸���ʦ")
    @RequestMapping("/update_teacher")
    public String updateTeacher(Teacher teacher) {
        Integer i = teacherService.updateTeacher(teacher);
        
        return "admin/teacher_list";
    }

    @Log(module = "����Ա��̨", method = "ɾ����ʦ")
    @RequestMapping(value="/delete_teacher")
    public String deleteTeacher(String id, HttpServletResponse response) throws Exception {
        Integer i = teacherService.deleteTeacherById(id);
        
        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //�����л��������json������
        result.put("success", true);
        
        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
        return null;
    }
    
    @Log(module = "����Ա��̨", method = "�����ʦ")
    @RequestMapping("/insert_teacher")
    public String insertTeacher(Teacher teacher, HttpServletResponse response) throws Exception {
        Integer i = teacherService.insertTeacher(teacher);
        
        return "admin/teacher_list";
    }
    
}
