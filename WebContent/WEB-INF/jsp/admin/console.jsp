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
 
<div class="demoTable">
  <span>控制台：</span>
</div>
 
<table class="layui-table" lay-data="{height:471, limit: 10, url:'<%=request.getContextPath() %>/admin/select_log_list.do', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'id', width:50, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'userId', width:100, sort: true}">用户ID</th>
      <th lay-data="{field:'ip', width:100, sort: true}">IP</th>
      <th lay-data="{field:'url', width:150, sort: true}">URL</th>
      <th lay-data="{field:'parameter', width:100, sort: true}">参数</th>
      <th lay-data="{field:'time', width:100, sort: true}">时间</th>
      <th lay-data="{field:'length', width:100, sort: true}">运行时间（ms）</th>
      <th lay-data="{field:'module', width:100, sort: true}">模块</th>
      <th lay-data="{field:'method', width:100, sort: true}">方法</th>
      <th lay-data="{field:'result', width:100, sort: true}">结果</th>
      <th lay-data="{field:'message', width:100, sort: true}">信息</th>
    </tr>
  </thead>
</table>
                         
<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>

<script>
layui.use('table', function(){
    $('.demoTable .layui-btn').on('click', function(){
      var type = $(this).data('type');
      active[type] ? active[type].call(this) : '';
    });
  });
</script>

</body>
</html>