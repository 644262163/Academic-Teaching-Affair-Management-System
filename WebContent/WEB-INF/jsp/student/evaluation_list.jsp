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
  <div class="layui-inline">
    <label>评价</label>
    <div class="layui-input-inline">
      <input type="text" id="s_score" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label>学期</label>
    <div class="layui-input-inline">
      <input type="text" id="s_term" class="layui-input">
    </div>
  </div>
  <button class="layui-btn" data-type="searchEvaluation">搜索列表</button>
  <button class="layui-btn" data-type="refreshEvaluation">还原默认</button>
</div>
 
<table class="layui-table" lay-data="{height:471, limit: 10, url:'<%=request.getContextPath() %>/student/select_evaluation_list.do', where: {studentId: '${id }'}, page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'studentId', width:100, sort: true}">学生id</th>
      <th lay-data="{field:'studentName', width:100, sort: true}">学生姓名</th>
      <th lay-data="{field:'courseId', width:100, sort: true}">课程id</th>
      <th lay-data="{field:'courseName', width:100, sort: true}">课程名字</th>
      <th lay-data="{field:'term', width:100, sort: true}">学期</th>
      <th lay-data="{field:'score', width:100, sort: true}">评价</th>
      <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
  </thead>
</table>

 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-mini" lay-event="edit">修改评价</a>
</script>
               
          
<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>

<script>
var updateObj, updateIndex;
function updateEvaluation(){
  var load = layer.load();
  $.ajax({
    url:'<%=request.getContextPath() %>/student/update_evaluation.do',
    type:'GET', 
    async:false,    //是否异步
    data:{
        studentId: '${id }',
      courseId:$("#courseId").val(),
      score:$("#score").val(),
    },
    timeout:5000,    //超时时间
    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    success:function(result){
      if(result.success){
        layer.msg('修改成功', {icon: 1});
        updateObj.update({
          score:$("#score").val(),
        });
      } else{
        layer.msg('修改失败', {icon: 2});
      }
    },
    error:function(xhr,textStatus){
      layer.msg('修改失败', {icon: 2});
    }
  });
  layer.close(updateIndex);
  layer.close(load);
}

layui.use('table', function(){
  var table = layui.table;
  //监听表格复选框选择
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //监听工具条
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
      //layer.alert('编辑行：<br>'+ JSON.stringify(data));
      updateObj = obj;
      updateIndex = layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['840px', '420px'], //宽高
        content: '\
          <div class="layui-form layui-form-pane">\
            <div class="layui-form-item">\
              <label class="layui-form-label">课程id</label>\
              <div class="layui-input-block">\
                <input type="text" id="courseId" name="courseId" autocomplete="off" placeholder="请输入课程id" class="layui-input" readonly unselectable="on">\
              </div>\
            </div>\
            <div class="layui-form-item">\
              <label class="layui-form-label">课程名字</label>\
              <div class="layui-input-block">\
                <input type="text" id="courseName" name="courseName" autocomplete="off" placeholder="请输入课程名字" class="layui-input" readonly unselectable="on">\
              </div>\
            </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">学期</label>\
                <div class="layui-input-block">\
                  <input type="text" id="term" name="term" autocomplete="off" placeholder="请输入学期" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">评价</label>\
                <div class="layui-input-block">\
                  <select id="score" name="score" lay-filter="evaluation">\
                    <option value=""></option>\
                    <option value="优">优</option>\
                    <option value="良">良</option>\
                    <option value="中">中</option>\
                  </select>\
                </div>\
              </div>\
              <button class="layui-btn" onclick="updateEvaluation()">修改信息</button>\
            </div>\
                '
      });
      $("#courseId").val(data.courseId);
        $("#courseName").val(data.courseName);
      $("#score").val(data.score);
        $("#term").val(data.term);
        layui.use('form', function(){
            var form = layui.form;
            form.render('select');
        });
    }
  });
  
  var $ = layui.$, active = {
    refreshEvaluation: function(){
      table.reload('idTest', {
        url: '<%=request.getContextPath() %>/student/select_evaluation_list.do'
        ,where: {studentId: '${id }'} //设定异步数据接口的额外参数
      });
    },
    searchEvaluation: function(){
      table.reload('idTest', {
        url: '<%=request.getContextPath() %>/student/select_evaluation_list.do'
        ,where: {
            studentId: '${id }',
            courseId: $("#s_courseId").val(),
            score: $("#s_score").val(),
              term:$("#s_term").val()
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