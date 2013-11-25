<%@page import="java.util.List" %>
<%@page import="java.util.Arrays" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<div id="pageNav">
    <ul id="pageNavList" class="tree">
        <li class="expanded">
            <h4><a href="#"><span class="icon i-home"></span>主菜单</a></h4>
        </li>
    </ul>
</div>
<div id="pageNav">
    ${UserMenuHtmlCache}


    <%--<script type="text/javascript">--%>
        <%--//临时添加折叠菜单.--%>
        <%--$(document).ready(function () {--%>
            <%--zTree1.expandAll(false);--%>
        <%--});--%>
    <%--</script>--%>

</div>