<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/WorkflowsController.js"></script>
<script src="${contextPath}/static/js/services/WorkflowsService.js"></script>

<div ng-controller="workflowController" ng-init="initEdit(${id})" >
	<form name="frmWorkflow" novalidate class="form-inline">
		<h2><spring:message code="workflow.title" /></h2>
		<!-- Top actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
		<!-- Context -->
		<div>
			<!-- name -->
			<div class="form-group">
				<label class="control-label" for="txtname"><spring:message code="workflow.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmWorkflows.txtname.$touched || frmname.$dirty) && frmWorkflows.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="workflow.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="workflow.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmWorkflows.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- description -->
			<div class="form-group">
				<label class="control-label" for="txtdescription"><spring:message code="workflow.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmWorkflows.txtdescription.$touched || frmdescription.$dirty) && frmWorkflows.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="workflow.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="workflow.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmWorkflows.txtdescription.$invalid">
						<validation-tooltip target="txtdescription">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idle -->
			<div class="form-group">
				<label class="control-label" for="txtidle"><spring:message code="workflow.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmWorkflows.txtidle.$touched || frmidle.$dirty) && frmWorkflows.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="workflow.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="workflow.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmWorkflows.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- workflowUpdate -->
			<div class="form-group">
				<label class="control-label" for="txtworkflowUpdate"><spring:message code="workflow.workflowUpdate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmWorkflows.txtworkflowUpdate.$touched || frmworkflowUpdate.$dirty) && frmWorkflows.txtworkflowUpdate.$invalid }">
					<input class="form-control" type="text" id="txtworkflowUpdate" name="txtworkflowUpdate" ng-model="workflow.workflowUpdate" />
					<!-- <input class="form-control" type="text" id="txtworkflowUpdate" name="txtworkflowUpdate" ng-model="workflow.workflowUpdate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmWorkflows.txtworkflowUpdate.$invalid">
						<validation-tooltip target="txtworkflowUpdate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- sortOrder -->
			<div class="form-group">
				<label class="control-label" for="txtsortOrder"><spring:message code="workflow.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmWorkflows.txtsortOrder.$touched || frmsortOrder.$dirty) && frmWorkflows.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="workflow.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="workflow.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmWorkflows.txtsortOrder.$invalid">
						<validation-tooltip target="txtsortOrder">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
		</div>
		<!-- Bottom actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
	</form>
</div>
