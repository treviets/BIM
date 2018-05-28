<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/PlanningModesController.js"></script>
<script src="${contextPath}/static/js/services/PlanningModesService.js"></script>

<div ng-controller="planningModeController" ng-init="initEdit(${id})" >
	<form name="frmPlanningMode" novalidate class="form-inline">
		<h2><spring:message code="planningMode.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="planningMode.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtname.$touched || frmname.$dirty) && frmPlanningModes.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="planningMode.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="planningMode.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- code -->
			<div class="form-group">
				<label class="control-label" for="txtcode"><spring:message code="planningMode.code"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtcode.$touched || frmcode.$dirty) && frmPlanningModes.txtcode.$invalid }">
					<input class="form-control" type="text" id="txtcode" name="txtcode" ng-model="planningMode.code" />
					<!-- <input class="form-control" type="text" id="txtcode" name="txtcode" ng-model="planningMode.code" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtcode.$invalid">
						<validation-tooltip target="txtcode">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="planningMode.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtsortOrder.$touched || frmsortOrder.$dirty) && frmPlanningModes.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="planningMode.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="planningMode.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtsortOrder.$invalid">
						<validation-tooltip target="txtsortOrder">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- mandatoryStartDate -->
			<div class="form-group">
				<label class="control-label" for="txtmandatoryStartDate"><spring:message code="planningMode.mandatoryStartDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtmandatoryStartDate.$touched || frmmandatoryStartDate.$dirty) && frmPlanningModes.txtmandatoryStartDate.$invalid }">
					<input class="form-control" type="text" id="txtmandatoryStartDate" name="txtmandatoryStartDate" ng-model="planningMode.mandatoryStartDate" />
					<!-- <input class="form-control" type="text" id="txtmandatoryStartDate" name="txtmandatoryStartDate" ng-model="planningMode.mandatoryStartDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtmandatoryStartDate.$invalid">
						<validation-tooltip target="txtmandatoryStartDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- mandatoryEndDate -->
			<div class="form-group">
				<label class="control-label" for="txtmandatoryEndDate"><spring:message code="planningMode.mandatoryEndDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtmandatoryEndDate.$touched || frmmandatoryEndDate.$dirty) && frmPlanningModes.txtmandatoryEndDate.$invalid }">
					<input class="form-control" type="text" id="txtmandatoryEndDate" name="txtmandatoryEndDate" ng-model="planningMode.mandatoryEndDate" />
					<!-- <input class="form-control" type="text" id="txtmandatoryEndDate" name="txtmandatoryEndDate" ng-model="planningMode.mandatoryEndDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtmandatoryEndDate.$invalid">
						<validation-tooltip target="txtmandatoryEndDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- applyTo -->
			<div class="form-group">
				<label class="control-label" for="txtapplyTo"><spring:message code="planningMode.applyTo"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtapplyTo.$touched || frmapplyTo.$dirty) && frmPlanningModes.txtapplyTo.$invalid }">
					<input class="form-control" type="text" id="txtapplyTo" name="txtapplyTo" ng-model="planningMode.applyTo" />
					<!-- <input class="form-control" type="text" id="txtapplyTo" name="txtapplyTo" ng-model="planningMode.applyTo" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtapplyTo.$invalid">
						<validation-tooltip target="txtapplyTo">
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
				<label class="control-label" for="txtidle"><spring:message code="planningMode.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtidle.$touched || frmidle.$dirty) && frmPlanningModes.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="planningMode.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="planningMode.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- mandatoryDuration -->
			<div class="form-group">
				<label class="control-label" for="txtmandatoryDuration"><spring:message code="planningMode.mandatoryDuration"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPlanningModes.txtmandatoryDuration.$touched || frmmandatoryDuration.$dirty) && frmPlanningModes.txtmandatoryDuration.$invalid }">
					<input class="form-control" type="text" id="txtmandatoryDuration" name="txtmandatoryDuration" ng-model="planningMode.mandatoryDuration" />
					<!-- <input class="form-control" type="text" id="txtmandatoryDuration" name="txtmandatoryDuration" ng-model="planningMode.mandatoryDuration" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPlanningModes.txtmandatoryDuration.$invalid">
						<validation-tooltip target="txtmandatoryDuration">
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
