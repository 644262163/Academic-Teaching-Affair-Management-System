package com.niit.controller.teacher;

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
import com.niit.bean.Teacher;
import com.niit.service.StudentService;
import com.niit.service.TeacherService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Resource
	private TeacherService teacherService;
	
	@RequestMapping("/teacher_list")
	public String teacherList(ModelMap resultMap) {
		List<Teacher> list = teacherService.selectTeacherList();
		resultMap.addAttribute("list", list);
		return "teacher/teacher_list";
	}
	
	@RequestMapping("/select_teacher_list")
	public String selectTeacherList(
			@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
			HttpServletResponse response) throws Exception {
		
		//�����ҳ
		PageBean<Teacher> pageBean = new PageBean<Teacher>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = teacherService.selectTeacherListByPage(pageBean);

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
