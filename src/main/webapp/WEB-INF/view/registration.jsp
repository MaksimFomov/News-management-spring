<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>
<html>
	<head>
	    <title></title>
	</head>
	
	<body>
		<div class="body-title">
		    <a href="/homePage">${goback_news} </a> ${header_registration_link}
		</div>

		<div class="add-table-margin">
			<form:form method="POST" action="/registration"
					   modelAttribute="user">
				<form:label path="login">${header_logination_login}</form:label>
				<form:input path="login" />
				<br/>
				<form:label path="password">${header_logination_password}</form:label>
				<form:input type="password" path="password" />
				<br/>
				<input type="submit" value="${save}" />
			</form:form>
		</div>
	</body>
</html>
