


/**
 * Controller for Payments
 **/

app.controller('paymentController', ['$scope', '$compile', '$mdDialog', '$rootScope', '$templateRequest', '$window', '$location', 'paymentService', function($scope, $compile, $mdDialog, $rootScope, $templateRequest, $window, $location, paymentService) {
	
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

		$scope.payment = {};
		// Init attachments.
		$scope.attachments = new FormData();
		$scope.fileNames = [];
		$scope.payment.attachments = [];
		
		// List bills for selection.
		$scope.listBillsForSelection();
		// List all payment types for selection.
		$scope.listPaymentTypesForSelection();
		// List all payment modes for selection.
		$scope.listPaymentModesForSelection();
		
		$scope.payment.id = id;
		if($scope.payment.id > -1) {
			$scope.getById($scope.payment.id);
		}

		$scope.frmDirty = false;
	}
	
	// Show list screen.
	$scope.list = function() {
		$window.location.href = $rootScope.makeGetURL('/payment/list');
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
		$templateRequest("/redsun/static/partials/financial/payments-form.html").then(function(html){
			
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
		$scope.payment = {id: -1}
		$scope.payment.attachments = [];
		$scope.attachments = new FormData();
		$scope.fileNames = [];
		$scope.frmDirty = false;
	}
	
	// Save.
	$scope.save = function() {
		if($scope.frmPayment.$invalid) {
			$scope.frmPayment.$dirty = true;
			$scope.frmDirty = true;
			return;
		}
		$scope.showMessage('Saving!', 'alert-success', false);
		var result;
		if($scope.payment.id > -1) {
			result = paymentService.update($scope.payment.id, $scope.payment);
		} else {
			result = paymentService.add($scope.payment);
		}
		result
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1){
				$scope.payment.id = response.result.id;
				if($scope.attachments.getAll('files').length > 0) {
					$scope.payment.attachments = response.result.attachments;
					// Upload attachments.
					$scope.uploadAttachments($scope.attachments, response.result.id);
					$scope.attachments = new FormData();
					// Set fileNames from attachments of this payment.
					angular.forEach($scope.payment.attachments, function(attachment, idx) {
						$scope.fileNames.push(attachment.fileName);
					});
				}
				$scope.showMessage('Saved!', 'alert-success', true);
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
	});
	}
	
	// Delete.
	$scope.delete = function(id){
		if($window.confirm('Are you sure to delete?')) {
			var result = paymentService.delete(id)
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
				$scope.showMessage('Error!', 'alert-danger', true);
			});
		}
	} 
	
	// Get by Id.
	$scope.getById = function(id) {
		paymentService.getById(id)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.payments !== undefined) {
				if(response.result.payments.length > 0) {
					$scope.payment = response.result.payments[0];
					// Set date time.
					if($scope.payment.paymentDate) {
						$scope.payment.paymentDate = new Date($scope.payment.paymentDate);
					}
					// Set fileNames from attachments of this payment.
					angular.forEach($scope.payment.attachments, function(attachment, idx) {
						$scope.fileNames.push(attachment.fileName);
					});
					// Show diaglog.
					$scope.showEditDialog();
				}
			} else {
				//$scope.showMessage('Error!', 'alert-danger', true);
				var file = new Blob([response], {type: 'application/*'});
				var fileURL = (window.URL || window.webkitURL).createObjectURL(file);
				var downloadLink = angular.element('<a></a>');
				downloadLink.attr('href', fileURL);
	            downloadLink.attr('download', fileName);
				downloadLink[0].click();
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
		});
	}
	
	// List for page and filter.
	$scope.getsForPageAndFilter = function(pageNo) {
		paymentService.getsForPageAndFilter($scope.itemsPerPage, pageNo, $scope.search)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.payments !== undefined) {
				$scope.payments = response.result.payments;
				$scope.totalCount = 0;
				if(response.result.payments.length > 0) {
					$scope.totalCount = response.result.payments[0].totalCount;
				}
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
		});
	}
	
	// List all bills for selection.
	$scope.listBillsForSelection = function() {
		paymentService.listBillsForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.bills !== undefined) {
				$scope.bills = response.result.bills;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
		});
		
	}
	
	// List all payment types for selection.
	$scope.listPaymentTypesForSelection = function() {
		paymentService.listPaymentTypesForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.paymentTypes !== undefined) {
				$scope.paymentTypes = response.result.paymentTypes;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
		});
		
	}
	
	// List all payment modes for selection.
	$scope.listPaymentModesForSelection = function() {
		paymentService.listPaymentModesForSelection()
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1 && response.result.paymentModes !== undefined) {
				$scope.paymentModes = response.result.paymentModes;
			} else {
				$scope.showMessage('Fail!', 'alert-danger', true);
			}
		})
		// error.
		.error(function(response, status, headers, config) {
			$scope.showMessage('Error!', 'alert-danger', true);
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
		paymentDate: { isOpen: false },
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
				$scope.payment.attachments.push(attachment);
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

		$scope.payment.attachments = $.grep($scope.payment.attachments, function (attachment) {
                                return attachment.fileName != fileName;
                            });
	}
    // Upload attachments.
    $scope.uploadAttachments = function (attachments, paymentId) {
		paymentService.uploadAttachments(attachments, paymentId)
		// success.
		.success(function(response, status, headers, config) {
		})
		// error.
		.error(function(response, status, headers, config) {
			$window.alert('Upload error!');
		});
    }
	// Download attachments.
	$scope.downloadAttachments = function(id, fileName) {
		paymentService.downloadAttachments(id, fileName)
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
			$scope.showMessage('Error!', 'alert-danger', true);
		});
	}

	////////////////////////////////////////
	// Amount.
	////////////////////////////////////////
	$scope.calculateAmount = function() {
		var untaxedAmount = parseInt($scope.payment.untaxedAmount);
		if(isNaN(untaxedAmount)) {
			untaxedAmount = 0;
		}
		var tax = parseInt($scope.payment.tax);
		if(isNaN(tax)) {
			tax = 0;
		}
		$scope.payment.fullAmount = untaxedAmount + (untaxedAmount*tax/100)
	}

	// Navigate to url.
	$scope.gotoUrl = function(url) {
		$window.location.href = url;
	}

}]);
