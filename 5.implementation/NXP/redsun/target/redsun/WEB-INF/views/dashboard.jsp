<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="dashboardCtrl" class="ng-scope">
	<div class="col-lg-12 padding-top">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
  				<div class="panel-title pull-left""><spring:message code="dashboard.title" text="Dashboard"/></div>
				<div class="pull-right">
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
			</div>
			<div class="panel-body">
				<div class="col-lg-6 col-sm-12">
					<svg id="pieChart"></svg>
				</div>
				<div class="col-lg-6 col-sm-12 items-group">
					
					<div class="col-lg-3 col-sm-12 items" ng-repeat="object in chartData">
						<div class="text-center items-title">{{object.statusName}}</div>
						<div class="text-center items-values">{{object.transactionAmount | currency:object.transactionCurrencyCode==='EUR'?'€':'$'}}</div>
						<div class="text-right items-footer">{{object.vendorStatusCount}}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>