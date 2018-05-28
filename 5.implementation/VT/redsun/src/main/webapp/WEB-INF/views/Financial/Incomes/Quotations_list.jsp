
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/Financial/Incomes/QuotationsController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/QuotationsService.js"></script>

<div ng-controller="quotationController" ng-init="initList()">
	<!-- panel -->
	<div class="panel panel-default">
		<!-- panel-heading -->
		<div class="panel-heading">
		    <div class="row">
			    <ul class="nav navbar-nav">
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/call-for-tender/list')">Call for tender</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/tender/list')">Tender</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/expenses/project/list')">Project expense</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/expenses/individual/list')">Individual expense</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/quotation/list')">Quotation</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/command/list')">Order</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/bill/list')">Bill</li>
			  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/payment/list')">Payment</li>
			    </ul>
		    </div>
		    <hr>
			<!-- filter	 -->
			<div class="row">
				
				<!-- filter by name -->
				<div class="col-md-2">
					<input type="text" ng-model="quotation.name" class="inpput-sm" placeholder="<spring:message code="quotation.name"/>" />
				</div>
				<!-- filter by projectName -->
				<div class="col-md-2">
					<input type="text" ng-model="quotation.projectName" class="inpput-sm" placeholder="<spring:message code="quotation.projectName"/>" />
				</div>
				<!-- filter by quotationTypeName -->
				<div class="col-md-2">
					<input type="text" ng-model="quotation.quotationTypeName" class="inpput-sm" placeholder="<spring:message code="quotation.quotationTypeName"/>" />
				</div>
				<!-- filter by description -->
				<div class="col-md-2">
					<input type="text" ng-model="quotation.description" class="inpput-sm" placeholder="<spring:message code="quotation.description"/>" />
				</div>
				<!-- filter by statusName -->
				<div class="col-md-2">
					<input type="text" ng-model="quotation.statusName" class="inpput-sm" placeholder="<spring:message code="quotation.statusName"/>" />
				</div>
				<!-- filter button -->
				<div class="col-md-2">
					<button type="button" class="btn" ng-click="getsForPageAndFilter(1)" data-toggle="tooltip" title='<spring:message code="quotation.tooltip.search"></spring:message>'><span class="glyphicon glyphicon-search"></span></button>
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
					<button class="btn btn-info" ng-click="add()" data-toggle="tooltip" title='<spring:message code="quotation.tooltip.create"></spring:message>'>
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
							<th ng-click="sortBy('name')"><spring:message code="quotation.name"/>
								<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('projectName')"><spring:message code="quotation.projectName"/>
								<span ng-show="sortKey === 'projectName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('quotationTypeName')"><spring:message code="quotation.quotationTypeName"/>
								<span ng-show="sortKey === 'quotationTypeName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('description')"><spring:message code="quotation.description"/>
								<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('statusName')"><spring:message code="quotation.statusName"/>
								<span ng-show="sortKey === 'statusName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th/>
							<th/>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="quotation.length">
							<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
						</tr>
						<tr dir-paginate="item in quotations | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
							<td>{{item.name}}</td>
							<td>{{item.projectName}}</td>
							<td>{{item.quotationTypeName}}</td>
							<td data-toggle="tooltip" title="{{(!item.description.length || item.description.length < 50) ? '' : item.description}}">{{item.description | limitTo: 50}} {{(!item.description.length || item.description.length < 50) ? '' : '...'}}</td>
							<td>{{item.statusName}}</td>
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
					<button class="btn btn-info" ng-click="add()" data-toggle="tooltip" title='<spring:message code="quotation.tooltip.create"></spring:message>'>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
