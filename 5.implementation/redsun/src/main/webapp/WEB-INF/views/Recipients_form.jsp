<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/RecipientsController.js"></script>
<script src="${contextPath}/static/js/services/RecipientsService.js"></script>

<div ng-controller="recipientController" ng-init="initEdit(${id})" >
	<form name="frmRecipient" novalidate class="form-inline">
		<h2><spring:message code="recipient.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="recipient.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtname.$touched || frmname.$dirty) && frmRecipients.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="recipient.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="recipient.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- companyNumber -->
			<div class="form-group">
				<label class="control-label" for="txtcompanyNumber"><spring:message code="recipient.companyNumber"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcompanyNumber.$touched || frmcompanyNumber.$dirty) && frmRecipients.txtcompanyNumber.$invalid }">
					<input class="form-control" type="text" id="txtcompanyNumber" name="txtcompanyNumber" ng-model="recipient.companyNumber" />
					<!-- <input class="form-control" type="text" id="txtcompanyNumber" name="txtcompanyNumber" ng-model="recipient.companyNumber" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcompanyNumber.$invalid">
						<validation-tooltip target="txtcompanyNumber">
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
				<label class="control-label" for="txtnumTax"><spring:message code="recipient.numTax"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtnumTax.$touched || frmnumTax.$dirty) && frmRecipients.txtnumTax.$invalid }">
					<input class="form-control" type="text" id="txtnumTax" name="txtnumTax" ng-model="recipient.numTax" />
					<!-- <input class="form-control" type="text" id="txtnumTax" name="txtnumTax" ng-model="recipient.numTax" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtnumTax.$invalid">
						<validation-tooltip target="txtnumTax">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- bankName -->
			<div class="form-group">
				<label class="control-label" for="txtbankName"><spring:message code="recipient.bankName"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtbankName.$touched || frmbankName.$dirty) && frmRecipients.txtbankName.$invalid }">
					<input class="form-control" type="text" id="txtbankName" name="txtbankName" ng-model="recipient.bankName" />
					<!-- <input class="form-control" type="text" id="txtbankName" name="txtbankName" ng-model="recipient.bankName" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtbankName.$invalid">
						<validation-tooltip target="txtbankName">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- ibanCountry -->
			<div class="form-group">
				<label class="control-label" for="txtibanCountry"><spring:message code="recipient.ibanCountry"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtibanCountry.$touched || frmibanCountry.$dirty) && frmRecipients.txtibanCountry.$invalid }">
					<input class="form-control" type="text" id="txtibanCountry" name="txtibanCountry" ng-model="recipient.ibanCountry" />
					<!-- <input class="form-control" type="text" id="txtibanCountry" name="txtibanCountry" ng-model="recipient.ibanCountry" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtibanCountry.$invalid">
						<validation-tooltip target="txtibanCountry">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- ibanKey -->
			<div class="form-group">
				<label class="control-label" for="txtibanKey"><spring:message code="recipient.ibanKey"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtibanKey.$touched || frmibanKey.$dirty) && frmRecipients.txtibanKey.$invalid }">
					<input class="form-control" type="text" id="txtibanKey" name="txtibanKey" ng-model="recipient.ibanKey" />
					<!-- <input class="form-control" type="text" id="txtibanKey" name="txtibanKey" ng-model="recipient.ibanKey" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtibanKey.$invalid">
						<validation-tooltip target="txtibanKey">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- ibanBban -->
			<div class="form-group">
				<label class="control-label" for="txtibanBban"><spring:message code="recipient.ibanBban"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtibanBban.$touched || frmibanBban.$dirty) && frmRecipients.txtibanBban.$invalid }">
					<input class="form-control" type="text" id="txtibanBban" name="txtibanBban" ng-model="recipient.ibanBban" />
					<!-- <input class="form-control" type="text" id="txtibanBban" name="txtibanBban" ng-model="recipient.ibanBban" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtibanBban.$invalid">
						<validation-tooltip target="txtibanBban">
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
				<label class="control-label" for="txtdesignation"><spring:message code="recipient.designation"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtdesignation.$touched || frmdesignation.$dirty) && frmRecipients.txtdesignation.$invalid }">
					<input class="form-control" type="text" id="txtdesignation" name="txtdesignation" ng-model="recipient.designation" />
					<!-- <input class="form-control" type="text" id="txtdesignation" name="txtdesignation" ng-model="recipient.designation" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtdesignation.$invalid">
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
				<label class="control-label" for="txtstreet"><spring:message code="recipient.street"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtstreet.$touched || frmstreet.$dirty) && frmRecipients.txtstreet.$invalid }">
					<input class="form-control" type="text" id="txtstreet" name="txtstreet" ng-model="recipient.street" />
					<!-- <input class="form-control" type="text" id="txtstreet" name="txtstreet" ng-model="recipient.street" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtstreet.$invalid">
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
				<label class="control-label" for="txtcomplement"><spring:message code="recipient.complement"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcomplement.$touched || frmcomplement.$dirty) && frmRecipients.txtcomplement.$invalid }">
					<input class="form-control" type="text" id="txtcomplement" name="txtcomplement" ng-model="recipient.complement" />
					<!-- <input class="form-control" type="text" id="txtcomplement" name="txtcomplement" ng-model="recipient.complement" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcomplement.$invalid">
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
				<label class="control-label" for="txtzip"><spring:message code="recipient.zip"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtzip.$touched || frmzip.$dirty) && frmRecipients.txtzip.$invalid }">
					<input class="form-control" type="text" id="txtzip" name="txtzip" ng-model="recipient.zip" />
					<!-- <input class="form-control" type="text" id="txtzip" name="txtzip" ng-model="recipient.zip" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtzip.$invalid">
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
				<label class="control-label" for="txtcity"><spring:message code="recipient.city"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcity.$touched || frmcity.$dirty) && frmRecipients.txtcity.$invalid }">
					<input class="form-control" type="text" id="txtcity" name="txtcity" ng-model="recipient.city" />
					<!-- <input class="form-control" type="text" id="txtcity" name="txtcity" ng-model="recipient.city" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcity.$invalid">
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
				<label class="control-label" for="txtstate"><spring:message code="recipient.state"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtstate.$touched || frmstate.$dirty) && frmRecipients.txtstate.$invalid }">
					<input class="form-control" type="text" id="txtstate" name="txtstate" ng-model="recipient.state" />
					<!-- <input class="form-control" type="text" id="txtstate" name="txtstate" ng-model="recipient.state" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtstate.$invalid">
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
				<label class="control-label" for="txtcountry"><spring:message code="recipient.country"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcountry.$touched || frmcountry.$dirty) && frmRecipients.txtcountry.$invalid }">
					<input class="form-control" type="text" id="txtcountry" name="txtcountry" ng-model="recipient.country" />
					<!-- <input class="form-control" type="text" id="txtcountry" name="txtcountry" ng-model="recipient.country" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcountry.$invalid">
						<validation-tooltip target="txtcountry">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- taxFree -->
			<div class="form-group">
				<label class="control-label" for="txttaxFree"><spring:message code="recipient.taxFree"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txttaxFree.$touched || frmtaxFree.$dirty) && frmRecipients.txttaxFree.$invalid }">
					<input class="form-control" type="text" id="txttaxFree" name="txttaxFree" ng-model="recipient.taxFree" />
					<!-- <input class="form-control" type="text" id="txttaxFree" name="txttaxFree" ng-model="recipient.taxFree" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txttaxFree.$invalid">
						<validation-tooltip target="txttaxFree">
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
				<label class="control-label" for="txtidle"><spring:message code="recipient.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtidle.$touched || frmidle.$dirty) && frmRecipients.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="recipient.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="recipient.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- legalNotice -->
			<div class="form-group">
				<label class="control-label" for="txtlegalNotice"><spring:message code="recipient.legalNotice"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtlegalNotice.$touched || frmlegalNotice.$dirty) && frmRecipients.txtlegalNotice.$invalid }">
					<input class="form-control" type="text" id="txtlegalNotice" name="txtlegalNotice" ng-model="recipient.legalNotice" />
					<!-- <input class="form-control" type="text" id="txtlegalNotice" name="txtlegalNotice" ng-model="recipient.legalNotice" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtlegalNotice.$invalid">
						<validation-tooltip target="txtlegalNotice">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- contactName -->
			<div class="form-group">
				<label class="control-label" for="txtcontactName"><spring:message code="recipient.contactName"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcontactName.$touched || frmcontactName.$dirty) && frmRecipients.txtcontactName.$invalid }">
					<input class="form-control" type="text" id="txtcontactName" name="txtcontactName" ng-model="recipient.contactName" />
					<!-- <input class="form-control" type="text" id="txtcontactName" name="txtcontactName" ng-model="recipient.contactName" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcontactName.$invalid">
						<validation-tooltip target="txtcontactName">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- contactEmail -->
			<div class="form-group">
				<label class="control-label" for="txtcontactEmail"><spring:message code="recipient.contactEmail"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcontactEmail.$touched || frmcontactEmail.$dirty) && frmRecipients.txtcontactEmail.$invalid }">
					<input class="form-control" type="text" id="txtcontactEmail" name="txtcontactEmail" ng-model="recipient.contactEmail" />
					<!-- <input class="form-control" type="text" id="txtcontactEmail" name="txtcontactEmail" ng-model="recipient.contactEmail" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcontactEmail.$invalid">
						<validation-tooltip target="txtcontactEmail">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- contactPhone -->
			<div class="form-group">
				<label class="control-label" for="txtcontactPhone"><spring:message code="recipient.contactPhone"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcontactPhone.$touched || frmcontactPhone.$dirty) && frmRecipients.txtcontactPhone.$invalid }">
					<input class="form-control" type="text" id="txtcontactPhone" name="txtcontactPhone" ng-model="recipient.contactPhone" />
					<!-- <input class="form-control" type="text" id="txtcontactPhone" name="txtcontactPhone" ng-model="recipient.contactPhone" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcontactPhone.$invalid">
						<validation-tooltip target="txtcontactPhone">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- contactMobile -->
			<div class="form-group">
				<label class="control-label" for="txtcontactMobile"><spring:message code="recipient.contactMobile"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtcontactMobile.$touched || frmcontactMobile.$dirty) && frmRecipients.txtcontactMobile.$invalid }">
					<input class="form-control" type="text" id="txtcontactMobile" name="txtcontactMobile" ng-model="recipient.contactMobile" />
					<!-- <input class="form-control" type="text" id="txtcontactMobile" name="txtcontactMobile" ng-model="recipient.contactMobile" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtcontactMobile.$invalid">
						<validation-tooltip target="txtcontactMobile">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- bankNationalAccountNumber -->
			<div class="form-group">
				<label class="control-label" for="txtbankNationalAccountNumber"><spring:message code="recipient.bankNationalAccountNumber"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtbankNationalAccountNumber.$touched || frmbankNationalAccountNumber.$dirty) && frmRecipients.txtbankNationalAccountNumber.$invalid }">
					<input class="form-control" type="text" id="txtbankNationalAccountNumber" name="txtbankNationalAccountNumber" ng-model="recipient.bankNationalAccountNumber" />
					<!-- <input class="form-control" type="text" id="txtbankNationalAccountNumber" name="txtbankNationalAccountNumber" ng-model="recipient.bankNationalAccountNumber" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtbankNationalAccountNumber.$invalid">
						<validation-tooltip target="txtbankNationalAccountNumber">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- bankInternationalAccountNumber -->
			<div class="form-group">
				<label class="control-label" for="txtbankInternationalAccountNumber"><spring:message code="recipient.bankInternationalAccountNumber"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtbankInternationalAccountNumber.$touched || frmbankInternationalAccountNumber.$dirty) && frmRecipients.txtbankInternationalAccountNumber.$invalid }">
					<input class="form-control" type="text" id="txtbankInternationalAccountNumber" name="txtbankInternationalAccountNumber" ng-model="recipient.bankInternationalAccountNumber" />
					<!-- <input class="form-control" type="text" id="txtbankInternationalAccountNumber" name="txtbankInternationalAccountNumber" ng-model="recipient.bankInternationalAccountNumber" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtbankInternationalAccountNumber.$invalid">
						<validation-tooltip target="txtbankInternationalAccountNumber">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- bankIdentificationCode -->
			<div class="form-group">
				<label class="control-label" for="txtbankIdentificationCode"><spring:message code="recipient.bankIdentificationCode"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmRecipients.txtbankIdentificationCode.$touched || frmbankIdentificationCode.$dirty) && frmRecipients.txtbankIdentificationCode.$invalid }">
					<input class="form-control" type="text" id="txtbankIdentificationCode" name="txtbankIdentificationCode" ng-model="recipient.bankIdentificationCode" />
					<!-- <input class="form-control" type="text" id="txtbankIdentificationCode" name="txtbankIdentificationCode" ng-model="recipient.bankIdentificationCode" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmRecipients.txtbankIdentificationCode.$invalid">
						<validation-tooltip target="txtbankIdentificationCode">
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
