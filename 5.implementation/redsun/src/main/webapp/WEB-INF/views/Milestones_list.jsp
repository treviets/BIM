
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/MilestonesController.js"></script>
<script src="${contextPath}/static/js/services/MilestonesService.js"></script>

<div ng-controller="milestoneController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by idProject -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idProject" class="inpput-sm" />
			</div>
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.name" class="inpput-sm" />
			</div>
			<!-- filter by description -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.description" class="inpput-sm" />
			</div>
			<!-- filter by creationDate -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.creationDate" class="input-sm" />
			</div>
			<!-- filter by idUser -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idUser" class="inpput-sm" />
			</div>
			<!-- filter by idStatus -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idStatus" class="inpput-sm" />
			</div>
			<!-- filter by idResource -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idResource" class="inpput-sm" />
			</div>
			<!-- filter by result -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.result" class="inpput-sm" />
			</div>
			<!-- filter by comment -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.comment" class="inpput-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idle" class="inpput-sm" />
			</div>
			<!-- filter by idMilestoneType -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idMilestoneType" class="inpput-sm" />
			</div>
			<!-- filter by idActivity -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idActivity" class="inpput-sm" />
			</div>
			<!-- filter by done -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.done" class="inpput-sm" />
			</div>
			<!-- filter by idleDate -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idleDate" class="input-sm" />
			</div>
			<!-- filter by doneDate -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.doneDate" class="input-sm" />
			</div>
			<!-- filter by handled -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.handled" class="inpput-sm" />
			</div>
			<!-- filter by handledDate -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.handledDate" class="input-sm" />
			</div>
			<!-- filter by idVersion -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.idVersion" class="inpput-sm" />
			</div>
			<!-- filter by reference -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.reference" class="inpput-sm" />
			</div>
			<!-- filter by externalReference -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.externalReference" class="inpput-sm" />
			</div>
			<!-- filter by cancelled -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.cancelled" class="inpput-sm" />
			</div>
			<!-- filter by clientId -->
			<div class="col-md-2">
				<input type="text" ng-model="milestone.clientId" class="inpput-sm" />
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
						<th ng-click="sortBy('idProject')"><spring:message code="milestone.idProject"/>
							<span ng-show="sortKey === 'idProject'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('name')"><spring:message code="milestone.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('description')"><spring:message code="milestone.description"/>
							<span ng-show="sortKey === 'description'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('creationDate')"><spring:message code="milestone.creationDate"/>
							<span ng-show="sortKey === 'creationDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idUser')"><spring:message code="milestone.idUser"/>
							<span ng-show="sortKey === 'idUser'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idStatus')"><spring:message code="milestone.idStatus"/>
							<span ng-show="sortKey === 'idStatus'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idResource')"><spring:message code="milestone.idResource"/>
							<span ng-show="sortKey === 'idResource'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('result')"><spring:message code="milestone.result"/>
							<span ng-show="sortKey === 'result'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('comment')"><spring:message code="milestone.comment"/>
							<span ng-show="sortKey === 'comment'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="milestone.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idMilestoneType')"><spring:message code="milestone.idMilestoneType"/>
							<span ng-show="sortKey === 'idMilestoneType'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idActivity')"><spring:message code="milestone.idActivity"/>
							<span ng-show="sortKey === 'idActivity'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('done')"><spring:message code="milestone.done"/>
							<span ng-show="sortKey === 'done'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idleDate')"><spring:message code="milestone.idleDate"/>
							<span ng-show="sortKey === 'idleDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('doneDate')"><spring:message code="milestone.doneDate"/>
							<span ng-show="sortKey === 'doneDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('handled')"><spring:message code="milestone.handled"/>
							<span ng-show="sortKey === 'handled'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('handledDate')"><spring:message code="milestone.handledDate"/>
							<span ng-show="sortKey === 'handledDate'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idVersion')"><spring:message code="milestone.idVersion"/>
							<span ng-show="sortKey === 'idVersion'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('reference')"><spring:message code="milestone.reference"/>
							<span ng-show="sortKey === 'reference'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('externalReference')"><spring:message code="milestone.externalReference"/>
							<span ng-show="sortKey === 'externalReference'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('cancelled')"><spring:message code="milestone.cancelled"/>
							<span ng-show="sortKey === 'cancelled'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('clientId')"><spring:message code="milestone.clientId"/>
							<span ng-show="sortKey === 'clientId'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="milestone.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in milestones | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.idProject}}</td>
						<td>{{item.name}}</td>
						<td>{{item.description}}</td>
						<td>{{item.creationDate}}</td>
						<td>{{item.idUser}}</td>
						<td>{{item.idStatus}}</td>
						<td>{{item.idResource}}</td>
						<td>{{item.result}}</td>
						<td>{{item.comment}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.idMilestoneType}}</td>
						<td>{{item.idActivity}}</td>
						<td>{{item.done}}</td>
						<td>{{item.idleDate}}</td>
						<td>{{item.doneDate}}</td>
						<td>{{item.handled}}</td>
						<td>{{item.handledDate}}</td>
						<td>{{item.idVersion}}</td>
						<td>{{item.reference}}</td>
						<td>{{item.externalReference}}</td>
						<td>{{item.cancelled}}</td>
						<td>{{item.clientId}}</td>
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