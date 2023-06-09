<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/view/localization/localizationBase.jsp" %>

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
		      ${menu_title}
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;">
				
				<a href="/newsList">${list_news}</a><br />
				</li>

				<c:if test="${sessionScope.role eq 'ROLE_ADMIN'}">
					<li style="padding-left: 15px;">
					    <a href="/addNews">${list_add}</a>
	                    <br />
					</li>
				</c:if>
			</ul>
		</div>
		
		<div class="clear"></div>
	</div>
	
	<div style="height: 25px;"></div>
</div>

