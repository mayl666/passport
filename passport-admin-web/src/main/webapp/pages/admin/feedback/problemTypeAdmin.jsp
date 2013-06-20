<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport后台</title>
    <%@ include file="/pages/admin/head.jsp"%>


<style>
div#header { display:none; background-color: #f5f5dc; }
div#logo { width:130px; height:37px; background: url(/img/admin/logo.png); }
#menu_name { display: none; } /* 去掉菜单标题栏 */
iframe#main { margin:0; padding:0; }
</style>
</head>
<body>
<%@ include file="problem_answer.jsp" %>
	<div id="page">
		<!-- 引入顶部信息 -->
		<%@ include file="/pages/admin/include_top.jsp"%>
		<div id="pageBd">
			<!-- 引入菜单 -->
			<%@ include file="/pages/admin/include_menu.jsp"%>
			<div id="pageCanvas" class="canvas">
				<div id="pageCanvasInt" class="canvasInt">
					<div id="pageCrumbs" class="crumbs">
						当前位置：<strong>后台管理</strong>
                        <span class="step">&gt;</span><strong>用户反馈管理</strong>
                    </div>
					<h2 id="pageTitle">用户反馈管理</h2>


                    <hr>
                    <h3 >增加反馈类型</h3>
                    <form id="addProblemTypeForm" action="/admin/adminProblem/addProblemType" method="post">
                        <table>
                            <tr>
                                <td>类型名称</td>
                                <td>
                                    <div class="input-append">
                                    <input id="typeName" name="typeName"  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>

                            <tr align="center">
                                <td><span class="button button-main"> <a href="javascript:onAddProblemTypeSubmit();" class="btn_save">添加</a></span>
                            </td></tr>
                        <table>
                    </form>


                    <hr>

                    <h3 >删除反馈类型</h3>
                    <form id="deleteProblemTypeForm" action="/admin/adminProblem/deleteProblemType" method="post">
                        <table>
                            <tr>
                                <td>类型名称</td>
                                <td>
                                    <div class="input-append">
                                        <input id="deltypeName" name="deltypeName"  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>

                            <tr align="center">
                                <td> <span class="button button-main "> <a href="javascript:onDelProblemTypeSubmit();" class="btn_save">删除</a>  </span>

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
                alert("请输入类型名称！");
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
                alert("请输入类型名称！");
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