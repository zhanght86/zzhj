<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>资质章办理</title>
	</head>
	<body>
		<!--审批表格-->
		 <div id="handling_Tb">
			<a  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add_handling_Tb()" >查看</a>			
		</div>
		<div id="handling_tanc" class="easyui-dialog"  style="width: 650px">
			<form action="" method="post" id="handling_form" class="approve-form">
				<input name="id" id="dis_none" style="display:none !important;color:#fff !important;" />
				<h2 align="center">资质章盖章经办单</h2>
				<ul class="cmn_list">
					<li><span>编号</span><input type="text" name="number"  value="" readonly="readonly" /></li>
					<li>
						<span>项目名称</span>
						<textarea name="projectName"  rows="" cols="" readonly="readonly" style="vertical-align: top;"W></textarea>
					</li>
					<li
						><span>页数</span>
						<input type="text" name="pageNumber" readonly="readonly" value="" />
					</li>
					<li>
						<span>份数</span>
						<input type="text" name="copiesNumber" readonly="readonly" value=""  />
					</li>
					<li>
						<span>盖章内容</span>
						<textarea name="text"  rows="" cols="" readonly="readonly" style="vertical-align: top;"></textarea>
					</li>
					<li>
						<span>盖章事由</span>
						<textarea name="why" rows="" cols="" readonly="readonly" style="vertical-align: top;"></textarea>
					</li>
					<li class="txt_ctr">
					<input type="button" value="经办" onclick="handling_Submit()"/>
					</li>
				</ul>
  			   
  			</form>

		</div>
		
		
		
		<table id="handling_Dg"></table>
			
			
		<script type="text/javascript">	
			
		$("#dis_none").hide();
		$('#handling_Dg').datagrid({
			url:'ziZhiSeal/approverZiZhiSeal.action',
		    fitColumns:false,
			pagination:true,
	 		singleSelect:true,
		    toolbar:'#handling_Tb',
	        onDblClickRow:function() {  //双击打开当前行详情函数
	           add_handling_Tb();
	        },
		   	columns:[[    
		        {field:'id',title:'id',checkbox:true},
				{field:'number',title:'编号',width:50},
				{field:'projectName',title:'项目名称',width:140},
				{field:'userId',title:'申请人',width:70,formatter:function(value){
	    			return value.name;
	    		}},
	    		{field:'requestDate',title:'申请时间',width:100,sortable:true},
	    		{field:'state',title:'状态',width:70},
	    		{field:'approver',title:'审批人',width:70},
	    		{field:'agent',title:'经办人',width:70},
	    		{field:'overDate',title:'盖章时间',width:100},
				{field:'text',title:'盖章内容',width:140},
				{field:'why',title:'盖章事由',width:140},
				{field:'pageNumber',title:'页数',width:70},
				{field:'copiesNumber',title:'份数',width:70}
		    ]]
		});
	
		handling_tanc = $('#handling_tanc').dialog({
			title : '经办',
			height : 400,
			closed : true,
			cache : false,
			modal : true
		});	
		
		
		//查看
		function add_handling_Tb(){		

			var row = $('#handling_Dg').datagrid('getSelected');
			if(row == null){
			   $.messager.alert("提示","请选择一条数据","info");
			}else{
				var  aa = row.why;
				if(aa == "是"){
					$(".cmn_list li input[type = radio]").eq(0).click()
				}else if(aa == "否"){
					$(".cmn_list li input[type = radio]").eq(1).click()
				}
				$("input[name = id]").val(row.id);
				$("input[name = number]").val(row.number);
				$("textarea[name = projectName]").val(row.projectName);
				$("input[name = pageNumber]").val(row.pageNumber);
				$("input[name = copiesNumber]").val(row.copiesNumber);
				$("textarea[name = text]").val(row.text);
				$("textarea[name = why]").val(row.why);
				handling_tanc.dialog('open') 
			}
		}

		 function handling_Submit(){	 	
			 var id = $("#dis_none").val();
			$.ajax({
				   url:"ziZhiSeal/handLing.action",
				   type:"post",
				   data:{'id':id},	   
				   success:function(data){
					   if(data == 1){
					   		$('#handling_tanc').dialog({
								closed : true
							});
					   	$('#handling_Dg').datagrid('reload');
					   	$.messager.alert("提示","经办完成","info");
					   	spprove_tanc.dialog('close');
					   	$("#listMes li").each(function(){
					   		 var target=$(this).attr("id");
					   		 if(target==id){
					   		 	 $(this).remove(); 
					   		 	var number =$("#listMes").children('li').length;
					   		 	if(number==0){
					   		 	 $("#message").hide();  
					   		 	}
					   		 }
					   	})
					   }else{
					   	$.messager.alert("提示","提交失败","info");
					   }
				   }
			})
		}
  	</script>
	</body>
</html>
