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
  <script src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
</head>
<body style="margin: 15px;">  
 
<div class="demoTable">
  <span>����Ա�û���</span>
  <button class="layui-btn" data-type="insertAdmin">��ӹ���Ա</button>
</div>
 
<table class="layui-table" lay-data="{height:471, limit: 10, url:'<%=request.getContextPath() %>/admin/select_admin_list.do', page:true, id:'idTest'}" lay-filter="demo">
  <thead>
    <tr>
      <th lay-data="{field:'user', width:250, sort: true, fixed: true}">ID</th>
      <th lay-data="{field:'user', width:250, sort: true}">�û���</th>
      <th lay-data="{field:'password', width:250, sort: true}">����</th>
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
      if(data.user === '${user }'){
        layer.alert('����ɾ���Լ�');
      } else{
        layer.confirm('���ɾ����ô', function(index){
          $.ajax({
            url:'<%=request.getContextPath() %>/admin/delete_admin.do',
            type:'GET', 
            async:false,    //�Ƿ��첽
            data:{
                user:data.user
            },
            timeout:5000,    //��ʱʱ��
            dataType:'json',    //���ص����ݸ�ʽ��json/xml/html/script/jsonp/text
            success:function(result){
                if(result.success){
                    layer.msg('ɾ���ɹ�');
                    obj.del();
                } else{
                    layer.msg('ɾ��ʧ��');
                }
            },
            error:function(xhr,textStatus){
                layer.msg('ɾ��ʧ��');
            }
          });
          layer.close(index);
        });
      }
    } else if(obj.event === 'edit'){
      //layer.alert('�༭�У�<br>'+ JSON.stringify(data));
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //���ϱ߿�
        area: ['840px', '420px'], //���
        content: '\
          <form class="layui-form layui-form-pane" action="<%=request.getContextPath() %>/admin/update_admin.do">\
            <div class="layui-form-item">\
              <label class="layui-form-label">�û���</label>\
              <div class="layui-input-block">\
                <input type="text" id="user" name="user" autocomplete="off" placeholder="�������û���" class="layui-input" readonly unselectable="on">\
              </div>\
            </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">����</label>\
                <div class="layui-input-block">\
                  <input type="text" id="password" name="password" autocomplete="off" placeholder="����������" class="layui-input">\
                </div>\
              </div>\
            <input type="submit" class="layui-btn" value="�޸���Ϣ" />\
          </form>\
        '
      });
      $("#user").val(data.user);
      $("#password").val(data.password);
    }
  });
  
  var $ = layui.$, active = {
    insertAdmin: function(){ //��ӹ���Ա
      layer.open({
        type: 1,
        skin: 'layui-layer-rim', //���ϱ߿�
        area: ['840px', '420px'], //���
        content: '\
          <form class="layui-form layui-form-pane" action="<%=request.getContextPath() %>/admin/insert_admin.do">\
            <div class="layui-form-item">\
              <label class="layui-form-label">�û���</label>\
              <div class="layui-input-block">\
                <input type="text" id="user" name="user" autocomplete="off" placeholder="�������û���" class="layui-input">\
              </div>\
            </div>\
            <div class="layui-form-item">\
                <label class="layui-form-label">����</label>\
                <div class="layui-input-block">\
                  <input type="text" id="password" name="password" autocomplete="off" placeholder="����������" class="layui-input">\
                </div>\
              </div>\
            <input type="submit" class="layui-btn" value="�����Ϣ" />\
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