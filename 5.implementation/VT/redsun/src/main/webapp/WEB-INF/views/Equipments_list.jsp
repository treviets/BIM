<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="equipmentController" ng-init="initListEquipments()">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<button type="button"  class="btn btn-primary"><spring:message code="equipment.title" /></button>
					<button type="button" ng-click="materialManagement()" class="btn"><spring:message code="material.title" /></button>
					<input type="text" class="form-control" placeholder="Search" ng-model="searchName">
				</h3>
				<div class="btn-group pull-right">
					<button type="button" ng-if="checkPermission('WH_3') || checkPermission('WH_2')" ng-click="addEquipment();" class="btn btn-default" data-toggle="tooltip" title=<spring:message code="equipment.tooltip.create"/>>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
					<button type="button" class="btn btn-default" data-toggle="tooltip" title=<spring:message code="equipment.tooltip.export"/> ng-click="exportEquipments('equipment-list')">
						<span class="glyphicon glyphicon-export"></span>
					</button>
				</div>
			</div>
			
				
			<div class="panel-body" id="equipment-list">

				<div class="table-responsive" id="scroll-table">

					<table class="table table-striped table-hover table-bordered">

						<thead>
							<tr>
						<th ng-click="sortBy('code')"><spring:message code="equipment.code"/>
							<span ng-show="sortKey === 'code'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('name')"><spring:message code="equipment.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('unit')"><spring:message code="equipment.unit"/>
							<span ng-show="sortKey === 'address'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('quantity')"><spring:message code="equipment.quantity"/>
							<span ng-show="sortKey === 'street'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('netPrice')"><spring:message code="equipment.netPrice"/>
							<span ng-show="sortKey === 'netPrice'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('price')"><spring:message code="equipment.price"/>
							<span ng-show="sortKey === 'price'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th/>
						<th/>
					</tr>
						</thead>
						<tbody>
					<tr ng-show="equipment.length">
					</tr>
					<tr dir-paginate="item in equipments | orderBy:sortKey:reverse | filter:searchName | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.code}}</td>
						<td class="totext">{{item.name}}<span class="tooltiptext">Description: {{item.description}}</span></td>
						<td>{{item.unitName}}</td>
						<td>{{item.quantity | number}}</td>
						<td>{{item.netPrice | number}}</td>
						<td>{{item.price | number}}</td>
						<td>
							<div ng-click="editEquipment(item.id)">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>
						</td>
						<td>
							<div ng-click="deleteEquipment(item.id)">
								<span class="glyphicon glyphicon-trash"></span>
							</div>
						</td>
					</tr>
				</tbody>
					</table>

				</div>
				<div class="panel-body">
					<div class="pull-left">
						<dir-pagination-controls max-size="8" direction-links="true" boundary-links="true" on-page-change="getEquipmentForPage(newPageNumber)"> </dir-pagination-controls>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>