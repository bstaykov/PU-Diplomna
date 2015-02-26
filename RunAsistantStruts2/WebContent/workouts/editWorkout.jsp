<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Workouts page</title>
<link href="${pageContext.request.contextPath}/css/mainRun.css"
	rel="stylesheet" type="text/css" />
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
	<div>
		<s:bean name="java.util.HashMap" id="qTableLayout">
			<s:param name="tablecolspan" value="%{2}" />
		</s:bean>
		<s:form action="updateWorkout" method="post" theme="qxhtml">
			<tr>
				<th colspan="2">Distance</th>
			</tr>
			<s:textfield 
				name="km" 
				maxlength="3" 
				label="KM" 
				pattern="^([0-9]|[1-9][0-9]|[1-9][0-9][0-9])$"
				placeholder="1...999 km"
				required="required">
			</s:textfield>
			<s:textfield 
				name="meters" 
				maxlength="3" 
				label="Meters"
				pattern="^([0-9]|[1-9][0-9]|[1-9][0-9][0-9])$"
				placeholder="1...999 m"
				required="required">
			</s:textfield>
			<tr>
				<th colspan="2">Time</th>
			</tr>
			<s:select 
				name="hours" 
				label="Hours" 
				value="hours"
				list="{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39}"
				required="required" />
			<s:select 
				name="minutes"
				value="minutes" 
				label="Minutes"
				list="{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40, 41, 42, 43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59}"
				required="required" />
			<s:select 
				name="seconds" 
				label="Seconds" 
				value="seconds"
				list="{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40, 41, 42, 43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59}"
				required="required" />
			<tr>
				<th colspan="2">Place</th>
			</tr>
			<s:textfield 
				name="place" 
				label="City"
				maxlength="25"
				pattern="^[a-zA-Z0-9\\s]+$"
				placeholder="city/place"
				required="required">
			</s:textfield>
			<tr>
				<th colspan="2">Date</th>
			</tr>
			<s:select 
				name="day" 
				label="Day"  
				value="day"
				list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}"
				required="required" />
			<s:select 
				name="month" 
				label="Month" 
				value="month"
				list="%{#{'1':'January', '2':'February', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', '8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}}"
				required="required" />
			<s:select 
				name="year" 
				label="Year" 
				value="year"
				list="{2013, 2014, 2015}" 
				required="required"/>
			<tr>
				<td colspan="2">
					<input type="submit" value="Edit"> 
					<input type="reset" value="Reset Form"> 
					<input type="hidden" name="wo_id" value=<s:property value="wo_id"/>>
				</td>
			</tr>
		</s:form>
	</div>
</body>
</html>