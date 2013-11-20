<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-8
  Time: ����7:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport��̨</title>
    <%@ include file="/pages/admin/head.jsp" %>

    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.7.3.custom.css"/>
    <script type="text/javascript" language="javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery-ui-1.7.3.custom.min.js"></script>
    <link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <!--link href="${urlStatic}/css/jqgrid/ui.jqgrid.css" type="text/css" rel="stylesheet" -->

    <style>
        div#header {
            display: none;
            background-color: #f5f5dc;
        }

        div#logo {
            width: 130px;
            height: 37px;
            background: url(/img/admin/logo.png);
        }

        #menu_name {
            display: none;
        }

            /* ȥ���˵������� */
        iframe#main {
            margin: 0;
            padding: 0;
        }
        table{border-collapse: collapse;}
        table input[type='text']{border:1px solid #ADDA27;}
        table input[type='text']:disabled{border-color: #fff;}
        table tr.error input.oldLevelCount{background: red;color:#fff;}
    </style>
</head>
<body>
<div id="page">
    <!-- ���붥����Ϣ -->
    <%@ include file="/pages/admin/include_top.jsp" %>
    <div id="pageBd">
        <!-- ����˵� -->
        <%@ include file="/pages/admin/include_menu.jsp" %>
        <div id="pageCanvas" class="canvas">
            <div id="pageCanvasInt" class="canvasInt">
                <div id="pageCrumbs" class="crumbs">
                    ��ǰλ�ã�<strong>��̨����</strong>
                    <span class="step">&gt;</span><strong>�ӿ�Ƶ�����ù���</strong>
                </div>
                <h2 id="pageTitle">�ӿ�Ƶ�����ù���</h2>

                <hr>
            </div>
            <!-- pageCanvasInt End -->

            <div>
                <a href="/admin/interface/getclientandlevel">����Ӧ����ȼ�</a>
                <a href="/admin/interface/queryinterfacelist">�ӿڲ���</a>
                <table style="font-size:13px" class="question-table" border="1">
                    <thead>
                    <tr align="center" style="background-color:#ADDA27;">
                        <td width="8%">
                            <div>����</div>
                        </td>
                        <td width="15%">
                            <div>�ӿ�����</div>
                        </td>
                        <td width="5%">
                            <div>�ӿڳ�ʼƵ��</div>
                        </td>
                        <td width="10%">
                            <div>��������</div>
                        </td>
                    </tr>
                    </thead>
                    <c:if test="${interfaceVOList!=null}">

                        <%--<td style="text-align:center;" rowspan="${rowCount}">����</td>--%>
                        <c:forEach items="${interfaceVOList}" var="primaryVO" varStatus="i">
                            <tr  align="center" data-pri="0" data-row-index="${i.index}">
                                <c:if test="${i.index==0}">
                                    <td rowspan="${rowCount}" width="8%">
                                        ����
                                    </td>
                                </c:if>
                                <c:if test="${1!=0}">
                                    <td style="text-align:center;" width="15%"><c:out
                                            value="${primaryVO.interfaceName}"/></td>
                                    <td style="text-align:center;" width="5%">
                                        <input type="text" class="oldLevelCount" style="display: block"
                                               value="<c:out value="${primaryVO.primaryLevelCount}"/>"
                                                   disabled="disabled"/>
                                        <input type="text" class="newLevelCount" style="display: none"/>
                                    </td>
                                    <td style="text-align:center;" width="20%">
                                        <input
                                                name="<c:out value="${primaryVO.primaryLevelCount}"/>" type="button"
                                                value="�޸�" class="modify"/>
                                        <input type="hidden" value="<c:out value="${primaryVO.id}"/>"
                                               class="modifyId"/>
                                        <input type="hidden" value="<c:out value="${primaryVO.interfaceName}"/>"
                                               class="modifyInterfaceName"/>
                                        <input type="hidden" value="0" class="modifyLevel"/>
                                        <input type="button" value="����" class="save"
                                               style="display: none"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>


                        <%--<td style="text-align:center;" rowspan="${rowCount}">�м�</td>--%>
                        <c:forEach items="${interfaceVOList}" var="middleVO" varStatus="j">
                            <tr align="center" data-pri="1" data-row-index="${j.index}">
                                <c:if test="${j.index==0}">
                                    <td rowspan="${rowCount}">
                                        �м�
                                    </td>
                                </c:if>
                                <c:if test="${1!=0}">
                                    <td style="text-align:center;"><c:out value="${middleVO.interfaceName}"/></td>
                                    <td style="text-align:center;">
                                        <input type="text" class="oldLevelCount" style="display: block"
                                               value="<c:out value="${middleVO.middleLevelCount}"/>" disabled="false"/>
                                        <input type="text" class="newLevelCount" style="display: none"/>
                                    </td>
                                    <td style="text-align:center;">
                                        <input name="<c:out value="${middleVO.middleLevelCount}"/>"
                                               type="button"
                                               value="�޸�" class="modify"/>
                                        <input type="hidden" value="<c:out value="${middleVO.id}"/>" class="modifyId"/>
                                        <input type="hidden" value="<c:out value="${middleVO.interfaceName}"/>"
                                               class="modifyInterfaceName"/>
                                        <input type="hidden" value="1" class="modifyLevel"/>
                                        <input type="button" value="����"
                                               style="display: none" class="save"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>


                        <%--<td style="text-align:center;" rowspan="${rowCount}">�߼�</td>--%>
                        <%--<c:forEach items="${interfaceVOList}" var="highVO" varStatus="k">
                            <tr align="center" data-pri="2" data-row-index="${k.index}">
                                <c:if test="${k.index==0}">
                                    <td rowspan="${rowCount}">
                                        �߼�
                                    </td>
                                </c:if>
                                <c:if test="${1!=0}">
                                    <td style="text-align:center;"><c:out value="${highVO.interfaceName}"/></td>
                                    <td style="text-align:center;">
                                        <input type="text" class="oldLevelCount" style="display: block"
                                               value="<c:out value="${highVO.highLevelCount}"/>" disabled="false"/>
                                        <input type="text" class="newLevelCount" style="display: none"/>
                                    </td>
                                    <td style="text-align:center;">
                                        <input name="<c:out value="${highVO.highLevelCount}"/>"
                                               type="button"
                                               value="�޸�" class="modify">
                                        <input type="hidden" value="<c:out value="${highVO.id}"/>" class="modifyId"/>
                                        <input type="hidden" value="<c:out value="${highVO.interfaceName}"/>"
                                               class="modifyInterfaceName"/>
                                        <input type="hidden" value="2" class="modifyLevel"/>
                                        <input class="save" type="button" value="����"
                                               style="display: none"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach> --%>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var url="/admin/interface/getinterfaceandlevellist";
        $.ajax({
            type: 'GET',
            url: url,
            dataType: "json"
        });
    });
    $(".modify").click(function (e) {
        var elem = this;
        var oldT = $(elem).parent('td').parent('tr').find("input.oldLevelCount").prop("disabled",false);
        //var newT = $(elem).parent('td').parent('tr').find("input.newLevelCount").show();
        //newT.val(oldT.val());
        $(elem).hide().parent('td').parent('tr').find("input.save").show();
        return;
    });
    $('.save').click(function () {
        $('tr').removeClass('error');
        var $tr = $(this).parent("td").parent("tr");
        var primaryId = $tr.find('.modifyId').val();
        var primaryInterfaceName = $tr.find('.modifyInterfaceName').val();
        var primaryLevel = $tr.find('.modifyLevel').val();
        var primaryLevelCount = $tr.find('.oldLevelCount').val();

        {
            var failed=false;
            var my_pri=+$tr.attr('data-pri'),my_index=+$tr.attr('data-row-index');
            $.each($("table tr[data-pri][data-row-index='"+my_index+"']"),function(index,item){

                var $item=$(item),tmp_pri;
                if((tmp_pri=+$item.attr('data-pri'))==my_pri)
                    return true;

                var val=+$item.find(".oldLevelCount").val();
                 if(tmp_pri>my_pri&&val<=primaryLevelCount){failed=true;$item.addClass('error')}
                if(tmp_pri<my_pri&&val>=primaryLevelCount){failed=true;$item.addClass("error");}

            });
                 if(failed){
                    return 0;//alert('ffz');
                 }
        }
        var url = "/admin/interface/saveinterfaceandlevel";
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                id: primaryId,
                interfaceName: primaryInterfaceName,
                level: primaryLevel,
                levelCount: primaryLevelCount
            },
            complete: function (data) {
                location.reload();
            },
            dataType: "json"
        });
    });
</script>
</body>
</html>