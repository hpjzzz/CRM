<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>源码智销系统</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <script>
        //处理登录过期跳转回登陆界面
        if (top != window) {
            top.location.href = "/login";
        }
        function submitForm() {
            $("#loginForm").form('submit',{
                url:"/login",
                onSubmit:function () {
                    return $(this).form('validate');
                },
                success:function (data) {
                    var result = $.parseJSON(data);
                    if (result.success) {
                        location.href = '/main';
                    }else {
                        //打印错误信息
                        $.messager.show({
                            title:'提示',
                            msg:'登陆有问题:'+result.msg,
                            timeout:5000,
                            showType:'show'
                        });
                    }
                }
            })

        }
        //回车登陆
        $(function () {
            $(document.documentElement).on('keyup', function (event) {
                //event对象
                var keyCode = event.keyCode;
                if (keyCode === 13) {
                    submitForm();
                }
            })
        })
    </script>
</head>
<body>
<div align="center" style="margin-top: 100px;">
    <div class="easyui-panel" title="智销系统用户登陆" style="width: 350px; height: 240px;">
        <form id="loginForm" class="easyui-form" method="post">
            <table align="center" style="margin-top: 15px;">
                <tr height="20">
                    <td>用户名:</td>
                </tr>
                <tr height="10">
                    <td><input name="username" class="easyui-validatebox" required="true" value="admin" /></td>
                </tr>
                <tr height="20">
                    <td>密&emsp;码:</td>
                </tr>
                <tr height="10">
                    <td><input name="password" type="password" class="easyui-validatebox" required="true" value="0" /></td>
                </tr>
                <tr height="40">
                    <td align="center"><a href="javascript:;" class="easyui-linkbutton" onclick="submitForm();">登陆</a> <a
                            href="javascript:;" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
