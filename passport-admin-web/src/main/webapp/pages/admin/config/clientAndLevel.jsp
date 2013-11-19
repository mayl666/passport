<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-8
  Time: 下午7:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="script" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport后台</title>
    <%@ include file="/pages/admin/head.jsp" %>

    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.7.3.custom.css"/>
    <script type="text/javascript" language="javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery-ui-1.7.3.custom.min.js"></script>
    <link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <!--link href="${urlStatic}/css/jqgrid/ui.jqgrid.css" type="text/css" rel="stylesheet" -->

    <style>
        div#header {
            display: none;
            background-color: #f5f5dc;
        }

        div#logo {
            width: 130px;
            height: 37px;
            background: url(/img/admin/logo.png);
        }

        #menu_name {
            display: none;
        }

            /* 去掉菜单标题栏 */
        iframe#main {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="page">
    <!-- 引入顶部信息 -->
    <%@ include file="/pages/admin/include_top.jsp" %>
    <div id="pageBd">
        <!-- 引入菜单 -->
        <%@ include file="/pages/admin/include_menu.jsp" %>
        <div id="pageCanvas" class="canvas">
            <div id="pageCanvasInt" class="canvasInt">
                <div id="pageCrumbs" class="crumbs">
                    当前位置：<strong>后台管理</strong>
                    <span class="step">&gt;</span><strong>接口频次配置管理</strong>
                </div>
                <h2 id="pageTitle">接口频次配置管理</h2>

                <hr>
            </div>
            <!-- pageCanvasInt End -->

            <div>
                <a href="/admin/interface/getinterfaceandlevellist">带我回主页面</a>

                <form id="clientLevelForm" method="post" action="/admin/interface/saveclientlevel">
                    <table>
                        <tr>
                            <td>请选择应用</td>
                            <td>
                                <select id="clientId" name="clientId" class="sql_where" size=1
                                        onchange="IFeelGood(this)">
                                    <option value="-1" selected>请选择</option>
                                    <c:forEach items="${clientVOList}" var="client" varStatus="status">
                                    <option value="<c:out value="${client.clientId}"/>"><c:out
                                            value="${client.clientName}"/></option>
                                    </c:forEach>
                            </td>
                            <td>请选择等级</td>
                            <td>
                                <select id="levelId" name="levelId" class="sql_where" size=1>
                                    <option value="-1" selected>请选择</option>
                                    <c:forEach items="${levelVOList}" var="level" varStatus="status">
                                    <option value="<c:out value="${level.levelId}"/>"><c:out
                                            value="${level.levelName}"/></option>
                                    </c:forEach>
                            </td>
                        </tr>
                        <input id="pageNum" name="pageNum" value="1" type="hidden">
                        <tr>
                            <td><span class="button button-main"><button type="button" onclick="saveClientAndLevel()">
                                保存
                            </button></span>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var url="/admin/interface/getclientandlevel";
        $.ajax({
            type: 'GET',
            url: url,
            dataType: "json"
        });
    });
    function IFeelGood(elem) {
        var client_id = elem.value;
        var data = "clientId=" + client_id;
        var url = "/admin/interface/getlevelbyclientid?" + data;
        $.ajax({
            type: 'GET',
            url: url,
            // data: data,
            success: function (data) {
                var levelInfo = data.levelInfo;
                if (levelInfo == 0) {
                    document.getElementById('levelId').value = '0';
                } else if (levelInfo == 1) {
                    document.getElementById('levelId').value = '1';
                } else if (levelInfo == 2) {
                    document.getElementById('levelId').value = '2';
                }
                levelInfo ? $("#levelId").val(levelInfo) : $("#levelId").val(-1);
            },
            error: function (data) {
                alert("error");
            },
            dataType: "json"
        });
    }
    function saveClientAndLevel() {
        var client_id = document.getElementById("clientId").value;
        var level_info = document.getElementById("levelId").value;
        if (client_id == "" || client_id == -1) {
            alert("请选择应用！");
            return false;
        } else if (level_info == "" || level_info == -1) {
            alert("请选择等级！");
            return false;
        }
        var url = "/admin/interface/saveclientlevel";
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                clientId: client_id,
                level: level_info
            },
            success: function (data) {
                alert(data.statusText);
            },
            dataType: "json"
        });
    }
</script>
</body>
</html>
