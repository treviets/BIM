
/**
 * Service for Quotations
 **/


app.factory('quotationService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(quotation) {
			var urlServer = $rootScope.makePostURL("/quotation/insert");
			return $http.post(urlServer, quotation);
		}
		
		// Update.
		, update: function(quotationId, quotation) {
			var urlServer = $rootScope.makePostURL("/quotation/update/" + quotationId);
			return $http.put(urlServer, quotation);
		}
		
		// Delete.
		, delete: function(quotationId) {
			var urlServer =$rootScope.makePostURL("/quotation/delete/" + quotationId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(quotationId) {
			var url = $rootScope.makeGetURL("/quotation/getbyid/" + quotationId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, quotation) {
			var url = $rootScope.makeGetURL("/quotation/list-extend/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: quotation });
		}
		
		// List all projects for selection.
		, listProjectsForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-projects");
			return $http.get(url);
		}
		
		// List all quotations types for selection.
		, listQuotationTypesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-quotation-types");
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
		, uploadAttachments: function(attachments, quotationId) {
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/files/upload-forward") + "&actionType=quotations" + "&id=" + quotationId,
	            data: attachments,
	            headers: {
	                'Content-Type': undefined
	            }
	        };
	        return $http(request);
	    }
		
		// Download attachments.
		, downloadAttachments: function(quotationId, fileName) {
			var url = $rootScope.makeGetURL('/files/download-farther')+ "?actionType=quotations" + "&id=" + quotationId + "&fileName=" + encodeURIComponent(fileName);
			return $http.get(url, { responseType: 'arraybuffer' });
		}
		
	}// return.
});
