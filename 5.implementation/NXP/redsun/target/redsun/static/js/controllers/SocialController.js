/**
 * Controller for File Sharing
 **/

app.controller('discussingController', ['$scope', '$compile', '$location', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log', '$window', '$anchorScroll', 'discussingService', 
                              		function($scope, $compile, $location, $rootScope, $mdDialog, $http, $templateRequest, $timeout, $log, $window, $anchorScroll, discussingService){

	$scope.chatList = [];
	$scope.memberList = [];
	$scope.chatObject = {};
	$scope.chatList = [];
	$scope.scrollToBottom = function(){
		var elem = document.getElementById('chatArea');
		elem.scrollTop = elem.scrollHeight+20;
	}
	$scope.initDiscussing = function(){
		
		//load message to chat room
		discussingService.listForProject($scope);
		$scope.$on('getDisccussionForProjectAlready', function() {
			if($scope.chatting.result.data) {
				$scope.chatList = $scope.chatting.result.data;
				$scope.$broadcast('bindChatObjectAlready');
			}
		});
		$scope.$on('bindChatObjectAlready', function(){
			//10 seconds delay
	        $timeout( function(){
	        	$scope.scrollToBottom();
	        }, 500 );
		});
		
		// WebSocket Initialization
	    var projectSocket = new WebSocket("ws://localhost:8080/redsun/project/websocket/register/");
	 
	    projectSocket.onmessage = function(message) {
	        $scope.newChat = angular.fromJson(message.data);
	        $scope.chatList.push($scope.newChat);
	        $scope.chatObject.message = '';
	        $timeout( function(){
	        	$scope.scrollToBottom();
	        }, 500 );
	        $scope.$apply();
	    };

	    projectSocket.onclose = function() {
	        $scope.message = {
	            type: "danger",
	            short: "Socket error",
	            long: "An error occured with the WebSocket."
	        };
	        $scope.$apply();    
	    }
	    
		angular.forEach($rootScope.projectresources, function(value, key) {
			if(!value.avatar){
				value.avatar = "/redsun/static/images/default-avatar.jpg";
			}
			$scope.memberList.push(value);
		});
		
	};
	$scope.sendChat = function(){
		if(!$scope.chatObject.message){
			return;
		}
		//Add new message to chat room
		discussingService.create($scope);
		$scope.$on('addDiscussionAlready', function() {
			if($scope.newChat) {
				$timeout( function(){
		        	$scope.scrollToBottom();
		        }, 500 );
			}
		});
		
	};
}]);