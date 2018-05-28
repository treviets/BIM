
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/ProductsController.js"></script>
<script src="${contextPath}/static/js/services/ProductsService.js"></script>

<div ng-controller="productController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="product.name" class="inpput-sm" />
			</div>
			<!-- filter by idClient -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idClient" class="inpput-sm" />
			</div>
			<!-- filter by idContact -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idContact" class="inpput-sm" />
			</div>
			<!-- filter by description -->
			<div class="col-md-2">
				<input type="text" ng-model="product.description" class="inpput-sm" />
			</div>
			<!-- filter by creationDate -->
			<div class="col-md-2">
				<input type="text" ng-model="product.creationDate" class="input-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idle" class="inpput-sm" />
			</div>
			<!-- filter by idProduct -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idProduct" class="inpput-sm" />
			</div>
			<!-- filter by designation -->
			<div class="col-md-2">
				<input type="text" ng-model="product.designation" class="inpput-sm" />
			</div>
			<!-- filter by scope -->
			<div class="col-md-2">
				<input type="text" ng-model="product.scope" class="inpput-sm" />
			</div>
			<!-- filter by idProductType -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idProductType" class="inpput-sm" />
			</div>
			<!-- filter by idComponentType -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idComponentType" class="inpput-sm" />
			</div>
			<!-- filter by idResource -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idResource" class="inpput-sm" />
			</div>
			<!-- filter by idUser -->
			<div class="col-md-2">
				<input type="text" ng-model="product.idUser" class="inpput-sm" />
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
						<th ng-click="sortBy('name')"><spring:message code="product.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idClient')"><spring:message code="product.idClient"/>
							<span ng-show="sortKey === 'idClient'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idContact')"><spring:message code="product.idContact"/>
							<span ng-show="sortKey === 'idContact'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('description')"><spring:message code="product.description"/>
							<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('creationDate')"><spring:message code="product.creationDate"/>
							<span ng-show="sortKey === 'creationDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="product.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idProduct')"><spring:message code="product.idProduct"/>
							<span ng-show="sortKey === 'idProduct'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('designation')"><spring:message code="product.designation"/>
							<span ng-show="sortKey === 'designation'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('scope')"><spring:message code="product.scope"/>
							<span ng-show="sortKey === 'scope'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idProductType')"><spring:message code="product.idProductType"/>
							<span ng-show="sortKey === 'idProductType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idComponentType')"><spring:message code="product.idComponentType"/>
							<span ng-show="sortKey === 'idComponentType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idResource')"><spring:message code="product.idResource"/>
							<span ng-show="sortKey === 'idResource'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idUser')"><spring:message code="product.idUser"/>
							<span ng-show="sortKey === 'idUser'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="product.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in products | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.name}}</td>
						<td>{{item.idClient}}</td>
						<td>{{item.idContact}}</td>
						<td>{{item.description}}</td>
						<td>{{item.creationDate}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.idProduct}}</td>
						<td>{{item.designation}}</td>
						<td>{{item.scope}}</td>
						<td>{{item.idProductType}}</td>
						<td>{{item.idComponentType}}</td>
						<td>{{item.idResource}}</td>
						<td>{{item.idUser}}</td>
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