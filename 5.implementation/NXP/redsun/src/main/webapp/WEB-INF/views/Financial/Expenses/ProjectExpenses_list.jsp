<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div ng-controller="projectExpensesController_List" class="container panel panel-success" ng-init="init(1, 1)">
    <div class="row panel-heading">
        <div class="col-sm-6 text-left">
            <spring:message code="project.expense.title"></spring:message>
        </div>
        <div class="col-sm-6 text-right">
            <button type="button" class="btn btn-info" ng-click="new()">
                <span class="glyphicon glyphicon-file"></span> <spring:message code="project.expense.button.new"></spring:message>
            </button>
        </div>
    </div>
    <div class="row panel-body">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th class="text-align-center"><spring:message code="project.expense.no"></spring:message></th>
                <th class="text-align-center"><spring:message code="project.expense.project"></spring:message></th>
                <th class="text-align-center"><spring:message code="project.expense.type"></spring:message></th>
                <th class="text-align-center"><spring:message code="project.expense.name"></spring:message></th>
                <th class="text-align-center"><spring:message code="project.expense.status"></spring:message></th>
                <th class="text-align-center"><spring:message code="project.expense.closed"></spring:message></th>
            </tr>
            </thead>
            <tbody>
            <tr dir-paginate="expense in expenses | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
                <td class="text-align-center">{{$index + 1 + itemsPerPage * (pageNo-1)}}</td>
                <td class="text-align-center">{{expense.project}}</td>
                <td class="text-align-center">{{expense.type}}</td>
                <td class="text-align-center">{{expense.name}}</td>
                <td class="text-align-center">{{expense.status}}</td>
                <td class="text-align-center">
                    <input ng-disabled="true" type="checkbox" ng-checked="expense.closed"/>
                </td>
                <td class="text-align-center">
                    <button type="button" class="btn btn-info" ng-click="viewDetail(expense.id)">
                        <span class="glyphicon glyphicon-eye-open"></span> <spring:message code="project.expense.button.update"></spring:message>
                    </button>
                    <button type="button" class="btn btn-info" ng-click="delete(expense.id)">
                        <span class="glyphicon glyphicon-remove-sign"></span> <spring:message code="project.expense.button.delete"></spring:message>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <dir-pagination-controls
                max-size="5"
                direction-links="true"
                boundary-links="true"
                on-page-change="init(123, newPageNumber)" >
        </dir-pagination-controls>
    </div>
</div>


