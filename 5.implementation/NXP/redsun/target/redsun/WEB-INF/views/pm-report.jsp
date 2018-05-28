<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />
<sec:authentication var="principal" property="principal" />

<div ng-controller="pmReportCtrl" class="ng-scope">
	<div class="col-lg-12 padding-top">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
  				<div class="panel-title pull-left glyphicon glyphicon-stats"> <spring:message code="report.title"/></div>
				<div class="pull-right">
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
			</div>
			<div class="panel-body">
				<div class="col-lg-12 select-boxes-group">
					<div class="col-lg-3 category-select">
						<select class="form-control" ng-model="module" ng-change="getProjectType(module)">
							<option value='' disabled selected="selected"><spring:message code="report.module"/></option>
							<c:forEach items="${principal.listMenus}" var="menu">
								<c:if test="${menu.id == 4 || menu.id == 5}">
									<option value="${menu.id}">${menu.description}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
					<div class="col-lg-3 category-select">
						<select class="form-control" ng-model="category" ng-options="cat.name for cat in categories" ng-change="getReports(category.id)">
						</select>
					</div>
					<div class="col-lg-3 category-select">
						<select class="form-control" ng-model="report" ng-options="rep.name for rep in reportsByCategory" ng-change="resetDropDown()">
						</select>
					</div>
					<div class="col-lg-3 category-select">
						<select class="form-control" ng-model="project" ng-options="p.name for p in projects" ng-change="getProject(project)">
							<option value='' disabled selected="selected"><spring:message code="report.project"/></option>
				      	</select>
					</div>
				</div>
				<br/>
				<br/>
				<br/>
				<div class="clearfix">
					<div id="pmreport" ng-include src="reportPanel" class="panel panel-default detail-sub-area" charset="utf-8">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>