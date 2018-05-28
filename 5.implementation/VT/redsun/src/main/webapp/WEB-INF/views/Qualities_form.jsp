<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/QualitiesController.js"></script>
<script src="${contextPath}/static/js/services/QualitiesService.js"></script>

<div ng-controller="qualitieController" ng-init="initEdit(${id})" >
	<form name="frmQualitie" novalidate class="form-inline">
		<h2><spring:message code="qualitie.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="qualitie.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmQualities.txtname.$touched || frmname.$dirty) && frmQualities.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="qualitie.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="qualitie.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmQualities.txtname.$invalid">
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
				<label class="control-label" for="txtcolor"><spring:message code="qualitie.color"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmQualities.txtcolor.$touched || frmcolor.$dirty) && frmQualities.txtcolor.$invalid }">
					<input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="qualitie.color" />
					<!-- <input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="qualitie.color" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmQualities.txtcolor.$invalid">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="qualitie.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmQualities.txtsortOrder.$touched || frmsortOrder.$dirty) && frmQualities.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="qualitie.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="qualitie.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmQualities.txtsortOrder.$invalid">
						<validation-tooltip target="txtsortOrder">
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
				<label class="control-label" for="txticon"><spring:message code="qualitie.icon"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmQualities.txticon.$touched || frmicon.$dirty) && frmQualities.txticon.$invalid }">
					<input class="form-control" type="text" id="txticon" name="txticon" ng-model="qualitie.icon" />
					<!-- <input class="form-control" type="text" id="txticon" name="txticon" ng-model="qualitie.icon" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmQualities.txticon.$invalid">
						<validation-tooltip target="txticon">
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
				<label class="control-label" for="txtidle"><spring:message code="qualitie.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmQualities.txtidle.$touched || frmidle.$dirty) && frmQualities.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="qualitie.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="qualitie.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmQualities.txtidle.$invalid">
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
