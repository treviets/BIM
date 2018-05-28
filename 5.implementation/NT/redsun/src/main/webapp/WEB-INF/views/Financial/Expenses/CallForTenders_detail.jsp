<%--
  User: haulam
  Date: 5/3/17
  Time: 11:25 PM
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container panel panel-success" data-spy="scroll"
	ng-controller="callForTenderController_Detail" ng-init="init()">
	<div class="row panel-heading">
		<div class="col-sm-6 text-left">
			<spring:message code="callfortender.title"></spring:message>
		</div>
		<!-- 
        <div class="col-sm-6 text-right">
            <button type="button" class="btn btn-info" ng-click="back()">
                <span class="glyphicon glyphicon-backward"></span> <spring:message code="callfortender.button.back"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-click="init()">
                <span class="glyphicon glyphicon-refresh"></span> <spring:message code="callfortender.button.reload"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-click="update()">
                <span class="glyphicon glyphicon-floppy-save"></span> <spring:message code="callfortender.button.save"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="tender.id != null || tender.id != undefined" ng-click="clearModel()">
                <span class="glyphicon glyphicon-file"></span> <spring:message code="callfortender.button.new"></spring:message>
            </button>
            <button type="button" class="btn btn-info" ng-show="tender.id != null || tender.id != undefined" ng-click="delete(tender.id)">
                <span class="glyphicon glyphicon-remove-sign"></span> <spring:message code="callfortender.button.delete"></spring:message>
            </button>
        </div> 
        -->

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
					code="callfortender.description"></spring:message>
			</a>
			<div>
				<form class="form-horizontal">
					<div class="form-group" ng-show="tender.id != null">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.id"></spring:message></label> <label
							class="control-label col-sm-8" for="name"
							style="text-align: left">{{tender.id}}</label>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.name"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="tender_name"
								ng-model="tender.name">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.type"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="type"
								ng-options="t.name for t in types"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.project"></spring:message></label>
						<div class="col-sm-8">
							<select class="form-control" ng-model="project"
								ng-options="p.name for p in projects"></select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.maximum.amount"></spring:message></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="name"
								ng-model="tender.maxAmount">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.expected.delivery.date"></spring:message></label>
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
								code="callfortender.description"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="tender.description"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.business.requirements"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="tender.businessRequirements"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.technical.requirements"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="tender.technicalRequirements"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="name"><spring:message
								code="callfortender.other.requirements"></spring:message></label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="5"
								ng-model="tender.otherRequirements"></textarea>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="row">
				<a class="accordion-toggle" data-toggle="collapse"
					data-parent="#accordion"> <spring:message
						code="callfortender.treatment"></spring:message>
				</a>
				<div>
					<form class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="callfortender.status"></spring:message></label>
							<div class="col-sm-8">
								<select class="form-control" ng-model="status"
									ng-options="st.name for st in statuses"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="callfortender.responsible"></spring:message></label>
							<div class="col-sm-8">
								<select class="form-control" ng-model="user"
									ng-options="u.name for u in users"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="callfortender.sent.date"></spring:message></label>
							<div class="col-sm-8">
								<p class="input-group">
									<input type="text" class="form-control"
										uib-datepicker-popup="{{format}}"
										ng-model="tender.sendDateTime" is-open="sendDateTime.opened"
										datepicker-options="dateOptions" ng-required="true"
										close-text="Close" alt-input-formats="altInputFormats" /> <span
										class="input-group-btn">
										<button type="button" class="btn btn-default"
											ng-click="open('sendDateTime')">
											<i class="glyphicon glyphicon-calendar"></i>
										</button>
									</span>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="callfortender.handled"></spring:message></label>
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
									code="callfortender.done"></spring:message></label>
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
									code="callfortender.closed"></spring:message></label>
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
									code="callfortender.cancelled"></spring:message></label>
							<div class="col-sm-1" style="padding-top: 8px;">
								<input type="checkbox" ng-model="tender.cancelled"
									ng-true-value="1" ng-false-value="0"
									ng-checked="tender.cancelled == 1">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-4" for="name"><spring:message
									code="callfortender.result"></spring:message></label>
							<div class="col-sm-8">
								<textarea class="form-control" rows="5" ng-model="tender.result"></textarea>
							</div>
						</div>
					</form>
				</div>
			</div>

			<!-- HA: Move button to bottom -->
			<div class="col-sm-6 text-right">
				<button type="button" class="btn btn-info" ng-click="back()">
					<span class="glyphicon glyphicon-backward"></span>
					<spring:message code="callfortender.button.back"></spring:message>
				</button>
				<button type="button" class="btn btn-info" ng-click="init()">
					<span class="glyphicon glyphicon-refresh"></span>
					<spring:message code="callfortender.button.reload"></spring:message>
				</button>
				<button type="button" class="btn btn-info" ng-click="update()">
					<span class="glyphicon glyphicon-floppy-save"></span>
					<spring:message code="callfortender.button.save"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="tender.id != null || tender.id != undefined"
					ng-click="clearModel()">
					<span class="glyphicon glyphicon-file"></span>
					<spring:message code="callfortender.button.new"></spring:message>
				</button>
				<button type="button" class="btn btn-info"
					ng-show="tender.id != null || tender.id != undefined"
					ng-click="delete(tender.id)">
					<span class="glyphicon glyphicon-remove-sign"></span>
					<spring:message code="callfortender.button.delete"></spring:message>
				</button>
			</div>
			<%@ include file="Attachment.jsp"%>
		</div>
	</div>
</div>
