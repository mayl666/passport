<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liuling
  Date: 13-11-25
  Time: 下午4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="/admin/interface/queryinterfacelist">接口操作</a>

<div>
    <label>
        <%
            String str = (String) request.getAttribute("errMessage");
            out.write(str);
        %>
    </label>
</div>
</body>
</html>