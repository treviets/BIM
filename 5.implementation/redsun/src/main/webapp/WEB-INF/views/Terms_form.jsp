<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/TermsController.js"></script>
<script src="${contextPath}/static/js/services/TermsService.js"></script>

<div ng-controller="termController" ng-init="initEdit(${id})" >
	<form name="frmTerm" novalidate class="form-inline">
		<h2><spring:message code="term.title" /></h2>
		<!-- Top actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
		<!-- Context -->
		<div>
			<!-- name -->
			<div class="form-group">
				<label class="control-label" for="txtname"><spring:message code="term.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtname.$touched || frmname.$dirty) && frmTerms.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="term.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="term.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idProject -->
			<div class="form-group">
				<label class="control-label" for="txtidProject"><spring:message code="term.idProject"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtidProject.$touched || frmidProject.$dirty) && frmTerms.txtidProject.$invalid }">
					<input class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="term.idProject" />
					<!-- <input class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="term.idProject" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtidProject.$invalid">
						<validation-tooltip target="txtidProject">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- amount -->
			<div class="form-group">
				<label class="control-label" for="txtamount"><spring:message code="term.amount"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtamount.$touched || frmamount.$dirty) && frmTerms.txtamount.$invalid }">
					<input class="form-control" type="text" id="txtamount" name="txtamount" ng-model="term.amount" />
					<!-- <input class="form-control" type="text" id="txtamount" name="txtamount" ng-model="term.amount" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtamount.$invalid">
						<validation-tooltip target="txtamount">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- validatedAmount -->
			<div class="form-group">
				<label class="control-label" for="txtvalidatedAmount"><spring:message code="term.validatedAmount"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtvalidatedAmount.$touched || frmvalidatedAmount.$dirty) && frmTerms.txtvalidatedAmount.$invalid }">
					<input class="form-control" type="text" id="txtvalidatedAmount" name="txtvalidatedAmount" ng-model="term.validatedAmount" />
					<!-- <input class="form-control" type="text" id="txtvalidatedAmount" name="txtvalidatedAmount" ng-model="term.validatedAmount" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtvalidatedAmount.$invalid">
						<validation-tooltip target="txtvalidatedAmount">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- plannedAmount -->
			<div class="form-group">
				<label class="control-label" for="txtplannedAmount"><spring:message code="term.plannedAmount"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtplannedAmount.$touched || frmplannedAmount.$dirty) && frmTerms.txtplannedAmount.$invalid }">
					<input class="form-control" type="text" id="txtplannedAmount" name="txtplannedAmount" ng-model="term.plannedAmount" />
					<!-- <input class="form-control" type="text" id="txtplannedAmount" name="txtplannedAmount" ng-model="term.plannedAmount" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtplannedAmount.$invalid">
						<validation-tooltip target="txtplannedAmount">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- date -->
			<div class="form-group">
				<label class="control-label" for="txtdate"><spring:message code="term.date"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtdate.$touched || frmdate.$dirty) && frmTerms.txtdate.$invalid }">
					<input class="form-control" type="text" id="txtdate" name="txtdate" ng-model="term.date" />
					<!-- <input class="form-control" type="text" id="txtdate" name="txtdate" ng-model="term.date" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtdate.$invalid">
						<validation-tooltip target="txtdate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- validatedDate -->
			<div class="form-group">
				<label class="control-label" for="txtvalidatedDate"><spring:message code="term.validatedDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtvalidatedDate.$touched || frmvalidatedDate.$dirty) && frmTerms.txtvalidatedDate.$invalid }">
					<input class="form-control" type="text" id="txtvalidatedDate" name="txtvalidatedDate" ng-model="term.validatedDate" />
					<!-- <input class="form-control" type="text" id="txtvalidatedDate" name="txtvalidatedDate" ng-model="term.validatedDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtvalidatedDate.$invalid">
						<validation-tooltip target="txtvalidatedDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- plannedDate -->
			<div class="form-group">
				<label class="control-label" for="txtplannedDate"><spring:message code="term.plannedDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtplannedDate.$touched || frmplannedDate.$dirty) && frmTerms.txtplannedDate.$invalid }">
					<input class="form-control" type="text" id="txtplannedDate" name="txtplannedDate" ng-model="term.plannedDate" />
					<!-- <input class="form-control" type="text" id="txtplannedDate" name="txtplannedDate" ng-model="term.plannedDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtplannedDate.$invalid">
						<validation-tooltip target="txtplannedDate">
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
				<label class="control-label" for="txtidle"><spring:message code="term.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtidle.$touched || frmidle.$dirty) && frmTerms.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="term.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="term.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idBill -->
			<div class="form-group">
				<label class="control-label" for="txtidBill"><spring:message code="term.idBill"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtidBill.$touched || frmidBill.$dirty) && frmTerms.txtidBill.$invalid }">
					<input class="form-control" type="text" id="txtidBill" name="txtidBill" ng-model="term.idBill" />
					<!-- <input class="form-control" type="text" id="txtidBill" name="txtidBill" ng-model="term.idBill" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtidBill.$invalid">
						<validation-tooltip target="txtidBill">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idUser -->
			<div class="form-group">
				<label class="control-label" for="txtidUser"><spring:message code="term.idUser"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtidUser.$touched || frmidUser.$dirty) && frmTerms.txtidUser.$invalid }">
					<input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="term.idUser" />
					<!-- <input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="term.idUser" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtidUser.$invalid">
						<validation-tooltip target="txtidUser">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- creationDate -->
			<div class="form-group">
				<label class="control-label" for="txtcreationDate"><spring:message code="term.creationDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmTerms.txtcreationDate.$touched || frmcreationDate.$dirty) && frmTerms.txtcreationDate.$invalid }">
					<input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="term.creationDate" />
					<!-- <input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="term.creationDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmTerms.txtcreationDate.$invalid">
						<validation-tooltip target="txtcreationDate">
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
