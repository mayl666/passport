<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>passport后台</title>
    <%@ include file="/pages/admin/head.jsp"%>

    <!--<link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.7.3.custom.css"/>
    <script type="text/javascript" language="javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" language="javascript" src="/js/jquery-ui-1.7.3.custom.min.js"></script>
 <link href="/css/jqgrid/ui.jqgrid.css" rel="stylesheet" type="text/css">
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <!--link href="${urlStatic}/css/jqgrid/ui.jqgrid.css" type="text/css" rel="stylesheet" -->

<style>
div#header { display:none; background-color: #f5f5dc; }
div#logo { width:130px; height:37px; background: url(/img/admin/logo.png); }
#menu_name { display: none; } /* 去掉菜单标题栏 */
iframe#main { margin:0; padding:0; }
</style>
<script>
    function   formatDate(d,userid)   {
        var  now=new Date(d);
        reg=/^\d+$/;
        if((d!='') && (reg.test(d))) {
            var ymd=new Date();
            ymd.setTime(d*1000);
            var year=ymd.getFullYear().toString();
            var month=(ymd.getMonth()+1).toString();
            var date=ymd.getDate().toString();
            var hour=ymd.getHours().toString();
            var minute=ymd.getMinutes().toString();
            var second=ymd.getSeconds().toString();
            //$('#ymd').val(year+'-'+month+'-'+date+' '+hour+':'+minute+':'+second);
            var tmp = document.getElementById(userid);
            tmp.innerHTML=year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
        }
       /* var   year=now.getYear()+1900;
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();
        //doc.innerHTML(year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second);
        var tmp = document.getElementById(userid);
        tmp.innerHTML=year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
        //$("#tda").append("AAAAA");
        //return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;*/
    }
</script>
</head>
<%
    String  realPath1  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);
