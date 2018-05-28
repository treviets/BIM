<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="projectCtrl as projectCtrl" ng-init="initProject(false);">
	<div class="col-lg-12 padding-top">
  		<div class="panel panel-default">
  			<div class="panel-heading clearfix">
  				<form class="navbar-form navbar-left">
	        		<div class="form-group">
	        			<input type="text" class="form-control" placeholder="Search" ng-model="searchName">
	        		</div> 
      			</form>
      			<div class="pull-right">
      				<span ng-click="addProject();" class="glyphicon glyphicon-plus" title="click here to add new project" ng-if="checkPermission('PM_2');"></span>&nbsp;&nbsp;&nbsp;
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
  			</div>
  			<div class="project-list panel-body">
  				<div ng-repeat="project in projects  | filter:searchName" class="project col-sm-3" ng-click="gotoDetailPageProject(this);">
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