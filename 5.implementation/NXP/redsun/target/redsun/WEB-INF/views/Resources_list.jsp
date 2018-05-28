<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="resourceController">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<button type="button" class="btn btn-primary"><spring:message code="resource.internal" /></button>
					<button type="button" ng-click="exteriorManagement()" class="btn"><spring:message code="resource.exterior" /></button>
					<input type="text" class ="form-control" placeholder="Search" ng-model="searchName">
				</h3>
				<ul class="list-inline" ng-if="checkPermission('HR_2')">
					<li><label class="control-label" for="attachments">{{'Import Human Resources' | translate}}</label></li>
					<li><input type="file" onchange="angular.element(this).scope().fileResourceSelected(this)" /></li>
					<li><button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="{{'Import Human Resources' | translate}}" ng-click="importResource()">
						<span class="glyphicon glyphicon-import"></span>
						</button>
					</li>
				</ul>
				<div class="btn-group pull-right">
					<span ng-click="newCreateResource();" ng-if="checkPermission('HR_2')" class="glyphicon glyphicon-plus" title=<spring:message code="resource.tooltip.create"/>></span>&nbsp;&nbsp;&nbsp;
					<span ng-click="exportResource();" title=<spring:message code="resource.tooltip.export"/> class="glyphicon glyphicon-export"></span>&nbsp;&nbsp;&nbsp;
					<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
				</div>
			</div>
			<div class="panel-body">
				<ul class="nav nav-tabs">
					<li class="active-users-tab active" ng-click="setActiveTabName('active-users');">
						<a href="#active-users-area" data-toggle="tab">Active resource</a>
					</li>
					<li class="inactive-users-tab" ng-click="setActiveTabName('inactive-users');">
						<a href="#inactive-users-area" data-toggle="tab">Deleted resource</a>
					</li>
				</ul>
				<div class="tab-content">
					<br/>
					<div id="active-users-area" ng-init="initListResource()" class="tab-pane active panel panel-success"> 
						<div class="table-responsive" id="scroll-table">
							<table class="table table-striped table-hover table-bordered" id="active-users-table">
								<thead>
									<tr>
										<th ng-click="sortBy('code')"><spring:message code="resource.code"/>
											<span ng-show="sortKey === 'code'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('name')"><spring:message code="resource.name"/>
											<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('fullName')"><spring:message code="resource.fullName"/>
											<span ng-show="sortKey === 'fullName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('title')"><spring:message code="resource.title"/>
											<span ng-show="sortKey === 'title'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('idCard')"><spring:message code="resource.idCard"/>
											<span ng-show="sortKey === 'idCard'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('mobile')"><spring:message code="resource.mobile"/>
											<span ng-show="sortKey === 'mobile'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('email')"><spring:message code="resource.email"/>
											<span ng-show="sortKey === 'email'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										
										<th/>
										<th/>
									</tr>
								</thead>
								<tbody>
									<tr ng-show="resource.length">
										<td colspan="4" style="text-align: center;"></td>
									</tr>
									<tr dir-paginate="item in resources | orderBy:sortKey:reverse | filter:searchName | itemsPerPage:itemsPerPage" total-items="totalCount">
										<td>{{item.code}}</td>
										<td>{{item.name}}</td>
										<td>{{item.fullName}}</td>
										<td>{{item.title}}</td>
										<td>{{item.idCard}}</td>
										<td>{{item.mobile}}</td>
										<td>{{item.email}}</td>
										<td>
											<div ng-click="editResource(item.id)" ng-if="checkPermission('HR_3')">
												<span class="glyphicon glyphicon-pencil"></span>
											</div>
										</td>
										<td>
											<div ng-click="deleteResource(item.id)" ng-if="checkPermission('HR_4')">
												<span class="glyphicon glyphicon-trash"></span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div id="inactive-users-area" ng-init="getDeletecResources()" class="tab-pane panel panel-success"> 
						<div class="table-responsive" id="scroll-table">
							<table class="table table-striped table-hover table-bordered" id="inactive-users-table">
								<thead>
									<tr>
										<th ng-click="sortBy('code')"><spring:message code="resource.code"/>
											<span ng-show="sortKey === 'code'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('name')"><spring:message code="resource.name"/>
											<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('fullName')"><spring:message code="resource.fullName"/>
											<span ng-show="sortKey === 'fullName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('title')"><spring:message code="resource.title"/>
											<span ng-show="sortKey === 'title'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('idCard')"><spring:message code="resource.idCard"/>
											<span ng-show="sortKey === 'idCard'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('mobile')"><spring:message code="resource.mobile"/>
											<span ng-show="sortKey === 'mobile'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										<th ng-click="sortBy('email')"><spring:message code="resource.email"/>
											<span ng-show="sortKey === 'email'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
										</th>
										
										<th/>
										<th/>
									</tr>
								</thead>
								<tbody>
									<tr ng-show="resource.length">
										<td colspan="4" style="text-align: center;"></td>
									</tr>
									<tr dir-paginate="item in deletedResources | orderBy:sortKey:reverse | filter:searchName | itemsPerPage:itemsPerPage" total-items="totalCount">
										<td>{{item.code}}</td>
										<td>{{item.name}}</td>
										<td>{{item.fullName}}</td>
										<td>{{item.title}}</td>
										<td>{{item.idCard}}</td>
										<td>{{item.mobile}}</td>
										<td>{{item.email}}</td>
										<td>
											<div ng-click="restoreResource(item.id)" ng-if="checkPermission('HR_2') || checkPermission('HR_3')">
												<span class="glyphicon glyphicon-refresh" title="click to restore this user"></span>
											</div>
										</td>
										<td>
											<div ng-click="removeResource(item.id)" ng-if="checkPermission('HR_4')">
												<span class="glyphicon glyphicon-remove" title="click to permanent delete this user"></span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="pull-left">
						<dir-pagination-controls max-size="8" direction-links="true"
							boundary-links="true" on-page-change="getsForPageResource(newPageNumber)">
						</dir-pagination-controls>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>