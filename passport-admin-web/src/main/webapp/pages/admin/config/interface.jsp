<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-8
  Time: ����7:13
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
                <a href="/pages/admin/config/addInterface.jsp">����</a>
                <a href="/admin/interface/getinterfaceandlevellist">���һ���ҳ��</a>
                <table style="font-size:13px" class="question-table">
                    <thead>
                    <tr style="background-color:#ADDA27;">
                        <th width="3%">
                            <div>���</div>
                        </th>
                        <th width="12%">
                            <div>�ӿ�����</div>
                        </th>
                        <th width="18%">
                            <div>��������</div>
                        </th>

                    </tr>
                    </thead>
                    <c:if test="${interfaceVOList!=null}">
                        <c:forEach items="${interfaceVOList}" var="interfaceVO">
                            <tr>
                                <td style="text-align:center;"><c:out value="${interfaceVO.id}"/></td>
                                <td style="text-align:center;">
                                    <c:out value="${interfaceVO.interfaceName}"/>
                                </td>
                                <td style="text-align:center;"><a
                                        href="/admin/interface/getinterface?id=<c:out value="${interfaceVO.id}"/>">�޸�</a>
                                    <a href="/admin/interface/delInterface?id=<c:out value="${interfaceVO.id}"/>">ɾ��</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
</body>
</html>