<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-8
  Time: 下午7:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport后台</title>
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

            /* 去掉菜单标题栏 */
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
    <!-- 引入顶部信息 -->
    <%@ include file="/pages/admin/include_top.jsp" %>
    <div id="pageBd">
        <!-- 引入菜单 -->
        <%@ include file="/pages/admin/include_menu.jsp" %>
        <div id="pageCanvas" class="canvas">
            <div id="pageCanvasInt" class="canvasInt">
                <div id="pageCrumbs" class="crumbs">
                    当前位置：<strong>后台管理</strong>
                    <span class="step">&gt;</span><strong>接口频次配置管理</strong>
                </div>
                <h2 id="pageTitle">接口频次配置管理</h2>

                <hr>
            </div>
            <!-- pageCanvasInt End -->

            <div>
                <a href="/admin/interface/getclientandlevel">配置应用与等级</a>
                <a href="/admin/interface/queryinterfacelist">接口操作</a>
                <table style="font-size:13px" class="question-table" border="1">
                    <thead>
                    <tr align="center" style="background-color:#ADDA27;">
                        <td width="8%">
                            <div>级别</div>
                        </td>
                        <td width="15%">
                            <div>接口名称</div>
                        </td>
                        <td width="5%">
                            <div>接口初始频次</div>
                        </td>
                        <td width="10%">
                            <div>操作类型</div>
                        </td>
                    </tr>
                    </thead>
                    <c:if test="${interfaceVOList!=null}">

                        <%--<td style="text-align:center;" rowspan="${rowCount}">初级</td>--%>
                        <c:forEach items="${interfaceVOList}" var="primaryVO" varStatus="i">
                            <tr  align="center" data-pri="0" data-row-index="${i.index}">
                                <c:if test="${i.index==0}">
                                    <td rowspan="${rowCount}" width="8%">
                                        初级
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
                                                value="修改" class="modify"/>
                                        <input type="hidden" value="<c:out value="${primaryVO.id}"/>"
                                               class="modifyId"/>
                                        <input type="hidden" value="<c:out value="${primaryVO.interfaceName}"/>"
                                               class="modifyInterfaceName"/>
                                        <input type="hidden" value="0" class="modifyLevel"/>
                                        <input type="button" value="保存" class="save"
                                               style="display: none"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>


                        <%--<td style="text-align:center;" rowspan="${rowCount}">中级</td>--%>
                        <c:forEach items="${interfaceVOList}" var="middleVO" varStatus="j">
                            <tr align="center" data-pri="1" data-row-index="${j.index}">
                                <c:if test="${j.index==0}">
                                    <td rowspan="${rowCount}">
                                        中级
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
                                               value="修改" class="modify"/>
                                        <input type="hidden" value="<c:out value="${middleVO.id}"/>" class="modifyId"/>
                                        <input type="hidden" value="<c:out value="${middleVO.interfaceName}"/>"
                                               class="modifyInterfaceName"/>
                                        <input type="hidden" value="1" class="modifyLevel"/>
                                        <input type="button" value="保存"
                                               style="display: none" class="save"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>


                        <%--<td style="text-align:center;" rowspan="${rowCount}">高级</td>--%>
                        <%--<c:forEach items="${interfaceVOList}" var="highVO" varStatus="k">
                            <tr align="center" data-pri="2" data-row-index="${k.index}">
                                <c:if test="${k.index==0}">
                                    <td rowspan="${rowCount}">
                                        高级
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
                                               value="修改" class="modify">
                                        <input type="hidden" value="<c:out value="${highVO.id}"/>" class="modifyId"/>
                                        <input type="hidden" value="<c:out value="${highVO.interfaceName}"/>"
                                               class="modifyInterfaceName"/>
                                        <input type="hidden" value="2" class="modifyLevel"/>
                                        <input class="save" type="button" value="保存"
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