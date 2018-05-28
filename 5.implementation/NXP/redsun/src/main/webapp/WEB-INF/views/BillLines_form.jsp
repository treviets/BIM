<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/BillLinesController.js"></script>
<script src="${contextPath}/static/js/services/BillLinesService.js"></script>

<div ng-controller="billLineController" ng-init="initEdit(${id})" >
	<form name="frmBillLine" novalidate class="form-inline">
		<h2><spring:message code="billLine.title" /></h2>
		<!-- Top actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
		<!-- Context -->
		<div>
			<!-- line -->
			<div class="form-group">
				<label class="control-label" for="txtline"><spring:message code="billLine.line"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtline.$touched || frmline.$dirty) && frmBillLines.txtline.$invalid }">
					<input class="form-control" type="text" id="txtline" name="txtline" ng-model="billLine.line" />
					<!-- <input class="form-control" type="text" id="txtline" name="txtline" ng-model="billLine.line" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtline.$invalid">
						<validation-tooltip target="txtline">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- quantity -->
			<div class="form-group">
				<label class="control-label" for="txtquantity"><spring:message code="billLine.quantity"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtquantity.$touched || frmquantity.$dirty) && frmBillLines.txtquantity.$invalid }">
					<input class="form-control" type="text" id="txtquantity" name="txtquantity" ng-model="billLine.quantity" />
					<!-- <input class="form-control" type="text" id="txtquantity" name="txtquantity" ng-model="billLine.quantity" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtquantity.$invalid">
						<validation-tooltip target="txtquantity">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- description -->
			<div class="form-group">
				<label class="control-label" for="txtdescription"><spring:message code="billLine.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtdescription.$touched || frmdescription.$dirty) && frmBillLines.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="billLine.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="billLine.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtdescription.$invalid">
						<validation-tooltip target="txtdescription">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- detail -->
			<div class="form-group">
				<label class="control-label" for="txtdetail"><spring:message code="billLine.detail"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtdetail.$touched || frmdetail.$dirty) && frmBillLines.txtdetail.$invalid }">
					<input class="form-control" type="text" id="txtdetail" name="txtdetail" ng-model="billLine.detail" />
					<!-- <input class="form-control" type="text" id="txtdetail" name="txtdetail" ng-model="billLine.detail" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtdetail.$invalid">
						<validation-tooltip target="txtdetail">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- price -->
			<div class="form-group">
				<label class="control-label" for="txtprice"><spring:message code="billLine.price"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtprice.$touched || frmprice.$dirty) && frmBillLines.txtprice.$invalid }">
					<input class="form-control" type="text" id="txtprice" name="txtprice" ng-model="billLine.price" />
					<!-- <input class="form-control" type="text" id="txtprice" name="txtprice" ng-model="billLine.price" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtprice.$invalid">
						<validation-tooltip target="txtprice">
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
				<label class="control-label" for="txtamount"><spring:message code="billLine.amount"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtamount.$touched || frmamount.$dirty) && frmBillLines.txtamount.$invalid }">
					<input class="form-control" type="text" id="txtamount" name="txtamount" ng-model="billLine.amount" />
					<!-- <input class="form-control" type="text" id="txtamount" name="txtamount" ng-model="billLine.amount" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtamount.$invalid">
						<validation-tooltip target="txtamount">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- refType -->
			<div class="form-group">
				<label class="control-label" for="txtrefType"><spring:message code="billLine.refType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtrefType.$touched || frmrefType.$dirty) && frmBillLines.txtrefType.$invalid }">
					<input class="form-control" type="text" id="txtrefType" name="txtrefType" ng-model="billLine.refType" />
					<!-- <input class="form-control" type="text" id="txtrefType" name="txtrefType" ng-model="billLine.refType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtrefType.$invalid">
						<validation-tooltip target="txtrefType">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- refId -->
			<div class="form-group">
				<label class="control-label" for="txtrefId"><spring:message code="billLine.refId"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtrefId.$touched || frmrefId.$dirty) && frmBillLines.txtrefId.$invalid }">
					<input class="form-control" type="text" id="txtrefId" name="txtrefId" ng-model="billLine.refId" />
					<!-- <input class="form-control" type="text" id="txtrefId" name="txtrefId" ng-model="billLine.refId" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtrefId.$invalid">
						<validation-tooltip target="txtrefId">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idTerm -->
			<div class="form-group">
				<label class="control-label" for="txtidTerm"><spring:message code="billLine.idTerm"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtidTerm.$touched || frmidTerm.$dirty) && frmBillLines.txtidTerm.$invalid }">
					<input class="form-control" type="text" id="txtidTerm" name="txtidTerm" ng-model="billLine.idTerm" />
					<!-- <input class="form-control" type="text" id="txtidTerm" name="txtidTerm" ng-model="billLine.idTerm" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtidTerm.$invalid">
						<validation-tooltip target="txtidTerm">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idResource -->
			<div class="form-group">
				<label class="control-label" for="txtidResource"><spring:message code="billLine.idResource"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtidResource.$touched || frmidResource.$dirty) && frmBillLines.txtidResource.$invalid }">
					<input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="billLine.idResource" />
					<!-- <input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="billLine.idResource" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtidResource.$invalid">
						<validation-tooltip target="txtidResource">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idActivityPrice -->
			<div class="form-group">
				<label class="control-label" for="txtidActivityPrice"><spring:message code="billLine.idActivityPrice"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtidActivityPrice.$touched || frmidActivityPrice.$dirty) && frmBillLines.txtidActivityPrice.$invalid }">
					<input class="form-control" type="text" id="txtidActivityPrice" name="txtidActivityPrice" ng-model="billLine.idActivityPrice" />
					<!-- <input class="form-control" type="text" id="txtidActivityPrice" name="txtidActivityPrice" ng-model="billLine.idActivityPrice" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtidActivityPrice.$invalid">
						<validation-tooltip target="txtidActivityPrice">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- startDate -->
			<div class="form-group">
				<label class="control-label" for="txtstartDate"><spring:message code="billLine.startDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtstartDate.$touched || frmstartDate.$dirty) && frmBillLines.txtstartDate.$invalid }">
					<input class="form-control" type="text" id="txtstartDate" name="txtstartDate" ng-model="billLine.startDate" />
					<!-- <input class="form-control" type="text" id="txtstartDate" name="txtstartDate" ng-model="billLine.startDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtstartDate.$invalid">
						<validation-tooltip target="txtstartDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- endDate -->
			<div class="form-group">
				<label class="control-label" for="txtendDate"><spring:message code="billLine.endDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtendDate.$touched || frmendDate.$dirty) && frmBillLines.txtendDate.$invalid }">
					<input class="form-control" type="text" id="txtendDate" name="txtendDate" ng-model="billLine.endDate" />
					<!-- <input class="form-control" type="text" id="txtendDate" name="txtendDate" ng-model="billLine.endDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtendDate.$invalid">
						<validation-tooltip target="txtendDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idMeasureUnit -->
			<div class="form-group">
				<label class="control-label" for="txtidMeasureUnit"><spring:message code="billLine.idMeasureUnit"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtidMeasureUnit.$touched || frmidMeasureUnit.$dirty) && frmBillLines.txtidMeasureUnit.$invalid }">
					<input class="form-control" type="text" id="txtidMeasureUnit" name="txtidMeasureUnit" ng-model="billLine.idMeasureUnit" />
					<!-- <input class="form-control" type="text" id="txtidMeasureUnit" name="txtidMeasureUnit" ng-model="billLine.idMeasureUnit" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtidMeasureUnit.$invalid">
						<validation-tooltip target="txtidMeasureUnit">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- extra -->
			<div class="form-group">
				<label class="control-label" for="txtextra"><spring:message code="billLine.extra"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtextra.$touched || frmextra.$dirty) && frmBillLines.txtextra.$invalid }">
					<input class="form-control" type="text" id="txtextra" name="txtextra" ng-model="billLine.extra" />
					<!-- <input class="form-control" type="text" id="txtextra" name="txtextra" ng-model="billLine.extra" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtextra.$invalid">
						<validation-tooltip target="txtextra">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- billingType -->
			<div class="form-group">
				<label class="control-label" for="txtbillingType"><spring:message code="billLine.billingType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmBillLines.txtbillingType.$touched || frmbillingType.$dirty) && frmBillLines.txtbillingType.$invalid }">
					<input class="form-control" type="text" id="txtbillingType" name="txtbillingType" ng-model="billLine.billingType" />
					<!-- <input class="form-control" type="text" id="txtbillingType" name="txtbillingType" ng-model="billLine.billingType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmBillLines.txtbillingType.$invalid">
						<validation-tooltip target="txtbillingType">
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
