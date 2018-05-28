
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/BillLinesController.js"></script>
<script src="${contextPath}/static/js/services/BillLinesService.js"></script>

<div ng-controller="billLineController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by line -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.line" class="inpput-sm" />
			</div>
			<!-- filter by quantity -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.quantity" class="inpput-sm" />
			</div>
			<!-- filter by description -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.description" class="inpput-sm" />
			</div>
			<!-- filter by detail -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.detail" class="inpput-sm" />
			</div>
			<!-- filter by price -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.price" class="inpput-sm" />
			</div>
			<!-- filter by amount -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.amount" class="inpput-sm" />
			</div>
			<!-- filter by refType -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.refType" class="inpput-sm" />
			</div>
			<!-- filter by refId -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.refId" class="inpput-sm" />
			</div>
			<!-- filter by idTerm -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.idTerm" class="inpput-sm" />
			</div>
			<!-- filter by idResource -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.idResource" class="inpput-sm" />
			</div>
			<!-- filter by idActivityPrice -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.idActivityPrice" class="inpput-sm" />
			</div>
			<!-- filter by startDate -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.startDate" class="input-sm" />
			</div>
			<!-- filter by endDate -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.endDate" class="input-sm" />
			</div>
			<!-- filter by idMeasureUnit -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.idMeasureUnit" class="inpput-sm" />
			</div>
			<!-- filter by extra -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.extra" class="inpput-sm" />
			</div>
			<!-- filter by billingType -->
			<div class="col-md-2">
				<input type="text" ng-model="billLine.billingType" class="inpput-sm" />
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
						<th ng-click="sortBy('line')"><spring:message code="billLine.line"/>
							<span ng-show="sortKey === 'line'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('quantity')"><spring:message code="billLine.quantity"/>
							<span ng-show="sortKey === 'quantity'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('description')"><spring:message code="billLine.description"/>
							<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('detail')"><spring:message code="billLine.detail"/>
							<span ng-show="sortKey === 'detail'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('price')"><spring:message code="billLine.price"/>
							<span ng-show="sortKey === 'price'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('amount')"><spring:message code="billLine.amount"/>
							<span ng-show="sortKey === 'amount'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('refType')"><spring:message code="billLine.refType"/>
							<span ng-show="sortKey === 'refType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('refId')"><spring:message code="billLine.refId"/>
							<span ng-show="sortKey === 'refId'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idTerm')"><spring:message code="billLine.idTerm"/>
							<span ng-show="sortKey === 'idTerm'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idResource')"><spring:message code="billLine.idResource"/>
							<span ng-show="sortKey === 'idResource'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idActivityPrice')"><spring:message code="billLine.idActivityPrice"/>
							<span ng-show="sortKey === 'idActivityPrice'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('startDate')"><spring:message code="billLine.startDate"/>
							<span ng-show="sortKey === 'startDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('endDate')"><spring:message code="billLine.endDate"/>
							<span ng-show="sortKey === 'endDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idMeasureUnit')"><spring:message code="billLine.idMeasureUnit"/>
							<span ng-show="sortKey === 'idMeasureUnit'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('extra')"><spring:message code="billLine.extra"/>
							<span ng-show="sortKey === 'extra'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('billingType')"><spring:message code="billLine.billingType"/>
							<span ng-show="sortKey === 'billingType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="billLine.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in billLines | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.line}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.description}}</td>
						<td>{{item.detail}}</td>
						<td>{{item.price}}</td>
						<td>{{item.amount}}</td>
						<td>{{item.refType}}</td>
						<td>{{item.refId}}</td>
						<td>{{item.idTerm}}</td>
						<td>{{item.idResource}}</td>
						<td>{{item.idActivityPrice}}</td>
						<td>{{item.startDate}}</td>
						<td>{{item.endDate}}</td>
						<td>{{item.idMeasureUnit}}</td>
						<td>{{item.extra}}</td>
						<td>{{item.billingType}}</td>
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