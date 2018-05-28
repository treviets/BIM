<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/ActivityPricesController.js"></script>
<script src="${contextPath}/static/js/services/ActivityPricesService.js"></script>

<div ng-controller="activityPriceController" ng-init="initEdit(${id})" >
	<form name="frmActivityPrice" novalidate class="form-inline">
		<h2><spring:message code="activityPrice.title" /></h2>
		<!-- Top actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
		<!-- Context -->
		<div>
			<!-- idProject -->
			<div class="form-group">
				<label class="control-label" for="txtidProject"><spring:message code="activityPrice.idProject"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtidProject.$touched || frmidProject.$dirty) && frmActivityPrices.txtidProject.$invalid }">
					<input class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="activityPrice.idProject" />
					<!-- <input class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="activityPrice.idProject" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtidProject.$invalid">
						<validation-tooltip target="txtidProject">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idActivityType -->
			<div class="form-group">
				<label class="control-label" for="txtidActivityType"><spring:message code="activityPrice.idActivityType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtidActivityType.$touched || frmidActivityType.$dirty) && frmActivityPrices.txtidActivityType.$invalid }">
					<input class="form-control" type="text" id="txtidActivityType" name="txtidActivityType" ng-model="activityPrice.idActivityType" />
					<!-- <input class="form-control" type="text" id="txtidActivityType" name="txtidActivityType" ng-model="activityPrice.idActivityType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtidActivityType.$invalid">
						<validation-tooltip target="txtidActivityType">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- name -->
			<div class="form-group">
				<label class="control-label" for="txtname"><spring:message code="activityPrice.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtname.$touched || frmname.$dirty) && frmActivityPrices.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="activityPrice.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="activityPrice.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- priceCost -->
			<div class="form-group">
				<label class="control-label" for="txtpriceCost"><spring:message code="activityPrice.priceCost"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtpriceCost.$touched || frmpriceCost.$dirty) && frmActivityPrices.txtpriceCost.$invalid }">
					<input class="form-control" type="text" id="txtpriceCost" name="txtpriceCost" ng-model="activityPrice.priceCost" />
					<!-- <input class="form-control" type="text" id="txtpriceCost" name="txtpriceCost" ng-model="activityPrice.priceCost" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtpriceCost.$invalid">
						<validation-tooltip target="txtpriceCost">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- subcontractor -->
			<div class="form-group">
				<label class="control-label" for="txtsubcontractor"><spring:message code="activityPrice.subcontractor"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtsubcontractor.$touched || frmsubcontractor.$dirty) && frmActivityPrices.txtsubcontractor.$invalid }">
					<input class="form-control" type="text" id="txtsubcontractor" name="txtsubcontractor" ng-model="activityPrice.subcontractor" />
					<!-- <input class="form-control" type="text" id="txtsubcontractor" name="txtsubcontractor" ng-model="activityPrice.subcontractor" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtsubcontractor.$invalid">
						<validation-tooltip target="txtsubcontractor">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- sortOrder -->
			<div class="form-group">
				<label class="control-label" for="txtsortOrder"><spring:message code="activityPrice.sortOrder"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtsortOrder.$touched || frmsortOrder.$dirty) && frmActivityPrices.txtsortOrder.$invalid }">
					<input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="activityPrice.sortOrder" />
					<!-- <input class="form-control" type="text" id="txtsortOrder" name="txtsortOrder" ng-model="activityPrice.sortOrder" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtsortOrder.$invalid">
						<validation-tooltip target="txtsortOrder">
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
				<label class="control-label" for="txtidle"><spring:message code="activityPrice.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtidle.$touched || frmidle.$dirty) && frmActivityPrices.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="activityPrice.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="activityPrice.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- subcontractorCost -->
			<div class="form-group">
				<label class="control-label" for="txtsubcontractorCost"><spring:message code="activityPrice.subcontractorCost"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtsubcontractorCost.$touched || frmsubcontractorCost.$dirty) && frmActivityPrices.txtsubcontractorCost.$invalid }">
					<input class="form-control" type="text" id="txtsubcontractorCost" name="txtsubcontractorCost" ng-model="activityPrice.subcontractorCost" />
					<!-- <input class="form-control" type="text" id="txtsubcontractorCost" name="txtsubcontractorCost" ng-model="activityPrice.subcontractorCost" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtsubcontractorCost.$invalid">
						<validation-tooltip target="txtsubcontractorCost">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idTeam -->
			<div class="form-group">
				<label class="control-label" for="txtidTeam"><spring:message code="activityPrice.idTeam"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtidTeam.$touched || frmidTeam.$dirty) && frmActivityPrices.txtidTeam.$invalid }">
					<input class="form-control" type="text" id="txtidTeam" name="txtidTeam" ng-model="activityPrice.idTeam" />
					<!-- <input class="form-control" type="text" id="txtidTeam" name="txtidTeam" ng-model="activityPrice.idTeam" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtidTeam.$invalid">
						<validation-tooltip target="txtidTeam">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- commissionCost -->
			<div class="form-group">
				<label class="control-label" for="txtcommissionCost"><spring:message code="activityPrice.commissionCost"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtcommissionCost.$touched || frmcommissionCost.$dirty) && frmActivityPrices.txtcommissionCost.$invalid }">
					<input class="form-control" type="text" id="txtcommissionCost" name="txtcommissionCost" ng-model="activityPrice.commissionCost" />
					<!-- <input class="form-control" type="text" id="txtcommissionCost" name="txtcommissionCost" ng-model="activityPrice.commissionCost" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtcommissionCost.$invalid">
						<validation-tooltip target="txtcommissionCost">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- isRef -->
			<div class="form-group">
				<label class="control-label" for="txtisRef"><spring:message code="activityPrice.isRef"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtisRef.$touched || frmisRef.$dirty) && frmActivityPrices.txtisRef.$invalid }">
					<input class="form-control" type="text" id="txtisRef" name="txtisRef" ng-model="activityPrice.isRef" />
					<!-- <input class="form-control" type="text" id="txtisRef" name="txtisRef" ng-model="activityPrice.isRef" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtisRef.$invalid">
						<validation-tooltip target="txtisRef">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- pct -->
			<div class="form-group">
				<label class="control-label" for="txtpct"><spring:message code="activityPrice.pct"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtpct.$touched || frmpct.$dirty) && frmActivityPrices.txtpct.$invalid }">
					<input class="form-control" type="text" id="txtpct" name="txtpct" ng-model="activityPrice.pct" />
					<!-- <input class="form-control" type="text" id="txtpct" name="txtpct" ng-model="activityPrice.pct" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtpct.$invalid">
						<validation-tooltip target="txtpct">
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
				<label class="control-label" for="txtidUser"><spring:message code="activityPrice.idUser"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtidUser.$touched || frmidUser.$dirty) && frmActivityPrices.txtidUser.$invalid }">
					<input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="activityPrice.idUser" />
					<!-- <input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="activityPrice.idUser" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtidUser.$invalid">
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
				<label class="control-label" for="txtcreationDate"><spring:message code="activityPrice.creationDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmActivityPrices.txtcreationDate.$touched || frmcreationDate.$dirty) && frmActivityPrices.txtcreationDate.$invalid }">
					<input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="activityPrice.creationDate" />
					<!-- <input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="activityPrice.creationDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmActivityPrices.txtcreationDate.$invalid">
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
