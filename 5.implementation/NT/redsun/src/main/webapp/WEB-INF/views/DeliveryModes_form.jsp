<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/DeliveryModesController.js"></script>
<script src="${contextPath}/static/js/services/DeliveryModesService.js"></script>

<div ng-controller="deliveryModeController" ng-init="initEdit(${id})" >
	<form name="frmDeliveryMode" novalidate class="form-inline">
		<h2><spring:message code="deliveryMode.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="deliveryMode.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmDeliveryModes.txtname.$touched || frmname.$dirty) && frmDeliveryModes.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="deliveryMode.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="deliveryMode.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmDeliveryModes.txtname.$invalid">
						<validation-tooltip target="txtname">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="deliveryMode.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmDeliveryModes.txtsortOrder.$touched || frmsortOrder.$dirty) && frmDeliveryModes.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="deliveryMode.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="deliveryMode.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmDeliveryModes.txtsortOrder.$invalid">
						<validation-tooltip target="txtsortOrder">
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
				<label class="control-label" for="txtidle"><spring:message code="deliveryMode.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmDeliveryModes.txtidle.$touched || frmidle.$dirty) && frmDeliveryModes.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="deliveryMode.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="deliveryMode.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmDeliveryModes.txtidle.$invalid">
						<validation-tooltip target="txtidle">
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
