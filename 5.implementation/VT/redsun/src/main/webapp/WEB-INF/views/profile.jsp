<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="profileController" class="ng-scope">
	<div class="col-lg-12 padding-top">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<div class="panel-title pull-left">
					<spring:message code="profile.title" />
				</div>
			</div>
			<div class="panel-body">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#changepass" data-toggle="tab"><spring:message
								code="profile.tab.change.password" /></a></li>
				</ul>
				<br />
				<div class="tab-content">
					<div id="changepass" class="tab-pane active">
						<div class="row">
							<div class="row">{{currentPasswordError}}
								<label class="col-lg-2 control-label" for="currentpassword"><spring:message
										code="profile.form.current.password" /></label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="password" ng-model="currentPassword" required>
								</div>
							</div>
							<br/>
							<div class="row">
								<label class="col-lg-2 control-label" for="newpassword"><spring:message
										code="profile.form.new.password" /></label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="newpassword" ng-model="newPassword" required>
								</div>
							</div>
							<br/>
							<div class="row">{{confirmPasswordError}}
								<label class="col-lg-2 control-label" for="confirmPassword"><spring:message
										code="profile.form.confirm.password" /></label>
								<div class="col-lg-10">
									<input type="password" class="form-control" id="confirmPassword" ng-model="confirmPassword" required>
								</div>
							</div>
							<br/>
							<div class="col-lg-offset-2 col-lg-10 text-center">
								<button type="button" class="btn btn-info" ng-click="changePassword();">
									<span class="glyphicon glyphicon-floppy-save"></span>
									<spring:message code="system.users.button.save" />
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>