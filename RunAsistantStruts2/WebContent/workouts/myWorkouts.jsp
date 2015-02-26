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
		<table class="mainTable">
			<colgroup>
				<col style="width: 5%">
				<col style="width: 14%">
				<col style="width: 13%">
				<col style="width: 8%">
				<col style="width: 10%">
				<col style="width: 21%">
				<col style="width: 11%">
				<col style="width: 8%">
				<col style="width: 8%">
			</colgroup>
			<tr>
				<th colspan="9">
					Total workouts: ${workoutsCount}
				</th>
			</tr>
			<tr>
				<th>№</th>
				<th>Date</th>
				<th>Place</th>
				<th>KM</th>
				<th>Time</th>
				<th>Pace</th>
				<th>km/h</th>
				<th>Delete</th>
				<th>Edit</th>
			</tr>

			<s:iterator status="stat" value="myWorkouts" var="item">
				<tr>
					<td><s:property value="#stat.count" /></td>
					<td>${item.date}</td>
					<td>${item.place}</td>
					<td>${item.distanceFloat}</td>
					<td>${item.time}</td>
					<td>${item.pace}</td>
					<td>${item.kmHour}</td>
					<td><s:a namespace="/workouts" action="deleteWorkOut"
							onclick="return confirm('Are you sure you want to delete this?')">
							<s:param name="wo_id" value="#item.wo_id" />
							<img src="${pageContext.request.contextPath}/images/delete.png"
								border="0" alt="delete" />
						</s:a></td>
					<td><s:a namespace="/workouts" action="getEditWorkout"
							onclick="return confirm('Are you sure you want to edit this?')">
							<s:param name="date" value="#item.date" />
							<s:param name="place" value="#item.place" />
							<s:param name="distance" value="#item.distanceFloat" />
							<s:param name="time" value="#item.time" />
							<s:param name="wo_id" value="#item.wo_id" />
							<img src="${pageContext.request.contextPath}/images/stylo.png"
								border="0" alt="delete" />
						</s:a></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<br />
	<div>
		<table class="statistic">
			<colgroup>
				<col style="width: 45%">
				<col style="width: 55%">
			</colgroup>
			<tr>
				<th>Total km</th>
				<td><s:property value="totalKM" /> km</td>
			</tr>
			<tr>
				<th>Total time</th>
				<td><s:property value="totalTime" /></td>
			</tr>
			<tr>
				<th>Average speed</th>
				<td><s:property value="averageTime" /> km/h</td>
			</tr>
			<tr>
				<th>Best 5km</th>
				<td><s:property value="fiveKM" /></td>
			</tr>
			<tr>
				<th>Best 10km</th>
				<td><s:property value="tenKM" /></td>
			</tr>
		</table>
	</div>
	<br />
</body>
</html>