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
    
    @Log(module = "����Ա��̨", method = "�����б�ҳ��")
    @RequestMapping("/evaluation_list")
    public String evaluationList() {

        return "admin/evaluation_list";
    }
    
    @Log(module = "����Ա��̨", method = "��ȡ�����б�")
    @RequestMapping("/select_evaluation_list")
    public String selectEvaluationList(
            Evaluation evaluation,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Evaluation> pageBean = new PageBean<Evaluation>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = evaluationService.selectEvaluationListByPage(evaluation, pageBean);

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
    
    @Log(module = "����Ա��̨", method = "�޸�����")
    @RequestMapping("/update_evaluation")
    public String updateEvaluation(Evaluation evaluation, HttpServletResponse response) throws Exception {
        Integer i = evaluationService.updateEvaluation(evaluation);
        
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

    @Log(module = "����Ա��̨", method = "ɾ������")
    @RequestMapping(value="/delete_evaluation")
    public String deleteEvaluation(String studentId, String courseId, HttpServletResponse response) throws Exception {
        Integer i = evaluationService.deleteEvaluationById(studentId, courseId);
        
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
    
    @Log(module = "����Ա��̨", method = "�������")
    @RequestMapping("/insert_evaluation")
    public String insertEvaluation(Evaluation evaluation, HttpServletResponse response) throws Exception {
        Integer i = evaluationService.insertEvaluation(evaluation);
        
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

}
