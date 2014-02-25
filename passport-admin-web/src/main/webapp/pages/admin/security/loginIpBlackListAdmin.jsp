<%@page import="com.sogou.upd.passport.model.black.BlackItem" %>
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
                        <span class="step">&gt;</span><strong>��½�ڰ���������</strong>
                    </div>
					<h2 id="pageTitle">��ǰip������</h2>

                    <form action="/admin/security/getLoginBlackIpByName" method="post">
                        ������ip��
                        <input type="text" name="ipStr"/>
                                <span class="button button-main">
                                  <input type="submit" value="��ѯ" class="button"/>
                                </span>
                        <c:if test="${curIpExist==false}">
                            <div style="color:#ff0000">��IP�ں����в�����</div>
                        </c:if>
                    </form>
                    <hr>
                    <form action="/admin/security/currentLoginBlackIplist" method="post">
                        <span class="button button-main">
                             <input type="submit" value="��ʾ���к�����IP�б�" class="button"/>
                        </span>
                    </form>
                    <hr>
                        ������ip��
                        <input type="text" id="addIpStr" name="addIpStr"/>
                                <span class="button button-main">
                                  <input type="submit" value="����" class="button" onclick="onAdd()"/>
                                </span>
                    <hr>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="7%"><div>ID </div></th>
                            <th width="7%"><div>����ʱ��</div></th>
                            <th width="4%"><div>ip</div></th>
                            <th width="6%"><div>����</div></th>
                            <th width="4%"><div>ͳ�Ƴ���ʱ��</div></th>
                            <th width="8%"><div>���������ʱ���ڷ�����</div></th>
                            <th width="8%"><div>�Ӻ��������޳�</div></th>
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
                                            ʧ������
                                        </c:if>
                                        <c:if test ="${loginBlackIp.limitSort == 1}">
                                            �ɹ�����
                                        </c:if>
                                        <c:if test ="${loginBlackIp.limitSort == 2}">
                                            ��̨���
                                        </c:if>
                                    </td>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.durationTime}
                                    </td>
                                    <td style="text-align:center;">
                                            ${loginBlackIp.insertServer}
                                    </td>
                                    <td style="text-align:center;">
                                        <input type="button" value="ɾ��" onclick="onDel('${loginBlackIp.id}','${loginBlackIp.name}')" />
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
        function trim(str){ //ɾ���������˵Ŀո�
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
                alert("�������Ϊ��");
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