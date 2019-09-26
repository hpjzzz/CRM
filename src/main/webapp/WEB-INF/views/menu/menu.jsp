<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/6
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/easyui/plugin/jquery.jdirk.js"></script>
<%--css 层叠样式表--%>
    <link href="/easyui/plugin/validatebox/jeasyui.extensions.validatebox.css" rel="stylesheet">
    <script src="/easyui/plugin/validatebox/jeasyui.extensions.validatebox.rules.js"></script>
    <script src="/js/menu.js"></script>
    <meta>
</head>
<body>
<table id="menuGrid" class="easyui-datagrid"
       data-options="fit:true,fixed:true,fitColumns:true,toolbar:'#tb',singleSelect:true,method:'post'" ;
       url="/menu/page"
       iconCls="icon-save"
       rownumbers="true" pagination="true">
    <thead>
    <tr>
        <th width="20"  field="name" >名称</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding-top:15px;height:auto">
    <div style="padding-bottom: 10px">
        <a href="#" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a href="#" data-method="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a href="#" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    </div>
    <!-- 这是部门的查询的功能 -->
    <div>
        <form id="searchForm" action="/menu/download" method="post">
            名称: <input name="name" class="easyui-textbox" style="width:80px;height:32px">
            <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查找</a>
        </form>
    </div>
</div>
<div id="menuDialog" class="easyui-dialog" title="员工操作" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="menuForm" method="post">
            <input id="menuId" name="id" type="hidden">
            <table cellpadding="5">
                <tr>
                    <td>名称:</td>
                    <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick=$('#menuForm').form('clear')>清除</a>
        </div>
    </div>
</div>
</body>


</html>
