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
		
		//�����ҳ
		PageBean<Student> pageBean = new PageBean<Student>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = studentService.getStudentListByPage(pageBean);

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
