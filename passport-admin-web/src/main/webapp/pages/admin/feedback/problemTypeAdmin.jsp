<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport��̨</title>
    <%@ include file="/pages/admin/head.jsp"%>


<style>
div#header { display:none; background-color: #f5f5dc; }
div#logo { width:130px; height:37px; background: url(/img/admin/logo.png); }
#menu_name { display: none; } /* ȥ���˵������� */
iframe#main { margin:0; padding:0; }
</style>
</head>
<body>
<%@ include file="problem_answer.jsp" %>
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
                        <span class="step">&gt;</span><strong>�û���������</strong>
                    </div>
					<h2 id="pageTitle">�û���������</h2>


                    <hr>
                    <h3 >���ӷ�������</h3>
                    <form id="addProblemTypeForm" action="/admin/adminProblem/addProblemType" method="post">
                        <table>
                            <tr>
                                <td>��������</td>
                                <td>
                                    <div class="input-append">
                                    <input id="typeName" name="typeName"  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>

                            <tr align="center">
                                <td><span class="button button-main"> <a href="javascript:onAddProblemTypeSubmit();" class="btn_save">���</a></span>
                            </td></tr>
                        <table>
                    </form>


                    <hr>

                    <h3 >ɾ����������</h3>
                    <form id="deleteProblemTypeForm" action="/admin/adminProblem/deleteProblemType" method="post">
                        <table>
                            <tr>
                                <td>��������</td>
                                <td>
                                    <div class="input-append">
                                        <input id="deltypeName" name="deltypeName"  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>

                            <tr align="center">
                                <td> <span class="button button-main "> <a href="javascript:onDelProblemTypeSubmit();" class="btn_save">ɾ��</a>  </span>

                                </td></tr>
                            <table>
                    </form>
				</div><!-- pageCanvasInt End -->

                <div>
                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">

        function onAddProblemTypeSubmit() {
            var form =  document.getElementById('addProblemTypeForm');
            var _typeName = form.typeName.value;
            if ( _typeName== "") {
                alert("�������������ƣ�");
                form.typeName.focus();
                return false;
            }

            var url =  "/admin/adminProblem/addProblemType";
            var data = "typeName="+_typeName;
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                success:  function(data){
                    alert(data.statusText);
                },
                dataType: "json"
            });

        }

        function onDelProblemTypeSubmit() {
            var form =  document.getElementById('deleteProblemTypeForm');
            var _typeName = form.deltypeName.value;
            if ( _typeName== "") {
                alert("�������������ƣ�");
                form.deltypeName.focus();
                return false;
            }

            var url =  "/admin/adminProblem/deleteProblemType";
            var data = "typeName="+_typeName;
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                success:  function(data){
                    alert(data.statusText);
                },
                dataType: "json"
            });

        }
    </script>
</body>
</html>