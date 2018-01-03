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

    @Log(module = "管理员后台", method = "管理员后台")
    @RequestMapping("/admin_index")
    public String adminIndex() {

        return "admin/admin_index";
    }

    @Log(module = "管理员后台", method = "管理员列表页面")
    @RequestMapping("/admin_list")
    public String adminList() {

        return "admin/admin_list";
    }
    
    @Log(module = "管理员后台", method = "获取管理员列表")
    @RequestMapping("/select_admin_list")
    public String selectAdminList(
            Administrator administrator,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<Administrator> pageBean = new PageBean<Administrator>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = administratorService.selectAdministratorListByPage(administrator, pageBean);

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
    
    @Log(module = "管理员后台", method = "修改管理员")
    @RequestMapping("/update_admin")
    public String updateAdmin(Administrator administrator, HttpServletResponse response) throws Exception {
        Integer i = administratorService.updateAdministrator(administrator);
        
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

    @Log(module = "管理员后台", method = "删除管理员")
    @RequestMapping(value="/delete_admin")
    public String deleteAdmin(String id, HttpServletResponse response) throws Exception {
        Integer i = administratorService.deleteAdministratorById(id);
        
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
    
    @Log(module = "管理员后台", method = "添加管理员")
    @RequestMapping("/insert_admin")
    public String insertAdmin(Administrator administrator, HttpServletResponse response) throws Exception {
        Integer i = administratorService.insertAdministrator(administrator);
        
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

    @Log(module = "管理员后台", method = "修改密码页面")
    @RequestMapping("/modify_password")
    public String modifyPassword() {

        return "admin/modify_password";
    }

    @Log(module = "管理员后台", method = "修改密码")
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

            //使用阿里巴巴的fastJson创建JSONObject
            JSONObject result = new JSONObject();
            //将序列化结果放入json对象中
            result.put("success", true);

            //使用自定义工具类向response中写入数据
            ResponseUtil.write(response, result);
        }

        return null;
    }

    @Log(module = "管理员后台", method = "欢迎页面")
    @RequestMapping("/welcome")
    public String welcome() {

        return "admin/welcome";
    }

    @Log(module = "管理员后台", method = "获取欢迎数据")
    @RequestMapping("/select_welcome")
    public String selectWelcome(
            @RequestParam(value = "administratorId", required = false) String administratorId,
            HttpServletResponse response) throws Exception {

        Administrator administrator = administratorService.selectAdministratorById(administratorId);
        com.niit.bean.Log log = new com.niit.bean.Log();
        log.setUserId(administratorId);
        log.setMethod("登陆成功");
        List<com.niit.bean.Log> list = logService.selectLogListByLog(log);

        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //将序列化结果放入json对象中
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

        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }

}
