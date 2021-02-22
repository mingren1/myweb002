<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error/error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <meta http-equiv="Refresh" content="3;URL=${APP_PATH }/index.htm"> --%>
<title>Insert title here</title>
</head>
<body>
	欢迎大家访问我们的网站
	<jsp:forward page="/cart/index.htm"></jsp:forward>
	<%-- 
${pageContext.request.contextPath }<br>
${APP_PATH }
<br>

<a href="http://localhost:8090/atcrowdfunding-main/a.jsp">xxx1</a><br>
<a href="${APP_PATH }/a.jsp">xxx2</a><br>
<a href="../../a.jsp">xxx3</a><br>



<jsp:include page="/a.jsp"></jsp:include>
<jsp:forward page="/a.jsp"></jsp:forward>


 --%>
</body>
</html>