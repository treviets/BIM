app.factory('expenseDetailService', function($http, $rootScope) {

	return {
		insert: function(expense, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-expenses-detail/insert");
			return $http.post(urlServer, expense).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		update: function(expenseId, expense, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-expenses-detail/update/" + expenseId);
			return $http.put(urlServer, expense).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		delete: function(expenseId, successFunc, failFunc) {
			var urlServer = $rootScope.makePostURL("/restful-expenses-detail/delete/" + expenseId);
			return $http.delete(urlServer).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		getById: function(expenseId, successFunc, failFunc) {
			var url = $rootScope.makeGetURL("/restful-expenses-detail/getById/" + expenseId);
			return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
		
		getAllByExpenseId: function(clientId, expensesId, itemsPerPage, pageNo, successFunc, failFunc) {
			var url = $rootScope.makeGetURL("/restful-expenses-detail/list/" + clientId + "/" + expensesId + "/"+ itemsPerPage + "/" + pageNo);
			return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
		},
	}
});