<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="resourceController" ng-init="initListExterior()">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
					<button type="button" ng-click="internalManagement()" class="btn"><spring:message code="resource.internal" /></button>
					<button type="button" class="btn btn-primary"><spring:message code="resource.exterior" /></button>
					<input type="text" class ="form-control" placeholder="Search" ng-model="searchName">
				</h3>
				<div class="btn-group pull-right">
					<button type="button" ng-click="newCreateExterior();" class="btn btn-default" data-toggle="tooltip"
						title=<spring:message code="resource.tooltip.create"/>>
						<span class="glyphicon glyphicon-plus"></span>
					</button>
					<button type="button" class="btn btn-default" data-toggle="tooltip"
						title=<spring:message code="resource.tooltip.export"/>>
						<span class="glyphicon glyphicon-export"></span>
					</button>
				</div>
			</div>
			
				
			<div class="panel-body">

				<div class="table-responsive">

					<table class="table table-striped table-hover table-bordered">

						<thead>
							<tr>
						<th ng-click="sortBy('code')"><spring:message code="resource.code"/>
							<span ng-show="sortKey === 'code'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('name')"><spring:message code="resource.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('fullName')"><spring:message code="resource.fullName"/>
							<span ng-show="sortKey === 'fullName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('title')"><spring:message code="resource.title"/>
							<span ng-show="sortKey === 'title'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('idCard')"><spring:message code="resource.idCard"/>
							<span ng-show="sortKey === 'idCard'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('email')"><spring:message code="resource.email"/>
							<span ng-show="sortKey === 'email'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th ng-click="sortBy('mobile')"><spring:message code="resource.mobile"/>
							<span ng-show="sortKey === 'mobile'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
						</th>
						<th/>
						<th/>
					</tr>
						</thead>
						<tbody>
					<tr ng-show="resource.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in resources | orderBy:sortKey:reverse | filter:searchName | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.code}}</td>
						<td>{{item.name}}</td>
						<td>{{item.fullName}}</td>
						<td>{{item.title}}</td>
						<td>{{item.idCard}}</td>
						<td>{{item.email}}</td>
						<td>{{item.mobile}}</td>
						<td>
							<div ng-if="checkPermission('HR_3')" ng-click="editResourceExterior(item.id)">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>
						</td>
						<td>
							<div ng-click="deleteResource(item.id)" ng-if="checkPermission('HR_4')">
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
							boundary-links="true" on-page-change="getsForPageResource(newPageNumber)">
						</dir-pagination-controls>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>