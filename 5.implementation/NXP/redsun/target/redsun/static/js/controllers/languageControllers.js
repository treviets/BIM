app.controller('languageCtrl', ['$scope', '$rootScope', '$translate', '$location', '$window', 'profileService', function($scope, $rootScope, $translate, $location, $window, profileService) {

	var lang = '';
	$scope.customUrl = function(language){
		try{
			$location.search("lang",language);
			lang = language;
			$window.location.reload();
			
		}
		catch(err){
			
		}
		finally{
		}
    }	
	
	$scope.$on('reloadFinish', function() {
		changeLanguage(lang);
	});
	
	changeLanguage = function(lang){
		$translate.use(lang);  // sets lang to use
	}
	
	getClientById = function(){
		profileService.getClientById($scope);
	}
	
	$scope.$on('getClientAlready', function() {
		if($scope.data)
			$scope.client = $scope.data;
	});
	
	getClientById();
}]);