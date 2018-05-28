
app.factory('expenseService', function($http, $rootScope) {

	return {
		insert: function(expense, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-expenses/insert");
			return $http.post(urlServer, expense).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		update: function(expenseId, expense, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-expenses/update/" + expenseId);
			return $http.put(urlServer, expense).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		delete: function(scope, expenseId, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-expenses/delete/" + scope + "/" + expenseId);
			return $http.delete(urlServer).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		getById: function(expenseId, successFunc, failFunc) {
			var url = $rootScope.makeGetURL("/restful-expenses/getById/" + expenseId);
			return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		getAllByScope: function(clientId, scope, itemsPerPage, pageNo, successFunc, failFunc) {
			var url = $rootScope.makeGetURL("/restful-expenses/list/" + clientId + "/" + scope + "/"+ itemsPerPage + "/" + pageNo);
			return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
	}
});