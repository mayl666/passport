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
    <%--<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>--%>
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
                <h2 id="pageTitle">����ɾ��ע���ֻ���</h2>

                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">����ɾ��</div>
                    <form class="navbar-form navbar-left" id="deleteRegMobileForm" name="deleteRegMobileForm" action="/admin/deleteRegMobiles" method="post">
                        <table class="table">
                            <div class="form-group">
                                <div class="input-group">
                                    <textarea class="form-control" id="mobiles" name="mobiles" rows="10" cols="40" placeholder="������Ҫɾ���ֻ���"></textarea>
                                </div>
                            </div>
                            <div class="input-group">
                                <p>
                                    <button type="submit" class="btn btn-default">���</button>
                                </p>
                            </div>
                            <c:if test="${failed!=null && failed!=''}">
                            <div class="list-group">
                                <div class="input-group">
                                    <span class="label label-danger">ɾ��ע���ֻ���ʧ�ܽ��</span>
                                </div>
                            </div>

                            <!-- ���ʧ�ܽ�� -->
                            <div class="list-group">
                                <div class="input-group">
                                    <textarea class="form-control" id="failed" name="failed" rows="10" cols="40"><c:out value="${failed}"/></textarea>
                                </div>
                            </div>
                            </c:if>
                        </table>
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
    //��������ֻ�
    function deleteRegMobiles() {
        var mobiles = $("#mobiles").val();
        $.post('/admin/deleteRegMobiles', {mobiles: mobiles}, function (data) {
            alert(data.statusText);
        }, 'json');
    }

    function showMsg() {
        <c:if test ="${msg!=null}">
            alert("${msg}");
        </c:if>
    }
    showMsg();
</script>
</body>
</html>