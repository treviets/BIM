

/**
 * Controller for ${entity.name}
 **/


app.factory('callForTenderService', function($http,$rootScope) {
	return {

		update: function(callForTenderId, callForTender, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-call-for-tender/update/" + callForTenderId);
			return $http.put(urlServer, callForTender).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},

        insert: function(callForTender, successFunc, failFunc) {
            var urlServer = $rootScope.makePostURL("/restful-call-for-tender/insert");
            return $http.post(urlServer, callForTender).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        delete: function(refType, callForTenderId, successFunc, failFunc) {
            var urlServer = $rootScope.makePostURL("/restful-call-for-tender/delete/" + refType + "/" + callForTenderId);
            return $http.delete(urlServer).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

		getAll: function (clientId, pageNo, itemsPerPage, successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-call-for-tender/list/" + clientId + "/" + pageNo + "/" + itemsPerPage);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

		getById: function(clientId, tenderId, successFunc, failFunc){
            var url = $rootScope.makeGetURL("/restful-call-for-tender/get/" + clientId + "/" + tenderId);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		}
	}
});