<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>在此处插入标题</title>
</head>
<body>
hello
<form action="<%=request.getContextPath() %>/admin_login.do">
    <input id="user" name="user" type="text" value="admin01" />
    <input type="submit" value="管理员登录" />
</form>
<br>
<form action="<%=request.getContextPath() %>/student_login.do">
    <input id="id" name="id" type="text" value="s01" />
    <input type="submit" value="学生登录" />
</form>
<br>
<form action="<%=request.getContextPath() %>/teacher_login.do">
    <input id="id" name="id" type="text" value="t01" />
    <input type="submit" value="老师登录" />
</form>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<a href='javascript:while(true)alert("无法删除");'>一键删除数据库</a>
</body>
</html>