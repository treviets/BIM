<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div>
	<div class="col-lg-12 padding-top">
        <!-- project form -->
			<div class="panel panel-default">
				<div class="panel-heading clearfix">
	  				<div class="panel-title pull-left"><span class="glyphicon glyphicon-dashboard"></span> <spring:message code="ifc.4d.title"/></div>
					<div class="pull-right">
	      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="<spring:message code="common.form.screen.exit"/>"></span>
	      				<span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="<spring:message code="common.form.screen.full"/>"></span>
	      			</div>
				</div>
				<IFRAME frameborder="0"
					style="width: 100%;height: 500px;z-index: 9999 !important;"
					id="bimserver" name="bimserver" scrolling="no"
					src="http://localhost:8080/bimui">
				</IFRAME>
				<div id="4dcharttitle" class="panel-heading clearfix">
						<h3 class="panel-title pull-left project-detail-title"><span class="glyphicon glyphicon-dashboard"></span> <spring:message code="report.4d.title"/></h3>
				</div>
				<div class="panel-body">
				<div id="4dcharts" ng-controller="pmRe4DCtrl">	
					<div class="col-sm-6">
						<canvas id="taskcostpie" class="chart chart-pie"
							chart-data="dataTaskCostPie" chart-labels="labelTaskCostPie" chart-options="optionTaskCostPie" chart-colors="colorTaskCostPie">
						</canvas> 
					</div>
					<div class="col-sm-6">
						<canvas id="line" class="chart chart-line"
							chart-data="dataCostExpMultiLine" chart-labels="labelCostExpMultiLine" chart-series="seriesCostExpMultiLine" chart-options="optiopCostExpMultiLine" chart-legend="true" chart-colors="colorCostExpMultiLine">
						</canvas>
					</div>
					<div class="col-sm-6">
						<canvas id="varline" class="chart chart-line"
							chart-data="dataVarianceMultiLine" chart-labels="labelVarianceLine" chart-series="seriesVarianceMultiLine" chart-options="optiopVarianceMultiLine" chart-legend="true" chart-colors="colorVarianceMultiLine">
						</canvas> 
					</div>
					<div class="col-sm-6">
						<canvas id="indexline" class="chart chart-line"
							chart-data="dataIndexMultiLine" chart-labels="labelIndexLine" chart-series="seriesIndexMultiLine" chart-options="optiopIndexMultiLine" chart-legend="true" chart-colors="colorIndexMultiLine">
						</canvas> 
					</div>
				</div>
				</div>
			</div>	
    </div>
</div>
	

<script type="text/javascript">
	//Get projectIds.
	var projectIds = [];
	$(document).ready(function() {
		
		var iframe = document.getElementById('bimserver').contentWindow;
		
		
		
		// Get language from url.
		var taskLanguage = 'en';
		var index = window.location.href.lastIndexOf('lang=');
		if(index > -1) {
			taskLanguage = window.location.href.substring(index + 5, index + 7);
		}
		
		window.addEventListener('message', function(event) {
			if(event.data) {
				switch(event.data.method) {
					case 'getLanguage': {
						let data = { method: 'setLanguage', language: taskLanguage };
						iframe.postMessage(data, '*');
						break;
					}
					case 'showChartfor4D': {
						showChartfor4D(event.data.vis, event.data.projectId);
						break;
					}
					case 'getProjectIds': {
						let data = { method: 'setProjectIds', projectIds: projectIds };
						iframe.postMessage(data, '*');
						break;
					}
				}
			}

		});
		
		// Show chart.
		showChartfor4D = function(vis, projectId){
			if(vis) {
				$('#4dcharts').css('display', 'block');
				$('#4dcharttitle').css('display', 'block');
			} else {
				$('#4dcharts').css('display', 'none');
				$('#4dcharttitle').css('display', 'block');
			}
			pmRe4DCtrlScope.initParam(projectId);
		}
	});

</script>

<div ng-controller="commonCtrl" ng-init="getProjectIncharging();"></div>