%>
<body>
	<div id="page">
        <div id="addFrame" style="display: none">
            <form id="addForm" action="/admin/blackList/addBlackList"  method="post">
                <table>
                    <tr>
                        <td>账 号：</td>
                        <td><input type="text" id="passportId_input" name="passportId_input"></td>
                    </tr>
                    <tr>
                        <td>生效时间：</td>
                        <td>
                            <select name="expire_time" id="expire_time">
                                <option value="5" selected="selected">5分钟</option>
                                <option value="60">1小时</option>
                                <option value="120">2小时</option>
                                <option value="360">6小时</option>
                                <option value="1440">1天</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="button" onclick="addBlackList()"  value="提交"/></td>
                    </tr>
                </table>
            </form>
        </div>
		<!-- 引入顶部信息 -->
		<%@ include file="/pages/admin/include_top.jsp"%>
		<div id="pageBd">
			<!-- 引入菜单 -->
			<%@ include file="/pages/admin/include_menu.jsp"%>
            <div id="dialog-message" title="提示" style="display: none">
                <p>
                    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                    修改成功！
                </p>
            </div>
            <div id="dialog-addmessage" title="提示" style="display: none">
                <p>
                    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
                    添加成功！
                </p>
            </div>
            <div id="dialog-confirm" title="警告！" style="display: none">
                <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>确认修改该账户状态？</p>
            </div>
			<div id="pageCanvas" class="canvas">
				<div id="pageCanvasInt" class="canvasInt">
					<div id="pageCrumbs" class="crumbs">
						当前位置：<strong>后台管理</strong>
                        <span class="step">&gt;</span><strong>黑名单管理</strong>
                    </div>
					<h2 id="pageTitle">黑名单管理</h2>


                    <hr>
                    <form id="queryProblemForm" action="/admin/blackList/queryBlackList" method="post">
                        <table>
                            <tr>
                                <td>通行证账号</td>
                                <td>
                                    <div class="input-append">
                                        <input id="passportId" name="passportId" value='${passportId}'  class="span2" type="text">
                                    </div>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<td>昵称</td>--%>
                                <%--<td>--%>
                                    <%--<div class="input-append">--%>
                                        <%--<input id="nickname" name="nickname" class="span2" type="text">--%>
                                    <%--</div>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                            <input id="pageNo" name="pageNo" value="1" type="hidden">
                            <tr>
                                <td>
                                    <span class="button button-main"><button type="submit" onclick="onQueryProblemSubmit();" class="button">开始查询</button></span>
                                    <span class="button button-main"><button type="button" onclick="onDisplayAddFrame();" class="button">新增</button></span>
                                </td>
                            </tr>
                        <table>
                    </form>


                    <hr>
				</div><!-- pageCanvasInt End -->

                <div>

                    <table style="font-size:13px" class="question-table">
                        <thead>
                        <tr style="background-color:#ADDA27;">
                            <th width="6%"><div>用户ID</div></th>
                            <th width="8%"><div>用户类型</div></th>
                            <th width="12%"><div>时效性</div></th>
                            <th width="8%"><div>创建时间</div></th>
                            <th width="8%"><div>修改时间</div></th>
                            <th width="4%"><div>状态</div></th>
                            <th width="4%"><div>设置</div></th>
                        </tr>
                        </thead>
                        <c:forEach items="${page.items}" var="item" varStatus="status">
                        <tr>
                            <td style="text-align:center;">
                                    ${item.userid}
                            </td>
                            <td style="text-align:center;">
                                <c:if test="${item.account_type==0}">未知</c:if>
                                <c:if test="${item.account_type==1}">搜狗</c:if>
                                <c:if test="${item.account_type==2}">搜狐</c:if>
                                <c:if test="${item.account_type==3}">手机</c:if>
                                <c:if test="${item.account_type==4}">外域</c:if>
                                <c:if test="${item.account_type==5}">第三方</c:if>
                                <c:if test="${item.account_type==6}">个性化</c:if>
                            </td>
                            <td style="text-align:center;" id="${item.id}">
                                    <script>formatDate(${item.expire_time},'${item.id}')</script>
                            </td>
                            <td style="text-align:center;">
                                    ${item.create_time}
                            </td>
                            <td style="text-align:center;">
                                    ${item.update_time}
                            </td>
                            <td style="text-align:center;">
                                    <c:if test="${item.status}" var="test">生效</c:if>
                                    <c:if test="${!test}">无效</c:if>
                            </td>
                            <td style="text-align:center;">
                                <c:if test="${test}" ><a onclick="resetValid('${item.id}',false)">置无效</a></c:if>
                                <c:if test="${!test}"><a onclick="resetValid('${item.id}',true)">置有效</a></c:if>
                            </td>
                        </tr>
                        </c:forEach>

                     </table>
                     第${page.pageIndex}/${page.maxPage}页  共${page.total}条
                    <a href="javascript:onProblemPage(1);" class="btn_save">首页</a>
                    <a href="javascript:onProblemPage(${page.pageIndex}-1);" class="btn_save"> 上一页</a>
                    <a href="javascript:onProblemPage(${page.pageIndex}+1);" class="btn_save"> 下一页</a>
                    <a href="javascript:onProblemPage(${page.maxPage});" class="btn_save"> 尾页</a>

                </div>
			</div><!-- pageCanvas End -->
		</div><!-- pageBd End -->
	</div><!-- page End -->

    <script type="text/javascript">



        function onQueryProblemSubmit(){
            jQuery("#queryProblemForm").submit();
        }


        function onProblemPage(pageNum){
            if(pageNum<=0){
                return;
            }
            if(pageNum>${page.maxPage}){
                return;
            }
            document.getElementById('passportId').value = '${passportId}';
            document.getElementById('pageNo').value = pageNum;

            jQuery("#queryProblemForm").submit();
        }


        function onDisplayAddFrame(){
            $("#addFrame").dialog();
        }

        function addBlackList(){
            $.post("/admin/blackList/addBlackList",
                    {
                        passportId_input: $("#passportId_input").val(),
                        expire_time:$("#expire_time").val()
                    },
                    function(data){
                        var json = JSON.parse(data)
                        if(json.success){
                            $("#addFrame").dialog('close');
                            $( "#dialog-addmessage" ).dialog({
                                modal: true,
                                height:140,
                                buttons: {
                                    Ok: function() {
                                        $( this ).dialog( "close" );
                                        $("#queryProblemForm").submit();
                                    }
                                }
                            });
                        } else {
                            alert(json.message);
                        }
                    });
        }

        function resetValid(id,valid){
            $( "#dialog-confirm" ).dialog({
                resizable: false,
                height:140,
                modal: true,
                buttons: {
                    "确认": function() {
                        $( this ).dialog( "close" );
                        $.post("/admin/blackList/updateBlackList",
                                {
                                    id: id,
                                    status:valid
                                },
                                function(data){
                                    var json = JSON.parse(data)
                                    if(json.success){
                                        $( "#dialog-message" ).dialog({
                                            modal: true,
                                            height:140,
                                            buttons: {
                                                Ok: function() {
                                                    $( this ).dialog( "close" );
                                                    $("#queryProblemForm").submit();
                                                }
                                            }
                                        });
                                    } else {
                                        alert(json.message);
                                    }
                                });
                    },
                    取消: function() {
                        $( this ).dialog( "close" );
                    }
                }
            });

        }


    </script>
</body>
</html>