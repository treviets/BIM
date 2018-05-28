
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/ClientsController.js"></script>
<script src="${contextPath}/static/js/services/ClientsService.js"></script>

<div ng-controller="clientController" ng-init="initListClient()">
	<!-- panel -->
	<div style="margin-top: 10px;"></div>
		<!-- list of content -->
<div class="col-lg-12">
	<div class="panel panel-default">	
		<div class="panel-heading clearfix">
		<div class="btn-group pull-left">
			<input type="text" class="form-control" placeholder="Search" ng-model="searchName">
		</div>
			<div class="btn-group pull-right">
				<span ng-if="checkPermission('CRM_3') || checkPermission('CRM_2')" ng-click="addClient()" class="glyphicon glyphicon-plus" title=<spring:message code="client.tooltip.create"/>></span>&nbsp;&nbsp;&nbsp;
				<span class="glyphicon glyphicon-export" ng-click="exportClient('clients-list')" title=<spring:message code="client.tooltip.export"/>></span>&nbsp;&nbsp;&nbsp;
				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
			</div>
			</div>	
		<div class="panel-body" id="clients-list">		
			<div class="table-responsive" id="scroll-table">
				<table class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th ng-click="sortBy('clientCode')"><spring:message code="client.clientCode"/>
								<span ng-show="sortKey === 'clientCode'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"></span>
							</th>
							<th ng-click="sortBy('name')"><spring:message code="client.name"/>
								<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"></span>
							</th>
							<th ng-click="sortBy('description')"><spring:message code="client.description"/>
								<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"></span>
							</th>
							<th ng-click="sortBy('street')"><spring:message code="client.street"/>
								<span ng-show="sortKey === 'street'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"></span>
							</th>
							<th ng-click="sortBy('city')"><spring:message code="client.city"/>
								<span ng-show="sortKey === 'city'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"></span>
							</th>
							<th ng-click="sortBy('country')"><spring:message code="client.country"/>
								<span ng-show="sortKey === 'country'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"></span>
							</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="client.length">
							<td colspan="4" style="text-align: center;"></td>
						</tr>
						<tr dir-paginate="item in clients | orderBy:sortKey:reverse | filter:searchName | itemsPerPage:itemsPerPage" total-items="totalCount">
							<td>{{item.clientCode}}</td>
							<td>{{item.name}}</td>
							<td>{{item.description}}</td>
							<td>{{item.street}}</td>
							<td>{{item.city}}</td>
							<td>{{item.country}}</td>
							<td>
								<div ng-if="checkPermission('CRM_3')" ng-click="editClient(item.id)">
									<span class="glyphicon glyphicon-pencil"></span>
								</div>
							</td>
							<td>
								<div ng-if="checkPermission('CRM_4')" ng-click="deleteClient(item.id)">
									<span class="glyphicon glyphicon-trash"></span>
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
					   on-page-change="getsForPageAndFilterClient(newPageNumber)" >
				</dir-pagination-controls>
			</div>
		</div>
	</div>
</div>
</div>
</div>
