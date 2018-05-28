<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div>
	<div class="col-lg-12 padding-top">
        <!-- project form -->
  		<div class="panel panel-default">
  			<div class="panel-heading clearfix">
    			<h3 class="panel-title pull-left project-detail-title"><spring:message code="social.title"/></h3>
		    	<div class="pull-right">
		    		<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
  			</div>
  			<div class="panel-body">
				<div class="clearfix">
					<div id="social-area" ng-controller="discussingController" ng-init="initDiscussing('ALL');" ng-include="'/redsun/static/partials/social-area.html'" class="panel panel-default detail-sub-area full-width">
					</div>
				</div>
			</div>
        </div>
    </div>
</div>
        