<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="contextPath"
	value="<%=getServletContext().getContextPath()%>" />

<div ng-controller="systemCtrl" class="ng-scope">
	<div class="col-lg-12 padding-top">
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<div class="panel-title pull-left">
					<spring:message code="system.title" />
				</div>
			</div>
			<div class="panel-body">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#users" data-toggle="tab"><spring:message
								code="system.menu.users" /></a></li>
					<li><a href="#roles" data-toggle="tab"><spring:message
								code="system.menu.roles" /></a></li>
					<li><a href="#menus" data-toggle="tab"><spring:message
								code="system.menu.menus" /></a></li>
				</ul>
				<br />
				<div class="tab-content">
					<div id="users" class="tab-pane active">
						<div class="row">
							<div class="row {{usernameError}}">
								<label class="col-lg-2 control-label" for="username">Name</label>
								<div class="col-lg-10">
									<select name="username" ng-model="user.username" class="form-control">
										<c:forEach items="${resources.resources}" var="resource"
											varStatus="status">
											<option value="${resource.code }"><c:out
													value="${resource.code } - ${resource.name}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<br/>
							<div class="row {{usernameError}}">
								<label class="col-lg-2 control-label" for="username"><spring:message
										code="system.users.username" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="input username" id="username"
										ng-model="user.username" required ng-readonly="true">
								</div>
							</div>
							<br/>
							<div class="row {{passwordError}}">
								<label class="col-lg-2 control-label" for="password"><spring:message
										code="system.users.password" /></label>
								<div class="col-lg-10">
									<input type="password" class="form-control"
										placeholder="input password" id="password"
										ng-model="user.password" required>
								</div>
							</div>
							<br/>
							<div class="row {{confirmPasswordError}}">
								<label class="col-lg-2 control-label" for="confirmPassword"><spring:message
										code="system.users.confirm.password" /></label>
								<div class="col-lg-10">
									<input type="password" class="form-control"
										placeholder="input password" id="confirmPassword"
										ng-model="user.confirmPassword" required>
								</div>
							</div>
							<br/>
							<div class="row {{rolesError}}">
								 <label class="col-lg-2 control-label" for="roleSelect"><spring:message
										code="system.users.roles" /></label>
								<div class="col-lg-10">
									<select name="roles" ng-model="user.role" class="form-control"
										id="roleSelect">
										<c:forEach items="${roleList}" var="role" varStatus="status">
											<option value="${role.id }">${role.name}-
												${role.description }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<br/>
							<div class="col-lg-offset-2 col-lg-10 text-center">
								<button type="button" ng-if="checkPermission('System_3') || checkPermission('System_2')" class="btn btn-info" ng-click="addUser()">
									<span class="glyphicon glyphicon-floppy-save"></span>
									<spring:message code="system.users.button.save" />
								</button>
							</div>
							</div>
						<hr />
						<div class="pull-left">
							<dir-pagination-controls max-size="8" direction-links="true"
								boundary-links="true"
								on-page-change="getProjects(newPageNumber)">
							</dir-pagination-controls>
						</div>
						<div class="table-responsive">
							<table class="table table-bordered table-striped table-sm">
								<tr>
									<th><spring:message code="system.users.no" /></th>
									<th><spring:message code="system.users.username" /></th>
									<th><spring:message code="system.users.fullname" /></th>
									<th><spring:message code="system.users.roles" /></th>
									<th><spring:message code="system.users.create.by" /></th>
									<th><spring:message code="system.users.create.date" /></th>
									<th><spring:message code="system.users.status" /></th>
									<th><spring:message code="system.users.action"
											text="Action" /></th>
								</tr>
								<c:forEach items="${userList}" var="user" varStatus="status">
									<tr>
										<th>${status.index+1}</th>
										<th><c:out value="${user.username}" /></th>
										<th><c:out value="${user.fullname}" /></th>
										<th><c:out value="${user.role}" /></th>
										<th><c:out value="${user.createdBy}" /></th>
										<th><c:out value="${user.createdDate}" /></th>
										<th><c:out value="${user.status}" /></th>
										<th><a ng-if="checkPermission('System_3')"
											ng-click="editUser('<c:out value="${user.username}"/>');"><span
												class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;<a ng-if="checkPermission('System_4')"
											href="/redsun/system/delete/user/<c:out value="${user.username}"/>"
											target="_self"><span class="glyphicon glyphicon-trash"></span></a></th>
									</tr>
								</c:forEach>
							</table>
						</div>
						
						<div class="pull-left">
							<dir-pagination-controls max-size="8" direction-links="true"
								boundary-links="true"
								on-page-change="getProjects(newPageNumber)">
							</dir-pagination-controls>
						</div>
					</div>
					<div id="roles" class="tab-pane">
					<br/>
						<fieldset class="form-group">
							<div class="row {{roleNameError}}">
								<label class="col-lg-2 control-label" for="username"><spring:message
										code="system.role.name" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="ex: management role" id="roleName"
										ng-model="newRole.name" required>
								</div>
							</div>
							<br/>
							<div class="row">
								<label class="col-lg-2 control-label" for="password"><spring:message
										code="system.role.description" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="description for role" id="password"
										ng-model="newRole.description">
								</div>
							</div>
							<br/>
							<div class="row">
								<label class="col-lg-2 control-label" for="roleSelect"><spring:message
										code="system.role.permission" /></label>
								<div class="col-lg-10 table-responsive">
									<table class="table table-bordered table-striped table-sm">
										<tr>
											<th><spring:message code="system.role.menu" /></th>
											<th><spring:message code="system.role.permission" /></th>
										</tr>
										<c:forEach items="${menuList}" var="menu" varStatus="status">
											<tr>
												<th><c:out value="${menu.description }" /> <input
													ng-show="false"
													ng-model="newRole.tempMenus[${status.index}].menuId"
													ng-init="newRole.tempMenus[${status.index}].menuId=${menu.id}" /></th>
												<th><input type="checkbox" name="read"
													ng-model="newRole.tempMenus[${status.index}].read"
													ng-true-value="1" />Read <input type="checkbox"
													name="create" ng-true-value="2"
													ng-model="newRole.tempMenus[${status.index}].create" />Create
													<input type="checkbox" name="update" ng-true-value="3"
													ng-model="newRole.tempMenus[${status.index}].update" />Update
													<input type="checkbox" name="delete" ng-true-value="4"
													ng-model="newRole.tempMenus[${status.index}].delete" />Delete</th>
											</tr>
										</c:forEach>
									</table>
								</div>
							</div>
							<div class="col-lg-offset-2 col-lg-10 text-center">
								<button ng-if="checkPermission('System_3') || checkPermission('System_2')" type="button" class="btn btn-info" ng-click="addRole()">
									<span class="glyphicon glyphicon-floppy-save"></span>
									<spring:message code="system.users.button.save" />
								</button>
							</div>
						</fieldset>
						<div class="table-responsive">
							<hr />
							<table class="table table-bordered table-striped table-sm" id="dataTable">
								<tr>
									<th>No</th>
									<th><spring:message code="system.role.name" /></th>
									<th><spring:message code="system.role.description" /></th>
									<th><spring:message code="system.role.menu" /></th>
									<th><spring:message code="system.users.action"
											text="Action" /></th>
								</tr>
								<c:forEach items="${roleList}" var="role" varStatus="status">
									<tr>
										<th>${status.index+1}</th>
										<th><c:out value="${role.name }" /></th>
										<th><c:out value="${role.description }" /></th>
										<th><c:forEach items="${role.menus}" var="menu" varStatus="s">
												<c:if test="${not empty menu.permission}">
													<c:out value="${s.index+1}. ${menu.menuName}" />
													
													<!-- read -->
													<c:if test="${fn:contains(menu.permission, '1')}">
														<span class="alert-warning glyphicon glyphicon-eye-open" aria-hidden="true"></span>
													</c:if>
													<!-- create -->
													<c:if test="${fn:contains(menu.permission, '2')}">
														<span class="alert-success glyphicon glyphicon-plus" aria-hidden="true"></span>
													</c:if>
													<!-- update -->
													<c:if test="${fn:contains(menu.permission, '3')}">
														<span class="alert-info glyphicon glyphicon-pencil" aria-hidden="true"></span>
													</c:if>
													<!-- delete -->
													<c:if test="${fn:contains(menu.permission, '4')}">
														<span class="alert-danger glyphicon glyphicon-remove" aria-hidden="true"></span>
													</c:if>
													
													</br>
												</c:if>
												
											</c:forEach></th>
										<th><a ng-if="checkPermission('System_3')" ng-click="editRole('<c:out value="${role.id}"/>');"><span
												class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;<a ng-if="checkPermission('System_4')"
											href="/redsun/system/delete/role/<c:out value="${role.id}"/>"
											target="_self"><span class="glyphicon glyphicon-trash"></span></a></th>
									</tr>
								</c:forEach>
							</table>
						</div>
						
					</div>
					<div id="menus" class="tab-pane">
					<br/>
						<fieldset class="form-group">
							<div class="row {{menuNameError}}">
								<label class="col-lg-2 control-label" for="menuName"><spring:message
										code="system.menus.name" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="input menu name" id="name" ng-model="menu.name"
										required>
								</div>
							</div>
							<br/>
							<div class="row {{menuDescriptionError}}">
								<label class="col-lg-2 control-label" for="description"><spring:message
										code="system.menus.description" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="ex: Menu for get all projects" id="description"
										ng-model="menu.description" required>
								</div>
							</div>
							<br/>
							<div class="row {{menuUrlError}}">
								<label class="col-lg-2 control-label" for="content"><spring:message
										code="system.menus.url" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="link to navigator once user clicked" id="content"
										ng-model="menu.url" required>
								</div>
							</div>
							<br/>
							<div class="row {{menuIconClassError}}">
								<label class="col-lg-2 control-label" for="iconClass"><spring:message
										code="system.menus.icon.class" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="class to show icon before menu label"
										id="iconClass" ng-model="menu.iconClass" required>
								</div>
							</div>
							<br/>
							<div class="row {{menuPositionError}}">
								<label class="col-lg-2 control-label" for="position"><spring:message
										code="system.menus.position" /></label>
								<div class="col-lg-10">
									<input type="text" class="form-control"
										placeholder="position for menu on left panel" id="position"
										ng-model="menu.position" required>
								</div>
							</div>
							<br/>
							<div class="col-lg-offset-2 col-lg-10 text-center">
								<button ng-if="checkPermission('System_3') || checkPermission('System_2')" type="button" class="btn btn-info" ng-click="addMenu()">
									<span class="glyphicon glyphicon-floppy-save"></span>
									<spring:message code="system.users.button.save" />
								</button>
							</div>
						</fieldset>
						<div class="table-responsive">
							<hr />
							<table class="table table-bordered table-striped table-sm">
								<tr>
									<th>No</th>
									<th><spring:message code="system.menus.name" /></th>
									<th><spring:message code="system.menus.description" /></th>
									<th><spring:message code="system.menus.url" /></th>
									<th><spring:message code="system.menus.icon.class" /></th>
									<th><spring:message code="system.menus.position" /></th>
									<th><spring:message code="system.menus.status" /></th>
									<th><spring:message code="system.users.action"
											text="Action" /></th>
	
								</tr>
								<c:forEach items="${menuList}" var="menu" varStatus="status">
									<tr>
										<th>${status.index+1}</th>
										<th><c:out value="${menu.name }" /></th>
										<th><c:out value="${menu.description }" /></th>
										<th><c:out value="${menu.url }" /></th>
										<th><c:out value="${menu.iconClass }" /></th>
										<th><c:out value="${menu.position }" /></th>
										<th><c:out value="${menu.status }" /></th>
										<th><a ng-if="checkPermission('System_3')"ng-click="editMenu('<c:out value="${menu.id}"/>');"><span
												class="glyphicon glyphicon-pencil"></span></a>&nbsp;&nbsp;<a ng-if="checkPermission('System_4')"
											href="/redsun/system/delete/menu/<c:out value="${menu.id}"/>"
											target="_self"><span class="glyphicon glyphicon-trash"></span></a></th>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>