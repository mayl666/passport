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
                    <span class="step">&gt;</span><strong>��������</strong>
                </div>
                <h2 id="pageTitle">��������</h2>
                <hr>
                <form class="form-horizontal" action="/admin/resetPassword" method="post">
                    <div class="form-group">
                        <label for="passportId" class="col-sm-2 control-label">�˺�</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="passportId" name="passportId" value="<c:out value="${passportId}"/>"
                                   placeholder="������ͨ��֤�ʺ�">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPwd" class="col-sm-2 control-label">������</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="newPwd" name="newPwd" value="<c:out value="${newPwd}"/>"
                                   placeholder="��ܰ��ʾ:�����������,�������Զ�����">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" onclick="submitResetForm()">��������</button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- pageCanvasInt End -->
        </div>
        <!-- pageCanvas End -->
    </div>
    <!-- pageBd End -->
</div>
<!-- page End -->

<script type="text/javascript">
    //�ύ
    function submitResetForm() {
        var passportId = $("#passportId").val();
        if (passportId == null || passportId == "") {
            alert("�û��˺Ų���Ϊ��");
            return false;
        }
        document.forms["resetForm"].submit();
    }

</script>
</body>
</html>