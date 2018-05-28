app.controller('billController', ['$scope', '$compile', '$mdDialog', '$rootScope', '$templateRequest', '$window', '$location', 'billService', function($scope, $compile, $mdDialog, $rootScope, $templateRequest, $window, $location, billService) {
	
	// CKEditor options.
	CKEDITOR.config.extraPlugins = "base64image";
	$scope.options = {
		language: 'en',
	    allowedContent: true,
	    entities: false
	};
	
	$scope.idProject = $rootScope.projectId;
	
	// Init for list.
	$scope.initList = function() {
		// Paging.
		$scope.itemsPerPage = 4;
		$scope.totalCount = 0;
		$scope.search = {idProject: $scope.idProject};
		
		$scope.getsForPageAndFilter(1);
	}
	
	// Init for edit.
	$scope.initEdit = function(id) {

		// Destroy ckeditors.
		if(CKEDITOR.instances["txtdescription"]) {
			CKEDITOR.instances["txtdescription"].destroy();
		}

		$scope.bill = {idProject: $scope.idProject};
		// Init attachments.
		$scope.attachments = new FormData();
		$scope.fileNames = [];
		$scope.bill.attachments = [];
		
		// List projects for selection.
		$scope.listProjectsForSelection();
		// List all bill types for selection.
		$scope.listBillTypesForSelection();
		// List statuses for selection.
		$scope.listStatusesForSelection();
		// List customers for selection.
		$scope.listCustomersForSelection();
		// List users for selection.
		$scope.listUsersForSelection();
		// List resources for selection.
		$scope.listResourcesForSelection();
		// List contacts for selection.
		$scope.listContactsForSelection();
		
		$scope.bill.id = id;
		if($scope.bill.id > -1) {
			$scope.getById($scope.bill.id);
		}

		$scope.frmDirty = false;
	}
	
	// Show list screen.
	$scope.list = function() {
		$window.location.href = $rootScope.makeGetURL('/bill/list');
	}
	
	// Show a add screen.
	$scope.add = function() {
		$scope.initEdit(-1);
		$scope.showEditDialog();
	}
	
	// Show a edit screen.
	$scope.edit = function(id) {
		$scope.initEdit(id);
	}
	
	// Show a dialog screen.
	$scope.showEditDialog = function() {
		
		// Show a dialog.
		$templateRequest("/redsun/static/partials/financial/bills-form.html").then(function(html){
			
			$compile(html);
			
			var addTenderPopup = $mdDialog.confirm({
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
			$mdDialog.show(addTenderPopup).then(function() {
			}, function() {
				$scope.getsForPageAndFilter(1);
			});
		});
		
	}
	
	// Create new.
	$scope.createNew = function() {
		$scope.bill = {id: -1}
		$scope.bill.attachments = [];
		$scope.attachments = new FormData();
		$scope.fileNames = [];
		$scope.frmDirty = false;
	}
	
	// Save.
	$scope.save = function() {
		if($scope.frmBill.$invalid) {
			$scope.frmBill.$dirty = true;
			$scope.frmDirty = true;
			return;
		}
		$scope.showMessage('Saving!', 'alert-success', false);
		var result;
		if($scope.bill.id > -1) {
			result = billService.update($scope.bill.id, $scope.bill);
		} else {
			result = billService.add($scope.bill);
		}
		result
		.then(function(response, status, headers, config) {
			response = angular.fromJson(response.data);
			if(response && response.status === 1){
				$scope.bill.id = response.result.id;
				if($scope.attachments.getAll('files').length > 0) {
					$scope.bill.attachments = response.result.attachments;
					// Upload attachments.
					$scope.uploadAttachments($scope.attachments, response.result.id);
					$scope.attachments = new FormData();
					// Set fileNames from attachments of this bill.
					angular.forEach($scope.bill.attachments, function(attachment, idx) {
						$scope.fileNames.push(attachment.fileName);
					});
				}
				$scope.showMessage('Saved!', 'alert-success', true);
				$scope.$broadcast('saveBillAlready');
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		});
	}
	
	// Delete.
	$scope.delete = function(id){
		if($window.confirm('Are you sure to delete?')) {
			var result = billService.delete(id)
			// success.
			.success(function(response, status, headers, config) {
				response = angular.fromJson(response);
				if(response && response.status === 1) {
					$scope.showMessage('Deleted!', 'alert-success', true);
					$scope.getsForPageAndFilter(1);
				} else {
					$scope.showMessage('Fail!', 'alert-danger', true);
				}
			})
			// error.
			.error(function(response, status, headers, config) {
			});
		}
	} 
	
	// Get by Id.
	$scope.getById = function(id) {
		billService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.bills !== undefined) {
				if(response.result.bills.length > 0) {
					$scope.bill = response.result.bills[0];
					// Set date time.
					if($scope.bill.sendDate) {
						$scope.bill.sendDate = new Date($scope.bill.sendDate);
					}
					if($scope.bill.paymentDate) {
						$scope.bill.paymentDate = new Date($scope.bill.paymentDate);
					}
					if($scope.bill.date) {
						$scope.bill.date = new Date($scope.bill.date);
					}
					// Set fileNames from attachments of this bill.
					angular.forEach($scope.bill.attachments, function(attachment, idx) {
						$scope.fileNames.push(attachment.fileName);
					});
					// Show diaglog.
					$scope.showEditDialog();
				}
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
	}
	
	// List for page and filter.
	$scope.getsForPageAndFilter = function(pageNo) {
		billService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.search)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.bills !== undefined) {
				$scope.bills = response.result.bills;
				$scope.totalCount = 0;
				if(response.result.bills.length > 0) {
					$scope.totalCount = response.result.bills[0].totalCount;
				}
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
	}
	
	// List all projects for selection.
	$scope.listProjectsForSelection = function() {
		billService.listProjectsForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.projects !== undefined) {
				$scope.projects = response.result.projects;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	// List all bill types for selection.
	$scope.listBillTypesForSelection = function() {
		billService.listBillTypesForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.billTypes !== undefined) {
				$scope.billTypes = response.result.billTypes;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	// List statuses for selection.
	$scope.listStatusesForSelection = function() {
		billService.listStatusesForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.statuses !== undefined) {
				$scope.statuses = response.result.statuses;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	// List customers for selection.
	$scope.listCustomersForSelection = function() {
		billService.listCustomersForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.customers !== undefined) {
				$scope.customers = response.result.customers;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	// List users for selection.
	$scope.listUsersForSelection = function() {
		billService.listUsersForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.users !== undefined) {
				$scope.users = response.result.users;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	// List resources for selection.
	$scope.listResourcesForSelection = function() {
		billService.listResourcesForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.resources !== undefined) {
				$scope.resources = response.result.resources;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	// List contacts for selection.
	$scope.listContactsForSelection = function() {
		billService.listContactsForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.contacts !== undefined) {
				$scope.contacts = response.result.contacts;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
		});
		
	}
	
	/*Extend functions*/
	
	// Sort by.
	$scope.sortBy = function(keyName){
		$scope.sortKey = keyName;
		$scope.reverse = !$scope.reverse;
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

	////////////////////////////////////////
    // Date picker popup.
	////////////////////////////////////////
	// Config date picker.
	var durationNumber = 10;
	$scope.maxDate = new Date();
	$scope.minDate = new Date();
	$scope.maxDate.setFullYear($scope.maxDate.getFullYear() + durationNumber);
	$scope.minDate.setFullYear($scope.minDate.getFullYear() - durationNumber);
    // Disable weekend selection
    disabled = function(data) {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }
    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yyyy',
        maxDate: $scope.maxDate,
        minDate: $scope.minDate,
        startingDay: 1
    };
	// Date object.
	$scope.DateData = {
		sendDate: { isOpen: false },
		paymentDate: { isOpen: false },
		date: { isOpen: false }
	}
	// Open date picker.
	$scope.openDatePicker = function(element) {
		element.isOpen = true;
	}
	// Change date handler.
	$scope.dateChangeHandler = function(model, element, id) {
		var valid = false;
		if(model === undefined || model === null) { // model is invalid.
			valid = !$('#'+ id).is('[required]');
		} else {
			valid = model >= $scope.minDate && model <= $scope.maxDate;
		}
		element.$setValidity('$valid', valid);
	}

	////////////////////////////////////////
	// Attachments.
	////////////////////////////////////////
	// Add attachments.
	$scope.addAttachments = function($files) {
		angular.forEach($files, function(file, idx) {
			if($.inArray(file.name, $scope.fileNames) < 0) {
				$scope.fileNames.push(file.name);
				$scope.attachments.append("files", file);
				var attachment = { fileName: file.name, fileSize: file.size, mimeType: file.type };
				$scope.bill.attachments.push(attachment);
			}
		});
		$scope.$apply();
	}
	// Clear value of file element.
	$scope.fileClear = function (event) {
		$(event.target).val(null);
	};
	// Delete attachments.
	$scope.deleteAttachments = function(fileName) {
		if(!$window.confirm('Are you sure to delete?')) {
			return;
		}
		$scope.fileNames = $.grep($scope.fileNames, function (pValue) {
                                return pValue != fileName;
                            });
		
		var files = $scope.attachments.getAll('files');
		var fileLength = files.length;
		for(var idx = 0; idx < fileLength; idx++) {
			if(files[idx].name == fileName) {
				files.splice(idx, 1);
				fileLength = -1;
			}
		}
		$scope.attachments = new FormData();
		angular.forEach(files, function(file, idx) {
			$scope.attachments.append("files", file);
		});

		$scope.bill.attachments = $.grep($scope.bill.attachments, function (attachment) {
                                return attachment.fileName != fileName;
                            });
	}
    // Upload attachments.
    $scope.uploadAttachments = function (attachments, billId) {
		billService.uploadAttachments(attachments, billId);
    }
    $scope.$on("saveBillAlready", function(){
    	$window.alert('Bill saved');
		$scope.getsForPageAndFilter(1);
		$mdDialog.hide();
    });
	// Download attachments.
	$scope.downloadAttachments = function(id, fileName) {
		billService.downloadAttachments(id, fileName)
		// success.
		.success(function(response, status, headers, config) {
			var file = new Blob([response], {type: 'application/*'});
			var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
			var downloadLink = angular.element('<a></a>');
			downloadLink.attr('href', fileURL);
            downloadLink.attr('download', fileName);
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
	}

	////////////////////////////////////////
	// Amount.
	////////////////////////////////////////
	$scope.calculateAmount = function() {
		var untaxedAmount = parseInt($scope.bill.untaxedAmount);
		if(isNaN(untaxedAmount)) {
			untaxedAmount = 0;
		}
		var tax = parseInt($scope.bill.tax);
		if(isNaN(tax)) {
			tax = 0;
		}
		$scope.bill.fullAmount = untaxedAmount + (untaxedAmount*tax/100)
	}

	// Navigate to url.
	$scope.gotoUrl = function(url) {
		$window.location.href = url;
	}

}]);
