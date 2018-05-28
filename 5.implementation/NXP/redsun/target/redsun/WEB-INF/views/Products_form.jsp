<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/ProductsController.js"></script>
<script src="${contextPath}/static/js/services/ProductsService.js"></script>

<div ng-controller="productController" ng-init="initEdit(${id})" >
	<form name="frmProduct" novalidate class="form-inline">
		<h2><spring:message code="product.title" /></h2>
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
				<label class="control-label" for="txtname"><spring:message code="product.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtname.$touched || frmname.$dirty) && frmProducts.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="product.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="product.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtname.$invalid">
						<validation-tooltip target="txtname">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idClient -->
			<div class="form-group">
				<label class="control-label" for="txtidClient"><spring:message code="product.idClient"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidClient.$touched || frmidClient.$dirty) && frmProducts.txtidClient.$invalid }">
					<input class="form-control" type="text" id="txtidClient" name="txtidClient" ng-model="product.idClient" />
					<!-- <input class="form-control" type="text" id="txtidClient" name="txtidClient" ng-model="product.idClient" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidClient.$invalid">
						<validation-tooltip target="txtidClient">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idContact -->
			<div class="form-group">
				<label class="control-label" for="txtidContact"><spring:message code="product.idContact"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidContact.$touched || frmidContact.$dirty) && frmProducts.txtidContact.$invalid }">
					<input class="form-control" type="text" id="txtidContact" name="txtidContact" ng-model="product.idContact" />
					<!-- <input class="form-control" type="text" id="txtidContact" name="txtidContact" ng-model="product.idContact" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidContact.$invalid">
						<validation-tooltip target="txtidContact">
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
				<label class="control-label" for="txtdescription"><spring:message code="product.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtdescription.$touched || frmdescription.$dirty) && frmProducts.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="product.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="product.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtdescription.$invalid">
						<validation-tooltip target="txtdescription">
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
				<label class="control-label" for="txtcreationDate"><spring:message code="product.creationDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtcreationDate.$touched || frmcreationDate.$dirty) && frmProducts.txtcreationDate.$invalid }">
					<input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="product.creationDate" />
					<!-- <input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="product.creationDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtcreationDate.$invalid">
						<validation-tooltip target="txtcreationDate">
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
				<label class="control-label" for="txtidle"><spring:message code="product.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidle.$touched || frmidle.$dirty) && frmProducts.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="product.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="product.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idProduct -->
			<div class="form-group">
				<label class="control-label" for="txtidProduct"><spring:message code="product.idProduct"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidProduct.$touched || frmidProduct.$dirty) && frmProducts.txtidProduct.$invalid }">
					<input class="form-control" type="text" id="txtidProduct" name="txtidProduct" ng-model="product.idProduct" />
					<!-- <input class="form-control" type="text" id="txtidProduct" name="txtidProduct" ng-model="product.idProduct" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidProduct.$invalid">
						<validation-tooltip target="txtidProduct">
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
				<label class="control-label" for="txtdesignation"><spring:message code="product.designation"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtdesignation.$touched || frmdesignation.$dirty) && frmProducts.txtdesignation.$invalid }">
					<input class="form-control" type="text" id="txtdesignation" name="txtdesignation" ng-model="product.designation" />
					<!-- <input class="form-control" type="text" id="txtdesignation" name="txtdesignation" ng-model="product.designation" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtdesignation.$invalid">
						<validation-tooltip target="txtdesignation">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- scope -->
			<div class="form-group">
				<label class="control-label" for="txtscope"><spring:message code="product.scope"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtscope.$touched || frmscope.$dirty) && frmProducts.txtscope.$invalid }">
					<input class="form-control" type="text" id="txtscope" name="txtscope" ng-model="product.scope" />
					<!-- <input class="form-control" type="text" id="txtscope" name="txtscope" ng-model="product.scope" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtscope.$invalid">
						<validation-tooltip target="txtscope">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idProductType -->
			<div class="form-group">
				<label class="control-label" for="txtidProductType"><spring:message code="product.idProductType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidProductType.$touched || frmidProductType.$dirty) && frmProducts.txtidProductType.$invalid }">
					<input class="form-control" type="text" id="txtidProductType" name="txtidProductType" ng-model="product.idProductType" />
					<!-- <input class="form-control" type="text" id="txtidProductType" name="txtidProductType" ng-model="product.idProductType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidProductType.$invalid">
						<validation-tooltip target="txtidProductType">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idComponentType -->
			<div class="form-group">
				<label class="control-label" for="txtidComponentType"><spring:message code="product.idComponentType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidComponentType.$touched || frmidComponentType.$dirty) && frmProducts.txtidComponentType.$invalid }">
					<input class="form-control" type="text" id="txtidComponentType" name="txtidComponentType" ng-model="product.idComponentType" />
					<!-- <input class="form-control" type="text" id="txtidComponentType" name="txtidComponentType" ng-model="product.idComponentType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidComponentType.$invalid">
						<validation-tooltip target="txtidComponentType">
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
				<label class="control-label" for="txtidResource"><spring:message code="product.idResource"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidResource.$touched || frmidResource.$dirty) && frmProducts.txtidResource.$invalid }">
					<input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="product.idResource" />
					<!-- <input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="product.idResource" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidResource.$invalid">
						<validation-tooltip target="txtidResource">
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
				<label class="control-label" for="txtidUser"><spring:message code="product.idUser"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmProducts.txtidUser.$touched || frmidUser.$dirty) && frmProducts.txtidUser.$invalid }">
					<input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="product.idUser" />
					<!-- <input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="product.idUser" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmProducts.txtidUser.$invalid">
						<validation-tooltip target="txtidUser">
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
