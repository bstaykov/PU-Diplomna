<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/index.css">
<title>Interceptor's Testing</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp">Login page</a>
	<a href="<%=request.getContextPath()%>/goToMyWorkouts.action">My
		Workouts</a>
	<a href="<%=request.getContextPath()%>/goInsertPageAction.action">Insert
		workout </a>
	<a href="<%=request.getContextPath()%>/linkSetTargets.action">Set
		Targets</a>
	<a href="<%=request.getContextPath()%>/registerForm.jsp">Register</a>
	<s:if test="%{#session.USERNAME != null}">
		<a href="<%=request.getContextPath()%>/logout2">Logout</a>
	</s:if>
	<a href="<%=request.getContextPath()%>/registerLink"
		style="text-decoration: none;"><input type="button"
		value="Register" /></a>
	<a href="<%=request.getContextPath()%>/linkMyTargets.action">My
		Targets</a>
	<a href="<%=request.getContextPath()%>/logout2"
		style="text-decoration: none;"><input type="button" value="Logout" /></a>
	<hr>

	<h1>PROPERTIES #session.property</h1>
	<h1>
		The attribute of 'ID' in session is:
		<s:property value="#session.ID" />
	</h1>
	<h1>
		The attribute of 'USERNAME' in session is:
		<s:property value="#session.USERNAME" />
	</h1>
</body>
</html>
<!--  -->