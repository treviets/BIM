<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div ng-controller="materialController" ng-init="initEditMaterial(${id})" >	
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<spring:message code="material.title" />
				</h3>
			</div>
		<div class="panel-body">
			<div hidden="hidden">
				<select class="form-control" ng-model="material.name" 
						ng-options="material.id as material.name for material in materials" ng-init ="getMaterials()" >
				</select>
			</div>
			<div hidden="hidden">
				<select class="form-control" ng-model="materiale.name" 
						ng-options="materiale.id as materiale.name for materiale in materialexist" ng-init ="checkExistMaterial()" >
				</select>
			</div>
			<div ng-init="countMaterial()"></div>
			<div ng-init="getClientId()"></div>
			<form class="form-horizontal">
			 	<div class="col-md-6 col-sm-6 col-xs-12">
			 		<div class="form-group form-group-sm {{materialErrorCode}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="material.action.code" />
							<span class="text-color-red">(*)</span>		
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="material.code">
						</div>
					</div>
					<div class="form-group form-group-sm {{materialErrorName}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="material.action.name" />
							<span class="text-color-red">(*)</span>		
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="material.name">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="material.action.unit" />
						</label>
						<div class="col-sm-10  {{materialErrorUnit}}">
							<select class="form-control" ng-model="material.unit"
								ng-options="unit.id as unit.name for unit in units" ng-init="getUnits()">
							</select>

						</div>
					</div>	
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="material.action.quantity" />
						</label>
						<div class="col-sm-10">
							<input class="form-control"  type="text"  ng-model="material.quantity">
						</div>
					</div>	
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="material.action.price" />
						</label>
						<div class="col-sm-10">
							<input class="form-control" min="1" type="number"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" ng-model="material.price">
						</div>
					</div>	
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="material.action.netPrice" />
						</label>
						<div class="col-sm-10">
							<input class="form-control" min="1" type="number"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" ng-model="material.netPrice">
						</div>
					</div>	
					<div class="form-group form-group-sm" >
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
								code="material.action.description" />
						</label>
						<div class="col-sm-10">
							<textarea rows="8" class="form-control" type="text" ng-model="material.description"></textarea>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
					<div class="form-group form-group-sm">
						<div class="col-sm-12 text-center">
							<button type="button" class="btn btn-default" ng-click="backListMaterial()">Back</button>
							<button ng-if="checkPermission('WH_3') || checkPermission('WH_2')" type="button" class="btn btn-info" ng-click="saveMaterial(${id})">Save</button>
						</div>
					</div>
		
				</div>
			</form>
		</div>
	</div>
	</div>
</div>	
