<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>
<div class="body-title">
	<a href="/newsList">${goback_news}</a> ${viewnews_goback_current}
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${title}</td>
			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="space_around_title_text">${date}</td>

			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${requestScope.news.date }" />
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="space_around_title_text">${brief}</td>
			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${requestScope.news.brief }" />
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="space_around_title_text">${content}</td>
			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div>
			</td>
		</tr>
	</table>
</div>

<c:if test="${sessionScope.role eq 'ROLE_ADMIN'}">
	<div class="first-view-button">
		<a href="/editNews?id=${news.id}">${edit} </a>
	</div>

	<div class="second-view-button">
		<form action="/deleteNews?id=${news.id}" method="post">
			<input type="submit" value="${delete}" />
		</form>
	</div>
</c:if>