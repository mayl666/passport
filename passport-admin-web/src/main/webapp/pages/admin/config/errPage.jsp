<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-12
  Time: ����12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport��̨</title>
    <%@ include file="/pages/admin/head.jsp" %>

    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.7.3.custom.css"/>
    <script type="text/javascript" language="javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery-ui-1.7.3.custom.min.js"></script>
    <link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">
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

            /* ȥ���˵������� */
        iframe#main {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="page">
    <!-- ���붥����Ϣ -->
    <%@ include file="/pages/admin/include_top.jsp" %>
    <div id="pageBd">
        <!-- ����˵� -->
        <%@ include file="/pages/admin/include_menu.jsp" %>
        <div id="pageCanvas" class="canvas">
            <div id="pageCanvasInt" class="canvasInt">
                <div id="pageCrumbs" class="crumbs">
                    ��ǰλ�ã�<strong>��̨����</strong>
                    <span class="step">&gt;</span><strong>�ӿ�Ƶ�����ù���</strong>
                </div>
                <h2 id="pageTitle">�ӿ�Ƶ�����ù���</h2>

                <hr>
            </div>
            <!-- pageCanvasInt End -->

            <div>
                <a href="/admin/interface/queryinterfacelist">����</a>

                <div>
                    <label>
                        <%
                            String str = (String) request.getAttribute("errMessage");
                            out.write(str);
                        %>
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

</script>
</body>
</html>