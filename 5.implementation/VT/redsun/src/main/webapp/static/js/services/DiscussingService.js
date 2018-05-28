app.factory('discussingService', function($http, $rootScope){
	return {
		
		getMemberOfProjectForChat : function($scope) {
			var url = $rootScope.makeGetURL("/projectresource/listforoneproject/" + $scope.projectId);
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMemberForChatAlready');
			});

			return true;
		},
		getResourceAllTypeForChat : function($scope) {
			var url = $rootScope.makeGetURL("/hr/list-all-type/");
			$http.get(url).then(function(response) {
				if (response.data) {
					$scope.data = angular.fromJson(response.data);
				}
				$scope.$broadcast('getMemberForChatAlready');
			});
	
			return true;
		},
		create: function($scope) {
			var urlServer = $rootScope.makePostURL("/discussing/create/");
			$scope.chatObject.scopes = 1;
			if($scope.chatObject.groupId === 'ALL'){
				$scope.chatObject.scopes = 2;
			}
			$scope.chatObject.groupId = $rootScope.groupId;
			$scope.chatObject.projectId = $rootScope.projectId;
			$http.post(urlServer, $scope.chatObject).then(function(response){
				$scope.chatObject.message = "";
				if(response.data.result){
					$scope.newChat = angular.fromJson(response.data.result.newChat);
					
				}
			}).finally(function() {
				$scope.$broadcast('addDiscussionAlready');
			});
			return false;
		},
		listChatsForGroup: function($scope) {
			var groupId = $rootScope.groupId;
			if($scope.tempGroupId){
				groupId = $scope.tempGroupId;
			}
			var urlServer = $rootScope.makePostURL("/discussing/list-by-group/"+groupId+"/");
			return $http.get(urlServer).then(function(response){
				if(response.data){
					$scope.chatting = angular.fromJson(response.data);
				}
				
			}).finally(function() {
				$scope.$broadcast('getListChatsForGroupAlready',{"groupId":groupId});
			});
			
			return false;
		},
		createNewGroup: function($scope) {
			var urlServer = $rootScope.makePostURL("/discussing/group/create/");
			$scope.group.projectId = $rootScope.projectId;
			$http({
			    method: 'POST',
			    url: urlServer,
			    data: $scope.group,
			    headers: {'Content-Type': 'application/json'}
			}).then(function(response){
				$scope.group = {};
				if(response.data.result){
					$scope.newGroup = angular.fromJson(response.data.result.newGroup);
				}
			}).finally(function() {
				$scope.$broadcast('addGroupAlready');
			});
			return false;
		},
		listGroups: function($scope) {
			var urlServer = $rootScope.makePostURL("/discussing/group/list/"+$rootScope.projectId+"/");
			return $http.get(urlServer).then(function(response){
				if(response.data){
					$scope.getGroupListResult = angular.fromJson(response.data);
				}
				
			}).finally(function() {
				$scope.$broadcast('getListGroupsAlready');
			});
		},
		listGroupsByUsername: function($scope) {
			var urlServer = $rootScope.makePostURL("/discussing/group/list/by-username/");
			return $http.get(urlServer).then(function(response){
				if(response.data){
					$scope.getGroupListByUsernameResult = angular.fromJson(response.data);
				}
				
			}).finally(function() {
				$scope.$broadcast('getListGroupsByUsernameAlready');
			});
		},
		joinGroup: function($scope) {
			var urlServer = $rootScope.makePostURL("/discussing/group/join/"+$scope.selectedGroup.id+"/");
			return $http.get(urlServer).then(function(response){
			}).finally(function() {
				$scope.$broadcast('joinGroupAlready');
			});
		}
	}
});