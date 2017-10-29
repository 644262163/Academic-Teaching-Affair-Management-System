package com.niit.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Evaluation;
import com.niit.bean.PageBean;
import com.niit.log.Log;
import com.niit.service.EvaluationService;
import com.niit.util.ResponseUtil;

@Controller
@RequestMapping("/admin")
public class AdministratorEvaluationController {
    
    @Resource
    private EvaluationService evaluationService;
    
    @Log(module = "管理员后台", method = "评价列表页面")
    @RequestMapping("/evaluation_list")
    public String evaluationList() {

        return "admin/evaluation_list";
    }
    
    @Log(module = "管理员后台", method = "获取评价列表")
    @RequestMapping("/select_evaluation_list")
    public String selectEvaluationList(
            Evaluation evaluation,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<Evaluation> pageBean = new PageBean<Evaluation>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = evaluationService.selectEvaluationListByPage(evaluation, pageBean);

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
    
    @Log(module = "管理员后台", method = "修改评价")
    @RequestMapping("/update_evaluation")
    public String updateEvaluation(Evaluation evaluation, HttpServletResponse response) throws Exception {
        Integer i = evaluationService.updateEvaluation(evaluation);
        
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

    @Log(module = "管理员后台", method = "删除评价")
    @RequestMapping(value="/delete_evaluation")
    public String deleteEvaluation(String studentId, String courseId, HttpServletResponse response) throws Exception {
        Integer i = evaluationService.deleteEvaluationById(studentId, courseId);
        
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
    
    @Log(module = "管理员后台", method = "添加评价")
    @RequestMapping("/insert_evaluation")
    public String insertEvaluation(Evaluation evaluation, HttpServletResponse response) throws Exception {
        Integer i = evaluationService.insertEvaluation(evaluation);
        
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

}
