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
import com.niit.bean.Administrator;
import com.niit.bean.PageBean;
import com.niit.service.AdministratorService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
	
	@Resource
	private AdministratorService administratorService;
	
	@RequestMapping("/admin_list")
	public String adminList(ModelMap resultMap) {
		List<Administrator> list = administratorService.selectAdministratorList();
		resultMap.addAttribute("list", list);
		return "admin/admin_list";
	}
	
	@RequestMapping("/select_admin_list")
	public String selectAdminList(
			@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
			HttpServletResponse response) throws Exception {
		
		//�����ҳ
		PageBean<Administrator> pageBean = new PageBean<Administrator>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = administratorService.selectAdministratorListByPage(pageBean);

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
	
	@RequestMapping("/update_admin")
	public String updateAdmin(Administrator administrator) {
		Integer i = administratorService.updateAdministrator(administrator);
		
		return "admin/admin_list";
	}
	
}
