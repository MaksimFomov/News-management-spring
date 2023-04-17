<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ResourceBundle" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization" var="loc" />

<!-- common elements -->
<fmt:message bundle="${loc}" key="goback.news" var="goback_news"/>
<fmt:message bundle="${loc}" key="nonews" var="nonews"/>
<fmt:message bundle="${loc}" key="edit" var="edit"/>
<fmt:message bundle="${loc}" key="delete" var="delete"/>
<fmt:message bundle="${loc}" key="title" var="title"/>
<fmt:message bundle="${loc}" key="date" var="date"/>
<fmt:message bundle="${loc}" key="brief" var="brief"/>
<fmt:message bundle="${loc}" key="content" var="content"/>
<fmt:message bundle="${loc}" key="save" var="save"/>
<fmt:message bundle="${loc}" key="cancel" var="cancel"/>

<!-- baseLayout.jsp-->
<fmt:message bundle="${loc}" key="wrapper.welcome" var="wrapper_welcome"/>

<!-- header.jsp-->
<fmt:message bundle="${loc}" key="header.name" var="header_name"/>
<fmt:message bundle="${loc}" key="header.en" var="button_en"/>
<fmt:message bundle="${loc}" key="header.ru" var="button_ru"/>
<fmt:message bundle="${loc}" key="header.logination.login" var="header_logination_login"/>
<fmt:message bundle="${loc}" key="header.logination.password" var="header_logination_password" />
<fmt:message bundle="${loc}" key="header.registration.link" var="header_registration_link" />
<fmt:message bundle="${loc}" key="header.en" var="header_en" />
<fmt:message bundle="${loc}" key="header.ru" var="header_ru"/>
<fmt:message bundle="${loc}" key="header.signin" var="header_signin" />
<fmt:message bundle="${loc}" key="header.signout" var="header_signout"/>

<!-- footer.jsp-->
<fmt:message bundle="${loc}" key="footer.name" var="footer_name"/>

<!-- guestInfo.jsp -->
<fmt:message bundle="${loc}" key="guestinfo.head" var="guestinfo_head" />
<fmt:message bundle="${loc}" key="guestinfo.goback.current" var="guestinfo_goback_current" />

<!-- menu.jsp -->
<fmt:message bundle="${loc}" key="menu.title" var="menu_title"/>
<fmt:message bundle="${loc}" key="list.news" var="list_news"/>
<fmt:message bundle="${loc}" key="list.add" var="list_add"/>

<!-- newsList.jsp -->
<fmt:message bundle="${loc}" key="newslist.goback.current" var="newslist_goback_current"/>
<fmt:message bundle="${loc}" key="newslist.view" var="newslist_view"/>

<!-- editNews.jsp -->
<fmt:message bundle="${loc}" key="editnews.goback.current" var="editnews_goback_current"/>

<!-- addNews.jsp -->
<fmt:message bundle="${loc}" key="addnews.goback.current" var="addnews_goback_current"/>

<!-- viewNews.jsp-->
<fmt:message bundle="${loc}" key="viewnews.goback.current" var="viewnews_goback_current"/>

<!-- error.jsp -->
<fmt:message bundle="${loc}" key="error.goback" var="error_goback"/>
<fmt:message bundle="${loc}" key="error.message" var="error_message"/>

<!-- auth_error -->
<fmt:message bundle="${loc}" key="auth.error.message" var="auth_error_message"/>

<!-- success messages -->
<fmt:message bundle="${loc}" key="save.success" var="save_success_message"/>
<fmt:message bundle="${loc}" key="register.success" var="register_success_message"/>
<fmt:message bundle="${loc}" key="delete.success" var="delete_success_message"/>

<!-- error messages -->
<fmt:message bundle="${loc}" key="register.error" var="register_error_message"/>
<fmt:message bundle="${loc}" key="register.invalid.values" var="register_invalid_values_message"/>
<fmt:message bundle="${loc}" key="add.edit.news.invalid.values" var="add_edit_news_invalid_values_message"/>