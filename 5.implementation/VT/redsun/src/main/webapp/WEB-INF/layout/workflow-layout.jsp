<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />
<!DOCTYPE html>
<html>
	<head>
		<title>AngularJS-FlowChart</title>
		<link rel="stylesheet" href="${contextPath}/static/lib/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${contextPath}/static/css/style.css" />

	</head>	
	<body 
		ng-app="app" 
		ng-controller="AppCtrl"
		mouse-capture
		ng-keydown="keyDown($event)"
		ng-keyup="keyUp($event)"
		>

		<!--header page-->
		<header>
			<div class="col-lg-12 header-menu">
				<span class="redsub-logo"> <img src="${contextPath}/static/images/logo.png"></span>
				<div class="navbar-right btn-group" id="bs-example-navbar-collapse-1">	
					<button type="button" class="btn btn-link btn-xs" ng-click="customUrl('en')">
						<span><img src="${contextPath}/static/images/usa.png"></span>
					</button>
					<button type="button" class="btn btn-link btn-xs" ng-click="customUrl('vn')">
						<span><img src="${contextPath}/static/images/vn.png"></span>
					</button>
				</div>
			</div>
		</header>
		<div class="clearfix"></div>
		<!--Content page-->
		<div class="col-sm-2 sidebar-wrapper" id="menuList">
			<%@ include file="menu.jsp"%>
		</div>
		<div class="col-sm-10 main-content-wrapper" id="mainBody">
			<decorator:body />
		</div>
		<!--End page-->
	
		<!--footer page-->
		<footer>
			<div class='clearfix'></div>
			<%@ include file="footer.jsp"%>
		</footer>
		

		<link rel="stylesheet" type="text/css" href="${contextPath}/static/workflow/app.css">

		<!-- Library code. -->
		<script src="${contextPath}/static/workflow/lib/jquery-2.0.2.js" type="text/javascript"></script>
		<script src="${contextPath}/static/workflow/lib/angular.js" type="text/javascript"></script>

		<!-- Flowchart code. -->
		<script src="${contextPath}/static/workflow/debug.js" type="text/javascript"></script>
		<script src="${contextPath}/static/workflow/flowchart/svg_class.js" type="text/javascript"></script>
		<script src="${contextPath}/static/workflow/flowchart/mouse_capture_service.js" type="text/javascript"></script>
		<script src="${contextPath}/static/workflow/flowchart/dragging_service.js" type="text/javascript"></script>
		<script src="${contextPath}/static/workflow/flowchart/flowchart_viewmodel.js" type="text/javascript"></script>
		<script src="${contextPath}/static/workflow/flowchart/flowchart_directive.js" type="text/javascript"></script>

		<!-- App code. -->
		<script src="${contextPath}/static/workflow/app.js" type="text/javascript"></script>
	</body>
</html>