<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/index.css">
<title>LogIn Check</title>
</head>
<body>
	<s:if test="#session.login != 'true'">
		<jsp:forward page="<%=request.getContextPath()%>/login.jsp" />
	</s:if>


</body>
</html>



