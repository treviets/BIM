<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/TeamsController.js"></script>
<script src="${contextPath}/static/js/services/TeamsService.js"></script>

<div ng-controller="teamController" ng-init="initEdit(${id})" >
	<form name="frmTeam" novalidate class="form-inline">
		<h2><spring:message code="team.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="team.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTeams.txtname.$touched || frmname.$dirty) && frmTeams.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="team.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="team.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTeams.txtname.$invalid">
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
				<label class="control-label" for="txtdescription"><spring:message code="team.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTeams.txtdescription.$touched || frmdescription.$dirty) && frmTeams.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="team.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="team.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTeams.txtdescription.$invalid">
						<validation-tooltip target="txtdescription">
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
				<label class="control-label" for="txtidle"><spring:message code="team.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTeams.txtidle.$touched || frmidle.$dirty) && frmTeams.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="team.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="team.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTeams.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idResource -->
			<div class="form-group">
				<label class="control-label" for="txtidResource"><spring:message code="team.idResource"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTeams.txtidResource.$touched || frmidResource.$dirty) && frmTeams.txtidResource.$invalid }">
					<input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="team.idResource" />
					<!-- <input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="team.idResource" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTeams.txtidResource.$invalid">
						<validation-tooltip target="txtidResource">
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
