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
    <%--<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>--%>
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
                <h2 id="pageTitle">批量删除注册手机号</h2>

                <div class="panel panel-default">
                    <!-- Default panel contents -->
                    <div class="panel-heading">批量删除</div>
                    <form class="navbar-form navbar-left" id="deleteRegMobileForm" name="deleteRegMobileForm" action="/admin/deleteRegMobiles" method="post">
                        <table class="table">
                            <div class="form-group">
                                <div class="input-group">
                                    <textarea class="form-control" id="mobiles" name="mobiles" rows="10" cols="40" placeholder="请输入要删除手机号"></textarea>
                                </div>
                            </div>
                            <div class="input-group">
                                <p>
                                    <button type="submit" class="btn btn-default">解除</button>
                                </p>
                            </div>
                            <c:if test="${failed!=null && failed!=''}">
                            <div class="list-group">
                                <div class="input-group">
                                    <span class="label label-danger">删除注册手机号失败结果</span>
                                </div>
                            </div>

                            <!-- 解绑失败结果 -->
                            <div class="list-group">
                                <div class="input-group">
                                    <textarea class="form-control" id="failed" name="failed" rows="10" cols="40"><c:out value="${failed}"/></textarea>
                                </div>
                            </div>
                            </c:if>
                        </table>
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
    //批量解绑手机
    function deleteRegMobiles() {
        var mobiles = $("#mobiles").val();
        $.post('/admin/deleteRegMobiles', {mobiles: mobiles}, function (data) {
            alert(data.statusText);
        }, 'json');
    }

    function showMsg() {
        <c:if test ="${msg!=null}">
            alert("${msg}");
        </c:if>
    }
    showMsg();
</script>
</body>
</html>