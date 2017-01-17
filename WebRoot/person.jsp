<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="k" %>

<%@include file="checkuser.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<k:import_js/>
	
	<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/icon.css">

   
 <script type="text/javascript" src="jquery-easyui-1.4/src/validate.js"></script>
<script type="text/javascript">
  <%--  $(function(){
     $('#dg').datagrid({
                pagination: true,
                fitColumns: true,
                noheader:true,
                rownumbers: true,
                singleSelect: true,
                url: '<%=path%>/person/displayperson_displayPerson.action',
                method: 'POST',
       /*          queryParams: {stuCol:stuCol,stuCla:stuText}, */
                columns:[[
                    {field:'indexs',title:'编号',width:20,align:'center',formatter: function (value, row, index) {
                        return "<span class='left_txt'>"+(index+1)+"</span>";
                    }},
                    {field:'pid',title:'人员编号',width:100,align:'center'},
                    {field:'name',title:'姓名',width:100,align:'center'},
                    {field:'loginName',title:'登录名',width:100,align:'center'},
                    {field:'pwd',title:'密码',width:100,align:'center'/*,formatter: function (value, row, index) {
                        var innerHTML = "";
                        innerHTML += "<span class='left_txt'>";
                        innerHTML += "<input type='button' onclick='edit("+index+")' value='编辑'>";
                        innerHTML += "&nbsp;&nbsp;&nbsp;";
                        innerHTML += "<input type='button' onclick='deleteUsr(\""+row.uname+"\")' value='删除'>";
                        innerHTML += "</span>";

                        return innerHTML;
                    }*/},
                    {
                      field:'gender',title:'性别',width:100,align:'center'
                    },
                    {
                     field:'age',title:'年龄',width:100,align:'center'
                    },
                    {
                     field:'authority',title:'角色',width:100,align:'center'
                    }
                    
                ]]//加载完成
                ,pagination:true
                ,onLoadSuccess: function (data) {
                    _items = data.rows;
                }

            });
   }); --%>
            
    var currentUrl;
    //添加操作 
	function newUser(){	
	  var authority=validate();
	 		 if(authority==false)
			    return;	   
		$('#dlg').dialog('open').dialog('setTitle','添加用户');
		$('#fm').form('clear');
		
		currentUrl = "<%=path%>/person/displayperson_addPerson.action";
	}
    

     //修改操作
    function editUser(){
        var authority=validate();
	 		 if(authority==false)
			    return;	   
    	//获取当前选择的记录行
    	var row=$('#dg').datagrid('getSelected');
    	if(row){
    		$('#dlg').dialog('open').dialog('setTitle','修改用户信息');
    		//form表单添加当前行记录信息
    		$('#fm').form('load',row);
    		$('#pid').attr('readonly','readonly');
    		//设定修改跳转的Servlet
    		currentUrl="<%=path%>/person/displayperson_updatePerson.action";
    		
    	};
    }
    	
    //保存操作
	 function saveUser(){	
	          
	   			$("#loginName").attr("disabled",false);
			/*$('#fm').form('submit',
			   {
				url: currentUrl,
				onSubmit: function(){				
					return $(this).form('validate');
				},
				success: function(result){								
					 var result = eval(result);
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
			);*/
			   var pname=$("#pname").val();
			   var loginName=$("#loginName").val();
			   var pwd=$("#pwd").val();
			   var gender=$("#gender").val();
			   var age=$("#age").val();
			   var authority=$("#authority").val();
			   var person=new Person(pname,loginName,pwd,gender,age,authority);
			   var title=$('#dlg').panel('options').title;
			   if(title=="添加用户"){
					  $.ajax({
					  url:currentUrl,
					  type:"post",
					  dataType:"json",
					  data:{
					   name:$("#pname").val(),
					   pwd:$("#pwd").val(),
					   gender:$("#gender").val(),
					   age:$("#age").val(),
					   authority:$("#authority").val(),
					   loginName:$("#loginName").val()
					  },
					  success:function(data,textStatus){
					      if(textStatus=='success'){
					        var json=eval(data);
							switch (json.success) {
							case "again":
							   $.messager.alert("操作提示", "登录名重复！");
								break;
							case "true":
							   //关闭当前窗口					
								 $('#dlg').dialog('close');
		                         //重新加载数据
								$('#dg').datagrid('reload');
							break;
							default:
							   $.messager.alert("操作提示","添加失败");
								break;
							}
							
					      }
					  }
					  ,error:function(){
					    window.location.reload();
					  }
					}); 
			   }
			   else
			     if(title=="修改用户信息"){
			      var row=$('#dg').datagrid('getSelected');
			      $("#loginName").attr("disabled",true);
			     if(row){
			        $.ajax({
		    		 url:currentUrl,
		    		 type:"post",
		    		 dataType:"json",
		    		 data:{
		    		  pid:row.pid,
		    		  name:$("#pname").val(),
		    		  loginName:$("#loginName").val(),
		    		  pwd:$("#pwd").val(),
		    		  gender:$("#gender").val(),
		    		  age:$("#age").val(),
		    		  authority:$("#authority").val()
		    		 },
		    		 success:function(data,textStatus){
		    		    if(textStatus=='success' && eval(data).success=='true'){
		    		          //关闭当前窗口					
						 $('#dlg').dialog('close');
                         //重新加载数据
						$('#dg').datagrid('reload');
						// window.location.reload();
		    		    }
		    		 },
		    		 error:function(data){
		    		   window.location.reload();
		    		 }
		    		 
		    		}); 
			     }
			        
			     }
			
			//window.location.href=currentUrl;
		}
	    function Person(name,pwd,loginName,gender,age,authority){
	      this.name=name;
	      this.pwd=pwd;
	      this.loginName=loginName;
	      this.gender=gender;
	      this.age=age;
	      this.authority=authority;
	    }
	  //删除操作
 		function deleteUser(){
 		  var authority=validate();
	 		 if(authority==false)
			    return;	   
			 currentUrl="<%=path%>/person/displayperson_deletePerson.action";			 
			 //获取选择的当前行
			 var row = $('#dg').datagrid('getSelected');		
			 if(row){
				  $.messager.confirm('Confirm','确定删除' +row.pid + '该记录吗?',function(r){
					  if(r){		 
							 $.post(currentUrl,{pid:row.pid},function(result){						   
							 if(result.success){							   
							 	   //关闭当前窗口					
								 $('#dlg').dialog('close');
		                         //重新加载数据
								$('#dg').datagrid('reload');
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
    
    	<div id="search" style="padding:3px">		
		请输入查询信息------<span>姓名:</span>
		<input id="selectname"  style="line-height:18px;border:1px solid #ccc">
		<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search" plain="true">
		Search</a>
		</div>
    	
    </div>
  

     <table id="dg" title="人员信息" class="easyui-datagrid" toolbar="#toolbar"
     url="<%=path%>/person/displayperson_displayPerson.action" pagination="true" pagePosition="bottom"
    rownumbers="true" fitColumns="true" singleSelect="true">
      <thead frozen="true">
         <tr>
            <th field="pid" width="80" align="center">人员编号</th>
            <th data-options="field:'name',align:'center',width:100">姓名</th>
            <th data-options="field:'loginName',align:'center',width:100">登录名</th>
            <th data-options="field:'pwd',align:'center',width:100">密码</th>
            <th data-options="field:'gender',align:'center',width:100">性别</th>
            <th data-options="field:'age',align:'center',width:100">年龄</th>
            <th data-options="field:'authority',width:100">角色</th>
         </tr>
      </thead>
    </table> 
     <!-- <table id="dg" class="easyui-datagrid">
       
     </table> -->
    <!-- 添加对话框 -->
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
    closed="true"  buttons="#dlg-buttons">
        <div class="ftitle">用户基本信息</div>
        <form id="fm" method="post" >
        <!-- 	<div class="fitem">
        	   <label>人员编号:</label>
				<input id="pid"  name="pid" class="easyui-validatebox" required="true">        
			</div> -->
			<div class="fitem">
        	   <label>姓名:</label>
				<input name="pname" class="easyui-validatebox" required="true" id="pname">        
			</div>
			<div class="fitem">
        	   <label>登录名:</label>
				<input name="loginName" class="easyui-validatebox" required="true" id="loginName" >        
			</div>
			<div class="fitem">
        	   <label>密码:</label>
				<input name="pwd" class="easyui-validatebox" required="true" type="password" id="pwd">        
			</div>
			<div class="fitem" required="true" class="easyui-validatebox" >
        	   <label>性别:</label>
        	    <select id="gender" >
        	        <option value="男" name="gender" selected="selected">男</option>
        	        <option value="女" name="gender">女</option>
        	    </select>
				<!-- <input  name="gender" type='radio' value='男'>男&nbsp;&nbsp;
				<input name="gender" type='radio' value='女'>女 -->
			</div>
			<div class="fitem">
        	   <label>年龄:</label>
				<input name="age" class="easyui-validatebox" required="true" id="age">        
			</div>
			<div class="fitem">
        	   <label>身份:</label>
				<select name='authority' id="authority" >
     				<option value="普通用户" selected="selected" >普通用户</option>
     				<option value="系统管理员">系统管理员</option>
    			</select>  
			</div>
        </form>
    </div>
    <div id="dlg-buttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok" >Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('.panel-tool-close').click()">Cancel</a>
	</div>
	
  </body>
</html>
