<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport��̨</title>
    <%@ include file="/pages/admin/head.jsp"%>
    <script src="/js/jquery.md5.js" type="text/javascript"></script>
<style>
div#header { display:none; background-color: #f5f5dc; }
div#logo { width:130px; height:37px; background: url(/img/admin/logo.png); }
#menu_name { display: none; } /* ȥ���˵������� */
iframe#main { margin:0; padding:0; }
</style>
</head>
<body>
	<div id="page">
		<!-- ���붥����Ϣ -->
		<%@ include file="/pages/admin/include_top.jsp"%>
		<div id="pageBd">
			<!-- ����˵� -->
			<%@ include file="/pages/admin/include_menu.jsp"%>
			<div id="pageCanvas" class="canvas">
				<div id="pageCanvasInt" class="canvasInt">
					<div id="pageCrumbs" class="crumbs">
						��ǰλ�ã�<strong>��̨����</strong>
                        <span class="step">&gt;</span><strong>�û��˺Ź���</strong>
                    </div>
					<h2 id="pageTitle">�û���������</h2>


                    <hr>
                    <form id="accountForm" action="" method="post">
                        <c:if test ="${account != null}">
                            <table>
                                <tbody>
                                <input type="hidden" id="passportId" name="passportId" value="${account.passportId}"/>
                                <input type="hidden" id="newState" name="newState" value="${account.status}"/>
                                <tr><td style="color: #005AA0">ID</td><td style="color: #005AA0">�û���</td><td style="color: #005AA0">����</td><td style="color: #005AA0">���ֻ�</td><td style="color: #005AA0">ע��ʱ��</td><td style="color: #005AA0">ע��IP</td>
                                    <td style="color: #005AA0">�汾</td><td style="color: #005AA0">�˺�����</td><td style="color: #005AA0">��/���</td><td style="color: #005AA0">��������</td></tr>
                                <tr>
                                    <td>${account.id}</td><td>${account.passportId}</td>
                                    <td><input type="text" id="oldPasswd" value="${account.passwd}" disabled="false"/>
                                        <input type="text" id="newPasswd" name="newPasswd" style="display: none" />
                                    </td>
                                    <td>${account.mobile}</td><td>${account.regTime}</td><td>${account.regIp}</td><td>${account.version}</td>
                                    <td>${account.accountType}</td>
                                    <td>
                                        <c:if test ="${account.status == 1}">
                                            <input type="button" value="���" onclick="forbid()"/>
                                        </c:if>
                                        <c:if test ="${account.status == 3}">
                                            <input type="button"  value="���" onclick="unForbid()"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <input id="oldPasswdBtn" type="button" value="����" onclick="resetPassword()"/>
                                        <input id="newPasswdBtn" type="button" value="����" onclick="savePassword()" style="display: none"/>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </c:if>
                    </form>



				</div><!-- pageCanvasInt End -->
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">
        function forbid() {
            document.getElementById('newState').value = 3;
            document.getElementById('accountForm').action = '/admin/alterAccount/updateState';
            document.getElementById('accountForm').submit();
        }
        function unForbid() {
            document.getElementById('newState').value = 1;
            document.getElementById('accountForm').action = '/admin/alterAccount/updateState';
            document.getElementById('accountForm').submit();
        }
        function resetPassword() {
            document.getElementById('oldPasswd').style.display = "none";
            document.getElementById('newPasswd').style.display = "block";
            document.getElementById('oldPasswdBtn').style.display = "none";
            document.getElementById('newPasswdBtn').style.display = "block";
        }
        function savePassword() {
            var newpasswdstr =  document.getElementById('newPasswd').value;
            if(newpasswdstr == ""){
                alert("�����������룡");
                return;
            }
            newpasswdstr = $.md5(newpasswdstr);
            document.getElementById('newPasswd').value =   newpasswdstr;
            document.getElementById('accountForm').action = '/admin/alterAccount/resetPassword';
            document.getElementById('accountForm').submit();
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