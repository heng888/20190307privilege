<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

</head>
<body>
	<!--头部-->
	<%@ include file="top.jsp"%>
	<!--主体内容-->
	<section class="publicMian">
		<%@ include file="left.jsp"%>
		<div class="right">
			<form  action="RolesServlet" method="get">
				<table border="1" style="text-align: center;">
					<tr>
						<td>用户名称</td>
						<td>${username}</td>
					</tr>
					<tr>
						<td>描述</td>
						<td>已具有权限： <c:forEach items="${privilegeList}"
								var="privileges">
	 ${privileges.name}<input type="checkbox" name="privilegedelete" disabled value="${privileges.id}" checked="checked">&nbsp;
	</c:forEach>
						</td>
					</tr>

					<tr>
						<td>系统所有权限列表</td>
						<td> <c:forEach items="${privilegeList}"
										var="privileges">
							${privileges.name}<input type="checkbox" onchange="changestate(this,this.checked)" name="privilegeadd" value="${privileges.id}" checked="checked">&nbsp;
						</c:forEach>
							<c:forEach items="${privilegesList}" var="privileges">
						 ${privileges.name}<input type="checkbox" onchange="changestate(this,this.checked)" name="privilegeadd"	value="${privileges.id}">&nbsp;
						</c:forEach></td>
					</tr>
<tr>
<td colspan="2">
<input type="hidden" name="roleID" id="roleid" value="${roleid}">
<input type="hidden" name="choose" value="3" >
<input type="button" value="授权" onclick="submits()"> </td>

</tr>

				</table>
			</form>

		</div>
	</section>
	<footer class="footer"> 版权归XXXX </footer>
	<script src="../js/time.js"></script>
</body>
<script src="/js/jquery.js"></script>
<script>
	//保证状态与checked保持一致
	function  changestate(th,checked) {
		if(checked){
		    $(th).attr("checked","checked");
		}else{
            $(th).removeAttr("checked");
		}
    }

	function submits() {
	    //准备参数
		var privilegedeletes = $("input[name='privilegedelete']");
        var privilegeadds = $("input[name='privilegeadd']");
        var privilDeletes ="";
        var privilAdds = "";
        $(privilegedeletes).each(function (i) {
			privilDeletes+=this.value+",";
        });
        $(privilegeadds).each(function (i) {
            if(this.checked==true){
                privilAdds+=this.value+",";
			}
        });

        privilDeletes=privilDeletes.substring(0,privilDeletes.length-1);
       	privilAdds = privilAdds.substring(0,privilAdds.length-1);

		$.ajax({
            url:"PrivilegeServlet",
            type:"post",
            dataType:"json",
            data:{
                roleid:$("#roleid").val(),
                privilegedeleteid:privilDeletes,
                privilegeaddid:privilAdds,
                choose:1
            },
            success:function (data) {
                if(data.result==1){
                    alert("授权成功");
                    window.location="RolesServlet?choose=1";
                }else{
                    alert("授权失败")
                }
            }
        })
    }

</script>
</html>