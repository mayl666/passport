<%@page import="java.util.List" %>
<%@page import="java.util.Arrays" %>
<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<%@ page isELIgnored="false" %>
<div id="pageNav">
    <ul id="pageNavList" class="tree">
        <li class="expanded">
            <h4><a href="#"><span class="icon i-home"></span>���˵�</a></h4>
        </li>
    </ul>
</div>
<div id="pageNav">
    ${UserMenuHtmlCache}


    <%--<script type="text/javascript">--%>
        <%--//��ʱ����۵��˵�.--%>
        <%--$(document).ready(function () {--%>
            <%--zTree1.expandAll(false);--%>
        <%--});--%>
    <%--</script>--%>

</div>