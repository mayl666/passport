<%@page import="com.sogou.upd.passport.model.black.BlackItem" %>
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
                        <span class="step">&gt;</span><strong>登陆黑白名单管理</strong>
                    </div>
					<h2 id="pageTitle">当前ip黑名单</h2>

                    <form action="/admin/security/getLoginBlackIpByName" method="post">
                        请输入ip：
                        <input type="text" name="ipStr"/>
                                <span class="button button-main">
                                  <input type="submit" value="查询" class="button"/>
                                </span>
                        <c:if test="${curIpExist==false}">
                            <div style="color:#ff0000">此IP在黑名中不存在</div>
                        </c:if>
                    </form>
                    <hr>
                    <form action="/admin/security/currentLoginBlackIplist" method="post">
                        <span class="button button-main">
                             <input type="submit" value="显示所有黑名单IP列表" class="button"/>
                        </span>
                    </form>
                    <hr>
                        请输入ip：
                        <input type="text" id="addIpStr" name="addIpStr"/>
                                <span class="button button-main">
                                  <input type="submit" value="增加" class="button" onclick="onAdd()"/>
                                </span>
                    <hr>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="7%"><div>ID </div></th>
                            <th width="7%"><div>进入时间</div></th>
                            <th width="4%"><div>ip</div></th>
                            <th width="6%"><div>限制</div></th>
                            <th width="4%"><div>统计持续时间</div></th>
                            <th width="8%"><div>进入黑名单时所在服务器</div></th>
                            <th width="8%"><div>从黑名单中剔除</div></th>
                        </tr>
                        </thead>
                        <c:if test ="${loginBlackIpList!=null}">
                            <c:forEach items="${loginBlackIpList}" var="loginBlackIp" varStatus="status">
                                <tr>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.id}
                                    </td>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.insertTime}
                                    </td>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.name}
                                    </td>
                                    <td style="text-align:center;">
                                        <c:if test ="${loginBlackIp.limitSort == 0}">
                                            失败限制
                                        </c:if>
                                        <c:if test ="${loginBlackIp.limitSort == 1}">
                                            成功限制
                                        </c:if>
                                        <c:if test ="${loginBlackIp.limitSort == 2}">
                                            后台添加
                                        </c:if>
                                    </td>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.durationTime}
                                    </td>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.insertServer}
                                    </td>
                                    <td style="text-align:center;">
                                        <input type="button" value="删除" onclick="onDel('${loginBlackIp.id}','${loginBlackIp.name}')" />
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
        function trim(str){ //删除左右两端的空格
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }

        function onDel(id,ipStr){
            var url =  "/admin/security/delLoginBlackIpByName";
            var data = "ipStr="+ipStr+"&id="+id;
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
            var ipStr =  document.getElementById('addIpStr').value;
            if(!ipStr){
                alert("增加项不能为空");
                return;
            }
            ipStr = trim(ipStr);
            var url =  "/admin/security/addLoginBlackIp";
            var data = "ipStr="+ipStr;
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