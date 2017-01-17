
<%
   response.setContentType("text/xml");
   response.setHeader("Cache-Control", "no-store");
   response.setHeader("Pragma","no-cache");
   response.setDateHeader("Expires", 0);
   response.getWriter().write("<msg>valid</msg>");
 %>
