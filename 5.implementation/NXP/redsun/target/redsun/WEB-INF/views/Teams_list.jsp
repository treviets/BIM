
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/TeamsController.js"></script>
<script src="${contextPath}/static/js/services/TeamsService.js"></script>

<div ng-controller="teamController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="team.name" class="inpput-sm" />
			</div>
			<!-- filter by description -->
			<div class="col-md-2">
				<input type="text" ng-model="team.description" class="inpput-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="team.idle" class="inpput-sm" />
			</div>
			<!-- filter by idResource -->
			<div class="col-md-2">
				<input type="text" ng-model="team.idResource" class="inpput-sm" />
			</div>
			<!-- filter button -->
			<div class="col-md-2">
				<button type="button" class="btn" ng-click="getsForPageAndFilter(1)"><span class="glyphicon glyphicon-search"></span></button>
			</div>
		</div>
		<!-- top actions -->
		<div class="row panel-body">
			<div class="pull-left">
				<dir-pagination-controls
					   max-size="8"
					   direction-links="true"
					   boundary-links="true" 
					   on-page-change="getsForPageAndFilter(newPageNumber)" >
				</dir-pagination-controls>
			</div>
			<div class="pull-right">
				<div ng-click="add()">
					<span><img src="${contextPath}/static/images/add.png"></span>
				</div>
			</div>
		</div>
		<!-- list of content -->
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th ng-click="sortBy('name')"><spring:message code="team.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('description')"><spring:message code="team.description"/>
							<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="team.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idResource')"><spring:message code="team.idResource"/>
							<span ng-show="sortKey === 'idResource'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="team.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in teams | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.name}}</td>
						<td>{{item.description}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.idResource}}</td>
						<td>
							<div ng-click="edit(item.id)">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>
						</td>
						<td>
							<div ng-click="delete(item.id)">
								<span class="glyphicon glyphicon-delete"></span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- bottom actions -->
		<div class="row panel-body">
			<div class="pull-left">
				<dir-pagination-controls
					   max-size="8"
					   direction-links="true"
					   boundary-links="true" 
					   on-page-change="getsForPageAndFilter(newPageNumber)" >
				</dir-pagination-controls>
			</div>
			<div class="pull-right">
				<div ng-click="add()">
					<span><img src="${contextPath}/static/images/add.png"></span>
				</div>
			</div>
		</div>
	</div>
</div>