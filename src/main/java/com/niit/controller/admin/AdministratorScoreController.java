package com.niit.controller.admin;

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

@Controller
@RequestMapping("/admin")
public class AdministratorScoreController {
    
    @Resource
    private ScoreService scoreService;
    
    @Log(module = "管理员后台", method = "成绩列表页面")
    @RequestMapping("/score_list")
    public String scoreList() {

        return "admin/score_list";
    }
    
    @Log(module = "管理员后台", method = "获取成绩列表")
    @RequestMapping("/select_score_list")
    public String selectScoreList(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "limit", required = false) String limit,
            HttpServletResponse response) throws Exception {
        
        //定义分页
        PageBean<Score> pageBean = new PageBean<Score>(
            Integer.parseInt(page),
            Integer.parseInt(limit));
        //拿到分页结果已经记录总数的page
        pageBean = scoreService.selectScoreListByPage(pageBean);

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
    
    @Log(module = "管理员后台", method = "修改成绩")
    @RequestMapping("/update_score")
    public String updateScore(Score score) {
        Integer i = scoreService.updateScore(score);
        
        return "admin/score_list";
    }

    @Log(module = "管理员后台", method = "删除成绩")
    @RequestMapping(value="/delete_score")
    public String deleteScore(String studentId, String courseId, HttpServletResponse response) throws Exception {
        System.out.println(studentId + courseId);
        Integer i = scoreService.deleteScoreById(studentId, courseId);
        
        //使用阿里巴巴的fastJson创建JSONObject
        JSONObject result = new JSONObject();
        //将序列化结果放入json对象中
        result.put("success", true);
        
        //使用自定义工具类向response中写入数据
        ResponseUtil.write(response, result);
        return null;
    }
    
    @Log(module = "管理员后台", method = "添加成绩")
    @RequestMapping("/insert_score")
    public String insertScore(Score score, HttpServletResponse response) throws Exception {
        Integer i = scoreService.insertScore(score);
        
        return "admin/score_list";
    }

}
