<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport后台</title>
    <%@ include file="/pages/admin/head.jsp"%>

    <!--<link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.7.3.custom.css"/>
    <script type="text/javascript" language="javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery-ui-1.7.3.custom.min.js"></script>
 <link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <!--link href="${urlStatic}/css/jqgrid/ui.jqgrid.css" type="text/css" rel="stylesheet" -->

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
                    <form id="queryProblemForm" action="/admin/adminProblem/queryProblem" method="post">
                        <table>
                            <tr>
                                <td>选择日期</td>
                                <td>
                                    <input type="text" id="startDateStr" name="startDateStr" value="" data-input="date">
                                    &nbsp;<input type="text" id="endDateStr" name="endDateStr" value="" data-input="date">
                                </td>
                            </tr>
                            <tr>
                                <td>选择状态</td>
                                <td>
                                    <label class="check"><input name="ckStatus0" id="ckStatus0" class="chkStatus" type="checkbox">未回复</label>
                                    <label class="check"><input name="ckStatus1" id="ckStatus1" class="chkStatus" type="checkbox">已回复</label>
                                    <label class="check"><input name="ckStatus2" id="ckStatus2" class="chkStatus" type="checkbox">已关闭</label>
                                    <input name="status" id="status"  type="hidden" value="0">
                                </td>
                            </tr>
                            <tr>
                                <td>选择问题类型</td>
                                </td>
                                <td>
                                    <select id="typeId" name="typeId" class="sql_where" size=1>
                                        <option  value="0" selected >所有类型</option>
                                        <c:forEach items="${typeList}" var="problemType" varStatus="status">
                                        <option  value="${problemType.id}">${problemType.typeName}</option>
                                        </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>反馈标题</td>
                                <td>
                                    <div class="input-append">
                                        <input id="title" name="title"  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>反馈内容</td>
                                <td>
                                    <div class="input-append">
                                        <input id="content" name="content" class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <input id="pageNum" name="pageNum" value="1" type="hidden">
                            <tr><td><span class="button button-main"><button type="submit" onclick="onQueryProblemSubmit();" class="button">开始查询</button></span>
                            </td></tr>
                        <table>
                    </form>


                    <hr>
				</div><!-- pageCanvasInt End -->

                <div>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="7%"><div>日期</div></th>
                            <th width="4%"><div>问题ID</div></th>
                            <th width="6%"><div>用户ID</div></th>
                            <th width="4%"><div>clientID</div></th>
                            <th width="6%"><div>用户邮箱</div></th>
                            <th width="8%"><div>问题类型</div></th>
                            <th width="12%"><div>反馈标题</div></th>
                            <th width="16%"><div>反馈内容</div></th>
                            <th width="8%"><div>状态</div></th>

                            <th width="8%"><div>回复</div></th>
                            <th width="12%"><div>更改状态</div></th>
                        </tr>
                        </thead>
                        <c:if test ="${problemVOList!=null}">
                        <c:forEach items="${problemVOList}" var="problemVO" varStatus="status">
                        <tr>
                            <td style="text-align:center;">
                                    ${problemVO.subTime}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.id}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.passportId}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.clientId}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.email}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.typeName}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.title}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.content}
                            </td>
                            <td style="text-align:center;">
                                    ${problemVO.stateName}
                            </td>
                            <td style="text-align:center;">
                               <input type="button" value="回复" onclick="onAnswer('${problemVO.id}','${problemVO.email}','${UserPassportId}')" />
                            </td>
                            <td style="text-align:center;">
                                <input type="button" value="已回复" onclick="onChanageState('${problemVO.id}','1','${UserPassportId}')" />
                                <input type="button" value="关闭" onclick="onChanageState('${problemVO.id}','2','${UserPassportId}')" />
                            </td>
                        </tr>
                        </c:forEach>

                        </c:if>
                     </table>
                     第${currentPage}/${totalPageNum}页
                    <a href="javascript:onProblemPage('${queryConditonStr}',1);" class="btn_save">第一页</a>
                    <a href="javascript:onProblemPage('${queryConditonStr}',${currentPage}-1);" class="btn_save"> 上一页</a>
                    <a href="javascript:onProblemPage('${queryConditonStr}',${currentPage}+1);" class="btn_save"> 下一页</a>
                    <a href="javascript:onProblemPage('${queryConditonStr}',${totalPageNum});" class="btn_save"> 尾页</a>

                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">

        function onQueryProblemSubmit(){
            if(document.getElementById('ckStatus2').checked == true){
                document.getElementById('status').value = 3;
            }else if(document.getElementById('ckStatus1').checked == true){
                document.getElementById('status').value = 2;
            }else if(document.getElementById('ckStatus0').checked == true){
                document.getElementById('status').value = 1;
            }

            jQuery("#queryProblemForm").submit();
        }
        function onProblemPage(conditionStr,pageNum){
            if(pageNum<=0){
                return;
            }
            if(pageNum>${totalPageNum}){
                return;
            }
            document.getElementById('status').value = '${status}';
            document.getElementById('startDateStr').value = '${startDateStr}';
            document.getElementById('endDateStr').value = '${endDateStr}';
            document.getElementById('title').value = '${title}';
            document.getElementById('content').value = '${content}';
            document.getElementById('typeId').value = '${typeId}';
            document.getElementById('pageNum').value = pageNum;

            jQuery("#queryProblemForm").submit();
        }
        function onAnswer(problemId,email,passportId){
            document.getElementById('_problemId').value = problemId;
            document.getElementById('_email').value = email;
            document.getElementById('_ansPassportId').value = passportId;

            jQuery("#floatProblemAnswer").dialog({
                autoOpen : true,
                title : '给用户发送回答',
                modal: true,
                draggable: false,
                resizable : false,
                //position: ['top','right'],
                width: 500,
                height:200
                /*close: function(){
                    window.location.reload();
                }*/
            });
        }
        function answerFormSubmit() {
            var form =  document.getElementById('answerForm');
            var _ansContent = form._ansContent.value;
            if ( _ansContent== "") {
                alert("请输入回答内容！");
                form._ansContent.focus();
                return false;
            }
            var _problemId = form._problemId.value;
            var _email = form._email.value;
            var _ansPassportId = form._ansPassportId.value;

            var url =  "/admin/adminProblem/addProblemAnswer";
            var data = "_problemId="+_problemId+"&_email="+_email+"&_ansPassportId="+_ansPassportId+"&_ansContent="+_ansContent;
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                success:  function(data){
                    alert(data.statusText);
                    //jQuery("#floatProblemAnswer").css("display","none");
                },
                dataType: "json"
            });

        }
        function onChanageState(problemId,status,passportId){
            var url =  "/admin/adminProblem/updateProblemStatus";
            var data = "_problemId="+problemId+"&_status="+status+"&_ansPassportId="+passportId;
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