<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看日志</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table id="jobLogtt"></table>  
    
    <div id="jobLogDd"  class="easyui-dialog" closed=true >          
    	 <div id="jobLogTb">
    	 	<input name="jobLogDate" type="date" />
    	 	<div style="margin-left:80px; display: inline;">
    	 		选择类型：  
				<select id="type2">
					<option>工作日报</option>
					<option>工作周报</option>
				</select>
			</div>
			<button style="margin-left:20px;" onclick="deleteJobLog()">删除</button>
			<button style="margin-left:4px;" onclick="searchJobLog()">搜索</button>
		</div>
		<table id="jobLogDg"></table>  
	</div>
    <script type="text/javascript">
    		var userId;
			$('#jobLogtt').treegrid({    
   				url:'users/roleUser.action',    
   				idField:'id',    
    			treeField:'text',    
   				columns:[[    
        		{field:'id',title:'编号',formatter:function(value){ userId=value;},hidden:true},    
        		{field:'text',title:'名称',width:300}, 
        		{field:'departmentName',title:'部门',width:200}, 
        		{field:'roleName',title:'职位',width:200},    
       		 	{field:'null',title:'查看',width:200,formatter:function(){
       		 		return "<a href='javascript:selectLog("+userId+")'>查看 </a>";
       		 	}}    
    			]]    
			}); 
			
			
			var jobLogId;
			function selectLog(userId){
				$('#jobLogDd').dialog({    
   					 title: '查看日志',    
   					 width: 600,    
    				height: 300,    
    				closed: false,     
    				modal: true ,
    				onOpen:function(){
    						$('#jobLogDg').datagrid({    
   			 				url:'jobLog/query.action',
   			 				queryParams: {
								id:userId
							},
   							fitColumns:true,
   			 				pagination:true,
   			 				singleSelect:true,
   			 				columns:[[
   			   					{field:'id',title:'编号',formatter:function(value){
   			   						jobLogId=value;
   			   						return value;
   			   					}},      
        						{field:'theme',title:'主题'}, 
        						{field:'date',title:'时间'}, 
        						{field:'null',title:'查看',width:200,formatter:function(){
       		 						return "<a href='javascript:showLog("+jobLogId+")'>查看 </a>";
       		 					}}        
    						]]    
						}); 
    				} 
			});  
		}
		//
			function searchJobLog(){
				 var date=$("input[name='jobLogDate']").val();
				var type2=$("#type2").find("option:selected").text();
				$('#jobLogDg').datagrid({    
   			 				url:'jobLog/searchJobLog.action',
   			 				queryParams: {
								date:date,
								type:type2,
								'user.id':userId
							},
   							fitColumns:true,
   			 				pagination:true,
   			 				singleSelect:true,
   			 				columns:[[
   			   					{field:'id',title:'编号',formatter:function(value){
   			   						jobLogId=value;
   			   						return value;
   			   					}},    
        						{field:'theme',title:'主题'}, 
        						{field:'date',title:'时间'}, 
        						{field:'null',title:'查看',width:200,formatter:function(){
       		 						return "<a href='javascript:showLog("+jobLogId+")'>查看 </a>";
       		 					}}        
    						]]    
						}); 
			}
			//
			
		function deleteJobLog(){
			var row = $('#jobLogDg').datagrid('getSelected');
			 if (row){
				$.post('jobLog/deleteJobLog.action',{'id':row.id},function(data){
					if(data>0){
						$('#jobLogDg').datagrid('reload');
						 $.messager.alert("提示", "删除成功", "info"); 
					}
				}); 	
			}else{
				 $.messager.alert("提示", "请选中一行记录", "info");  
			} 
		}
		//
		function showLog(jobLogId){
			$.post('jobLog/queryId.action',{'id':jobLogId},function(data){
				window.open(data);   
			});
		}
    </script>
  </body>
</html>
