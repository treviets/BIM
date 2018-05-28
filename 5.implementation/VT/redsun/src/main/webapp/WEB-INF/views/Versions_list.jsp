
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/VersionsController.js"></script>
<script src="${contextPath}/static/js/services/VersionsService.js"></script>

<div ng-controller="versionController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by idProduct -->
			<div class="col-md-2">
				<input type="text" ng-model="version.idProduct" class="inpput-sm" />
			</div>
			<!-- filter by idContact -->
			<div class="col-md-2">
				<input type="text" ng-model="version.idContact" class="inpput-sm" />
			</div>
			<!-- filter by idResource -->
			<div class="col-md-2">
				<input type="text" ng-model="version.idResource" class="inpput-sm" />
			</div>
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="version.name" class="inpput-sm" />
			</div>
			<!-- filter by description -->
			<div class="col-md-2">
				<input type="text" ng-model="version.description" class="inpput-sm" />
			</div>
			<!-- filter by creationDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.creationDate" class="input-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="version.idle" class="inpput-sm" />
			</div>
			<!-- filter by initialEisDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.initialEisDate" class="input-sm" />
			</div>
			<!-- filter by plannedEisDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.plannedEisDate" class="input-sm" />
			</div>
			<!-- filter by realEisDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.realEisDate" class="input-sm" />
			</div>
			<!-- filter by initialEndDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.initialEndDate" class="input-sm" />
			</div>
			<!-- filter by plannedEndDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.plannedEndDate" class="input-sm" />
			</div>
			<!-- filter by realEndDate -->
			<div class="col-md-2">
				<input type="text" ng-model="version.realEndDate" class="input-sm" />
			</div>
			<!-- filter by isEis -->
			<div class="col-md-2">
				<input type="text" ng-model="version.isEis" class="inpput-sm" />
			</div>
			<!-- filter by scope -->
			<div class="col-md-2">
				<input type="text" ng-model="version.scope" class="inpput-sm" />
			</div>
			<!-- filter by versionNumber -->
			<div class="col-md-2">
				<input type="text" ng-model="version.versionNumber" class="inpput-sm" />
			</div>
			<!-- filter by idUser -->
			<div class="col-md-2">
				<input type="text" ng-model="version.idUser" class="inpput-sm" />
			</div>
			<!-- filter by idVersionType -->
			<div class="col-md-2">
				<input type="text" ng-model="version.idVersionType" class="inpput-sm" />
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
						<th ng-click="sortBy('idProduct')"><spring:message code="version.idProduct"/>
							<span ng-show="sortKey === 'idProduct'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idContact')"><spring:message code="version.idContact"/>
							<span ng-show="sortKey === 'idContact'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idResource')"><spring:message code="version.idResource"/>
							<span ng-show="sortKey === 'idResource'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('name')"><spring:message code="version.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('description')"><spring:message code="version.description"/>
							<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('creationDate')"><spring:message code="version.creationDate"/>
							<span ng-show="sortKey === 'creationDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="version.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('initialEisDate')"><spring:message code="version.initialEisDate"/>
							<span ng-show="sortKey === 'initialEisDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('plannedEisDate')"><spring:message code="version.plannedEisDate"/>
							<span ng-show="sortKey === 'plannedEisDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('realEisDate')"><spring:message code="version.realEisDate"/>
							<span ng-show="sortKey === 'realEisDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('initialEndDate')"><spring:message code="version.initialEndDate"/>
							<span ng-show="sortKey === 'initialEndDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('plannedEndDate')"><spring:message code="version.plannedEndDate"/>
							<span ng-show="sortKey === 'plannedEndDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('realEndDate')"><spring:message code="version.realEndDate"/>
							<span ng-show="sortKey === 'realEndDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('isEis')"><spring:message code="version.isEis"/>
							<span ng-show="sortKey === 'isEis'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('scope')"><spring:message code="version.scope"/>
							<span ng-show="sortKey === 'scope'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('versionNumber')"><spring:message code="version.versionNumber"/>
							<span ng-show="sortKey === 'versionNumber'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idUser')"><spring:message code="version.idUser"/>
							<span ng-show="sortKey === 'idUser'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idVersionType')"><spring:message code="version.idVersionType"/>
							<span ng-show="sortKey === 'idVersionType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="version.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in versions | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.idProduct}}</td>
						<td>{{item.idContact}}</td>
						<td>{{item.idResource}}</td>
						<td>{{item.name}}</td>
						<td>{{item.description}}</td>
						<td>{{item.creationDate}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.initialEisDate}}</td>
						<td>{{item.plannedEisDate}}</td>
						<td>{{item.realEisDate}}</td>
						<td>{{item.initialEndDate}}</td>
						<td>{{item.plannedEndDate}}</td>
						<td>{{item.realEndDate}}</td>
						<td>{{item.isEis}}</td>
						<td>{{item.scope}}</td>
						<td>{{item.versionNumber}}</td>
						<td>{{item.idUser}}</td>
						<td>{{item.idVersionType}}</td>
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