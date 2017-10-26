package com.niit.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Course;
import com.niit.bean.PageBean;
import com.niit.log.Log;
import com.niit.service.CourseService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorCourseController {
    
    @Resource
    private CourseService courseService;
    
    @Log(module = "����Ա��̨", method = "�γ��б�ҳ��")
    @RequestMapping("/course_list")
    public String courseList(ModelMap resultMap) {
        List<Course> list = courseService.selectCourseList();
        resultMap.addAttribute("list", list);
        return "admin/course_list";
    }
    
    @Log(module = "����Ա��̨", method = "��ȡ�γ��б�")
    @RequestMapping("/select_course_list")
    public String selectCourseList(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Course> pageBean = new PageBean<Course>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = courseService.selectCourseListByPage(pageBean);

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
    
    @Log(module = "����Ա��̨", method = "�޸Ŀγ�")
    @RequestMapping("/update_course")
    public String updateCourse(Course course) {
        Integer i = courseService.updateCourse(course);
        
        return "admin/course_list";
    }

    @Log(module = "����Ա��̨", method = "ɾ���γ�")
    @RequestMapping(value="/delete_course")
    public String deleteCourse(String id, HttpServletResponse response) throws Exception {
        Integer i = courseService.deleteCourseById(id);
        
        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //�����л��������json������
        result.put("success", true);
        
        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
        return null;
    }
    
    @Log(module = "����Ա��̨", method = "��ӿγ�")
    @RequestMapping("/insert_course")
    public String insertCourse(Course course, HttpServletResponse response) throws Exception {
        Integer i = courseService.insertCourse(course);
        
        return "admin/course_list";
    }

}
