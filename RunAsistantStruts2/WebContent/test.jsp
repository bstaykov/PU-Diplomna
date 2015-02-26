<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/index.css">
<title>Run</title>
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
	<a href="<%=request.getContextPath()%>/Check.jsp">Check jsp</a>
	<s:if test="%{#session.USERNAME != null}">
		<a href="<%=request.getContextPath()%>/logout2">Logout2</a>
		<a href="<%=request.getContextPath()%>/logoutUserAction.action">Logout</a>

	</s:if>
	<a href="<%=request.getContextPath()%>/registerLink.action"
		style="text-decoration: none;"><input type="button"
		value="Register" /></a>
	<a href="<%=request.getContextPath()%>/logout2">Logout2</a>
	<a href="<%=request.getContextPath()%>/logoutUserAction.action">Logout</a>

	<hr>

	<h1>PROPERTIES #session.property</h1>
	<h1>
		The attribute of 'Date' in session is:
		<s:property value="#session.Date" />
	</h1>
	<h1>
		The attribute of 'ID' in session is:
		<s:property value="#session.ID" />
	</h1>
	<h1>
		The attribute of 'USERNAME' in session is:
		<s:property value="#session.USERNAME" />
	</h1>
	<h1>
		The attribute of 'PASSWORD' in session is:
		<s:property value="#session.PASSWORD" />
	</h1>
	<h1>
		The attribute of 'ROLE' in session is:
		<s:property value="#session.ROLE" />
	</h1>

	<hr>
	<div>
		<table class="logInfo" cellspacing="0" cellpadding="3" border="1">
			<colgroup>
				<col style="width: 50%">
				<col style="width: 50%">
			</colgroup>
			<tbody>
				<tr>
					<th><label>User Name</label></th>
					<td class="left-text" colspan="3"><input type="text"
						value=<s:property value="#session.USERNAME" />></td>
				</tr>
				<tr>
					<th><label>Password</label></th>
					<td class="left-text" colspan="3"><input type="text"
						value=<s:property value="#session.PASSWORD" />></td>
				</tr>
				<tr>
					<td id="f5" colspan="2"><a
						href="<%=request.getContextPath()%>/logout2"
						style="text-decoration: none;"><input type="button"
							value="Logout" /></a>
				</tr>
			</tbody>
		</table>
	</div>
	<hr>
	<s:property value="insertedData" />
	<a href="<%=request.getContextPath()%>/goInsertPageAction.action">Insert
		workout </a>
	<br />
	<s:if test="%{#session.USERNAME != null}">
		<a href="<%=request.getContextPath()%>/logout2">Logout2</a>
		<br />
	</s:if>
	<a href="<%=request.getContextPath()%>/workouts/insertWorkouts.jsp">Insert
		workout link</a>
	<br />
	<a href="<%=request.getContextPath()%>/goToMyWorkouts.action">My
		Workouts</a>
	<br />
	<a href="<%=request.getContextPath()%>/index.jsp">Login page</a>
	<br />
	<a href="<%=request.getContextPath()%>/registerForm.jsp">Register</a>
	<br />
</body>
</html>
<!--  -->