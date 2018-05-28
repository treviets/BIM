<%--
  User: haulam
  Date: 5/3/17
  Time: 11:25 PM
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container panel panel-success" data-spy="scroll"
	ng-controller="tenderController_Detail" ng-init="init()">
	<div class="row panel-heading">
		<div class="col-sm-6 text-left">
			<spring:message code="tender.title"></spring:message>
		</div>
		<div class="col-sm-6 text-right">
			<button type="button" class="btn btn-info" ng-click="back()">
				<span class="glyphicon glyphicon-backward"></span>
				<spring:message code="tender.button.back"></spring:message>
			</button>
			<button type="button" class="btn btn-info" ng-click="init()">
				<span class="glyphicon glyphicon-refresh"></span>
				<spring:message code="tender.button.reload"></spring:message>
			</button>
			<!--  <button type="button" class="btn btn-info" ng-click="update()">
                <span class="glyphicon glyphicon-floppy-save"></span> <spring:message code="tender.button.save"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="tender.id != null || tender.id != undefined"
                    ng-click="clearModel()">
                <span class="glyphicon glyphicon-file"></span> <spring:message code="tender.button.new"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="tender.id != null || tender.id != undefined"
                    ng-click="delete(tender.id)">
                <span class="glyphicon glyphicon-remove-sign"></span> <spring:message code="tender.button.delete"></spring:message>
            </button>
             -->
		</div>
	</div>

	<div id="success-alert" class="row alert alert-info alert-dismissable"
		ng-show="msgSuccess != undefined || msgSuccess != null">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Success!</strong> {{msgSuccess}}
	</div>
	<div id="error-alert" class="row alert alert-danger alert-dismissable"
		ng-show="msgFail != undefined || msgFail != null">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Warning!</strong> {{msgFail}}
	</div>

	<div class="row panel-body">
		<div class="col-sm-6">
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion"> <spring:message
					code="tender.description"></spring:message>
			</a>
			<div>
				<form class="form-horizontal">
					<div class="form-group" ng-show="tender.id != null">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.id"></spring:message></label> <label
							class="control-label col-sm-8" for="name"
							style="text-align: left">{{tender.id}}</label>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.name"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="name"
								ng-model="tender.name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.type"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="type"
								ng-options="t.name for t in types"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.project"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="project"
								ng-options="p.name for p in projects"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.call.for.tender"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="callfortender"
								ng-options="callfortender.name for callfortender in callfortenders"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.provider"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="provider"
								ng-options="provider.name for provider in providers"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.external.reference"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="name"
								ng-model="tender.externalReference">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.description"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="tender.description"></textarea>
						</div>
					</div>
				</form>
			</div>
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion"> <spring:message
					code="tender.treatment"></spring:message>
			</a>
			<div>
				<form class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.status"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="status"
								ng-options="st.name for st in statuses"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.responsible"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="user"
								ng-options="u.name for u in users"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.contact"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="contact"
								ng-options="contact.name for contact in contacts"></select>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.request.date"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}"
									ng-model="tender.requestDateTime"
									is-open="requestDateTime.opened"
									datepicker-options="dateOptions" ng-required="true"
									close-text="Close" alt-input-formats="altInputFormats" /> <span
									class="input-group-btn">
									<button type="button" class="btn btn-default"
										ng-click="open('requestDateTime')">
										<i class="glyphicon glyphicon-calendar"></i>
									</button>
								</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.expected.answer.date"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}"
									ng-model="tender.expectedTenderDateTime"
									is-open="expectedTenderDateTime.opened"
									datepicker-options="dateOptions" ng-required="true"
									close-text="Close" alt-input-formats="altInputFormats" /> <span
									class="input-group-btn">
									<button type="button" class="btn btn-default"
										ng-click="open('expectedTenderDateTime')">
										<i class="glyphicon glyphicon-calendar"></i>
									</button>
								</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.date.of.receipt"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}"
									ng-model="tender.receptionDateTime"
									is-open="receptionDateTime.opened"
									datepicker-options="dateOptions" ng-required="true"
									close-text="Close" alt-input-formats="altInputFormats" /> <span
									class="input-group-btn">
									<button type="button" class="btn btn-default"
										ng-click="open('receptionDateTime')">
										<i class="glyphicon glyphicon-calendar"></i>
									</button>
								</span>
							</p>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="tender.offer.validity"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}"
									ng-model="tender.offerValidityEndDate"
									is-open="offerValidityEndDate.opened"
									datepicker-options="dateOptions" ng-required="true"
									close-text="Close" alt-input-formats="altInputFormats" /> <span
									class="input-group-btn">
									<button type="button" class="btn btn-default"
										ng-click="open('offerValidityEndDate')">
										<i class="glyphicon glyphicon-calendar"></i>
									</button>
								</span>
							</p>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="row">
				<div>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.initial"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									ng-model="tender.initialAmount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.tax"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" ng-model="tender.taxPct">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.tax.amount"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									value="{{tender.initialAmount * tender.taxPct / 100}}" readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.full.amount"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									value="{{tender.initialAmount * tender.taxPct / 100 + tender.initialAmount * 1}}"
									readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.negotiated"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									ng-model="tender.plannedTaxAmount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.negotiated.tax.amount"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									value="{{tender.plannedTaxAmount * tender.taxPct / 100}}"
									readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.negotiated.full"></spring:message></label>
							<div class="col-sm-4">
								<input type="text" class="form-control"
									value="{{tender.plannedTaxAmount * tender.taxPct / 100 + tender.plannedTaxAmount * 1}}"
									readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.payment.conditions"></spring:message></label>
							<div class="col-sm-8">
								<textarea class="form-control" rows="5"
									ng-model="tender.paymentCondition"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.delivery.delay"></spring:message></label>
							<div class="col-sm-8">
								<textarea class="form-control" rows="5"
									ng-model="tender.deliveryDelay"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.expected.delivery.date"></spring:message></label>
							<div class="col-sm-8">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}"
										ng-model="tender.deliveryDate" is-open="deliveryDate.opened"
										datepicker-options="dateOptions" ng-required="true"
										close-text="Close" alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('deliveryDate')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.handled"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="tender.handled"
									ng-true-value="1" ng-false-value="0"
									ng-checked="tender.handled">
							</div>
							<div class="col-sm-7">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}"
										ng-model="tender.handledDate" is-open="handledDate.opened"
										datepicker-options="dateOptions" ng-required="true"
										close-text="Close" alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('handledDate')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.done"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="tender.done" ng-true-value="1"
									ng-false-value="0" ng-checked="tender.done == 1">
							</div>
							<div class="col-sm-7">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}" ng-model="tender.doneDate"
										is-open="doneDate.opened" datepicker-options="dateOptions"
										ng-required="true" close-text="Close"
										alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('doneDate')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.closed"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="tender.idle" ng-true-value="1"
									ng-false-value="0" ng-checked="tender.idle == 1">
							</div>
							<div class="col-sm-7">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}" ng-model="tender.idleDate"
										is-open="idleDate.opened" datepicker-options="dateOptions"
										ng-required="true" close-text="Close"
										alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('idleDate')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.cancelled"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="tender.cancelled"
									ng-true-value="1" ng-false-value="0"
									ng-checked="tender.cancelled == 1">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="tender.result"></spring:message></label>
							<div class="col-sm-8">
								<textarea class="form-control" rows="5" ng-model="tender.result"></textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- HA: Move button to bottom -->
			<div class="text-right">
				<button type="button" class="btn btn-info" ng-click="update()">
					<span class="glyphicon glyphicon-floppy-save"></span>
					<spring:message code="tender.button.save"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="tender.id != null || tender.id != undefined"
					ng-click="clearModel()">
					<span class="glyphicon glyphicon-file"></span>
					<spring:message code="tender.button.new"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="tender.id != null || tender.id != undefined"
					ng-click="delete(tender.id)">
					<span class="glyphicon glyphicon-remove-sign"></span>
					<spring:message code="tender.button.delete"></spring:message>
				</button>
			</div>
			<%@ include file="Attachment.jsp"%>
		</div>
	</div>
</div>
