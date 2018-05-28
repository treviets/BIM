app.controller('docDirectoryCtrl',['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                           		'$window', 'docManagementService', 'resourceService', 'moduleService', 'resourceService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, docManagementService, resourceService, moduleService, resourceService) {

	var msgTitle = '';
	var msgContent = '';
	var msgOk = '';
	var msgCancel = '';
	var msgDelSuccess = '';
	var msgDelFail = '';
	var msgDelErr = '';
	var msgSaveSuccess = '';
	var msgSaveFail = '';
	var msgAlarm = '';
	$scope.subject = 'V/v nhận tài liệu';
	$scope.content = 'Xin mời các bạn vào nhận tài liệu';
	$scope.moduleRoleKey = "document_0";
	$scope.initEditDocDirectory =  function(){
		$scope.module_permission_key = "document_0"; 
		// get role for user login
		moduleService.getModuleRole($scope);
		
	}
	// get resource
	/*
	$scope.getResources = function() {
		resourceService.getResources($scope);
	};
	$scope.$on('getResourcesAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
		// get project roles
		$scope.getProjectRoles();
	});
	*/
	getResourceAllType = function() {
		resourceService.getResourceAllType($scope);
	};
	$scope.$on('getResourceAllTypeAlready', function() {
		if ($scope.data) {
			$scope.resourceAllType = $scope.data.result.resources;
		}
		$scope.getProjectRoles();
	});
	$scope.$on('getProjectRolesAlready', function() {
		if ($scope.data) {
			$scope.projectRoles = $scope.data;
		}
	});
	getDirectory = function(){
		var lan = $location.search().lang;
		docManagementService.getDirectory($scope);
	}
	
	getResources = function() {
		resourceService.getResources($scope);
	};
	
	getResourceAssignedByDirectoryId = function(){
		docManagementService.getResourceAssignedByDirectoryId($scope);
	}
	

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
    		msgSaving = $translate.instant('doc.file.msg.content.save.folder.msg.saving');
    		msgSaveSuccess = $translate.instant('doc.file.msg.content.save.folder.msg.success');
    		msgSaveFail = $translate.instant('doc.file.msg.content.save.folder.msg.fail');
    		msgAlarm = $translate.instant('doc.folder.form.multichoice.alert.len.folder');
        });
    }
	
	$scope.$on('getDirectoryAlready', function() {
		if ($scope.data) {
			$scope.roleList = $scope.data.result.listDirectories;
			//getResourceAllType();
			var lan = $location.search().lang;
			if(lan!=undefined)
				setLang(lan);
			else setLang('en');
			if($scope.roleList.length == 0)
				$scope.tree01.currentNode = null;
		}
	});
	
	$scope.getDocument = function($event){
		var root = $scope;
        var currentScope = angular.element($event.target).scope();
		$scope.directoryId = currentScope.node.id;
		docManagementService.getDocument($scope);
	}
	$scope.$on('getDocumentAlready', function() {
		if ($scope.data) {
			$scope.docs = $scope.data.result.docs;
		}
	});
	
	$scope.$on('getResourcesAlready', function() {
		if ($scope.data) {
			$scope.resources = $scope.data.result.resources;
		}
	});
	
	$scope.$on('getResourceAssignedByIdAlready', function() {
		if ($scope.data) {
			$scope.resourceByDirectoryId = $scope.data.result.listResourceAssignedById;
		}
	});
	
	getDirectory();
	
	$scope.tableMode = "FixedResizer";
	
	$scope.projectId = $rootScope.projectId;
	$scope.documents = [];

	$scope.$watch('currentNode', function(newValue, oldValue) {
		
	});

	// Init for edit.
	$scope.initEdit = function(id) {
		
		// List projects for selection.
		$scope.listProjectsForSelection();

		$scope.createNew();
		
		$scope.directory.id = id;
		if($scope.directory.id > -1) {
			$scope.getById($scope.directory.id);
		}
		
		$scope.frmDirty = false;
	}
	
	// Show a add screen.
	$scope.add = function() {
		$scope.initEdit(-1);
		$scope.showEditDialog();
	}
	
	// Show a edit screen.
	$scope.edit = function() {
		if(!$scope.tree01.currentNode) {
			$scope.showMessage(msgAlarm, 'alert-danger', true);
			return;
		}
		$scope.initEdit($scope.tree01.currentNode.id);
	}
	
	// Show a dialog screen.
	$scope.showEditDialog = function() {
		
		// Show a dialog.
		$templateRequest("/redsun/static/partials/directory-form.html").then(function(html){
			
			$compile(html);
			
			var fromPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:true,
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
				getDirectory();
			});
		});
		
	}
	
	// Show a dialog assign screen.
	$scope.showAssignDialog = function() {
		// Show a dialog.
		$templateRequest("/redsun/static/partials/directory-assignto-resource-form.html").then(function(html){
			
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
	
	$scope.showTrashDialog = function(ev) {
	    $mdDialog.show({
	      skipHide: true,
	      controller: function DialogController($scope, $mdDialog) {
				
	            $scope.closeDialog = function() {
	              $mdDialog.hide();
	            }
	            
			},
	      templateUrl: '/redsun/static/partials/display-directory-in-trash.html',
	      targetEvent: ev,
	      clickOutsideToClose:false,
	      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
	    });
	  };
	  
	// Show a dialog folder default screen.
		$scope.showFolderDafaultDialog = function() {
			// Show a dialog.
			$templateRequest("/redsun/static/partials/directory-structure-default.html").then(function(html){
				
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
		
	// Create new.
	$scope.createNew = function() {
		$scope.directory = {id: -1, projectId: $scope.projectId}
		$scope.directory.attachments = [];
		$scope.attachments = new FormData();
		$scope.fileNames = [];
		$scope.frmDirty = false;
	}
	
	// Save.
	$scope.save = function() {
		
		if($scope.frmDirectory.$invalid) {
			$scope.frmDirectory.$dirty = true;
			$scope.frmDirty = true;
			return;
		}
		$scope.showMessageForm(msgSaving, 'alert-success', false);
		// Ge parent directory info.
		if(($scope.tree01.currentNode || $scope.tree01.currentNode != null) && $scope.directory.id !== $scope.tree01.currentNode.id) {
			var location = $scope.tree01.currentNode.name;
			if($scope.tree01.currentNode.location || $scope.tree01.currentNode.location != null) {
				location = $scope.tree01.currentNode.location + location;
			}
			location += "\\";
			$scope.directory.location = location;
			$scope.directory.parentId = $scope.tree01.currentNode.id;
			//$scope.directory.projectId = $scope.tree01.currentNode.projectId;
			//$scope.directory.clientId = $scope.tree01.currentNode.clientId;
		}
		// Update.
		if($scope.directory.id > -1) {
			docManagementService.update($scope.directory.id, $scope.directory)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1){
					$scope.directory.id = response.result.id;
					if($scope.tree01.currentNode) {
						$scope.tree01.currentNode.name = $scope.directory.name;
					}
					$scope.showMessageForm(msgSaveSuccess, 'alert-success', true);
				} else if(response && response.status === -1) {
					$scope.showMessageForm($translate.instant(response.description), 'alert-danger', true);
				} else if(response && response.status === -3) {
					$scope.showMessageForm($translate.instant(response.description), 'alert-danger', true);
				} else {
					$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
				}
			})
			// error.
			.error(function(response, status, headers, config) {
				$scope.showMessageForm(msgDelErr, 'alert-danger', true);
			});
		} else { // Insert.
			docManagementService.add($scope.directory)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1) {
					$scope.directory.id = response.result.id;
					$scope.showMessageForm(msgSaveSuccess, 'alert-success', true);
				} else if(response && response.status === -2) {
					$scope.showMessageForm($translate.instant(response.description), 'alert-danger', true);
				} else {
					$scope.showMessageForm(msgSaveFail, 'alert-danger', true);
				}
			})
			// error.
			.error(function(response, status, headers, config) {
				$scope.showMessageForm(msgDelErr, 'alert-danger', true);
			});
		}
	}
	
	$scope.moveFolderToTrash = function(){
		$scope.directoryId = $scope.tree01.currentNode.id;
		if($scope.tree01.currentNode.location == null)
			$scope.location = $scope.tree01.currentNode.name;
		else{
			$scope.location = $scope.tree01.currentNode.location.replace(/\//g, "-") + $scope.tree01.currentNode.name;
			$scope.location = $scope.location.replace(/\\/g, "-") ;
		}
		docManagementService.moveFolderToTrash($scope);
	}
	
	deleteDirectory = function(id, location){
		var result = docManagementService.delete(id, location)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1) {
				$scope.showMessage(msgDelSuccess, 'alert-success', true);
				$scope.$broadcast('deleteSuccess', {done: true});
				$scope.getsForPageAndFilter(1);
				
			} else {
				$scope.showMessage(msgDelFail, 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage(msgDelErr, 'alert-danger', true);
		});
	} 
	
	$scope.$on('deleteSuccess', function(event, agrs) {
		if (agrs.done) {
			getDirectory();
		}
	});
	
	// Get by Id.
	$scope.getById = function(id) {
		docManagementService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.directory !== undefined) {
				if(response.result.directory.length > 0) {
					$scope.directory = response.result.directory[0];
					// Show diaglog.
					$scope.showEditDialog();
				}
			} else {
				$scope.showMessage(msgDelFail, 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage(msgDelErr, 'alert-danger', true);
		});
	}
	
	// List all projects for selection.
	$scope.listProjectsForSelection = function() {
		docManagementService.listProjectsForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.projects !== undefined) {
				$scope.projects = response.result.projects;
			} else {
				$scope.showMessage(msgDelFail, 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage(msgDelErr, 'alert-danger', true);
		});
		
	}

	// Show message.
	$scope.showMessage = function(message, cssName, autoHide) {
		$scope.alertMessage = message;
		$('#alertMessage').addClass(cssName);
		$('#alertMessage').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertMessage').slideUp(500, function() {
						$('#alertMessage').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}

	// Show message.
	$scope.showMessageForm = function(message, cssName, autoHide) {
		$scope.alertMessageForm = message;
		$('#alertMessageForm').addClass(cssName);
		$('#alertMessageForm').slideDown(500, function() {
			if(autoHide) {
				$window.setTimeout(function() {
					$('#alertMessageForm').slideUp(500, function() {
						$('#alertMessageForm').removeClass(cssName);
	            	});
				}, 1000);
			}
		});
	}
	
	 //Delete folder
	$scope.btnDelete = function(event) {
		var location = $scope.tree01.currentNode.location;
		if(location == null)
			location = $scope.tree01.currentNode.name;
		else{
			location = location.replace(/\//g, "-") + $scope.tree01.currentNode.name;
			location = location.replace(/\\/g, "-");
		}
		var msgContentMoveTrash = $translate.instant('doc.file.msg.content.delete.folder');
		var confirm = $mdDialog.confirm()
			.title(msgTitle)
			.textContent(msgContentMoveTrash)
			.ariaLabel('Lucky day')
			.targetEvent(event)
			.ok(msgOk)
			.cancel(msgCancel);

		$mdDialog.show(confirm).then(function() {
			deleteDirectory($scope.tree01.currentNode.id, location);
			}, function() {
				$scope.status = 'Không thực hiện';
		});
		
	}
	
	 //move folder to trash
	$scope.moveTrash = function(event) {
		var msgContentMoveTrash = $translate.instant('doc.file.msg.content.move.folder.trash');
		var confirm = $mdDialog.confirm()
			.title(msgTitle)
			.textContent(msgContentMoveTrash)
			.ariaLabel('Lucky day')
			.targetEvent(event)
			.ok(msgOk)
			.cancel(msgCancel);

		$mdDialog.show(confirm).then(function() {
				$scope.moveFolderToTrash();
			}, function() {
				$scope.status = 'Không thực hiện';
		});
		
	}
	
	$scope.$on('moveToTrashSuccess', function() {
		if ($scope.data) {
			if($scope.data.data.status==1)
				getDirectory();
		}
	});

	////////////////////////////////////////
	// Documents.
	////////////////////////////////////////
	
	// Clear value of file element.
	$scope.fileClear = function (event) {
		$(event.target).val(null);
	};
	
	// Insert documents.
	$scope.addDocuments = function($files) {
		$scope.documentFiles = new FormData();
		angular.forEach($files, function(file, idx) {
			$scope.documentFiles.append("files", file);
		});
	}
	// Insert documents.
	$scope.saveDocuments = function() {
		if(!$scope.tree01.currentNode) {
			$scope.showMessage(msgAlarm, 'alert-danger', true);
			return;
		}
		var location = $scope.tree01.currentNode.name;
		if($scope.tree01.currentNode.location || $scope.tree01.currentNode.location != null) {
			location = $scope.tree01.currentNode.location + location;
		}
		location += "/";
		//Upload documents.
		docManagementService.uploadDocuments($scope.documentFiles, location)
		// success.
		.success(function(response, status, headers, config) {
			var iCount = 0;
			angular.forEach($scope.documentFiles, function(file, idx) {
				var document = {"name": file.name, "location": location, "projectId": $scope.projectId, "refType": "Directory", "directoryId": $scope.tree01.currentNode.id, "mimeType": file.type, "fileSize": file.size, "link": "", "documenType": 0, "documentVersionId": 1, "locked": 0, "description": "description"};
				docManagementService.addDocument(document)
				// success.
				.success(function(response, status, headers, config) {
					iCount++;
					if(iCount === $scope.documentFiles.getAll('files').length) {
						$scope.$broadcast('addDocument', {done: true});
					}
				})
				// error.
				.error(function(response, status, headers, config) {
					$scope.$broadcast('addDocument', {done: false});
				})
				;
			});
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.$broadcast('addDocument', {done: false});
		});
		
		// Upload files.
		//$scope.uploadDocuments($scope.documentFiles, location);
	}
	
	// Validate result of saving documents.
	$scope.$on('addDocument', function(event, agrs) {
		
		if(agrs.done) {
			$scope.showMessage(msgSaveSuccess, 'alert-success', true);
			getResources();
			getResourceAssignedByDirectoryId();
			sendEmailDoc();
		} else {
			$scope.showMessage(msgSaveFail, 'alert-danger', true);
		}
	});
	
	// delete documents.
	$scope.deleteDocument = function(doc) {
		$scope.document = doc;
		docManagementService.deleteDocument($scope)
			// success.
			.success(function(response, status, headers, config) {
				$scope.$broadcast('deleteDocument', {done: true});	
			})
			// error.
			.error(function(response, status, headers, config) {
				$scope.$broadcast('deleteDocument', {done: false});
			});
	}
	
	// Validate result of deleting documents.
	$scope.$on('deleteDocument', function(event, agrs) {
		if(agrs.done) {
			$scope.showMessage(msgDelSuccess, 'alert-success', true);
		} else {
			$scope.showMessage(msgDelFail, 'alert-danger', true);
		}
	});
	
    // Upload Document.
    $scope.uploadDocuments = function (documents, location) {
		docManagementService.upload(documents, location)
		// success.
		.success(function(response, status, headers, config) {
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Upload error!', 'alert-danger', true);
		});
    }
	// Download Documents.
	$scope.downloadDocuments = function(doc) {
		$scope.fileName = doc.name;
		$scope.id = doc.id;
		window.location = $rootScope.makeGetURL('/directory/download/' + $scope.id + "/" + $scope.fileName+"/");
		/*
		docManagementService.downloadDocuments($scope)
		// success.
		.success(function(response, status, headers, config) {
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', doc.name);
			downloadLink[0].click();
		})
		// error.
		.error(function(response, status, headers, config) {
			//$scope.showMessage('Error!', 'alert-danger', true);
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', fileName);
			downloadLink[0].click();
		});
		*/
	}
	
	sendEmailDoc = function(){
		var listEmail = '';
		$scope.directoryId = $scope.tree01.currentNode.id;
		if($scope.chkDoc){
			for(var i=0; i<$scope.resourceByDirectoryId.length; i++){
				for(var j=0; j<$scope.resources.length; j++)
					if($scope.resourceByDirectoryId[i].parentId == $scope.resources[j].id){
						listEmail += $scope.resources[j].email;
						if(i<$scope.resourceByDirectoryId.length-1)
							listEmail += ',';
						break;
					}
			}
			if(listEmail != ''){
				$scope.toEmail = listEmail;
				docManagementService.sendEmail($scope);
			}
		}
	}
	
	$scope.initParam = function(doc){
		$scope.chkDoc = doc;
	}
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
	}
	
	 //show confirm dialog
	$scope.showConfirm = function(event, doc) {
		var confirm = $mdDialog.confirm()
			.title(msgTitle)
			.textContent(msgContent)
			.ariaLabel('Lucky day')
			.targetEvent(event)
			.ok(msgOk)
			.cancel(msgCancel);

		$mdDialog.show(confirm).then(function() {
				$scope.deleteDocument(doc);
			}, function() {
				$scope.status = 'Không thực hiện';
		});
		
	};
	
	$scope.btnOk = function(){
		closeDialog();
	}
	
	$scope.showConfigMailDoc = function(ev) {		
		$mdDialog.show({
		      skipHide: true,
		      scope: $scope,
		      preserveScope: true,
		      controller: function DialogController($scope, $mdDialog) {
					
		            closeDialog = function() {
		              $mdDialog.hide();
		            }
		            
				},
				template: 
					 // '<div>' +	
			          '<md-dialog>' +
			          '	 <md-toolbar> ' +
			          '	   <div> ' +
			          ' 	  <span flex>{{ ' + '\'' + 'doc.file.dialog.custom.mail.title' + '\'' + '| translate}}</span>' +
			          '    </div>' +
			          '  </md-toolbar>' +
			          '  <md-dialog-content>' +
			          '    <md-input-container> ' +
			          '       <span>{{ ' + '\'' + 'doc.file.dialog.custom.mail.subject' + '\'' + '| translate}}</span> ' +
			          '       <input type="text" ng-model="subject" placeholder="{{ ' + '\'' + 'doc.file.dialog.custom.mail.title' + '\'' + '| translate}}"><br> ' +
			          '       <span>{{ ' + '\'' + 'doc.file.dialog.custom.mail.content' + '\'' + '| translate}}</span> ' +
			          ' 	  <textarea rows="4" cols="50" ng-model="content"></textarea> ' +
			          '     </md-input-container>' +
			          '  </md-dialog-content>' +
			          '  <md-dialog-actions layout="row">' +
			          '    <md-button ng-click="btnOk();" class="md-primary">' +
			          '      {{ ' + '\'' + 'doc.file.msg.ok' + '\'' + '| translate}}' +
			          '    </md-button>' +
			          '  </md-dialog-actions>' +
			          '</md-dialog>', //+
			          //'<\div>',
		      targetEvent: ev,
		      clickOutsideToClose:false,
		      fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
		    });
	  };
	  
	  $scope.showProjectRole = function(){
			//get module role by user
			moduleService.getModuleRolesByKey($scope);
			
			$scope.getModuleProperties('document');
			$templateRequest("/redsun/static/partials/document-role.html").then(function(html){
				$compile(html)($scope);
				$scope.pageTitle = "Role";
				$scope.projectRoles = [];
				$scope.congifuration = [];
				var projectRolePopup = $mdDialog.confirm({
					template : html,
					clickOutsideToClose:true,
					scope: $scope,
					preserveScope: true,  // do not forget this if use parent scope
					controller: function DialogController($scope, $mdDialog) {
			            $scope.closeDialog = function() {
			              $mdDialog.hide();
			            }
					}
				});
				$mdDialog.show(projectRolePopup);
				
			});
			
		};
		//get module permission list
		$scope.getProjectRoles = function() {
			var key = $scope.moduleRoleKey;
			$scope.module_permission_key = key;
			moduleService.getModulePermission($scope,key);
		};
		// get module properties
		$scope.getModuleProperties = function(moduleName){
			  
			moduleService.getModuleProperties($scope, moduleName);
		}
		$scope.$on('getModulePropertisAlready', function(){
			if($scope.data){
				$scope.moduleProperties = $scope.data;
				
			}
		});
		
		$scope.$on('getModulePermissionAlready', function() {
			if ($scope.data) {
				var tempData = $scope.data;
				$scope.projectRoles = [];
				
				var perName = "";
				var modulePermission = null;
				for(var i = 0; i< tempData.length; i++){
					
					// new permission
					if(tempData[i].name != perName){
						perName = tempData[i].name;
						if(modulePermission !=null){
							$scope.projectRoles.push(modulePermission);
						}
						
						modulePermission = {};
						modulePermission.id = tempData[i].id;
						modulePermission.name = tempData[i].name;
						modulePermission.key = tempData[i].key;
						modulePermission[tempData[i].moduleProperty.item] = tempData[i].permission;
					}else{
						modulePermission[tempData[i].moduleProperty.item] = tempData[i].permission;
					}
				}
				// add last object
				if(modulePermission !=null){
					$scope.projectRoles.push(modulePermission);
				}
			}
		});
		
		// add new role to project role
		$scope.addProjectRole = function(){
			var role = {};
			for(var i =0; i < $scope.moduleProperties.length; i++){
				role[$scope.moduleProperties[i].item] ='';
			}
			
			$scope.projectRoles.push(role);
		}
		
		// save project role
		$scope.saveProjectRole = function(projectRole){
			var modulePermissionList = [];
			
			for (var property in projectRole) {
				
				if (projectRole.hasOwnProperty(property) && property !="$$hashKey") {
			    	var modulePermission = {};
					var moduleProperty = {};
					moduleProperty.name = "document";
			    	moduleProperty.item = property;
			    	
			    	modulePermission.id = projectRole.id;
			    	modulePermission.name = projectRole.name;
			    	modulePermission.permission = projectRole[property];
			    	modulePermission.moduleProperty = moduleProperty;
			    	modulePermission.key = 0;
			    	modulePermissionList.push(modulePermission);
			    }
			}
			
			moduleService.saveModulePermission($scope, modulePermissionList);
		}
		$scope.deleteProjectRole = function(modulePermission){
			if(modulePermission.id >0){
				moduleService.deleteModulePermission($scope,modulePermission.id);
			}
			
		}
		$scope.isProjectRolePsCheck = function(value, vCheck){
			if(value == null){
				value = "";
			}
			if(value.indexOf(vCheck)>=0){
				return true;
			}else{
				return false;
			}
		}
		$scope.projectRolePsChange= function(projectRole,key,bool, value){
			var v = value + "|";
			if(projectRole[key]== null){
				projectRole[key] ="";
			}
			if(bool){
				// check if exist or not
				if(projectRole[key].indexOf(v)<0){
					projectRole[key] = v + projectRole[key];
				}
				 
			}else{
				projectRole[key] = projectRole[key].replace(new RegExp(v, 'g'), '');
			}
		}
	  $scope.$on("saveModulePermissionAlready", function(){
		  $scope.closeDialog();
		  $scope.showProjectRole();
	  })
	  $scope.$on("deleteModulePermissionAlready", function(){
		  $scope.closeDialog();
		  $scope.showProjectRole();
	  })
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
	  $scope.updateProjectPermission = function(username, permissionId){
			$scope.moduleRole = {
					username: username,
					modulePermission:{
						id: parseInt(permissionId),
						key: $scope.module_permission_key
					}
			}
			moduleService.updateModuleRole($scope);
		}
		
		$scope.$on('updateModuleRoleComplete', function() {
			if ($scope.data) {
				$scope.closeDialog();
				$scope.showProjectRole();
				
			}
		});
		// get project permission
		$scope.$on('getModuleRolesByKeyAlready', function(){
			if($scope.data){
				$scope.moduleRoles =[];
				for(var i in $scope.data){
					$scope.moduleRoles[$scope.data[i].username] = $scope.data[i].modulePermission; 
				}
			}
			// get resource
			//$scope.getResources();
			getResourceAllType();
		});
}]);


	

	