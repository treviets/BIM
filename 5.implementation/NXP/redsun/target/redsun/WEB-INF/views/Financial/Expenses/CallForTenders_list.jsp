<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div ng-controller="callForTenderController_List" class="container panel panel-success" ng-init="init(123, 1)">
    <div class="row panel-heading">
        <div class="col-sm-6 text-left">
            <spring:message code="callfortender.title.list"></spring:message>
        </div>
        <div class="col-sm-6 text-right">
            <button type="button" class="btn btn-info" ng-click="newTender()">
                <span class="glyphicon glyphicon-file"></span> <spring:message code="callfortender.button.new"></spring:message>
            </button>
        </div>
    </div>
    <div class="row panel-body">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th class="text-align-center"><spring:message code="callfortender.no"></spring:message></th>
                <th class="text-align-center"><spring:message code="callfortender.project"></spring:message></th>
                <th class="text-align-center"><spring:message
                        code="callfortender.type"></spring:message></th>
                <th class="text-align-center"><spring:message code="callfortender.tender"></spring:message></th>
                <th class="text-align-center"><spring:message code="callfortender.status"></spring:message></th>
                <th class="text-align-center"><spring:message code="callfortender.close"></spring:message></th>
            </tr>
            </thead>
            <tbody>
            <tr dir-paginate="tender in tenders | orderBy:sortKey:reverse | itemsPerPage:itemsPerPage" total-items="totalCount">
                <td class="text-align-center">{{$index + 1 + itemsPerPage * (pageNo-1)}}</td>
                <td class="text-align-center">{{tender.projectName}}</td>
                <td class="text-align-center">{{tender.typeName}}</td>
                <td class="text-align-center">{{tender.tenderName}}</td>
                <td class="text-align-center">{{tender.status}}</td>
                <td class="text-align-center">
                    <input ng-disabled="true" type="checkbox" ng-checked="tender.closed"/>
                </td>
                <td class="text-align-center">
                    <button type="button" class="btn btn-info" ng-click="viewTenderDetail(tender.id)">
                        <span class="glyphicon glyphicon-eye-open"></span> <spring:message code="callfortender.button.update"></spring:message>
                    </button>
                    <button type="button" class="btn btn-info" ng-click="delete(tender.id)">
                        <span class="glyphicon glyphicon-remove-sign"></span> <spring:message code="callfortender.button.delete"></spring:message>
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


