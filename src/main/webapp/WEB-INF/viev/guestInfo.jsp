<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

${guestinfo_head}

<div class="body-title">
	<a href="">${goback_news} </a> ${guestinfo_goback_current}
</div>

<form action="command.do?method=delete" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news.title}" />
				</div>
				
				<div class="news-date">
					<c:out value="${news.date}" />
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
			</div>
		</div>
	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
			${nonews}
		</c:if>
	</div>
</form>
