app.controller('tasksCtrl',['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$templateCache', '$timeout', '$log', '$location', '$translate',
                    		'$window', 'taskService', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest,$templateCache, $timeout,$log, $location, $translate,
		$window, taskService) {
	$scope.pageNo = 1; // initialize page no to 1
	$scope.totalCount = 0;
	$scope.itemsPerPage = 100; // this could be a dynamic value
	$scope.task = {};
	//arrays for drag and drop
	$scope.tasks = [];
	$scope.taskBacklog = [];
	$scope.taskTodo = [];
	$scope.taskInprogress = [];
	$scope.taskDone = [];
	$scope.taskBlock = [];
	var listMail  ='';
	var msgTitle = '';
	var msgContent = '';
	var msgOk = '';
	var msgCancel = '';
	var msgDelSuccess = '';
	var msgDelFail = '';
	var msgDelErr = '';
	
	$scope.dropSuccessHandler = function($event, index, array){
		array.splice(index,1);
	};
	$scope.onDrop = function($event, $data, array, statusId){
		$data.statusId = statusId;
		array.push($data);
		$scope.task = $data;
		$scope.gotoTaskDetailPage($data);
	};
	
	//get resource
	$scope.getTaskResources = function() {
		taskService.getTaskResources($scope);
	};
	
	$scope.$on('getTaskResourcesAlready', function() {
		if ($scope.data) {
			$scope.assignments = $scope.data.result.assignments;
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
		}
	});
	//get task for one the project
	$scope.getTasksOnePrject = function(id) {
		$scope.projectId = id;
		taskService.getTasksOnePrject($scope);
	};

	$scope.$on('getTasksOnePrjectAlready', function() {
		if($scope.data) {
			$scope.tasks = $scope.data.result.tasks;
			$scope.taskBacklog =  $scope.data.result.taskBacklog;
			$scope.taskTodo = $scope.data.result.taskTodo;
			$scope.taskInprogress = $scope.data.result.taskInprogress;
			$scope.taskDone = $scope.data.result.taskDone;
			$scope.taskBlock = $scope.data.result.taskBlock;
		}
	});
	//get resource
	$scope.getTaskResourcesOneProject = function() {
		taskService.getTaskResourcesOneProject($scope);
	};
	
	$scope.$on('getTaskResourcesOneProjectAlready', function() {
		if ($scope.data) {
			$scope.taskresources = $scope.data.result.taskresources;
		}
	});
	
	// get filter resource
	$scope.getFilterProjectResources = function() {
		taskService.getFilterProjectResources($scope);
	};
	$scope.$on('getFilterProjectResourcesAlready', function() {
		if ($scope.data) {
			$scope.projectresourcesfiltered = $scope.data.result.projectresources;
		}
	});
	// get filter equipments
	$scope.getFilterProjectEquipments = function() {
		taskService.getFilterProjectEquipments($scope);
	};
	$scope.$on('getFilterProjectEquipmentsAlready', function() {
		if ($scope.data) {
			$scope.projectequipments = $scope.data.result.projectequipments;
		}
	});
	
	// get filter materials
	$scope.getFilterProjectMaterials = function() {
		taskService.getFilterProjectMaterials($scope);
	};
	$scope.$on('getFilterProjectMaterialsAlready', function() {
		if ($scope.data) {
			$scope.projectmaterials = $scope.data.result.projectmaterials;
		}
	});
	//get Roles
	$scope.getRoles = function() {
		taskService.getRoles($scope);
	};
	$scope.$on('getRolesAlready', function() {
		if ($scope.data) {
			$scope.roles = $scope.data.result.roles;
		}
	});
	//get document version by task id
	$scope.getDocumentVersionByTask = function() {
		taskService.getDocumentVersionByTask($scope);
	};
	$scope.$on('getDocumentVersionByTaskAlready', function() {
		if ($scope.data) {
			$scope.documentversions = $scope.data.result.documentversions;
		}
	});
	//get task equipment
	$scope.getTaskEquipments = function() {
		taskService.getTaskEquipments($scope);
	};
	
	$scope.$on('getTaskEquipmentsAlready', function() {
		if ($scope.data) {
			$scope.taskequipments = $scope.data.result.taskequipments;
		}
	});
	
	//get task material
	$scope.getTaskMaterials = function() {
		taskService.getTaskMaterials($scope);
	};
	
	$scope.$on('getTaskMaterialsAlready', function() {
		if ($scope.data) {
			$scope.taskmaterials = $scope.data.result.taskmaterials;
		}
	});
	
	$scope.getComments = function() {
		taskService.getComments($scope);
	};


	$scope.$on('getCommentsAlready', function() {
		if ($scope.data) {
			$scope.comments = $scope.data.result.comments;
		}
	});
	
	

	$scope.getTasks = function() {
		taskService.getTasks($scope);
	};


	$scope.$on('getTasksAlready', function() {
		if ($scope.data) {
			$scope.tasks = $scope.data.result.tasks;
		}
	});

	$scope.getProjectEquipments = function() {
		taskService.getProjectEquipments($scope);
	};


	$scope.$on('getProjectEquipmentsAlready', function() {
		if ($scope.data) {
			$scope.projectequipments = $scope.data.result.projectequipments;
		}
	});
	$scope.getProjectMaterials = function() {
		taskService.getProjectMaterials($scope);
	};


	$scope.$on('getProjectMaterialsAlready', function() {
		if ($scope.data) {
			$scope.projectmaterials = $scope.data.result.projectmaterials;
		}
	});
	
	//get document id
	$scope.getDocumentByTask = function() {
		taskService.getDocumentByTask($scope);
	};
	
	$scope.$on('getDocumentByTaskAlready', function() {
		if ($scope.data) {
			$scope.documents = $scope.data.result.documents;
		}
	});
	$scope.getByIdProject = function(id) {
		taskService.getByIdProject($scope);
	}
	
	$rootScope.$on('showTaskDialog', function(ev, args) {
		$scope.ganttParentId = args.parentId;
		$scope.addTask();
	});
	
	
	
	// Import task from file.
	$scope.fileTaskSelected = function(element) {
		$scope.fileTask = element.files[0];
	}
	$scope.importTask = function() {
		if(!$scope.fileTask) {
			alert('Select file to import!!!');
		} else {
			$scope.importTaskFile = new FormData();
			$scope.importTaskFile.append('file', $scope.fileTask);
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/task/import-task") + "&filePath=null&projectId="+$scope.projectId,
	            data: $scope.importTaskFile,
	            headers: {
	                'Content-Type': undefined
	            }
	        };			
			
			$http(request).success(function(response) {
				alert('Import file successfully!!!');
				$window.location.href = $window.location;
			})
			.error(function(response) {
				alert('Cannot import this file, please contact Administrator!!!');
			});
			
		}
	}
	$scope.addTask = function(){
		$scope.task={};
		$scope.task.statusId = 15;
		$scope.task.typeId = 27;
		$scope.document = {};
		$scope.docVer= {};
		var hasNoError = true;
		$scope.member = [];
		$templateRequest("/redsun/static/partials/task-tabs-add.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Add new task";
			$scope.task.projectId = $scope.projectId;
			if($scope.ganttParentId){
				$scope.task.parentId = $scope.ganttParentId;
				$scope.ganttParentId = null;
				$compile(html)($scope);
			}
			var addTaskPopup = null;
			addTaskPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		              addTaskPopup = null;
		            }
		            
		            $scope.saveTask = function(attachFile){
		            	$scope.task.wbs = $scope.projectId + "_" + Math.floor((Math.random() * 1000) + 1);
		        		if(!$scope.task.taskName){
		        			$scope.taskNameError = 'has-error';
		        			hasNoError = false;
		        		}
		        		
		        		if(!$scope.task.estimateTime){
		        			$scope.taskDurationError = 'has-error';
		        			hasNoError = false;
		        		}
		        		var startDateProject = new Date($scope.project.startDate);
		        		var startProject = startDateProject.getDate() + "/" + (startDateProject.getMonth()+1) +  "/" + startDateProject.getFullYear()  ;
		        		var count = 1;
		        		var dayMilliseconds = 1000*60*60*24;
		        		var start = new Date($scope.task.startDate);
		        		var startTask = start.getDate() + "/" + (start.getMonth() + 1) + "/" + start.getFullYear() ;
		        		if($scope.task.endDate == null && $scope.task.estimateTime > 0 && $scope.project.workSunday == '0'){
	                		while (count <= $scope.task.estimateTime){
		                		var day = start.getDay();
		                		if(day != 0){
		                			count = count +1;
		                		}
		                		start = new Date(+start + dayMilliseconds);
		                	}
	                		$scope.task.endDate =  new Date(+start - dayMilliseconds);
	                	}
		        		
		        		if($scope.task.endDate == null && $scope.task.estimateTime > 0 && $scope.project.workSunday == '1'){
	                		while (count <= $scope.task.estimateTime){
		                		var day = start.getDay();
		                			count = count + 1;
		                		start = new Date(+start + dayMilliseconds);
		                	}
	                		$scope.task.endDate =  new Date(+start - dayMilliseconds);
	                	}
	                	var end = new Date($scope.task.endDate);
	                	
	                	if(!$scope.task.startDate || (new Date(startTask, "dd/mm/yyyy").getTime() < new Date(startProject, "dd/mm/yyyy").getTime())){
	                		$("#startDate").text("Start Date must after Start Date: "+ startProject+ " in Project");
							  $("#startDate").css("color", "red");
							  hasNoError = false;
	                	}else{
	                		hasNoError = true;
	                	}
	                	if(!$scope.task.responsible){
		        			$scope.taskResponsibleError = 'has-error';
		        			hasNoError = false;
		        		}
		        		
		            	if(hasNoError == true){
		            		taskService.createTask($scope)
		            		$rootScope.$on('addTaskAlready', function() {
		            			
		            			var member = [];
		            			$scope.equipment = [];
		            			$scope.material = [];
		            			var selected = $.grep($scope.projectresources, function(p1, p2) {
		            				return p1.isSelected;
		            			});
		            			if($scope.projectequipments){
		            				var selectedEquipment = $.grep($scope.projectequipments, function(p1, p2) {
			            				return p1.quan;
			            			});
		            				for(var i=0;i<selectedEquipment.length;i++){
			            				var equipmentId = selectedEquipment[i].id;
			            				var clientId = selectedEquipment[i].clientId;
			            				$scope.equipment.push({projectId: $scope.projectId, taskId: $scope.idTask, equipmentId: selectedEquipment[i].equipmentId, quantity: selectedEquipment[i].quan, clientId: selectedEquipment[i].clientId});
			            			}
			            			
			            			
		            			}
		            			if($scope.projectmaterials){
		            				var selectedMaterial = $.grep($scope.projectmaterials, function(p1, p2) {
			            				return p1.quan;
			            			});
		            				for(var i=0;i<selectedMaterial.length;i++){
			            				var materialId = selectedMaterial[i].id;
			            				var clientId = selectedMaterial[i].clientId;
			            				$scope.material.push({projectId: $scope.projectId, taskId: $scope.idTask, projectMaterialId: selectedMaterial[i].materialId, quantity: selectedMaterial[i].quan, clientId: selectedMaterial[i].clientId});
			            			}
		            			}
		            			
		            			var member1=[];
		            			for(var i=0;i<selected.length;i++){
		            				var sum = 0;
		            				var resourceId = selected[i].resourceId;
		            				var clientId = selected[i].clientId;
		            				for (var k = 0; k<$scope.tasks.length;k++){
		            					for (var j = 0; j<$scope.taskresources.length;j++){
		            						var startOldTask = new Date($scope.tasks[k].startDate);
		            						var endOldTask = new Date ($scope.tasks[k].endDate);
		            						while(startOldTask <= endOldTask){
		            							var sOldTask = startOldTask.getDate() + "/" + (startOldTask.getMonth() + 1) + "/" + startOldTask.getFullYear() ;
		            							if (sOldTask == startTask && selected[i].id == $scope.taskresources[j].resourceId){
		            								sum++;
		            								if(sum == 1){
		        	            						if($window.confirm($scope.taskresources[j].resourceName + ' was assigned in other task. Accept?')){
		        	            							var span="glyphicon glyphicon-exclamation-sign";
		        	            							if (!selected[i].quan){
		        	            								selected[i].quan = 1;
		        	            							}
		        	            							if(selected[i].emailOfResponsible != null){
		        	            								listMail +=  selected[i].emailOfResponsible + ',';
		        	            							}
		        	            							
		        	            							member1.push({projectId: $scope.projectId, resourceId: resourceId, salary: selected[i].salary, quantity: selected[i].quan, warning:span, taskId: $scope.idTask, clientId: selected[i].clientId});
		        	            							taskService.createTaskResources1(member1);
		        	            							selected[i].isSelected = false;
		        	            							member1=[];
		        	            						}else{
		        	            							selected[i].isSelected = false;
		        	            						}
		        	            					}
		            							}
		            							startOldTask = new Date(+startOldTask + dayMilliseconds);
		            						}
		            					}
		            					
		            				}
		            				if (selected[i].isSelected == true){
		            					if(selected[i].emailOfResponsible != null){
		            						listMail +=  selected[i].emailOfResponsible + ',';
		            					}
		            					
		            					
		            					if (!selected[i].quan){
            								selected[i].quan = 1;
            							}
		            					$scope.member.push({projectId: $scope.projectId, resourceId: resourceId, salary: selected[i].salary, quantity: selected[i].quan, taskId: $scope.idTask, clientId: selected[i].clientId});
		            				}
		            			}
		            			
		            			
		            			$scope.document.projectId= $scope.projectId;
		            			$scope.document.taskId = $scope.idTask;
		            			$scope.document.name = $scope.task.taskName;
		            			$scope.document.statusId = $scope.task.statusId;
		            			$scope.document.typeId = $scope.task.typeId;
		            			
		            			if($("#checkSendMail").is(':checked') == true){
		            				taskService.sendEmail(listMail);
		            			}
		            			taskService.createTaskResources($scope.member);
		            			if($scope.equipment){
		            				taskService.createEquipment($scope.equipment);
		            			}
		            			if($scope.material){
		            				taskService.createMaterial($scope.material);
		            			}
		            			
		            			taskService.createDocument($scope);
		            		});
		            		$scope.$on('addDocumentAlready', function() {
		            			$scope.docVer.taskId = $scope.idTask;
		            			$scope.docVer.projectId = $scope.projectId;
		            			$scope.docVer.documentId = $scope.idDoc;
		            			if ($scope.task.attachFile != null){
		            				var  string = $scope.task.attachFile.name;
		            				var len = string.length;
		            				
		            				$scope.docVer.name = string.slice(0, len-4); 
		            				$scope.docVer.statusId = $scope.task.statusId;
		            				var file = $scope.task.attachFile;
		            				var uploadUrlD = $rootScope.makePostURL("/documentversion/upfile/" + $scope.idTask);
		            				var fd = new FormData();
		            				fd.append('file', file);
		            				
		            				
		            				$http.post(uploadUrlD, fd, {
		            					
		            					headers : {
		            						'Content-Type' : undefined
		            					}
		            				}).then(function() {
		            					taskService.createDocumentVersion($scope,true);
		            				});
		            			} else {
		            				$mdDialog.hide();
			            		    $window.location.href = $window.location;
		            			}
		            		});
		            		$scope.$on('addDocumentVersionAlready', function() {
		            			$window.location.reload();
		            		});
		            		
		            	}
		            	taskService.getTasksOnePrject($scope);
		            }
				}
			});
			$mdDialog.show(addTaskPopup);
			
		});
	};
	
	$rootScope.$on('getTaskDetailDialog', function(ev, args) {
		var task = args;
		task.id = args.id;
		$scope.gotoTaskDetailPage(task);
	});
	
	$rootScope.$on('updateTaskForGantt', function(ev, task){
		$scope.taskId = task.id;
		$scope.task = task;
		taskService.updateTask($scope);
	});
	$scope.gotoTaskDetailPage = function(object){
		$scope.taskId = object.id;
		$scope.task = object;
		$scope.task.comment = '';
		$scope.task.startDate = new Date($scope.task.startDate);
		var listMail = "";
		$scope.updatematerial = {};
		var updateActualWorkForMember ={};
		$scope.member = [];
		$scope.material = [];
		$scope.equipment = [];
		$scope.taskresourcetracking  = [];
		$scope.taskequipmenttracking  = {};
		$scope.taskmaterialtracking  = {};
		$templateRequest("/redsun/static/partials/task-tabs-edit.html").then(function(html){
			$compile(html)($scope);
			$scope.pageTitle = "Edit task";
			$scope.task.projectId = $scope.projectId;
			var addTaskPopup = null;
				addTaskPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		              addTaskPopup = null;
		            }
		        	$scope.isTrashTask = function(){
		        		if($window.confirm('Do you want to delete task?') == true){
		        			$scope.task.isTrash = '1';
		        			taskService.updateTask($scope);
		        			$mdDialog.hide();
		        			$window.location.reload();
		        		}
		        	};
		        $scope.saveTask = function(attachFile){
		            	var hasNoError = true;
		            	$scope.task.endDate = null;
		            	
	                	if(!$scope.task.taskName){
		        			$scope.taskNameError = 'has-error';
		        			hasNoError = false;
		        		}
		        		
		        		if(!$scope.task.estimateTime){
		        			$scope.taskDurationError = 'has-error';
		        			hasNoError = false;
		        		}
		            	var startDateProject = new Date($scope.project.startDate);
		        		var startProject = startDateProject.getDate() + "/" + (startDateProject.getMonth()+1) +  "/" + startDateProject.getFullYear()  ;
		        		var count = 1;
		        		var dayMilliseconds = 1000*60*60*24;
		        		var start = new Date($scope.task.startDate);
		        		var startTask = start.getDate() + "/" + (start.getMonth() + 1) + "/" + start.getFullYear() ;
		        		if($scope.task.endDate == null && $scope.task.estimateTime > 0 && $scope.project.workSunday == '0'){
	                		while (count <= $scope.task.estimateTime){
		                		var day = start.getDay();
		                		if(day != 0){
		                			count = count +1;
		                		}
		                		start = new Date(+start + dayMilliseconds);
		                	}
	                		$scope.task.endDate =  new Date(+start - dayMilliseconds);
	                	}
		        		if(!$scope.task.startDate || (new Date(startTask,  "dd/mm/yyyy").getTime() < new Date(startProject,  "dd/mm/yyyy").getTime())){
	                		$("#startDate").text("Start Date must after Start Date: "+ startProject+ " in Project");
							  $("#startDate").css("color", "red");
							  hasNoError = false;
	                	}
	                	
		        		if($scope.task.estimateTime > 0 && $scope.project.workSunday == '1'){
	                		while (count <= $scope.task.estimateTime){
		                		var day = start.getDay();
		                			count = count +1;
		                		start = new Date(+start + dayMilliseconds);
		                	}
	                		$scope.task.endDate =  new Date(+start - dayMilliseconds);
	                	}
	                	
		            	if(hasNoError == true){
		            		$scope.docVer = {};
	                		if($scope.task.attachFileNew != null){
	                			//insert HR
				            	var selected = $.grep($scope.projectresourcesfiltered, function(p1, p2) {
				            		return p1.isSelected;
				            	});
				            	var member1=[];
				            	listMail = "";
		            			for(var i=0;i<selected.length;i++){
		            				var sum = 0;
		            				var resourceId = selected[i].id;
		            				var clientId = selected[i].clientId;
		            				for (var k = 0; k<$scope.tasks.length;k++){
		            					for (var j = 0; j<$scope.taskresources.length;j++){
		            						var startOldTask = new Date($scope.tasks[k].startDate);
		            						var endOldTask = new Date ($scope.tasks[k].endDate);
		            						while(startOldTask <= endOldTask){
		            							var sOldTask = startOldTask.getDate() + "/" + (startOldTask.getMonth() + 1) + "/" + startOldTask.getFullYear() ;
		            							var check = false;
		            							if (sOldTask == startTask && selected[i].id == $scope.taskresources[j].resourceId  && $scope.tasks[k].id ==   $scope.taskresources[j].taskId){
		            								sum++;
		            								if(sum == 1){
		        	            						if(check == false  && $window.confirm($scope.taskresources[j].resourceName + ' was assigned in other task. Accept?')){
		        	            							
		        	            							var span="glyphicon glyphicon-exclamation-sign";
		        	            							if (!selected[i].quan){
		        	            								selected[i].quan = 1;
		        	            							}
		        	            							member1.push({projectId: $scope.projectId, resourceId: resourceId, salary: selected[i].salary, quantity: selected[i].quan, warning:span, taskId: $scope.taskId, clientId: selected[i].clientId});
		        	            							if($scope.taskresources[j].emailOfAssignee !== 'null'){
		        	            								listMail +=  $scope.taskresources[j].emailOfAssignee + ',';
		        	            							}
		        	            							
		        	            							taskService.createTaskResources1(member1);
		        	            							selected[i].isSelected = false;
		        	            							member1=[];
				            								check = true;

		        	            						}else{
		        	            							selected[i].isSelected = false;
		        	            						}
		        	            					}
		            							}
		            							startOldTask = new Date(+startOldTask + dayMilliseconds);
		            						}
		            						
		            					}
		            					
		            				}
		            				if (selected[i].isSelected == true){
		            					if(selected[i].emailOfResponsible != null){
		            						listMail +=  selected[i].emailOfResponsible + ',';
		            					}
            							
            							if (!selected[i].quan){
            								selected[i].quan = 1;
            							}
            							$scope.member.push({projectId: $scope.projectId, resourceId: selected[i].resourceId, salary: selected[i].salary, quantity: selected[i].quan, taskId: $scope.taskId, clientId: selected[i].clientId});
            						}
		            			}
	                			var file = $scope.task.attachFileNew;
			                	var uploadUrl = $rootScope.makePostURL("/documentversion/upfile/"+$scope.task.id);
	                			var fd = new FormData();
			                	fd.append('file', file);
			                	
			                	$http.post(uploadUrl, fd, {
			               
				                	headers : {
				                	'Content-Type' : undefined
				                	}
				                	}).then(function() {
				                		$scope.docVer.taskId = $scope.task.id;
						            	$scope.docVer.projectId = $scope.projectId;
						            	if($scope.documents){
						            		$scope.docVer.documentId = $scope.documents.id;
						            	}
						            	
						            	var  string = $scope.task.attachFileNew.name;
						            	var len = string.length;
						            	
						            	$scope.docVer.name = string.slice(0, len-4); 
						            	$scope.docVer.statusId = $scope.task.statusId;
				                		taskService.createDocumentVersion($scope, false);

				                		$scope.task.actuallyTime += $scope.task.actualWork;
				                		
				                		taskService.updateTask($scope);
					            		
				                		//---------------HR------------------
						            	//update task_resource
				                	
				                		if($scope.assignments){
				                			for(var i = 0; i < $scope.assignments.length;i++){
						                		$scope.updateActualWorkForMember =  $scope.assignments[i];
						                		if($scope.updateActualWorkForMember.actual){
						                			$scope.updateActualWorkForMember.actualWork ? $scope.updateActualWorkForMember.actualWork: $scope.updateActualWorkForMember.actualWork = 0;
							                		$scope.updateActualWorkForMember.actualWork += $scope.updateActualWorkForMember.actual;
							                		taskService.updateActualWorkForMember($scope.updateActualWorkForMember);
							                		// one by one resource add into task_resource_tracking
							                		if($scope.assignments[i].coefficient != undefined)
							                			$scope.assignments[i].coefficient = $scope.assignments[i].coefficient.replace(/,/, ".");
													$scope.taskresourcetracking.push({taskId: $scope.assignments[i].taskId, projectId: $scope.assignments[i].projectId, actualTime: $scope.updateActualWorkForMember.actual, overTime: $scope.assignments[i].overtime, coefficient: $scope.assignments[i].coefficient , quantity: $scope.assignments[i].quantity, resourceId:  $scope.assignments[i].resourceId, salary: $scope.assignments[i].salary});
													if($scope.updateActualWorkForMember.emailOfAssignee!= null){
														listMail +=  $scope.updateActualWorkForMember.emailOfAssignee + ',';
													}
						                		}
						                		
						                	}
				                		}
					                	
					                	if($scope.taskresourcetracking){
					                		//insert  task resource tracking
						                	for (var j=0;j<$scope.taskresourcetracking.length;j++){
			    								taskService.createResourceTracking($scope.taskresourcetracking[j]);
											}
					                	}
					                	
					                	//insert HR
						            	var selected = $.grep($scope.projectresourcesfiltered, function(p1, p2) {
						            		return p1.isSelected;
						            	});
						            	var member1=[];
				            			for(var i=0;i<selected.length;i++){
				            				var sum = 0;
				            				var resourceId = selected[i].id;
				            				var clientId = selected[i].clientId;
				            				for (var k = 0; k<$scope.tasks.length;k++){
				            				for (var j = 0; j<$scope.taskresources.length;j++){
				            						var startOldTask = new Date($scope.tasks[k].startDate);
				            						var endOldTask = new Date ($scope.tasks[k].endDate);
				            						while(startOldTask <= endOldTask){
				            							var sOldTask = startOldTask.getDate() + "/" + (startOldTask.getMonth() + 1) + "/" + startOldTask.getFullYear() ;
				            							var check = false;
				            							if (sOldTask == startTask && selected[i].id == $scope.taskresources[j].resourceId  && $scope.tasks[k].id ==   $scope.taskresources[j].taskId){
				            								sum++;
				            								if(sum == 1){
				        	            						if(check == false  && $window.confirm($scope.taskresources[j].resourceName + ' was assigned in other task. Accept?') == true){
				        	            							
				        	            							var span="glyphicon glyphicon-exclamation-sign";
				        	            							if (!selected[i].quan){
				        	            								selected[i].quan = 1;
				        	            							}
				        	            							if($scope.taskresources[j].emailOfAssignee!= null){
				        	            								listMail +=  $scope.taskresources[j].emailOfAssignee + ',';
				        	            							}
				        											
				        	            							member1.push({projectId: $scope.projectId, resourceId: resourceId, salary: selected[i].salary, quantity: selected[i].quan, warning:span, taskId: $scope.taskId, clientId: selected[i].clientId});
				        	            							taskService.createTaskResources1(member1);
				        	            							selected[i].isSelected = false;
				        	            							member1=[];
						            								check = true;

				        	            						}else if ($window.confirm($scope.taskresources[j].resourceName + ' was assigned in other task. Accept?') == false){
				        	            							selected[i].isSelected = false;
				        	            						}
				        	            					}
				            							}
				            							startOldTask = new Date(+startOldTask + dayMilliseconds);
				            						}
				            						
				            					}
				            					
				            				}
				            				if (selected[i].isSelected == true){
				            					if(selected[i].emailOfResponsible != null){
				            						listMail +=  selected[i].emailOfResponsible + ',';
				            					}
		            							
		            							
		            							if (!selected[i].quan){
		            								selected[i].quan = 1;
		            							}
		            							$scope.member.push({projectId: $scope.projectId, resourceId: selected[i].resourceId, salary: selected[i].salary, quantity: selected[i].quan, taskId: $scope.taskId, clientId: selected[i].clientId});
		            						}
				            			}
					            		//delete HR
						            	var selectedTaskResource = $.grep($scope.assignments, function(p1, p2) {
						            		return p1.isSelected;
						            	});
						            	for(var s = 0; s < selectedTaskResource.length; s++){
						            		taskService.deleteTaskResources(selectedTaskResource[s]);
						            	}

						            	//---------------equipment------------------
						            	if($scope.projectequipments){
						            		//insert equipments
						            		var selectedEquipment = $.grep($scope.projectequipments, function(p1, p2) {
							            		return p1.quan;
							            	});
							            	for(var i=0;i<selectedEquipment.length;i++){
							            		var equipmentId = selectedEquipment[i].id;
							            		var clientId = selectedEquipment[i].clientId;
							            		$scope.equipment.push({projectId: $scope.projectId, taskId: $scope.taskId, equipmentId: selectedEquipment[i].equipmentId, quantity: selectedEquipment[i].quan, clientId: selectedEquipment[i].clientId});
							            	}
							            	//update quantity
							            	for (var e= 0; e< $scope.taskequipments.length;e++){
							            		$scope.updateActualWorkEquipment =  $scope.taskequipments[e];
							            		if($scope.updateActualWorkEquipment.actual){
						                			$scope.updateActualWorkEquipment.actualWork ? $scope.updateActualWorkEquipment.actualWork: $scope.updateActualWorkEquipment.actualWork = 0;
							                		$scope.updateActualWorkEquipment.actualWork += $scope.updateActualWorkEquipment.actual;
							                		taskService.updateQuantityTE($scope.updateActualWorkEquipment);
							                		//insert into tracking
							                		$scope.updateActualWorkEquipment.actualTime = $scope.updateActualWorkEquipment.actual;
							            			$scope.updateActualWorkEquipment.owner = $scope.task.ownerId;
							            			taskService.createEquipmentTracking($scope.updateActualWorkEquipment);
						                		}
							            	}
							            	//delete Equipment
							            	var selectedTaskEquipment = $.grep($scope.taskequipments, function(p1, p2) {
							            		return p1.isSelected;
							            	});
							            	for(var e = 0; e < selectedTaskEquipment.length; e++){
							            		taskService.deleteTaskEquipment(selectedTaskEquipment[e]);
							            	}
						            	}
						            	
						            	//---------------material------------------
						            	if($scope.projectmaterials){
						            		//insert materials
							            	var selectedMaterial = $.grep($scope.projectmaterials, function(p1, p2) {
							            		return p1.quan;
							            	});
							            		
							            	for(var i=0;i<selectedMaterial.length;i++){
							            		var materialId = selectedMaterial[i].id;
							            		var clientId = selectedMaterial[i].clientId;
							            		$scope.material.push({projectId: $scope.projectId, taskId: $scope.taskId, projectMaterialId: selectedMaterial[i].materialId, quantity: selectedMaterial[i].quan, clientId: selectedMaterial[i].clientId});
							            	}
							            	//update  planning
							            	for (var m = 0; m < $scope.taskmaterials.length;m++){
							            		taskService.updateQuantityTM($scope.taskmaterials[m]);
							            	}
							            	
							            	for(var i=0;i<$scope.taskmaterials.length;i++){
							            		$scope.updatematerial = $scope.taskmaterials[i];
							            		if($scope.updatematerial.actual){
							            			var materialId = $scope.updatematerial.id;
							            			$scope.updatematerial.actualQuantity = $scope.updatematerial.actual + $scope.updatematerial.actualQuantity;
							            			taskService.updateMaterial($scope.updatematerial);
							            			
							            			$scope.taskmaterialtracking = $scope.taskmaterials[i];
							            			$scope.taskmaterialtracking.actualMaterial = $scope.taskmaterialtracking.actual;
							            			$scope.taskmaterialtracking.materialId = $scope.taskmaterialtracking.projectMaterialId;
							            			taskService.createMaterialTracking($scope.taskmaterialtracking);
							            		}
						            			
						            		}
							            	//delete material
							            	var selectedTaskMaterial = $.grep($scope.taskmaterials, function(p1, p2) {
							            		return p1.isDeleted;
							            	});
							            	for(var m = 0; m < selectedTaskMaterial.length; m++){
							            		taskService.deleteTaskMaterial(selectedTaskMaterial[m]);
							            	}
						            	}
						            	
						            	
						            	
						            	if(listMail){
						            		taskService.sendEmail(listMail);
						            	}
						            	taskService.createTaskResources($scope.member);
						            	if($scope.equipment){
						            		taskService.createEquipment($scope.equipment);
						            	}
						            	if($scope.material){
						            		taskService.createMaterial($scope.material);
						            	}
						            	
						            	if($mdDialog.hide()){
					            			$window.location.href = $window.location;
					            		}
				                	
				                	});
	                		} else {
		                		
		                		$scope.task.actuallyTime += $scope.task.actualWork;
		                		taskService.updateTask($scope);
			            		
		                		//---------------HR------------------
				            	//update task_resource
		                	
		                		if($scope.assignments){
		                			for(var i = 0; i < $scope.assignments.length;i++){
				                		$scope.updateActualWorkForMember =  $scope.assignments[i];
				                		if($scope.updateActualWorkForMember.actual){
				                			$scope.updateActualWorkForMember.actualWork ? $scope.updateActualWorkForMember.actualWork: $scope.updateActualWorkForMember.actualWork = 0;
					                		$scope.updateActualWorkForMember.actualWork += $scope.updateActualWorkForMember.actual;
					                		taskService.updateActualWorkForMember($scope.updateActualWorkForMember);
					                		// one by one resource add into task_resource_tracking
					                		if($scope.assignments[i].coefficient != undefined)
					                			$scope.assignments[i].coefficient = $scope.assignments[i].coefficient.replace(/,/, ".");
											$scope.taskresourcetracking.push({taskId: $scope.assignments[i].taskId, projectId: $scope.assignments[i].projectId, actualTime: $scope.updateActualWorkForMember.actual, overTime: $scope.assignments[i].overtime, coefficient: $scope.assignments[i].coefficient , quantity: $scope.assignments[i].quantity, resourceId:  $scope.assignments[i].resourceId, salary: $scope.assignments[i].salary});
											if($scope.updateActualWorkForMember.emailOfAssignee!= null){
												listMail +=  $scope.updateActualWorkForMember.emailOfAssignee + ',';
											}
				                		}
				                		
				                	}
		                		}
			                	
			                	if($scope.taskresourcetracking){
			                		//insert  task resource tracking
				                	for (var j=0;j<$scope.taskresourcetracking.length;j++){
	    								taskService.createResourceTracking($scope.taskresourcetracking[j]);
								}
			                	}
			                	
			                	//insert HR
				            	var selected = $.grep($scope.projectresourcesfiltered, function(p1, p2) {
				            		return p1.isSelected;
				            	});
				            	var member1=[];
		            			for(var i=0;i<selected.length;i++){
		            				var sum = 0;
		            				var resourceId = selected[i].id;
		            				var clientId = selected[i].clientId;
		            				for (var k = 0; k<$scope.tasks.length;k++){
		            				for (var j = 0; j<$scope.taskresources.length;j++){
		            						var startOldTask = new Date($scope.tasks[k].startDate);
		            						var endOldTask = new Date ($scope.tasks[k].endDate);
		            						while(startOldTask <= endOldTask){
		            							var sOldTask = startOldTask.getDate() + "/" + (startOldTask.getMonth() + 1) + "/" + startOldTask.getFullYear() ;
		            							var check = false;
		            							if (sOldTask == startTask && selected[i].id == $scope.taskresources[j].resourceId  && $scope.tasks[k].id ==   $scope.taskresources[j].taskId){
		            								sum++;
		            								if(sum == 1){
		        	            						if(check == false  && $window.confirm($scope.taskresources[j].resourceName + ' was assigned in other task. Accept?') == true){
		        	            							
		        	            							var span="glyphicon glyphicon-exclamation-sign";
		        	            							if (!selected[i].quan){
		        	            								selected[i].quan = 1;
		        	            							}
		        	            							if($scope.taskresources[j].emailOfAssignee!= null){
		        	            								listMail +=  $scope.taskresources[j].emailOfAssignee + ',';
		        	            							}
		        											
		        	            							member1.push({projectId: $scope.projectId, resourceId: resourceId, salary: selected[i].salary, quantity: selected[i].quan, warning:span, taskId: $scope.taskId, clientId: selected[i].clientId});
		        	            							taskService.createTaskResources1(member1);
		        	            							selected[i].isSelected = false;
		        	            							member1=[];
				            								check = true;

		        	            						}else if ($window.confirm($scope.taskresources[j].resourceName + ' was assigned in other task. Accept?') == false){
		        	            							selected[i].isSelected = false;
		        	            						}
		        	            					}
		            							}
		            							startOldTask = new Date(+startOldTask + dayMilliseconds);
		            						}
		            						
		            					}
		            					
		            				}
		            				if (selected[i].isSelected == true){
		            					if(selected[i].emailOfResponsible !== 'null'){
		            						listMail +=  selected[i].emailOfResponsible + ',';
		            					}
            							
            							
            							if (!selected[i].quan){
            								selected[i].quan = 1;
            							}
            							$scope.member.push({projectId: $scope.projectId, resourceId: selected[i].resourceId, salary: selected[i].salary, quantity: selected[i].quan, taskId: $scope.taskId, clientId: selected[i].clientId});
            						}
		            			}
			            		//delete HR
				            	var selectedTaskResource = $.grep($scope.assignments, function(p1, p2) {
				            		return p1.isSelected;
				            	});
				            	for(var s = 0; s < selectedTaskResource.length; s++){
				            		taskService.deleteTaskResources(selectedTaskResource[s]);
				            	}

				     //---------------equipment------------------
				            	if($scope.projectequipments){
				            		//insert equipments
				            		var selectedEquipment = $.grep($scope.projectequipments, function(p1, p2) {
					            		return p1.quan;
					            	});
					            	for(var i=0;i<selectedEquipment.length;i++){
					            		var equipmentId = selectedEquipment[i].id;
					            		var clientId = selectedEquipment[i].clientId;
					            		$scope.equipment.push({projectId: $scope.projectId, taskId: $scope.taskId, equipmentId: selectedEquipment[i].equipmentId, quantity: selectedEquipment[i].quan, clientId: selectedEquipment[i].clientId});
					            	}
					            	//update quantity
					            	for (var e= 0; e< $scope.taskequipments.length;e++){
					            		$scope.updateActualWorkEquipment =  $scope.taskequipments[e];
					            		if($scope.updateActualWorkEquipment.actual){
				                			$scope.updateActualWorkEquipment.actualWork ? $scope.updateActualWorkEquipment.actualWork: $scope.updateActualWorkEquipment.actualWork = 0;
					                		$scope.updateActualWorkEquipment.actualWork += $scope.updateActualWorkEquipment.actual;
					                		taskService.updateQuantityTE($scope.updateActualWorkEquipment);
					                		//insert into tracking
					                		$scope.updateActualWorkEquipment.actualTime = $scope.updateActualWorkEquipment.actual;
					            			$scope.updateActualWorkEquipment.owner = $scope.task.ownerId;
					            			taskService.createEquipmentTracking($scope.updateActualWorkEquipment);
				                		}
					            	}
					            	//delete Equipment
					            	var selectedTaskEquipment = $.grep($scope.taskequipments, function(p1, p2) {
					            		return p1.isSelected;
					            	});
					            	for(var e = 0; e < selectedTaskEquipment.length; e++){
					            		taskService.deleteTaskEquipment(selectedTaskEquipment[e]);
					            	}
				            	}
				            	
				     //---------------material------------------
				            	if($scope.projectmaterials){
				            		//insert materials
					            	var selectedMaterial = $.grep($scope.projectmaterials, function(p1, p2) {
					            		return p1.quan;
					            	});
					            		
					            	for(var i=0;i<selectedMaterial.length;i++){
					            		var materialId = selectedMaterial[i].id;
					            		var clientId = selectedMaterial[i].clientId;
					            		$scope.material.push({projectId: $scope.projectId, taskId: $scope.taskId, projectMaterialId: selectedMaterial[i].materialId, quantity: selectedMaterial[i].quan, clientId: selectedMaterial[i].clientId});
					            	}
					            	//update  planning
					            	for (var m = 0; m < $scope.taskmaterials.length;m++){
					            		taskService.updateQuantityTM($scope.taskmaterials[m]);
					            	}
					            	
					            	for(var i=0;i<$scope.taskmaterials.length;i++){
				            			
				            			$scope.updatematerial = $scope.taskmaterials[i];
				            			if($scope.updatematerial.actual) {
				            				var materialId = $scope.updatematerial.id;
					            			$scope.updatematerial.actualQuantity = $scope.updatematerial.actual + $scope.updatematerial.actualQuantity;
					            			taskService.updateMaterial($scope.updatematerial);
					            			
					            			$scope.taskmaterialtracking = $scope.taskmaterials[i];
					            			$scope.taskmaterialtracking.actualMaterial = $scope.taskmaterialtracking.actual;
					            			$scope.taskmaterialtracking.materialId = $scope.taskmaterialtracking.projectMaterialId;
					            			taskService.createMaterialTracking($scope.taskmaterialtracking);
				            			}
				            			
				            		}
					            	//delete material
					            	var selectedTaskMaterial = $.grep($scope.taskmaterials, function(p1, p2) {
					            		return p1.isDeleted;
					            	});
					            	for(var m = 0; m < selectedTaskMaterial.length; m++){
					            		taskService.deleteTaskMaterial(selectedTaskMaterial[m]);
					            	}
				            	}
				            	
				            	
				            	taskService.createTaskResources($scope.member);
				            	if($scope.equipment){
				            		taskService.createEquipment($scope.equipment);
				            	}
				            	if($scope.material){
				            		taskService.createMaterial($scope.material);
				            	}
				            	if(listMail){
				            		taskService.sendEmailUpdateTask(listMail);
				            	}
				            	
				            	if($mdDialog.hide()){
			            			$window.location.href = $window.location;
			            		}
		                	
		                	};
	                	
		            	}
		            }
				}
			});
			$mdDialog.show(addTaskPopup);
			
		});
	};
	$scope.isTrashAllTaskOneProject = function() {
		if($window.confirm('Delete All Task?') == true){
			for (var i = 0;i<$scope.tasks.length;i++){
				$scope.tasks[i].isTrash = '1';
				$scope.taskId = $scope.tasks[i].id;
				$scope.task = $scope.tasks[i];
				taskService.updateTask($scope);
			}
			$window.location.href = $window.location;
			}
	};
	
	// get projects
	$scope.getProjects = function() {
		taskService.getProjects($scope);
	};
	$scope.$on('getProjectsAlready', function() {
		if ($scope.data) {
			$scope.projects = $scope.data.result.projects;
		}
	});
	
	// get statuses
	$scope.getStatuses = function() {
		taskService.getStatuses($scope);
	};
	$scope.$on('getStatusAlready', function() {
		if ($scope.data) {
			$scope.statuses = $scope.data.result.statuses;
		}
	});
	
	// get priorities
	$scope.getPriorities = function() {
		taskService.getPriorities($scope);
	};
	$scope.$on('getPrioritiesAlready', function() {
		if ($scope.data) {
			$scope.priorities = $scope.data.result.priorities;
			$scope.task.priorityId = 1;
		}
	});
	// getProjectResources
	$scope.getProjectResources = function() {
		taskService.getProjectResources($scope);
	};


	$scope.$on('getProjectResourcesAlready', function() {
		if ($scope.data) {
			$scope.projectresources = $scope.data.result.projectresources;
		}
	});
	// get resource
	$scope.getResources = function() {
		taskService.getResources($scope);
	};
	$scope.$on('getResourcesAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
	});
	// get types
	$scope.getTypes = function() {
		taskService.getTypes($scope);
	};
	$scope.$on('getTypesAlready', function() {
		if ($scope.data) {
			$scope.types = $scope.data.result.types;
		}
	});
	// Download Documents.
	$scope.downloadDocuments = function(doc) {
		$scope.name = doc.name;
		$scope.id = doc.id;
		var ex = doc.link;
		ex = ex.substring(ex.lastIndexOf("."), ex.length);
		$scope.name = $scope.name + ex;
		taskService.downloadDocuments($scope)
		// success.
		.success(function(response, status, headers, config) {
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', doc.name + ex);
			downloadLink[0].click();
		})
		// error.
		.error(function(response, status, headers, config) {
			//$scope.showMessage('Error!', 'alert-danger', true);
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', doc.name + ex);
			downloadLink[0].click();
		});
	}
	// delete documents.
	$scope.deleteDocument = function(doc) {
		$scope.document = doc;
		if($window.confirm(msgContent) == true){
			taskService.deleteDocument($scope)
				// success.
				.success(function(response, status, headers, config) {
					$scope.$broadcast('deleteDocument', {done: true});	
				})
				// error.
				.error(function(response, status, headers, config) {
					$scope.$broadcast('deleteDocument', {done: false});
				});
		}
	}
	
	$scope.$on('deleteDocument', function(event, agrs) {
		if(agrs.done) {
			$scope.getDocumentVersionByTask()
		}
	});
	setLang = function(langKey) {
        // You can change the language during runtime
        $translate.use(langKey).then(function () {
        	msgTitle = $translate.instant('doc.file.msg.title');
        	msgContent = $translate.instant('doc.file.msg.content');
        	msgOk = $translate.instant('doc.file.msg.ok');
        	msgCancel = $translate.instant('doc.file.msg.cancel');
        	msgDelSuccess = $translate.instant('doc.file.msg.content.delete.folder.msg.success');
    		msgDelFail = $translate.instant('doc.file.msg.content.delete.folder.msg.fail');
    		msgDelErr = $translate.instant('doc.file.msg.content.delete.folder.msg.err');
        });
    }

}]);