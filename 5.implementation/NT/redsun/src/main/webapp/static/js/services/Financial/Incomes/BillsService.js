
/**
 * Service for Bills
 **/


app.factory('billService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(bill) {
			var urlServer = $rootScope.makePostURL("/bill/insert");
			return $http.post(urlServer, bill);
		}
		
		// Update.
		, update: function(billId, bill) {
			var urlServer = $rootScope.makePostURL("/bill/update/" + billId);
			return $http.put(urlServer, bill);
		}
		
		// Delete.
		, delete: function(billId) {
			var urlServer = $rootScope.makePostURL("/bill/delete/" + billId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(billId) {
			var url = $rootScope.makeGetURL("/bill/getbyid/" + billId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, bill) {
			var url = $rootScope.makeGetURL("/bill/list-extend/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: bill });
		}
		
		// List all projects for selection.
		, listProjectsForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-projects");
			return $http.get(url);
		}
		
		// List all bills types for selection.
		, listBillTypesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-bill-types");
			return $http.get(url);
		}
		
		// List statuses for selection.
		, listStatusesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-statuses/Expense");
			return $http.get(url);
		}
		
		// List customers for selection.
		, listCustomersForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-customers");
			return $http.get(url);
		}
		
		// List users for selection.
		, listUsersForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-users");
			return $http.get(url);
		}
		
		// List resources for selection.
		, listResourcesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-resources");
			return $http.get(url);
		}
		
		// List contacts for selection.
		, listContactsForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-contacts");
			return $http.get(url);
		}
	    
		// Upload attachments.
		, uploadAttachments: function(attachments, billId) {
	        var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/files/upload-forward")+ "&actionType=bills" + "&id=" + billId,
	            data: attachments,
	            headers: {
	                'Content-Type': undefined
	            }
	        };
	        return $http(request);
	    }
		
		// Download attachments.
		, downloadAttachments: function(billId, fileName) {
			var url = $rootScope.makeGetURL('/files/download-farther')+ "?actionType=bills" + '&id=' + billId + "&fileName=" + encodeURIComponent(fileName);
			return $http.get(url, { responseType: 'arraybuffer' });
		}
		
	}// return.
});
