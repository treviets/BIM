<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" />

<script src="${contextPath}/static/js/controllers/ClientsController.js"></script>
<script src="${contextPath}/static/js/services/ClientsService.js"></script>

<div ng-controller="clientController" ng-init="initEditClient(${id})" >
<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">
					<spring:message code="client.title" />
				</h3>
				<div class="pull-right">
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
			</div>
			
		<div class="panel-body">
			<form class="form-horizontal">
			 	<div class="col-md-6 col-sm-6 col-xs-12">
			 		<div class="form-group form-group-sm {{clientCodeError}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message code="client.code"></spring:message>
						<span class="text-color-red">(*)</span>		
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text"  ng-model="client.clientCode" required ng-readonly="editMode">
						</div>
					</div>
					<div class="form-group form-group-sm {{clientNameError}}">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message code="client.name"></spring:message>
						<span class="text-color-red">(*)</span>		
						</label>
						<div class="col-sm-10">
							<input class="form-control" type="text"  ng-model="client.name"  required >
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message code="client.street"></spring:message>
						</label>
						<div class="col-sm-10">
							<input class="form-control" id="txtstreet" name="txtstreet"  ng-model="client.street">
						</div>
					</div>	
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message code="client.city"></spring:message></label>
						<div class="col-sm-10">
								<input class="form-control"  id="txtcity" name="txtcity" ng-model="client.city">
						</div>
					</div>
					<div class="form-group form-group-sm">
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message code="client.country"></spring:message></label>
						<div class="col-sm-10">
								<input class="form-control" id="txtcountry" name="txtcountry" ng-model="client.country">
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-12">
					<div class="form-group form-group-sm" >
						<label class="col-sm-2 control-label" for="formGroupInputSmall"><spring:message code="client.description"></spring:message>
						</label>
						<div class="col-sm-10" >
							<textarea rows="11" id="txtdescription" name="txtdescription"  class="form-control" type="text" ng-model="client.description"></textarea>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12 col-lg-12 col-xs-12">
						<div class="form-group form-group-sm">
							<div class="col-sm-12 text-center">
								<button type="button" class="btn btn-default" ng-click="backListClient();">Back</button>
								<button type="submit" class="btn btn-info" ng-click="saveClient(${id})">
									<span class="glyphicon glyphicon-floppy-save"></span>Save</button>
							</div>
						</div>
		
					</div>	
				
			</form>
		</div>
	</div>
	</div>
</div>
