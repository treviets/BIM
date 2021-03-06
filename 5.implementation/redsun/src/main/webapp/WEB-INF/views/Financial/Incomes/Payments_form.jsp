
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/Financial/Incomes/PaymentsController.js"></script>
<script src="${contextPath}/static/js/services/Financial/Incomes/PaymentsService.js"></script>

<script src="${contextPath}/static/js/utils/upload-file/upload-files-directive.js"></script>


<div ng-controller="paymentController" ng-init="initEdit(${id})" >

	<form name="frmPayment" novalidate>
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
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="payment.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="payment.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="payment.tooltip.create"></spring:message>'>
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
								<label class="control-label col-sm-6" for="txtname"><spring:message code="payment.name"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtname.$touched || frmPayment.txtname.$dirty || frmDirty) && frmPayment.txtname.$invalid }">
									<input class="form-control" type="text" id="txtname" name="txtname" ng-model="payment.name" required maxlength="50"></input>
									<div class="input-group-addon" ng-show="frmPayment.txtname.$invalid">
										<validation-tooltip target="txtname">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
							
							<!-- idBill -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidBill"><spring:message code="payment.billName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtidBill.$touched || frmPayment.txtidBill.$dirty || frmDirty) && frmPayment.txtidBill.$invalid }">
									<select class="form-control" type="text" id="txtidBill" name="txtidBill" ng-model="payment.idBill" ng-options="bill.id as bill.name for bill in bills" required></select>
									<div class="input-group-addon" ng-show="frmPayment.txtidBill.$invalid">
										<validation-tooltip target="txtidBill">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
				
							<!-- idPaymentType -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidPaymentType"><spring:message code="payment.paymentTypeName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtidPaymentType.$touched || frmPayment.txtidPaymentType.$dirty || frmDirty) && frmPayment.txtidPaymentType.$invalid }">
									<select class="form-control" type="text" id="txtidPaymentType" name="txtidPaymentType" ng-model="payment.idPaymentType" ng-options="paymentType.id as paymentType.name for paymentType in paymentTypes" required ></select>
									<div class="input-group-addon" ng-show="frmPayment.txtidPaymentType.$invalid">
										<validation-tooltip target="txtidPaymentType">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>

							<!-- description -->
							<div class="form-group col-sm-12">
								<button class="btn btn-link" data-toggle="collapse" data-target="#pnlResult"><spring:message code="payment.description"></spring:message></button>
								<div id="pnlResult" class="collapse in">
									<div class="input-group col-sm-12" ng-class="{ 'has-error': (frmPayment.txtdescription.$touched || frmdescription.$dirty) && frmPayment.txtdescription.$invalid }">
										<div ckeditor="options" class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="payment.description" ></div>
									</div>
								</div>
							</div>

						</div>
					</div>
					
					<!-- Extend -->
					<div class="col-sm-6">
					
						<div class="form-horizontal">
				
							<!-- idPaymentMode -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidPaymentMode"><spring:message code="payment.paymentModeName"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtidPaymentMode.$touched || frmPayment.txtidPaymentMode.$dirty || frmDirty) && frmPayment.txtidPaymentMode.$invalid }">
									<select class="form-control" type="text" id="txtidPaymentMode" name="txtidPaymentMode" ng-model="payment.idPaymentMode" ng-options="paymentMode.id as paymentMode.name for paymentMode in paymentModes" required ></select>
									<div class="input-group-addon" ng-show="frmPayment.txtidPaymentMode.$invalid">
										<validation-tooltip target="txtidPaymentMode">
											<ul class="list-unstyled">
												<li validation-message ng-if="$field.$error.required">This is a required field. </li>
											</ul>
										</validation-tooltip>
									</div>
								</div>
							</div>
							
							<!-- paymentDate -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtpaymentDate"><spring:message code="payment.paymentDate"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtpaymentDate.$touched || frmPayment.txtpaymentDate.$dirty || frmDirty) && frmPayment.txtpaymentDate.$invalid }">
									<input type="text" class="form-control" id="txtpaymentDate" name="txtpaymentDate" ng-model="payment.paymentDate" ng-change="dateChangeHandler(payment.paymentDate, frmPayment.txtpaymentDate, 'txtpaymentDate')"
										uib-datepicker-popup="dd/MM/yyyy" is-open="DateData.paymentDate.isOpen" datepicker-options="dateOptions" close-text="Close" ></input>
									<span class="input-group-btn">
										<button type="button" class="btn btn-default" ng-click="openDatePicker(DateData.paymentDate)">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</div>
							</div>

							<!-- paymentAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtpaymentAmount"><spring:message code="payment.paymentAmount"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtpaymentAmount.$touched || frmPayment.txtpaymentAmount.$dirty || frmDirty) && frmPayment.txtpaymentAmount.$invalid }">
									<input class="form-control" type="number" min="0" id="txtpaymentAmount" name="txtpaymentAmount" ng-model="payment.paymentAmount" />
								</div>
							</div>
							
							<!-- paymentFeeAmount -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtpaymentFeeAmount"><spring:message code="payment.paymentFeeAmount"></spring:message></label>
								<div class="input-group col-sm-6" ng-class="{ 'has-error': (frmPayment.txtpaymentFeeAmount.$touched || frmPayment.txtpaymentFeeAmount.$dirty || frmDirty) && frmPayment.txtpaymentFeeAmount.$invalid }">
									<input class="form-control" type="number" min="0" id="txtpaymentFeeAmount" name="txtpaymentFeeAmount" ng-model="payment.paymentFeeAmount" />
								</div>
							</div>

							<!-- idle -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="txtidle"><spring:message code="payment.idle"></spring:message></label>
								<div class="control-label col-sm-6" >
									<input type="checkbox" class="pull-left" ng-model="payment.idle" ng-true-value="1" ng-false-value="0" ></input>
								</div>
							</div>

							<!-- attachments -->
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-6" for="attachments"><spring:message code="payment.attachments"></spring:message></label>
								<div class="input-group col-sm-6">
									<input type="file" class="form-control btn btn-default" ng-click="fileClear($event)" id="attachments" name="attachments" multiple ng-files="addAttachments($files)" />
									<table class="table table-bordered">
										<tr ng-repeat="file in payment.attachments">
											<td>{{file.fileName}}</td>
											<td>
												<div ng-click="deleteAttachments(file.fileName)"><span class="glyphicon glyphicon-trash"></span></div>
											</td>
											<td>
												<div ng-if="file.id != null"><div ng-click="downloadAttachments(payment.id, file.fileName)"><span class="glyphicon glyphicon-download"></span></div></div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						
						</div>
					</div>

				</div>

			</div>
			<!-- Bottom actions -->
			<div class="panel-footer">
				<div class="row">
				
					<div class="btn btn-link pull-left" ng-click="list()"><spring:message code="payment.title" /></div>
					<div class="btn-group pull-right">
						<button class="btn btn-info" ng-click="save()" data-toggle="tooltip" title='<spring:message code="payment.tooltip.save"></spring:message>'>
							<span class="glyphicon glyphicon-floppy-disk"></span>
						</button>
						<button class="btn btn-info" ng-click="createNew()" data-toggle="tooltip" title='<spring:message code="payment.tooltip.create"></spring:message>'>
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
