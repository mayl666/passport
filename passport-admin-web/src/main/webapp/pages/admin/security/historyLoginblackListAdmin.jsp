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
					<h2 id="pageTitle">历史登陆黑名单</h2>


                    <hr>

                    <form id="queryProblemForm" action="/admin/security/queryHistroyLoginBlackItem" method="post">
                        <table>
                            <tr>
                                <td>选择日期</td>
                                <td>
                                    <input type="text" id="startDateStr" name="startDateStr" value="" data-input="date">
                                    &nbsp;<input type="text" id="endDateStr" name="endDateStr" value="" data-input="date">
                                </td>
                            </tr>

                            <tr>
                                <td>选择限制项</td>
                                <td>
                                    <select id="sortId" name="sortId" class="sql_where" size=1>
                                        <option  value="-1" selected >所有</option>
                                        <option  value="0">username</option>
                                        <option  value="1">ip</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>选择限制类型</td>
                                </td>
                                <td>
                                    <select id="typeId" name="typeId" class="sql_where" size=1>
                                        <option  value="-1" selected >所有类型</option>
                                        <option  value="0">失败</option>
                                        <option  value="1">成功</option>
                                        <option  value="2">后台添加</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>统计持续时间最大值</td>
                                <td>
                                    <div class="input-append">
                                        <input id="maxDuration" name="maxDuration"  class="span2" type="text">
                                    </div>
                                </td>
                                <td>统计持续时间最小值</td>
                                <td>
                                    <div class="input-append">
                                        <input id="minDuration" name="minDuration" class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>ip或者username</td>
                                <td>
                                    <div class="input-append">
                                        <input id="name" name="name" class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <input id="pageNum" name="pageNum" value="1" type="hidden">
                            <tr><td><span class="button button-main"><button type="submit" class="button">开始查询</button></span>
                            </td></tr>
                            <table>
                    </form>

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

                    第${currentPage}/${totalPageNum}页
                    <a href="javascript:onProblemPage(1);" class="btn_save">第一页</a>
                    <a href="javascript:onProblemPage(${currentPage}-1);" class="btn_save"> 上一页</a>
                    <a href="javascript:onProblemPage(${currentPage}+1);" class="btn_save"> 下一页</a>
                    <a href="javascript:onProblemPage(${totalPageNum});" class="btn_save"> 尾页</a>

				</div><!-- pageCanvasInt End -->

                <div>
                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">

        function onQueryBlckItemSubmit() {
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

        function onProblemPage(pageNum){
            if(pageNum<=0){
                return;
            }
            if(pageNum>${totalPageNum}){
                return;
            }
            document.getElementById('sortId').value = '${sortId}';
            document.getElementById('typeId').value = '${typeId}';
            document.getElementById('startDateStr').value = '${startDateStr}';
            document.getElementById('endDateStr').value = '${endDateStr}';
            document.getElementById('maxDuration').value = '${maxDuration}';
            document.getElementById('minDuration').value = '${minDuration}';
            document.getElementById('name').value = '${name}';
            document.getElementById('pageNum').value = pageNum;

            jQuery("#queryProblemForm").submit();
        }

        function onDel(id,ipStr){
            var url =  "/admin/security/delLoginBlackItemById";
            var data = "id="+id;
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