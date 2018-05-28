<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/MilestonesController.js"></script>
<script src="${contextPath}/static/js/services/MilestonesService.js"></script>

<div ng-controller="milestoneController" ng-init="initEdit(${id})" >
	<form name="frmMilestone" novalidate class="form-inline">
		<h2><spring:message code="milestone.title" /></h2>
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
				<label class="control-label" for="txtidProject"><spring:message code="milestone.idProject"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidProject.$touched || frmidProject.$dirty) && frmMilestones.txtidProject.$invalid }">
					<input class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="milestone.idProject" />
					<!-- <input class="form-control" type="text" id="txtidProject" name="txtidProject" ng-model="milestone.idProject" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidProject.$invalid">
						<validation-tooltip target="txtidProject">
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
				<label class="control-label" for="txtname"><spring:message code="milestone.name"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtname.$touched || frmname.$dirty) && frmMilestones.txtname.$invalid }">
					<input class="form-control" type="text" id="txtname" name="txtname" ng-model="milestone.name" />
					<!-- <input class="form-control" type="text" id="txtname" name="txtname" ng-model="milestone.name" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtname.$invalid">
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
				<label class="control-label" for="txtdescription"><spring:message code="milestone.description"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtdescription.$touched || frmdescription.$dirty) && frmMilestones.txtdescription.$invalid }">
					<input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="milestone.description" />
					<!-- <input class="form-control" type="text" id="txtdescription" name="txtdescription" ng-model="milestone.description" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtdescription.$invalid">
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
				<label class="control-label" for="txtcreationDate"><spring:message code="milestone.creationDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtcreationDate.$touched || frmcreationDate.$dirty) && frmMilestones.txtcreationDate.$invalid }">
					<input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="milestone.creationDate" />
					<!-- <input class="form-control" type="text" id="txtcreationDate" name="txtcreationDate" ng-model="milestone.creationDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtcreationDate.$invalid">
						<validation-tooltip target="txtcreationDate">
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
				<label class="control-label" for="txtidUser"><spring:message code="milestone.idUser"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidUser.$touched || frmidUser.$dirty) && frmMilestones.txtidUser.$invalid }">
					<input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="milestone.idUser" />
					<!-- <input class="form-control" type="text" id="txtidUser" name="txtidUser" ng-model="milestone.idUser" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidUser.$invalid">
						<validation-tooltip target="txtidUser">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idStatus -->
			<div class="form-group">
				<label class="control-label" for="txtidStatus"><spring:message code="milestone.idStatus"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidStatus.$touched || frmidStatus.$dirty) && frmMilestones.txtidStatus.$invalid }">
					<input class="form-control" type="text" id="txtidStatus" name="txtidStatus" ng-model="milestone.idStatus" />
					<!-- <input class="form-control" type="text" id="txtidStatus" name="txtidStatus" ng-model="milestone.idStatus" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidStatus.$invalid">
						<validation-tooltip target="txtidStatus">
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
				<label class="control-label" for="txtidResource"><spring:message code="milestone.idResource"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidResource.$touched || frmidResource.$dirty) && frmMilestones.txtidResource.$invalid }">
					<input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="milestone.idResource" />
					<!-- <input class="form-control" type="text" id="txtidResource" name="txtidResource" ng-model="milestone.idResource" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidResource.$invalid">
						<validation-tooltip target="txtidResource">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- result -->
			<div class="form-group">
				<label class="control-label" for="txtresult"><spring:message code="milestone.result"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtresult.$touched || frmresult.$dirty) && frmMilestones.txtresult.$invalid }">
					<input class="form-control" type="text" id="txtresult" name="txtresult" ng-model="milestone.result" />
					<!-- <input class="form-control" type="text" id="txtresult" name="txtresult" ng-model="milestone.result" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtresult.$invalid">
						<validation-tooltip target="txtresult">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- comment -->
			<div class="form-group">
				<label class="control-label" for="txtcomment"><spring:message code="milestone.comment"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtcomment.$touched || frmcomment.$dirty) && frmMilestones.txtcomment.$invalid }">
					<input class="form-control" type="text" id="txtcomment" name="txtcomment" ng-model="milestone.comment" />
					<!-- <input class="form-control" type="text" id="txtcomment" name="txtcomment" ng-model="milestone.comment" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtcomment.$invalid">
						<validation-tooltip target="txtcomment">
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
				<label class="control-label" for="txtidle"><spring:message code="milestone.idle"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidle.$touched || frmidle.$dirty) && frmMilestones.txtidle.$invalid }">
					<input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="milestone.idle" />
					<!-- <input class="form-control" type="text" id="txtidle" name="txtidle" ng-model="milestone.idle" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidle.$invalid">
						<validation-tooltip target="txtidle">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idMilestoneType -->
			<div class="form-group">
				<label class="control-label" for="txtidMilestoneType"><spring:message code="milestone.idMilestoneType"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidMilestoneType.$touched || frmidMilestoneType.$dirty) && frmMilestones.txtidMilestoneType.$invalid }">
					<input class="form-control" type="text" id="txtidMilestoneType" name="txtidMilestoneType" ng-model="milestone.idMilestoneType" />
					<!-- <input class="form-control" type="text" id="txtidMilestoneType" name="txtidMilestoneType" ng-model="milestone.idMilestoneType" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidMilestoneType.$invalid">
						<validation-tooltip target="txtidMilestoneType">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idActivity -->
			<div class="form-group">
				<label class="control-label" for="txtidActivity"><spring:message code="milestone.idActivity"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidActivity.$touched || frmidActivity.$dirty) && frmMilestones.txtidActivity.$invalid }">
					<input class="form-control" type="text" id="txtidActivity" name="txtidActivity" ng-model="milestone.idActivity" />
					<!-- <input class="form-control" type="text" id="txtidActivity" name="txtidActivity" ng-model="milestone.idActivity" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidActivity.$invalid">
						<validation-tooltip target="txtidActivity">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- done -->
			<div class="form-group">
				<label class="control-label" for="txtdone"><spring:message code="milestone.done"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtdone.$touched || frmdone.$dirty) && frmMilestones.txtdone.$invalid }">
					<input class="form-control" type="text" id="txtdone" name="txtdone" ng-model="milestone.done" />
					<!-- <input class="form-control" type="text" id="txtdone" name="txtdone" ng-model="milestone.done" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtdone.$invalid">
						<validation-tooltip target="txtdone">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idleDate -->
			<div class="form-group">
				<label class="control-label" for="txtidleDate"><spring:message code="milestone.idleDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidleDate.$touched || frmidleDate.$dirty) && frmMilestones.txtidleDate.$invalid }">
					<input class="form-control" type="text" id="txtidleDate" name="txtidleDate" ng-model="milestone.idleDate" />
					<!-- <input class="form-control" type="text" id="txtidleDate" name="txtidleDate" ng-model="milestone.idleDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidleDate.$invalid">
						<validation-tooltip target="txtidleDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- doneDate -->
			<div class="form-group">
				<label class="control-label" for="txtdoneDate"><spring:message code="milestone.doneDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtdoneDate.$touched || frmdoneDate.$dirty) && frmMilestones.txtdoneDate.$invalid }">
					<input class="form-control" type="text" id="txtdoneDate" name="txtdoneDate" ng-model="milestone.doneDate" />
					<!-- <input class="form-control" type="text" id="txtdoneDate" name="txtdoneDate" ng-model="milestone.doneDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtdoneDate.$invalid">
						<validation-tooltip target="txtdoneDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- handled -->
			<div class="form-group">
				<label class="control-label" for="txthandled"><spring:message code="milestone.handled"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txthandled.$touched || frmhandled.$dirty) && frmMilestones.txthandled.$invalid }">
					<input class="form-control" type="text" id="txthandled" name="txthandled" ng-model="milestone.handled" />
					<!-- <input class="form-control" type="text" id="txthandled" name="txthandled" ng-model="milestone.handled" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txthandled.$invalid">
						<validation-tooltip target="txthandled">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- handledDate -->
			<div class="form-group">
				<label class="control-label" for="txthandledDate"><spring:message code="milestone.handledDate"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txthandledDate.$touched || frmhandledDate.$dirty) && frmMilestones.txthandledDate.$invalid }">
					<input class="form-control" type="text" id="txthandledDate" name="txthandledDate" ng-model="milestone.handledDate" />
					<!-- <input class="form-control" type="text" id="txthandledDate" name="txthandledDate" ng-model="milestone.handledDate" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txthandledDate.$invalid">
						<validation-tooltip target="txthandledDate">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- idVersion -->
			<div class="form-group">
				<label class="control-label" for="txtidVersion"><spring:message code="milestone.idVersion"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtidVersion.$touched || frmidVersion.$dirty) && frmMilestones.txtidVersion.$invalid }">
					<input class="form-control" type="text" id="txtidVersion" name="txtidVersion" ng-model="milestone.idVersion" />
					<!-- <input class="form-control" type="text" id="txtidVersion" name="txtidVersion" ng-model="milestone.idVersion" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtidVersion.$invalid">
						<validation-tooltip target="txtidVersion">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- reference -->
			<div class="form-group">
				<label class="control-label" for="txtreference"><spring:message code="milestone.reference"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtreference.$touched || frmreference.$dirty) && frmMilestones.txtreference.$invalid }">
					<input class="form-control" type="text" id="txtreference" name="txtreference" ng-model="milestone.reference" />
					<!-- <input class="form-control" type="text" id="txtreference" name="txtreference" ng-model="milestone.reference" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtreference.$invalid">
						<validation-tooltip target="txtreference">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- externalReference -->
			<div class="form-group">
				<label class="control-label" for="txtexternalReference"><spring:message code="milestone.externalReference"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtexternalReference.$touched || frmexternalReference.$dirty) && frmMilestones.txtexternalReference.$invalid }">
					<input class="form-control" type="text" id="txtexternalReference" name="txtexternalReference" ng-model="milestone.externalReference" />
					<!-- <input class="form-control" type="text" id="txtexternalReference" name="txtexternalReference" ng-model="milestone.externalReference" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtexternalReference.$invalid">
						<validation-tooltip target="txtexternalReference">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- cancelled -->
			<div class="form-group">
				<label class="control-label" for="txtcancelled"><spring:message code="milestone.cancelled"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtcancelled.$touched || frmcancelled.$dirty) && frmMilestones.txtcancelled.$invalid }">
					<input class="form-control" type="text" id="txtcancelled" name="txtcancelled" ng-model="milestone.cancelled" />
					<!-- <input class="form-control" type="text" id="txtcancelled" name="txtcancelled" ng-model="milestone.cancelled" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtcancelled.$invalid">
						<validation-tooltip target="txtcancelled">
			                <ul class="list-unstyled">
			                    <li validation-message ng-if="$field.$error.required">This is a required field. </li>
			                    <li validation-message ng-if="$field.$error.minlength">Minimum length required is 3. </li>
			                </ul>
				        </validation-tooltip>
					</div> -->
				</div>
			</div>
			<!-- clientId -->
			<div class="form-group">
				<label class="control-label" for="txtclientId"><spring:message code="milestone.clientId"></spring:message></label>
				<div class="input-group" ng-class="{ 'has-error': (frmMilestones.txtclientId.$touched || frmclientId.$dirty) && frmMilestones.txtclientId.$invalid }">
					<input class="form-control" type="text" id="txtclientId" name="txtclientId" ng-model="milestone.clientId" />
					<!-- <input class="form-control" type="text" id="txtclientId" name="txtclientId" ng-model="milestone.clientId" required ng-minlength="3"/>
					<div class="input-group-addon" ng-show="frmMilestones.txtclientId.$invalid">
						<validation-tooltip target="txtclientId">
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
