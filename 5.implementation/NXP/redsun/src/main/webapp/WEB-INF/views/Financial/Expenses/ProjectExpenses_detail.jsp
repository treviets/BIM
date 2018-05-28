<%--
  User: haulam
  Date: 5/3/17
  Time: 11:25 PM
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container panel panel-success" data-spy="scroll"
	ng-controller="projectExpensesController_Detail" ng-init="init()">
	<div class="row panel-heading">
		<div class="col-sm-6 text-left">
			<spring:message code="project.expense"></spring:message>
		</div>
		<div class="col-sm-6 text-right">
			<button type="button" class="btn btn-info" ng-click="back()">
				<span class="glyphicon glyphicon-backward"></span>
				<spring:message code="project.expense.button.back"></spring:message>
			</button>
			<button type="button" class="btn btn-info" ng-click="init()">
				<span class="glyphicon glyphicon-refresh"></span>
				<spring:message code="project.expense.button.reload"></spring:message>
			</button>
			<!-- 
            <button type="button" class="btn btn-info" ng-click="update()">
                <span class="glyphicon glyphicon-floppy-save"></span> <spring:message
                    code="project.expense.button.save"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="expense.id != null || expense.id != undefined"
                    ng-click="clearModel()">
                <span class="glyphicon glyphicon-file"></span> <spring:message
                    code="project.expense.button.new"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="expense.id != null || expense.id != undefined"
                    ng-click="delete(expense.id)">
                <span class="glyphicon glyphicon-remove-sign"></span> <spring:message
                    code="project.expense.button.delete"></spring:message>
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
					code="project.expense.description"></spring:message>
			</a>
			<div>
				<form class="form-horizontal">
					<div class="form-group" ng-show="expense.id != null">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.id"></spring:message></label> <label
							class="control-label col-sm-8" style="text-align: left">{{expense.id}}</label>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.name"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="expense.name"
								ng-model="expense.name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.type"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="type"
								ng-options="t.name for t in types"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.project"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="project"
								ng-options="project.name for project in projects"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.provider"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="provider"
								ng-options="provider.name for provider in providers"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.contact"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="contact"
								ng-options="contact.name for contact in contacts"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.business.responsible"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="businessUser"
								ng-options="businessUser.name for businessUser in businessUsers"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.financial.responsible"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="financialUser"
								ng-options="financialUser.name for financialUser in financialUsers"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.description"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="expense.description"></textarea>
						</div>
					</div>
				</form>
			</div>
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion"> <spring:message
					code="project.expense.treatment"></spring:message>
			</a>
			<div>
				<form class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.status"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="status"
								ng-options="st.name for st in statuses"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.order.date"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}" ng-model="expense.sendDate"
									is-open="sendDate.opened" datepicker-options="dateOptions"
									ng-required="true" close-text="Close"
									alt-input-formats="altInputFormats" /> <span
									class="input-group-btn">
									<button type="button" class="btn btn-default"
										ng-click="open('sendDate')">
										<i class="glyphicon glyphicon-calendar"></i>
									</button>
								</span>
							</p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.delivery.mode"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="delivery"
								ng-options="delivery.name for delivery in deliveries"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.delivery.delay"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control"
								id="expense.deliveryDelay" ng-model="expense.deliveryDelay">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.expected.delivery.date"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}"
									ng-model="expense.deliveryDate" is-open="deliveryDate.opened"
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
						<label class="control-label col-sm-4"><spring:message
								code="project.expense.date.of.receipt"></spring:message></label>
						<div class="col-sm-8">
							<p class="input-group">
								<input type="text" class="form-control"
									uib-datepicker-popup="{{format}}"
									ng-model="expense.receptionDate" is-open="receptionDate.opened"
									datepicker-options="dateOptions" ng-required="true"
									close-text="Close" alt-input-formats="altInputFormats" /> <span
									class="input-group-btn">
									<button type="button" class="btn btn-default"
										ng-click="open('receptionDate')">
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
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.planned"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									ng-model="expense.plannedAmount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.tax"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									ng-model="expense.taxPct">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.planned.tax.amount"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									value="{{expense.plannedAmount * expense.taxPct / 100}}"
									disabled>
							</div>

						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.planned.full"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									value="{{expense.plannedAmount * expense.taxPct / 100 + expense.plannedAmount * 1}}">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.planned.payment.date"></spring:message></label>
							<div class="col-sm-8">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}"
										ng-model="expense.expensePlannedDate"
										is-open="expensePlannedDate.opened"
										datepicker-options="dateOptions" ng-required="true"
										close-text="Close" alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('expensePlannedDate')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.real"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									ng-model="expense.realAmount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.real.tax.amount"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									value="{{expense.realAmount * expense.taxPct / 100}}" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.real.full"></spring:message></label>
							<div class="col-sm-8">
								<input type="text" class="form-control"
									value="{{expense.realAmount * expense.taxPct / 100 + expense.realAmount * 1}}">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.real.payment.date"></spring:message></label>
							<div class="col-sm-8">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}"
										ng-model="expense.expenseRealDate"
										is-open="expenseRealDate.opened"
										datepicker-options="dateOptions" ng-required="true"
										close-text="Close" alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('expenseRealDate')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.result"></spring:message></label>
							<div class="col-sm-8">
								<textarea class="form-control" rows="5"
									ng-model="expense.result"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.payment.done"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="expense.done" ng-true-value="1"
									ng-false-value="0" ng-checked="expense.done == 1">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.closed"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="expense.idle" ng-true-value="1"
									ng-false-value="0" ng-checked="expense.idle == 1">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="project.expense.cancelled"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="expense.cancelled"
									ng-true-value="1" ng-false-value="0"
									ng-checked="expense.cancelled == 1">
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- HA: Move button to bottom -->
			<div class="text-right">
				<button type="button" class="btn btn-info" ng-click="update()">
					<span class="glyphicon glyphicon-floppy-save"></span>
					<spring:message code="project.expense.button.save"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="expense.id != null || expense.id != undefined"
					ng-click="clearModel()">
					<span class="glyphicon glyphicon-file"></span>
					<spring:message code="project.expense.button.new"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="expense.id != null || expense.id != undefined"
					ng-click="delete(expense.id)">
					<span class="glyphicon glyphicon-remove-sign"></span>
					<spring:message code="project.expense.button.delete"></spring:message>
				</button>
			</div>
			<%@ include file="Attachment.jsp"%>
		</div>
	</div>
	<%@ include file="ExpenseDetail_list.jsp"%>
</div>
<%@ include file="ExpenseDetailTemplate.jsp"%>
