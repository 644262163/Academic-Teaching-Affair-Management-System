package com.niit.controller.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.PageBean;
import com.niit.bean.StudentCourse;
import com.niit.log.Log;
import com.niit.service.CourseService;
import com.niit.service.StudentCourseService;
import com.niit.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentCourseController {

    @Resource
    private StudentCourseService studentCourseService;
    
    @Log(module = "ѧ����̨", method = "�γ��б�ҳ��")
    @RequestMapping("/course_list")
    public String courseList() {

        return "student/course_list";
    }
    
    @Log(module = "ѧ����̨", method = "��ȡ�γ��б�")
    @RequestMapping("/select_course_list")
    public String selectCourseList(
            StudentCourse studentCourse,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = studentCourseService.selectStudentCourseList(studentCourse, pageBean);

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
