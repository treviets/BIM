<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />

<script src="${contextPath}/static/js/utils/upload-file/upload-files-directive.js"></script>
<script src="${contextPath}/static/js/utils/bpmn/modeler.js"></script>
<script src="${contextPath}/static/js/utils/bpmn/bower_components/bpmn-js/dist/bpmn-modeler.js"></script>
<script src="${contextPath}/static/lib/bootstrap/js/jquery.min.js"></script>
<script src="${contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
html, body, #canvas {
	height: 400px;
	width: 100%;
	padding: 0;
	border: 2px solid white;
}

</style>

<link rel="stylesheet" href="${contextPath}/static/js/utils/bpmn/bower_components/bpmn-js/dist/assets/diagram-js.css">
<link rel="stylesheet" href="${contextPath}/static/js/utils/bpmn/bower_components/bpmn-js/dist/assets/bpmn-font/css/bpmn-embedded.css">

<div ng-controller="TabController" ng-init="initProjectId();">
	<div class="panel panel-default">
		<div class="panel-heading clearfix">
			<div class="pull-right">
				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span> 
				<span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen">
				</span>
			</div>
		</div>
		

		
		
		<div class="Body-process" id="mainContent" ng-init="getAllPhase();">
			<div id="canvas">
			
			</div>
			<div class="save-diagram" id="save-button">
				<button  ng-if="checkPermission('BPMN_2') || checkPermission('BPMN_3')" class="btn btn-info btn-save-diagram">
					<span class="glyphicon glyphicon-floppy-save"></span> Save</button>
			</div>
			
			<script	src="${contextPath}/static/js/utils/bpmn/bower_components/bpmn-js/dist/bpmn-modeler.js"></script>
			<script src="${contextPath}/static/js/utils/bpmn/modeler.js"></script>
			
			
			

			
		</div>
	
	</div>
</div>