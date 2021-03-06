<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/mainRun.css"
	rel="stylesheet" type="text/css" />
<title>Insert Workouts</title>
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
		<s:form action="insertWorkout" method="post" theme="qxhtml">
			
			<tr>
				<th colspan="2">Distance</th>
			</tr>
			<s:textfield 
				name="km" 
				maxlength="3" 
				value="5" 
				label="KM"
				pattern="^([0-9]|[1-9][0-9]|[1-9][0-9][0-9])$"
				placeholder="1...999 km"
				required="required">
			</s:textfield>
			<s:textfield 
				name="meters" 
				maxlength="3" 
				value="0" 
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
				value ="seconds"
				list="{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40, 41, 42, 43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59}"
				required="required" />
			<tr>
				<th colspan="2">Place</th>
			</tr>
			<s:textfield 
				name="place" 
				maxlength="25" 
				value="Sofia" 
				label="City"
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
				list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31}" 
				required="required"/>
			<s:select 
				name="month" 
				label="Month"
				list="%{#{'0':'January', '1':'February', '2':'March', '3':'April', '4':'May', '5':'June', '6':'July', '7':'August', '8':'September', '9':'October', '10':'November', '11':'December'}}"
				required="required" />
			<s:select 
				name="year" 
				label="Year" 
				list="{2013, 2014, 2015}" 
				required="required"/>
			<tr>
				<td colspan="2">
					<input type="submit" value="Save">
					<input type="reset" value="Reset Form">
				</td>
			</tr>
			<s:if test="%{inserted != null}">
				<tr>
					<th colspan="2"><s:property value="inserted" /></th>
				</tr>
			</s:if>
		</s:form>
		<br>
		<h2>
			<s:property value="insertedData" />
		</h2>
		
	</div>	

	<script>
		function setDate() {
			if('<s:property value="km" />' == "") {
				var date = new Date();		
				var actualDay = date.getDate();
				document.getElementById("insertWorkout_day").value = actualDay;
				var actualMonth = date.getMonth();
				document.getElementById("insertWorkout_month").value = actualMonth;			
				var actualYear = date.getFullYear();
				document.getElementById("insertWorkout_year").value = actualYear;
			}
		}
		setDate();
		
		function wrongDataReInsert() {
			if('<s:property value="km" />' != "") {
				document.getElementById("insertWorkout_km").value = '<s:property value="km" />';
				document.getElementById("insertWorkout_meters").value = '<s:property value="meters" />';
				document.getElementById("insertWorkout_hours").value = '<s:property value="hours" />';
				document.getElementById("insertWorkout_minutes").value = '<s:property value="minutes" />';
				document.getElementById("insertWorkout_seconds").value = '<s:property value="seconds" />';
				document.getElementById("insertWorkout_place").value = '<s:property value="place" />';
				document.getElementById("insertWorkout_day").value = '<s:property value="day" />';
				document.getElementById("insertWorkout_month").value = '<s:property value="month" />' - 1;
				document.getElementById("insertWorkout_year").value = '<s:property value="year" />';
			}
		}
		wrongDataReInsert();
	</script>
</body>
</html>

