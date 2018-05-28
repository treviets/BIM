<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="HRListController">
	<div style="margin-top: 10px;"></div>
	<div class="col-lg-12 padding-top" style="background: none">
		<div class="panel panel-default" style=" float:left;padding-bottom: 10px;background-color: white;width:100%;">
 		<div class="panel-heading clearfix">
  				<form class="navbar-form navbar-left">
	        		<div class="form-group">
	        			<input type="text" class="form-control" placeholder="Search" ng-model="searchName">
	        		</div> 
      			</form>
      			<div class="pull-right">
      				<span ng-click="addProject();" class="glyphicon glyphicon-plus" title="click here to add new project" ng-if="checkPermission('PM_2');"></span>&nbsp;&nbsp;&nbsp;
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="exit fullscreen"></span><span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="fullscreen"></span>
      			</div>
  			</div>
			<h4 style="margin-left: 10px"><span class="glyphicon glyphicon-user">
			</span> Human Resource List</h3>
  			<hr/>
  			
			<div ng-repeat="item in lstHR  | filter:{fullName:searchName}" class="col-sm-2" ng-click="goDetail()"
				style="height:300px;margin-left:10px;padding: 5px; border-radius: 5px; background-color: #d9edf7; margin-right: 10px; margin-top: 10px;cursor:pointer">
				<div style="text-align: center">
					<img src="${contextPath}{{item.img}}"
						style="width: 100px; height: 100px; border-radius: 1000px; object-fit: cover; object-position: 0px 0px;">
					<div class="user-infos">
						<div class="row align-items-center">
							<div class="col-md-12">
								<label>{{item.code}}</label>
							</div>
						</div>
						<div class="row align-items-center">
							<div class="col-md-12">
								<label>{{item.fullName}}</label>
							</div>
						</div>
						<div class="row align-items-center">
							<div class="col-md-12">
								<label>{{item.position.name}}</label>
							</div>
						</div>
						<div class="row align-items-center">
							<div class="col-md-12">
								<label> <i class="glyphicon glyphicon-phone"></i>
									{{item.phone}}
									<span ng-if="item.phone==null" style="color:red">not update</span>
								</label>
							</div>
						</div>
						<div class="row align-items-center">
							<div class="col-md-12">
								<label><i class="glyphicon glyphicon-tag"></i> 
								{{item.department.name}}
								<span ng-if="item.department.name==null" style="color:red">not update</span>
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>