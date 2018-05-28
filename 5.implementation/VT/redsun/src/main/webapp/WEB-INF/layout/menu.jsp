<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>
<c:set var="fullPath" value="<%= request.getRequestURI() %>"/>
<sec:authentication var="principal" property="principal" />

	<div class="menu-space"></div>
	<div class="sidebar-nav">
		<div class="navbar-style navbar navbar-default" role="navigation">
			<div class="navbar-collapse collapse sidebar-navbar-collapse">
				<ul class="nav navbar-nav">
					<c:forEach items="${principal.listMenus}" var="menu">
						<li class='col-lg-12 <c:if test="${fn:contains(fn:trim(fullPath),fn:trim(menu.url))}">selected</c:if>' ><a href="${menu.url }" target="_self"><span class="${menu.iconClass }"></span> ${menu.description }</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
