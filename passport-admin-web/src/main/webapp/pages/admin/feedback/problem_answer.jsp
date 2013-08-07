<%@ page pageEncoding="GBK" contentType="text/html;charset=utf-8"%>
<div id="floatProblemAnswer" style="display:none">
  <div id="problemAnswer">
	   <form id="answerForm" method="post" style="margin: 0;">
           <table align="center" border="0">
               <tr>
                   <TD align="right" >回答内容</td>
                   <td><textarea id="_ansContent" class="span2" name="_ansContent" style="width: 50; height:60;">
                       </textarea>
                   </td>
               </tr>
               <tr>
                   <td>
                       <input type="hidden" id="_problemId" name="_problemId"/>
                       <input type="hidden" id="_email" name="_email"/>
                       <input type="hidden" id="_ansPassportId" name="_ansPassportId"/>

                   </td>
               </tr>
               <tr>
                   <td align="center" height="15" colspan="2">
                      <!-- <input type="submit" name="submit" value="提交" onclick="answerFormSubmit()">-->
                       <a href="javascript:answerFormSubmit();" class="btn_save">发送</a>
                       <!--<input type="button" id="goback" value="返回" onclick=""> -->
                   </td>
               </tr>
           </table>

       </form>
	 </div>
				
 </div>

<script type="text/javascript">
function goback() {
  jQuery("#floatProblemAnswer").css("display","none");
}
</script>