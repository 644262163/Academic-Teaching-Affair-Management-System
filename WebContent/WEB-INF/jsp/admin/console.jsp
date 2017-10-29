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
  <div class="layui-inline">
    <label class="layui-form-label">用户ID</label>
    <div class="layui-input-inline">
      <input type="text" id="s_userId" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label>IP</label>
    <div class="layui-input-inline">
      <input type="text" id="s_ip" class="layui-input">
    </div>
  </div>
  <div class="layui-inline">
    <label>结果</label>
    <div class="layui-input-inline">
      <input type="text" id="s_result" class="layui-input">
    </div>
  </div>
  <button class="layui-btn" data-type="searchLog">搜索列表</button>
  <button class="layui-btn" data-type="refreshLog">还原默认</button>
</div>
 
<table class="layui-table" lay-data="{height:471, limit: 90, url:'<%=request.getContextPath() %>/admin/select_log_list.do', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'id', width:100, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'userId', width:100}">用户ID</th>
      <th lay-data="{field:'ip', width:150}">IP</th>
      <th lay-data="{field:'module', width:100}">模块</th>
      <th lay-data="{field:'method', width:140}">方法</th>
      <th lay-data="{field:'result', width:100}">结果</th>
      <th lay-data="{field:'time', width:200}">时间</th>
      <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
  </thead>
</table>

<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看详情</a>
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
        //layer.msg('ID：'+ data.id + ' 的查看操作');
        layer.open({
          type: 1,
          skin: 'layui-layer-rim', //加上边框
          area: ['840px', '420px'], //宽高
          content: '\
            <div class="layui-form layui-form-pane">\
              <div class="layui-form-item">\
                <label class="layui-form-label">id</label>\
                <div class="layui-input-block">\
                  <input type="text" id="id" name="id" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">用户id</label>\
                <div class="layui-input-block">\
                  <input type="text" id="userId" name="userId" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">IP</label>\
                <div class="layui-input-block">\
                  <input type="text" id="ip" name="ip" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">URL</label>\
                <div class="layui-input-block">\
                  <input type="text" id="url" name="url" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">参数</label>\
                <div class="layui-input-block">\
                  <input type="text" id="parameter" name="parameter" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">时间</label>\
                <div class="layui-input-block">\
                  <input type="text" id="time" name="time" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">运行时间（ms）</label>\
                <div class="layui-input-block">\
                  <input type="text" id="length" name="length" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">模块</label>\
                <div class="layui-input-block">\
                  <input type="text" id="module" name="module" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">方法</label>\
                <div class="layui-input-block">\
                  <input type="text" id="method" name="method" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">结果</label>\
                <div class="layui-input-block">\
                  <input type="text" id="result" name="result" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
              <div class="layui-form-item">\
                <label class="layui-form-label">信息</label>\
                <div class="layui-input-block">\
                  <input type="text" id="message" name="message" autocomplete="off" class="layui-input" readonly unselectable="on">\
                </div>\
              </div>\
            </div>\
          '
        });
        $("#id").val(data.id);
        $("#userId").val(data.userId);
        $("#ip").val(data.ip);
        $("#url").val(data.url);
        $("#parameter").val(data.parameter);
        $("#time").val(data.time);
        $("#length").val(data.length);
        $("#module").val(data.module);
        $("#method").val(data.method);
        $("#result").val(data.result);
        $("#message").val(data.message);
      }
    });
    
    var $ = layui.$, active = {
      refreshLog: function(){
        table.reload('idTest', {
          url: '<%=request.getContextPath() %>/admin/select_log_list.do'
          ,where: {} //设定异步数据接口的额外参数
        });
      },
      searchLog: function(){
        table.reload('idTest', {
          url: '<%=request.getContextPath() %>/admin/select_log_list.do'
          ,where: {
              userId: $("#s_userId").val(),
              ip: $("#s_ip").val(),
              result: $("#s_result").val()
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