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
    <span>修改密码：</span>
</div>

<div class="layui-form layui-form-pane">
    <div class="layui-form-item">
        <label class="layui-form-label">请输入旧密码：</label>
        <div class="layui-input-block">
            <input type="text" id="oldPassword" name="oldPassword" autocomplete="off" placeholder="请输入旧密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">请输入新密码：</label>
        <div class="layui-input-block">
            <input type="text" id="newPassword" name="newPassword" autocomplete="off" placeholder="请输入新密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn" onclick="updatePassword()">修改信息</button>
    </div>
</div>

<script src="<%=request.getContextPath() %>/static/layui/layui.js" charset="utf-8"></script>

<script>
    function updatePassword(){
        var load = layer.load();
        $.ajax({
            url:'<%=request.getContextPath() %>/teacher/update_password.do',
            type:'GET',
            async:false,    //是否异步
            data:{
                teacherId:'${id }',
                oldPassword:$("#oldPassword").val(),
                newPassword:$("#newPassword").val(),
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
        layer.close(load);
    }

layui.use('layer', function(){
    var layer = layui.layer;

});
</script>

</body>
</html>