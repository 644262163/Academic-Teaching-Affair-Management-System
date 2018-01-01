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
import com.niit.log.Log;
import com.niit.service.LogService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorLogController {
    
    @Resource
    private LogService logService;

    @Log(module = "����Ա��̨", method = "����̨ҳ��")
    @RequestMapping("/console")
    public String adminList() {

        return "admin/console";
    }
    
    @Log(module = "����Ա��̨", method = "��ȡ��־�б�")
    @RequestMapping("/select_log_list")
    public String selectLogList(
            com.niit.bean.Log log,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<com.niit.bean.Log> pageBean = new PageBean<com.niit.bean.Log>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = logService.selectLogListByPage(log, pageBean);
        
        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //ͨ��fastJson���л�listΪjsonArray
        String jsonArray = JSON.toJSONStringWithDateFormat(pageBean.getResult(), "yyyy-MM-dd HH:mm:ss");
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
