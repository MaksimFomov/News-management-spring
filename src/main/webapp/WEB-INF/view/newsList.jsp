<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="body-title">
	<a href="/newsList">${goback_news} </a> ${newslist_goback_current}
</div>

<form action="/deleteNews" method="post">
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
				
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'ROLE_ADMIN'}">
						      <a href="/editNews?id=${news.id}">${edit} </a>
						</c:if>
						
						<span>&nbsp&nbsp</span>
						
						<a href="/viewNews?id=${news.id}">${newslist_view} </a>
   					    <c:if test="${sessionScope.role eq 'ROLE_ADMIN'}">
   					         <input type="checkbox" name="id" value="${news.id}" />
   					    </c:if>
					</div>
				</div>
			</div>
		</div>
		<hr/>
	</c:forEach>

    <c:if test="${(sessionScope.role eq 'ROLE_ADMIN') and (not (requestScope.news eq null))}">
        <div class="delete-button-position">
            <input type="submit" value="${delete}" />
        </div>
    </c:if>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        ${nonews}
	</c:if>
	</div>
</form>
