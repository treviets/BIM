<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/CronExecutionsController.js"></script>
<script src="${contextPath}/static/js/services/CronExecutionsService.js"></script>

<div ng-controller="cronExecutionController" ng-init="initEdit(${id})" >
	<form name="frmCronExecution" novalidate class="form-inline">
		<h2><spring:message code="cronExecution.title" /></h2>
		<!-- Top actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
		<!-- Context -->
		<div>
			<!-- cron -->
			<div class="form-group">
				<label class="control-label" for="txtcron"><spring:message code="cronExecution.cron"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmCronExecutions.txtcron.$touched || frmcron.$dirty) && frmCronExecutions.txtcron.$invalid }">
					<input class="form-control" type="text" id="txtcron" name="txtcron" ng-model="cronExecution.cron" />
					<!-- <input class="form-control" type="text" id="txtcron" name="txtcron" ng-model="cronExecution.cron" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmCronExecutions.txtcron.$invalid">
						<validation-tooltip target="txtcron">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- fileExecuted -->
			<div class="form-group">
				<label class="control-label" for="txtfileExecuted"><spring:message code="cronExecution.fileExecuted"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmCronExecutions.txtfileExecuted.$touched || frmfileExecuted.$dirty) && frmCronExecutions.txtfileExecuted.$invalid }">
					<input class="form-control" type="text" id="txtfileExecuted" name="txtfileExecuted" ng-model="cronExecution.fileExecuted" />
					<!-- <input class="form-control" type="text" id="txtfileExecuted" name="txtfileExecuted" ng-model="cronExecution.fileExecuted" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmCronExecutions.txtfileExecuted.$invalid">
						<validation-tooltip target="txtfileExecuted">
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
				<label class="control-label" for="txtidle"><spring:message code="cronExecution.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmCronExecutions.txtidle.$touched || frmidle.$dirty) && frmCronExecutions.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="cronExecution.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="cronExecution.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmCronExecutions.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- fonctionName -->
			<div class="form-group">
				<label class="control-label" for="txtfonctionName"><spring:message code="cronExecution.fonctionName"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmCronExecutions.txtfonctionName.$touched || frmfonctionName.$dirty) && frmCronExecutions.txtfonctionName.$invalid }">
					<input class="form-control" type="text" id="txtfonctionName" name="txtfonctionName" ng-model="cronExecution.fonctionName" />
					<!-- <input class="form-control" type="text" id="txtfonctionName" name="txtfonctionName" ng-model="cronExecution.fonctionName" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmCronExecutions.txtfonctionName.$invalid">
						<validation-tooltip target="txtfonctionName">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- nextTime -->
			<div class="form-group">
				<label class="control-label" for="txtnextTime"><spring:message code="cronExecution.nextTime"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmCronExecutions.txtnextTime.$touched || frmnextTime.$dirty) && frmCronExecutions.txtnextTime.$invalid }">
					<input class="form-control" type="text" id="txtnextTime" name="txtnextTime" ng-model="cronExecution.nextTime" />
					<!-- <input class="form-control" type="text" id="txtnextTime" name="txtnextTime" ng-model="cronExecution.nextTime" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmCronExecutions.txtnextTime.$invalid">
						<validation-tooltip target="txtnextTime">
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
