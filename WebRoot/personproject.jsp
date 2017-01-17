<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<%@taglib tagdir="/WEB-INF/tags" prefix="k"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>项目成员维护</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<k:import_js />
<script type="text/javascript" src="jquery-easyui-1.4/src/validate.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/icon.css">

<script type="text/javascript" src="jquery-easyui-1.4/jquery-2.0.2.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4/src/cookie.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4/src/validate.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>	 
<script type="text/javascript">
    
    var currentUrl;
    //添加操作 
	function newUser(){	
	  var authority=validate();
	 		 if(authority==false)
			    return;	   
	    $("#sys").css("opacity",0);	
		$('#dlg').dialog('open').dialog('setTitle','添加项目成员信息');
		$('#fm').form('clear');
		$('#proidtext').hide();
		$('#proid').hide();
				
		currentUrl = "<%=path%>/personproject/displaypersonproject_addPersonProject.action";
	}
    

     //修改操作
    function editUser(){
    var authority=validate();
	 		 if(authority==false)
			    return;	   
    	//获取当前选择的记录行
    	 $("#sys").css("opacity",1);	
    	var row=$('#dg').datagrid('getSelected');
    	if(row){
    		$('#dlg').dialog('open').dialog('setTitle','修改项目成员信息');
    		$("#sysid").val(row.ppid);
    		$("#sysid").attr("disabled","disabled");
    		$("#remark").val(row.remark);
    		//form表单添加当前行记录信息
    		$('#fm').form('load',row);
    		$('#proidtext').show();
    		$('#proid').show();
    		$('#proid').attr('readonly','readonly');
    		//$('#pid').attr('readonly','readonly'); 
    		//设定修改跳转的Servlet
    		currentUrl="<%=path%>/personproject/displaypersonproject_updatePersonProject.action";
    		//alter(cu rrentUrl);
    	}
    }
    	
    //保存操作
	 function saveUser(){		
	 var title=$('#dlg').panel('options').title; 
	 var ppid=$("#sysid").val();
	  var projectitem=$("#projectitem").val();
	  var protext=$("#projectitem").find("option:selected").text();
      var cc=$('#cc').combobox('getValue');
      var ctext=$("#cc").combobox('getText');
	  var remark=$("textarea").val();
	  if(projectitem=="" || cc=="" || remark==""){
	    alert('项目成员信息不能为空');
	    return;
	  }
	  if(title=='添加项目成员信息'){
	   $.ajax({
		    url:currentUrl,
		    type:"post",
		    dataType:"json",
		    data:{
			    proid:projectitem,
			    pid:cc,
			    remark:remark
		   },
		   success:function(data,textStatus){
		      if(textStatus=='success' && eval(data).success=='true'){
		           //关闭当前窗口					
						 $('#dlg').dialog('close');
                         //重新加载数据
						$('#dg').datagrid('reload');
						 //window.location.reload();
		      }
		   },error:function(data){
		      window.location.reload();
		   }
		  });  
	  }else
	      if(title=='修改项目成员信息'){
	        $.ajax({
		    url:currentUrl,
		    type:"post",
		    dataType:"json",
		    data:{
			    proid:projectitem,
			    pid:cc,
			    remark:remark,
			    ppid:ppid
		   },
		   success:function(data,textStatus){
		      if(textStatus=='success' && eval(data).success=='true'){
		           //关闭当前窗口					
						 $('#dlg').dialog('close');
                         //重新加载数据
						$('#dg').datagrid('reload');
						 //window.location.reload();
		      }
		   },error:function(data){
		      window.location.reload();
		   }
		  });   
	      }
		  


	 
		/* 	$('#fm').form('submit',{
				url: currentUrl,
				onSubmit: function(){				
					return $(this).form('validate');	
				},
				success: function(result){								
					 var result = eval('(' + result +')');
					 if(result.success){	
                         //关闭当前窗口					
						 $('#dlg').dialog('close');
                         //重新加载数据
						//$('#dg').datagrid('reload');
						 window.location.reload();
					 }else{						
						 $.messager.show({
							 title:'Error',
							 msg:result.msg
						 });
					 }
				}
			}
			); */
			
		}
	
	  //删除操作
 		function deleteUser(){
 		    var authority=validate();
	 		 if(authority==false)
			    return;	   
			 currentUrl="<%=path%>/personproject/displaypersonproject_deletePersonProject.action";			 
			 //获取选择的当前行
			 var row = $('#dg').datagrid('getSelected');		
			 if(row){
				  $.messager.confirm('Confirm','确定删除' +"\""+row.projectName +"\""+ '该记录吗?',function(r){
					  if(r){			 
							$.post(currentUrl,{ppid:row.ppid},function(result){						   
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
		t&nbsp; <a href="javaScript:newUser();" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">增加</a> <a
			href="javaScript:editUser()" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true">修改</a> <a
			href="javaScript:deleteUser()" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true">删除</a>

		<!-- <div id="search" style="padding:3px">		
		请输入查询信息------<span>姓名:</span>
		<input id="selectname"  style="line-height:18px;border:1px solid #ccc">
		<a href="javaScript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">
		Search</a>
		</div> -->

	</div>


	<table id="dg" title="项目信息" class="easyui-datagrid" toolbar="#toolbar"
		url="<%=path%>/personproject/displaypersonproject_displaypersonProject.action"
		pagination="true" pagePosition="bottom" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead frozen="true">
			<tr>
				<th field="ppid" width="80"align:'center'>系统编号</th>
				<th data-options="field:'projectid',width:100,align:'center'">项目编号</th>
				<th data-options="field:'projectName',width:200,align:'center'">项目名称</th>
				<th data-options="field:'pid',width:100,align:'center'">成员编号</th>
				<th data-options="field:'name',width:100,align:'center'">成员姓名</th>
				<th data-options="field:'remark',width:200,align:'center'">备注</th>
			</tr>
		</thead>
	</table>

	<!-- 添加对话框 -->
	<div id="dlg" class="easyui-dialog"
		style="width:400px;height:280px;padding:10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">添加项目成员信息</div>
		<hr />
		<form id="fm" method="post" novalidate>
			<div class="fitem" id="sys">
				<label>系统编号:</label> <input type="text" style="width: 150px"
					id="sysid" />
			</div>
			<div class="fitem">
				<label>项目名称:</label> <select id="projectitem" style="width: 100px;">

				</select>
			</div>

			&nbsp;&nbsp;&nbsp;
			<div class="fitem">
				<label>成员姓名:</label>
				<!-- <input name="personId" class="easyui-validatebox" required="true">  -->
				<!-- 下拉框显示项目负责人 -->
				<select id="cc" style="width: 100px;">

				</select>
				<%-- <input id="cc" class="easyui-combobox" name="person.pid"  
    data-options="valueField:'pid',textField:'name',url:'<%=path%>/project/displayproject_addInputPerson.action'" />  --%>
			</div>
			<div>
				<label>备注</label>
				<textarea rows="10" cols="20" id="remark"></textarea>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javaScript:saveUser()" class="easyui-linkbutton"
			iconCls="icon-ok">Save</a> <a href="javaScript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	<script type="text/javascript">
          	$('#cc').combobox({
					url : '<%=path%>/project/displayproject_addInputPerson.action',
					valueField : 'pid',
					textField : 'name',
					edittable : false,
					onLoadSuccess : onLoadSuccess
				});
				 function onLoadSuccess() {
					var target = $(this);
					var data = target.combobox("getData");
					var options = target.combobox("options");
					if (data && data.length > 0) {
						var fs = data[0];
						target.combobox("setValue", fs[options.valueField]);
				}		
	 }
	$.ajax({
				  url:"<%=path%>/project/displayproject_addInputProject.action",
				  type:"post",
				  dataType:"json",
				  success:function(data,textStatus){
				    var json=eval(data);
				    for(var i=0;i<json.length;i++){
				     $("#projectitem").append("<option value='"+json[i].proid+"'>"+json[i].projectname+"</option>");
				    }
				  },
				  error:function(){
				    window.location.reload();
				  }
				});
     </script>
</body>
</html>
