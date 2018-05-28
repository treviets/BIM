app.controller('customerCtrl', ['$scope', '$rootScope', '$http', 'customerService', function($scope, $rootScope, $http, customerService) {

	var pageno = 1; // initialize page no to 1
	$scope.totalCount = 0;
	$scope.itemsPerPage = 10; //this could be a dynamic value from a drop down
  
	 $scope.getCustomers = function(pageNo) {
		// Get customer by page
		 if($scope.code=="") $scope.code = undefined;
		 if($scope.name=="") $scope.name = undefined;
		 customerService.getCustomers($scope, $rootScope, $http, pageNo);
	};
	$scope.$on('getCustomersAlready', function() {
		$scope.customers = $scope.data.result.customers;
		$scope.totalCount = $scope.customers[0].totalCount;
	});
	$scope.sort = function(keyName){
        $scope.sortKey = keyName;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    }
	$scope.getCustomers(pageno);
}]);