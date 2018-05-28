app.controller('TabController', ['$rootScope', '$scope', '$http', '$window', '$templateRequest', '$compile',  '$mdDialog', '$timeout', 'phaseItemService', function($rootScope, $scope, $http, $window, $templateRequest, $compile,  $mdDialog, $timeout, phaseItemService) {

	// Get selected project Id 	
	$scope.initProjectId = function() {
		$scope.projectId = $window.localStorage['seletedProjectId'];
		$rootScope.projectId = $window.localStorage['seletedProjectId'];
	};
	
	// Initialize BPMN area
	$scope.initProjectBPMNArea = function() {
		$scope.projectId = $window.localStorage['seletedProjectId'];
		$rootScope.projectId = $window.localStorage['seletedProjectId'];
		
		$scope.hasData = false;
		$rootScope.hasSubProcessLevel2 = false;
		$rootScope.hasSubProcessLevel3 = false;
		
		$scope.getAllPhase();	
	};
	
	// Initializing Define BPMN process
	$scope.initProcessUsing = function() {
		$rootScope.hasProcess = false;
	};
	
	
    $scope.error = false;
    $scope.sucess = false;
    $scope.isCurrentStep = false;
    
    // All data from database
    $scope.expandData = null;
    $scope.expandSubData = [];
    $scope.expandSubDataLevel2 = [];
    $scope.activeStep = 0;
    $scope.activeSubStep = 0;
    $scope.resource = [];
    
    
    $scope.actionType = "";
    $scope.currentStepId = "";
    
    $scope.emailList = "";
    $scope.previousEmailList = "";
    $scope.nextEmailList = "";
    
    $scope.subDataStepCurrent = {};
    $scope.submitOrReject = false;
    
    $scope.rejectReason =  "";
	$scope.rejectDescription = "";
	
    // Required document for next step
    $scope.requireDocument = [];
    
    // Uploaded document in each of step    
    $scope.uploadedDocument = [];
    
    // All document for project
    $scope.allDocument = [];
    
    // Validate for next step, isNextStep = true. Can next step    
    $scope.isNextStep = false;
	
    $scope.message = "";
    
    $scope.projectdocuments = [];
    
    // Get projects for binding phase
	$scope.getProjects = function() {
		phaseItemService.getProjects($scope);
	};
	
	// Get Step Id which is selected
	$scope.testFunction = function(stepId) {
		$rootScope.stepId = stepId;
	};
	
	// When all of project have already
	$scope.$on('getProjectsAlready', function() {
		if ($scope.data) {
			$scope.projects = $scope.data.result.projects;
		}
	});
	
	// Get Resource for binding step
	$scope.getProjectResource = function() {
		phaseItemService.getProjectResources($scope, $rootScope);
	};
	// When all of resource belonging project have already
	$scope.$on('getProjectResourcesAlready', function() {
		if ($scope.data) {
			$scope.projectresources = $scope.data.result.projectresources;
			$scope.data = null;
			phaseItemService.getSelectedResources($scope, $rootScope);
		}
	});
	
	// When all of selected resource belonging project have already
	$scope.$on('getSelectedResourcesAlready', function() {
		if ($scope.data) {
			$scope.resourcesSelected = [];
			$scope.resourceAvailables = $scope.projectresources;
			
			$scope.selectedresources = $scope.data.result["resource-step"];
			$scope.data = null;
			for (var i = $scope.resourceAvailables.length-1; i >=0; i--) {
				var resourceObject = $scope.resourceAvailables[i];
				for (var j = 0; j < $scope.selectedresources.length; j++) {
					if (resourceObject.resourceCode === $scope.selectedresources[j].resourceCode) {
						$scope.resourcesSelected.push(resourceObject);
						$scope.projectresources.splice(i,1);
						break;
					}
				}
			}
		}
	});
	
	// Get all document for project
	$scope.getUploadedDocument = function(){
		phaseItemService.getDocuments($scope, $scope.projectId).success(function(data) {
			$scope.allDocument = data.result["phase-document"];
			$scope.uploadedDocument = [];
			angular.forEach($scope.allDocument, function(value, key) {
				if($scope.expandData && (value.activeStep === $scope.expandData.activeStep)) {
					$scope.uploadedDocument.push(value.documentName);
				}
			});
			
			$scope.checkDeleteFileAction();
	    });
	};
		
	// Show Resource Dialog for assigning resource to project
	$scope.showResourceDialog = function() {
		$templateRequest("/redsun/static/partials/resource-dialog.html").then(function(html){
			$compile(html)($scope);
			$scope.getProjectResource();
			
			var oldFilePopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialogAttachFile = function() {
		              $mdDialog.hide(oldFilePopup);
		              $mdDialog.show(addTaskPopup);
		            }
		            $scope.closeResourceDialog = function() {
			          $mdDialog.hide();
		            }
				}
			});
			
			$mdDialog.show(oldFilePopup);
		});
	};
	
	// Show Document Dialog for assigning checklist document for project
	$scope.showDocumentDialog = function() {
		$templateRequest("/redsun/static/partials/document-dialog.html").then(function(html){
			$compile(html)($scope);
			var oldFilePopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialogAttachFile = function() {
		              $mdDialog.hide(oldFilePopup);
		              $mdDialog.show(addTaskPopup);
		            }
		            $scope.closeDocumentDialog = function() {
			          $mdDialog.hide();
		            }
		            
				}
			});
			$mdDialog.show(oldFilePopup);
		});
	};
	
	
	
	// Add Resource member for step
	$scope.addResourceForStep = function(objResource) {
		objResource.projectId = $scope.projectId;
		objResource.stepId = $rootScope.stepId;
		
		var postURL = $rootScope.makePostURL("/design/resource");
	    
	    $.ajax({
            type: 'POST',
        	url: postURL,
        	data: JSON.stringify(objResource),
        	contentType: "application/json",
        	success: function (result) {
        		$scope.resourcesSelected.push(objResource);
        		for (var i = 0; i < $scope.projectresources.length; i++){
        			var resource = $scope.projectresources[i];
        			if(resource.resourceCode === objResource.resourceCode){
        				$scope.projectresources.splice(i,1);
        				break;
        			}
        		}
        		$scope.$apply();
        	},
        	error: function (error) {
        		console.log("Error in creating resource");
        	}
		  });
	};
	
	// Remove Resource member for step
	$scope.removeResourceForStep = function(objResource) {
		var objDelete = {};
		objDelete.projectId = $scope.projectId;
		objDelete.stepId = $rootScope.stepId;
		objDelete.resourceCode = objResource.resourceCode;
		
	    var postURL = $rootScope.makePostURL("/design/resource/delete");
	    $.ajax({
            type: 'POST',
        	url: postURL,
        	data: JSON.stringify(objDelete),
        	contentType: "application/json",
        	success: function (result) {
        		for (var i = 0; i < $scope.resourcesSelected.length; i++){
        			var resource = $scope.resourcesSelected[i];
        			if(resource.resourceCode === objResource.resourceCode){
        				$scope.resourcesSelected.splice(i,1);
        				$scope.projectresources.push(objResource);
        				break;
        			}
        		}
        		$scope.$apply();
        	},
        	error: function (error) {
        		console.log("Error in deleting resource");
        	}
		});
	};
	
	// Get Project document for binding step
	$scope.getProjectDocument = function() {
		$scope.documentName = "";
		$scope.documentDescription = "";
		phaseItemService.getProjectDocuments($scope);
	};
	
	$scope.$on('getProjectDocumentAlready', function() {
		if ($scope.data) {
			$scope.projectdocuments = $scope.data.result['document-step'];
		}
	});
	
	// Create Document for each Step
	$scope.createProjectDocument = function() {
		var objDocument = {};
		objDocument.idProject = $scope.projectId;
		objDocument.idStep = $rootScope.stepId;
		objDocument.documentName = $scope.documentName;
	    objDocument.documentDescription = $scope.documentDescription;
		
	    var CSRF_HEADER = $('meta[name="_csrf_header"]').attr('content');
		var CSRF_TOKEN = $('meta[name="_csrf_token"]').attr('content');
	    var postURL = "/redsun/design/document"+ "?_csrf_header=" + CSRF_HEADER + "&_csrf=" + CSRF_TOKEN + "&format=xml";
	    
	    $.ajax({
            type: 'POST',
        	url: postURL,
        	data: JSON.stringify(objDocument),
        	contentType: "application/json",
        	success: function (result) {
        		$scope.projectdocuments.push(objDocument);
        		$scope.$apply();
        	},
        	error: function (error) {
        		console.log("Error in creating document");
        	}
		  });
	};
	
	// Delete document of step
	$scope.deleteDocumentStep = function(document) {
		$scope.objDeleteDocument = document;
		
		phaseItemService.deleteDocument($scope);
	};
	// When document remomved
	$scope.$on('deleteDocumentForStepSuccessfully', function() {
		if ($scope.data) {
			phaseItemService.getProjectDocuments($scope);
		}
	});
	
	// Get all phase for Process
	$scope.getAllPhase = function() {
		phaseItemService.getAllById($scope);
	};

	$scope.$on('getPhaseItemAlready', function() {
		if ($scope.data) {
			if ($scope.data.result['phase-item'].length > 0) {
				$scope.hasData = true;
				$rootScope.hasProcess = true;
			}
			var dataObject = $scope.data.result['phase-item'][0];
			if(dataObject){
				$scope.expandData = angular.fromJson(dataObject);
				$scope.dataSet = angular.fromJson(dataObject.contain);
				var activeSteps = $scope.expandData.activeStep.split("_");
				var activeStepLevel1, activeStepLevel2, activeStepLevel3;
				
				if (activeSteps && activeSteps.length > 0) {
					$scope.expandData.activeStepLevel1 = parseInt(activeSteps[0]);
				}
				if (activeSteps && activeSteps.length > 1) {
					$scope.expandData.activeStepLevel2 = parseInt(activeSteps[1]);
				}
				
				if (activeSteps && activeSteps.length > 2) {
					$scope.expandData.activeStepLevel3 =  parseInt(activeSteps[2]);
				}
				
				// Set requirement for this step
	    		$scope.requireDocument = $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].document;
	    		
	    		// Set resource for this step
	    		$scope.resource = $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].resource;
	    		
		    	$scope.expandSubData = [];
		    	$scope.expandSubDataLevel2 = [];
		    	
		    	if ($scope.expandData.activeStepLevel1 && $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps) { // Has sub process
		    		// Display Sub process slide
		    		$rootScope.hasSubProcessLevel2 = true;
		    		
		    		// Sub data for level 2
		    		$scope.expandSubData = $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps
		    		
		    		if($scope.expandData.activeStepLevel2 && $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1]) {
		    			$scope.subDataActive = $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1];
		    			// Set requirement for this step
			    		$scope.requireDocument = $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1].document;
			    		
			    		// Set resource for this step
			    		$scope.resource = $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1].resource;

		    		}

		    		// Check is ActiveStepLevel3 !== null => has sub process level 3
		    		if($scope.expandData.activeStepLevel3 && $scope.expandSubData[$scope.expandData.activeStepLevel2  - 1].subSteps) {
		    			$rootScope.hasSubProcessLevel3 = true;
		    			
		    			$scope.expandSubDataLevel2 = $scope.expandSubData[$scope.expandData.activeStepLevel2  - 1].subSteps
		    			
		    			$scope.subDataActive = $scope.expandSubData[$scope.expandData.activeStepLevel3 - 1];
		    			// Set requirement for this step
			    		$scope.requireDocument = $scope.expandSubData[$scope.expandData.activeStepLevel3 - 1].document;
			    		
			    		// Set resource for this step
			    		$scope.resource = $scope.expandSubData[$scope.expandData.activeStepLevel3 - 1].resource;   		
		    		}
		    	} 
				
			}
		}
		
		$scope.getUploadedDocument();
		$scope.checkSubmitAndRejectAction();
	});
	
	/**
	 * Validate Submit and Reject action based on the user name
	 * If logging user name existing in assigned resource => Show Submit and Reject button
	 * If admin role logged into => Show Submit and Reject button
	 */
	$scope.checkSubmitAndRejectAction = function() {
		$scope.submitOrReject = false;
		var adminUser = "admin";
		if(currentUsername === adminUser){
			$scope.submitOrReject = true;
			return;
		}
		if($scope.resource && $scope.resource.length > 0){
			//check if current user name is in the list of resource
			angular.forEach($scope.resource, function(value, key) {
				if(value.resourceCode === currentUsername) {
					$scope.submitOrReject = true;
    					return;
				}
			});
		}
	};
	
	$scope.getCurrentStepId = function() {
		var dataLevel1 = $scope.dataSet[$scope.expandData.activeStepLevel1 -1];
		var currentStepId = dataLevel1.idStep;
		
		if (dataLevel1.subSteps) {
			var dataLevel2 = dataLevel1.subSteps;
			currentStepId = $scope.expandSubData[$scope.expandData.activeStepLevel2 -1].idStep;
			
			if(dataLevel2[$scope.expandData.activeStepLevel2 -1].subSteps) {
				currentStepId = $scope.expandSubDataLevel2[$scope.expandData.activeStepLevel3 -1].idStep;
			}
		}
		
		return currentStepId;
	};
	
    // Get the next step	
    $scope.submitForNextStep = function() {
    	$scope.message = "";
    	$scope.currentStepId = $scope.getCurrentStepId();
    	$scope.actionType = "submit";
    	
    	$scope.validateDocument();
    	var isNext = $scope.isNextStep;
    	if (isNext === true) {
    		if ($scope.expandSubData.length === 0) { // Process is level 1
    			$scope.expandData.activeStepLevel1 += 1;
    			$scope.activeMainStep();
    		}
    		else { 
    			// Process level 3
    			if ($scope.expandSubDataLevel2 && $scope.expandSubDataLevel2.length !== 0) {
    				var lengthOfSubData = $scope.expandSubDataLevel2.length;
    				var indexOfSubStep = $scope.expandData.activeStepLevel3;
        	   
        			if (indexOfSubStep < lengthOfSubData) {
        				
            			// Sub Data activated in UI for binding document
            			$scope.subDataStepCurrent = $scope.expandSubDataLevel2[indexOfSubStep - 1];
            			$scope.subDataForStep = $scope.expandSubDataLevel2[indexOfSubStep];
        	        
        	        	// Update step to Data base
            			$scope.expandData.activeStepLevel3 += 1;
        	        	$scope.activeMainStep();    	
        	    	} else {
        	    		
        	    		if(indexOfSubStep === lengthOfSubData && $scope.expandData.activeStepLevel2 >= $scope.dataSet[$scope.expandData.activeStepLevel1 -1].subSteps.length){
        	    			$scope.expandData.activeStepLevel2 = 1;
        	    			$scope.expandData.activeStepLevel1 +=1;
        	    			$rootScope.hasSubProcessLevel2 = false;
        	    		} else {
        	    			$scope.expandData.activeStepLevel2 += 1;
            	    		$scope.expandData.activeStepLevel3 = 1;
        	    		}
        	    		
        	    		
        	    		$scope.activeMainStep();
        	        }
    			} 
    			// Process is level 2
    			else if ($scope.expandSubData && $scope.expandSubData.length !== 0) {
    				var lengthOfSubData = $scope.expandSubData.length;
        	    		var indexOfSubStep = $scope.expandData.activeStepLevel2;
        	   
        			if (indexOfSubStep < lengthOfSubData) {
        				
            			// Sub Data activated in UI for binding document
            			$scope.subDataStepCurrent = $scope.expandSubData[indexOfSubStep - 1];
            			$scope.subDataForStep = $scope.expandSubData[indexOfSubStep];
        	        
        	        	// Update step to Data base
            			$scope.expandData.activeStepLevel2 += 1;
            			$scope.activeMainStep();    	
        	    	} else {
        	    		$scope.expandData.activeStepLevel1 += 1;
        	    		$scope.expandData.activeStepLevel2 = 1;
        	    		$scope.activeMainStep();
        	        }
    			} 
    			
    		}
    		
    	} else {
    		$scope.message = "Upload all required documents";
   		 	$scope.alertType = "alert-danger";
    	}
    };
    
    // Concatenating sub step to ActiveStep and saving into database
    $scope.activeMainStep = function() {
	    	if($scope.expandData.activeStepLevel1 < 1){
	    		$scope.expandData.activeStepLevel1 = 1;
	    	}
	    	if($scope.expandData.activeStepLevel2 < 1){
	    		$scope.expandData.activeStepLevel2 = 1;
	    	}
	    	if($scope.expandData.activeStepLevel3 <1){
	    		$scope.expandData.activeStepLevel3 = 1;
	    	}
		$scope.expandData.activeStep=$scope.expandData.activeStepLevel1+"_"+$scope.expandData.activeStepLevel2+"_"+$scope.expandData.activeStepLevel3;
	    	$scope.updateActiveStep($scope.expandData);	
    }
    
    // Call service to update expand data
    $scope.updateActiveStep = function(objPhase) {
	    	$scope.phaseItemUpdate = objPhase;
	    	phaseItemService.updatePhase($scope);
    }; 
    
    $scope.$on('updatePhaseAlready', function() {
		$scope.getAllPhase();
	});
   
    // Show dialog when "Submit" button get pressed
	$scope.showRejectDialog = function() {
		$templateRequest("/redsun/static/partials/reject-comment.html").then(function(html){
			$compile(html)($scope);
			var oldFilePopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:false,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeRejectDialog = function() {
			          $mdDialog.hide();
		            }
		            
				}
			});
			$mdDialog.show(oldFilePopup);
		});
	};
	
	// Reject to previous step
	$scope.rejectForPreviousStep = function() {
		$scope.message = "";
		$scope.currentStepId = $scope.getCurrentStepId();
    	$scope.actionType = "reject";
    	
    	if ($scope.expandSubData.length === 0) { // Has not Sub process
    		$scope.expandData.activeStepLevel1 -= 1;
    		if ($scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps) {
    			$scope.expandData.activeStepLevel2 = $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps?$scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps.length:1;
    		}
    		$scope.activeMainStep();    
		}
		else {
			// Has process level 3
			if ($scope.expandSubDataLevel2 && $scope.expandSubDataLevel2.length !== 0) {
				// Check it is first item => hide
				var lengthOfSubData = $scope.expandSubDataLevel2.length;
		    	var indexOfSubStep = $scope.expandData.activeStepLevel3;
		   
				if (indexOfSubStep !== 1) {
	    			// Sub Data activated in UI for binding document
	    			$scope.subDataStepCurrent = $scope.expandSubDataLevel2[indexOfSubStep - 1];
	    			$scope.subDataForStep = $scope.expandSubDataLevel2[indexOfSubStep -2];
		        
		        	// Update step to Data base
	    			$scope.expandData.activeStepLevel3 = indexOfSubStep - 1;
		        	$scope.activeMainStep();    	
		    	} else {
		    		$scope.expandData.activeStepLevel2 -= 1;
		    		if ($scope.expandSubData[$scope.expandData.activeStepLevel2 - 1] && $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1].subSteps) {
		    			$scope.expandData.activeStepLevel3 = $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1].subSteps.length;
		    		}
		    		if ($scope.expandData.activeStepLevel2 === 0) {
		    			$scope.expandData.activeStepLevel1 -= 1;
		    			$scope.expandData.activeStepLevel2 = $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps?$scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps.length:1;
		    			$rootScope.hasSubProcessLevel2 = false;
		    			$scope.expandData.activeStepLevel3 = 1;
		    		}
		    		$rootScope.hasSubProcessLevel3 = false;
		    		$scope.activeMainStep();	
		        }
			} else {
				// Has sub process
		    	var lengthOfSubData = $scope.expandSubData.length;
		    	var indexOfSubStep = $scope.expandData.activeStepLevel2;
		   
				if (indexOfSubStep !== 1) {
	    			// Sub Data activated in UI for binding document
	    			$scope.subDataStepCurrent = $scope.expandSubData[indexOfSubStep - 1];
	    			$scope.subDataForStep = $scope.expandSubData[indexOfSubStep -2];
		        
	    			// Update step to Data base
	    			$scope.expandData.activeStepLevel2 = indexOfSubStep - 1;
		        	$scope.activeMainStep();    	
		    	} else {
		    		$scope.expandData.activeStepLevel1 -= 1;
		    		if ($scope.dataSet[$scope.expandData.activeStepLevel1 - 1] && $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps) {
		    			$scope.expandData.activeStepLevel2 = $scope.dataSet[$scope.expandData.activeStepLevel1 - 1].subSteps.length;
		    			$scope.subDataStepCurrent = $scope.expandSubData[$scope.expandData.activeStepLevel2 - 1];
		    			if($scope.subDataStepCurrent && $scope.subDataStepCurrent.length > 0){
		    				$scope.expandData.activeStepLevel3 = $scope.subDataStepCurrent.length;
		    			}
		    		}
		    		$scope.activeMainStep();    		
		    		$rootScope.hasSubProcessLevel2 = false;
		        }
			}
		}
	};
	
    // Binding data for Slider in level 1
    $scope.isSetSubProcessLevel1 = function(index) {
    	if (index === $scope.expandData.activeStepLevel1) {
    		return "active";
    	} else if (index < $scope.expandData.activeStepLevel1) {
    		return "complete";
    	} else {
    		return "disabled";
    	}
    };
    
    // Binding data for Slider in level 2
    $scope.isSetSubProcessLevel2 = function(indexOfSubStep) {
    	if (indexOfSubStep === $scope.expandData.activeStepLevel2) {
    		return "active";
    	} else if (indexOfSubStep < $scope.expandData.activeStepLevel2) {
    		return "complete";
    	} else {
    		return "disabled";
    	}
    };
    
    // Binding data for Slider in level 3
    $scope.isSetSubProcessLevel3 = function(indexOfSubStep) {
    	if (indexOfSubStep === $scope.expandData.activeStepLevel3) {
    		return "active";
    	} else if (indexOfSubStep < $scope.expandData.activeStepLevel3) {
    		return "complete";
    	} else {
    		return "disabled";
    	}
    };
    
    // Back to previous step when previous button get pressed    
    $scope.previous = function(){
        var i = $scope.getIndex($scope.current.index, -1);
		$scope.tab = i + 1;
        $scope.current = $scope.dataSet[i];
    };
    
    // Check for next step. If user input enough document. It can next
    $scope.validateDocument = function() {
    	// Get upload document and required document
    	var requireDocument = $scope.prepareDocument($scope.requireDocument);
    	var uploadedDocument = $scope.prepareDocument($scope.uploadedDocument);
    	
    	if (uploadedDocument >= requireDocument) {
    		$scope.isNextStep = true;
    		
    	} else {
    		$scope.isNextStep = false;
    	}
    };
    
    // Convert Required Document array to String to compare    
    $scope.prepareDocument = function(arrDocument) {
    	return arrDocument.length;
    };
    
   
    // Convert to integer: 1, 2, 3, 4
    $scope.prepareIndex = function(index) {
    	return parseInt(index.toString().split(".")[1]);
    };
    
       
    // Submit file in design process
    $scope.submitFile = function(myFile, index) {
    	$scope.myFile = myFile;
    	$scope.myFileIndex = index;
    	var isSubmit = $scope.validateUploadFile($scope);
    	if(isSubmit) {
    		var fileName = myFile.name;
            var projectId = $scope.projectId
            var phase = $scope.expandData.activeStepLevel1;
            var subPhase = $scope.expandData.activeStepLevel2;
            var sPath = "/redsun/design/upfile/" + projectId  + "/" + phase + "/" + subPhase;
            
        	var sUrl = fileName;
            
        	 $scope.docName = fileName;
             $scope.docUrl = sUrl;
        	
        	var formData = new FormData();
        	formData.append('file', myFile);
        	
        	// Call Post method to push file to server    	
        	$http.post(sPath, formData, { headers : { 'Content-Type' : undefined }}).success(function() {
    	    		
	    		$scope.message = "Upload success";
	    		$scope.alertType = "alert-success";
	    		
	    		// Add uploaded document into database
	    		var objDocument = {};
	    		objDocument.idProject = $scope.projectId;
	    		objDocument.activeStep = $scope.expandData.activeStep
	    		objDocument.uploadBy = "";
	    		objDocument.uploadDate = new Date();
	    		objDocument.updateBy = "";
	    		objDocument.updateDate = "";
	    		
	    		objDocument.documentName = $scope.docName;
	    		objDocument.documentUrl = $scope.docUrl;
	    		
	    		$scope.createdDocument = objDocument;
	    		phaseItemService.addDocument($scope);

    			$scope.allDocument.push(objDocument);
    			$scope.uploadedDocument.push(fileName);
    	    
		    	}).error(function() {
		    		$scope.message = "Cannot upload file at this time";
		    		$scope.alertType = "alert-danger";
		    	});
    	}
    };
    
    // If this step has not resource, show error
    $scope.validateUploadFile = function($scope) {
	    var requiredName = $scope.requireDocument[$scope.myFileIndex].documentName;
	    var continueSubmit = true;
	    
	    var sSubmitFileName = $scope.myFile.name;
	    if(requiredName !== sSubmitFileName){
	    	$scope.message = "Invalid filename!";
    		$scope.alertType = "alert-danger";
    		continueSubmit = false;
	    }
	    return continueSubmit;
    };
    
    // Delete selected document
    $scope.deleteUploadedDocument = function(document) {
    	// Delete document and save into database
    	$scope.deleteDocument = document
    	phaseItemService.deleteFile($scope);
    };
    
    // When selected document was deleted
    $scope.$on('deleteDocumentSuccessfully', function() {
		if ($scope.data) {
			$scope.getUploadedDocument();
		}
	});
    
    // Set visible for Delete document button
    $scope.checkDeleteFileAction = function() {
    	$scope.currentStep = 0;
    	if($scope.expandData != null){
    		$scope.currentStep = $scope.expandData.activeStep;
    	}
    	
    };
    
    // Navigate to BPMN view
    $scope.useStandardProcess = function() {
    	var projectId = $scope.projectId;
    	$window.location.href = $rootScope.makeGetURL("/bpmn/" + projectId);
    };
    
    $scope.$on('getStandardProcessAlready', function() {
		if ($scope.data) {
			$scope.expandData = angular.fromJson($scope.data.result.process[0]);
			$scope.dataSet = angular.fromJson($scope.data.result.process[0].contain);
			$scope.activeStep = $scope.expandData.activeStepLevel1;
	    	$scope.activeSubStep = $scope.expandData.activeStepLevel2;
	    	
	    	$scope.hasData = true;
	    	$scope.data = null;
		}
	});
    
}]);
