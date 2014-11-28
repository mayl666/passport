<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-12
  Time: 下午12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport后台</title>
    <%@ include file="/pages/admin/head.jsp" %>

    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.7.3.custom.css"/>
    <script type="text/javascript" language="javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery-ui-1.7.3.custom.min.js"></script>
    <%--<link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">--%>
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
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
                <a href="/admin/interface/queryinterfacelist">返回</a>

                <form id="addInterfaceForm" method="post" action="/admin/interface/saveinterface">
                    接口名称：
                    <c:choose>
                        <c:when test="${interfaceVO!=null}">
                            <div>
                                <input type="text" name="interfaceName"
                                       value="<c:out value="${interfaceVO.interfaceName}" />"/>
                                初级：<input type="text" name="primaryLevelCount"
                                          value="<c:out value="${interfaceVO.primaryLevelCount}" />"/>
                                中级:<input type="text" name="middleLevelCount"
                                          value="<c:out value="${interfaceVO.middleLevelCount}" />"/>
                                高级:<input type="text" name="highLevelCount"
                                          value="<c:out value="${interfaceVO.highLevelCount}" />"/>
                                <input type="hidden" name="interId" value="<c:out value="${interfaceVO.id}"/>"/>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div>
                                <input type="text" name="interfaceName"/>
                                初级:<input type="text" name="primaryLevelCount"/>
                                中级：<input type="text" name="middleLevelCount"/>
                                高级:<input type="text" name="highLevelCount"/>
                            </div>
                            <input type="hidden" value="" name="interId"/>
                        </c:otherwise>
                    </c:choose>
                    <p>
                        <label>
                            <span class="button button-main"><button type="submit">
                                保存
                            </button></span>
                        </label>
                        <label>
                            <span class="button button-main"> <button type="button" onclick="onCancelInterfaceSubmit()">
                                取消
                            </button></span>
                        </label>
                    </p>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
//    function onSaveInterfaceSubmit() {
//        var form = document.getElementById('addInterfaceForm');
//        var _interfaceName = form.interfaceName.value;
//        var _primaryLevelCount = form.primaryLevelCount.value;
//        var _middleLevelCount = form.middleLevelCount.value;
//        var _highLevelCount = form.highLevelCount.value;
////        alert("interfaceName is" + _interfaceName);
//        if (_interfaceName == "") {
//            alert("请输入接口名称！");
//            form.interfaceName.focus();
//            return 0;
//        }
//        if (_primaryLevelCount == "") {
//            alert("请设置接口初级频次！");
//            form.primaryLevelCount.focus();
//            return 0;
//        }
//        if (_middleLevelCount == "") {
//            alert("请设置接口中级频次！");
//            form.middleLevelCount.focus();
//            return 0;
//        }
//        if (_highLevelCount == "") {
//            alert("请设置接口高级频次！");
//            form.highLevelCount.focus();
//            return 0;
//        }

//        var url = "/admin/interface/saveinterface";
//       // var data = "interfaceName=" + _interfaceName + "&id=" + _id;
////        {"interfaceName":_interfaceName,"id":_id}
////        alert(data);
//        $.ajax({
//            type: 'POST',
//            url: url,
//            data: {
//                interfaceName:_interfaceName,
//                primaryLevelCount:_primaryLevelCount,
//                middleLevelCount:_middleLevelCount,
//                highLevelCount:_highLevelCount,
//                id:_id
//            },
//            context: document.body,
//            success: function (data) {
//                alert(data.statusText);
//            },
//            dataType: "json"
//        });
//    }

    function onCancelInterfaceSubmit() {
        location.reload();
    }
</script>
</body>
</html>