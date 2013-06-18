<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport��̨</title>
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
                    <form id="queryProblemForm" action="/admin/adminProblem/queryProblem" method="post">
                        <table>
                            <tr>
                                <td>ѡ������</td>
                                <td>
                                    <input type="text" id="startDateStr" name="startDateStr" value="" data-input="date">
                                    &nbsp;<input type="text" id="endDateStr" name="endDateStr" value="" data-input="date">
                                </td>
                            </tr>
                            <tr>
                                <td>ѡ��״̬</td>
                                <td>
                                    <label class="check"><input name="ckStatus0" id="ckStatus0" class="chkStatus" checked="true" type="checkbox">δ�ظ�</label>
                                    <label class="check"><input name="ckStatus1" id="ckStatus1" class="chkStatus" type="checkbox">�ѻظ�</label>
                                    <label class="check"><input name="ckStatus2" id="ckStatus2" class="chkStatus" type="checkbox">�ѹر�</label>
                                    <input name="status" id="status"  type="hidden" value="0">
                                </td>
                            </tr>
                            <tr>
                                <td>ѡ����������</td>
                                </td>
                                <td>
                                    <select id="typeId" name="typeId" class="sql_where" size=1>
                                        <option  value="0" selected >��������</option>
                                        <c:forEach items="${typeList}" var="problemType" varStatus="status">
                                        <option  value="${problemType.id}">${problemType.typeName}</option>
                                        </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>��������</td>
                                <td>
                                    <div class="input-append">
                                        <input id="title" name="title"  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>��������</td>
                                <td>
                                    <div class="input-append">
                                        <input id="content" name="content" class="span2" type="text">
                                    </div>
                                </td>
                            </tr>

                            <tr><td><span class="button button-main"><button type="submit" onclick="onQueryProblemSubmit();" class="button">��ʼ��ѯ</button></span>
                            </td></tr>
                        <table>
                    </form>


                    <hr>
				</div><!-- pageCanvasInt End -->

                <div>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="7%"><div>����</div></th>
                            <th width="7%"><div>����ID</div></th>
                            <th width="6%"><div>�û�ID</div></th>
                            <th width="6%"><div>clientID</div></th>
                            <th width="6%"><div>�û�����</div></th>
                            <th width="8%"><div>��������</div></th>
                            <th width="16%"><div>��������</div></th>
                            <th width="16%"><div>��������</div></th>
                            <th width="4%"><div>״̬</div></th>

                            <th width="12%"><div>�ظ�</div></th>
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
                                    ${problemVO.status}
                            </td>
                            <td style="text-align:center;">
                               <input type="button" value="�ظ�" onclick="onAnswer('${problemVO.id}','${problemVO.email}','${UserPassportId}')" />
                            </td>
                        </tr>
                        </c:forEach>
                        </c:if>
                     </table>
                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">
        var xmlHttp;

        function createXMLHttpRequest(){
            try{
                xmlHttp=new XMLHttpRequest();
            }catch (e){
                try{
                    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }catch (e){
                    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                }
            }
            return xmlHttp;
        }

        function doAjaxPost(form){
            createXMLHttpRequest();
            if(xmlHttp==null){
                window.status="����������֧��AJAX!";
                return;
            }
            xmlHttp.onreadystatechange=checkCallBack;
            xmlHttp.open("post",url,true);
            var SendContent="r_Content="+escape(document.getElementById("r_Content").value);
            xmlhttp.send(SendContent);
            xmlHttp.send(url);
        }

        function checkCallBack(){
            if(xmlHttp.readyState==4){
                var obj=xmlHttp.responseText;
                alert(obj);
                var doc = eval('(' + obj + ')');
                //alert(doc.result);
                //alert(doc.userName);
                //alert(doc.userId);
                //alert(doc.failType);
            }
        }
        function onQueryProblemSubmit(){
            if(document.getElementById('ckStatus0').checked == true){
                document.getElementById('status').value = 0;
            }else if(document.getElementById('ckStatus1').checked == true){
                document.getElementById('status').value = 1;
            }else{
                document.getElementById('status').value = 2;
            }
            jQuery("#queryProblemForm").submit();
        }

        function onAnswer(problemId,email,passportId){
            document.getElementById('_problemId').value = problemId;
            document.getElementById('_email').value = email;
            document.getElementById('_ansPassportId').value = passportId;

            jQuery("#floatProblemAnswer").dialog({
                autoOpen : true,
                title : '���û����ͻش�',
                modal: true,
                draggable: false,
                resizable : false,
                //position: ['top','right'],
                width: 430,
                height:370,
                close: function(){
                    window.location.reload();
                }
            });
        }
        function answerFormSubmit(form) {
            var form =  document.getElementById('answerForm');
            if (form.content.value == "") {
                alert("������ش����ݣ�");
                form.content.focus();
                return false;
            }
            jQuery("#answerForm").submit();

        }
        function goback() {
            jQuery("#floatProblemAnswer").css("display","none");
        }
    </script>
</body>
</html>