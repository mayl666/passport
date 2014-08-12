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
                    <span class="step">&gt;</span><strong>重置密码</strong>
                </div>
                <h2 id="pageTitle">重置密码</h2>
                <hr>
                <form class="form-horizontal" action="/admin/resetPassword" method="post">
                    <div class="form-group">
                        <label for="passportId" class="col-sm-2 control-label">账号</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="passportId" name="passportId" value="<c:out value="${passportId}"/>"
                                   placeholder="请输入通行证帐号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPwd" class="col-sm-2 control-label">新密码</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="newPwd" name="newPwd" value="<c:out value="${newPwd}"/>"
                                   placeholder="温馨提示:点击重置密码,新密码自动生成">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default" onclick="submitResetForm()">重置密码</button>
                        </div>
                    </div>
                </form>
            </div>
            <!-- pageCanvasInt End -->
        </div>
        <!-- pageCanvas End -->
    </div>
    <!-- pageBd End -->
</div>
<!-- page End -->

<script type="text/javascript">
    //提交
    function submitResetForm() {
        var passportId = $("#passportId").val();
        if (passportId == null || passportId == "") {
            alert("用户账号不能为空");
            return false;
        }
        document.forms["resetForm"].submit();
    }

</script>
</body>
</html>