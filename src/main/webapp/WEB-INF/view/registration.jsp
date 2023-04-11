<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>
<html>
	<head>
	    <title></title>
	</head>
	
	<body>
		<div class="body-title">
		    <a href="controller?command=go_to_news_list">${goback_news} </a> ${header_registration_link}
		</div>
		
		<div class="add-table-margin">
		    <form action="controller" method="post">
		            ${header_logination_login}
		        <input type="text" name="login"/> <br>
		            ${header_logination_password}
		        <input type="password" name="password"/> <br>
		        <input type="hidden" name="command" value="do_registration" />
		        <input type="submit" value="${save}"/>
		    </form>
		</div>
	</body>
</html>
