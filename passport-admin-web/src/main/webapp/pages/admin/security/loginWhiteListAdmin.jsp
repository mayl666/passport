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
                        <span class="step">&gt;</span><strong>��ȫ�Թ���</strong>
                    </div>
					<h2 id="pageTitle">����������</h2>


                    <hr>


                    <form action="/admin/security/getWhiteItemByName" method="post">
                        ������ip����username��
                        <input type="text" name="ipOrUsername"/>
                                <span class="button button-main">
                                  <input type="submit" value="��ѯ" class="button"/>
                                </span>
                        <c:if test="${curIpExist==false}">
                            <div style="color:#ff0000">��IP�ں����в�����</div>
                        </c:if>
                    </form>
                    <hr>
                    <form action="/admin/security/loginWhiteList" method="post">
                        <span class="button button-main">
                             <input type="submit" value="��ʾ���а������б�" class="button"/>
                        </span>
                    </form>
                    <hr>

                    ������ip����username��
                    <input type="text" id="ipOrUsername" name="ipOrUsername"/>
                                <span class="button button-main">
                                  <input type="submit" value="����" class="button" onclick="onAdd()"/>
                                </span>
                    <hr>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="7%"><div>ip����username </div></th>
                            <th width="8%"><div>�Ӱ��������޳�</div></th>
                        </tr>
                        </thead>
                        <c:if test ="${loginWhiteList!=null}">
                            <c:forEach items="${loginWhiteList}" var="loginWhiteValue" varStatus="status">
                                <tr>
                                    <td style="text-align:center;">
                                            ${loginWhiteValue}
                                    </td>
                                    <td style="text-align:center;">
                                        <input type="button" value="ɾ��" onclick="onDel('${loginWhiteValue}')" />
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