<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/LikelihoodsController.js"></script>
<script src="${contextPath}/static/js/services/LikelihoodsService.js"></script>

<div ng-controller="likelihoodController" ng-init="initEdit(${id})" >
	<form name="frmLikelihood" novalidate class="form-inline">
		<h2><spring:message code="likelihood.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="likelihood.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmLikelihoods.txtname.$touched || frmname.$dirty) && frmLikelihoods.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="likelihood.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="likelihood.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmLikelihoods.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- value -->
			<div class="form-group">
				<label class="control-label" for="txtvalue"><spring:message code="likelihood.value"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmLikelihoods.txtvalue.$touched || frmvalue.$dirty) && frmLikelihoods.txtvalue.$invalid }">
					<input class="form-control" type="text" id="txtvalue" name="txtvalue" ng-model="likelihood.value" />
					<!-- <input class="form-control" type="text" id="txtvalue" name="txtvalue" ng-model="likelihood.value" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmLikelihoods.txtvalue.$invalid">
						<validation-tooltip target="txtvalue">
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
				<label class="control-label" for="txtcolor"><spring:message code="likelihood.color"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmLikelihoods.txtcolor.$touched || frmcolor.$dirty) && frmLikelihoods.txtcolor.$invalid }">
					<input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="likelihood.color" />
					<!-- <input class="form-control" type="text" id="txtcolor" name="txtcolor" ng-model="likelihood.color" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmLikelihoods.txtcolor.$invalid">
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
				<label class="control-label" for="txtsortOrder"><spring:message code="likelihood.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmLikelihoods.txtsortOrder.$touched || frmsortOrder.$dirty) && frmLikelihoods.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="likelihood.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="likelihood.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmLikelihoods.txtsortOrder.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="likelihood.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmLikelihoods.txtidle.$touched || frmidle.$dirty) && frmLikelihoods.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="likelihood.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="likelihood.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmLikelihoods.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- valuePct -->
			<div class="form-group">
				<label class="control-label" for="txtvaluePct"><spring:message code="likelihood.valuePct"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmLikelihoods.txtvaluePct.$touched || frmvaluePct.$dirty) && frmLikelihoods.txtvaluePct.$invalid }">
					<input class="form-control" type="text" id="txtvaluePct" name="txtvaluePct" ng-model="likelihood.valuePct" />
					<!-- <input class="form-control" type="text" id="txtvaluePct" name="txtvaluePct" ng-model="likelihood.valuePct" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmLikelihoods.txtvaluePct.$invalid">
						<validation-tooltip target="txtvaluePct">
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
