<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/mainRun.css"
	rel="stylesheet" type="text/css" />
<title>Run</title>
</head>
<body>
	<header>
		<s:if test="%{#session.NAMES != null}">
			<ul class="menu clearfix">
				<li>
					<a href="<%=request.getContextPath()%>/index.jsp">Login page</a>
				</li>
				<li>Workouts
					<ul>
						<li>
							<a href="<%=request.getContextPath()%>/goToMyWorkouts.action">My Workouts</a>
						</li>
						<li>
							<a href="<%=request.getContextPath()%>/goInsertPageAction.action">Insert workout</a>
						</li>
						<li>
							<a href="<%=request.getContextPath()%>/linkChronometer.action">Chronometer</a>
						</li>
					</ul>
				</li>
				<li>Targets
					<ul>
						<li>
							<a href="<%=request.getContextPath()%>/linkSetTargets.action">Set Targets</a>
						</li>
						<li>
							<a href="<%=request.getContextPath()%>/linkMyTargets.action">My Targets</a>
						</li>
					</ul>
				</li>
				<li>Ranking
					<ul>
						<li>
							<a href="<%=request.getContextPath()%>/allTimesPositions.action">All times</a> 
						</li>
						<li>
							<a href="<%=request.getContextPath()%>/monthlyPositions.action">Last month</a> 
						</li>
					</ul>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/linkEditProfile.action">Profile</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/linkCompetitions.action">Competitions</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/interestingFacts.action">Charts</a>
				</li>
				<li>
					<a href="<%=request.getContextPath()%>/logout2"
						onclick="return confirm('Are you sure you want to Logout?')">
						Logout
					</a>
				</li>
			</ul>
		</s:if>
	</header>
	<div>
		
			<s:if test="%{#session.NAMES != null}">
				<ul class="menu clearfix logoutTable ul-border">
					<li>
						<span>
								<a class="edit-profile" href="<%=request.getContextPath()%>/linkEditProfile.action"
									title="My Profile">
									<s:property value="#session.NAMES" /> 
								</a>
								<img class="profile-pic" src="images/storage/<s:property value="#session.ProfilePicUrl"/>.jpg" alt="picture" >
								<a href="<%=request.getContextPath()%>/logout2"
									onclick="return confirm('Are you sure you want to Logout?')">
								Logout
							</a>
							</span></li>
					</ul>
			</s:if>
	</div>
		
	<br />
	<div>
		<s:if test="%{#session.NAMES == null}">
			<s:bean name="java.util.HashMap" id="qTableLayout">
				<s:param name="tablecolspan" value="%{2}" />
			</s:bean>
			<s:form action="loginTestInterceptor" method="post" theme="qxhtml">
				<s:textfield 
					name="username" 
					label="Username" 
				
					value="user1"
				
					placeholder="user1"
					maxlength="25"
					required="required">
				</s:textfield>
				<s:textfield 
					name="password" 
					label="Password" 
					value="pass"
					maxlength="25" 
					type="password"
					required="required">
				</s:textfield>
				<tr>
					<td id="f5" colspan="2">
						<s:if test="%{#session.NAMES == null}">
							<input type="submit" value="Login">
							<input type="reset" value="Reset Form">
							<a href="<%=request.getContextPath()%>/registerLink.action"
								style="text-decoration: none;">
								Register
							</a>
						</s:if> 
						<s:if test="%{#session.NAMES != null}">
							<a href="<%=request.getContextPath()%>/logout2" style="text-decoration: none;">
								<input type="button" value="Logout" />
							</a>
						</s:if>
					</td>
				</tr>
			</s:form>
			<h2>
				<s:property value="login" />
			</h2>
			<br>
		</s:if>
	</div>
</body>
</html>