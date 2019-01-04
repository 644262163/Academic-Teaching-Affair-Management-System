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
import com.niit.bean.TeacherCourse;
import com.niit.log.Log;
import com.niit.service.TeacherCourseService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorTeacherCourseController {

    @Resource
    private TeacherCourseService teacherCourseService;

    @Log(module = "管理员后台", method = "老师课程列表页面")
    @RequestMapping("/teacher_course_list")
    public String teacherCourseList() {

        return "admin/teacher_course_list";
    }

    @Log(module = "管理员后台", method = "获取老师课程列表")
    @RequestMapping("/select_teacher_course_list")
    public String selectTeacherCourseList(
            TeacherCourse teacherCourse,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {

        //定义分页
        PageBean<TeacherCourse> pageBean = new PageBean<TeacherCourse>(
                Integer.parseInt(page),
                Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = teacherCourseService.selectTeacherCourseListByPage(teacherCourse, pageBean);

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

    @Log(module = "管理员后台", method = "修改老师课程")
    @RequestMapping("/update_teacher_course")
    public String updateTeacherCourse(TeacherCourse teacherCourse, HttpServletResponse response) throws Exception {
        Integer i = teacherCourseService.updateTeacherCourse(teacherCourse);

        if(i > 0) {
            //使用阿里巴巴的fastJson创建JSONObject
            JSONObject result = new JSONObject();
            //将序列化结果放入json对象中
            result.put("success", true);

            //使用自定义工具类向response中写入数据
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "管理员后台", method = "删除老师课程")
    @RequestMapping(value="/delete_teacher_course")
    public String deleteTeacherCourse(String id, HttpServletResponse response) throws Exception {
        Integer i = teacherCourseService.deleteTeacherCourseById(id);

        if(i > 0) {
            //使用阿里巴巴的fastJson创建JSONObject
            JSONObject result = new JSONObject();
            //将序列化结果放入json对象中
            result.put("success", true);

            //使用自定义工具类向response中写入数据
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "管理员后台", method = "添加老师课程")
    @RequestMapping("/insert_teacher_course")
    public String insertTeacherCourse(TeacherCourse teacherCourse, HttpServletResponse response) throws Exception {
        Integer i = teacherCourseService.insertTeacherCourse(teacherCourse);

        if(i > 0) {
            //使用阿里巴巴的fastJson创建JSONObject
            JSONObject result = new JSONObject();
            //将序列化结果放入json对象中
            result.put("success", true);

            //使用自定义工具类向response中写入数据
            ResponseUtil.write(response, result);
        }

        return null;
    }

}
