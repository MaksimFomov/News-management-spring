<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="body-title">
	<a href="/newsList">${goback_news} </a> ${addnews_goback_current}
</div>

<div class="add-table-margin">
    <form:form method="POST" action="/addNews"
           modelAttribute="news">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">
                    <form:label path="title">${title}</form:label>
                </td>

                <td class="space_around_view_text">
                	<div class="word-breaker">
                        <form:input path="title" />
					</div>
				</td>
            </tr>
            
            <tr>
                <td class="space_around_title_text">
                    <form:label path="date">${date}</form:label>
                </td>

                <td class="space_around_view_text">
                	<div class="word-breaker">
                        <form:input type="date" path="date" />
					</div>
				</td>
            </tr>
            
            <tr>
                <td class="space_around_title_text">
                    <form:label path="brief">${brief}</form:label>
                </td>
                <td class="space_around_view_text">
                	<div class="word-breaker">
                        <form:textarea rows="7" cols="30" path="brief" style="resize: none;"/>
					</div>
				</td>
            </tr>
            
            <tr>
                <td class="space_around_title_text">
                    <form:label path="content">${content}</form:label>
                </td>
                <td class="space_around_view_text">
                	<div class="word-breaker">
                    	<form:textarea rows="11" cols="30" path="content" style="resize: none;"/>
                    </div>
                </td>
            </tr>
        </table>

        <div class="first-view-button">
            <input type="submit" value="${save}" />
        </div>
    </form:form>
    
    <div class="second-view-button">
        <form action="/newsList">
            <input type="submit" value="${cancel}" />
        </form>
    </div>
</div>
