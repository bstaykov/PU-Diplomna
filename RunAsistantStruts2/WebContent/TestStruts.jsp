<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>TestStruts.jsp</title>
</head>
<body>
	<s:form action="updateWorkout.action">
      <s:select name="username" label="Username"
         list="{'0','1','2','3','00','01','02','03'}"
         value="#session.context" />

      <s:select label="Company Office" name="mySelection"
         value="%{'America'}"
         list="%{#{'America':'America'}}">
      <s:optgroup label="Asia" 
         list="%{#{'India':'India','China':'China'}}" />
      <s:optgroup label="Europe"
         list="%{#{'UK':'UK','Sweden':'Sweden','Italy':'Italy'}}" />
      </s:select>

      <s:combobox label="My Sign" name="mySign"
         list="#{'aries':'aries','capricorn':'capricorn'}"
         headerKey="-1" 
         headerValue="--- Please Select ---" emptyOption="true"
         value="capricorn" />
      <s:doubleselect 
       		label="Occupation" 
       		name="occupation"
         	list="{'Technical','Other'}" 
         	doubleName="occupations2"
         	doubleList="top == 'Technical' ? {'I.T', 'Hardware'} : {'Accounting', 'H.R'}" />

   </s:form>
</body>
</html>