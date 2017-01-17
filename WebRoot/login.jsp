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
<k:import_js />
<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
#bei {
	background-image: url("Image/test3.jpg");
	height: 100%;
	width: 100%;
	background-size: 100%;
	background-repeat: no-repeat;
	padding-top: 200px;
}
</style>
<script type="text/javascript">

     var req;
       function validate(){
         var idField=document.getElementById("loginName");
         var url="validate.jsp?id="+escape(idField.value);
         if(window.XMLHttpRequest){
            req=new XMLHttpRequest();
         }
         else 
           if(window.ActiveXobject)
          	{ 
              req=new ActiveXobject("Microsoft.XMLHTTP");
            }
            req.open("get",url,true);
            req.onreadystatechange=callback;
            req.send(null);
            
       }
       function callback(){
          if(req.readyState==4){
             if(req.status==200){
               var msg=req.responseXML.getElementsByTagName("msg")[0];
               alert(msg.childNodes[0].nodeValue);
             }
          }
       }

         function subup(){      
            var loginName=document.getElementById("loginName").value;
            var pwd=document.getElementById("pwd").value;
            var authority=document.getElementById("authority").value;
            var flag= validForm(loginName,pwd,authority);
            if(flag){
		         $.ajax({
		              url:"<%=path%>/user/login_validateUser!validateUser.action",
		              type:"post",
		              data:{
		                loginName:loginName,
		                pwd:pwd,
		                authority:authority
		              },
		              success:function(data,textStatus){
		                  if(textStatus=='success')
		                	{
		                	   if(data=='登录成功'){
		                	  /*    if(authority=='普通用户'){
		                	       alert("该用户为普通用户，不具有访问系统的权限");
		                	     }else{ */
		                	         setCookie("username", loginName, "", "/");
		                	   		 setCookie("password",pwd,"","/");
		                	    	 setCookie("authority", authority,"", "/");
		                	   		 window.location.href="main.jsp";
		                	     
		                	   } else{
		                	      alert('用户名，密码和身份不匹配');
		                	      window.location.reload();
		                	   } 
		                	}
		              },
		              error:function(){
		                // window.location.reload();
		              }
		            });
	            }
         
   }

      
	         
         

      
      var validForm=function(loginname,pwd,authority){
          if(loginname=='' || loginname==null){
            alert('用户名不能为空');
            return false;
          }
          if(pwd=="" || pwd==null){
           alert('密码不能为空');
           return false;
          }
          
          if(authority=='' || authority==null){
           alert('身份不能为空');
           return false;
          }
          
          return true;
      };
      var keyup=function(){
        if(window.event.keyCode==13)
		{
		   subup();
		}
      };
      $("#sub").click(function(){
        subup(); 
      });
  </script>
</head>

<body>
	<div id="bei" align="center">
		<div style="border-left:900px">
			<h1 style="color:white">登录任务管理系统</h1>
			<form name="myform" method="post" action="">
				<table>
					<tr>
						<td><span style="color:white">账号:</span>
						</td>
						<td>
							<p>
								<input type='text' name='loginName' id="loginName" />
							</p></td>
					</tr>
					<tr>
						<td><span style="color:white">密码:</span></td>
						<td><input type='password' name='pwd' id="pwd"  onkeydown="keyup()"/><br />
						</td>
					</tr>
					<tr>
						<td>
							<p>
								<span style="color:white">身份:</span>
							</p></td>
						<td><select name='authority' id="authority">
								<option value="普通用户">普通用户</option>
								<option value="系统管理员">系统管理员</option>
						</select>&nbsp;&nbsp; <input type="button" name='submit' value='OK' id="sub" onkeydown="keyup()"  onclick="subup()"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
