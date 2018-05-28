<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="projectCtrl as projectCtrl" ng-init="initProject(true);">
	<div class="col-lg-12 padding-top">
  		<div class="panel panel-default">
  			<div class="panel-heading clearfix">
  				<form class="navbar-form navbar-left">
	        		<div class="form-group">
	          			<input type="text" ng-model="searchCode" class="form-control input-sm" placeholder=<spring:message code="project.nav.id"/>>
	        		</div> 
	        		<div class="form-group">
	          			<input type="text" ng-model="searchName" class="form-control input-sm" placeholder=<spring:message code="project.nav.name"/>>
	        		</div>
	        		<div class="form-group">
	          			<input type="text" ng-model="searchCustomerName" class="form-control input-sm" placeholder=<spring:message code="project.nav.customer"/>>
	        		</div> 
	        		<button type="button" ng-click="getProjects(1);" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>      
      			</form>
      			<div class="pull-right">
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
  			</div>
  			<div class="project-list panel-body">
  				<div ng-repeat="project in projects" class="project col-sm-3" ng-click="gotoDetailBPMNDetail(this);">
  					<div class="project-title">
  						{{project.name}}
  					</div>
  					<div class="project-description">
  						{{project.description | limitTo: 60}}{{project.description.length > 60 ? '...' : ''}}
  					</div>
  					<div class="project-infos">
  						<span class="glyphicon glyphicon-user col-sm-4">{{project.resource}}</span>
  						<span class="glyphicon glyphicon-tags col-sm-4">{{project.task}}</span>
  						<span class="glyphicon glyphicon-picture col-sm-4 text-primary"></span>
  					</div>
  				</div>
  			</div>
		</div>
    </div>
</div>