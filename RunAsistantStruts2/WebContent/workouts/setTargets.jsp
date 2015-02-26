<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/mainRun.css"
	rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>Targets</title>
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
    		<s:param name="tablecolspan" value="%{10}" />
		</s:bean>
		<s:form action="setTargets" method="post" theme="qxhtml">
			<tr>
				<td colspan="10">Targets</td>
			</tr>
			<tr>
				<td colspan="4">5KM</td>
				<td colspan="4">10KM</td>
				<td colspan="2">30 days(KM)</td>
			</tr>
			<s:textfield 
				name="minutesFive" 
				label="Minutes" 
				value="25"
				maxlength="2"
				pattern="^([0-9]|[1-5][0-9])$"
				placeholder="0..59"
				required="required">
			</s:textfield>
			<s:textfield 
				name="secondsFive" 
				label="Seconds" 
				value="0"
				maxlength="2"
				pattern="^([0-9]|[1-5][0-9])$"
				placeholder="0..59"
				required="required">
			</s:textfield>
			<s:textfield 
				name="minutesTen" 
				label="Minutes" 
				value="50"
				maxlength="2"
				pattern="^([0-9]|[1-5][0-9])$"
				placeholder="0..59"
				required="required">
			</s:textfield>
			<s:textfield 
				name="secondsTen" 
				label="Seconds" 
				value="0"
				maxlength="2"
				pattern="^([0-9]|[1-5][0-9])$"
				placeholder="0..59"
				required="required">
			</s:textfield>
			<s:textfield 
				name="km" 
				label="KM" 
				value="100" 
				maxlength="3"
				pattern="^([0-9]|[1-9][0-9]|[1-9][0-9][0-9])$"
				required="required">
			</s:textfield>
			<tr>
				<td colspan="10">
					<input type="submit" value="Set Targets">
				</td>
			</tr>
		</s:form>
	</div>
</body>
</html>