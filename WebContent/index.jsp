<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆页面</title>
    <script src="<%=request.getContextPath() %>/static/jquery-easyui-1.3.5/jquery.min.js"></script>

</head>
<body>
<form action="<%=request.getContextPath() %>/index.do" method="post">
    <INPUT id="id" name="id" type="text"
           placeholder="请输入用户名" value="admin01">
    <INPUT id="password" name="password" type="password"
           placeholder="请输入密码" value="">
    <span>${msg }</span>
    <input type="submit" value="登录" />
</form>
<div>
    <a href='javascript:$("#id").val("admin01");$("#password").val("123");'>一键填写管理员账号密码</a>
    <br>
    <a href='javascript:$("#id").val("t1");$("#password").val("123");'>一键填写老师账号密码</a>
    <br>
    <a href='javascript:$("#id").val("s1");$("#password").val("123");'>一键填写学生账号密码</a>
</div>
</body>
</html>