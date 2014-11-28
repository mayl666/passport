<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport后台管理系统</title>
    <%@ include file="/pages/admin/head.jsp"%>
<style>
div#header { display:none; background-color: #f5f5dc; }
div#logo { width:130px; height:37px; background: url(/img/admin/logo.png); }
#menu_name { display: none; } /* 去掉菜单标题栏 */
iframe#main { margin:0; padding:0; }
</style>
</head>
<body>
	<div id="page">
		<!-- 引入顶部信息 -->
		<%@ include file="/pages/admin/include_top.jsp"%>
		<div id="pageBd">
			<!-- 引入菜单 -->
			<%@ include file="/pages/admin/include_menu.jsp"%>
			<div id="pageCanvas" class="canvas">
				<div id="pageCanvasInt" class="canvasInt">
					<div id="pageCrumbs" class="crumbs">
						当前位置：<strong>管理后台主页</strong>
					</div>
					<h2 id="pageTitle">主页</h2>
					<div id="pageContent">
						<%@ include file="/pages/admin/intro.jsp"%>
					</div>



				</div><!-- pageCanvasInt End -->
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->
</body>
</html>