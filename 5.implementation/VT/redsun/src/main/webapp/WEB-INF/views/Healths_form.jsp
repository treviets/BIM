<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/HealthsController.js"></script>
<script src="${contextPath}/static/js/services/HealthsService.js"></script>

<div ng-controller="healthController" ng-init="initEdit(${id})" >
	<form name="frmHealth" novalidate class="form-inline">
		<h2><spring:message code="health.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="health.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmHealths.txtname.$touched || frmname.$dirty) && frmHealths.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="health.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="health.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmHealths.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- color -->
			<div class="form-group">
				<label class="control-label" for="txtcolor"><spring:message code="health.color"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmHealths.txtcolor.$touched || frmcolor.$dirty) && frmHealths.txtcolor.$invalid }">
					<input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="health.color" />
					<!-- <input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="health.color" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmHealths.txtcolor.$invalid">
						<validation-tooltip target="txtcolor">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="health.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmHealths.txtsortOrder.$touched || frmsortOrder.$dirty) && frmHealths.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="health.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="health.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmHealths.txtsortOrder.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="health.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmHealths.txtidle.$touched || frmidle.$dirty) && frmHealths.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="health.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="health.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmHealths.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- icon -->
			<div class="form-group">
				<label class="control-label" for="txticon"><spring:message code="health.icon"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmHealths.txticon.$touched || frmicon.$dirty) && frmHealths.txticon.$invalid }">
					<input class="form-control" type="text" id="txticon" name="txticon" ng-model="health.icon" />
					<!-- <input class="form-control" type="text" id="txticon" name="txticon" ng-model="health.icon" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmHealths.txticon.$invalid">
						<validation-tooltip target="txticon">
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
