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
  <span>学生用户：</span>
</div>
 
<table class="layui-table" lay-data="{height:471, limit: 10, url:'<%=request.getContextPath() %>/teacher/select_student_list.do', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'id', width:250, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'id', width:250, sort: true}">用户名</th>
      <th lay-data="{field:'password', width:250, sort: true}">密码</th>
      <th lay-data="{field:'name', width:100, sort: true}">姓名</th>
      <th lay-data="{field:'clazz', width:100, sort: true}">班级</th>
      <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
  </thead>
</table>

 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
</script>
               
          
<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>

<script>
layui.use('table', function(){
  var table = layui.table;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      layer.msg('ID：'+ data.id + ' 的查看操作');
    } else if(obj.event === 'edit'){
      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['840px', '420px'], //宽高
        content: '\
          <form class="layui-form layui-form-pane" action="<%=request.getContextPath() %>/admin/update_student.do">\
            <div class="layui-form-item">\
              <label class="layui-form-label">用户名</label>\
              <div class="layui-input-block">\
                <input type="text" id="id" name="id" autocomplete="off" placeholder="请输入用户名" class="layui-input" readonly unselectable="on">\
              </div>\
            </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">密码</label>\
                <div class="layui-input-block">\
                  <input type="text" id="password" name="password" autocomplete="off" placeholder="请输入密码" class="layui-input">\
                </div>\
              </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">姓名</label>\
                <div class="layui-input-block">\
                  <input type="text" id="name" name="name" autocomplete="off" placeholder="请输入姓名" class="layui-input">\
                </div>\
              </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">班级</label>\
                <div class="layui-input-block">\
                  <input type="text" id="clazz" name="clazz" autocomplete="off" placeholder="请输入班级" class="layui-input">\
                </div>\
              </div>\
            <input type="submit" class="layui-btn" value="修改信息" />\
          </form>\
        '
      });
      $("#id").val(data.id);
      $("#password").val(data.password);
      $("#name").val(data.name);
      $("#clazz").val(data.clazz);
    }
  });
  
  $('.demoTable .layui-btn').on('click', function(){
    var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
});

</script>

</body>
</html>