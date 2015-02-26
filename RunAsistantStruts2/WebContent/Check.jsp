<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>check.jsp After login!</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp">Login page</a>
	<a href="<%=request.getContextPath()%>/goToMyWorkouts.action">My Workouts</a>
	<a href="<%=request.getContextPath()%>/goInsertPageAction.action">Insert workout </a>
	<s:a href="test.action">test.jsp</s:a>
	<s:if test="%{#session.USERNAME != null}">
		<a href="<%=request.getContextPath()%>/logout2">Logout2</a>
		<br />
	</s:if>
	<img class="profile-pic" src="images/storage/<s:property value="#session.ProfilePicUrl"/>.jpg" alt="picture" >							
	<hr>
	<h1>PROPERTIES</h1>
	<h1>User: <s:property value="username"/></h1>
	<h1>Password: <s:property value="password"/></h1>
	<h1>Value of ROLE : <s:property value="role" /></h1>
	<h1>Value of ID : <s:property value="id" /></h1>
	<h1>Value of USERNAME : <s:property value="usernameRole" /></h1>
	<h1><s:a href="test.action">Test properties/session...</s:a></h1>
	<br />
	<h1>PROPERTIES #session.property</h1>
	<h1>The attribute of 'logined' in session is: <s:property value="#session.logined" /></h1>
	<h1>The attribute of 'context' in session is: <s:property value="#session.context" /></h1>
	<h1>The attribute of 'ID' in session is: <s:property value="#session.ID" /></h1>
	<h1>The attribute of 'USERNAME' in session is: <s:property value="#session.USERNAME" /></h1>
	<h1>The attribute of 'ROLE' in session is: <s:property value="#session.ROLE" /></h1>
	<br />
		
</body>
</html>
<!--  -->