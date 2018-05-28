
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/ProvidersController.js"></script>
<script src="${contextPath}/static/js/services/ProvidersService.js"></script>

<div ng-controller="providerController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.name" class="inpput-sm" />
			</div>
			<!-- filter by idProviderType -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.idProviderType" class="inpput-sm" />
			</div>
			<!-- filter by description -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.description" class="inpput-sm" />
			</div>
			<!-- filter by providerCode -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.providerCode" class="inpput-sm" />
			</div>
			<!-- filter by idPaymentDelay -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.idPaymentDelay" class="inpput-sm" />
			</div>
			<!-- filter by numTax -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.numTax" class="inpput-sm" />
			</div>
			<!-- filter by tax -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.tax" class="inpput-sm" />
			</div>
			<!-- filter by designation -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.designation" class="inpput-sm" />
			</div>
			<!-- filter by street -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.street" class="inpput-sm" />
			</div>
			<!-- filter by complement -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.complement" class="inpput-sm" />
			</div>
			<!-- filter by zip -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.zip" class="inpput-sm" />
			</div>
			<!-- filter by city -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.city" class="inpput-sm" />
			</div>
			<!-- filter by state -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.state" class="inpput-sm" />
			</div>
			<!-- filter by country -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.country" class="inpput-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="provider.idle" class="inpput-sm" />
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
						<th ng-click="sortBy('name')"><spring:message code="provider.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idProviderType')"><spring:message code="provider.idProviderType"/>
							<span ng-show="sortKey === 'idProviderType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('description')"><spring:message code="provider.description"/>
							<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('providerCode')"><spring:message code="provider.providerCode"/>
							<span ng-show="sortKey === 'providerCode'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idPaymentDelay')"><spring:message code="provider.idPaymentDelay"/>
							<span ng-show="sortKey === 'idPaymentDelay'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('numTax')"><spring:message code="provider.numTax"/>
							<span ng-show="sortKey === 'numTax'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('tax')"><spring:message code="provider.tax"/>
							<span ng-show="sortKey === 'tax'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('designation')"><spring:message code="provider.designation"/>
							<span ng-show="sortKey === 'designation'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('street')"><spring:message code="provider.street"/>
							<span ng-show="sortKey === 'street'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('complement')"><spring:message code="provider.complement"/>
							<span ng-show="sortKey === 'complement'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('zip')"><spring:message code="provider.zip"/>
							<span ng-show="sortKey === 'zip'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('city')"><spring:message code="provider.city"/>
							<span ng-show="sortKey === 'city'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('state')"><spring:message code="provider.state"/>
							<span ng-show="sortKey === 'state'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('country')"><spring:message code="provider.country"/>
							<span ng-show="sortKey === 'country'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="provider.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="provider.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in providers | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.name}}</td>
						<td>{{item.idProviderType}}</td>
						<td>{{item.description}}</td>
						<td>{{item.providerCode}}</td>
						<td>{{item.idPaymentDelay}}</td>
						<td>{{item.numTax}}</td>
						<td>{{item.tax}}</td>
						<td>{{item.designation}}</td>
						<td>{{item.street}}</td>
						<td>{{item.complement}}</td>
						<td>{{item.zip}}</td>
						<td>{{item.city}}</td>
						<td>{{item.state}}</td>
						<td>{{item.country}}</td>
						<td>{{item.idle}}</td>
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