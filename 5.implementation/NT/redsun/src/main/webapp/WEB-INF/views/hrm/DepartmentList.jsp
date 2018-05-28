<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />
<link rel="stylesheet"
	href="${contextPath}/static/js/lib/department-tree/Treant.css">
<link rel="stylesheet"
	href="${contextPath}/static/js/lib/department-tree/custom-colored.css">

<div ng-controller="DepartmentListController">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12 padding-top" style="background: none">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
  				<div class="panel-title pull-left"><span class="glyphicon glyphicon-stats"></span> <spring:message code="hrm.form.department.title"/></div>
				<div class="pull-right">					
					<span ng-click="exportOrgPDF('organization_chart', 'custom-colored');" class="glyphicon glyphicon-export" title="{{'report_pm_costs_menu_export_pdf' | translate}}"></span>
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="<spring:message code="common.form.screen.exit"/>"></span>
      				<span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="<spring:message code="common.form.screen.full"/>"></span>
      			</div>
			</div>
			<input type="button" value="Add" ng-model="btn-Add" id="btn-Add" ng-click="addNode()" style="display: none"/>
			<div class="chart" id="custom-colored">--@--</div>

		</div>
		<script
			src="${contextPath}/static/js/lib/department-tree/vendor/raphael.js"></script>
		<script src="${contextPath}/static/js/lib/department-tree/Treant.js"></script>

		
    </script>

	</div>
</div>
