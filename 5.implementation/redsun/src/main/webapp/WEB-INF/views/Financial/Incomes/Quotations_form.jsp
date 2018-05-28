
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<script
	src="${contextPath}/static/js/controllers/Financial/Incomes/QuotationsController.js"></script>
<script
	src="${contextPath}/static/js/services/Financial/Incomes/QuotationsService.js"></script>
<script
	src="${contextPath}/static/js/utils/upload-file/upload-files-directive.js"></script>

<div ng-controller="quotationController" ng-init="initEdit(${id})">

	<form name="frmQuotation" novalidate>
		<div class="panel panel-default">
			<!-- Top actions -->
			<div class="panel-heading">
				<div class="row">
					<ul class="nav navbar-nav">
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/call-for-tender/list')">Call
							for tender</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/tender/list')">Tender</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/expenses/project/list')">Project
							expense</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/expenses/individual/list')">Individual
							expense</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/quotation/list')">Quotation</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/command/list')">Order</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/bill/list')">Bill</li>
						<li class="list-group-item list-group-item-info"
							ng-click="gotoUrl('http://localhost:8080/redsun/payment/list')">Payment</li>
					</ul>
				</div>
				<hr>
				<div class="row">

					<div class="btn btn-link pull-left" ng-click="list()">
						<spring:message code="quotation.title" />
					</div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()"
							data-toggle="tooltip"
							title='<spring:message code="quotation.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()"
							data-toggle="tooltip"
							title='<spring:message code="quotation.tooltip.create"></spring:message>'>
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
								<label class="control-label col-sm-6" for="txtname"><spring:message
										code="quotation.name"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtname.$touched || frmQuotation.txtname.$dirty || frmDirty) && frmQuotation.txtname.$invalid }">
									<input class="form-control" type="text" id="txtname"
										name="txtname" ng-model="quotation.name" required
										maxlength="50"></input>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtname.$invalid">
										<validation-tooltip target="txtname">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- idProject -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidProject"><spring:message
										code="quotation.projectName"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtidProject.$touched || frmQuotation.txtidProject.$dirty || frmDirty) && frmQuotation.txtidProject.$invalid }">
									<select class="form-control" type="text" id="txtidProject"
										name="txtidProject" ng-model="quotation.idProject"
										ng-options="project.id as project.name for project in projects"
										required></select>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtidProject.$invalid">
										<validation-tooltip target="txtidProject">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- idQuotationType -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidQuotationType"><spring:message
										code="quotation.quotationTypeName"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtidQuotationType.$touched || frmQuotation.txtidQuotationType.$dirty || frmDirty) && frmQuotation.txtidQuotationType.$invalid }">
									<select class="form-control" type="text"
										id="txtidQuotationType" name="txtidQuotationType"
										ng-model="quotation.idQuotationType"
										ng-options="quotationType.id as quotationType.name for quotationType in quotationTypes"
										required></select>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtidQuotationType.$invalid">
										<validation-tooltip target="txtidQuotationType">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- idClient -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidClient"><spring:message
										code="quotation.clientName"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtidClient.$touched || frmQuotation.txtidClient.$dirty || frmDirty) && frmQuotation.txtidClient.$invalid }">
									<select class="form-control" type="text" id="txtidClient"
										name="txtidClient" ng-model="quotation.idClient"
										ng-options="customer.id as customer.name for customer in customers"
										required></select>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtidClient.$invalid">
										<validation-tooltip target="txtidClient">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- idContact -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidContact"><spring:message
										code="quotation.contactName"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtidContact.$touched || frmQuotation.txtidContact.$dirty || frmDirty) && frmQuotation.txtidContact.$invalid }">
									<select class="form-control" type="text" id="txtidContact"
										name="txtidContact" ng-model="quotation.idContact"
										ng-options="contact.id as contact.name for contact in contacts"
										required></select>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtidContact.$invalid">
										<validation-tooltip target="txtidContact">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- idUser -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidUser"><spring:message
										code="quotation.userName"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtidUser.$touched || frmQuotation.txtidUser.$dirty || frmDirty) && frmQuotation.txtidUser.$invalid }">
									<select class="form-control" type="text" id="txtidUser"
										name="txtidUser" ng-model="quotation.idUser"
										ng-options="user.id as user.name for user in users" required></select>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtidUser.$invalid">
										<validation-tooltip target="txtidUser">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- idStatus -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidStatus"><spring:message
										code="quotation.statusName"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtidStatus.$touched || frmQuotation.txtidStatus.$dirty || frmDirty) && frmQuotation.txtidStatus.$invalid }">
									<select class="form-control" type="text" id="txtidStatus"
										name="txtidStatus" ng-model="quotation.idStatus"
										ng-options="status.id as status.name for status in statuses"
										required></select>
									<div class="input-group-addon"
										ng-show="frmQuotation.txtidStatus.$invalid">
										<validation-tooltip target="txtidStatus">
										<ul class="list-unstyled">
											<li validation-message ng-if="$field.$error.required">This
												is a required field.</li>
										</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- description -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#pnlDescription">
									<spring:message code="quotation.description"></spring:message>
								</button>
								<div id="pnlDescription" class="collapse in">
									<div class="input-group col-sm-12"
										ng-class="{ 'has-error': (frmQuotation.txtdescription.$touched || frmdescription.$dirty) && frmQuotation.txtdescription.$invalid }">
										<textarea class="form-control" type="text" id="txtdescription"
											name="txtdescription" ng-model="quotation.description"></textarea>
									</div>
								</div>
							</div>

						</div>
					</div>

					<!-- Extend -->
					<div class="col-sm-6">

						<div class="form-horizontal">

							<!-- sendDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtsendDate"><spring:message
										code="quotation.sendDate"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtsendDate.$touched || frmQuotation.txtsendDate.$dirty || frmDirty) && frmQuotation.txtsendDate.$invalid }">
									<input type="text" class="form-control" id="txtsendDate"
										name="txtsendDate" ng-model="quotation.sendDate"
										ng-change="dateChangeHandler(quotation.sendDate, frmQuotation.txtsendDate, 'txtsendDate')"
										uib-datepicker-popup="dd/MM/yyyy"
										is-open="DateData.sendDate.isOpen"
										datepicker-options="dateOptions" close-text="Close"></input> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="openDatePicker(DateData.sendDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- handledDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txthandledDate"><spring:message
										code="quotation.handledDate"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txthandledDate.$touched || frmQuotation.txthandledDate.$dirty || frmDirty) && frmQuotation.txthandledDate.$invalid }">
									<input type="text" class="form-control" id="txthandledDate"
										name="txthandledDate" ng-model="quotation.handledDate"
										ng-change="dateChangeHandler(quotation.handledDate, frmQuotation.txthandledDate, 'txthandledDate')"
										uib-datepicker-popup="dd/MM/yyyy"
										is-open="DateData.handledDate.isOpen"
										datepicker-options="dateOptions" close-text="Close" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="openDatePicker(DateData.handledDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- initialEndDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtinitialEndDate"><spring:message
										code="quotation.initialEndDate"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtinitialEndDate.$touched || frmQuotation.txtinitialEndDate.$dirty || frmDirty) && frmQuotation.txtinitialEndDate.$invalid }">
									<input type="text" class="form-control" id="txtinitialEndDate"
										name="txtinitialEndDate" ng-model="quotation.initialEndDate"
										ng-change="dateChangeHandler(quotation.initialEndDate, frmQuotation.txtinitialEndDate, 'txtinitialEndDate')"
										uib-datepicker-popup="dd/MM/yyyy"
										is-open="DateData.initialEndDate.isOpen"
										datepicker-options="dateOptions" close-text="Close" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="openDatePicker(DateData.initialEndDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- doneDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtdoneDate"><spring:message
										code="quotation.doneDate"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtdoneDate.$touched || frmQuotation.txtdoneDate.$dirty || frmDirty) && frmQuotation.txtdoneDate.$invalid }">
									<input type="text" class="form-control" id="txtdoneDate"
										name="txtdoneDate" ng-model="quotation.doneDate"
										ng-change="dateChangeHandler(quotation.doneDate, frmQuotation.txtdoneDate, 'txtdoneDate')"
										uib-datepicker-popup="dd/MM/yyyy"
										is-open="DateData.doneDate.isOpen"
										datepicker-options="dateOptions" close-text="Close" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="openDatePicker(DateData.doneDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- cancelled -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtcancelled"><spring:message
										code="quotation.cancelled"></spring:message></label>
								<div class="control-label col-sm-6">
									<input type="checkbox" class="pull-left"
										ng-model="quotation.cancelled" ng-true-value="1"
										ng-false-value="0"></input>
								</div>
							</div>

							<!-- untaxedAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtuntaxedAmount"><spring:message
										code="quotation.untaxedAmount"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtuntaxedAmount.$touched || frmQuotation.txtuntaxedAmount.$dirty || frmDirty) && frmQuotation.txtuntaxedAmount.$invalid }">
									<input class="form-control" type="number" min="0"
										id="txtuntaxedAmount" name="txtuntaxedAmount"
										ng-model="quotation.untaxedAmount"
										ng-change="calculateAmount()" />
								</div>
							</div>

							<!-- tax -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txttax"><spring:message
										code="quotation.tax"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txttax.$touched || frmQuotation.txttax.$dirty || frmDirty) && frmQuotation.txttax.$invalid }">
									<input class="form-control" type="number" min="0" id="txttax"
										name="txttax" ng-model="quotation.tax"
										ng-change="calculateAmount()" />
								</div>
							</div>

							<!-- fullAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtfullAmount"><spring:message
										code="quotation.fullAmount"></spring:message></label>
								<div class="input-group col-sm-6"
									ng-class="{ 'has-error': (frmQuotation.txtfullAmount.$touched || frmQuotation.txtfullAmount.$dirty || frmDirty) && frmQuotation.txtfullAmount.$invalid }">
									<input class="form-control" type="number" min="0"
										id="txtfullAmount" name="txtfullAmount"
										ng-model="quotation.fullAmount" readonly />
								</div>
							</div>

							<!-- attachments -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="attachments"><spring:message
										code="quotation.attachments"></spring:message></label>
								<div class="input-group col-sm-6">
									<input type="file" class="form-control btn btn-default"
										ng-click="fileClear($event)" id="attachments"
										name="attachments" multiple ng-files="addAttachments($files)" />
									<table class="table table-bordered">
										<tr ng-repeat="file in quotation.attachments">
											<td>{{file.fileName}}</td>
											<td>
												<div ng-click="deleteAttachments(file.fileName)">
													<span class="glyphicon glyphicon-trash"></span>
												</div>
											</td>
											<td>
												<div ng-if="file.id != null">
													<div
														ng-click="downloadAttachments(quotation.id, file.fileName)">
														<span class="glyphicon glyphicon-download"></span>
													</div>
												</div>
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
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#pnlAdditionalInfo">
									<spring:message code="quotation.additionalInfo"></spring:message>
								</button>
								<div id="pnlAdditionalInfo" class="collapse in">
									<div class="input-group col-sm-12"
										ng-class="{ 'has-error': (frmQuotation.txtadditionalInfo.$touched || frmadditionalInfo.$dirty) && frmQuotation.txtadditionalInfo.$invalid }">
										<div ckeditor="options" class="form-control" type="text"
											id="txtadditionalInfo" name="txtadditionalInfo"
											ng-model="quotation.additionalInfo"></div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="col-sm-6">

						<div class="form-horizontal">

							<!-- comment -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#pnlComment">
									<spring:message code="quotation.comment"></spring:message>
								</button>
								<div id="pnlComment" class="collapse in">
									<div class="input-group"
										ng-class="{ 'has-error': (frmQuotation.txtcomment.$touched || frmcomment.$dirty) && frmQuotation.txtcomment.$invalid }">
										<div ckeditor="options" class="form-control" type="text"
											id="txtcomment" name="txtcomment"
											ng-model="quotation.comment"></div>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">

						<div class="form-horizontal">
							<!-- result -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse"
									data-target="#pnlResult">
									<spring:message code="quotation.result"></spring:message>
								</button>
								<div id="pnlResult" class="collapse in">
									<div class="input-group col-sm-12"
										ng-class="{ 'has-error': (frmQuotation.txtresult.$touched || frmresult.$dirty) && frmQuotation.txtresult.$invalid }">
										<div ckeditor="options" class="form-control" type="text"
											id="txtresult" name="txtresult" ng-model="quotation.result"></div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<!-- Bottom actions -->
					<div class="col-sm-12">
						<div class="btn btn-link pull-left" ng-click="list()">
							<spring:message code="quotation.title" />
						</div>
						<button class="btn btn-info" ng-click="save()"
							data-toggle="tooltip"
							title='<spring:message code="quotation.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()"
							data-toggle="tooltip"
							title='<spring:message code="quotation.tooltip.create"></spring:message>'>
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>

		</div>
	</form>
</div>
