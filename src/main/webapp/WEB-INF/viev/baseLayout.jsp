<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> News management </title>
		<link rel="stylesheet" type="text/css" href="../../styles/newsStyle.css">
	</head>
	
	<body>
		<div class="page">
			<div class="header">
				<c:import url="/WEB-INF/view/header.jsp">
					<c:param  name="header_name" value="${header_name}"/>
				</c:import>
			</div>
	
			<div class="base-layout-wrapper">
				<div class="menu">
					<c:if test="${not (sessionScope.userActivity eq 'active')}">
						<c:out value="${wrapper_welcome}"/>
					</c:if>
					
					<c:if test="${sessionScope.userActivity eq 'active'}">
						<c:import url="/WEB-INF/view/menu.jsp" />
					</c:if>
			</div>
	
			<div class="content">
					<c:choose>
						<c:when test="${not empty sessionScope.error_msg}">
							<c:import url="/WEB-INF/view/error.jsp"/>
						</c:when>
						
						<c:when test="${not (sessionScope.userActivity eq 'active')}">
							<c:import url="/WEB-INF/view/guestBody.jsp" />
						</c:when>
						
						<c:when test="${(sessionScope.userActivity eq 'active')}">
							<c:import url="/WEB-INF/view/body.jsp" />
						</c:when>
					</c:choose>
				</div>
			</div>
	
			<div class="footer">
				<c:import url="/WEB-INF/view/footer.jsp" />
			</div>
		</div>
	</body>
</html>