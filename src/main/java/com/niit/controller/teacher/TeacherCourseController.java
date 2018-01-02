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
    
    @Log(module = "老师后台", method = "课程列表页面")
    @RequestMapping("/course_list")
    public String courseList() {

        return "teacher/course_list";
    }
    
    @Log(module = "老师后台", method = "获取课程列表")
    @RequestMapping("/select_course_list")
    public String selectCourseList(
            TeacherCourse teacherCourse,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = teacherCourseService.selectTeacherCourseList(teacherCourse, pageBean);

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONString(pageBean.getResult());
        JSONArray array = JSONArray.parseArray(jsonArray);
        //将序列化结果放入json对象中
        result.put("data", array);
        result.put("count", pageBean.getTotal());
        result.put("code", 0);

        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }

}
