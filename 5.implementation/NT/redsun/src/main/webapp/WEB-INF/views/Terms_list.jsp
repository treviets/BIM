
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/TermsController.js"></script>
<script src="${contextPath}/static/js/services/TermsService.js"></script>

<div ng-controller="termController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="term.name" class="inpput-sm" />
			</div>
			<!-- filter by idProject -->
			<div class="col-md-2">
				<input type="text" ng-model="term.idProject" class="inpput-sm" />
			</div>
			<!-- filter by amount -->
			<div class="col-md-2">
				<input type="text" ng-model="term.amount" class="inpput-sm" />
			</div>
			<!-- filter by validatedAmount -->
			<div class="col-md-2">
				<input type="text" ng-model="term.validatedAmount" class="inpput-sm" />
			</div>
			<!-- filter by plannedAmount -->
			<div class="col-md-2">
				<input type="text" ng-model="term.plannedAmount" class="inpput-sm" />
			</div>
			<!-- filter by date -->
			<div class="col-md-2">
				<input type="text" ng-model="term.date" class="input-sm" />
			</div>
			<!-- filter by validatedDate -->
			<div class="col-md-2">
				<input type="text" ng-model="term.validatedDate" class="input-sm" />
			</div>
			<!-- filter by plannedDate -->
			<div class="col-md-2">
				<input type="text" ng-model="term.plannedDate" class="input-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="term.idle" class="inpput-sm" />
			</div>
			<!-- filter by idBill -->
			<div class="col-md-2">
				<input type="text" ng-model="term.idBill" class="inpput-sm" />
			</div>
			<!-- filter by idUser -->
			<div class="col-md-2">
				<input type="text" ng-model="term.idUser" class="inpput-sm" />
			</div>
			<!-- filter by creationDate -->
			<div class="col-md-2">
				<input type="text" ng-model="term.creationDate" class="input-sm" />
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
						<th ng-click="sortBy('name')"><spring:message code="term.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idProject')"><spring:message code="term.idProject"/>
							<span ng-show="sortKey === 'idProject'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('amount')"><spring:message code="term.amount"/>
							<span ng-show="sortKey === 'amount'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('validatedAmount')"><spring:message code="term.validatedAmount"/>
							<span ng-show="sortKey === 'validatedAmount'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('plannedAmount')"><spring:message code="term.plannedAmount"/>
							<span ng-show="sortKey === 'plannedAmount'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('date')"><spring:message code="term.date"/>
							<span ng-show="sortKey === 'date'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('validatedDate')"><spring:message code="term.validatedDate"/>
							<span ng-show="sortKey === 'validatedDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('plannedDate')"><spring:message code="term.plannedDate"/>
							<span ng-show="sortKey === 'plannedDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="term.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idBill')"><spring:message code="term.idBill"/>
							<span ng-show="sortKey === 'idBill'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idUser')"><spring:message code="term.idUser"/>
							<span ng-show="sortKey === 'idUser'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('creationDate')"><spring:message code="term.creationDate"/>
							<span ng-show="sortKey === 'creationDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="term.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in terms | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.name}}</td>
						<td>{{item.idProject}}</td>
						<td>{{item.amount}}</td>
						<td>{{item.validatedAmount}}</td>
						<td>{{item.plannedAmount}}</td>
						<td>{{item.date}}</td>
						<td>{{item.validatedDate}}</td>
						<td>{{item.plannedDate}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.idBill}}</td>
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