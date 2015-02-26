<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/mainRun.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>Registration form</title>
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
		<s:form 
			action="editProfile"
			method="post" 
			theme="qxhtml" 
			enctype="multipart/form-data" 
			onsubmit="return checkSize(108000)">
			<s:if test="%{updated != null}">
				<tr>
					<th colspan="2"><s:property value="updated" /></th>
				</tr>
			</s:if>
			<tr>
				<td colspan="2">
					<img class="profile-image" src="images/storage/<s:property value="#session.ProfilePicUrl"/>.jpg" alt="picture" >
				</td>
			</tr>
			<s:textfield 
				name="firstName"
				maxlength="25"
				label="First name" 
				required="true"
				pattern="^[a-zA-Z]{4,25}"
				placeholder="First name">
			</s:textfield>
			<s:textfield 
				name="lastName" 
				maxlength="25"
				label="Last name" 
				required="true"
				pattern="^[a-zA-Z]{4,25}"
				placeholder="Last name">
			</s:textfield>
			<s:textfield 
				name="city"
				maxlength="25"
				label="City" 
				required="true" 
				cssClass="left-text"
				pattern="^[a-zA-Z\s]{4,25}"
				placeholder="City">
			</s:textfield>					
			<s:textfield 
				type="text"
				name="age"
				placeholder="0..129"
				maxlength="3" 
				pattern="^([1-9]|[1-9][0-9]|[1][0-2][0-9])$" 
				label="Age"
				required="true">
			</s:textfield>
			<s:file 
				name="myFile" 
				label="Profile pic"
				cssClass="textOverflow"
				/>	
			<s:select 
				label="Gender"
       			name="gender"
       			headerKey="men" headerValue="Male"
       			list="%{#{'women':'Female'}}"
       			required="true"/>
			<s:textfield 
				name="password" 
				maxlength="25"
				label="Password" 
				type="password" 
				required="true"
				pattern="^[a-zA-Z0-9]{4,25}"
				placeholder="Password">
			</s:textfield>
			<tr>
				<td id="f5" colspan="2">
					<input type="submit" value="Edit profile"> 
					<input type="reset" value="Reset Form">
				</td>
			</tr>
		</s:form>
	</div>
	
	<script>
    function checkSize(max_img_size)
    {
        var input = document.getElementById("editProfile_myFile");
        if(input.files && input.files.length == 1)
        {           
            if (input.files[0].size > max_img_size) 
            {
                alert("Picture must be less than 100MB");
                return false;
            }
        }
        return true;
    }
    </script>
</body>
</html>