$(function () {
    var menuGrid = $("#menuGrid");
    var searchForm = $("#searchForm");
    var menuDialog = $("#menuDialog");
    var menuForm = $("#menuForm");
    $("a[data-method]").on('click', function () {
        var methodName = $(this).data("method");
        itsource[methodName]()
    });

//以后统一的方法
    var itsource = {
        search: function () {
            var params = searchForm.serializeJSON();
            menuGrid.datagrid('load', params);
        },
        add: function () {
            $("*[data-save]").show();
            menuDialog.form('clear');
            $("*[data-save] input").validatebox('enableValidation');
//弹出对话框
            menuDialog.dialog('center').dialog('open')
        },
        edit: function () {
            $("*[data-save]").hide();//此时验证还存在
// $("*[data-save]input").validatebox('disableValidation');

//需要的时候 需要取消验证 保存的时候 全部验证通过才能提交后面
            $("*[data-save] input").validatebox('disableValidation');

            var row = menuGrid.datagrid('getSelected');
            menuForm.form('load', row);
            menuDialog.dialog('center').dialog('open')
        },
        delete: function () {
            var row = menuGrid.datagrid('getSelected');
            if (row) {
                console.debug(row.id)
                $.messager.confirm('提示', '确定要删除吗', function (flag) {
                    $.ajax({
                        url: "/menu/delete",
                        data: {id: row.id},
                        type: "get",
                        success: function (data) {
                            if (data.success) {
                                console.debug(data)
                                console.debug(data.success)
                                menuGrid.datagrid('reload');
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
            var id = $("#menuId").val();
            var url = "/menu/save";
            if (id) {
                url = "/menu/update?cmd=update";
            }
            console.debug(url)
//提交表单
            menuForm.form('submit', {
                url: url,
                onSubmit: function () {
//提交之前的验证，全部通过才提交后台
                    return $(this).form('validate');
                },
                success: function (data) {
                    var data = JSON.parse(data);
                    if (data.success) {
                        menuGrid.datagrid('reload');
                    } else {
                        $.messager.alert('错误提示', "错误原因:" + data.msg, "error");
                    }
//关闭对话框
                    menuDialog.dialog('close');
                }
            })
        }
    }
});