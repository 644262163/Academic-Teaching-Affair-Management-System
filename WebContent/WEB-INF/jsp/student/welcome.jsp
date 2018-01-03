<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css"  media="all">
  <script src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
</head>
<body style="margin: 15px;">  
 
<div class="layui-table"  lay-size="lg">
  <tr><th><p id="p1">您好！</p></th></tr>
  <tr><th><p id="p2"></p></th></tr>
  <tr><th><p id="p3"></p></th></tr>
  <tr><th><p id="p4"></p></th></tr>
</div>

<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>

<script>
    layui.use(['util', 'laydate', 'layer'], function(){
        var util = layui.util
            ,laydate = layui.laydate
            ,layer = layui.layer;


    //某个时间在当前时间的多久前
    function setTimeAgo(y, M, d, H, m, s){
        var str = util.timeAgo(new Date(y, M||0, d||1, H||0, m||0, s||0));
        lay('#p4').html("距离现在：" + str + "！");
    };

    $.ajax({
        url:'<%=request.getContextPath() %>/student/select_welcome.do',
        type:'GET',
        async:true,    //是否异步
        data:{
            studentId:'${id }'
        },
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(result){
            if(result.name){
                $("#p1").html("您好！ " + result.name + " 同学！");
            }
            if(result.cnt){
                $("#p2").html("这是您第 " + result.cnt + " 次登陆账号！");
            }
            if(result.time){
                $("#p3").html("您上次登录时间为： " + result.time.y + "年" + (result.time.M + 1) + "月" + result.time.d + "日" +
                    result.time.H + "时" + result.time.m + "分" + result.time.s + "秒" + "！");
                setTimeAgo(result.time.y, result.time.M, result.time.d, result.time.H, result.time.m, result.time.s);
            }
        },
        error:function(xhr,textStatus){}
    });

    });


</script>

</body>
</html>