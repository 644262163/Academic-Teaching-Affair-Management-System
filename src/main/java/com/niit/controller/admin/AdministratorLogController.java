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

    @Log(module = "管理员后台", method = "控制台页面")
    @RequestMapping("/console")
    public String adminList() {

        return "admin/console";
    }
    
    @Log(module = "管理员后台", method = "获取日志列表")
    @RequestMapping("/select_log_list")
    public String selectLogList(
            com.niit.bean.Log log,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<com.niit.bean.Log> pageBean = new PageBean<com.niit.bean.Log>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = logService.selectLogListByPage(log, pageBean);
        
        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //通过fastJson序列化list为jsonArray
        String jsonArray = JSON.toJSONStringWithDateFormat(pageBean.getResult(), "yyyy-MM-dd HH:mm:ss");
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
