<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>" /> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/uicalendar/fullcalendar.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/lib/uicalendar/calendarDemo.css" />
<!-- jquery, moment, and angular have to get included before fullcalendar -->
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/bootstrap/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/angular.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/angular-route.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/ui-bootstrap-tpls-0.13.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/moment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/fullcalendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/gcal.js"></script>



<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/uicalendar/calendarDemo.js"></script>




<div ng-controller="CalendarCtrl">

<div ui-calendar="uiConfig.calendar" class="span8 calendar" ng-model="eventSources2"></div> 

</div>





