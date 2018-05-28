<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div ng-controller="equipmentController" ng-init="initEditEquipment(${id})" >	
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<spring:message code="equipment.title" />
				</h3>
			</div>
		<div class="panel-body">
			<div hidden="hidden">
				<select class="form-control" ng-model="equipment.name" 
						ng-options="equipment.id as equipment.name for equipment in equipments" ng-init ="getEquipments()" >
				</select>
			</div>
			<div hidden="hidden">
			 	<select class="form-control" ng-model="equipmente.name" 
						ng-options="equipmente.id as equipmente.name for equipmente in equipmentexist" ng-init ="checkExistEquipment()" >
				</select>
			</div>
			<div ng-init="countEquipment()"></div>
			<div ng-init="getClientId()"></div>
			<form class="form-horizontal">
			 	<div class="col-md-6 col-sm-6 col-xs-12">
			 		<div class="form-group form-group-sm {{equipmentErrorCode}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="equipment.action.code" />
							<span class="text-color-red">(*)</span>		
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="equipment.code">
						</div>
					</div>
					<div class="form-group form-group-sm {{equipmentErrorName}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="equipment.action.name" />
							<span class="text-color-red">(*)</span>		
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text" ng-model="equipment.name">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="equipment.action.unit" />
						</label>
						<div class="col-sm-10 {{equipmentErrorUnit}}">
							<select class="form-control" ng-model="equipment.unit"
								ng-options="unit.id as unit.name for unit in units" ng-init="getUnits()">
							</select>
						</div>
					</div>	
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="equipment.action.quantity" />
						</label>
						<div class="col-sm-10">
							<input class="form-control" min="1" type="number"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" ng-model="equipment.quantity">
						</div>
					</div>	
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="equipment.action.price" />
						</label>
						<div class="col-sm-10">
							<input class="form-control" min="1" type="number"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" ng-model="equipment.price">
						</div>
					</div>	
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
				<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
									code="equipment.action.netPrice" />
						</label>
						<div class="col-sm-10">
							<input class="form-control" min="1" type="number"  oninput="this.value = this.value.replace(/[^0-9.]/g, ''); this.value = this.value.replace(/(\..*)\./g, '$1');" ng-model="equipment.netPrice">
						</div>
					</div>	
					<div class="form-group form-group-sm" >
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message
								code="equipment.action.description" />
						</label>
						<div class="col-sm-10">
							<textarea rows="8" class="form-control" type="text" ng-model="equipment.description"></textarea>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
					<div class="form-group form-group-sm">
						<div class="col-sm-12 text-center">
							<button type="button" class="btn btn-default" ng-click="backListEquipment()">Back</button>
							<button type="button" class="btn btn-info" ng-click="saveEquipment(${id})">
								<span class="glyphicon glyphicon-floppy-save"></span> Save</button>
						</div>
					</div>
		
				</div>
			</form>
		</div>
	</div>
	</div>
</div>	
