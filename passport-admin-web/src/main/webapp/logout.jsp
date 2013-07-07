<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%
//清除本地cookie
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Cache-Control","no-store");
response.setDateHeader("Expires", 0); 
response.setHeader("Pragma","no-cache"); 

Cookie cookie = new Cookie("jpassport-sp", null);
cookie.setMaxAge(0);
cookie.setPath(request.getContextPath());

response.addCookie(cookie);

//通知passport同步其它应用退出状态
//String webUrl = "http://passport-app.upd.sogou-inc.com";
//response.sendRedirect("https://passport.sogou-inc.com/logout.jsp?url="+webUrl);
%>
