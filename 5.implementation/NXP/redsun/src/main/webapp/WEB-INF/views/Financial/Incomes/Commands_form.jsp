
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/Financial/Incomes/CommandsController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/CommandsService.js"></script>

<script src="${contextPath}/static/js/utils/upload-file/upload-files-directive.js"></script>


<div ng-controller="commandController" ng-init="initEdit(${id})" >

	<form name="frmCommand" novalidate>
		<div class="panel panel-default">
			<!-- Top actions -->
			<div class="panel-heading">
			    <div class="row">
				    <ul class="nav navbar-nav">
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/call-for-tender/list')">Call for tender</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/tender/list')">Tender</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/expenses/project/list')">Project expense</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/expenses/individual/list')">Individual expense</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/quotation/list')">Quotation</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/command/list')">Order</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/bill/list')">Bill</li>
				  		<li class="list-group-item list-group-item-info" ng-click="gotoUrl('http://localhost:8080/redsun/payment/list')">Payment</li>
				    </ul>
			    </div>
			    <hr>
				<div class="row">
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="command.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="command.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="command.tooltip.create"></spring:message>'>
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
								<label class="control-label col-sm-6" for="txtname"><spring:message code="command.name"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtname.$touched || frmCommand.txtname.$dirty || frmDirty) && frmCommand.txtname.$invalid }">
									<input class="form-control" type="text" id="txtname" name="txtname" ng-model="command.name" required maxlength="50"></input>
									<div class="input-group-addon" ng-show="frmCommand.txtname.$invalid">
										<validation-tooltip target="txtname">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
							
							<!-- idProject -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidProject"><spring:message code="command.projectName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtidProject.$touched || frmCommand.txtidProject.$dirty || frmDirty) && frmCommand.txtidProject.$invalid }">
									<select class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="command.idProject" ng-options="project.id as project.name for project in projects" required></select>
									<div class="input-group-addon" ng-show="frmCommand.txtidProject.$invalid">
										<validation-tooltip target="txtidProject">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idCommandType -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidCommandType"><spring:message code="command.commandTypeName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtidCommandType.$touched || frmCommand.txtidCommandType.$dirty || frmDirty) && frmCommand.txtidCommandType.$invalid }">
									<select class="form-control" type="text" id="txtidCommandType" name="txtidCommandType" ng-model="command.idCommandType" ng-options="commandType.id as commandType.name for commandType in commandTypes" required ></select>
									<div class="input-group-addon" ng-show="frmCommand.txtidCommandType.$invalid">
										<validation-tooltip target="txtidCommandType">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idClient -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidClient"><spring:message code="command.clientName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtidClient.$touched || frmCommand.txtidClient.$dirty || frmDirty) && frmCommand.txtidClient.$invalid }">
									<select class="form-control" type="text" id="txtidClient" name="txtidClient" ng-model="command.idClient" ng-options="customer.id as customer.name for customer in customers" required ></select>
									<div class="input-group-addon" ng-show="frmCommand.txtidClient.$invalid">
										<validation-tooltip target="txtidClient">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idContact -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidContact"><spring:message code="command.contactName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtidContact.$touched || frmCommand.txtidContact.$dirty || frmDirty) && frmCommand.txtidContact.$invalid }">
									<select class="form-control" type="text" id="txtidContact" name="txtidContact" ng-model="command.idContact" ng-options="contact.id as contact.name for contact in contacts" required ></select>
									<div class="input-group-addon" ng-show="frmCommand.txtidContact.$invalid">
										<validation-tooltip target="txtidContact">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idUser -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidUser"><spring:message code="command.userName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtidUser.$touched || frmCommand.txtidUser.$dirty || frmDirty) && frmCommand.txtidUser.$invalid }">
									<select class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="command.idUser" ng-options="user.id as user.name for user in users" required ></select>
									<div class="input-group-addon" ng-show="frmCommand.txtidUser.$invalid">
										<validation-tooltip target="txtidUser">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idStatus -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidStatus"><spring:message code="command.statusName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtidStatus.$touched || frmCommand.txtidStatus.$dirty || frmDirty) && frmCommand.txtidStatus.$invalid }">
									<select class="form-control" type="text" id="txtidStatus" name="txtidStatus" ng-model="command.idStatus" ng-options="status.id as status.name for status in statuses" required ></select>
									<div class="input-group-addon" ng-show="frmCommand.txtidStatus.$invalid">
										<validation-tooltip target="txtidStatus">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- description -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse" data-target="#pnlDescription"><spring:message code="command.description"></spring:message></button>
								<div id="pnlDescription" class="collapse in">
									<div class="input-group col-sm-12" ng-class="{ 'has-error': (frmCommand.txtdescription.$touched || frmdescription.$dirty) && frmCommand.txtdescription.$invalid }">
										<textarea class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="command.description" ></textarea >
									</div>
								</div>
							</div>

						</div>
					</div>
					
					<!-- Extend -->
					<div class="col-sm-6">
					
						<div class="form-horizontal">
							
							<!-- handledDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txthandledDate"><spring:message code="command.handledDate"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txthandledDate.$touched || frmCommand.txthandledDate.$dirty || frmDirty) && frmCommand.txthandledDate.$invalid }">
									<input type="text" class="form-control" id="txthandledDate" name="txthandledDate" ng-model="command.handledDate" ng-change="dateChangeHandler(command.handledDate, frmCommand.txthandledDate, 'txthandledDate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.handledDate.isOpen" datepicker-options="dateOptions" close-text="Close" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.handledDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>
							
							<!-- initialEndDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtinitialEndDate"><spring:message code="command.initialEndDate"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtinitialEndDate.$touched || frmCommand.txtinitialEndDate.$dirty || frmDirty) && frmCommand.txtinitialEndDate.$invalid }">
									<input type="text" class="form-control" id="txtinitialEndDate" name="txtinitialEndDate" ng-model="command.initialEndDate" ng-change="dateChangeHandler(command.initialEndDate, frmCommand.txtinitialEndDate, 'txtinitialEndDate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.initialEndDate.isOpen" datepicker-options="dateOptions" close-text="Close" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.initialEndDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- doneDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtdoneDate"><spring:message code="command.doneDate"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtdoneDate.$touched || frmCommand.txtdoneDate.$dirty || frmDirty) && frmCommand.txtdoneDate.$invalid }">
									<input type="text" class="form-control" id="txtdoneDate" name="txtdoneDate" ng-model="command.doneDate" ng-change="dateChangeHandler(command.doneDate, frmCommand.txtdoneDate, 'txtdoneDate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.doneDate.isOpen" datepicker-options="dateOptions" close-text="Close" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.doneDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- cancelled -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtcancelled"><spring:message code="command.cancelled"></spring:message></label>
								<div class="control-label col-sm-6" >
									<input type="checkbox" class="pull-left" ng-model="command.cancelled" ng-true-value="1" ng-false-value="0" ></input>
								</div>
							</div>

							<!-- untaxedAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtuntaxedAmount"><spring:message code="command.untaxedAmount"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtuntaxedAmount.$touched || frmCommand.txtuntaxedAmount.$dirty || frmDirty) && frmCommand.txtuntaxedAmount.$invalid }">
									<input class="form-control" type="number" min="0" id="txtuntaxedAmount" name="txtuntaxedAmount" ng-model="command.untaxedAmount" ng-change="calculateAmount()" />
								</div>
							</div>
							
							<!-- tax -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txttax"><spring:message code="command.tax"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txttax.$touched || frmCommand.txttax.$dirty || frmDirty) && frmCommand.txttax.$invalid }">
									<input class="form-control" type="number" min="0" id="txttax" name="txttax" ng-model="command.tax" ng-change="calculateAmount()" />
								</div>
							</div>
							
							<!-- fullAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtfullAmount"><spring:message code="command.fullAmount"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmCommand.txtfullAmount.$touched || frmCommand.txtfullAmount.$dirty || frmDirty) && frmCommand.txtfullAmount.$invalid }">
									<input class="form-control" type="number" min="0" id="txtfullAmount" name="txtfullAmount" ng-model="command.fullAmount" readonly />
								</div>
							</div>

							<!-- attachments -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="attachments"><spring:message code="command.attachments"></spring:message></label>
								<div class="input-group col-sm-6">
									<input type="file" class="form-control btn btn-default" ng-click="fileClear($event)" id="attachments" name="attachments" multiple ng-files="addAttachments($files)" />
									<table class="table table-bordered">
										<tr ng-repeat="file in command.attachments">
											<td>{{file.fileName}}</td>
											<td>
												<div ng-click="deleteAttachments(file.fileName)"><span class="glyphicon glyphicon-trash"></span></div>
											</td>
											<td>
												<div ng-if="file.id != null"><div ng-click="downloadAttachments(command.id, file.fileName)"><span class="glyphicon glyphicon-download"></span></div></div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						
						</div>
					</div>

				</div>
			
				<div class="row">
					<div class="col-sm-6">
					
						<div class="form-horizontal">

							<!-- additionalInfo -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse" data-target="#pnlAdditionalInfo"><spring:message code="command.additionalInfo"></spring:message></button>
								<div id="pnlAdditionalInfo" class="collapse in">
									<div class="input-group col-sm-12" ng-class="{ 'has-error': (frmCommand.txtadditionalInfo.$touched || frmadditionalInfo.$dirty) && frmCommand.txtadditionalInfo.$invalid }">
										<div ckeditor="options" class="form-control" type="text" id="txtadditionalInfo" name="txtadditionalInfo" ng-model="command.additionalInfo" ></div>
									</div>
								</div>
							</div>
						
						</div>
					</div>
					<div class="col-sm-6">
					
						<div class="form-horizontal">

							<!-- comment -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse" data-target="#pnlComment"><spring:message code="command.comment"></spring:message></button>
								<div id="pnlComment" class="collapse in">
									<div class="input-group" ng-class="{ 'has-error': (frmCommand.txtcomment.$touched || frmcomment.$dirty) && frmCommand.txtcomment.$invalid }">
										<div ckeditor="options" class="form-control" type="text" id="txtcomment" name="txtcomment" ng-model="command.comment" ></div>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				</div>

			</div>
			<!-- Bottom actions -->
			<div class="panel-footer">
				<div class="row">
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="command.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="command.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="command.tooltip.create"></spring:message>'>
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
