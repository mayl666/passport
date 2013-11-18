<%@page import="java.util.Calendar"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.apache.commons.lang3.time.DateUtils"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.commons.lang3.time.DateFormatUtils"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link rel="stylesheet"  type="text/css"  href="/css/chosen.css" />
<script src="/js/jquery-1.4.4.js" type="text/javascript"></script>
<script src="/js/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
<script src="/js/grid.locale-cn.js" type="text/javascript"></script>
<script src="/js/jquery.jqGrid.src.js" type="text/javascript"></script>
<script src="/js/wan.admin.js" type="text/javascript"></script>
<script src="/js/chosen.jquery.min.js" type="text/javascript"></script>
<link href="/css/jqgrid/flick/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css">
<link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">
<link href="/css/base.css" rel="stylesheet" type="text/css">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<!--link href="${urlStatic}/css/jqgrid/ui.jqgrid.css" type="text/css" rel="stylesheet" -->
<script type="text/javascript" src="/js/FusionCharts/FusionCharts.js"></script>
<style type="text/css">
body, h1, h2, ul, dl, dt, dd, p,form  {margin:0;padding:0;}
body {color:#333;font:12px/20px Tahoma,Verdana,Arial,sans-serif;}
/* * { font-family:"Î¢ÈíÑÅºÚ" !important; } */
img {border: 0;}
.ui-jqgrid { font-size: 12px; } /* Ä¬ÈÏÊÇ11px */
#loading {background: url('/img/admin/ajax-loader-sm.gif') no-repeat scroll 50% 50% transparent; height: 20px; width: 20px; float: left; margin-top: -29px; display: none; }
</style>
