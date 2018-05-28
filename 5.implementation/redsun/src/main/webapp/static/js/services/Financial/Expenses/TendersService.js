

app.factory('tenderService', function($http, $rootScope) {
	return {
	
		insert: function(tender, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-tender/insert");
			return $http.post(urlServer, tender).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		}
		
		, update: function(tenderId, tender, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-tender/update/" + tenderId);
			return $http.put(urlServer, tender).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		}
		
		, delete: function(refType, tenderId, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-tender/delete/" + refType + "/" + tenderId);
			return $http.delete(urlServer).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		}
		
		, getById: function(tenderId, successFunc, failFunc) {
			var url = $rootScope.makeGetURL("/restful-tender/getById/" + tenderId);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		}
		
		, getsForPageAndFilter: function(clientId, pageNo, itemsPerPage, successFunc, failFunc) {
			var url = $rootScope.makeGetURL("/restful-tender/list/" + clientId + "/" + pageNo  + "/" + itemsPerPage);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		}
	}
});