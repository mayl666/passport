<%@ page import="java.text.SimpleDateFormat" %>
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
<script>
    function   formatDate(d,userid)   {
        var  now=new Date(d);
        var   year=now.getYear()+1900;
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();
        //doc.innerHTML(year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second);
        var tmp = document.getElementById(userid);
        tmp.innerHTML=year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
        //$("#tda").append("AAAAA");
        //return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
    }
</script>
</head>
<%
    String  realPath1  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
%>
<body>
	<div id="page">
        <div id="addFrame" style="display: none">
            <form id="addForm" action="/admin/blackList/addBlackList"  method="post">
                <table>
                    <tr>
                        <td>�� �ţ�</td>
                        <td><input type="text" id="passportId_input" name="passportId_input"></td>
                    </tr>
                    <tr>
                        <td>��Чʱ�䣺</td>
                        <td>
                            <select name="expire_time" id="expire_time">
                                <option value="0" selected="selected">��ʹ��Ч</option>
                                <option value="1">1����</option>
                                <option value="5">5����</option>
                                <option value="10">10����</option>
                                <option value="30">30����</option>
                                <option value="60">60����</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="button" onclick="addBlackList()"  value="�ύ"/></td>
                    </tr>
                </table>
            </form>
        </div>
		<!-- ���붥����Ϣ -->
		<%@ include file="/pages/admin/include_top.jsp"%>
		<div id="pageBd">
			<!-- ����˵� -->
			<%@ include file="/pages/admin/include_menu.jsp"%>
            <div id="dialog-message" title="��ʾ" style="display: none">
                <p>
                    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                    �޸ĳɹ���
                </p>
            </div>
            <div id="dialog-addmessage" title="��ʾ" style="display: none">
                <p>
                    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                    ��ӳɹ���
                </p>
            </div>
            <div id="dialog-confirm" title="���棡" style="display: none">
                <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>ȷ���޸ĸ��˻�״̬��</p>
            </div>
			<div id="pageCanvas" class="canvas">
				<div id="pageCanvasInt" class="canvasInt">
					<div id="pageCrumbs" class="crumbs">
						��ǰλ�ã�<strong>��̨����</strong>
                        <span class="step">&gt;</span><strong>����������</strong>
                    </div>
					<h2 id="pageTitle">����������</h2>


                    <hr>
                    <form id="queryProblemForm" action="/admin/blackList/queryBlackList" method="post">
                        <table>
                            <tr>
                                <td>ͨ��֤�˺�</td>
                                <td>
                                    <div class="input-append">
                                        <input id="passportId" name="passportId" value='${passportId}'  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td>�ǳ�</td>--%>
                                <%--<td>--%>
                                    <%--<div class="input-append">--%>
                                        <%--<input id="nickname" name="nickname" class="span2" type="text">--%>
                                    <%--</div>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <input id="pageNo" name="pageNo" value="1" type="hidden">
                            <tr>
                                <td>
                                    <span class="button button-main"><button type="submit" onclick="onQueryProblemSubmit();" class="button">��ʼ��ѯ</button></span>
                                    <span class="button button-main"><button type="button" onclick="onDisplayAddFrame();" class="button">����</button></span>
                                </td>
                            </tr>
                        <table>
                    </form>


                    <hr>
				</div><!-- pageCanvasInt End -->

                <div>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="6%"><div>�û�ID</div></th>
                            <th width="8%"><div>�û�����</div></th>
                            <th width="12%"><div>ʱЧ��</div></th>
                            <th width="8%"><div>����ʱ��</div></th>
                            <th width="8%"><div>�޸�ʱ��</div></th>
                            <th width="4%"><div>״̬</div></th>
                            <th width="4%"><div>����</div></th>
                        </tr>
                        </thead>
                        <c:forEach items="${page.items}" var="item" varStatus="status">
                        <tr>
                            <td style="text-align:center;">
                                    ${item.userid}
                            </td>
                            <td style="text-align:center;">
                                <c:if test="${item.account_type==0}">δ֪</c:if>
                                <c:if test="${item.account_type==1}">�ѹ�</c:if>
                                <c:if test="${item.account_type==2}">�Ѻ�</c:if>
                                <c:if test="${item.account_type==3}">�ֻ�</c:if>
                                <c:if test="${item.account_type==4}">����</c:if>
                                <c:if test="${item.account_type==5}">������</c:if>
                                <c:if test="${item.account_type==6}">���Ի�</c:if>
                            </td>
                            <td style="text-align:center;" id="${item.userid}">
                                    <script>formatDate(${item.expire_time},'${item.userid}')</script>
                            </td>
                            <td style="text-align:center;">
                                    ${item.create_time}
                            </td>
                            <td style="text-align:center;">
                                    ${item.update_time}
                            </td>
                            <td style="text-align:center;">
                                    <c:if test="${item.status}" var="test">��Ч</c:if>
                                    <c:if test="${!test}">��Ч</c:if>
                            </td>
                            <td style="text-align:center;">
                                <c:if test="${test}" ><a onclick="resetValid('${item.userid}',false)">����Ч</a></c:if>
                                <c:if test="${!test}"><a onclick="resetValid('${item.userid}',true)">����Ч</a></c:if>
                            </td>
                        </tr>
                        </c:forEach>

                     </table>
                     ��${page.pageIndex}/${page.maxPage}ҳ  ��${page.total}��
                    <a href="javascript:onProblemPage(1);" class="btn_save">��ҳ</a>
                    <a href="javascript:onProblemPage(${page.pageIndex}-1);" class="btn_save"> ��һҳ</a>
                    <a href="javascript:onProblemPage(${page.pageIndex}+1);" class="btn_save"> ��һҳ</a>
                    <a href="javascript:onProblemPage(${page.maxPage});" class="btn_save"> βҳ</a>

                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">



        function onQueryProblemSubmit(){
            jQuery("#queryProblemForm").submit();
        }


        function onProblemPage(pageNum){
            if(pageNum<=0){
                return;
            }
            if(pageNum>${page.maxPage}){
                return;
            }
            document.getElementById('passportId').value = '${passportId}';
            document.getElementById('pageNo').value = pageNum;

            jQuery("#queryProblemForm").submit();
        }


        function onDisplayAddFrame(){
            $("#addFrame").dialog();
        }

        function addBlackList(){
            $.post("/admin/blackList/addBlackList",
                    {
                        passportId_input: $("#passportId_input").val(),
                        expire_time:$("#expire_time").val()
                    },
                    function(data){
                        var json = JSON.parse(data)
                        if(json.success){
                            $("#addFrame").dialog('close');
                            $( "#dialog-addmessage" ).dialog({
                                modal: true,
                                height:140,
                                buttons: {
                                    Ok: function() {
                                        $( this ).dialog( "close" );
                                        $("#queryProblemForm").submit();
                                    }
                                }
                            });
                        } else {
                            alert(json.message);
                        }
                    });
        }

        function resetValid(passportId,valid){
            $( "#dialog-confirm" ).dialog({
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    "ȷ��": function() {
                        $( this ).dialog( "close" );
                        $.post("/admin/blackList/updateBlackList",
                                {
                                    passportId: passportId,
                                    status:valid
                                },
                                function(data){
                                    var json = JSON.parse(data)
                                    if(json.success){
                                        $( "#dialog-message" ).dialog({
                                            modal: true,
                                            height:140,
                                            buttons: {
                                                Ok: function() {
                                                    $( this ).dialog( "close" );
                                                    $("#queryProblemForm").submit();
                                                }
                                            }
                                        });
                                    } else {
                                        alert(json.message);
                                    }
                                });
                    },
                    ȡ��: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });

        }


    </script>
</body>
</html>