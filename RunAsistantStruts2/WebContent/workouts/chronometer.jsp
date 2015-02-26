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
<script>
	var hours = 0;
	var minutes = 0;
	var seconds = 0;
	var timer;
	var startTime;
	var firstCycle = true;
	var isStoped = true;
	var isPaused = false;
	var pauseStart;
	var TIME_ZONE_DIFFERENCE;
	function startTimer() {
		
		if(!isStoped && !isPaused){
			console.log('!isStoped && !isPaused');
			return;
		}
		
		if(isPaused){
			var timeDiference = new Date().getTime() - pauseStart.getTime();
			var pausedTime = new Date();
			pausedTime.setTime(timeDiference);
			pausedTime.setHours(pausedTime.getHours() - TIME_ZONE_DIFFERENCE, pausedTime.getMinutes(), pausedTime.getSeconds(), pausedTime.getMilliseconds());						
			var startTimeAddPausedTime = pausedTime.getTime() + startTime.getTime();
			startTime.setTime(startTimeAddPausedTime);
			startTime.setHours(startTime.getHours() + TIME_ZONE_DIFFERENCE, startTime.getMinutes(), startTime.getSeconds(), startTime.getMilliseconds());
			timer = setInterval(setTime, 1000);
			isPaused = false;
			return;
		}
		
		if(isStoped){
			startTime = new Date();
			isStoped = false;
		}
		isStoped = false;
		timer = setInterval(setTime, 1000);		
	}
	function pauseTimer() {
		if(isStoped || isPaused){
			return;
		}
		isPaused = true;
		pauseStart = new Date();
		clearInterval(timer);
		insertValues();
	}
	
	function resetTimer(){
		clearChronometer();
		clearTimer();
		}

	function stopTimer(){
		if(isStoped){
			return;
		}
		insertValues();
		clearChronometer();
	}
	
	function clearTimer(){
		document.getElementById("timer").innerHTML = '00 : 00 : 00';
	}
	
	function insertValues(){
		/* document.getElementById("insertWorkout_hours").value = hours;
		document.getElementById("insertWorkout_minutes").value = minutes;
		document.getElementById("insertWorkout_seconds").value = seconds; */
	}
	
	function setTime() {
		var time = new Date();
		var timeDiference = time.getTime() - startTime.getTime();
		time.setTime(timeDiference);
		
		if(firstCycle){
			firstCycle = false;
			TIME_ZONE_DIFFERENCE = time.getHours();
		}
		
		hours = time.getHours() - TIME_ZONE_DIFFERENCE;
		if(hours === 40){
			stopTimer();
			return;
		}
		minutes = time.getMinutes();
		seconds = time.getSeconds();
		var displayHours = hours < 10 ? ('0' + hours) : hours;
		var displayMinutes = minutes < 10 ? ('0' + minutes) : minutes;
		var displaySeconds = seconds < 10 ? ('0' + seconds) : seconds;
		document.getElementById("timer").innerHTML = displayHours + " : " + displayMinutes + " : " + displaySeconds;
	}
	
	function clearChronometer(){
		clearInterval(timer);
		isPaused = false;
		isStoped = true;
		
	}
	
	function getHours(){
		console.log('getHours() = ' + hours);
		return hours;
	}
	function getMinutes(){
		return minutes;
	}
	function getSeconds(){
		console.log('getSeconds() = ' + seconds);
		//return seconds;
	}
	
	function assignValues(){
	    document.getElementById("seconds").value = seconds;
	    document.getElementById("minutes").value = minutes;
	    document.getElementById("hours").value = hours;
	}
	
	</script>
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
	<div id="timer">00 : 00 : 00
	</div>
	<div id="controls">
		<a class="activate-stop" href="#" onclick="startTimer()">Start</a>
		<a class="activate-stop" href="#" onclick="pauseTimer()">Pause</a>
		<a class="activate-stop" href="#" onclick="stopTimer()">STOP</a>
		<a class="activate-stop" href="#" onclick="resetTimer()">Reset</a>
		<s:form action="goInsertPageAction" method="post">
			<s:hidden id="seconds" name="seconds" value=""/>
			<s:hidden id="minutes" name="minutes" value=""/>
			<s:hidden id="hours" name="hours" value=""/>
			<br>				
			<input type="submit" value="Save" onclick="stopTimer(); assignValues();">
		</s:form>
	</div>
		
</body>
</html>

