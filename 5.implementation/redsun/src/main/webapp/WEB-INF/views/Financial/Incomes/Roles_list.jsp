
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/Financial/Incomes/RolesController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/RolesService.js"></script>

<div ng-controller="roleController" ng-init="initList()">
	<!-- panel -->
	<div class="panel panel-default">
		<!-- panel-heading -->
		<div class="panel-heading">
			<!-- filter	 -->
			<div class="row">
				
				<!-- filter by name -->
				<div class="col-md-2">
					<input type="text" ng-model="role.name" class="inpput-sm" placeholder="<spring:message code="role.name"/>" />
				</div>
				<!-- filter by description -->
				<div class="col-md-2">
					<input type="text" ng-model="role.description" class="inpput-sm" placeholder="<spring:message code="role.description"/>" />
				</div>
				<!-- filter button -->
				<div class="col-md-2">
					<button type="button" class="btn" ng-click="getsForPageAndFilter(1)" data-toggle="tooltip" title='<spring:message code="role.tooltip.search"></spring:message>'><span class="glyphicon glyphicon-search"></span></button>
				</div>
				
			</div>
		
			<!-- top actions -->
			<div class="row">
				<div class="pull-left">
					<dir-pagination-controls
						   max-size="8"
						   direction-links="true"
						   boundary-links="true" 
						   on-page-change="getsForPageAndFilter(newPageNumber)" >
					</dir-pagination-controls>
				</div>
				<div class="btn-group pull-right">
					<button class="btn btn-default" ng-click="add()" data-toggle="tooltip" title='<spring:message code="role.tooltip.create"></spring:message>'>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</div>
	
		<!-- list of content -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th ng-click="sortBy('name')"><spring:message code="role.name"/>
								<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('description')"><spring:message code="role.description"/>
								<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('defaultCost')"><spring:message code="role.defaultCost"/>
								<span ng-show="sortKey === 'defaultCost'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th/>
							<th/>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="role.length">
							<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
						</tr>
						<tr dir-paginate="item in roles | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
							<td>{{item.name}}</td>
							<td data-toggle="tooltip" title="{{(!item.description.length || item.description.length < 50) ? '' : item.description}}">{{item.description | limitTo: 50}} {{(!item.description.length || item.description.length < 50) ? '' : '...'}}</td>
							<td>{{item.defaultCost}}</td>
							<td>
								<div ng-click="edit(item.id)">
									<span class="glyphicon glyphicon-pencil"></span>
								</div>
							</td>
							<td>
								<div ng-click="delete(item.id)">
									<span class="glyphicon glyphicon-trash"></span>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- bottom actions -->
		<div class="panel-footer">
			<div class="row">
			
				<div class="pull-left">
					<dir-pagination-controls
						   max-size="8"
						   direction-links="true"
						   boundary-links="true" 
						   on-page-change="getsForPageAndFilter(newPageNumber)" >
					</dir-pagination-controls>
				</div>
				<div class="btn-group pull-right">
					<button class="btn btn-default" ng-click="add()" data-toggle="tooltip" title='<spring:message code="role.tooltip.create"></spring:message>'>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
