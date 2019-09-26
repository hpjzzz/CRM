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
    <script src="/js/role.js"></script>
    <meta>
</head>
<body>
<table id="roleGrid" class="easyui-datagrid"
       data-options="fit:true,fixed:true,fitColumns:true,toolbar:'#tb',singleSelect:true,method:'post'" ;
       url="/role/page"
       iconCls="icon-save"
       rownumbers="true" pagination="true">
    <thead>
    <tr>
        <th width="20"  field="name" sortable="true">角色名称</th>
        <th width="20"  field="sn" sortable="true">角色编号</th>
        <th width="20"  field="permission" sortable="true" data-options="formatter:permissionFormat">权限</th>
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
        <form id="searchForm" action="/role/download" method="post">
            名称: <input name="name" class="easyui-textbox" style="width:80px;height:32px">
            <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查找</a>
        </form>
    </div>
</div>
<div id="roleDialog" class="easyui-dialog" title="员工操作" style="width:900px;height:450px;padding:10px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div style="padding:10px 60px 20px 60px">
        <form id="roleForm" method="post">
            <input id="roleId" name="id" type="hidden">
            <table cellpadding="5">
                <tr>
                    <td>
                        角色名称:<input class="easyui-validatebox" type="text" name="name"></input>
                        角色编码:<input class="easyui-validatebox" type="text" name="sn"></input>
                    </td>
                </tr>
                <tr >
                    <td>
                        <div id="cc" class="easyui-layout" style="width:800px;height:300px;">
                            <div data-options="region:'west'" style="width:400px;">
                                <table id="userPermissionGrid">
                                    <thead>
                                    <tr>
                                        <th width="10"  field="name" >名称</th>
                                        <th width="20"  field="url" >对应的资源</th>
                                        <th width="20"  field="sn" >对象的权限</th>
                                    </tr>
                                    </thead>
                                </table>

                            </div>
                            <div data-options="region:'center'" style="width:400px;height:300px;background:#eee;">
                                <table id="allPermissionGrid">
                                    <thead>
                                    <tr>
                                        <th width="10"  field="name" >名称</th>
                                        <th width="20"  field="url" >对应的资源</th>
                                        <th width="20"  field="sn" >对象的权限</th>
                                    </tr>
                                    </thead>
                                </table>

                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" data-method="save">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick=$('#roleForm').form('clear')>清除</a>
        </div>
    </div>
</div>
</body>


</html>
