<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>passport后台</title>
    <%@ include file="/pages/admin/head.jsp" %>
    <%--<script src="/js/jquery.md5.js" type="text/javascript"></script>--%>

    <!-- 最新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <%--<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>--%>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
                    <span class="step">&gt;</span><strong>用户管理</strong>
                </div>
                <h2 id="pageTitle">解除绑定</h2>

                <form class="navbar-form navbar-left" role="search" action="/admin/unBind" method="post">
                    <div class="form-group">
                        <input type="text" id="passportId" name="passportId" value="${account.passportId}"
                               class="form-control"
                               placeholder="用户账号">
                    </div>
                    <button type="submit" class="btn btn-default">查询</button>
                </form>


                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">绑定信息</div>
                    <form class="navbar-form navbar-left" id="unBindForm" name="unBindForm" action="" method="post">
                        <c:if test="${account!=null && account!=''}">
                            <table class="table">
                                <tbody>
                                <input type="hidden" id="ppId" name="ppId"
                                       value="<c:out value="${account.passportId}"/>"/>
                                <input type="hidden" id="phone" name="phone"
                                       value="<c:out value="${account.mobile}"/>"/>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-envelope">密保邮箱</span>
                                        <c:choose>
                                            <c:when test="${account.email!=null && account.email!=''}">
                                                <c:out value="${account.email}"></c:out>
                                                <span class="label label-success">已经绑定</span>

                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default"
                                                            onclick="unbindEmail()">解绑
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="label label-danger">未设置</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span class="glyphicon glyphicon-phone">密保手机</span>
                                        <c:choose>
                                            <c:when test="${account.mobile!=null && account.mobile!=''}">
                                                <c:out value="${account.mobile}"></c:out>
                                                <span class="label label-success">已经绑定</span>

                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-default"
                                                            onclick="unbindMobile()">解绑
                                                    </button>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="label label-danger">未设置</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </c:if>
                    </form>
                </div>
            </div>
            <!-- pageCanvasInt End -->
        </div>
        <!-- pageCanvas End -->
    </div>
    <!-- pageBd End -->
</div>
<!-- page End -->
<script type="text/javascript">

    //解绑邮箱
    function unbindEmail() {
        var passportId = $("#ppId").val();
        $.post('/admin/unBindEmail', {passportId: passportId}, function (data) {
             alert(data.statusText);
        }, 'json');
    }

    //解绑手机
    function unbindMobile() {
        var passportId = $("#ppId").val();
        var mobile = $("#phone").val();
        $.post('/admin/unBindPhone', {passportId: passportId, mobile: mobile}, function (data) {
            alert(data.statusText);
        }, 'json');
    }
</script>

</body>
</html>