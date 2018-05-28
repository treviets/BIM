
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/PlanningModesController.js"></script>
<script src="${contextPath}/static/js/services/PlanningModesService.js"></script>

<div ng-controller="planningModeController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.name" class="inpput-sm" />
			</div>
			<!-- filter by code -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.code" class="inpput-sm" />
			</div>
			<!-- filter by sortOrder -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.sortOrder" class="inpput-sm" />
			</div>
			<!-- filter by mandatoryStartDate -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.mandatoryStartDate" class="inpput-sm" />
			</div>
			<!-- filter by mandatoryEndDate -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.mandatoryEndDate" class="inpput-sm" />
			</div>
			<!-- filter by applyTo -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.applyTo" class="inpput-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.idle" class="inpput-sm" />
			</div>
			<!-- filter by mandatoryDuration -->
			<div class="col-md-2">
				<input type="text" ng-model="planningMode.mandatoryDuration" class="inpput-sm" />
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
						<th ng-click="sortBy('name')"><spring:message code="planningMode.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('code')"><spring:message code="planningMode.code"/>
							<span ng-show="sortKey === 'code'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('sortOrder')"><spring:message code="planningMode.sortOrder"/>
							<span ng-show="sortKey === 'sortOrder'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('mandatoryStartDate')"><spring:message code="planningMode.mandatoryStartDate"/>
							<span ng-show="sortKey === 'mandatoryStartDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('mandatoryEndDate')"><spring:message code="planningMode.mandatoryEndDate"/>
							<span ng-show="sortKey === 'mandatoryEndDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('applyTo')"><spring:message code="planningMode.applyTo"/>
							<span ng-show="sortKey === 'applyTo'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="planningMode.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('mandatoryDuration')"><spring:message code="planningMode.mandatoryDuration"/>
							<span ng-show="sortKey === 'mandatoryDuration'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="planningMode.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in planningModes | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.name}}</td>
						<td>{{item.code}}</td>
						<td>{{item.sortOrder}}</td>
						<td>{{item.mandatoryStartDate}}</td>
						<td>{{item.mandatoryEndDate}}</td>
						<td>{{item.applyTo}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.mandatoryDuration}}</td>
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