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
    
    <title>任务分解</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

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
	  function newTask(){	
	      var authority=validate();
	 		 if(authority==false)
			    return;	   
		$('#dlg').dialog('open').dialog('setTitle','添加任务分解信息');
		$('#fm').form('clear');
		currentUrl = "<%=path%>/task/displayTask_addTask.action";
	}
	   //保存
	   function saveTask(){
	   
	   	      var title=$('#dlg').panel('options').title; 
	       if(title=="添加任务分解信息"){
	       var proid=$("#belong").val();
	       var pid=$('#cc').combobox('getValue');
	       var taskname=$("#taskname").val();
	       var tasktype=$("#tasktype").val();
	       var taskstatus=$("#taskstatus").val();
	       var begintime=$("#beginTime").datebox('getValue');
	       var endtime=$("#endTime").datebox('getValue');
	       var praticaltime=$("#praticalTime").datebox('getValue');
	       if(proid=="" || pid=="" || taskname=="" || tasktype=="" || taskstatus=="" || begintime=="" || endtime=="" || praticaltime==""){
	          alert("各项信息不能为空");
	          return;
	       }
	       var arr=new Array(taskname,tasktype,taskstatus,begintime,endtime,praticaltime,pid,proid);
	         $.ajax({
	            url:currentUrl,
	            type:"post",
	            dataType:"json",
	            data:{
		             array:JSON.stringify(arr)
	            },
	            success:function(data,textStatus){
	              var json=eval(data);
	              if(json.result=="success"){
	                      //关闭当前窗口					
						 $('#dlg').dialog('close');
                         //重新加载数据
						$('#dg').datagrid('reload');
						// window.location.reload();
	              }else{
	                 alert("添加失败");
	              }
	            },error:function(data){
	             window.location.reload();
	            }
	         });
	       }else
	       if(title=="更改任务状态"){
	            	var row=$('#dg').datagrid('getSelected');
	            	var taskname=$("#taskname").val();
	            	var taskstatus=$("#taskstatus").val();
	            	if(taskname=="" || taskstatus==""){
	            	   alert("任务名称和任务状态均不能为空"); 
	            	   return;
	            	}
	            	$.ajax({
	            	  url:currentUrl,
	            	  type:"post",
	            	  dataType:"json",
	            	  data:{
	            	    taskname:taskname,
	            	    taskstatus:taskstatus,
	            	    taskid:row.taskid
	            	  },
	            	  success:function(data,textStatus){
	            	       var json=eval(data);
	            	       if(json.result=="success"){
	            	               //关闭当前窗口					
								 $('#dlg').dialog('close');
		                         //重新加载数据
								$('#dg').datagrid('reload');
								// window.location.reload();
	            	       }
	            	  },error:function(data){
	            	     window.location.reload();
	            	  }
	            	});
	       }
	   }
	   //删除任务
	   function deleteTask(){
	      var authority=validate();
	 		 if(authority==false)
			    return;	   
	         currentUrl="<%=path%>/task/displayTask_deleteTask.action";			 
			 //获取选择的当前行
			 var row = $('#dg').datagrid('getSelected');		
			 if(row){
				 $.ajax({
				  url:currentUrl,
				  type:"post",
				  dataType:"json",
				  data:{
				    taskid:row.taskid
				  },
				  success:function(data,textStatus){
				     var json=eval(data);
				     if(json.result=='success'){
				            //关闭当前窗口					
						 $('#dlg').dialog('close');
                         //重新加载数据
						$('#dg').datagrid('reload');
						// window.location.reload();
				     }
				  },error:function(data){
				     window.location.reload();
				  }
				 });
			 }
	   }
	   //更新
	   function editTask(){
	     var authority=validate();
	 		 if(authoirty==false)
			  return;
	     	//获取当前选择的记录行
    	var row=$('#dg').datagrid('getSelected');
    	if(row){
    		$('#dlg').dialog('open').dialog('setTitle','更改任务状态');
    		$(".ftitle").html("修改任务状态");
    		$(".fitem").css("visibility","hidden");
    		//form表单添加当前行记录信息
    		$('#fm').form('load',row);
    		//设定修改跳转的Servlet
    		currentUrl="<%=path%>/task/displayTask_updateTask.action";
    		//alter(cu rrentUrl);
    		$("#taskname").val(row.taskname);
    	}
	   }
	   
	
	</script>
  </head>
  
  <body>
      <div id="toolbar">
       <a href="javaScript:newTask();" class="easyui-linkbutton" 
       iconCls="icon-add" plain="true" >添加任务</a>
       <a href="javaScript:deleteTask()" class="easyui-linkbutton" 
       iconCls="icon-remove" plain="true" >删除任务</a>
       <a href="javaScript:editTask()" class="easyui-linkbutton" 
       iconCls="icon-edit" plain="true" >更改任务状态</a> 
    
    	<!-- <div id="search" style="padding:3px">		
		请输入查询信息------<span>姓名:</span>
		<input id="selectname"  style="line-height:18px;border:1px solid #ccc">
		<a href="javaScript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">
		Search</a>
		</div> -->
    	<div id="search" style="padding:3px">		
		请输入查询条件------<span>项目名称:</span>
		<select  style="width: 150px" id="selectname">
		   <option value="0">------------</option>
		</select>
		<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">
		Search</a>
		</div>
    </div>
  

    <table id="dg" title="任务分解" class="easyui-datagrid" toolbar="#toolbar"
     url="<%=path%>/task/displayTask_displayTask.action" pagination="true" pagePosition="bottom"
    rownumbers="true" fitColumns="true" singleSelect="true">
      <thead frozen="true">
         <tr>
           <th field="taskid" width="80">任务编号</th>
           <th data-options="field:'projectName',width:100,align:'center'">所在项目</th>
            <th data-options="field:'name',width:100,align:'center'">执行人</th>
            <th data-options="field:'taskname',width:100,align:'center'">任务名称</th>
            <th data-options="field:'tasktype',width:100,align:'center'">任务类型</th>
            <th data-options="field:'taskstatus',width:100,align:'center'">任务状态</th>
            <th data-options="field:'begintime',width:100,align:'center'">预计开始时间</th>
            <th data-options="field:'endtime',width:100,align:'center'">预计结束时间</th>
            <th data-options="field:'praticaltime',width:100,align:'center'">实际结束时间</th>
         </tr>
      </thead>
    </table>
    
    <!-- 添加对话框 -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
    closed="true"  buttons="#dlg-buttons">
        <div class="ftitle">添加任务信息</div>
        <hr/>
        <form id="fm" method="post" novalidate>
        <div class="fitem">
        	   <label>所属项目:</label>
				<select style="width: 150px" id="belong">
				     
				</select>    
	  </div>
        	<div class="fitem">
        	   <label>执行人:</label>
				<select id="cc" style="width: 100px;">
				</select> 
			</div>
			<div>
        	   <label>任务名称:</label>
				<input name="taskname class="easyui-validatebox" required="true" id="taskname">        
			</div>
			<div class="fitem">
				<label>任务类型:</label>
			<select name="type"  id="tasktype" style="width: 150px">	 	
				<option value="需求" selected="selected">需求</option>
				<option value="设计">设计</option>
				<option value="开发">开发</option>
				</select>
			</div>
			<div>
        	   	<label>任务状态:</label>
				<select id="taskstatus">
					<option value="未开始" selected="selected">未开始</option>
					<option value="设计">设计</option>
					<option value="已完成">已完成</option>
				</select>
			</div>
			<div class="fitem">
        	   <label>预计开始时间:</label>
				<input name="beginTime" class="easyui-datebox" required="true" id="beginTime">        
			</div>
			<div class="fitem">
        	   <label>预计结束时间:</label>
				<input name="endTime" class="easyui-datebox" required="true" id="endTime">        
			</div>
			 <div class="fitem">
			     <label>实际结束时间:</label>
				<input name="praticaltime" class="easyui-datebox" required="true" id="praticalTime">        
			 </div>
        </form>
    </div>
    <div id="dlg-buttons">
		<a href="javaScript:saveTask()" class="easyui-linkbutton" iconCls="icon-ok" >确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('.panel-tool-close').click()">取消</a>
	</div>
  </body>
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
				     $("#selectname,#belong").append("<option value='"+json[i].proid+"'>"+json[i].projectname+"</option>");
				    }
				  },
				  error:function(){
				    window.location.reload();
				  }
				});
	   function doSearch(){
	     var proid=$("#selectname").val();
	     if(proid==0){
	       alert("请选择一个项目名称");
	       return ;
	     }
	     $('#dg').datagrid('load',{
			 	proid:proid
		   });
	   }
  </script>
</html>
