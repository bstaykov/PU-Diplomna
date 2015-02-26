<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/mainRun.css"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>My Workouts page</title>
</head>
<body>
	<header>
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
				<a href="<%=request.getContextPath()%>/linkEditProfile.action">
					Profile
				</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/linkCompetitions.action">Competitions</a>
			</li>
			<li>
				<a href="<%=request.getContextPath()%>/interestingFacts.action">Charts</a>
			</li>
			<s:if test="%{#session.NAMES != null}">
				<li>
					<a href="<%=request.getContextPath()%>/logout2"
						onclick="return confirm('Are you sure you want to Logout?')">
						Logout
					</a>
				</li>
			</s:if>
		</ul>
		
	</header>
	<div>
			<s:if test="%{#session.NAMES != null}">
				<table class="logoutTable">
					<tr>
						<td id="f5">
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
							</span>
						</td>
					</tr>
				</table>
			</s:if>
		</div>
	<br />
	<br />
	<div>
		
		<table class="ranking">
			<colgroup>
				<col style="width: 10%">
				<col style="width: 60%">
				<col style="width: 30%">
			</colgroup>
				<tr>
					<th colspan="3">Monthly Ranking</th>
				</tr>
			<s:iterator status="stat" value="arr" var="item">
				<tr>
					<td>
						<s:property value="#stat.count" />
					</td>
					<td>${item.name}</td>
					<td>${item.km}km</td>
				</tr>
			</s:iterator>
		</table>
		<%-- 
		<hr>
		<ol>
			<s:iterator value="arr" var="item">
  				<li>
  					<span>${item.name}</span> <span>${item.km}km</span>
				</li>
			</s:iterator>
		</ol> --%>
	</div>
</body>
</html>