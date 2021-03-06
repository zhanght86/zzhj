<%@ page language="java"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告管理</title>

</head>
<body>
<div class="PagingBody">
    <h3>公 告 管 理</h3>
</div>
<div class="buttonBar" id="buttonBarnoticeManager">
    <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNotice()">删除公告</a>
</div>
<table id="showNotice"></table>

<script type="text/javascript">
    $('#showNotice').datagrid({
        url: 'notice/queryAllList.action',
        pagination: true,
        singleSelect: true,  //仅可以选中一行
        fitColumns: true, //自动展开 铺满横向
        toolbar: "#buttonBarnoticeManager",  //使上方控制器和表格融为一体
        columns: [[
            {field: 'id', title: '编号', checkbox: true},
            {field: 'theme', title: '公告主题'},
            {field: 'text', title: '公告内容',width:400},
            {
                field: 'userId', title: '用户', formatter: function (value) {
                return value.name;
            }
            },
            {field: 'releaseDate', title: '发布日期'}
        ]]
    });

    function deleteNotice() {
        var Data = $('#showNotice').datagrid('getSelected');
        if (Data) {
            $.messager.confirm('警告', '您确认想要删除该公告吗？', function (r) {
                if (r) {
                    $.ajax({
                        url:"notice/deleteNotice.action",
                        type:'post',
                        data:{
                            noticeId:Data.id
                        },
                        success:function(data){
                            if(data>0){
                            	 $('#showNotice').datagrid("reload");
                                $.messager.alert("提示","删除成功！","info")
                            }else{
                                $.messager.alert("提示","数据有误，请后重试！","info")
                            }
                        },
                        error:function(){
                            $.messager.alert("提示","系统有误，请后重试！","info")
                        }
                    })
                }
            });
        }else{
            $.messager.alert("提示","请先选中一条公告！","info")
        }
    }
</script>
</body>
</html>