<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/ReferencablesController.js"></script>
<script src="${contextPath}/static/js/services/ReferencablesService.js"></script>

<div ng-controller="referencableController" ng-init="initEdit(${id})" >
	<form name="frmReferencable" novalidate class="form-inline">
		<h2><spring:message code="referencable.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="referencable.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmReferencables.txtname.$touched || frmname.$dirty) && frmReferencables.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="referencable.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="referencable.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmReferencables.txtname.$invalid">
						<validation-tooltip target="txtname">
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
				<label class="control-label" for="txtidle"><spring:message code="referencable.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmReferencables.txtidle.$touched || frmidle.$dirty) && frmReferencables.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="referencable.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="referencable.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmReferencables.txtidle.$invalid">
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
