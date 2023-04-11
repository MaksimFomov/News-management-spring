<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="body-title">
	<a href="controller?command=go_to_news_list">${goback_news} </a> ${addnews_goback_current}
</div>

<div class="add-table-margin">
    <form action="controller" method="post">
        <table class="news_text_format">
            <tr>
                <td class="space_around_title_text">${title}</td>

                <td class="space_around_view_text">
                	<div class="word-breaker">
                    	<input type="text" name="news_title" value="<c:out value="${requestScope.news.title }"/>"/>
					</div>
				</td>
            </tr>
            
            <tr>
                <td class="space_around_title_text">${date}</td>

                <td class="space_around_view_text">
                	<div class="word-breaker">
                		<input type="date" name="news_date" value="<c:out value="${requestScope.news.date }"/>"/>
					</div>
				</td>
            </tr>
            
            <tr>
                <td class="space_around_title_text">${brief}</td>
                <td class="space_around_view_text">
                	<div class="word-breaker">
                    	<textarea rows="7" cols="30" name="news_brief" style="resize: none;"><c:out value="${requestScope.news.brief }" /></textarea>
					</div>
				</td>
            </tr>
            
            <tr>
                <td class="space_around_title_text">${content}</td>
                <td class="space_around_view_text">
                	<div class="word-breaker">
                    	<textarea rows="11" cols="30" name="news_content" style="resize: none;"><c:out value="${requestScope.news.content }" /></textarea>
                    </div>
                </td>
            </tr>
        </table>

        <div class="first-view-button">
            <!-- form -->
            <input type="hidden" name="command" value="do_add_news" /> <input
                type="hidden" name="id" value="${news.id}" /> <input
                type="submit" value="${save}" />
        </div>
    </form>
    
    <div class="second-view-button">
        <form action="controller">
            <input type="hidden" name="command" value="go_to_news_list" /> <input
                type="hidden" name="id" value="${news.id}" /> <input
                type="submit" value="${cancel}" />
        </form>
    </div>
</div>
