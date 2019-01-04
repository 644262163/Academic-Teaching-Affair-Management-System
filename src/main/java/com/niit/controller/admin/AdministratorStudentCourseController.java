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

    @Log(module = "管理员后台", method = "学生课程列表页面")
    @RequestMapping("/student_course_list")
    public String studentCourseList() {

        return "admin/student_course_list";
    }

    @Log(module = "管理员后台", method = "获取学生课程列表")
    @RequestMapping("/select_student_course_list")
    public String selectStudentCourseList(
            StudentCourse studentCourse,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {

        //定义分页
        PageBean<StudentCourse> pageBean = new PageBean<StudentCourse>(
                Integer.parseInt(page),
                Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = studentCourseService.selectStudentCourseListByPage(studentCourse, pageBean);

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

    @Log(module = "管理员后台", method = "修改学生课程")
    @RequestMapping("/update_student_course")
    public String updateStudentCourse(StudentCourse studentCourse, HttpServletResponse response) throws Exception {
        Integer i = studentCourseService.updateStudentCourse(studentCourse);

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

    @Log(module = "管理员后台", method = "删除学生课程")
    @RequestMapping(value="/delete_student_course")
    public String deleteStudentCourse(String id, HttpServletResponse response) throws Exception {
        Integer i = studentCourseService.deleteStudentCourseById(id);

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

    @Log(module = "管理员后台", method = "添加学生课程")
    @RequestMapping("/insert_student_course")
    public String insertStudentCourse(StudentCourse studentCourse, HttpServletResponse response) throws Exception {
        Integer i = studentCourseService.insertStudentCourse(studentCourse);

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
