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
					<h2 id="pageTitle">��ʷ��½������</h2>


                    <hr>

                    <form id="queryProblemForm" action="/admin/security/queryHistroyLoginBlackItem" method="post">
                        <table>
                            <tr>
                                <td>ѡ������</td>
                                <td>
                                    <input type="text" id="startDateStr" name="startDateStr" value="" data-input="date">
                                    &nbsp;<input type="text" id="endDateStr" name="endDateStr" value="" data-input="date">
                                </td>
                            </tr>

                            <tr>
                                <td>ѡ��������</td>
                                <td>
                                    <select id="sortId" name="sortId" class="sql_where" size=1>
                                        <option  value="-1" selected >����</option>
                                        <option  value="0">username</option>
                                        <option  value="1">ip</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>ѡ����������</td>
                                </td>
                                <td>
                                    <select id="typeId" name="typeId" class="sql_where" size=1>
                                        <option  value="-1" selected >��������</option>
                                        <option  value="0">ʧ��</option>
                                        <option  value="1">�ɹ�</option>
                                        <option  value="2">��̨���</option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td>ͳ�Ƴ���ʱ�����ֵ</td>
                                <td>
                                    <div class="input-append">
                                        <input id="maxDuration" name="maxDuration"  class="span2" type="text">
                                    </div>
                                </td>
                                <td>ͳ�Ƴ���ʱ����Сֵ</td>
                                <td>
                                    <div class="input-append">
                                        <input id="minDuration" name="minDuration" class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>ip����username</td>
                                <td>
                                    <div class="input-append">
                                        <input id="name" name="name" class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <input id="pageNum" name="pageNum" value="1" type="hidden">
                            <tr><td><span class="button button-main"><button type="submit" class="button">��ʼ��ѯ</button></span>
                            </td></tr>
                            <table>
                    </form>

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

                    ��${currentPage}/${totalPageNum}ҳ
                    <a href="javascript:onProblemPage(1);" class="btn_save">��һҳ</a>
                    <a href="javascript:onProblemPage(${currentPage}-1);" class="btn_save"> ��һҳ</a>
                    <a href="javascript:onProblemPage(${currentPage}+1);" class="btn_save"> ��һҳ</a>
                    <a href="javascript:onProblemPage(${totalPageNum});" class="btn_save"> βҳ</a>

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
                alert("�������������ƣ�");
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