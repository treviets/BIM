<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style type="text/css">
.person-info-row {
	line-height: 25px;
}
</style>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="HRDetailController" >
	<div style="margin-top: 10px;"></div>
	<div class="panel panel-default detail-sub-area full-width"
		style="padding: 10px">
		<div class="panel-heading clearfix">
		<div class="pull-left">
		<img src="${contextPath}/static/images/user.jpg"
						style="width: 100px; height: 100px; border-radius: 1000px; object-fit: cover; object-position: 0px 0px;margin-bottom: 10px">
		<h3 class="panel-title pull-left project-detail-title ng-binding">Nguyễn
				Văn A</h3>
		</div>
			
			<div class="pull-right">
				<span ng-click="deleteProject(2);"
					class="glyphicon glyphicon-trash ng-scope"
					title="click here to delete project information"
					ng-if="permission.projectRole.projectPermission.indexOf(4)>=0 &amp;&amp; (checkPermission('PM_4') || checkPermission('Design_4'))"
					role="button" tabindex="0" style=""></span> <span
					ng-click="fullscreen();"
					class="glyphicon glyphicon-fullscreen ng-scope"
					ng-if="!isFullscreen" title="fullscreen" role="button" tabindex="0"></span>
			</div>
		</div>
		<div class="tab-pane panel panel-success ng-scope active"
			style="margin-top: 20px">
			<div class="panel-heading clearfix ng-scope">
				<h3 class="panel-title pull-left">Personal Info</h3>
				<div class="pull-right">
					<span class="glyphicon glyphicon-edit" role="button" ng-click="addNew()"></span> <span
						class="glyphicon glyphicon-plus" role="button" ng-click="addNew()" tabindex="0"></span>
					<span class="glyphicon glyphicon-export" role="button" tabindex="0"></span>
				</div>
			</div>
			
			<div class="panel-body ">
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm">
						<div class="col-sm-12">Code: NV01</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Name: Nguyễn Văn A</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Sex: Nam</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Birth: 20/10/1990</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Born: HCM</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Address: tp HCM</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Nationality: Việt Nam</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Religion: Kinh</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">ID: 7998779980</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Date: 10/12/2012</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Place: HCM</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Mobile: 090808080</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Email: nguyenvana@gmail.com</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Resident Address: tp HCM</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Temporary Address: tp HCM</div>
					</div>
				</div>
				<div class="col-md-4 person-info-row">
					<div class="form-group form-group-sm ">
						<div class="col-sm-12">Marital Status: Chưa kết hôn</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default detail-sub-area full-width">
			<div class="panel-heading clearfix">
				<h3 class="panel-title pull-left">Staff Info</h3>
				<div class="pull-right">
					<span class="glyphicon glyphicon-fullscreen" title="fullscreen"></span>
				</div>
			</div>
			<!-- Content -->
			<div class="panel-body" >
				<ul class="nav nav-tabs">
					<li class="active"><a href="#relationship" data-toggle="tab">Relationship</a></li>
					<li><a href="#education" data-toggle="tab">Education</a></li>
					<li><a href="#workInfo" data-toggle="tab">Work Info</a></li>
					<li><a href="#workingProcess" data-toggle="tab">Working
							Process</a></li>
					<li><a href="#projectInfo" data-toggle="tab">Project Info</a></li>
					<li><a href="#laborContract" data-toggle="tab">Labor
							Contract</a></li>
					<li><a href="#salaryInfo" data-toggle="tab">Salary
							Info</a></li>
					<li><a href="#salaryProcess" data-toggle="tab">Salary
							Process</a></li>
					<li><a href="#accountInfo" data-toggle="tab">Account Info</a></li>
					<li><a href="#document" data-toggle="tab">Document</a></li>
				</ul>
				<div class="tab-content" style="overflow: auto">
					<br />
					<div id="relationship" class="tab-pane panel-success active">
						<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Relationship list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="saveRelationship()" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Relationship</th>
									<th class="text-align-center">Full Name</th>
									<th class="text-align-center">Date of birth</th>
									<th class="text-align-center">Major</th>
									<th class="text-align-center">Work place</th>
									<th class="text-align-center">Contact</th>
									<th></th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>Cha</td>
									<td>Nguyễn Văn B</td>
									<td>10/12/1970</td>
									<td>Worker</td>
									<td>HCM</td>
									<td>080880800898</td>
									<td><span class="glyphicon glyphicon-edit" ng-click="saveRelationship()" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
								<tr>
									<td>2</td>
									<td>Mẹ</td>
									<td>Nguyễn Văn c</td>
									<td>10/12/1970</td>
									<td>Worker</td>
									<td>HCM</td>
									<td>080880800898</td>
									<td><span class="glyphicon glyphicon-edit" ng-click="saveRelationship" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="education" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Education list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="saveEducation();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Education" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Level</th>
									<th class="text-align-center">Major</th>
									<th class="text-align-center">School</th>
									<th class="text-align-center">Education type</th>
									<th class="text-align-center">Certificate</th>
									<th class="text-align-center">Cource</th>
									<th class="text-align-center">Organization</th>
									<th class="text-align-center">Total time</th>
									<th class="text-align-center">Certificate</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>Anh văn</td>
									<td>Anh văn chuyên ngành</td>
									<td>Văn Lang</td>
									<td>Chính quy</td>
									<td>A</td>
									<td>Giao tiếp</td>
									<td></td>
									<td>12 tháng</td>
									<td></td>
									<td><span class="glyphicon glyphicon-edit" ng-click="saveEducation();" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
								<tr>
									<td>2</td>
									<td>Anh văn</td>
									<td>Anh văn chuyên ngành</td>
									<td>Văn Lang</td>
									<td>Chính quy</td>
									<td>A</td>
									<td>Giao tiếp</td>
									<td></td>
									<td>12 tháng</td>
									<td></td>
									<td><span class="glyphicon glyphicon-edit" ng-click="saveEducation();" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="workInfo" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Work info list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="saveWorkInfo()" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Position</th>
									<th class="text-align-center">Current position</th>
									<th class="text-align-center">Department</th>
									<th class="text-align-center">Current project</th>
									<th class="text-align-center">Date join Company</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>Trưởng phòng</td>
									<td>Trưởng phòng</td>
									<td>Kinh doanh</td>
									<td>Marketing</td>
									<td>12/10/2002</td>
									<td><span class="glyphicon glyphicon-edit" style="margin-right:10px"  ng-click="saveWorkInfo()"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
								<tr>
									<td>2</td>
									<td>Trưởng phòng</td>
									<td>Trưởng phòng</td>
									<td>Kinh doanh</td>
									<td>Marketing</td>
									<td>12/10/2002</td>
									<td><span class="glyphicon glyphicon-edit" style="margin-right:10px"  ng-click="saveWorkInfo()"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="workingProcess" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Work process list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="workingProcess();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Date</th>
									<th class="text-align-center">Total date</th>
									<th class="text-align-center">Position</th>
									<th class="text-align-center">Organization</th>
									<th class="text-align-center">Contact</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>10/9/2002</td>
									<td>15 month</td>
									<td>Trưởng phòng</td>
									<td></td>
									<td></td>
									<td><span class="glyphicon glyphicon-edit" ng-click="workingProcess();" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>

					<div id="projectInfo" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Project info list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="projectInfo();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Date</th>
									<th class="text-align-center">Position</th>
									<th class="text-align-center">Project</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>12/12/2006</td>
									<td>Giám đốc</td>
									<td>Xây dựng</td>
									<td><span class="glyphicon glyphicon-edit" ng-click="projectInfo();" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="laborContract" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Labor contract list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="laborContract();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Date</th>
									<th class="text-align-center">Total date</th>
									<th class="text-align-center">Position</th>
									<th class="text-align-center">Organization</th>
									<th class="text-align-center">Contact</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>12/12/2017</td>
									<td>12 month</td>
									<td>Giám đốc</td>
									<td></td>
									<td></td>
									<td><span  ng-click="laborContract();" class="glyphicon glyphicon-edit" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="salaryInfo" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Salary info list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="salaryInfo();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Total Salary</th>
									<th class="text-align-center">Salary insurance salary</th>
									<th class="text-align-center">Salary performance</th>
									<th class="text-align-center">Salary KPI</th>
									<th class="text-align-center">Allowance</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td><span class="glyphicon glyphicon-edit" ng-click="salaryInfo();" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="salaryProcess" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Salary process list
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="salaryProcess();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Date</th>
									<th class="text-align-center">Old salary </th>
									<th class="text-align-center">New salary</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td></td>
									<td></td>
									<td></td>
									<td><span ng-click="salaryProcess();" class="glyphicon glyphicon-edit" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="accountInfo" class="tab-pane panel-success">
					<div class=" row panel-heading">
					        <div class="col-sm-6 text-left">
					            Account info List
					        </div>
					        <div class="col-sm-6 text-right">
					        	<span  ng-click="accountInfo();" class="glyphicon glyphicon-plus ng-scope" title="click here to add new Relationship" role="button" tabindex="0"></span>
					 			<span  class="glyphicon glyphicon-export" title="click here to export these Relationship" role="button" tabindex="0"></span>&nbsp;&nbsp;&nbsp;
					        </div>
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">Account number</th>
									<th class="text-align-center">Bank</th>
									<th class="text-align-center">Branch</th>
									<th class="text-align-center">Note</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>6456464646464</td>
									<td>Việt Á</td>
									<td>tp HCM</td>
									<td></td>
									<td><span class="glyphicon glyphicon-edit" ng-click="accountInfo();" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="document" class="tab-pane panel-success">
					<div class=" row panel-heading">
				        <div class="col-sm-12 col-md-4 text-left" >
				            <input type="file" class="form-control" style="margin-top:5px;"/>
				        </div>
				        <div class="col-sm-12 col-md-4 text-left" >
				            <input type="text" class="form-control" placeholder="File name" style="margin-top:5px;"/>
				        </div>
				        <div class="col-sm-12 col-md-4 text-left" >
				            <input type="button" value="Save" class="btn btn-info" style="margin-top:5px;"/>
				        </div>					        
					    </div>
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th class="text-align-center">No.</th>
									<th class="text-align-center">File name</th>
									<th class="text-align-center">Link</th>									
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>Chứng chỉ tiếng anh</td>
									<td>abc.pdf</td>
									<td><span class="glyphicon glyphicon-edit" style="margin-right:10px"></span>
									<span class="glyphicon glyphicon-trash" style="color:red" onclick="return confirm('Are you sure delete?')"></span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div></div>