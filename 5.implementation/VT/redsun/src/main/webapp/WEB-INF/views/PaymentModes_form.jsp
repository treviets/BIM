<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/PaymentModesController.js"></script>
<script src="${contextPath}/static/js/services/PaymentModesService.js"></script>

<div ng-controller="paymentModeController" ng-init="initEdit(${id})" >
	<form name="frmPaymentMode" novalidate class="form-inline">
		<h2><spring:message code="paymentMode.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="paymentMode.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentModes.txtname.$touched || frmname.$dirty) && frmPaymentModes.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="paymentMode.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="paymentMode.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentModes.txtname.$invalid">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="paymentMode.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentModes.txtsortOrder.$touched || frmsortOrder.$dirty) && frmPaymentModes.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="paymentMode.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="paymentMode.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentModes.txtsortOrder.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="paymentMode.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentModes.txtidle.$touched || frmidle.$dirty) && frmPaymentModes.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="paymentMode.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="paymentMode.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentModes.txtidle.$invalid">
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
