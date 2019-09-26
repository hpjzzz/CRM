function permissionFormat(data) {
    var str = "";
    for (var i = 0; i < data.length; i++) {
        var permission = data[i];
        if (i != data.length - 1) {
            str += permission.name + ",";
        } else {
            str += permission.name;
        }
    }
    return str;
}


$(function () {
    var roleGrid = $("#roleGrid");
    var searchForm = $("#searchForm");
    var roleDialog = $("#roleDialog");
    var roleForm = $("#roleForm");
    $("a[data-method]").on('click', function () {
        var methodName = $(this).data("method");
        itsource[methodName]()
    });

//以后统一的方法
    var itsource = {
        search: function () {
            var params = searchForm.serializeJSON();
            roleGrid.datagrid('load', params);
        },
        add: function () {
            $("*[data-save]").show();
            roleDialog.form('clear');
            $("*[data-save] input").validatebox('enableValidation');
//弹出对话框
            roleDialog.dialog('center').dialog('open')

            //清除表格里面的数据
            $("#userPermissionGrid").datagrid("loadData",[]);
        },
        edit: function () {
            //弹出表单窗口
            //选中了某一条数据才删除
            var row = roleGrid.datagrid("getSelected");
            if(row) {
                //隐藏有data-save属性的元素
                $("*[data-save]").hide();
                //禁用有data-save属性的input元素的验证功能
                $("*[data-save] input").validatebox("disableValidation");

                roleForm.form("clear");//清除数据
                roleDialog.dialog("center").dialog("open");
                //为form加载数据
                $("#roleForm").form("load",row);
                //会出现数据回显bug
                // $("#userPermissionGrid").datagrid('loadData',row.permission)
                //修改权限回显操作
                var permissions = [];
                $.extend(permissions,row.permission);
                $("#userPermissionGrid").datagrid('loadData',permissions);

            }else{
                $.messager.alert('提示信息','请选择一行再进行修改!','info');
            }
        },
        delete: function () {
            var row = roleGrid.datagrid('getSelected');
            if (row) {
                console.debug(row.id)
                $.messager.confirm('提示', '确定要删除吗', function (flag) {
                    $.ajax({
                        url: "/role/delete",
                        data: {id: row.id},
                        type: "get",
                        success: function (data) {
                            if (data.success) {
                                console.debug(data)
                                console.debug(data.success)
                                roleGrid.datagrid('reload');
                            } else {
                                $.messager.alert('提示', "错误原因：" + data.msg, 'error');
                            }
                        }
                    });
                });
            } else {
                $.messager.alert('温馨提示', "请选中一行", 'warning')
            }
        },
        save: function () {
//新增和修改公用的提交表单
            var id = $("#roleId").val();
            var url = "/role/save";
            if (id) {
                url = "/role/update?cmd=update";
            }
            console.debug(url)
//提交表单
            roleForm.form('submit', {
                url: url,
                onSubmit: function (params) {
//提交之前的验证，全部通过才提交后台
                    //提交额外的参数 username=zs password=ls
                    //权限权限 permissions[0].id = 23
                    //做验证
                    //获取所有的数据
                    var rows = $("#userPermissionGrid").datagrid('getRows');
                    for (var i = 0; i < rows.length; i++) {
                        var row = rows[i];
                        //permissions[0].id = 1 permissions[1].id=2
                        params["permission[" + i + "].id"] = row.id;
                    }
                    return $("#roleForm").form("validate");
                },
                success: function (data) {
                    var data = JSON.parse(data);
                    if (data.success) {
                        roleGrid.datagrid('reload');
                    } else {
                        $.messager.alert('错误提示', "错误原因:" + data.msg, "error");
                    }
//关闭对话框
                    roleDialog.dialog('close');
                }
            })
        }, addPermissionRow: function (index, row) {

            //row 选中这行
            //如果有存在 不用再添加了
            //得到userPermissionGrid所有的数据
            var rows = $("#userPermissionGrid").datagrid('getRows');
            for (var i = 0; i < rows.length; i++) {
                var userRow = rows[i];
                if (userRow.id == row.id) {
                    $.messager.show({
                        title: '温馨提示',
                        msg: '该权限已经被添加过了..',
                        timeout: 5000,
                        showType: 'slide'
                    });
                    return;
                }
            }
            //添加到左边的表格
            $('#userPermissionGrid').datagrid('appendRow', row);
        },
        removePermissionRow: function (index, row) {
            //移除行
            $("#userPermissionGrid").datagrid('deleteRow', index);
        }
    }


    //创建表格:
    $("#userPermissionGrid").datagrid({
        fit: true,
        fixed: true,
        fitColumns: true,
        rownumbers: true,
        onDblClickRow: itsource.removePermissionRow
    });


    $("#allPermissionGrid").datagrid({
        url: "/permission/page",
        fit: true,
        fixed: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        onDblClickRow: itsource.addPermissionRow

    });
})