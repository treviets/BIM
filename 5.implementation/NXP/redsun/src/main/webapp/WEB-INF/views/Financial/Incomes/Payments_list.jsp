
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/Financial/Incomes/PaymentsController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/PaymentsService.js"></script>

<div ng-controller="paymentController" ng-init="initList()">
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
					<input type="text" ng-model="payment.name" class="inpput-sm" placeholder="<spring:message code="payment.name"/>" />
				</div>
				<!-- filter by billName -->
				<div class="col-md-2">
					<input type="text" ng-model="payment.billName" class="inpput-sm" placeholder="<spring:message code="payment.billName"/>" />
				</div>
				<!-- filter by paymentTypeName -->
				<div class="col-md-2">
					<input type="text" ng-model="payment.paymentTypeName" class="inpput-sm" placeholder="<spring:message code="payment.paymentTypeName"/>" />
				</div>
				<!-- filter by description -->
				<div class="col-md-2">
					<input type="text" ng-model="payment.description" class="inpput-sm" placeholder="<spring:message code="payment.description"/>" />
				</div>
				<!-- filter button -->
				<div class="col-md-2">
					<button type="button" class="btn" ng-click="getsForPageAndFilter(1)" data-toggle="tooltip" title='<spring:message code="payment.tooltip.search"></spring:message>'><span class="glyphicon glyphicon-search"></span></button>
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
					<button class="btn btn-info" ng-click="add()" data-toggle="tooltip" title='<spring:message code="payment.tooltip.create"></spring:message>'>
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
							<th ng-click="sortBy('name')"><spring:message code="payment.name"/>
								<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('billName')"><spring:message code="payment.billName"/>
								<span ng-show="sortKey === 'billName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('paymentTypeName')"><spring:message code="payment.paymentTypeName"/>
								<span ng-show="sortKey === 'paymentTypeName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th ng-click="sortBy('description')"><spring:message code="payment.description"/>
								<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
							</th>
							<th/>
							<th/>
						</tr>
					</thead>
					<tbody>
						<tr ng-show="payment.length">
							<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
						</tr>
						<tr dir-paginate="item in payments | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
							<td>{{item.name}}</td>
							<td>{{item.billName}}</td>
							<td>{{item.paymentTypeName}}</td>
							<td data-toggle="tooltip" title="{{(!item.description.length || item.description.length < 50) ? '' : item.description}}">{{item.description | limitTo: 50}} {{(!item.description.length || item.description.length < 50) ? '' : '...'}}</td>
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
					<button class="btn btn-info" ng-click="add()" data-toggle="tooltip" title='<spring:message code="payment.tooltip.create"></spring:message>'>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
