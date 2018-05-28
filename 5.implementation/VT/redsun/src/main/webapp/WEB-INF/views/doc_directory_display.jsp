<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="docDirectoryCtrl" class="ng-scope" ng-init="initEditDocDirectory()">
	<div class="col-lg-12 padding-top">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
  				<div class="panel-title pull-left"><span class="glyphicon glyphicon-folder-open"></span> <spring:message code="document.title"/></div>
				<div class="pull-right">					
					<span ng-click="add();" class="glyphicon glyphicon-plus" title="<spring:message code="doc.folder.form.button.add.tooltip"/>" ng-if="permission.projectRole.folder.indexOf(2)>=0"></span>
					<span ng-click="edit();" class="glyphicon glyphicon-pencil" title="<spring:message code="doc.folder.form.button.edit.tooltip"/>" ng-if="permission.projectRole.folder.indexOf(3)>=0"></span>
					<span ng-click="showAssignDialog();" class="glyphicon glyphicon-user" title="<spring:message code="doc.folder.form.button.user.tooltip"/>" ng-if="permission.projectRole.assignFolder.indexOf(1)>=0"></span>
					<span ng-click="moveTrash();" class="glyphicon glyphicon-trash" title="<spring:message code="doc.folder.form.button.moveto.trash.tooltip"/>" ng-if="permission.projectRole.folder.indexOf(4)>=0"></span>
					<span ng-click="btnDelete();" class="glyphicon glyphicon-remove" title="<spring:message code="doc.folder.form.button.delete.tooltip"/>" ng-if="permission.projectRole.folder.indexOf(4)>=0"></span>
					<span ng-click="showTrashDialog($event);" class="glyphicon glyphicon-share-alt" title="<spring:message code="doc.folder.form.button.undo.trash.tooltip"/>" ng-if="permission.projectRole.folder.indexOf(4)>=0"></span>
					<span ng-click="showConfigMailDoc($event);" class="glyphicon glyphicon-envelope" title="<spring:message code="doc.folder.form.button.mail.tooltip"/>" ng-if="permission.projectRole.configureMail.indexOf(1)>=0"></span>
					<span ng-click="showProjectRole();" class="glyphicon glyphicon-wrench" title="<spring:message code="doc.folder.form.button.permission.tooltip"/>" ng-if="permission.projectRole.role.indexOf(1)>=0"></span>
					<span ng-click="showFolderDafaultDialog();" class="glyphicon glyphicon-cog" title="<spring:message code="doc.folder.form.button.folder.default.tooltip"/>" ng-if="permission.projectRole.role.indexOf(1)>=0"></span>
      				<span ng-click="exitFullscreen();" class="glyphicon glyphicon-resize-small" ng-if="isFullscreen" title="<spring:message code="common.form.screen.exit"/>"></span>
      				<span ng-click="fullscreen();" class="glyphicon glyphicon-fullscreen" ng-if="!isFullscreen" title="<spring:message code="common.form.screen.full"/>"></span>
      			</div>
			</div>
			<div class="panel-body">
				<!-- Alert Message -->
				<div id="alertMessage" class="alert" style="display: none">
					<strong>{{alertMessage}}</strong>
				</div>
				<!-- Add files -->
  				<div class="well" ng-if="permission.projectRole.uploadDoc.indexOf(1)>=0">
  					<ul class="list-inline">
  						<li><label class="control-label" for="attachments"><spring:message code="doc.file.form.label"/></label></li>
  						<li><input type="file" class="form-control btn btn-default" ng-click="fileClear($event)" id="documents" name="documents" multiple ng-files="addDocuments($files)" /></li>
  						<li><button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="<spring:message code="doc.file.form.button.save.tooltip"/>" ng-click="saveDocuments()">
  								<span class="glyphicon glyphicon-import"></span>
  							</button>
  						</li>
  					</ul>
  					<div class="checkbox">
  						<label><input type="checkbox" ng-model="chkDoc" ng-change="initParam(chkDoc);"><spring:message code="doc.file.form.chk.mail"/></label>
					</div>
  				</div>
				
				<div class="row vdivide">
					<div class="col-md-4 col-sm-4 col-xs-12">
						<div
		      				data-angular-treeview="true"
		      				data-tree-id="tree01"
		      				data-tree-model="roleList"
		      				data-node-id="id"
		      				data-node-label="name"
		      				data-node-children="subFolders"
		      				ng-click="getDocument($event);">
		    			</div>
	    			</div>
	    			<div class="col-md-8 col-sm-8 col-xs-12">
	    				<input type="text" class ="form-control" placeholder="Search" ng-model="searchName">
	    				<div class="mygrid-wrapper-div">
							<div class="table-responsive">
								<table class="table table-bordered table-hover">
									<thead>
										<tr>
											<th ng-click="sortBy('name')"><spring:message code="document.file.name"/>
												<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
											</th>
											<th ng-click="sortBy('createDateTime')"><spring:message code="document.file.date"/>
												<span ng-show="sortKey === 'createDateTime'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
											</th>
											<th ng-click="sortBy('createDateTime')"><spring:message code="document.file.size"/>
												<span ng-show="sortKey === 'createDateTime'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/></span>
											</th>
											<th>
	          									<span class="glyphicon glyphicon-download"></span>
	        								</th>
	        								<th>
	        									<span class="glyphicon glyphicon-trash"></span>
	        								</th>
	        								
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="doc in docs | orderBy:sortKey:reverse | filter:searchName"">
											<td>{{doc.name}}</td>
											<td>{{doc.createDateTime}}</td>
											<td></td>
											<td>
											<!-- 
												<button type="button" class="btn btn-default btn-sm" ng-click="downloadDocuments(doc);" ng-if="permission.projectRole.downloadFile.indexOf(1)>=0">
	          										<span class="glyphicon glyphicon-download"></span>
	        									</button>
	        								-->
	        								<a class="btn btn-default btn-sm" ng-click="downloadDocuments(doc)" href="">
                        						<span class="glyphicon glyphicon-download"></span>
                    						</a>
	        								</td>
	        								<td>
												<button type="button" class="btn btn-default btn-sm" ng-click="showConfirm($event, doc)" ng-if="permission.projectRole.deleteFile.indexOf(1)>=0">
	          										<span class="glyphicon glyphicon-trash"></span> 
	        									</button>
											</td>
										</tr>
									</tbody>
								</table>
			    			</div>
		    			</div>
	    			</div>
    			</div>
			</div>
		</div>
	</div>
</div>