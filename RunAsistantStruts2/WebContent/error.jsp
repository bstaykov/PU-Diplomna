<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/mainRun.css"
	rel="stylesheet" type="text/css" />
<title>ERRORS PAGE</title>
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
					<a href="<%=request.getContextPath()%>/logout2"	onclick="return confirm('Are you sure you want to Logout?')">
						Logout
					</a>
				</li>
			</ul>
		</s:if>
	</header>
	<h1>ERROR : Възникна грешка</h1>
	<br />
	<s:property value="noUser" />
	<s:property value="goToLog" />
	<s:property value="UpdateError" />
	<s:property value="insertedData"/>
</body>
</html>