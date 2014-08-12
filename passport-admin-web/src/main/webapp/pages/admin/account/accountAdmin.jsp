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
                    <span class="step">&gt;</span><strong>�û��˺Ź���</strong>
                </div>
                <h2 id="pageTitle">�û��˺Ź���</h2>

                <form class="navbar-form navbar-left" role="search" action="/admin/alterAccount/queryAccount"
                      method="post">
                    ������
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="ͨ��֤�ʺ�" name="userName" id="userName"/>
                    </div>
                    ��
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="�û��ǳ�" name="nickName" id="nickName"/>
                    </div>
                    <button type="submit" class="btn btn-default" onclick="checkParam()">Submit</button>
                    <c:if test="${exist==false}">
                        <div style="color:#ff0000">�˺Ų�����</div>
                    </c:if>
                </form>

                <hr>
                <form id="accountForm" action="" method="post">

                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading">�û��˺���Ϣ</div>

                        <c:if test="${account!=null && account!=''}">

                        <!-- Table -->
                        <table class="table">
                            <tbody>
                            <input type="hidden" id="passportId" name="passportId"
                                   value="<c:out value="${account.passportId}"/>"/>
                            <input type="hidden" id="newState" name="newState"
                                   value="<c:out value="${account.flag}"/>"/>
                            <tr>
                                <td style="color: #005AA0">�û���</td>
                                <td style="color: #005AA0">�ǳ�</td>
                                <td style="color: #005AA0">���ֻ�</td>
                                <td style="color: #005AA0">������</td>
                                <td style="color: #005AA0">ע��ʱ��</td>
                                <td style="color: #005AA0">ע��IP</td>
                                <td style="color: #005AA0">�˺�����</td>
                            </tr>
                            <tr>
                                <td><c:out value="${account.passportId}"/></td>
                                <td>
                                    <c:if test="${account.uniqname!=null && account.uniqname!=''}">
                                        <c:out value="${account.uniqname}"/>
                                    </c:if>
                                        <%--<c:if test="${account.uniqname==null}">
                                            δ����
                                        </c:if>--%>
                                </td>
                                <td>
                                    <c:if test="${account.mobile!=null && account.mobile!=''}">
                                        <c:out value="${account.mobile}"/>
                                    </c:if>
                                        <%--<c:if test="${account.mobile==null}">
                                            δ����
                                        </c:if>--%>
                                </td>
                                <td>
                                    <c:if test="${account.email!=null && account.email!=''}">
                                        <c:out value="${account.email}"/>
                                    </c:if>
                                        <%--<c:if test="${account.email==null}">
                                            δ����
                                        </c:if>--%>
                                </td>
                                <td><c:out value="${account.regTime}"/></td>
                                <td><c:out value="${account.regIp}"/></td>
                                <td><c:out value="${account.accountTypeName}"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    </c:if>
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
    //    function forbid() {
    //        document.getElementById('newState').value = 3;
    //        document.getElementById('accountForm').action = '/admin/alterAccount/updateState';
    //        document.getElementById('accountForm').submit();
    //    }
    //    function unForbid() {
    //        document.getElementById('newState').value = 1;
    //        document.getElementById('accountForm').action = '/admin/alterAccount/updateState';
    //        document.getElementById('accountForm').submit();
    //    }
    //    function resetPassword() {
    //        document.getElementById('oldPasswd').style.display = "none";
    //        document.getElementById('newPasswd').style.display = "block";
    //        document.getElementById('oldPasswdBtn').style.display = "none";
    //        document.getElementById('newPasswdBtn').style.display = "block";
    //    }
    //    function savePassword() {
    //        var newpasswdstr = document.getElementById('newPasswd').value;
    //        if (newpasswdstr == "") {
    //            alert("�����������룡");
    //            return;
    //        }
    //            newpasswdstr = $.md5(newpasswdstr);
    //        document.getElementById('newPasswd').value = newpasswdstr;
    //        document.getElementById('accountForm').action = '/admin/alterAccount/resetPassword';
    //        document.getElementById('accountForm').submit();
    //    }

    //    function resetUserPassword() {
    //        document.getElementById('oldPasswd').style.display = "none";
    //        document.getElementById('newPasswd').style.display = "block";
    //        var passport_id = $("#passportId").val();
    //        $.post('/admin/resetPassword', {passportId: passport_id}, function (res) {
    //            if (res.status == 0) {
    //                var data = res.data;
    //                $('#newPasswd').val(data.newPassword);
    //            }
    //        }, 'json');
    //    }

    //����У��
    function checkParam() {
        //����У��
        var userName = $("#userName").val();
        var nickName = $("#nickName").val();
        if (userName == '' && nickName == '') {
            alert("������ͨ��֤�ʺŻ����û��ǳƽ��в�ѯ��");
            return false;
        }
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