app.factory('pmReExpenseService', function($http, $rootScope) {
	// Might use a resource here that returns a JSON array
	return {
		
		// listCallAndTendert.
		listCallAndTendert: function(projectId, startDate, endDate) {
			var urlServer = $rootScope.makeGetURL("/reportexpense/listcallandtender/"
					+ projectId + "/" + startDate + "/" + endDate);
			return $http.get(urlServer);
		},
	
		// listBillAndPayment.
		listBillAndPayment: function(projectId, startDate, endDate) {
			var urlServer = $rootScope.makeGetURL("/reportexpense/listbillandpayment/"
					+ projectId + "/" + startDate + "/" + endDate);
			return $http.get(urlServer);
		},
		
		// listCallAndTendert.
		listQuotationAndOrder: function(projectId, startDate, endDate) {
			var urlServer = $rootScope.makeGetURL("/reportexpense/listquotationandorder/"
					+ projectId + "/" + startDate + "/" + endDate);
			return $http.get(urlServer);
		}
	
	};
});