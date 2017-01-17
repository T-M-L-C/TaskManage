<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="k"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>项目管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="jquery-easyui-1.4/jquery-2.0.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4/src/cookie.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4/src/validate.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>	 
	<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/icon.css">
<script type="text/javascript">
    
    var currentUrl;
    //添加操作 
	function newUser(){		
	   var authority=validate();
	 		 if(authority==false)
			    return;	   
		$('#dlg').dialog('open').dialog('setTitle','添加项目信息');
		$('#fm').form('clear');
		$('#proidtext').hide();
		$('#proid').hide();
				
		currentUrl = "<%=path%>/project/displayproject_addProject.action";
	}
    

     //修改操作
    function editUser(){
       var authority=validate();
	 		 if(authority==false)
			    return;	   
    	//获取当前选择的记录行
    	var row=$('#dg').datagrid('getSelected');
    	if(row){
    		$('#dlg').dialog('open').dialog('setTitle','修改项目信息');
    		//form表单添加当前行记录信息
    		$('#fm').form('load',row);
    		$('#proidtext').show();
    		$('#proid').show();
    		$('#proid').attr('readonly','readonly');
    		currentUrl="<%=path%>/project/displayproject_updateProject.action";
    	}
    }
    	
    //保存操作
	 function saveUser(){		   
			$('#fm').form('submit',{
				url: currentUrl,
				onSubmit: function(){				
					return $(this).form('validate');
					
				},
				success: function(result){								
					 var result = eval('(' + result +')');
					 if(result.success){	
                         //关闭当前窗口					
						/*  $('#dlg').dialog('close');
                         //重新加载数据
						$('#dg').datagrid('reload'); */
					   window.location.reload();
					 }else{						
						 $.messager.show({
							 title:'Error',
							 msg:result.msg
						 });
					 }
				}
			}
			);
		}
	
	  //删除操作
 		function deleteUser(){
 		  var authority=validate();
	 		 if(authority==false)
			    return;	   
			 currentUrl="<%=path%>/project/displayproject_deleteProject.action";			 
			 //获取选择的当前行
			 var row = $('#dg').datagrid('getSelected');		
			 if(row){
				  $.messager.confirm('Confirm','确定删除' +"\""+row.projectName +"\""+ '该记录吗?',function(r){
					  if(r){			 
							$.post(currentUrl,{projectId:row.projectId},function(result){						   
							 if(result.success){							   
							 	      //关闭当前窗口					
									 $('#dlg').dialog('close');
			                         //重新加载数据
									$('#dg').datagrid('reload');
									// window.location.reload();
							 }else{				   
							 	$.messager.show({
							 		title:'error',
							 		msg:result.msg
							 	});
							 }
						 },'json'); 

					 } 
				 }); 
				 
			 }
			
		 }
		 //按条件查询部门信息
		 function doSearch(){
			 $('#dg').datagrid('load',{
			 	select:$('#selectname').val()
			 });
		 }

</script>
  </head>
  
  <body>
    <div id="toolbar">
       <a href="javaScript:newUser();" class="easyui-linkbutton" 
       iconCls="icon-add" plain="true" >增加</a>
       <a href="javaScript:editUser()" class="easyui-linkbutton" 
       iconCls="icon-edit" plain="true" >修改</a>
       <a href="javaScript:deleteUser()" class="easyui-linkbutton" 
       iconCls="icon-remove" plain="true" >删除</a> 
    
    	<!-- <div id="search" style="padding:3px">		
		请输入查询信息------<span>姓名:</span>
		<input id="selectname"  style="line-height:18px;border:1px solid #ccc">
		<a href="javaScript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">
		Search</a>
		</div> -->
    	
    </div>
  

    <table id="dg" title="项目信息" class="easyui-datagrid" toolbar="#toolbar"
     url="<%=path%>/project/displayproject_displayProject.action" pagination="true" pagePosition="bottom"
    rownumbers="true" fitColumns="true" singleSelect="true">
      <thead frozen="true">
         <tr>
           <th field="projectId" width="80">项目编号</th>
           <th data-options="field:'projectName',width:80">项目名称</th>
            <th data-options="field:'projectCode',width:80">项目编码</th>
            <th data-options="field:'name',width:80">项目负责人</th>
            <th data-options="field:'status',width:80">项目状态</th>
            <th data-options="field:'beginTime',width:80">开始时间</th>
            <th data-options="field:'endTime',width:80">结束时间</th>
            
         </tr>
      </thead>
    </table>
    
    <!-- 添加对话框 -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
    closed="true"  buttons="#dlg-buttons">
        <div class="ftitle">添加项目信息</div>
        <hr/>
        <form id="fm" method="post" novalidate>
        <div class="fitem">
        	   <label id="proidtext">项目编号:</label>
				<input id="proid" name="projectId"  required="true">        
			</div>
        	<div class="fitem">
        	   <label>项目名称:</label>
				<input name="projectName" class="easyui-validatebox" required="true">        
			</div>
			<div class="fitem">
        	   <label>项目编码:</label>
				<input name="projectCode" class="easyui-validatebox" required="true">        
			</div>
			<div class="fitem">
				<label>项目状态:</label>
			<select name="status">	 	
				<option value="立项" selected>立项</option>
				<option value="进行">进行</option>
				<option value="结项">结项</option></select>
			</div>
			<div class="fitem">
        	   	<label>项目负责人:</label>
				<!-- <input name="personId" class="easyui-validatebox" required="true">  --> 
				<!-- 下拉框显示项目负责人 -->      
			    <input id="cc" class="easyui-combobox" name="person.pid"  
    data-options="valueField:'pid',textField:'name',url:'<%=path%>/project/displayproject_addInputPerson.action'" /> 
			</div>
			<div class="fitem">
        	   <label>开始时间:</label>
				<input name="beginTime" class="easyui-datebox" required="true" id="beginTime">        
			</div>
			<div class="fitem">
        	   <label>结束时间:</label>
				<input name="endTime" class="easyui-datebox" required="true" id="endTime">        
			</div>
			
        </form>
    </div>
    <div id="dlg-buttons">
		<a href="javaScript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" >Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('.panel-tool-close').click()">Cancel</a>
	</div>
    <script type="text/javascript">
       //得到当前日期
 	formatterDate = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		  return date.getFullYear() + '-' + month + '-' + day;
   };

	window.onload = function () { 
	   $('#beginTime').datebox('setValue', formatterDate(new Date()));
	};
    </script>
  </body>
</html>
