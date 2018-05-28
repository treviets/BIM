<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div ng-controller="resourceController" ng-init="initEditResource(${id})" >	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					Human resource
				</h3>
			</div>
			<div class="panel-body">
			<div hidden="hidden">
				<select class="form-control" ng-model="resource.name" 
					ng-options="resource.id as resource.name for resource in resources" ng-init ="getResources()" >
				</select>
				<select class="form-control" ng-model="item.name" 
					ng-options="item.id as item.name for item in resourcesexists" ng-init ="getResourcesExist()" >
				</select>
			</div>
							
							<div ng-init="countResource()"></div>
							<div ng-init="getClientId()"></div>
				<form class="form-horizontal" id="aForm">
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="form-group form-group-sm {{codeError}}" >
							<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.code" />
								<span class="text-color-red">(*)</span>
							</label>
							<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="resource.code"  required="required">
							</div>
					</div>
					<div class="form-group form-group-sm {{resourceNameError}}" >
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
								code="resource.action.name" />
							<span class="text-color-red">(*)</span>
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="resource.name" required="required">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
								code="resource.action.fullName" /></label>
						<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="resource.fullName"/>
						</div>
					</div>
					<div class="form-group form-group-sm {{titleError}}" >
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
								code="resource.action.title" />
							<span class="text-color-red">(*)</span>
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="resource.title"  required="required">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.email" /></label>
						<div class="col-sm-10">
								<input class="form-control" id="email" type="email" ng-model="resource.email" />
								<span id="result"></span>
						</div>
					</div>
					<div class="form-group form-group-sm {{mobileError}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.mobile" />
							<span class="text-color-red">(*)</span>
						</label>
						<div class="col-sm-10">
								<input class="form-control" type="text" id = "mobile"  ng-model="resource.mobile"  required="required"/> 
								<span id="resultNumberMobile"></span>
						</div>
					</div>
					<div class="form-group form-group-sm">
							<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.phone" /></label>
							<div class="col-sm-10">
									<input class="form-control" type="text" id = "phone" ng-model="resource.phone"/>
									<span id="resultNumberPhone"></span>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="form-group form-group-sm {{IdCardError}}" >
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
								code="resource.action.idCard" />
							<span class="text-color-red">(*)</span>
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="resource.idCard"  required="required">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.fax" /></label>
						<div class="col-sm-10">
								<input class="form-control" type="number" ng-model="resource.fax"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.address" /></label>
						<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="resource.address"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.street" /></label>
						<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="resource.street"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.city" /></label>
						<div class="col-sm-10">
								<input class="form-control" type="text" ng-model="resource.city"/>
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="resource.action.salary" /></label>
						<div class="col-sm-10">
								<input class="form-control" type="number" ng-model="resource.salary" />
								<span id="result"></span>
						</div>
					</div>
					<div class="form-group form-group-sm">
								<input type="checkbox"/>Auto create account
						</div>
					</div>
					<div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
						<div class="form-group form-group-sm">
							<div class="col-sm-12 text-center">
								<button type="button" class="btn btn-default" ng-click="backListResource()">Back</button>
								<button ng-if="checkPermission('HR_3') || checkPermission('HR_2')" type="button" class="btn btn-info" ng-click="saveResource(${id})">
									<span class="glyphicon glyphicon-floppy-save"></span> Save</button>
							</div>
						</div>
		
					</div>					
				</form>
			</div>
		</div>
	</div>
</div>
