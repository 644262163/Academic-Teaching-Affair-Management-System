<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>��̨����</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/static/layui/css/layui.css">
  <script src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">����Ա��̨����</div>
    <!-- ͷ�����򣨿����layui���е�ˮƽ������ -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">����̨</a></li>
      <li class="layui-nav-item"><a href="">�û�����</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">����ϵͳ</a>
        <dl class="layui-nav-child">
          <dd><a href="">�γ̹���</a></dd>
          <dd><a href="">�ɼ�����</a></dd>
          <dd><a href="">���۹���</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          ${administrator.user }
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">��������</a></dd>
          <dd><a href="">�޸�����</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">�˳�</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- ��ർ�����򣨿����layui���еĴ�ֱ������ -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:$('#iframe').attr('src','');">�û�����</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:$('#iframe').attr('src','admin/admin_list.do');">����Ա</a></dd>
            <dd><a href="javascript:;">��ʦ</a></dd>
            <dd><a href="javascript:;">ѧ��</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item">
          <a href="javascript:;">�γ̹���</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">�б�һ</a></dd>
            <dd><a href="javascript:;">�б��</a></dd>
            <dd><a href="">������</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">�ɼ�����</a></li>
        <li class="layui-nav-item"><a href="">���۹���</a></li>
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- ������������ -->
    <iframe id="iframe" src=""
      frameborder="0" width="100%" scrolling="no" height="100%">
      <p>�����������֧��  iframe ��ǩ��</p>
    </iframe>
  </div>
  
  <div class="layui-footer">
    <!-- �ײ��̶����� -->
    Academic-Teaching-Affair-Management-System
  </div>
</div>
<script src="<%=request.getContextPath() %>/static/layui/layui.js"></script>
<script>
//JavaScript��������
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>