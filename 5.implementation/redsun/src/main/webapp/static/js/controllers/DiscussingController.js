/**
 * Controller for File Sharing
 **/

app.controller('discussingController', ['$scope', '$compile', '$location', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log', '$window', '$anchorScroll','$sce', '$location', 'discussingService', 
                              		function($scope, $compile, $location, $rootScope, $mdDialog, $http, $templateRequest, $timeout, $log, $window, $anchorScroll, $sce, $location, discussingService){

	$scope.chatList = [];
	$scope.memberList = [];
	$scope.chatObject = {};
	$scope.group = {};
	$scope.listGroup = [];
	$scope.newMassage = [];
	
	$scope.listGroupOfCurrentUser = [];
	
	$scope.scrollToBottom = function(){
		var elem = document.getElementById('chatArea');
		if(elem){
			elem.scrollTop = elem.scrollHeight+20;
			if($scope.newMassage[$rootScope.groupId]){
				$scope.newMassage[$rootScope.groupId] = 0;
				$window.localStorage[$rootScope.groupId+"_message_count"] = $scope.chatList[$rootScope.groupId].length;
			}
			
		}
		
	}
	$scope.initSocialGroups = function(){
		discussingService.listGroups($scope);
		discussingService.listGroupsByUsername($scope);
		$scope.backToGroupsList();
	};
	$scope.gotoGroupDetail = function(groupObject){
		$scope.isGroupDetail = true;
		$scope.selectedGroup = groupObject;
		if($scope.isGroupDetail == true){
			$scope.isCheck = groupObject.id;
			
		}
		//get list for groups first
		$templateRequest("/redsun/static/partials/social/group-detail.html").then(function(html){
			$scope.groupContent = html;
			$scope.needToJoin = true;
			if($scope.listGroupOfCurrentUser.length > 0){
				angular.forEach($scope.listGroupOfCurrentUser, function(value, key) {
					  if(value.id === groupObject.id){
						  $scope.needToJoin = false;
					  }
				});
				
				if(!$scope.needToJoin){
					$scope.initDiscussing("group_"+$scope.selectedGroup.id);
				}
			}
			
			
		});
	};
	$scope.askToJoinThisGroup = function(){
		discussingService.joinGroup($scope);
	};
	$scope.$on('joinGroupAlready',function() {
		if(!$scope.selectedGroup.members){
			$scope.selectedGroup.members = [];
		}
		$scope.selectedGroup.members.push({"username":currentUsername});
		$scope.listGroupOfCurrentUser.push($scope.selectedGroup);
		$scope.needToJoin = false;
		$scope.initDiscussing("group_"+$scope.selectedGroup.id);
	});
	
	$scope.backToGroupsList = function(){
		$scope.isGroupDetail = false;
		$rootScope.groupId = -1;
		//get list for groups first
		$templateRequest("/redsun/static/partials/social/groups-list.html").then(function(html){
			$scope.groupContent = html;
		});
	}
	$scope.createNewGroup = function(){
		$templateRequest("/redsun/static/partials/social/group-add-new.html").then(function(html){
			$compile(html)($scope);
			var addGroupPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:true,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveNewGroup = function(){
		            	if(!$scope.group.name || !$scope.group.description){
		            		$scope.groupCreateError = "Full fill both fields to create new group";
		            	} else {
		            		$scope.groupCreateError = "";
		            		angular.forEach($scope.listGroup, function(value, key) {
		    					if(value.name === $scope.group.name){
		    						$scope.groupCreateError = "This group already existed.";
		    					}
		    				});
		            		if($scope.groupCreateError === ""){
		            			discussingService.createNewGroup($scope);
		            		}
		            		
		            	}
		            };
				}
			});
			$mdDialog.show(addGroupPopup);
		});
	}
	$scope.$on('addGroupAlready', function(){
		if($scope.newGroup){
			if($scope.listGroup.indexOf($scope.newGroup) === -1){
				$scope.listGroup.push($scope.newGroup);
			}
			
			$mdDialog.hide();
			$scope.$broadcast('bindingChatForEachGroup');
		}
	});
	$scope.initDiscussing = function(groupId){
		$rootScope.groupId = groupId;
		if(groupId === 'ALL'){
			$rootScope.projectId = 0;
			discussingService.getResourceAllTypeForChat($scope);
		} else {
			if(groupId.indexOf("Project_")>-1){
				$rootScope.projectId = groupId.split("_")[1];
				$scope.projectId = $rootScope.projectId;
				discussingService.getMemberOfProjectForChat($scope);
			}
		}
		//load message to chat room
		discussingService.listChatsForGroup($scope);
		
		$scope.$on('getListChatsForGroupAlready', function(event, params) {
			if($scope.chatting && $scope.chatting.result.data) {
				$scope.chatList[params.groupId] = $scope.chatting.result.data;
				var messageReadAlreadyNumber = 0;
				if($window.localStorage[params.groupId+"_message_count"]){
					messageReadAlreadyNumber = parseInt($window.localStorage[params.groupId+"_message_count"]);
				} else {
					$window.localStorage[params.groupId+"_message_count"] = 0;
				}
				$scope.newMassage[params.groupId] = $scope.chatList[params.groupId].length-messageReadAlreadyNumber;
				$scope.$broadcast('bindChatObjectAlready');
			}
		});
		
		$scope.$on('bindChatObjectAlready', function(){
			//10 seconds delay
	        $timeout( function(){
	        	$scope.scrollToBottom();
	        }, 500 );
		});
		
		$scope.$on('getListGroupsAlready', function(){
			
			if($scope.getGroupListResult && $scope.getGroupListResult.result.data) {
				$scope.listGroup = $scope.getGroupListResult.result.data;
				$scope.$broadcast('bindingChatForEachGroup');
			}
		});
		
		$scope.$on('bindingChatForEachGroup', function(){
			angular.forEach($scope.listGroup, function(value, key) {
				//load message to chat room
				$scope.tempGroupId='group_'+value.id;
				discussingService.listChatsForGroup($scope);
				$scope.tempGroupId = null;
			});
		});
		
		$scope.$on('getListGroupsByUsernameAlready', function(){
			if($scope.getGroupListByUsernameResult && $scope.getGroupListByUsernameResult.result.data) {
				$scope.listGroupOfCurrentUser = $scope.getGroupListByUsernameResult.result.data;
			}
		});
		
		
		// WebSocket Initialization
	    var projectSocket = new WebSocket("ws"+ "://" + $location.host() + ":" + $location.port()+"/redsun/project/websocket/register/");
	 
	    projectSocket.onmessage = function(message) {
	        $scope.newChat = angular.fromJson(message.data);
	        
			if($scope.newChat.groupId !== $rootScope.groupId){
				if(!$scope.newMassage[$scope.newChat.groupId]){
					$scope.newMassage[$scope.newChat.groupId] = 1;
				} else {
					$scope.newMassage[$scope.newChat.groupId] += 1;
				}
				$scope.chatList[$scope.newChat.groupId].push($scope.newChat);
			} else {
				if($window.localStorage[$scope.newChat.groupId+"_message_count"]){
					var messageCount = parseInt($window.localStorage[$scope.newChat.groupId+"_message_count"]);
					$window.localStorage[$scope.newChat.groupId+"_message_count"] = messageCount + 1;
				} else {
					$window.localStorage[$scope.newChat.groupId+"_message_count"] = 1;
				}
				$scope.chatList[$rootScope.groupId].push($scope.newChat);
			}
	        
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
	    $scope.$on('getMemberForChatAlready',function() {
			if($scope.data) {
				if( $scope.data.result.resources){
					$rootScope.projectresources = $scope.data.result.resources;
				} else {
					$rootScope.projectresources = $scope.data.result.projectresources;
				}
				if($rootScope.projectresources.length > 0){
					$scope.memberList = [];
				}
				
				angular.forEach($rootScope.projectresources, function(value, key) {
					if(!value.avatar){
						value.avatar = "/redsun/static/images/default-avatar.jpg";
					}
					if($scope.memberList.indexOf(value) < 0){
						$scope.memberList.push(value);
					}
					
				});
			}
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