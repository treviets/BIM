
/**
 * Service for Payments
 **/


app.factory('paymentService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(payment) {
			var urlServer = $rootScope.makePostURL("/payment/insert");
			return $http.post(urlServer, payment);
		}
		
		// Update.
		, update: function(paymentId, payment) {
			var urlServer = $rootScope.makePostURL("/payment/update/" + paymentId);
			return $http.put(urlServer, payment);
		}
		
		// Delete.
		, delete: function(paymentId) {
			var urlServer = $rootScope.makePostURL("/payment/delete/" + paymentId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(paymentId) {
			var url = $rootScope.makeGetURL("/payment/getbyid/" + paymentId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, payment) {
			var url = $rootScope.makeGetURL("/payment/list-extend/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: payment });
		}
		
		// List all bills for selection.
		, listBillsForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-bills");
			return $http.get(url);
		}
		
		// List all payments types for selection.
		, listPaymentTypesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-payment-types");
			return $http.get(url);
		}
		
		// List all payments modes for selection.
		, listPaymentModesForSelection: function() {
			var url = $rootScope.makeGetURL("/restful-selection/list-payment-modes");
			return $http.get(url);
		}
	    
		// Upload attachments.
		, uploadAttachments: function(attachments, paymentId) {
	        var filePath = "attachment.path.payments";
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/files/upload-forward") + "&filePath=" + filePath + "&id=" + paymentId,
	            data: attachments,
	            headers: {
	                'Content-Type': undefined
	            }
	        };
	        return $http(request);
	    }
		
		// Download attachments.
		, downloadAttachments: function(paymentId, fileName) {
			var filePath = "attachment.path.payments";
			var url = $rootScope.makeGetURL('/files/download-farther?filePath=' + filePath + "&id=" + paymentId + "&fileName=" + encodeURIComponent(fileName));
			return $http.get(url, { responseType: 'arraybuffer' });
		}
		
	}// return.
});
