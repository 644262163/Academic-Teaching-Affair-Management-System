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
    
    @Log(module = "管理员后台", method = "课程列表页面")
    @RequestMapping("/course_list")
    public String courseList(ModelMap resultMap) {
        List<Course> list = courseService.selectCourseList();
        resultMap.addAttribute("list", list);
        return "admin/course_list";
    }
    
    @Log(module = "管理员后台", method = "获取课程列表")
    @RequestMapping("/select_course_list")
    public String selectCourseList(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<Course> pageBean = new PageBean<Course>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = courseService.selectCourseListByPage(pageBean);

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
    
    @Log(module = "管理员后台", method = "修改课程")
    @RequestMapping("/update_course")
    public String updateCourse(Course course) {
        Integer i = courseService.updateCourse(course);
        
        return "admin/course_list";
    }

    @Log(module = "管理员后台", method = "删除课程")
    @RequestMapping(value="/delete_course")
    public String deleteCourse(String id, HttpServletResponse response) throws Exception {
        Integer i = courseService.deleteCourseById(id);
        
        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //将序列化结果放入json对象中
        result.put("success", true);
        
        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }
    
    @Log(module = "管理员后台", method = "添加课程")
    @RequestMapping("/insert_course")
    public String insertCourse(Course course, HttpServletResponse response) throws Exception {
        Integer i = courseService.insertCourse(course);
        
        return "admin/course_list";
    }

}
