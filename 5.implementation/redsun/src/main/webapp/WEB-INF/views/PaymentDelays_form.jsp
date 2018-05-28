<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/PaymentDelaysController.js"></script>
<script src="${contextPath}/static/js/services/PaymentDelaysService.js"></script>

<div ng-controller="paymentDelayController" ng-init="initEdit(${id})" >
	<form name="frmPaymentDelay" novalidate class="form-inline">
		<h2><spring:message code="paymentDelay.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="paymentDelay.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentDelays.txtname.$touched || frmname.$dirty) && frmPaymentDelays.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="paymentDelay.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="paymentDelay.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentDelays.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- days -->
			<div class="form-group">
				<label class="control-label" for="txtdays"><spring:message code="paymentDelay.days"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentDelays.txtdays.$touched || frmdays.$dirty) && frmPaymentDelays.txtdays.$invalid }">
					<input class="form-control" type="text" id="txtdays" name="txtdays" ng-model="paymentDelay.days" />
					<!-- <input class="form-control" type="text" id="txtdays" name="txtdays" ng-model="paymentDelay.days" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentDelays.txtdays.$invalid">
						<validation-tooltip target="txtdays">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- endOfMonth -->
			<div class="form-group">
				<label class="control-label" for="txtendOfMonth"><spring:message code="paymentDelay.endOfMonth"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentDelays.txtendOfMonth.$touched || frmendOfMonth.$dirty) && frmPaymentDelays.txtendOfMonth.$invalid }">
					<input class="form-control" type="text" id="txtendOfMonth" name="txtendOfMonth" ng-model="paymentDelay.endOfMonth" />
					<!-- <input class="form-control" type="text" id="txtendOfMonth" name="txtendOfMonth" ng-model="paymentDelay.endOfMonth" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentDelays.txtendOfMonth.$invalid">
						<validation-tooltip target="txtendOfMonth">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="paymentDelay.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentDelays.txtsortOrder.$touched || frmsortOrder.$dirty) && frmPaymentDelays.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="paymentDelay.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="paymentDelay.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentDelays.txtsortOrder.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="paymentDelay.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmPaymentDelays.txtidle.$touched || frmidle.$dirty) && frmPaymentDelays.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="paymentDelay.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="paymentDelay.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmPaymentDelays.txtidle.$invalid">
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
