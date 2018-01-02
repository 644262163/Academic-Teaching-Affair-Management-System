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
  <span>老师课程列表：</span>
  <div class="layui-inline">
    <label>学期</label>
    <div class="layui-input-inline">
      <input type="text" id="s_term" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label>信息</label>
    <div class="layui-input-inline">
      <input type="text" id="s_information" class="layui-input">
    </div>
  </div>
  <button class="layui-btn" data-type="searchTeacherCourse">搜索列表</button>
  <button class="layui-btn" data-type="refreshTeacherCourse">还原默认</button>
</div>

<table class="layui-table" lay-data="{height:471, limit: 10, url:'<%=request.getContextPath() %>/teacher/select_course_list.do', where: {teacherId: '${id }'}, page:true, id:'idTest'}" lay-filter="demo">
  <thead>
  <tr>
    <th lay-data="{field:'id', width:50, sort: true, fixed: true}">ID</th>
    <th lay-data="{field:'teacherId', width:100, sort: true}">老师id</th>
    <th lay-data="{field:'teacherName', width:100, sort: true}">老师姓名</th>
    <th lay-data="{field:'courseId', width:100, sort: true}">课程id</th>
    <th lay-data="{field:'courseName', width:100, sort: true}">课程名字</th>
    <th lay-data="{field:'term', width:100, sort: true}">学期</th>
    <th lay-data="{field:'information', width:300, sort: true}">信息</th>
  </tr>
  </thead>
</table>

<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;
        var $ = layui.$, active = {
            refreshTeacherCourse: function(){
                table.reload('idTest', {
                    url: '<%=request.getContextPath() %>/teacher/select_course_list.do',
                    where: {teacherId: '${id }'} //设定异步数据接口的额外参数
                });
            },
            searchTeacherCourse: function(){
                table.reload('idTest', {
                    url: '<%=request.getContextPath() %>/teacher/select_course_list.do'
                    ,where: {
                        teacherId: '${id }',
                        term: $("#s_term").val(),
                        information: $("#s_information").val()
                    } //设定异步数据接口的额外参数
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

</script>

</body>
</html>