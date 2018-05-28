<%--
  User: haulam
  Date: 5/3/17
  Time: 11:25 PM
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container panel panel-success" data-spy="scroll"
	ng-controller="individualExpensesController_Detail" ng-init="init()">
	<div class="row panel-heading">
		<div class="col-sm-6 text-left">
			<spring:message code="individual.expense"></spring:message>
		</div>
		<div class="col-sm-6 text-right">
			<button type="button" class="btn btn-info" ng-click="back()">
				<span class="glyphicon glyphicon-backward"></span>
				<spring:message code="individual.expense.button.back"></spring:message>
			</button>
			<button type="button" class="btn btn-info" ng-click="init()">
				<span class="glyphicon glyphicon-refresh"></span>
				<spring:message code="individual.expense.button.reload"></spring:message>
			</button>
			<!--
             <button type="button" class="btn btn-info" ng-click="update()">
                <span class="glyphicon glyphicon-floppy-save"></span> <spring:message code="individual.expense.button.save"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="expense.id != null || expense.id != undefined" ng-click="clearModel()">
                <span class="glyphicon glyphicon-file"></span> <spring:message code="individual.expense.button.new"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="expense.id != null || expense.id != undefined" ng-click="delete(expense.id)">
                <span class="glyphicon glyphicon-remove-sign"></span> <spring:message code="individual.expense.button.delete"></spring:message>
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
					code="individual.expense.description"></spring:message>
			</a>
			<div>
				<form class="form-horizontal">
					<div class="form-group" ng-show="expense.id != null">
						<label class="control-label col-sm-4"><spring:message
								code="individual.expense.id"></spring:message></label> <label
							class="control-label col-sm-8" style="text-align: left">{{expense.id}}</label>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="individual.expense.name"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="expense.name"
								ng-model="expense.name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="individual.expense.type"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="type"
								ng-options="t.name for t in types"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="individual.expense.project"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="project"
								ng-options="project.name for project in projects"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="individual.expense.resource"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="resource"
								ng-options="resource.name for resource in resources"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4"><spring:message
								code="individual.expense.description"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="expense.description"></textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="row">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#accordion"> <spring:message
						code="individual.expense.treatment"></spring:message>
				</a>
				<div>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="individual.expense.status"></spring:message></label>
							<div class="col-sm-8">
								<select class="form-control" ng-model="status"
									ng-options="st.name for st in statuses"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="individual.expense.responsible"></spring:message></label>
							<div class="col-sm-8">
								<select class="form-control" ng-model="user"
									ng-options="u.name for u in users"></select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="individual.expense.planned"></spring:message></label>
							<div class="col-sm-3">
								<input type="text" class="form-control"
									id="expense.plannedAmount" ng-model="expense.plannedAmount">
							</div>
							<div class="col-sm-5">
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
									code="individual.expense.real"></spring:message></label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="expense.realAmount"
									ng-model="expense.realAmount">
							</div>
							<div class="col-sm-5">
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
									code="individual.expense.payment.done"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="expense.done" ng-true-value="1"
									ng-false-value="0" ng-checked="expense.done == 1">
							</div>

						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="individual.expense.closed"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="expense.idle" ng-true-value="1"
									ng-false-value="0" ng-checked="expense.idle == 1">
							</div>

						</div>
						<div class="form-group">
							<label class="control-label col-sm-4"><spring:message
									code="individual.expense.cancelled"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="expense.cancelled"
									ng-true-value="1" ng-false-value="0"
									ng-checked="expense.cancelled == 1">
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- HA: move button to bottom -->
			<div class="text-right">
				<button type="button" class="btn btn-info" ng-click="update()">
					<span class="glyphicon glyphicon-floppy-save"></span>
					<spring:message code="individual.expense.button.save"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="expense.id != null || expense.id != undefined"
					ng-click="clearModel()">
					<span class="glyphicon glyphicon-file"></span>
					<spring:message code="individual.expense.button.new"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="expense.id != null || expense.id != undefined"
					ng-click="delete(expense.id)">
					<span class="glyphicon glyphicon-remove-sign"></span>
					<spring:message code="individual.expense.button.delete"></spring:message>
				</button>
			</div>
			<%@ include file="Attachment.jsp"%>
		</div>
	</div>
	<%@ include file="ExpenseDetail_list.jsp"%>
</div>
<%@ include file="ExpenseDetailTemplate.jsp"%>
