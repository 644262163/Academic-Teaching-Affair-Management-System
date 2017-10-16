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
import com.niit.bean.PageBean;
import com.niit.bean.Student;
import com.niit.service.StudentService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorStudentController {
	
	@Resource
	private StudentService studentService;
	
	@RequestMapping("/student_list")
	public String studentList(ModelMap resultMap) {
		List<Student> list = studentService.getStudentList();
		resultMap.addAttribute("list", list);
		return "admin/student_list";
	}
	
	@RequestMapping("/get_student_list")
	public String getStudentList(
			@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
			HttpServletResponse response) throws Exception {
		
		//定义分页
		PageBean<Student> pageBean = new PageBean<Student>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = studentService.getStudentListByPage(pageBean);

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
