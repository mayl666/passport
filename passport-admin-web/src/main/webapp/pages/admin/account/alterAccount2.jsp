<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>�˺Ź���</title>
    <style type="text/css">
        form {
            font: 14px "����", "Microsoft YaHei", Verdana, Arial, Helvetica, sans-serif;
        }
        input {
            font: bold 11px "Microsoft YaHei", Verdana, Arial, Helvetica, sans-serif;
        }
        th {
            font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            color: #4f6b72;
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            border-top: 1px solid #C1DAD7;
            letter-spacing: 2px;
            text-transform: uppercase;
            text-align: left;
            padding: 6px 6px 6px 12px;
            background: #CAE8EA no-repeat;
        }

        th.nobg {
            border-top: 0;
            border-left: 0;
            border-right: 1px solid #C1DAD7;
            background: none;
        }

        td {
            border-right: 1px solid #C1DAD7;
            border-bottom: 1px solid #C1DAD7;
            background: #fff;
            font: 12px "΢���ź�", "Microsoft YaHei", "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            padding: 6px 6px 6px 12px;
            color: #4f6b72;
        }


        td.alt {
            background: #F5FAFA;
            color: #797268;
        }

        th.spec {
            border-left: 1px solid #C1DAD7;
            border-top: 0;
            background: #fff no-repeat;
            font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        }

        th.specalt {
            border-left: 1px solid #C1DAD7;
            border-top: 0;
            background: #f5fafa no-repeat;
            font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
            color: #797268;
        }
    </style>
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
</head>
<body>
<div id="step1" class="class1">
    <form action="/admin/alterAccount/queryAccount" method="post">
        <p>�������û�������(example@sogou.com)���ֻ��ţ�</p>
        <table>
            <tbody>
            <tr>
                <input type="text" name="username"/>
                <input type="submit" value="�ύ"/>
                <c:if test="${exist==false}">
                    <div id="hasuser">�˺Ų�����</div>
                </c:if>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="step2" class="class2">
    <form id="accountForm" action="" method="post">
        <c:if test ="${account != null}">
            <table>
                <tbody>
                <input type="hidden" id="passportId" name="passportId" value="$account.passportId"/>
                <input type="hidden" id="newState" name="newState" value="$account.state"/>
                <tr><td>ID</td><td>�û���</td><td>����</td><td>���ֻ�</td><td>ע��ʱ��</td><td>ע��IP</td>
                    <td>�汾</td><td>�˺�����</td><td>��/���</td><td>��������</td></tr>
                <tr>
                    <td>$account.id</td><td>$account.passportId</td>
                    <td><input type="text" id="oldPasswd" value="$account.passwd" disabled="false"/>
                        <input type="text" id="newPasswd" name="newPasswd" style="display: none" />
                    </td>
                    <td>$account.mobile</td><td>$account.regTime</td><td>$account.regIp</td><td>$account.version</td>
                    <td>$account.accountType</td>
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
</div>
</body>