<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-12
  Time: ����12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport��̨</title>
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
    <%--<%@ include file="/pages/admin/include_top.jsp" %>--%>
    <div id="pageBd">
        <!-- ����˵� -->
        <%--<%@ include file="/pages/admin/include_menu.jsp" %>--%>
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

                <form id="addInterfaceForm" method="post" action="/admin/interface/saveinterface">
                    �ӿ����ƣ�
                    <c:choose>
                        <c:when test="${interfaceVO!=null}">
                            <label>
                                <input type="text" name="interfaceName"
                                       value="<c:out value="${interfaceVO.interfaceName}" /> "/>
                                <input type="hidden" name="interId"  value="<c:out value="${interfaceVO.id}"/>"/>
                            </label>
                        </c:when>

                        <c:otherwise>
                            <label>
                                <input type="text" name="interfaceName"/>
                            </label>
                            <input type="hidden" value="" name="interId"/>
                        </c:otherwise>
                    </c:choose>
                    <p>
                        <label>
                            <span class="button button-main"><button type="submit">����</button></span>
                        </label>
                        <label>
                            <span class="button button-main"> <a href="javascript:onCancelInterfaceSubmit();"
                                                                 class="btn_save">ȡ��</a></span>
                        </label>
                    </p>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function onSaveInterfaceSubmit() {
        var form = document.getElementById('addInterfaceForm');
        var _interfaceName = form.interfaceName.value;
        var _id = "";
        alert("interfaceName is" + _interfaceName);
        if (_interfaceName == "") {
            alert("������ӿ����ƣ�");
            form.interfaceName.focus();
            return false;
        }

        var url = "/admin/interface/saveinterface";
       // var data = "interfaceName=" + _interfaceName + "&id=" + _id;
//        {"interfaceName":_interfaceName,"id":_id}
//        alert(data);
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                interfaceName:_interfaceName,
                id:_id
            },
            context: document.body,
            success: function (data) {
                alert(data.statusText);
            },
            dataType: "json"
        });
    }
</script>
</body>
</html>