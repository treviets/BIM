<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/ProfilesController.js"></script>
<script src="${contextPath}/static/js/services/ProfilesService.js"></script>

<div ng-controller="profileController" ng-init="initEdit(${id})" >
	<form name="frmProfile" novalidate class="form-inline">
		<h2><spring:message code="profile.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="profile.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProfiles.txtname.$touched || frmname.$dirty) && frmProfiles.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="profile.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="profile.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProfiles.txtname.$invalid">
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
				<label class="control-label" for="txtdescription"><spring:message code="profile.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProfiles.txtdescription.$touched || frmdescription.$dirty) && frmProfiles.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="profile.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="profile.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProfiles.txtdescription.$invalid">
						<validation-tooltip target="txtdescription">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- profileCode -->
			<div class="form-group">
				<label class="control-label" for="txtprofileCode"><spring:message code="profile.profileCode"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProfiles.txtprofileCode.$touched || frmprofileCode.$dirty) && frmProfiles.txtprofileCode.$invalid }">
					<input class="form-control" type="text" id="txtprofileCode" name="txtprofileCode" ng-model="profile.profileCode" />
					<!-- <input class="form-control" type="text" id="txtprofileCode" name="txtprofileCode" ng-model="profile.profileCode" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProfiles.txtprofileCode.$invalid">
						<validation-tooltip target="txtprofileCode">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="profile.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProfiles.txtsortOrder.$touched || frmsortOrder.$dirty) && frmProfiles.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="profile.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="profile.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProfiles.txtsortOrder.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="profile.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProfiles.txtidle.$touched || frmidle.$dirty) && frmProfiles.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="profile.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="profile.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProfiles.txtidle.$invalid">
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
