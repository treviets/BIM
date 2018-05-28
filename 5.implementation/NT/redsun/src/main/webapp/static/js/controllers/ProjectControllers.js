app.controller('projectCtrl',['$scope', '$compile', '$rootScope', '$mdDialog', '$http', '$templateRequest', '$timeout','$log',
                      		'$window', 'projectService', 'memberService', 'moduleService', function($scope, $compile, $rootScope, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, projectService, memberService, moduleService) {
	
	$scope.initProject = function(isDesignManagement){
		$rootScope.isDesignManagement = isDesignManagement;
		$window.localStorage['isDesignManagement'] = isDesignManagement;
		$scope.project = {};
		$scope.getProjects();
		$scope.permission = {};
		$scope.module_permission_key = "";
		$scope.alreadyInitGantt = false;
	}
	
	$scope.getProjectRoles = function(){
		moduleService.getModulePermission($scope);
	};
	$scope.$on('getProjectRolesAlready', function() {
		if ($scope.data) {
			$scope.projectRoles = $scope.data;
			
		}
	});
	
	$scope.getProjects = function() {
		projectService.getProjects($scope);
	};
	$scope.$on('getProjectsAlready', function() {
		if ($scope.data) {
			$scope.projects = $scope.data.result.projects;
		}
	});
	// get resource
	$scope.getResources = function() {
		projectService.getResources($scope);
	};
	$scope.$on('getResourcesAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
	});
	// get types
	$scope.getTypes = function() {
		projectService.getTypes($scope);
	};
	$scope.$on('getTypesAlready', function() {
		if ($scope.data) {
			$scope.types = $scope.data.result.types;
		}
	});
	
	// get workers
	$scope.getWorkers = function() {
		projectService.getWorkers($scope);
	};
	$scope.$on('getWorkerAlready', function() {
		if ($scope.data) {
			$scope.workers = $scope.data.result.workers;
		}
	});
	
	// get customers
	$scope.getCustomers = function() {
		projectService.getCustomers($scope);
	};
	$scope.$on('getCustomersAlready', function() {
		if ($scope.data) {
			$scope.clients = $scope.data.result.clients;
		}
	});
	// get all project
	$scope.getAllProject = function() {
		projectService.getAllProject($scope);
	};
	$scope.$on('getAllAlready', function() {
		if ($scope.data) {
			$scope.projects = $scope.data.result.projects;
		}
	});
	
	// get projects for parent
	$scope.getParentsProject = function() {
		projectService.getParentsProject($scope);
	};
	$scope.$on('getParentsProjectAlready', function() {
		if ($scope.data) {
			$scope.projects = $scope.data.result.projects;
		}
	});
	// get filter resource
	$scope.getFilterMember = function() {
		projectService.getFilterMember($scope);
	};
	$scope.$on('getFilterMemberAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
	});
	// get filter equipments
	$scope.getFilterEquipment = function() {
		projectService.getFilterEquipment($scope);
	};
	$scope.$on('getFilterEquipmentAlready', function() {
		if ($scope.data) {
			$scope.equipments = $scope.data.result.equipments;
		}
	});
	// get filter materials
	$scope.getFilterMaterial = function() {
		projectService.getFilterMaterial($scope);
	};
	$scope.$on('getFilterMaterialAlready', function() {
		if ($scope.data) {
			$scope.materials = $scope.data.result.materials;
		}
	});

	// get equipment
	$scope.getEquipments = function() {
		projectService.getEquipments($scope);
	};
	$scope.$on('getEquipmentsAlready', function() {
		if ($scope.data) {
			$scope.equipments = $scope.data.result.equipments;
		}
	});
	

	// get project equipment
	$scope.getProjectEquipments = function() {
		projectService.getProjectEquipments($scope);
	};
	$scope.$on('getProjectEquipmentsAlready', function() {
		if ($scope.data) {
			$scope.projectequipments = $scope.data.result.projectequipments;
		}
	});
	// get project permission
	$scope.$on('getModuleRoleAlready', function(){
		if($scope.data){
			var permission = {};
			var data = $scope.data;
			for(var i = 0; i< data.length; i++){
				var item = data[i].moduleProperty.item
				for (var property in data[i]) {
					if (data[i].hasOwnProperty(property) && property !="$$hashKey") {
						permission[property] = data[i][property];
					}
				}
				permission[item] = data[i].permission; 
				
				
			}
			$scope.permission = {
					projectRole: permission
			};
			
		}
	});

	// get project material
	$scope.getProjectMaterials = function() {
		projectService.getProjectMaterials($scope);
	};
	$scope.$on('getProjectMaterialsAlready', function() {
		if ($scope.data) {
			$scope.projectmaterials = $scope.data.result.projectmaterials;
		}
	});
	
	// get material
	$scope.getMaterials = function() {
		projectService.getMaterials($scope);
	};
	$scope.$on('getMaterialsAlready', function() {
		if ($scope.data) {
			$scope.materials = $scope.data.result.materials;
		}
	});
	//get Roles
	$scope.getRoles = function() {
		projectService.getRoles($scope);
	};
	$scope.$on('getRolesAlready', function() {
		if ($scope.data) {
			$scope.roles = $scope.data.result.roles;
		}
	});
	
	//get Project_resources
	$scope.getProjectResources = function() {
		projectService.getProjectResources($scope);
	};
	$scope.$on('getProjectResourcesAlready', function() {
		if ($scope.data) {
			$scope.projectresources = $scope.data.result.projectresources;
		}
	});
	
	
	$scope.addProject = function(){
		$scope.project.statusId = 38;
		$scope.project.typeId = 48;
		$templateRequest("/redsun/static/partials/project-tabs-add.html").then(function(html){
			$compile(html)($scope);
			var addProjectPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveProject = function(){
		            	var hasNoError = true;
		            	for(var i=0; i<$scope.projects.length; i++){
		            		if($scope.project.code == $scope.projects[i].code){
		            			hasNoError = false;
		            			break;
		            		}
		            	}
		            	if(!$scope.project.name){
		        			$scope.projectNameError = 'has-error';
		        			hasNoError = false;
		        		}
		            	if(!$scope.project.code){
		        			$scope.projectCodeError = 'has-error';
		        			hasNoError = false;
		        		}
		            	if(!$scope.project.customerId){
		        			$scope.projectCustomerError = 'has-error';
		        			hasNoError = false;
		        		}
		            	var start = new Date($scope.project.startDate);
	                	var dayMilliseconds = 1000*60*60*24;
	                	var weekendDay = 0;
	                	var count = 1;
	                	var duration = $scope.project.duration * dayMilliseconds;
	                	if($("#checkSunday").is(':checked') == false){
	                	while (count <= $scope.project.duration){
	                		var day = start.getDay();
	                		if(day != 0){
	                			count = count +1;
	                		}
	                		start = new Date(+start + dayMilliseconds);
	                	}
	                	}
	                	if($("#checkSunday").is(':checked') == true){
	                		$scope.project.workSunday = '1';
	                		while (count <= $scope.project.duration){
		                		var day = start.getDay();
		                			count = count + 1;
		                		start = new Date(+ start + dayMilliseconds);
		                	}
	                	}
	                	$scope.project.endDate = new Date(+ start - dayMilliseconds);
	                	
	                	if(hasNoError == true){
			            	projectService.createProject($scope);
			            	$mdDialog.hide();
			            	
		            	}
	                	else
	                		alert('project code exist');
		            };
		            $scope.$on("addProjectAlready", function(){
		            	$scope.member = [];
		            	$scope.equipment = [];
		            	$scope.material = [];
		            	var selected = $.grep($scope.resources, function(p1, p2) {
		            		return p1.isSelected;
		            	});
		            	if($scope.equipments){
		            		var selectedEquipment = $.grep($scope.equipments, function(p1, p2) {
			            		return p1.isSelected;
			            	});
			            	for(var i=0;i<selectedEquipment.length;i++){
			            		var equipmentId = selectedEquipment[i].id;
			            		var clientId = selectedEquipment[i].clientId;
			            		$scope.equipment.push({projectId: $scope.proId, equipmentId: selectedEquipment[i].id, quantity: selectedEquipment[i].quan, netPrice: selectedEquipment[i].netPrice, price: selectedEquipment[i].price, clientId: selectedEquipment[i].clientId});
			            	}
		            	}
		            	
		            	if($scope.materials){
		            		var selectedMaterial = $.grep($scope.materials, function(p1, p2) {
			            		return p1.isSelected;
			            	});
			            	for(var i=0;i<selectedMaterial.length;i++){
			            		var materialId = selectedMaterial[i].id;
			            		var clientId = selectedMaterial[i].clientId;
			            		$scope.material.push({projectId: $scope.proId, materialId: selectedMaterial[i].id, quantity: selectedMaterial[i].quan, netPrice: selectedMaterial[i].netPrice, price: selectedMaterial[i].price, clientId: selectedMaterial[i].clientId});
			            	}
		            	}
		            	
		            	for(var i=0;i<selected.length;i++){
		            		var resourceId = selected[i].id;
		            		var clientId = selected[i].clientId;
		            		if (selected[i].quantity == null){
		            			selected[i].quantity = 1;
		            		}
		            		$scope.member.push({projectId: $scope.proId, resourceId: selected[i].id, quantity: selected[i].quantity, clientId: selected[i].clientId});
		            	}
		            	projectService.createMember($scope.member);
		            	if($scope.equipment){
		            		projectService.createEquipment($scope.equipment);
		            	}
		            	if($scope.material){
		            		projectService.createMaterial($scope.material);
		            	}
		            	
		            	$mdDialog.hide();
		            	projectService.getProjects($scope);
		            });
				}
			});
			$mdDialog.show(addProjectPopup);

		}); 
	};
	
	$scope.$on('getProjectsAlready', function() {
		if ($scope.data) {
		$scope.projects = $scope.data.result.projects;
		}
	});

	// get statuses
	$scope.getStatuses = function() {
		projectService.getStatuses($scope);
	};
	$scope.$on('getStatusAlready', function() {
		if ($scope.data) {
			$scope.statuses = $scope.data.result.statuses;
		}
	});
	// Init for edit.
	$scope.initEditProject = function(id) {
		$scope.projectId = id;
		$scope.module_permission_key = "project_" + id; 
		$scope.project = {};
		//permission
		if (id == null || id > 0) {
			projectService.getStatuses($scope);
			projectService.getTypes($scope);
			projectService.getCustomers($scope);
			projectService.getByIdProject($scope);
			projectService.getProjectGanttData($scope);
			// get permission
			moduleService.getModuleRole($scope, $scope.module_permission_key)
		}
		
		
	};
	$scope.getByIdProject = function(id) {
		projectService.getByIdProject($scope);
	}
	$scope.editProject = function(){
		$scope.member = [];
    	$scope.equipment = [];
    	$scope.material = [];
		$scope.pageTitle = 'Edit project';
		$templateRequest("/redsun/static/partials/project-tabs-edit.html").then(function(html){
			$compile(html)($scope);
			var addProjectPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveProject = function(){
		            	var hasError, hasNoError = true;
		            	if(!$scope.project.name){
		        			$scope.projectNameError = 'has-error';
		        			hasNoError = false;
		        		}
		            	if(!$scope.project.code){
		        			$scope.projectCodeError = 'has-error';
		        			hasNoError = false;
		        		}
		            	if(!$scope.project.customerId){
		        			$scope.projectCustomerError = 'has-error';
		        			hasNoError = false;
		        		}
		            	if(hasNoError == true){
		            		//if( $window.confirm('Do you want to edit project?') == true){
		            	projectService.updateProject($scope);
		            	 $mdDialog.hide();
		            	if($scope.projectequipments){
		            		//edit PE
			            	for (var e = 0; e < $scope.projectequipments.length; e++){
		            			projectService.updateQuantityPE($scope.projectequipments[e]);
		            		}
			            	//delete PE
			            	var selectedProjectEquipment = $.grep($scope.projectequipments, function(p1, p2) {
			            		return p1.isSelected;
			            	});
			            	for(var s = 0; s < selectedProjectEquipment.length; s++){
		            			projectService.deleteProjectEquipments(selectedProjectEquipment[s]);
	            			}
			            	var selectedEquipment = $.grep($scope.equipments, function(p1, p2) {
			            		return p1.quantity;
			            	});
			            	for(var i=0;i<selectedEquipment.length;i++){
			            		var equipmentId = selectedEquipment[i].id;
			            		var clientId = selectedEquipment[i].clientId;
			            		if (!selectedEquipment[i].quantity){
			            			alert("Quantity not empty!");
			            			hasError = false;
			            		}
			            		if (selectedEquipment[i].quantity != null){
			            			$scope.equipment.push({projectId: $scope.projectId, equipmentId: selectedEquipment[i].id,  netPrice: selectedEquipment[i].netPrice, price: selectedEquipment[i].price, quantity: selectedEquipment[i].quantity, netPrice: selectedEquipment[i].netPrice, price: selectedEquipment[i].price, clientId: selectedEquipment[i].clientId});
			            		}
			            	}
		            	}
		            	
		            	if($scope.projectresources){
		            		//edit HR
			            	for (var p = 0;p<$scope.projectresources.length;p++){
		            			projectService.updateQuantityPHR($scope.projectresources[p]);
		            		}
			            	//delete HR
			            	var selectedProjectResource = $.grep($scope.projectresources, function(p1, p2) {
			            		return p1.isSelected;
			            	});
			            	for(var s = 0; s < selectedProjectResource.length; s++){
			            		projectService.deleteProjectResources(selectedProjectResource[s]);
			            	}
		            	}
		            	
		            	if($scope.projectmaterials){
		            		//edit PM
			            	for (var m = 0; m < $scope.projectmaterials.length; m++){
		            			projectService.updateQuantityPM($scope.projectmaterials[m]);
		            		}
			            	//delete PM
			            	var selectedProjectMaterial = $.grep($scope.projectmaterials, function(p1, p2) {
			            		return p1.isSelected;
			            	});
		            		for(var m = 0; m < selectedProjectMaterial.length; m++){
		            			projectService.deleteProjectMaterials(selectedProjectMaterial[m]);
	            			}
		            		var selectedMaterial = $.grep($scope.materials, function(p1, p2) {
			            		return p1.quan;
			            	});
			            	for(var i=0;i<selectedMaterial.length;i++){
			            		var materialId = selectedMaterial[i].id;
			            		var clientId = selectedMaterial[i].clientId;
			            		if (selectedMaterial[i].quan == null){
			            			alert("Quantity not empty!");
			            			hasError = false;
			            		}
			            		if (selectedMaterial[i].quan != null){
			            			$scope.material.push({projectId: $scope.projectId, materialId: selectedMaterial[i].id,  netPrice: selectedMaterial[i].netPrice, price: selectedMaterial[i].price,quantity: selectedMaterial[i].quan,  netPrice: selectedMaterial[i].netPrice, price: selectedMaterial[i].price, clientId: selectedMaterial[i].clientId});
			            		}
			            	}
		            	}
		            	$scope.selectAllHR = function(){
		            		  for (var i = 0; i < $scope.resources.length; i++) {
		            		    $scope.resources[i].isSelected = true;
		            		  }
	            		};
		            	var selected = $.grep($scope.resources, function(p1, p2) {
		            		return p1.isSelected;
		            	});
		            	for(var i=0;i<selected.length;i++){
		            		var resourceId = selected[i].id;
		            		var clientId = selected[i].clientId;
		            		if (!selected[i].quan){
		            			selected[i].quan = 1;
		            		}
		            		$scope.member.push({projectId: $scope.projectId, resourceId: selected[i].id, salary: selected[i].salary, quantity: selected[i].quan, clientId: selected[i].clientId});
		            	}
		            	
		            		projectService.createMember($scope.member);
		            		projectService.createEquipment($scope.equipment);
		            		projectService.createMaterial($scope.material);
		            		 $mdDialog.hide();
		            //}
		            }
				}
		           
		            }
			});
			$mdDialog.show(addProjectPopup);
			
		});    
	};
	
	// goto detail page
	$scope.gotoDetailPageProject = function(object) {
		$scope.module_permission_key = "project_" + object.project.id; 
		$window.localStorage['seletedProjectId'] = object.project.id;
		$scope.getProjectRoles();
		var isDesignManagement = $window.localStorage['isDesignManagement'];
		if(isDesignManagement !== 'true'){
			$window.location.href = $rootScope.makeGetURL("/project/detail/" + object.project.id);
		} else {
			$window.location.href = $rootScope.makeGetURL("/design/detail/" + object.project.id);
		}
		
		
		
	};
	
	
	// goto BPMN Detail page
	$scope.gotoDetailBPMNDetail = function(object) {
		$window.localStorage['seletedProjectId'] = object.project.id;
		$window.location.href = $rootScope.makeGetURL("/bpmn/" + object.project.id);
	}
	
	// goto design process
	$scope.navToDesignProcess = function(object) {
		$window.localStorage['seletedProjectId'] = object.project.id;
		$window.location.href = $rootScope.makeGetURL("/design/" + object.project.id);
		
	};
	
	$scope.$on('getProjectGanttChartDataAlready', function() {
		
		var opts = [];
		if($scope.taskData){
			angular.forEach($scope.taskData.priority, function(valueObject, key) {
				this.push({key:valueObject.id , label:valueObject.name});
			}, opts);
		}
		
		
		var optsUser = [];
		angular.forEach($scope.taskData.users, function(valueObject, key) {
			this.push({key:valueObject.id , label:valueObject.name});
		}, optsUser);
		
		redsun.config.popup.sections = [
	          {name:"Assignee",    height:38, map_to:"assignee", type:"select", options:optsUser},
	          {name:"Priority",    height:38, map_to:"priority", type:"select", options:opts},
	          {name:"description", height:88, map_to:"text", type:"textarea", focus:true},
	          {name:"time",        height:72, map_to:"auto", type:"duration"}
	    ];
		redsun.locale.labels.section_Assignee = "Assignee";
		redsun.locale.labels.section_Priority = "Priority";
		
		
		
		redsun.config.work_time = true;


		redsun.config.scale_unit = "day";
		redsun.config.date_scale = "%D, %d";
		redsun.config.min_column_width = 60;
		redsun.config.duration_unit = "day";
		redsun.config.scale_height = 20*3;
		redsun.config.row_height = 30;



		var weekScaleTemplate = function(date){
			var dateToStr = redsun.date.date_to_str("%d %M");
			var weekNum = redsun.date.date_to_str("(week %W)");
			var endDate = redsun.date.add(redsun.date.add(date, 1, "week"), -1, "day");
			return dateToStr(date) + " - " + dateToStr(endDate) + " " + weekNum(date);
		};

		redsun.config.subscales = [
			{unit:"month", step:1, date:"%F, %Y"},
			{unit:"week", step:1, template:weekScaleTemplate}

		];

		redsun.templates.task_cell_class = function(task, date){
			if(!redsun.isWorkTime(date))
				return "week_end";
			return "";
		};

		redsun.templates.task_text=function(start,end,task){
		    return "<b>Text:</b> "+task.text+", <b>Completed:</b>"+Math.round(task.progress*100)+"%";
		};			
		redsun.init("ganttChart");
		redsun.parse($scope.taskData);
		//just register event once time while initilize
		if(!$scope.alreadyInitGantt){
			$scope.alreadyInitGantt = true;
			redsun.attachEvent("onTaskCreated", function(task){
				if(task.parent){
					var parentTask = redsun.getTask(task.parent);
					task.parentId = parentTask.wbs;
				}
				
				$rootScope.$broadcast('showTaskDialog',task);
			});
			
			redsun.attachEvent("onTaskDblClick", function(taskId, mode){
				var task = redsun.getTask(taskId);
				$rootScope.$broadcast('getTaskDetailDialog',task);
				return false;
			});
			redsun.attachEvent("onAfterTaskDrag", function(taskId, index, parent ){
				var task = redsun.getTask(taskId);
				task.completed = task.progress*100;
				task.startDate = task.startDateForGantt;
				task.endDate = task.end_date;
				task.estimateTime = task.duration;
				//update task here
				$rootScope.$broadcast('updateTaskForGantt',task);
			});
			
			$rootScope.$on('addTaskAlready', function(){
				projectService.getProjectGanttData($scope);
			});
			$rootScope.$on('updateTaskAlready', function(){
				projectService.getProjectGanttData($scope);
			});
		}
		
	});

	
	
	// Navigate to url.
	$scope.gotoUrl = function(url) {
		$window.location.href = url;
	}
	
	
	// Import member from file.
	$scope.fileResourceSelected = function(element) {
		$scope.fileResource = element.files[0];
	}
	$scope.importResource = function() {
		if(!$scope.fileResource) {
			alert('Choose a file');
		} else {
			$scope.importResourceFile = new FormData();
			$scope.importResourceFile.append('file', $scope.fileResource);
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/hr/import-resource") + "&filePath=null&projectId="+$scope.projectId,
	            data: $scope.importResourceFile,
	            headers: {
	                'Content-Type': undefined
	            }
	        };			
			
			$http(request)
			.success(function(response) {
				alert('Done');
			})
			.error(function(response) {
				alert('Failed');
			});
			
		}
	}
	
	// Import equipment from file.
	$scope.fileEquipmentSelected = function(element) {
		$scope.fileEquipment = element.files[0];
	}
	$scope.importEquipment = function() {
		if(!$scope.fileEquipment) {
			alert('Choose a file');
		} else {
			$scope.importEquipmentFile = new FormData();
			$scope.importEquipmentFile.append('file', $scope.fileEquipment);
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/equipment/import-equipment") + "&filePath=null&projectId="+$scope.projectId,
	            data: $scope.importEquipmentFile,
	            headers: {
	                'Content-Type': undefined
	            }
	        };			
			
			$http(request)
			.success(function(response) {
				alert('Done');
			})
			.error(function(response) {
				alert('Failed');
			});
			
		}
	}
	
	// Import material from file.
	$scope.fileMaterialSelected = function(element) {
		$scope.fileMaterial = element.files[0];
	}
	$scope.importMaterial = function() {
		if(!$scope.fileMaterial) {
			alert('Choose a file');
		} else {
			$scope.importMaterialFile = new FormData();
			$scope.importMaterialFile.append('file', $scope.fileMaterial);
			var request = {
	            method: 'POST',
	            url: $rootScope.makePostURL("/material/import-material") + "&filePath=null&projectId="+$scope.projectId,
	            data: $scope.importMaterialFile,
	            headers: {
	                'Content-Type': undefined
	            }
	        };			
			
			$http(request)
			.success(function(response) {
				alert('Done');
			})
			.error(function(response) {
				alert('Failed');
			});
			
		}
	}

	$scope.updateProjectPermission = function(username, moduleRoleId){
		var selectRoleId = -1;
		if(moduleRoleId > -1){
			selectRoleId = $rootScope.projectresources[moduleRoleId].projectRoleId;
		}
		
		$scope.moduleRole = {
				username: username,
				modulePermission:{
					id: selectRoleId,
					key: $scope.module_permission_key
				}
		}
		moduleService.updateModuleRole($scope);
	}
	
	$scope.$on('updateModuleRoleComplete', function() {
		if ($scope.data) {
			if(parseInt($scope.data)>0){
			}
			$window.location.reload();
			
		}
	});
	// delete Project
	$scope.deleteProject= function(id) {
			if($window.confirm('Do you want to delete Project?') == true){			
				if(projectService.deleteProject($scope)){
					$window.location.href = $rootScope.makeGetURL("/project");
			}
		}
	};
	
	$scope.baselineForm = function() {
		// Show a dialog.
		$templateRequest("/redsun/static/partials/baseline.html").then(function(html){
			
			$compile(html);
			var fromPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
					
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            
				}
			});
			$mdDialog.show(fromPopup).then(function() {
			}, function() {
				//getDirectory();
			});
		});
		
	}
}]);




	

	