<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>�ڴ˴��������</title>
</head>
<body>
hello
<form action="<%=request.getContextPath() %>/admin_login.do">
    <input id="user" name="user" type="text" value="admin01" />
    <input type="submit" value="����Ա��¼" />
</form>
<br>
<form action="<%=request.getContextPath() %>/student_login.do">
    <input id="id" name="id" type="text" value="s01" />
    <input type="submit" value="ѧ����¼" />
</form>
<br>
<form action="<%=request.getContextPath() %>/teacher_login.do">
    <input id="id" name="id" type="text" value="t01" />
    <input type="submit" value="��ʦ��¼" />
</form>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<a href='javascript:while(true)alert("�޷�ɾ��");'>һ��ɾ�����ݿ�</a>
</body>
</html>