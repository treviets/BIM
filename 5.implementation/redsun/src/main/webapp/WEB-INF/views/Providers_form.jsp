<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/ProvidersController.js"></script>
<script src="${contextPath}/static/js/services/ProvidersService.js"></script>

<div ng-controller="providerController" ng-init="initEdit(${id})" >
	<form name="frmProvider" novalidate class="form-inline">
		<h2><spring:message code="provider.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="provider.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtname.$touched || frmname.$dirty) && frmProviders.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="provider.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="provider.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idProviderType -->
			<div class="form-group">
				<label class="control-label" for="txtidProviderType"><spring:message code="provider.idProviderType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtidProviderType.$touched || frmidProviderType.$dirty) && frmProviders.txtidProviderType.$invalid }">
					<input class="form-control" type="text" id="txtidProviderType" name="txtidProviderType" ng-model="provider.idProviderType" />
					<!-- <input class="form-control" type="text" id="txtidProviderType" name="txtidProviderType" ng-model="provider.idProviderType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtidProviderType.$invalid">
						<validation-tooltip target="txtidProviderType">
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
				<label class="control-label" for="txtdescription"><spring:message code="provider.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtdescription.$touched || frmdescription.$dirty) && frmProviders.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="provider.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="provider.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtdescription.$invalid">
						<validation-tooltip target="txtdescription">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- providerCode -->
			<div class="form-group">
				<label class="control-label" for="txtproviderCode"><spring:message code="provider.providerCode"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtproviderCode.$touched || frmproviderCode.$dirty) && frmProviders.txtproviderCode.$invalid }">
					<input class="form-control" type="text" id="txtproviderCode" name="txtproviderCode" ng-model="provider.providerCode" />
					<!-- <input class="form-control" type="text" id="txtproviderCode" name="txtproviderCode" ng-model="provider.providerCode" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtproviderCode.$invalid">
						<validation-tooltip target="txtproviderCode">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idPaymentDelay -->
			<div class="form-group">
				<label class="control-label" for="txtidPaymentDelay"><spring:message code="provider.idPaymentDelay"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtidPaymentDelay.$touched || frmidPaymentDelay.$dirty) && frmProviders.txtidPaymentDelay.$invalid }">
					<input class="form-control" type="text" id="txtidPaymentDelay" name="txtidPaymentDelay" ng-model="provider.idPaymentDelay" />
					<!-- <input class="form-control" type="text" id="txtidPaymentDelay" name="txtidPaymentDelay" ng-model="provider.idPaymentDelay" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtidPaymentDelay.$invalid">
						<validation-tooltip target="txtidPaymentDelay">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- numTax -->
			<div class="form-group">
				<label class="control-label" for="txtnumTax"><spring:message code="provider.numTax"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtnumTax.$touched || frmnumTax.$dirty) && frmProviders.txtnumTax.$invalid }">
					<input class="form-control" type="text" id="txtnumTax" name="txtnumTax" ng-model="provider.numTax" />
					<!-- <input class="form-control" type="text" id="txtnumTax" name="txtnumTax" ng-model="provider.numTax" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtnumTax.$invalid">
						<validation-tooltip target="txtnumTax">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- tax -->
			<div class="form-group">
				<label class="control-label" for="txttax"><spring:message code="provider.tax"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txttax.$touched || frmtax.$dirty) && frmProviders.txttax.$invalid }">
					<input class="form-control" type="text" id="txttax" name="txttax" ng-model="provider.tax" />
					<!-- <input class="form-control" type="text" id="txttax" name="txttax" ng-model="provider.tax" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txttax.$invalid">
						<validation-tooltip target="txttax">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- designation -->
			<div class="form-group">
				<label class="control-label" for="txtdesignation"><spring:message code="provider.designation"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtdesignation.$touched || frmdesignation.$dirty) && frmProviders.txtdesignation.$invalid }">
					<input class="form-control" type="text" id="txtdesignation" name="txtdesignation" ng-model="provider.designation" />
					<!-- <input class="form-control" type="text" id="txtdesignation" name="txtdesignation" ng-model="provider.designation" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtdesignation.$invalid">
						<validation-tooltip target="txtdesignation">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- street -->
			<div class="form-group">
				<label class="control-label" for="txtstreet"><spring:message code="provider.street"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtstreet.$touched || frmstreet.$dirty) && frmProviders.txtstreet.$invalid }">
					<input class="form-control" type="text" id="txtstreet" name="txtstreet" ng-model="provider.street" />
					<!-- <input class="form-control" type="text" id="txtstreet" name="txtstreet" ng-model="provider.street" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtstreet.$invalid">
						<validation-tooltip target="txtstreet">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- complement -->
			<div class="form-group">
				<label class="control-label" for="txtcomplement"><spring:message code="provider.complement"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtcomplement.$touched || frmcomplement.$dirty) && frmProviders.txtcomplement.$invalid }">
					<input class="form-control" type="text" id="txtcomplement" name="txtcomplement" ng-model="provider.complement" />
					<!-- <input class="form-control" type="text" id="txtcomplement" name="txtcomplement" ng-model="provider.complement" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtcomplement.$invalid">
						<validation-tooltip target="txtcomplement">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- zip -->
			<div class="form-group">
				<label class="control-label" for="txtzip"><spring:message code="provider.zip"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtzip.$touched || frmzip.$dirty) && frmProviders.txtzip.$invalid }">
					<input class="form-control" type="text" id="txtzip" name="txtzip" ng-model="provider.zip" />
					<!-- <input class="form-control" type="text" id="txtzip" name="txtzip" ng-model="provider.zip" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtzip.$invalid">
						<validation-tooltip target="txtzip">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- city -->
			<div class="form-group">
				<label class="control-label" for="txtcity"><spring:message code="provider.city"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtcity.$touched || frmcity.$dirty) && frmProviders.txtcity.$invalid }">
					<input class="form-control" type="text" id="txtcity" name="txtcity" ng-model="provider.city" />
					<!-- <input class="form-control" type="text" id="txtcity" name="txtcity" ng-model="provider.city" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtcity.$invalid">
						<validation-tooltip target="txtcity">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- state -->
			<div class="form-group">
				<label class="control-label" for="txtstate"><spring:message code="provider.state"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtstate.$touched || frmstate.$dirty) && frmProviders.txtstate.$invalid }">
					<input class="form-control" type="text" id="txtstate" name="txtstate" ng-model="provider.state" />
					<!-- <input class="form-control" type="text" id="txtstate" name="txtstate" ng-model="provider.state" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtstate.$invalid">
						<validation-tooltip target="txtstate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- country -->
			<div class="form-group">
				<label class="control-label" for="txtcountry"><spring:message code="provider.country"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtcountry.$touched || frmcountry.$dirty) && frmProviders.txtcountry.$invalid }">
					<input class="form-control" type="text" id="txtcountry" name="txtcountry" ng-model="provider.country" />
					<!-- <input class="form-control" type="text" id="txtcountry" name="txtcountry" ng-model="provider.country" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtcountry.$invalid">
						<validation-tooltip target="txtcountry">
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
				<label class="control-label" for="txtidle"><spring:message code="provider.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProviders.txtidle.$touched || frmidle.$dirty) && frmProviders.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="provider.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="provider.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProviders.txtidle.$invalid">
						<validation-tooltip target="txtidle">
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
