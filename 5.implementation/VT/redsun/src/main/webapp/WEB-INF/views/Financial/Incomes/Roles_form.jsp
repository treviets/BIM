
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/Financial/Incomes/RolesController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/RolesService.js"></script>

<script src="${contextPath}/static/js/utils/upload-file/upload-files-directive.js"></script>


<div ng-controller="roleController" ng-init="initEdit(${id})" >

	<form name="frmRole" novalidate>
		<div class="panel panel-default">
			<!-- Top actions -->
			<div class="panel-heading">
				<div class="row">
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="role.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="role.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="role.tooltip.create"></spring:message>'>
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
			<!-- Alert Message -->
			<div id="alertMessage" class="alert" style="display: none">
				<strong>{{alertMessage}}</strong>
			</div>
			<!-- Context -->
			<div class="panel-body">
				<div class="row">
					
					<!-- Info -->
					<div class="col-sm-6">
						
						<div class="form-horizontal">
					
							<!-- name -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtname"><spring:message code="role.name"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmRole.txtname.$touched || frmRole.txtname.$dirty || frmDirty) && frmRole.txtname.$invalid }">
									<input class="form-control" type="text" id="txtname" name="txtname" ng-model="role.name" required maxlength="50"></input>
									<div class="input-group-addon" ng-show="frmRole.txtname.$invalid">
										<validation-tooltip target="txtname">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- description -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse" data-target="#pnlDescription"><spring:message code="role.description"></spring:message></button>
								<div id="pnlDescription" class="collapse in">
									<div class="input-group col-sm-12" ng-class="{ 'has-error': (frmRole.txtdescription.$touched || frmdescription.$dirty) && frmRole.txtdescription.$invalid }">
										<textarea class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="role.description" ></textarea >
									</div>
								</div>
							</div>

							<!-- defaultCost -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtdefaultCost"><spring:message code="role.defaultCost"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtdefaultCost.$touched || frmPayment.txtdefaultCost.$dirty || frmDirty) && frmPayment.txtdefaultCost.$invalid }">
									<input class="form-control" type="number" min="0" id="txtdefaultCost" name="txtdefaultCost" ng-model="role.defaultCost" />
								</div>
							</div>

						</div>
					</div>

				</div>
			</div>
			<!-- Bottom actions -->
			<div class="panel-footer">
				<div class="row">
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="role.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="role.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="role.tooltip.create"></spring:message>'>
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
