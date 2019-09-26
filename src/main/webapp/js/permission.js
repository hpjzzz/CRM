$(function () {
    var permissionGrid = $("#permissionGrid");
    var searchForm = $("#searchForm");
    var permissionDialog = $("#permissionDialog");
    var permissionForm = $("#permissionForm");
    $("a[data-method]").on('click', function () {
        var methodName = $(this).data("method");
        itsource[methodName]()
    });

//以后统一的方法
    var itsource = {
        search: function () {
            var params = searchForm.serializeJSON();
            permissionGrid.datagrid('load', params);
        },
        add: function () {
            $("*[data-save]").show();
            permissionDialog.form('clear');
            $("*[data-save] input").validatebox('enableValidation');
//弹出对话框
            permissionDialog.dialog('center').dialog('open')
        },
        edit: function () {
            $("*[data-save]").hide();//此时验证还存在
// $("*[data-save]input").validatebox('disableValidation');

//需要的时候 需要取消验证 保存的时候 全部验证通过才能提交后面
            $("*[data-save] input").validatebox('disableValidation');

            var row = permissionGrid.datagrid('getSelected');
            permissionForm.form('load', row);
            permissionDialog.dialog('center').dialog('open')
        },
        delete: function () {
            var row = permissionGrid.datagrid('getSelected');
            if (row) {
                console.debug(row.id)
                $.messager.confirm('提示', '确定要删除吗', function (flag) {
                    $.ajax({
                        url: "/permission/delete",
                        data: {id: row.id},
                        type: "get",
                        success: function (data) {
                            if (data.success) {
                                console.debug(data)
                                console.debug(data.success)
                                permissionGrid.datagrid('reload');
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
            var id = $("#permissionId").val();
            var url = "/permission/save";
            if (id) {
                url = "/permission/update?cmd=update";
            }
            console.debug(url)
//提交表单
            permissionForm.form('submit', {
                url: url,
                onSubmit: function () {
//提交之前的验证，全部通过才提交后台
                    return $(this).form('validate');
                },
                success: function (data) {
                    var data = JSON.parse(data);
                    if (data.success) {
                        permissionGrid.datagrid('reload');
                    } else {
                        $.messager.alert('错误提示', "错误原因:" + data.msg, "error");
                    }
//关闭对话框
                    permissionDialog.dialog('close');
                }
            })
        }
    }
});