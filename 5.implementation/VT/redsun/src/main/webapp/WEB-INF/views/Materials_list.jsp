<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="materialController" ng-init="initListMaterials()">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
					<button type="button" ng-click="equipmentManagement()" class="btn"><spring:message code="equipment.title" /></button>
					<button type="button"  class="btn btn-primary"><spring:message code="material.title" /></button>
					<input type="text" class="form-control" placeholder="Search" ng-model="searchName">
				</h3>
				<div class="btn-group pull-right">
					<button type="button" ng-if="checkPermission('WH_3') || checkPermission('WH_2')" ng-click="addMaterial();" class="btn btn-default" data-toggle="tooltip" title=<spring:message code="material.tooltip.create"/>>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
					<button type="button" class="btn btn-default" data-toggle="tooltip" ng-click="exportMaterials('material-list')" title=<spring:message code="material.tooltip.export"/>>
						<span class="glyphicon glyphicon-export"></span>
					</button>
				</div>
			</div>
			
				
			<div class="panel-body" id="material-list">

				<div class="table-responsive" id="scroll-table">

					<table class="table table-striped table-hover table-bordered">

						<thead>
							<tr>
						<th ng-click="sortBy('code')"><spring:message code="material.code"/>
							<span ng-show="sortKey === 'code'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('name')"><spring:message code="material.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('unit')"><spring:message code="material.unit"/>
							<span ng-show="sortKey === 'address'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('quantity')"><spring:message code="material.quantity"/>
							<span ng-show="sortKey === 'street'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('netPrice')"><spring:message code="material.netPrice"/>
							<span ng-show="sortKey === 'netPrice'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('price')"><spring:message code="material.price"/>
							<span ng-show="sortKey === 'price'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th/>
						<th/>
					</tr>
						</thead>
						<tbody>
					<tr ng-show="material.length">
					</tr>
					<tr dir-paginate="item in materials | orderBy:sortKey:reverse | filter:searchName | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.code}}</td>
						<td>{{item.name}}</td>
						<td>{{item.unitName}}</td>
						<td>{{item.quantity | number}}</td>
						<td>{{item.netPrice | number}}</td>
						<td>{{item.price | number}}</td>
						<td>
							<div ng-click="editMaterial(item.id)">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>
						</td>
						<td>
							<div ng-click="deleteMaterial(item.id)">
								<span class="glyphicon glyphicon-trash"></span>
							</div>
						</td>
					</tr>
				</tbody>
					</table>

				</div>
				<div class="panel-body">
					<div class="pull-left">
						<dir-pagination-controls max-size="8" direction-links="true"
							boundary-links="true" on-page-change="getMaterialForPage(newPageNumber)">
						</dir-pagination-controls>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>