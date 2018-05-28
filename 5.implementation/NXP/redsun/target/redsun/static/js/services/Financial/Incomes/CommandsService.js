
/**
 * Service for Commands
 **/


app.factory('commandService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(command) {
			var urlServer = $rootScope.makePostURL("/command/insert");
			return $http.post(urlServer, command);
		}
		
		// Update.
		, update: function(commandId, command) {
			var urlServer = $rootScope.makePostURL("/command/update/" + commandId);
			return $http.put(urlServer, command);
		}
		
		// Delete.
		, delete: function(commandId) {
			var urlServer = $rootScope.makePostURL("/command/delete/" + commandId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(commandId) {
			var url = $rootScope.makeGetURL("/command/getbyid/" + commandId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, command) {
			var url = $rootScope.makeGetURL("/command/list-extend/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: command });
		}
		
		// List all projects for selection.
		, listProjectsForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-projects");
			return $http.get(url);
		}
		
		// List all commands types for selection.
		, listCommandTypesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-command-types");
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
		, uploadAttachments: function(attachments, commandId) {
	        var filePath = "attachment.path.commands";
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/files/upload-forward") + "&filePath=" + filePath + "&id=" + commandId,
	            data: attachments,
	            headers: {
	                'Content-Type': undefined
	            }
	        };
	        return $http(request);
	    }
		
		// Download attachments.
		, downloadAttachments: function(commandId, fileName) {
			var filePath = "attachment.path.commands";
			var url = $rootScope.makeGetURL('/files/download-farther?filePath=' + filePath + "&id=" + commandId + "&fileName=" + encodeURIComponent(fileName));
			return $http.get(url, { responseType: 'arraybuffer' });
		}
		
	}// return.
});
