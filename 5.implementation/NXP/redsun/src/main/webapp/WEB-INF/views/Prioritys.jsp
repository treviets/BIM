<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />
<div ng-controller="priorityCtrl" ng-init="getPriorities(1)">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" ng-model="name" class="form-control input-sm"
							placeholder=<spring:message code="priority.nav.name"/>>
					</div>
					<button type="button" ng-click="getPriorities(1);"
						class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</form>
			</div>
			<div class="panel-body">

				<div class="panel panel-default">
					<div class="panel-heading clearfix">
						<h3 class="panel-title pull-left">
							<spring:message code="priority.title" />
						</h3>
						<div class="btn-group pull-right">
							<button type="button" class="btn btn-default"
								data-toggle="tooltip"
								title=<spring:message code="priority.tooltip.create"/>>
								<span class="glyphicon glyphicon-plus"></span>
							</button>
							<button type="button" ng-click="save(${id})"
								class="btn btn-default" data-toggle="tooltip"
								title=<spring:message code="priority.tooltip.save"/>>
								<span class="glyphicon glyphicon-floppy-disk"></span>
							</button>
							<button type="button" class="btn btn-default"
								data-toggle="tooltip"
								title=<spring:message code="priority.tooltip.print"/>>
								<span class="glyphicon glyphicon-print"></span>
							</button>
							<button type="button" class="btn btn-default"
								data-toggle="tooltip"
								title=<spring:message code="priority.tooltip.export"/>>
								<span class="glyphicon glyphicon-export"></span>
							</button>
							<button type="button" class="btn btn-default"
								data-toggle="tooltip"
								title=<spring:message code="priority.tooltip.copy"/>>
								<span class="glyphicon glyphicon-copy"></span>
							</button>
							<button type="button" class="btn btn-default"
								data-toggle="tooltip"
								title=<spring:message code="priority.tooltip.send"/>>
								<span class="glyphicon glyphicon-envelope">
							</button>

						</div>
						<br> <br>
						<div class="btn-group pull-left" ng-init="initEditPriority(${id})">
							<table class="table table-striped table-hover table-bordered">

								<tr>
									<td><input class="form-control" type="text"
										placeholder="Name" ng-model="priority.name" /></td>
									<td><input class="form-control" type="text"
										placeholder="Value" ng-model="priority.value" /></td>
									<td><input class="form-control" type="text"
										placeholder="Color" ng-model="priority.color" /></td>
									<td><input class="form-control" type="text"
										placeholder="SortOrder" ng-model="priority.sortOrder" /></td>
									<td><input class="form-control" type="text"
										placeholder="Idle" ng-model="priority.idle" /></td>
								</tr>
							</table>
						</div>
					</div>

				</div>
				<div class="table-responsive">

					<table class="table table-striped table-hover table-bordered">

						<thead>
							<tr>
								<th ng-click="sort('priority.name')"><spring:message
										code="priority.name" /> <span class="glyphicon sort-icon"
									ng-show="sortKey=='priority.name'"
									ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
								</th>
								<th ng-click="sort('priority.value')"><spring:message
										code="priority.value" /> <span class="glyphicon sort-icon"
									ng-show="sortKey=='priority.value'"
									ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
								</th>
								<th ng-click="sort('priority.color')"><spring:message
										code="priority.color" /> <span class="glyphicon sort-icon"
									ng-show="sortKey=='priority.color'"
									ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
								</th>
								<th ng-click="sort('priority.sortorder')"><spring:message
										code="priority.sortorder" /> <span
									class="glyphicon sort-icon"
									ng-show="sortKey=='priority.sortorder'"
									ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
								</th>
								<th ng-click="sort('priority.idle')"><spring:message
										code="priority.idle" /> <span class="glyphicon sort-icon"
									ng-show="sortKey=='priority.idle'"
									ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
								</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-show="priorities.length <= 0">
								<td colspan="5" style="text-align: center;">Loading new
									data!!</td>
							</tr>
							<tr
								dir-paginate="item in priorities | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage"
								total-items="totalCount">
								<td>{{item.name}}</td>
								<td>{{item.value}}</td>
								<td>{{item.color}}</td>
								<td>{{item.sortOrder}}</td>
								<td>{{item.idle}}</td>

								<td><div ng-click="edit(item.id)">
										<span class="glyphicon glyphicon-pencil"></span>
									</div></td>
								<td><div ng-click="delete(item.id)">
										<span class="glyphicon glyphicon-trash"></span>
									</div></td>
							</tr>
						</tbody>
					</table>
					<div class="panel-body">
						<div class="pull-left">
							<dir-pagination-controls max-size="8" direction-links="true"
								boundary-links="true"
								on-page-change="getPriorities(newPageNumber)">
							</dir-pagination-controls>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>