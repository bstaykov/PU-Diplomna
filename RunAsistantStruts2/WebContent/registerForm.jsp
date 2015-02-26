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
	<div>
		<a href="<%=request.getContextPath()%>/index.jsp">Login page</a>
		<hr>
	</div>
	<s:if test="%{#session.USERNAME != null}">								
		<a href="<%=request.getContextPath()%>/logout2">
			<input type="button" value="Logout"/>
		</a>														
	</s:if>
	<div>
		<s:bean name="java.util.HashMap" id="qTableLayout">
			<s:param name="tablecolspan" value="%{2}" />
		</s:bean>
		<s:form 
			action="register"
			method="post" 
			theme="qxhtml" 
			enctype="multipart/form-data" 
			onsubmit="return checkSize(108000)">
			<s:textfield 
				name="username" 
				maxlength="25"
				label="User name" 
				required="true"
				pattern="^[a-zA-Z0-9]{4,25}"
				placeholder="User Name">
			</s:textfield>
			<s:textfield 
				name="password" 
				maxlength="25"
				label="Password" 
				type="password" 
				required="true"
				pattern="^[a-zA-Z0-9]{4,25}"
				placeholder="Password">
			</s:textfield>
			<s:textfield 
				name="copyOfPassword" 
				maxlength="25"
				label="Repeat Password" 
				type="password" 
				required="true"
				pattern="^[a-zA-Z0-9]{4,25}"
				placeholder="Password">
			</s:textfield>
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
				name="email" 
				type="email" 
				placeholder="mail@abv.bg" 
				pattern="[A-Za-z0-9!#$%&'*+-/=?^_`{|}~]+@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*" 
				maxlength="50"
				label="Email" 
				required="true">
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
				/>	
			<s:select 
				label="Gender"
       			name="gender"
       			headerKey="men" headerValue="Male"
       			list="%{#{'women':'Female'}}"
       			required="true"/>
			<tr>
				<td id="f5" colspan="2">
					<input type="submit" value="Register"> 
					<input type="reset" value="Reset Form">
				</td>
			</tr>
		</s:form>
	</div>
	<script>
    function checkSize(max_img_size)
    {
        var input = document.getElementById("register_myFile");
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