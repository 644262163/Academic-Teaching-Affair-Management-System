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
import com.niit.service.AdministratorService;
import com.niit.util.PageUtil;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorController {
	
	@Resource
	private AdministratorService administratorService;
	
	@RequestMapping("/admin_list")
	public String adminList(ModelMap resultMap) {
		List<Administrator> list = administratorService.getAdministratorList();
		resultMap.addAttribute("list", list);
		return "admin_list";
	}
	
	@RequestMapping("/get_admin_list")
	public String getAdminList(
			@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
			HttpServletResponse response) throws Exception {
		
		//�����ҳ
        //PageUtil<Administrator> pageUtil = new PageUtil<Administrator>(
        //		Integer.parseInt(page),
        //        Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        //pageUtil = administratorService.getAdministratorListByPage(pageUtil);
        
        List<Administrator> list = administratorService.getAdministratorList();
		//ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //ͨ��fastJson���л�listΪjsonArray
        String jsonArray = JSON.toJSONString(list);
        JSONArray array = JSONArray.parseArray(jsonArray);
        //�����л��������json������
        result.put("data", array);
        result.put("count", list.size());
        result.put("code", 0);

        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
		return null;
	}
}
