<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/6
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%@include file="head.jsp" %>
    <script src="/js/main.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:100px;">
    <h1>源码时代智销系统</h1>
    <div style="float: right">
        欢迎
        <span style="color: #1e16ff">
            <shiro:user>
                <shiro:principal property="username"/>
            </shiro:user>
        </span>
        登陆，<a href="/logout">退出</a>
    </div>
</div>
<div data-options="region:'west',title:'菜单',split:true" style="width:230px;">
    <ul id="menuTree"></ul>
</div>
<div id="dataTab" data-options="region:'center'"
     class="easyui-tabs" style="padding:5px;background:#eee;">
    <div title="Home">
    </div>
</div>
</body>

</html>
