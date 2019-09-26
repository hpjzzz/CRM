function imgFormat(data) {
    if (data) {
        return "<img style='height: 50px;width: 50px' src='"+data+"' alt='没有头像'/>";
    }
}
function objFormat(data) {
    if (data) {
        return data.name;
    }
}
//检查用户名是否重复

$.extend($.fn.validatebox.defaults.rules, {
    checkUsername: {
        validator: function(value,param){
            //新增和修改都用，但是新增不需要选择行
            // var row = $("#employeeGrid").datagrid('getSelected');
            var id = $("#employeeId").val();
            var result = false;
            $.ajaxSettings.async = false;
            $.get('/employee/checkUserName',{username:value,id:id},function (isExsits) {
                result = isExsits;
            })
            return result;
        },
        message: '用户名已存在'
    }
});
//检查密码是否一致
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '密码不一致'
    }
});

$(function () {
    var employeeGrid = $("#employeeGrid");
    var searchForm = $("#searchForm");
    var employeeDialog = $("#employeeDialog");
    var employeeForm = $("#employeeForm");
    $("a[data-method]").on('click',function () {
        var methodName = $(this).data("method");
        itsource[methodName]()
    });

    //以后统一的方法
    var itsource = {
        search:function () {
            var params = searchForm.serializeJSON();
            employeeGrid.datagrid('load',params);
        },
        add:function () {
            $("*[data-save]").show();
            employeeDialog.form('clear');
            $("*[data-save] input").validatebox('enableValidation');
            //弹出对话框
            employeeDialog.dialog('center').dialog('open')
        },
        edit:function () {
            $("*[data-save]").hide();//此时验证还存在
            // $("*[data-save]input").validatebox('disableValidation');

            //需要的时候 需要取消验证 保存的时候 全部验证通过才能提交后面
            $("*[data-save] input").validatebox('disableValidation');

            var row = employeeGrid.datagrid('getSelected');
            employeeForm.form('load',row);
            employeeDialog.dialog('center').dialog('open')
        },
        delete:function () {
            var row = employeeGrid.datagrid('getSelected');
            if (row) {
                console.debug(row.id)
                $.messager.confirm('提示','确定要删除吗',function (flag) {
                    $.ajax({
                        url:"/employee/delete",
                        data:{id:row.id},
                        type:"get",
                        success:function (data) {
                            if (data.success) {
                                console.debug(data)
                                console.debug(data.success)
                                employeeGrid.datagrid('reload');
                            }else {
                                console.debug("zzzzzzzzzzzzzz")
                                $.messager.alert('提示',"错误原因："+data.msg,'error');
                            }
                        }
                    });
                });
            }else {
                $.messager.alert('温馨提示',"请选中一行",'warning')
            }
        },
        save:function () {
            //新增和修改公用的提交表单
           var id = $("#employeeId").val();
           var url = "/employee/save";
           if (id) {
               url = "/employee/update?cmd=update";
           }
           console.debug(url)
           //提交表单
            employeeForm.form('submit',{
                url:url,
                onSubmit:function () {
                    //提交之前的验证，全部通过才提交后台
                    return $(this).form('validate');
                },
                success:function (data) {
                    var data = JSON.parse(data);
                    if (data.success) {
                        employeeGrid.datagrid('reload');
                    }else {
                        $.messager.alert('错误提示',"错误原因:"+data.msg,"error");
                    }
                    //关闭对话框
                    employeeDialog.dialog('close');
                }
            })
        }
    }
});