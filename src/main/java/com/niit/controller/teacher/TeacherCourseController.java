package com.niit.controller.teacher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.niit.bean.TeacherCourse;
import com.niit.service.TeacherCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.PageBean;
import com.niit.log.Log;
import com.niit.util.ResponseUtil;

import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherCourseController {
    
    @Resource
    private TeacherCourseService teacherCourseService;
    
    @Log(module = "��ʦ��̨", method = "�γ��б�ҳ��")
    @RequestMapping("/course_list")
    public String courseList() {

        return "teacher/course_list";
    }
    
    @Log(module = "��ʦ��̨", method = "��ȡ�γ��б�")
    @RequestMapping("/select_course_list")
    public String selectCourseList(
            TeacherCourse teacherCourse,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = teacherCourseService.selectTeacherCourseList(teacherCourse, pageBean);

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

}
