<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/6
  Time: 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script src="/easyui/plugin/jquery.jdirk.js"></script>
<%--css 层叠样式表--%>
    <link href="/easyui/plugin/validatebox/jeasyui.extensions.validatebox.css" rel="stylesheet">
    <script src="/easyui/plugin/validatebox/jeasyui.extensions.validatebox.rules.js"></script>
    <script src="/js/employee.js"></script>
    <meta>
</head>
<body>
<table id="employeeGrid" class="easyui-datagrid"
       data-options="fit:true,fixed:true,fitColumns:true,toolbar:'#tb',singleSelect:true,method:'post'" ;
       url="/employee/page"
       iconCls="icon-save"
       rownumbers="true" pagination="true">
    <thead>
    <tr>
        <th width="20" field="headimage" data-options="formatter:imgFormat">头像</th>
        <th width="20" field="username">用户名</th>
        <th width="20" field="password">密码</th>
        <th width="20" field="email">邮件</th>
        <th width="20" field="age" align="right">年龄</th>
        <th width="20" field="department" align="right" data-options="formatter:objFormat">部门名称</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding-top:15px;height:auto">
    <div style="padding-bottom: 10px">
        <a href="#" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a href="#" data-method="edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <shiro:hasPermission name="employee:delete">
        <a href="#" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        </shiro:hasPermission>
    </div>
    <!-- 这部门是查询的功能 -->
    <div>
        <form id="searchForm" action="/employee/download" method="post">
            用户名： <input name="username" class="easyui-textbox" style="width:80px;height:32px">
            邮件: <input name="email" class="easyui-textbox" style="width:80px;height:32px">
            部门 :
            <input class="easyui-combobox" name="departmentId"
                   data-options="valueField:'id',textField:'name',panelHeight:'auto',url:'/util/departmentList'">
            <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查找</a>
        </form>
    </div>
</div>
<div id="employeeDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="employeeForm" method="post">
            <table cellpadding="5">
                <input id="employeeId" name="id" type="hidden">
                <tr>
                    <td>用户名:</td>
                    <td><input id="username" class="easyui-textbox" type="text" name="username" data-options="required:true" validType="checkUsername"></input></td>
                </tr>
                <tr data-save="true">
                    <td>密码:</td>
                    <td><input id="password" class="easyui-validatebox" type="text" name="password" data-options="required:true"></input></td>
                </tr>
                <tr data-save="true">
                    <td>确认密码:</td>
                    <td><input id="repassword" class="easyui-validatebox" type="text" name="repassword" data-options="required:true" validType="equals['#password']"></input></td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input class="easyui-textbox" type="text" name="email" data-options="required:true,validType:'email'"></input></td>
                </tr>
                <tr>
                    <td>年龄:</td>
                    <td><input class="easyui-textbox" type="text" name="age" data-options="required:true" validType="integerRange[18,80]"></input></td>
                </tr>
                <tr>
                    <td>部门:</td>
                    <td><input class="easyui-combobox" name="departmentId"
                               data-options="valueField:'id',textField:'name',panelHeight:'auto',url:'/util/departmentList'"></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick=$('#employeeForm').form('clear')>清除</a>
        </div>
    </div>
</div>
</body>


</html>
