<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="wrapper">
	<div class="newstitle"> ${header_name} </div> 
	
	<div class="local-link">
		<div align="right">
			<a href="controller?command=do_change_language&local=en"> ${header_en} </a> &nbsp;&nbsp;
			<a href="controller?command=do_change_language&local=ru"> ${header_ru} </a> <br /> <br />
		</div>

		<c:if test="${not (sessionScope.userActivity eq 'active')}">
			<div align="right">
				<form:form method="POST" action="/authorization"
						   modelAttribute="user">
					<form:label path="login">${header_logination_login}</form:label>
					<form:input path="login" />
					<br/>
					<form:label path="password">${header_logination_password}</form:label>
					<form:input path="password" />
					<br/>

					<c:if test="${not (sessionScope.auth_error eq null)}">
						<font color="red">
							<c:out value="${auth_error_message}" />
							<c:remove var="auth_error"/>
						</font>
					</c:if>

					<c:if test="${not (sessionScope.register_success eq null)}">
						<font color="green">
							<c:out value="${register_success_message}" />
							<c:remove var="register_success"/>
						</font>
					</c:if>

					<c:if test="${not (sessionScope.register_error eq null)}">
						<font color="red">
							<c:out value="${register_error_message}" />
							<c:remove var="register_error"/>
						</font>
					</c:if>

					<c:if test="${not (sessionScope.invalid_values_for_register eq null)}">
						<font color="red">
							<c:out value="${register_invalid_values_message}" />
							<c:remove var="invalid_values_for_register"/>
						</font>
					</c:if>

					<a href="/registration">${header_registration_link}</a>
					<input type="submit" value="${header_signin}" /><br />
				</form:form>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.userActivity eq 'active'}">
			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="do_sign_out" /> 
					<input type="submit" value="${header_signout}" /><br />
				</form>

				<c:if test="${not (sessionScope.save_success eq null)}">
					<font color="green">
						<c:out value="${save_success_message}" />
						<c:remove var="save_success"/>
					</font>
				</c:if>
				
				<c:if test="${not (sessionScope.delete_success eq null)}">
					<font color="green">
						<c:out value="${delete_success_message}" />
						<c:remove var="delete_success"/>
					</font>
				</c:if>
				
				<c:if test="${not (sessionScope.add_or_edit_news_error eq null)}">
					<font color="red">
						<c:out value="${add_edit_news_invalid_values_message}" />
						<c:remove var="add_or_edit_news_error"/>
					</font>
				</c:if>
			</div>
		</c:if>
	</div>
</div>