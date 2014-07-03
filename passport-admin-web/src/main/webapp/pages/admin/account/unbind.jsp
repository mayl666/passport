<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport��̨</title>
    <%@ include file="/pages/admin/head.jsp" %>
    <%--<script src="/js/jquery.md5.js" type="text/javascript"></script>--%>

    <!-- ���� Bootstrap ���� CSS �ļ� -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

    <!-- ��ѡ��Bootstrap�����ļ���һ�㲻�����룩 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">

    <!-- jQuery�ļ��������bootstrap.min.js ֮ǰ���� -->
    <%--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>--%>

    <!-- ���µ� Bootstrap ���� JavaScript �ļ� -->
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
                    <span class="step">&gt;</span><strong>�û�����</strong>
                </div>
                <h2 id="pageTitle">�����</h2>

                <form class="navbar-form navbar-left" role="search" action="/admin/unBind" method="post">
                    <div class="form-group">
                        <input type="text" id="passportId" name="passportId" value="${account.passportId}"
                               class="form-control"
                               placeholder="�û��˺�">
                    </div>
                    <button type="submit" class="btn btn-default">��ѯ</button>
                </form>


                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">����Ϣ</div>
                    <form class="navbar-form navbar-left" id="unBindForm" name="unBindForm" action="" method="post">
                        <c:if test="${account!=null && account!=''}">
                            <table class="table">
                                <tbody>
                                <input type="hidden" id="ppId" name="ppId"
                                       value="<c:out value="${account.passportId}"/>"/>
                                <input type="hidden" id="phone" name="phone"
                                       value="<c:out value="${account.mobile}"/>"/>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-envelope">�ܱ�����</span>
                                        <c:choose>
                                            <c:when test="${account.email!=null && account.email!=''}">
                                                <c:out value="${account.email}"></c:out>
                                                <span class="label label-success">�Ѿ���</span>

                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default"
                                                            onclick="unbindEmail()">���
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="label label-danger">δ����</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-phone">�ܱ��ֻ�</span>
                                        <c:choose>
                                            <c:when test="${account.mobile!=null && account.mobile!=''}">
                                                <c:out value="${account.mobile}"></c:out>
                                                <span class="label label-success">�Ѿ���</span>

                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default"
                                                            onclick="unbindMobile()">���
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="label label-danger">δ����</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </c:if>
                    </form>
                </div>
            </div>
            <!-- pageCanvasInt End -->
        </div>
        <!-- pageCanvas End -->
    </div>
    <!-- pageBd End -->
</div>
<!-- page End -->
<script type="text/javascript">

    //�������
    function unbindEmail() {
        var passportId = $("#ppId").val();
        $.post('/admin/unBindEmail', {passportId: passportId}, function (data) {
             alert(data.statusText);
        }, 'json');
    }

    //����ֻ�
    function unbindMobile() {
        var passportId = $("#ppId").val();
        var mobile = $("#phone").val();
        $.post('/admin/unBindPhone', {passportId: passportId, mobile: mobile}, function (data) {
            alert(data.statusText);
        }, 'json');
    }
</script>

</body>
</html>