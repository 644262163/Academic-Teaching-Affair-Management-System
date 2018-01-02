package com.niit.controller.teacher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.niit.bean.Score;
import com.niit.log.Log;
import com.niit.bean.PageBean;
import com.niit.service.ScoreService;
import com.niit.util.ResponseUtil;

import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherScoreController {
    
    @Resource
    private ScoreService scoreService;
    
    @Log(module = "��ʦ��̨", method = "�ɼ��б�ҳ��")
    @RequestMapping("/score_list")
    public String scoreList() {

        return "teacher/score_list";
    }
    
    @Log(module = "��ʦ��̨", method = "��ȡ�ɼ��б�")
    @RequestMapping("/select_score_list")
    public String selectScoreList(
            Score score,
            @RequestParam(value = "teacherId", required = false) String teacherId,
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //�����ҳ
        PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //�õ���ҳ����Ѿ���¼������page
        pageBean = scoreService.selectScoreListByTeacher(teacherId, score, pageBean);

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
    
    @Log(module = "��ʦ��̨", method = "�޸ĳɼ�")
    @RequestMapping("/update_score")
    public String updateScore(Score score, HttpServletResponse response) throws Exception {
        Integer i = scoreService.updateScore(score);

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
