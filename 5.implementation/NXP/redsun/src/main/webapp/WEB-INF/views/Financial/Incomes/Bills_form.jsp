
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/Financial/Incomes/BillsController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/BillsService.js"></script>

<script src="${contextPath}/static/js/utils/upload-file/upload-files-directive.js"></script>


<div ng-controller="billController" ng-init="initEdit(${id})" >

	<form name="frmBill" novalidate>
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
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="bill.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="bill.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="bill.tooltip.create"></spring:message>'>
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
								<label class="control-label col-sm-6" for="txtname"><spring:message code="bill.name"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtname.$touched || frmBill.txtname.$dirty || frmDirty) && frmBill.txtname.$invalid }">
									<input class="form-control" type="text" id="txtname" name="txtname" ng-model="bill.name" required maxlength="50"></input>
									<div class="input-group-addon" ng-show="frmBill.txtname.$invalid">
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
								<label class="control-label col-sm-6" for="txtidProject"><spring:message code="bill.projectName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtidProject.$touched || frmBill.txtidProject.$dirty || frmDirty) && frmBill.txtidProject.$invalid }">
									<select class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="bill.idProject" ng-options="project.id as project.name for project in projects" required></select>
									<div class="input-group-addon" ng-show="frmBill.txtidProject.$invalid">
										<validation-tooltip target="txtidProject">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idBillType -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidBillType"><spring:message code="bill.billTypeName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtidBillType.$touched || frmBill.txtidBillType.$dirty || frmDirty) && frmBill.txtidBillType.$invalid }">
									<select class="form-control" type="text" id="txtidBillType" name="txtidBillType" ng-model="bill.idBillType" ng-options="billType.id as billType.name for billType in billTypes" required ></select>
									<div class="input-group-addon" ng-show="frmBill.txtidBillType.$invalid">
										<validation-tooltip target="txtidBillType">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idClient -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidClient"><spring:message code="bill.clientName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtidClient.$touched || frmBill.txtidClient.$dirty || frmDirty) && frmBill.txtidClient.$invalid }">
									<select class="form-control" type="text" id="txtidClient" name="txtidClient" ng-model="bill.idClient" ng-options="customer.id as customer.name for customer in customers" required ></select>
									<div class="input-group-addon" ng-show="frmBill.txtidClient.$invalid">
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
								<label class="control-label col-sm-6" for="txtidContact"><spring:message code="bill.contactName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtidContact.$touched || frmBill.txtidContact.$dirty || frmDirty) && frmBill.txtidContact.$invalid }">
									<select class="form-control" type="text" id="txtidContact" name="txtidContact" ng-model="bill.idContact" ng-options="contact.id as contact.name for contact in contacts" required ></select>
									<div class="input-group-addon" ng-show="frmBill.txtidContact.$invalid">
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
								<label class="control-label col-sm-6" for="txtidUser"><spring:message code="bill.userName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtidUser.$touched || frmBill.txtidUser.$dirty || frmDirty) && frmBill.txtidUser.$invalid }">
									<select class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="bill.idUser" ng-options="user.id as user.name for user in users" required ></select>
									<div class="input-group-addon" ng-show="frmBill.txtidUser.$invalid">
										<validation-tooltip target="txtidUser">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
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
								<label class="control-label col-sm-6" for="txtsendDate"><spring:message code="bill.sendDate"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtsendDate.$touched || frmBill.txtsendDate.$dirty || frmDirty) && frmBill.txtsendDate.$invalid }">
									<input type="text" class="form-control" id="txtsendDate" name="txtsendDate" ng-model="bill.sendDate" ng-change="dateChangeHandler(bill.sendDate, frmBill.txtsendDate, 'txtsendDate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.sendDate.isOpen" datepicker-options="dateOptions" close-text="Close" ></input>
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.sendDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>
							
							<!-- paymentDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtpaymentDate"><spring:message code="bill.paymentDate"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtpaymentDate.$touched || frmBill.txtpaymentDate.$dirty || frmDirty) && frmBill.txtpaymentDate.$invalid }">
									<input type="text" class="form-control" id="txtpaymentDate" name="txtpaymentDate" ng-model="bill.paymentDate" ng-change="dateChangeHandler(bill.paymentDate, frmBill.txtpaymentDate, 'txtpaymentDate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.paymentDate.isOpen" datepicker-options="dateOptions" close-text="Close" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.paymentDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- date -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtdate"><spring:message code="bill.date"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtdate.$touched || frmBill.txtdate.$dirty || frmDirty) && frmBill.txtdate.$invalid }">
									<input type="text" class="form-control" id="txtdate" name="txtdate" ng-model="bill.date" ng-change="dateChangeHandler(bill.date, frmBill.txtdate, 'txtdate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.date.isOpen" datepicker-options="dateOptions" close-text="Close" />
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.date)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- cancelled -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtcancelled"><spring:message code="bill.cancelled"></spring:message></label>
								<div class="control-label col-sm-6" >
									<input type="checkbox" class="pull-left" ng-model="bill.cancelled" ng-true-value="1" ng-false-value="0" ></input>
								</div>
							</div>

							<!-- untaxedAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtuntaxedAmount"><spring:message code="bill.untaxedAmount"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtuntaxedAmount.$touched || frmBill.txtuntaxedAmount.$dirty || frmDirty) && frmBill.txtuntaxedAmount.$invalid }">
									<input class="form-control" type="number" min="0" id="txtuntaxedAmount" name="txtuntaxedAmount" ng-model="bill.untaxedAmount" ng-change="calculateAmount()" />
								</div>
							</div>
							
							<!-- tax -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txttax"><spring:message code="bill.tax"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txttax.$touched || frmBill.txttax.$dirty || frmDirty) && frmBill.txttax.$invalid }">
									<input class="form-control" type="number" min="0" id="txttax" name="txttax" ng-model="bill.tax" ng-change="calculateAmount()" />
								</div>
							</div>
							
							<!-- fullAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtfullAmount"><spring:message code="bill.fullAmount"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmBill.txtfullAmount.$touched || frmBill.txtfullAmount.$dirty || frmDirty) && frmBill.txtfullAmount.$invalid }">
									<input class="form-control" type="number" min="0" id="txtfullAmount" name="txtfullAmount" ng-model="bill.fullAmount" readonly />
								</div>
							</div>

							<!-- attachments -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="attachments"><spring:message code="bill.attachments"></spring:message></label>
								<div class="input-group col-sm-6">
									<input type="file" class="form-control btn btn-default" ng-click="fileClear($event)" id="attachments" name="attachments" multiple ng-files="addAttachments($files)" />
									<table class="table table-bordered">
										<tr ng-repeat="file in bill.attachments">
											<td>{{file.fileName}}</td>
											<td>
												<div ng-click="deleteAttachments(file.fileName)"><span class="glyphicon glyphicon-trash"></span></div>
											</td>
											<td>
												<div ng-if="file.id != null"><div ng-click="downloadAttachments(bill.id, file.fileName)"><span class="glyphicon glyphicon-download"></span></div></div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						
						</div>
					</div>

				</div>
			
				<div class="row">
					<div class="col-sm-12">
					
						<div class="form-horizontal">
							<!-- description -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse" data-target="#pnlResult"><spring:message code="bill.description"></spring:message></button>
								<div id="pnlResult" class="collapse in">
									<div class="input-group col-sm-12" ng-class="{ 'has-error': (frmBill.txtdescription.$touched || frmdescription.$dirty) && frmBill.txtdescription.$invalid }">
										<div ckeditor="options" class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="bill.description" ></div>
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
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="bill.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="bill.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="bill.tooltip.create"></spring:message>'>
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
