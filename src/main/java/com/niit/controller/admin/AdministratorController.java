package com.niit.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.niit.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Administrator;
import com.niit.bean.PageBean;
import com.niit.log.Log;
import com.niit.service.AdministratorService;
import com.niit.util.ResponseUtil;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

    @Resource
    private LogService logService;
    @Resource
    private AdministratorService administratorService;

    @Log(module = "����Ա��̨", method = "����Ա��̨")
    @RequestMapping("/admin_index")
    public String adminIndex() {

        return "admin/admin_index";
    }

    @Log(module = "����Ա��̨", method = "����Ա�б�ҳ��")
    @RequestMapping("/admin_list")
    public String adminList() {

        return "admin/admin_list";
    }
    
    @Log(module = "����Ա��̨", method = "��ȡ����Ա�б�")
    @RequestMapping("/select_admin_list")
    public String selectAdminList(
            Administrator administrator,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Administrator> pageBean = new PageBean<Administrator>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = administratorService.selectAdministratorListByPage(administrator, pageBean);

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
    
    @Log(module = "����Ա��̨", method = "�޸Ĺ���Ա")
    @RequestMapping("/update_admin")
    public String updateAdmin(Administrator administrator, HttpServletResponse response) throws Exception {
        Integer i = administratorService.updateAdministrator(administrator);
        
        if(i > 0) {
            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);
            
            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }
        
        return null;
    }

    @Log(module = "����Ա��̨", method = "ɾ������Ա")
    @RequestMapping(value="/delete_admin")
    public String deleteAdmin(String id, HttpServletResponse response) throws Exception {
        Integer i = administratorService.deleteAdministratorById(id);
        
        if(i > 0) {
            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);
            
            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }
        
        return null;
    }
    
    @Log(module = "����Ա��̨", method = "��ӹ���Ա")
    @RequestMapping("/insert_admin")
    public String insertAdmin(Administrator administrator, HttpServletResponse response) throws Exception {
        Integer i = administratorService.insertAdministrator(administrator);
        
        if(i > 0) {
            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);
            
            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }
        
        return null;
    }

    @Log(module = "����Ա��̨", method = "�޸�����ҳ��")
    @RequestMapping("/modify_password")
    public String modifyPassword() {

        return "admin/modify_password";
    }

    @Log(module = "����Ա��̨", method = "�޸�����")
    @RequestMapping("/update_password")
    public String updatePassword(
            @RequestParam(value = "administratorId", required = false) String administratorId,
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            HttpServletResponse response) throws Exception {

        Administrator administrator = administratorService.selectAdministratorById(administratorId);
        if(administrator.getPassword().equals(oldPassword)) {
            administrator.setPassword(newPassword);
            administratorService.updateAdministrator(administrator);

            //ʹ�ð���Ͱ͵�fastJson����JSONObject
            JSONObject result = new JSONObject();
            //�����л��������json������
            result.put("success", true);

            //ʹ���Զ��幤������response��д������
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "����Ա��̨", method = "��ӭҳ��")
    @RequestMapping("/welcome")
    public String welcome() {

        return "admin/welcome";
    }

    @Log(module = "����Ա��̨", method = "��ȡ��ӭ����")
    @RequestMapping("/select_welcome")
    public String selectWelcome(
            @RequestParam(value = "administratorId", required = false) String administratorId,
            HttpServletResponse response) throws Exception {

        Administrator administrator = administratorService.selectAdministratorById(administratorId);
        com.niit.bean.Log log = new com.niit.bean.Log();
        log.setUserId(administratorId);
        log.setMethod("��½�ɹ�");
        List<com.niit.bean.Log> list = logService.selectLogListByLog(log);

        //ʹ�ð���Ͱ͵�fastJson����JSONObject
        JSONObject result = new JSONObject();
        //�����л��������json������
        result.put("name", administrator.getId());
        result.put("cnt", list.size());
        if(list.size() > 1) {
            Map<String, Object> time = new HashMap<>();
            Date date = list.get(1).getTime();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            // y, M, d, H, m, s
            time.put("y", c.get(Calendar.YEAR));
            time.put("M", c.get(Calendar.MONTH));
            time.put("d", c.get(Calendar.DAY_OF_MONTH));
            time.put("H", c.get(Calendar.HOUR));
            time.put("m", c.get(Calendar.MINUTE));
            time.put("s", c.get(Calendar.SECOND));
            result.put("time", time);
        }

        //ʹ���Զ��幤������response��д������
        ResponseUtil.write(response, result);
        return null;
    }

}
