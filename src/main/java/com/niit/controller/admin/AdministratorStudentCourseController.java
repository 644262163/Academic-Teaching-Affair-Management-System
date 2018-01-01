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
import com.niit.bean.StudentCourse;
import com.niit.log.Log;
import com.niit.service.StudentCourseService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorStudentCourseController {

    @Resource
    private StudentCourseService studentCourseService;

    @Log(module = "����Ա��̨", method = "ѧ���γ��б�ҳ��")
    @RequestMapping("/student_course_list")
    public String studentCourseList() {

        return "admin/student_course_list";
    }

    @Log(module = "����Ա��̨", method = "��ȡѧ���γ��б�")
    @RequestMapping("/select_student_course_list")
    public String selectStudentCourseList(
            StudentCourse studentCourse,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {

        //�����ҳ
        PageBean<StudentCourse> pageBean = new PageBean<StudentCourse>(
                Integer.parseInt(page),
                Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = studentCourseService.selectStudentCourseListByPage(studentCourse, pageBean);

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

    @Log(module = "����Ա��̨", method = "�޸�ѧ���γ�")
    @RequestMapping("/update_student_course")
    public String updateStudentCourse(StudentCourse studentCourse, HttpServletResponse response) throws Exception {
        Integer i = studentCourseService.updateStudentCourse(studentCourse);

        if(i > 0) {
            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);

            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "����Ա��̨", method = "ɾ��ѧ���γ�")
    @RequestMapping(value="/delete_student_course")
    public String deleteStudentCourse(String id, HttpServletResponse response) throws Exception {
        Integer i = studentCourseService.deleteStudentCourseById(id);

        if(i > 0) {
            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);

            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "����Ա��̨", method = "���ѧ���γ�")
    @RequestMapping("/insert_student_course")
    public String insertStudentCourse(StudentCourse studentCourse, HttpServletResponse response) throws Exception {
        Integer i = studentCourseService.insertStudentCourse(studentCourse);

        if(i > 0) {
            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);

            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }

        return null;
    }

}
