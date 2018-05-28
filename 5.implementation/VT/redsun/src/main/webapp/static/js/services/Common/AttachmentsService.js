/**
 * Created by HauLL on 3/15/2017.
 */

app.factory('attachmentService', function($http, $rootScope) {
    return {
        getByRef: function(refType, refId, successFunc, failFunc){
            var url = $rootScope.makeGetURL("/restful-attachments/getByRefId/" + refType + "/" + refId);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        upload: function (realFile, attachModel, successFunc, failFunc) {
            var url = $rootScope.makePostURL("/restful-attachments/upload");
            var formData = new FormData();
            formData.append('file', realFile);
            formData.append('attachModel', JSON.stringify(attachModel));

            return $http.post(url, formData, {
                transformRequest: angular.identity,
                headers: { 'Content-Type' : undefined}
            }).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        insert: function(refId, attachmentModel, successFunc, failFunc) {
            var urlServer = $rootScope.makePostURL("/restful-attachments/insert");
            return $http.post(urlServer, attachmentModel).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        delete: function(attachId, successFunc, failFunc){
            var urlServer = $rootScope.makePostURL("/restful-attachments/delete/" + attachId);
            return $http.delete(urlServer).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        }
    }
});