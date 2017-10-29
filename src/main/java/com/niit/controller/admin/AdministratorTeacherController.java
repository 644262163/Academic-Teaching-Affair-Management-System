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
    
    @Log(module = "管理员后台", method = "老师列表页面")
    @RequestMapping("/teacher_list")
    public String teacherList() {

        return "admin/teacher_list";
    }
    
    @Log(module = "管理员后台", method = "获取老师列表")
    @RequestMapping("/select_teacher_list")
    public String selectTeacherList(
            Teacher teacher,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<Teacher> pageBean = new PageBean<Teacher>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = teacherService.selectTeacherListByPage(teacher, pageBean);

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
    
    @Log(module = "管理员后台", method = "修改老师")
    @RequestMapping("/update_teacher")
    public String updateTeacher(Teacher teacher) {
        Integer i = teacherService.updateTeacher(teacher);
        
        return "admin/teacher_list";
    }

    @Log(module = "管理员后台", method = "删除老师")
    @RequestMapping(value="/delete_teacher")
    public String deleteTeacher(String id, HttpServletResponse response) throws Exception {
        Integer i = teacherService.deleteTeacherById(id);
        
        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //将序列化结果放入json对象中
        result.put("success", true);
        
        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }
    
    @Log(module = "管理员后台", method = "添加老师")
    @RequestMapping("/insert_teacher")
    public String insertTeacher(Teacher teacher, HttpServletResponse response) throws Exception {
        Integer i = teacherService.insertTeacher(teacher);
        
        return "admin/teacher_list";
    }
    
}
