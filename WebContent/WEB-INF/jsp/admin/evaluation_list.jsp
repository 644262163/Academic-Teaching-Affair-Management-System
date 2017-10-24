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
  <span>评价列表：</span>
  <button class="layui-btn" data-type="insertEvaluation">添加评价</button>
</div>
 
<table class="layui-table" lay-data="{height:471, limit: 10, url:'<%=request.getContextPath() %>/admin/select_evaluation_list.do', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'studentId', width:250, sort: true}">学生ID</th>
      <th lay-data="{field:'courseId', width:250, sort: true}">课程ID</th>
      <th lay-data="{field:'score', width:250, sort: true}">分数</th>
      <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
  </thead>
</table>

 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
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
    } else if(obj.event === 'del'){
      if(false){
        layer.alert('不能删除自己');
      } else{
        layer.confirm('真的删除行么', function(index){
          $.ajax({
            url:'<%=request.getContextPath() %>/admin/delete_evaluation.do',
            type:'GET', 
            async:false,    //是否异步
            data:{
                studentId:data.studentId,
                courseId:data.courseId
            },
            timeout:5000,    //超时时间
            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success:function(result){
                if(result.success){
                    layer.msg('删除成功');
                    obj.del();
                } else{
                    layer.msg('删除失败');
                }
            },
            error:function(xhr,textStatus){
                layer.msg('删除失败');
            }
          });
          layer.close(index);
        });
      }
    } else if(obj.event === 'edit'){
      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['840px', '420px'], //宽高
        content: '\
          <form class="layui-form layui-form-pane" action="<%=request.getContextPath() %>/admin/update_evaluation.do">\
            <div class="layui-form-item">\
              <label class="layui-form-label">学生id</label>\
              <div class="layui-input-block">\
                <input type="text" id="studentId" name="studentId" autocomplete="off" placeholder="请输入学生id" class="layui-input" readonly unselectable="on">\
              </div>\
            </div>\
            <div class="layui-form-item">\
              <label class="layui-form-label">课程id</label>\
              <div class="layui-input-block">\
                <input type="text" id="courseId" name="courseId" autocomplete="off" placeholder="请输入课程id" class="layui-input" readonly unselectable="on">\
              </div>\
            </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">分数</label>\
                <div class="layui-input-block">\
                  <input type="text" id="score" name="score" autocomplete="off" placeholder="请输入分数" class="layui-input">\
                </div>\
              </div>\
            <input type="submit" class="layui-btn" value="修改信息" />\
          </form>\
        '
      });
      $("#studentId").val(data.studentId);
      $("#courseId").val(data.courseId);
      $("#score").val(data.score);
    }
  });
  
  var $ = layui.$, active = {
    insertEvaluation: function(){ //添加评价
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['840px', '420px'], //宽高
        content: '\
          <form class="layui-form layui-form-pane" action="<%=request.getContextPath() %>/admin/insert_evaluation.do">\
          <div class="layui-form-item">\
            <label class="layui-form-label">学生id</label>\
              <div class="layui-input-block">\
                <input type="text" id="studentId" name="studentId" autocomplete="off" placeholder="请输入学生id" class="layui-input">\
              </div>\
            </div>\
            <div class="layui-form-item">\
              <label class="layui-form-label">课程id</label>\
              <div class="layui-input-block">\
                <input type="text" id="courseId" name="courseId" autocomplete="off" placeholder="请输入课程id" class="layui-input">\
              </div>\
            </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">分数</label>\
                <div class="layui-input-block">\
                  <input type="text" id="score" name="score" autocomplete="off" placeholder="请输入分数" class="layui-input">\
                </div>\
              </div>\
            <input type="submit" class="layui-btn" value="添加信息" />\
          </form>\
        '
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