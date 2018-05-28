<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />
<sec:authentication var="principal" property="principal" />
<script>
var currentUsername = '${principal.username}';
</script>
<div class="col-lg-12 header-menu" ng-controller="languageCtrl">
	<span class="redsub-logo"> <img ng-src="${contextPath}{{client.logo}}"  width="125" height="127"></span>
	<div class="navbar-right btn-group" id="bs-example-navbar-collapse-1">
		<a href="${contextPath}/profile" target="_self" class="btn btn-link btn-xs">
			<span class="glyphicon glyphicon-user gi-2x" title="<spring:message code="profile.icon.profile"/>"> ${principal.username}</span>
		</a>
		<a href="${contextPath}/logout" target="_self" class="btn btn-link btn-xs">
			<span class="glyphicon glyphicon-log-out gi-2x" title="<spring:message code="profile.icon.logout"/>"></span></a>
		<button type="button" class="btn btn-link btn-xs" ng-click="customUrl('en')">
			<span><img src="${contextPath}/static/images/usa.png"></span>
		</button>
		<button type="button" class="btn btn-link btn-xs" ng-click="customUrl('vn')">
			<span><img src="${contextPath}/static/images/vn.png"></span>
		</button>
	</div>
	<a href="javascript:toggleMenu();" class="btn btn-info btn-sm">
		<span class="glyphicon glyphicon-menu-hamburger"></span>
	</a>
	<ul class="nav navbar-nav sm-menu" id="mobile-nav">
		<c:forEach items="${principal.listMenus}" var="menu">
			<li class='col-lg-12 <c:if test="${fn:contains(fn:trim(fullPath),fn:trim(menu.url))}">selected</c:if>' ><a href="${menu.url }" target="_self"><span class="${menu.iconClass }"></span> ${menu.description }</a></li>
		</c:forEach>
	</ul>
</div>
<script>
	function toggleMenu(){
		 $("#mobile-nav").toggle();
	}
</script>