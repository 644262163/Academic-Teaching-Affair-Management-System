package com.niit.controller.student;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Evaluation;
import com.niit.bean.PageBean;
import com.niit.log.Log;
import com.niit.service.EvaluationService;
import com.niit.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentEvaluationController {
    
    @Resource
    private EvaluationService evaluationService;
    
    @Log(module = "ѧ����̨", method = "�����б�ҳ��")
    @RequestMapping("/evaluation_list")
    public String evaluationList() {

        return "student/evaluation_list";
    }
    
    @Log(module = "ѧ����̨", method = "��ȡ�����б�")
    @RequestMapping("/select_evaluation_list")
    public String selectEvaluationList(
            Evaluation evaluation,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = evaluationService.selectEvaluationList(evaluation, pageBean);

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
    
    @Log(module = "ѧ����̨", method = "�޸�����")
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

}
