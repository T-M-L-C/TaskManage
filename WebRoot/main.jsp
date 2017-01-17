<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags"  prefix="k" %>
<%@include file="checkuser.jsp"%>
  <%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务管理系统</title>



    <link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4/themes/icon.css">
	<k:import_js/>
    
   
<style>
  article, aside, figure, footer, header, hgroup, 
  menu, nav, section { display: block; }
  .west{
    width:200px;
    padding:10px;
  }
  .north{
    height:100px;
  }
</style>

<script type="text/javascript">
$(function () {
     var username=getCookieValue("username");
     if(username==null || username==''){
       alert('你还未登录，不具有访问本页面的权限');
       window.location.replace="<%=path%>/login.jsp";
     }
     $("#username").html(username);
    //动态菜单数据
    var treeData = [{
            text : "人员维护",
            children : [{
                    text : "人员管理",
                    attributes : {
                        url : "<%=path%>/person.jsp"
                    }
                } 
            ]
        },{
            text : "项目维护",
            children : [{
                    text : "项目管理",
                    attributes : {
                        url : "<%=path%>/project.jsp"
                    }
                },{
                    text : "项目成员",
                    attributes : {
                        url : "<%=path%>/personproject.jsp"
                    }
                },{
                    text : "任务分解",
                    attributes : {
                        url : "<%=path%>/task.jsp "
                    }
                }
            ]
        }
    ];
    
    //实例化树形菜单
    $("#tree").tree({
        data : treeData,
        lines : true,
        onClick : function (node) {
            if (node.attributes) {
                Open(node.text, node.attributes.url);
            }
        }
    });
    //在右边center区域打开菜单，新增tab
    function Open(text, url) {
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
        	var myContent="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
            $('#tabs').tabs('add', {
                title : text,
                closable : true,
                content : myContent
            });
        }
    }
    
    //绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu : function (e, title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data("tabTitle", title);
        }
    });
    
    //实例化menu的onClick事件
    $("#tabsMenu").menu({
        onClick : function (item) {
            CloseTab(this, item.name);
        }
    });
    
    //几个关闭事件的实现
    function CloseTab(menu, type) {
        var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#tabs");
        
        if (type === "close") {
            tabs.tabs("close", curTabTitle);
            return;
        }
        
        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];
        
        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "Other") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "All") {
                closeTabsTitle.push(opt.title);
            }
        });
        
        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
    }
});

  function logout(){
     deleteCookie("username", "/");
     deleteCookie("password", "/");
     deleteCookie("authority", "/");
  
     window.location.href="<%=path%>/login.jsp";
  }
</script>

</head>
<body class="easyui-layout"> 
 
  <!-- 顶部显示的内容 -->
  <div region="north" class="north" title="任务管理系统">
    <div style="position: absolute;right:50px;font-size: 14px;font-family: "微软雅黑";">
       <span id="username" style="color:#CC3F44"></span>
       <span>,&nbsp;欢迎你</span>
       &nbsp;&nbsp;&nbsp;&nbsp;
       <span><a href="javascript:void(0)" style="text-decoration: none;" onclick="logout()">退出登录</a></span>
   </div>
    <h1>任务管理系统</h1>
  </div>
  <!-- 中部区域显示界面的内容 -->
  <div region="center" title="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs" width="100%">
      <div title="首页"></div>
    </div>
  </div>
  <!-- 导航菜单的显示 -->
  <div region="west" class="west" title="menu">
    <ul id="tree"></ul>
  </div>

</body>

</html>
