<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div ng-controller="projectCtrl as projectCtrl" ng-init="initEditProject(${id})">
	<div class="col-lg-12 padding-top">
        <!-- project form -->
  		<div class="panel panel-default">
  			<div class="panel-heading clearfix">
    			<h3 class="panel-title pull-left project-detail-title"><spring:message code="project.title"/>: <Strong>{{project.name}}</Strong> - {{project.code}}</h3>
		    	<div class="pull-right">
		    		<span ng-click="baselineForm();" class="glyphicon glyphicon-align-left" title="click here to set baseline" ng-if="permission.projectRole.projectPermission.indexOf(1)>=0 &&  ((checkPermission('PM_2') || checkPermission('PM_3')) || (checkPermission('Design_2') || checkPermission('Design_3')) )"></span>&nbsp;&nbsp;&nbsp;
		    		<span ng-click="editProject(${id});" class="glyphicon glyphicon-pencil" title="click here to edit project information" ng-if="permission.projectRole.projectPermission.indexOf(1)>=0 &&  ((checkPermission('PM_2') || checkPermission('PM_3')) || (checkPermission('Design_2') || checkPermission('Design_3')) )"></span>&nbsp;&nbsp;&nbsp;
		    		<span ng-click="deleteProject(${id});" class="glyphicon glyphicon-trash" title="click here to delete project information" ng-if="permission.projectRole.projectPermission.indexOf(4)>=0 && (checkPermission('PM_4') || checkPermission('Design_4'))"></span>&nbsp;&nbsp;&nbsp;
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
  			</div>
  			<div class="panel-body">
				<div class="project-dashboard clearfix" ng-show="!isDetailFullscreen">
					<div class="full-left col-lg-12" id="ganttChart" style="height:400px;">
					</div>
				</div>
				<br/>
				<div class="clearfix">
					<div class="panel panel-default">
						
					
						<div class="panel-heading clearfix">
				 			<h3 class="panel-title pull-left">Details</h3>
						   	<div class="pull-right">
						   		<span ng-click="exitDetailFullscreen('financial-area');" class="glyphicon glyphicon-resize-small" ng-if="isDetailFullscreen" title="exit fullscreen"></span><span ng-click="detailFullscreen('financial-area');" class="glyphicon glyphicon-fullscreen" ng-if="!isDetailFullscreen" title="fullscreen"></span>
						   	</div>
						</div>
						<!-- Content -->
						<div class="panel-body">
							<ul class="nav nav-tabs">
							
								<li class="task-tab active" ng-if="permission.projectRole.taskPermission.indexOf(1)>=0">
									<a href="#tasks-area" data-toggle="tab">Task</a>
								</li>
								<li class="document-tab hidden-xs" ng-if="permission.projectRole.documentsPermission.indexOf(1)>=0">
									<a ng-if="!isDesignManagement" href="#document-area" data-toggle="tab">Documents</a>
									<a ng-if="isDesignManagement" href="#bpmn-area" data-toggle="tab">BPMN Documents</a>
								</li>
								<li class="member-tab" ng-if="permission.projectRole.membersPermisison.indexOf(1)>=0">
									<a href="#projet-member-area" data-toggle="tab">Members</a>
								</li>
								 <li class="risk-tab" ng-if="permission.projectRole.riskPermission.indexOf(1)>=0">
									<a href="#risk-area" data-toggle="tab">Risk</a>
								</li>
							</ul>
							<div class="tab-content">
								<br/>
								<div id="tasks-area" class="tab-pane active panel panel-success" ng-include="'/redsun/static/partials/task-area.html'" ng-controller="tasksCtrl" ng-init="getTasksOnePrject(${id});" ng-if="permission.projectRole.taskPermission.indexOf(1)>=0"> 
								</div>
								<div ng-if="!isDesignManagement" id="document-area" class="tab-pane panel panel-success" ng-include="'/redsun/static/partials/document-area.html'" ng-controller="documentController" ng-init="getDocumentsOneProject(${id});" ng-if="permission.projectRole.documentsPermission.indexOf(1)>=0">
								</div>
								<div ng-if="isDesignManagement" id="bpmn-area" class="tab-pane panel panel-success" ng-include="'/redsun/static/partials/bpmn-area.html'" ng-if="permission.projectRole.documentsPermission.indexOf(1)>=0">
								</div>
								<div id="projet-member-area" class="tab-pane panel panel-success" ng-include="'/redsun/static/partials/project-member.html'" ng-controller="memberController" ng-init="getMemberOneProject(${id});" ng-if="permission.projectRole.membersPermisison.indexOf(1)>=0">
								</div>
								<div id="risk-area" class="tab-pane panel panel-success" ng-include="'/redsun/static/partials/risk-area.html'" ng-controller="riskCtrl" ng-init="getRisksOnePrject(${id});" ng-if="permission.projectRole.riskPermission.indexOf(1)>=0">
								</div>
							</div>
						</div>

					</div>
					<div id="social-area" ng-controller="discussingController" ng-init="initDiscussing('groupProject_${id}');" ng-include="'/redsun/static/partials/social-area.html'" class="panel panel-default detail-sub-area full-width">
					</div>
					
					<div id="financial-area" ng-include="'/redsun/static/partials/financial-area.html'" class="panel panel-default detail-sub-area full-width">
					</div>

				</div>
			</div>
        </div>
    </div>
</div>
        