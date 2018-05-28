<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />
<!DOCTYPE html>
<html lang="en">
<head>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf_token" content="${_csrf.token}"/>
<meta property="og:title" content="Red Sun" />
<meta property="og:description" content="The BIM Management System" />
<meta property="og:url" content="http://www.redsun.edu.vn/" />
<meta property="og:image" content="http://www.redsun.edu.vn/images/nxp.png" />
<link rel="shortcut icon" href="/redsun/static/images/favicon.ico">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Redsun - BIM Management System</title>

<%@ include file="script.jsp"%>
<%@ include file="style.jsp"%>

</head>
<body ng-app="redsun" ng-controller="commonCtrl" ng-init="commonInit();">
	<!--header page-->
	<header>
		<%@ include file="header.jsp"%>
	</header>
	<div class="clearfix"></div>
	<!--Content page-->
	<div class="col-lg-2 col-md-2 hidden-xs sidebar-wrapper" id="menuList">
		<%@ include file="menu.jsp"%>
	</div>
	<div class="col-lg-10 col-md-10 col-sm-12 col-xs-12 main-content-wrapper" id="mainBody">
		<decorator:body />
		
	</div>
	<!--End page-->

	<!--footer page-->
	<footer>
		<div class='clearfix'></div>
		<%@ include file="footer.jsp"%>
	</footer>
</body>
</html>
