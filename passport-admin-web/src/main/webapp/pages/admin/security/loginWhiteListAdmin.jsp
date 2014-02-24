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
                        <span class="step">&gt;</span><strong>安全性管理</strong>
                    </div>
					<h2 id="pageTitle">白名单管理</h2>


                    <hr>


                    <form action="/admin/security/getWhiteItemByName" method="post">
                        请输入ip或者username：
                        <input type="text" name="ipOrUsername"/>
                                <span class="button button-main">
                                  <input type="submit" value="查询" class="button"/>
                                </span>
                        <c:if test="${curIpExist==false}">
                            <div style="color:#ff0000">此IP在黑名中不存在</div>
                        </c:if>
                    </form>
                    <hr>
                    <form action="/admin/security/loginWhiteList" method="post">
                        <span class="button button-main">
                             <input type="submit" value="显示所有白名单列表" class="button"/>
                        </span>
                    </form>
                    <hr>

                    请输入ip或者username：
                    <input type="text" id="ipOrUsername" name="ipOrUsername"/>
                                <span class="button button-main">
                                  <input type="submit" value="增加" class="button" onclick="onAdd()"/>
                                </span>
                    <hr>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="7%"><div>ip或者username </div></th>
                            <th width="8%"><div>从白名单中剔除</div></th>
                        </tr>
                        </thead>
                        <c:if test ="${loginWhiteList!=null}">
                            <c:forEach items="${loginWhiteList}" var="loginWhiteValue" varStatus="status">
                                <tr>
                                    <td style="text-align:center;">
                                            ${loginWhiteValue}
                                    </td>
                                    <td style="text-align:center;">
                                        <input type="button" value="删除" onclick="onDel('${loginWhiteValue}')" />
                                    </td>
                                </tr>
                            </c:forEach>

                        </c:if>
                    </table>

				</div><!-- pageCanvasInt End -->

                <div>
                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">

        function onDel(loginWhiteValue){
            var url =  "/admin/security/delLoginWhiteItem";
            var data = "ipOrUsername="+loginWhiteValue;
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

        function onAdd(){
            var ipOrUsername =  document.getElementById('ipOrUsername').value;

            var url =  "/admin/security/addWhiteItem";
            var data = "ipOrUsername="+ipOrUsername;
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