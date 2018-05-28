<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/TrendsController.js"></script>
<script src="${contextPath}/static/js/services/TrendsService.js"></script>

<div ng-controller="trendController" ng-init="initEdit(${id})" >
	<form name="frmTrend" novalidate class="form-inline">
		<h2><spring:message code="trend.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="trend.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTrends.txtname.$touched || frmname.$dirty) && frmTrends.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="trend.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="trend.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTrends.txtname.$invalid">
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
				<label class="control-label" for="txtcolor"><spring:message code="trend.color"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTrends.txtcolor.$touched || frmcolor.$dirty) && frmTrends.txtcolor.$invalid }">
					<input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="trend.color" />
					<!-- <input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="trend.color" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTrends.txtcolor.$invalid">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="trend.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTrends.txtsortOrder.$touched || frmsortOrder.$dirty) && frmTrends.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="trend.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="trend.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTrends.txtsortOrder.$invalid">
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
				<label class="control-label" for="txticon"><spring:message code="trend.icon"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTrends.txticon.$touched || frmicon.$dirty) && frmTrends.txticon.$invalid }">
					<input class="form-control" type="text" id="txticon" name="txticon" ng-model="trend.icon" />
					<!-- <input class="form-control" type="text" id="txticon" name="txticon" ng-model="trend.icon" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTrends.txticon.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="trend.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTrends.txtidle.$touched || frmidle.$dirty) && frmTrends.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="trend.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="trend.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTrends.txtidle.$invalid">
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
