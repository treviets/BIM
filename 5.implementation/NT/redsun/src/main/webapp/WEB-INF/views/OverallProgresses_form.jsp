<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/OverallProgressesController.js"></script>
<script src="${contextPath}/static/js/services/OverallProgressesService.js"></script>

<div ng-controller="overallProgresseController" ng-init="initEdit(${id})" >
	<form name="frmOverallProgresse" novalidate class="form-inline">
		<h2><spring:message code="overallProgresse.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="overallProgresse.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmOverallProgresses.txtname.$touched || frmname.$dirty) && frmOverallProgresses.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="overallProgresse.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="overallProgresse.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmOverallProgresses.txtname.$invalid">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="overallProgresse.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmOverallProgresses.txtsortOrder.$touched || frmsortOrder.$dirty) && frmOverallProgresses.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="overallProgresse.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="overallProgresse.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmOverallProgresses.txtsortOrder.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="overallProgresse.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmOverallProgresses.txtidle.$touched || frmidle.$dirty) && frmOverallProgresses.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="overallProgresse.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="overallProgresse.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmOverallProgresses.txtidle.$invalid">
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
