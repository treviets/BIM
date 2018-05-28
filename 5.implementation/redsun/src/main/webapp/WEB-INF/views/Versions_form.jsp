<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/VersionsController.js"></script>
<script src="${contextPath}/static/js/services/VersionsService.js"></script>

<div ng-controller="versionController" ng-init="initEdit(${id})" >
	<form name="frmVersion" novalidate class="form-inline">
		<h2><spring:message code="version.title" /></h2>
		<!-- Top actions -->
		<div class="row">
			<div class="pull-right" ng-click="save(${id})">
				<img src="${ contextPath }/static/images/save.png"/>
			</div>
		</div>
		<!-- Context -->
		<div>
			<!-- idProduct -->
			<div class="form-group">
				<label class="control-label" for="txtidProduct"><spring:message code="version.idProduct"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtidProduct.$touched || frmidProduct.$dirty) && frmVersions.txtidProduct.$invalid }">
					<input class="form-control" type="text" id="txtidProduct" name="txtidProduct" ng-model="version.idProduct" />
					<!-- <input class="form-control" type="text" id="txtidProduct" name="txtidProduct" ng-model="version.idProduct" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtidProduct.$invalid">
						<validation-tooltip target="txtidProduct">
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
				<label class="control-label" for="txtidContact"><spring:message code="version.idContact"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtidContact.$touched || frmidContact.$dirty) && frmVersions.txtidContact.$invalid }">
					<input class="form-control" type="text" id="txtidContact" name="txtidContact" ng-model="version.idContact" />
					<!-- <input class="form-control" type="text" id="txtidContact" name="txtidContact" ng-model="version.idContact" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtidContact.$invalid">
						<validation-tooltip target="txtidContact">
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
				<label class="control-label" for="txtidResource"><spring:message code="version.idResource"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtidResource.$touched || frmidResource.$dirty) && frmVersions.txtidResource.$invalid }">
					<input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="version.idResource" />
					<!-- <input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="version.idResource" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtidResource.$invalid">
						<validation-tooltip target="txtidResource">
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
				<label class="control-label" for="txtname"><spring:message code="version.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtname.$touched || frmname.$dirty) && frmVersions.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="version.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="version.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtname.$invalid">
						<validation-tooltip target="txtname">
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
				<label class="control-label" for="txtdescription"><spring:message code="version.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtdescription.$touched || frmdescription.$dirty) && frmVersions.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="version.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="version.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtdescription.$invalid">
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
				<label class="control-label" for="txtcreationDate"><spring:message code="version.creationDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtcreationDate.$touched || frmcreationDate.$dirty) && frmVersions.txtcreationDate.$invalid }">
					<input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="version.creationDate" />
					<!-- <input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="version.creationDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtcreationDate.$invalid">
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
				<label class="control-label" for="txtidle"><spring:message code="version.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtidle.$touched || frmidle.$dirty) && frmVersions.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="version.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="version.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- initialEisDate -->
			<div class="form-group">
				<label class="control-label" for="txtinitialEisDate"><spring:message code="version.initialEisDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtinitialEisDate.$touched || frminitialEisDate.$dirty) && frmVersions.txtinitialEisDate.$invalid }">
					<input class="form-control" type="text" id="txtinitialEisDate" name="txtinitialEisDate" ng-model="version.initialEisDate" />
					<!-- <input class="form-control" type="text" id="txtinitialEisDate" name="txtinitialEisDate" ng-model="version.initialEisDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtinitialEisDate.$invalid">
						<validation-tooltip target="txtinitialEisDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- plannedEisDate -->
			<div class="form-group">
				<label class="control-label" for="txtplannedEisDate"><spring:message code="version.plannedEisDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtplannedEisDate.$touched || frmplannedEisDate.$dirty) && frmVersions.txtplannedEisDate.$invalid }">
					<input class="form-control" type="text" id="txtplannedEisDate" name="txtplannedEisDate" ng-model="version.plannedEisDate" />
					<!-- <input class="form-control" type="text" id="txtplannedEisDate" name="txtplannedEisDate" ng-model="version.plannedEisDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtplannedEisDate.$invalid">
						<validation-tooltip target="txtplannedEisDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- realEisDate -->
			<div class="form-group">
				<label class="control-label" for="txtrealEisDate"><spring:message code="version.realEisDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtrealEisDate.$touched || frmrealEisDate.$dirty) && frmVersions.txtrealEisDate.$invalid }">
					<input class="form-control" type="text" id="txtrealEisDate" name="txtrealEisDate" ng-model="version.realEisDate" />
					<!-- <input class="form-control" type="text" id="txtrealEisDate" name="txtrealEisDate" ng-model="version.realEisDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtrealEisDate.$invalid">
						<validation-tooltip target="txtrealEisDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- initialEndDate -->
			<div class="form-group">
				<label class="control-label" for="txtinitialEndDate"><spring:message code="version.initialEndDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtinitialEndDate.$touched || frminitialEndDate.$dirty) && frmVersions.txtinitialEndDate.$invalid }">
					<input class="form-control" type="text" id="txtinitialEndDate" name="txtinitialEndDate" ng-model="version.initialEndDate" />
					<!-- <input class="form-control" type="text" id="txtinitialEndDate" name="txtinitialEndDate" ng-model="version.initialEndDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtinitialEndDate.$invalid">
						<validation-tooltip target="txtinitialEndDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- plannedEndDate -->
			<div class="form-group">
				<label class="control-label" for="txtplannedEndDate"><spring:message code="version.plannedEndDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtplannedEndDate.$touched || frmplannedEndDate.$dirty) && frmVersions.txtplannedEndDate.$invalid }">
					<input class="form-control" type="text" id="txtplannedEndDate" name="txtplannedEndDate" ng-model="version.plannedEndDate" />
					<!-- <input class="form-control" type="text" id="txtplannedEndDate" name="txtplannedEndDate" ng-model="version.plannedEndDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtplannedEndDate.$invalid">
						<validation-tooltip target="txtplannedEndDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- realEndDate -->
			<div class="form-group">
				<label class="control-label" for="txtrealEndDate"><spring:message code="version.realEndDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtrealEndDate.$touched || frmrealEndDate.$dirty) && frmVersions.txtrealEndDate.$invalid }">
					<input class="form-control" type="text" id="txtrealEndDate" name="txtrealEndDate" ng-model="version.realEndDate" />
					<!-- <input class="form-control" type="text" id="txtrealEndDate" name="txtrealEndDate" ng-model="version.realEndDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtrealEndDate.$invalid">
						<validation-tooltip target="txtrealEndDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- isEis -->
			<div class="form-group">
				<label class="control-label" for="txtisEis"><spring:message code="version.isEis"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtisEis.$touched || frmisEis.$dirty) && frmVersions.txtisEis.$invalid }">
					<input class="form-control" type="text" id="txtisEis" name="txtisEis" ng-model="version.isEis" />
					<!-- <input class="form-control" type="text" id="txtisEis" name="txtisEis" ng-model="version.isEis" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtisEis.$invalid">
						<validation-tooltip target="txtisEis">
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
				<label class="control-label" for="txtscope"><spring:message code="version.scope"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtscope.$touched || frmscope.$dirty) && frmVersions.txtscope.$invalid }">
					<input class="form-control" type="text" id="txtscope" name="txtscope" ng-model="version.scope" />
					<!-- <input class="form-control" type="text" id="txtscope" name="txtscope" ng-model="version.scope" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtscope.$invalid">
						<validation-tooltip target="txtscope">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- versionNumber -->
			<div class="form-group">
				<label class="control-label" for="txtversionNumber"><spring:message code="version.versionNumber"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtversionNumber.$touched || frmversionNumber.$dirty) && frmVersions.txtversionNumber.$invalid }">
					<input class="form-control" type="text" id="txtversionNumber" name="txtversionNumber" ng-model="version.versionNumber" />
					<!-- <input class="form-control" type="text" id="txtversionNumber" name="txtversionNumber" ng-model="version.versionNumber" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtversionNumber.$invalid">
						<validation-tooltip target="txtversionNumber">
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
				<label class="control-label" for="txtidUser"><spring:message code="version.idUser"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtidUser.$touched || frmidUser.$dirty) && frmVersions.txtidUser.$invalid }">
					<input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="version.idUser" />
					<!-- <input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="version.idUser" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtidUser.$invalid">
						<validation-tooltip target="txtidUser">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idVersionType -->
			<div class="form-group">
				<label class="control-label" for="txtidVersionType"><spring:message code="version.idVersionType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmVersions.txtidVersionType.$touched || frmidVersionType.$dirty) && frmVersions.txtidVersionType.$invalid }">
					<input class="form-control" type="text" id="txtidVersionType" name="txtidVersionType" ng-model="version.idVersionType" />
					<!-- <input class="form-control" type="text" id="txtidVersionType" name="txtidVersionType" ng-model="version.idVersionType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmVersions.txtidVersionType.$invalid">
						<validation-tooltip target="txtidVersionType">
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
