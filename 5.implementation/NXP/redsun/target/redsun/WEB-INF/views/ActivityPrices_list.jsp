
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/ActivityPricesController.js"></script>
<script src="${contextPath}/static/js/services/ActivityPricesService.js"></script>

<div ng-controller="activityPriceController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by idProject -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.idProject" class="inpput-sm" />
			</div>
			<!-- filter by idActivityType -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.idActivityType" class="inpput-sm" />
			</div>
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.name" class="inpput-sm" />
			</div>
			<!-- filter by priceCost -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.priceCost" class="inpput-sm" />
			</div>
			<!-- filter by subcontractor -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.subcontractor" class="inpput-sm" />
			</div>
			<!-- filter by sortOrder -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.sortOrder" class="inpput-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.idle" class="inpput-sm" />
			</div>
			<!-- filter by subcontractorCost -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.subcontractorCost" class="inpput-sm" />
			</div>
			<!-- filter by idTeam -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.idTeam" class="inpput-sm" />
			</div>
			<!-- filter by commissionCost -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.commissionCost" class="inpput-sm" />
			</div>
			<!-- filter by isRef -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.isRef" class="inpput-sm" />
			</div>
			<!-- filter by pct -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.pct" class="inpput-sm" />
			</div>
			<!-- filter by idUser -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.idUser" class="inpput-sm" />
			</div>
			<!-- filter by creationDate -->
			<div class="col-md-2">
				<input type="text" ng-model="activityPrice.creationDate" class="input-sm" />
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
						<th ng-click="sortBy('idProject')"><spring:message code="activityPrice.idProject"/>
							<span ng-show="sortKey === 'idProject'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idActivityType')"><spring:message code="activityPrice.idActivityType"/>
							<span ng-show="sortKey === 'idActivityType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('name')"><spring:message code="activityPrice.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('priceCost')"><spring:message code="activityPrice.priceCost"/>
							<span ng-show="sortKey === 'priceCost'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('subcontractor')"><spring:message code="activityPrice.subcontractor"/>
							<span ng-show="sortKey === 'subcontractor'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('sortOrder')"><spring:message code="activityPrice.sortOrder"/>
							<span ng-show="sortKey === 'sortOrder'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="activityPrice.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('subcontractorCost')"><spring:message code="activityPrice.subcontractorCost"/>
							<span ng-show="sortKey === 'subcontractorCost'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idTeam')"><spring:message code="activityPrice.idTeam"/>
							<span ng-show="sortKey === 'idTeam'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('commissionCost')"><spring:message code="activityPrice.commissionCost"/>
							<span ng-show="sortKey === 'commissionCost'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('isRef')"><spring:message code="activityPrice.isRef"/>
							<span ng-show="sortKey === 'isRef'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('pct')"><spring:message code="activityPrice.pct"/>
							<span ng-show="sortKey === 'pct'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idUser')"><spring:message code="activityPrice.idUser"/>
							<span ng-show="sortKey === 'idUser'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('creationDate')"><spring:message code="activityPrice.creationDate"/>
							<span ng-show="sortKey === 'creationDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="activityPrice.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in activityPrices | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.idProject}}</td>
						<td>{{item.idActivityType}}</td>
						<td>{{item.name}}</td>
						<td>{{item.priceCost}}</td>
						<td>{{item.subcontractor}}</td>
						<td>{{item.sortOrder}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.subcontractorCost}}</td>
						<td>{{item.idTeam}}</td>
						<td>{{item.commissionCost}}</td>
						<td>{{item.isRef}}</td>
						<td>{{item.pct}}</td>
						<td>{{item.idUser}}</td>
						<td>{{item.creationDate}}</td>
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