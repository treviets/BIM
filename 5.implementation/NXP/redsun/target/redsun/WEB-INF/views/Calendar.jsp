
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="<%=getServletContext().getContextPath()%>" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/uicalendar/fullcalendar.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/fullcalendar.js"></script>

<section id="directives-calendar" ng-controller="CalendarCtrl">
	<div class="well">
		<div class="row-fluid">
			<div class="span8">
				<uib-tabset>
					<uib-tab select="renderCalender('mainCalendar');">
						<!-- <div class="alert alert-success" ng-show="alertMessage != undefined && alertMessage != ''">{{alertMessage}}</div> -->
						<div class="btn-toolbar">
							<div class="btn-group">
								<button class="btn btn-success" ng-click="changeView('agendaDay', 'mainCalendar')">Day</button>
								<button class="btn btn-success" ng-click="changeView('agendaWeek', 'mainCalendar')">Week</button>
								<button class="btn btn-success" ng-click="changeView('month', 'mainCalendar')">Month</button>
							</div>
						</div>
						<div class="calendar" ng-model="eventSources" calendar="mainCalendar" ui-calendar="uiConfig.calendar"></div>
					</uib-tab>
				</uib-tabset>
			</div>
		</div>
	</div>
</section>

