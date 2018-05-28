
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= getServletContext().getContextPath() %>"/>

<script src="${contextPath}/static/js/controllers/RecipientsController.js"></script>
<script src="${contextPath}/static/js/services/RecipientsService.js"></script>

<div ng-controller="recipientController" ng-init="initList()">
	<!-- panel -->
	<div class="panel">
		<!-- filter	 -->
		<div class="row panel-heading">
			<!-- filter by name -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.name" class="inpput-sm" />
			</div>
			<!-- filter by companyNumber -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.companyNumber" class="inpput-sm" />
			</div>
			<!-- filter by numTax -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.numTax" class="inpput-sm" />
			</div>
			<!-- filter by bankName -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.bankName" class="inpput-sm" />
			</div>
			<!-- filter by ibanCountry -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.ibanCountry" class="inpput-sm" />
			</div>
			<!-- filter by ibanKey -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.ibanKey" class="inpput-sm" />
			</div>
			<!-- filter by ibanBban -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.ibanBban" class="inpput-sm" />
			</div>
			<!-- filter by designation -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.designation" class="inpput-sm" />
			</div>
			<!-- filter by street -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.street" class="inpput-sm" />
			</div>
			<!-- filter by complement -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.complement" class="inpput-sm" />
			</div>
			<!-- filter by zip -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.zip" class="inpput-sm" />
			</div>
			<!-- filter by city -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.city" class="inpput-sm" />
			</div>
			<!-- filter by state -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.state" class="inpput-sm" />
			</div>
			<!-- filter by country -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.country" class="inpput-sm" />
			</div>
			<!-- filter by taxFree -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.taxFree" class="inpput-sm" />
			</div>
			<!-- filter by idle -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.idle" class="inpput-sm" />
			</div>
			<!-- filter by legalNotice -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.legalNotice" class="inpput-sm" />
			</div>
			<!-- filter by contactName -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.contactName" class="inpput-sm" />
			</div>
			<!-- filter by contactEmail -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.contactEmail" class="inpput-sm" />
			</div>
			<!-- filter by contactPhone -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.contactPhone" class="inpput-sm" />
			</div>
			<!-- filter by contactMobile -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.contactMobile" class="inpput-sm" />
			</div>
			<!-- filter by bankNationalAccountNumber -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.bankNationalAccountNumber" class="inpput-sm" />
			</div>
			<!-- filter by bankInternationalAccountNumber -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.bankInternationalAccountNumber" class="inpput-sm" />
			</div>
			<!-- filter by bankIdentificationCode -->
			<div class="col-md-2">
				<input type="text" ng-model="recipient.bankIdentificationCode" class="inpput-sm" />
			</div>
			<!-- filter button -->
			<div class="col-md-2">
				<button type="button" class="btn" ng-click="getsForPageAndFilter(1)"><span class="glyphicon glyphicon-search"></span></button>
			</div>
		</div>
		<!-- top actions -->
		<div class="row panel-body">
			<div class="pull-left">
				<dir-pagination-controls
					   max-size="8"
					   direction-links="true"
					   boundary-links="true" 
					   on-page-change="getsForPageAndFilter(newPageNumber)" >
				</dir-pagination-controls>
			</div>
			<div class="pull-right">
				<div ng-click="add()">
					<span><img src="${contextPath}/static/images/add.png"></span>
				</div>
			</div>
		</div>
		<!-- list of content -->
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th ng-click="sortBy('name')"><spring:message code="recipient.name"/>
							<span ng-show="sortKey === 'name'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('companyNumber')"><spring:message code="recipient.companyNumber"/>
							<span ng-show="sortKey === 'companyNumber'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('numTax')"><spring:message code="recipient.numTax"/>
							<span ng-show="sortKey === 'numTax'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('bankName')"><spring:message code="recipient.bankName"/>
							<span ng-show="sortKey === 'bankName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('ibanCountry')"><spring:message code="recipient.ibanCountry"/>
							<span ng-show="sortKey === 'ibanCountry'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('ibanKey')"><spring:message code="recipient.ibanKey"/>
							<span ng-show="sortKey === 'ibanKey'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('ibanBban')"><spring:message code="recipient.ibanBban"/>
							<span ng-show="sortKey === 'ibanBban'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('designation')"><spring:message code="recipient.designation"/>
							<span ng-show="sortKey === 'designation'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('street')"><spring:message code="recipient.street"/>
							<span ng-show="sortKey === 'street'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('complement')"><spring:message code="recipient.complement"/>
							<span ng-show="sortKey === 'complement'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('zip')"><spring:message code="recipient.zip"/>
							<span ng-show="sortKey === 'zip'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('city')"><spring:message code="recipient.city"/>
							<span ng-show="sortKey === 'city'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('state')"><spring:message code="recipient.state"/>
							<span ng-show="sortKey === 'state'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('country')"><spring:message code="recipient.country"/>
							<span ng-show="sortKey === 'country'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('taxFree')"><spring:message code="recipient.taxFree"/>
							<span ng-show="sortKey === 'taxFree'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('idle')"><spring:message code="recipient.idle"/>
							<span ng-show="sortKey === 'idle'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('legalNotice')"><spring:message code="recipient.legalNotice"/>
							<span ng-show="sortKey === 'legalNotice'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('contactName')"><spring:message code="recipient.contactName"/>
							<span ng-show="sortKey === 'contactName'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('contactEmail')"><spring:message code="recipient.contactEmail"/>
							<span ng-show="sortKey === 'contactEmail'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('contactPhone')"><spring:message code="recipient.contactPhone"/>
							<span ng-show="sortKey === 'contactPhone'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('contactMobile')"><spring:message code="recipient.contactMobile"/>
							<span ng-show="sortKey === 'contactMobile'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('bankNationalAccountNumber')"><spring:message code="recipient.bankNationalAccountNumber"/>
							<span ng-show="sortKey === 'bankNationalAccountNumber'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('bankInternationalAccountNumber')"><spring:message code="recipient.bankInternationalAccountNumber"/>
							<span ng-show="sortKey === 'bankInternationalAccountNumber'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th ng-click="sortBy('bankIdentificationCode')"><spring:message code="recipient.bankIdentificationCode"/>
							<span ng-show="sortKey === 'bankIdentificationCode'" class="glyphicon sort-icon" ng-class="{'glyphicon-chevron-up': reverse, 'glyphicon-chevron-down': !reverse}"/>
						</th>
						<th/>
						<th/>
					</tr>
				</thead>
				<tbody>
					<tr ng-show="recipient.length">
						<td colspan="4" style="text-align: center;"><spring:message code="common.loading"></spring:message></td>
					</tr>
					<tr dir-paginate="item in recipients | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
						<td>{{item.name}}</td>
						<td>{{item.companyNumber}}</td>
						<td>{{item.numTax}}</td>
						<td>{{item.bankName}}</td>
						<td>{{item.ibanCountry}}</td>
						<td>{{item.ibanKey}}</td>
						<td>{{item.ibanBban}}</td>
						<td>{{item.designation}}</td>
						<td>{{item.street}}</td>
						<td>{{item.complement}}</td>
						<td>{{item.zip}}</td>
						<td>{{item.city}}</td>
						<td>{{item.state}}</td>
						<td>{{item.country}}</td>
						<td>{{item.taxFree}}</td>
						<td>{{item.idle}}</td>
						<td>{{item.legalNotice}}</td>
						<td>{{item.contactName}}</td>
						<td>{{item.contactEmail}}</td>
						<td>{{item.contactPhone}}</td>
						<td>{{item.contactMobile}}</td>
						<td>{{item.bankNationalAccountNumber}}</td>
						<td>{{item.bankInternationalAccountNumber}}</td>
						<td>{{item.bankIdentificationCode}}</td>
						<td>
							<div ng-click="edit(item.id)">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>
						</td>
						<td>
							<div ng-click="delete(item.id)">
								<span class="glyphicon glyphicon-delete"></span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- bottom actions -->
		<div class="row panel-body">
			<div class="pull-left">
				<dir-pagination-controls
					   max-size="8"
					   direction-links="true"
					   boundary-links="true" 
					   on-page-change="getsForPageAndFilter(newPageNumber)" >
				</dir-pagination-controls>
			</div>
			<div class="pull-right">
				<div ng-click="add()">
					<span><img src="${contextPath}/static/images/add.png"></span>
				</div>
			</div>
		</div>
	</div>
</div>