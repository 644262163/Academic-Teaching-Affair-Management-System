package com.niit.controller.teacher;

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
@RequestMapping("/teacher")
public class TeacherEvaluationController {
    
    @Resource
    private EvaluationService evaluationService;
    
    @Log(module = "��ʦ��̨", method = "�����б�ҳ��")
    @RequestMapping("/evaluation_list")
    public String evaluationList() {

        return "teacher/evaluation_list";
    }
    
    @Log(module = "��ʦ��̨", method = "��ȡ�����б�")
    @RequestMapping("/select_evaluation_list")
    public String selectEvaluationList(
            Evaluation evaluation,
            @RequestParam(value = "teacherId", required = false) String teacherId,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = evaluationService.selectEvaluationListByTeacher(teacherId, evaluation, pageBean);

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
