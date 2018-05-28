

/**
 * Controller for ${entity.name}
 **/


app.factory('productService', function($http, $rootScope) {
	return {
	
		// Insert.
		add: function(product) {
			var urlServer = $rootScope.makePostURL("/product/insert");
			return $http.post(urlServer, product);
		}
		
		// Update.
		, update: function(productId, product) {
			var urlServer = $rootScope.makePostURL("/product/update/" + productId);
			return $http.put(urlServer, product);
		}
		
		// Delete.
		, delete: function(productId) {
			var urlServer = $rootScope.makePostURL("/product/delete/" + productId);
			return $http.delete(urlServer);
		}
		
		// Get by Id.
		, getById: function(productId) {
			var url = $rootScope.makeGetURL("/product/getbyid/" + productId);
			return $http.get(url);
		}
		
		// List for page and filter.
		, getsForPageAndFilter: function(itemsPerPage, pageNo, product) {
			var url = $rootScope.makeGetURL("/product/list/page/filter/" + itemsPerPage + "/" + pageNo);
			return $http.get(url, { params: product });
		}
		
	}// return.
});