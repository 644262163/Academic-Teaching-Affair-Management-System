<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
  <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css"  media="all">
  <!-- ע�⣺�����ֱ�Ӹ������д��뵽���أ�����css·����Ҫ�ĳ��㱾�ص� -->
</head>
<body style="margin: 15px;">  
<div style="margin-bottom: 5px;">          
  <p>ѧ���û���</p>

</div>
 
<div class="layui-btn-group demoTable">
  <button class="layui-btn" data-type="getCheckData">��ȡѡ��������</button>
  <button class="layui-btn" data-type="getCheckLength">��ȡѡ����Ŀ</button>
  <button class="layui-btn" data-type="isAll">��֤�Ƿ�ȫѡ</button>
</div>
 
<table class="layui-table" lay-data="{height:400, url:'<%=request.getContextPath() %>/admin/select_student_list.do', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{checkbox:true, fixed: true}"></th>
      <th lay-data="{field:'id', width:180, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'id', width:180}">�û���</th>
      <th lay-data="{field:'password', width:180, sort: true}">����</th>
      <th lay-data="{field:'name', width:180}">����</th>
      <th lay-data="{field:'clazz', width:180, sort: true}">�༶</th>
      <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
  </thead>
</table>

 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">�鿴</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">�༭</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">ɾ��</a>
</script>
               
          
<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>
<!-- ע�⣺�����ֱ�Ӹ������д��뵽���أ�����js·����Ҫ�ĳ��㱾�ص� -->
<script>
layui.use('table', function(){
  var table = layui.table;
  //�������ѡ��ѡ��
  table.on('checkbox(demo)', function(obj){
    console.log(obj)
  });
  //����������
  table.on('tool(demo)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      layer.msg('ID��'+ data.id + ' �Ĳ鿴����');
    } else if(obj.event === 'del'){
      layer.confirm('���ɾ����ô', function(index){
        obj.del();
        layer.close(index);
      });
    } else if(obj.event === 'edit'){
      layer.alert('�༭�У�<br>'+ JSON.stringify(data))
    }
  });
  
  var $ = layui.$, active = {
    getCheckData: function(){ //��ȡѡ������
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.alert(JSON.stringify(data));
    }
    ,getCheckLength: function(){ //��ȡѡ����Ŀ
      var checkStatus = table.checkStatus('idTest')
      ,data = checkStatus.data;
      layer.msg('ѡ���ˣ�'+ data.length + ' ��');
    }
    ,isAll: function(){ //��֤�Ƿ�ȫѡ
      var checkStatus = table.checkStatus('idTest');
      layer.msg(checkStatus.isAll ? 'ȫѡ': 'δȫѡ')
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