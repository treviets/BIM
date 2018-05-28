<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />
<!DOCTYPE html>
<html lang="en">
<head>
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

<link rel="stylesheet"
	href="${contextPath}/static/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath}/static/css/style.css" />

</head>
<body>
	<div class="login-panel">
		<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12 text-center login-page-title">
			<h2 class="login-title"><spring:message code="login.title" htmlEscape="false"/></h2>
		</div>
		<div class="clear-both"></div>
		<div class="row">
			<div class="col-md-4 login-form">
				<c:url var="loginUrl" value="/login" />
				<form class="form" role="form" id="loginForm" method="post" action="${loginUrl}" accept-charset="UTF-8">
					<div class="form-group">
						<label class="sr-only"><spring:message code="login.username"/></label> 
						<input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
					</div>
					<div class="form-group">
						<label class="sr-only"><spring:message code="login.password"/></label> 
						<input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox"
							name="_spring_security_remember_me" value="true"
							checked="checked"> <spring:message code="login.remember.me"/>
						</label>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-success btn-block btn-lime">
							<spring:message code="login.submit.button.label"/>
						</button>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
				</form>
				<a href="#" data-toggle="modal" data-target="#myModal"><spring:message code="login.forgot.password"/></a>
				<div class="error-area text-center">
					<c:if test="${not empty param.isShowMessage}">
						<span class="error"><spring:message code="login.error.message"/></span>
					</c:if>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>


