

/**
 * Controller for Individual Expenses
 **/

app.controller('projectExpensesController_List', ['$scope', '$compile', '$mdDialog', '$rootScope', '$templateRequest', '$window', 'expenseService', function($scope, $compile, $mdDialog, $rootScope, $templateRequest, $window, expenseService){

    $scope.itemsPerPage = 5;
    $scope.totalCount = 0;
    $scope.pageNo = 0;
    var scope = "ProjectExpense";

    $scope.init = function (clientId, pageNo) {
        $scope.pageNo = pageNo;
        expenseService.getAllByScope(clientId, scope, $scope.itemsPerPage, pageNo, function (successResponse) {
/*        	
                $scope.expenses = successResponse.data.result.expenses;
                if($scope.expenses.length > 0) {
                    $scope.totalCount = $scope.expenses[0].extColCount;
                }
*/                
	        	if($scope.$parent.$parent.projectId) {
	                $scope.expenses = $.grep(successResponse.data.result.expenses, function (element, index) {
	                    return element.projectId == $scope.$parent.$parent.projectId;
	                });
	                if($scope.expenses.length > 0) {
	                    $scope.totalCount = $scope.expenses[0].extColCount;
	                }
	        	}
                
            },
            function (failResponse) {}
        );
    }

    $scope.viewDetailExpenses = function(id){
        //window.location = $rootScope.makeGetURL("/expenses/project/get?id=" + id);
    	$templateRequest("/redsun/static/partials/financial/project-expenses-form.html").then(function(html){
    		$scope.tenderId = id;
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
				$scope.init(1, 1);
			});
		});
    }

    $scope.newExpenses = function(){
    	$templateRequest("/redsun/static/partials/financial/project-expenses-form.html").then(function(html){
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
				$scope.init(1, 1);
			});
		});
    }

    $scope.deleteExpenses = function (id) {
        expenseService.delete(scope, id, function (successResponse) {
            window.location = $rootScope.MAIN_DOMAIN + "/expenses/project/list";
        }, function (failResponse) {});
    }

}]);

app.controller('projectExpensesController_Detail', ['$scope', '$rootScope', '$location', '$uibModal', '$http', '$window', 'expenseService', 'expenseDetailService', 'attachmentService', 'selectionsService', function($scope, $rootScope, $location, $uibModal, $http, $window, expenseService, expenseDetailService, attachmentService, selectionsService){

    $scope.projects = {};
    $scope.project = {};
    $scope.types = {};
    $scope.type = {};
    $scope.statuses = {};
    $scope.status = {};
    $scope.businessUsers = {};
    $scope.businessUser = {};
    $scope.financialUsers = {};
    $scope.financialUser = {};
    $scope.provider = {};
    $scope.providers = {};
    $scope.contact = {};
    $scope.contacts = {};
    $scope.deliveries = {};
    $scope.delivery = {};
    $scope.expense = {};

    $scope.attachments = {};
    $scope.files = [];
    $scope.modelAttachment = {};
    $scope.isExist = true;
    $scope.msgFail = null;
    $scope.msgSuccess = null;

    var scope = "ProjectExpense";
    
    $scope.idProject = $rootScope.projectId;

    // CKEditor options.
    CKEDITOR.config.extraPlugins = "base64image";
    $scope.options = {
        language: 'en',
        allowedContent: true,
        entities: false
    };
    $scope.downloadAttachmentsLink = $rootScope.MAIN_DOMAIN +"/restful-attachments/download/";

    // detail expenses
    $scope.itemsPerPage = 5;
    $scope.totalCount = 0;
    $scope.pageNo = 0;
    $scope.initForDetails = function (clientId, expenseId, pageNo) {
        $scope.pageNo = pageNo;
        if(expenseId === null){
            expenseId = $scope.expense && $scope.expense.id;
        }
        expenseDetailService.getAllByExpenseId(clientId, expenseId, $scope.itemsPerPage, pageNo, function (successResponse) {
            $scope.expenseDetails = successResponse.data.result.expenseDetails;
            if($scope.expenseDetails.length > 0) {
                $scope.totalCount = $scope.expenseDetails[0].extColCount;
            }
        }, function (failResponse) {

        })
    }

    $scope.deleteDetail = function(id){
        expenseDetailService.delete(id, function (successResponse) {
            $scope.initForDetails(123, $scope.expense.id, 1);
        }, function (failResponse) {})
    }

    $scope.updateDetail = function (expenseDetail) {
        $scope.expenseDetail = {};
        if(expenseDetail !== undefined){
            $scope.expenseDetail = expenseDetail;
        }
        $scope.expenseDetail.idExpense =  $scope.expense.id;
        var modalInstance = $uibModal.open({
            templateUrl : "expenseDetail.html",
            controller : "modalInstanceCtrl",
            backdrop : 'static',
            scope : $scope,
            resolve : {
                expenseDetail : function() {
                    return $scope.expenseDetail;
                },
                types: function () {
                    return $scope.types;
                },
                type: function () {
                    return $scope.type;
                }
            }
        });

        modalInstance.result.then(function (ok) {
            "use strict";
            console.log(ok);
            $scope.initForDetails(123, $scope.expense.id, 1);
        }, function (cancel) {
            "use strict";
            console.log(cancel);
            $scope.initForDetails(123, $scope.expense.id, 1);
        });
    }

    /*
     * Ref:  http://luxiyalu.com/angular-all-about-inputfile/
     * */
    $scope.fileNameChanged = function (file) {
        var files = file.files;
        for (var i = 0; i < files.length; i++) {
            $scope.files.push(files[i]);
        }
        $scope.loadAttachments($scope.expense.id);
    }

    $scope.deleteCacheFiles = function (index) {
        $scope.files.splice(index, 1);
        $scope.loadAttachments($scope.expense.id);
    }

    $scope.download = function(id, fileName){
        window.location = $scope.downloadAttachmentsLink + id + '/' + fileName + '/';
    }

    $scope.back = function(){
        window.location = $rootScope.MAIN_DOMAIN + "/expenses/project/list";
    }

    $scope.saveCacheFiles = function (index) {
        var realFile = $scope.files[index];
        $scope.modelAttachment.refId = $scope.expense.id;
        $scope.modelAttachment.refType = scope;
        $scope.modelAttachment.fileName = realFile.name;
        $scope.modelAttachment.fileSize = realFile.size;
        $scope.modelAttachment.mimeType = realFile.type;
        $scope.modelAttachment.creationDate = realFile.lastModifiedDate;
        $scope.modelAttachment.description = realFile.description;
        attachmentService.upload(realFile, $scope.modelAttachment, function (successResponse) {
            $scope.attachments = {};
            $scope.files.splice(index, 1);
            $scope.loadAttachments($scope.expense.id);
        }, function (failResponse) {});
    }

    $scope.initDetail = function(id){

        $scope.isExist = true;
        $scope.msgFail = null;
        $scope.msgSuccess = null;

        //var id = $location.search().id;
        if(id === undefined || id === null){
            $scope.isExist = false;
            $scope.loadAllCombobox();
            return;
        }

        expenseService.getById(id,
            function (successResponse) {
                var data = successResponse.data.result.expenses;
                if(data.length > 0){
                    $scope.expense = data[0];
                    $scope.expense.sendDate = new Date(data[0].sendDate);
                    $scope.expense.deliveryDate = new Date(data[0].deliveryDate);
                    $scope.expense.receptionDate = new Date(data[0].receptionDate);
                    $scope.expense.expensePlannedDate = new Date(data[0].expensePlannedDate);
                    $scope.expense.expenseRealDate = new Date(data[0].expenseRealDate);
                    $scope.loadAttachments(id);
                    $scope.loadAllCombobox();
                    $scope.initForDetails(123, id, 1);
                }
            },
            function (failResponse) {}
        );
    }

    $scope.loadAllCombobox = function(){
        // call service to get id, name of project
        selectionsService.getProjectCombobox(function (successResponse) {
            $scope.projects = successResponse.data.result.projects;
            
            $scope.expense.idProject = $scope.idProject;

            for(var i = 0; i < $scope.projects.length; i++){
                if($scope.projects[i].id === $scope.expense.idProject){
                    $scope.project = $scope.projects[i];
                    break;
                }
            }
        }, function (failResponse) {});

        // call service to get id, name of status
        selectionsService.getStatusCombobox('Expense', function (successResponse) {
            $scope.statuses = successResponse.data.result.statuses;
            for(var i = 0; i < $scope.statuses.length; i++){
                if($scope.statuses[i].id === $scope.expense.idStatus){
                    $scope.status = $scope.statuses[i];
                    break;
                }
            }
        }, function (failResponse) {});

        // call service to get id, name of users
        selectionsService.getUsersCombobox(function (successResponse) {
            $scope.businessUsers = successResponse.data.result.users;
            for(var i = 0; i < $scope.businessUsers.length; i++){
                if($scope.businessUsers[i].id === $scope.expense.idUser){
                    $scope.businessUser = $scope.businessUsers[i];
                    break;
                }
            }
            $scope.financialUsers = successResponse.data.result.users;
            for(var i = 0; i < $scope.financialUsers.length; i++){
                if($scope.financialUsers[i].id === $scope.expense.idResponsible){
                    $scope.financialUser = $scope.financialUsers[i];
                    break;
                }
            }

        }, function (failResponse) {});

        selectionsService.getExpenseTypesCombobox(function (successResponse) {
            $scope.types = successResponse.data.result.expensetypes;
            for(var i = 0; i < $scope.types.length; i++){
                if($scope.types[i].id === $scope.expense.idExpenseType){
                    $scope.type = $scope.types[i];
                    break;
                }
            }
        }, function (failResponse) {});

        //provider
        selectionsService.getProvidersCombobox(function (successResponse) {
            $scope.providers = successResponse.data.result.providers;
            for(var i = 0; i < $scope.providers.length; i++){
                if($scope.providers[i].id === $scope.expense.idProvider){
                    $scope.provider = $scope.providers[i];
                    break;
                }
            }
        }, function (failResponse) {});

        //contact
        selectionsService.getContactsCombobox(function (successResponse) {
            $scope.contacts = successResponse.data.result.contacts;
            for(var i = 0; i < $scope.contacts.length; i++){
                if($scope.contacts[i].id === $scope.expense.idContact){
                    $scope.contact = $scope.contacts[i];
                    break;
                }
            }
        }, function (failResponse) {});

        //delivery mode
        selectionsService.getDeliveryModeCombobox(function (successResponse) {
            $scope.deliveries = successResponse.data.result.deliverymodes;
            for(var i = 0; i < $scope.deliveries.length; i++){
                if($scope.deliveries[i].id === $scope.expense.idDeliveryMode){
                    $scope.delivery = $scope.deliveries[i];
                    break;
                }
            }
        }, function (failResponse) {});

    }

    $scope.loadAttachments = function (refID) {
        attachmentService.getByRef(scope, refID, function (successResponse) {
            $scope.attachments = successResponse.data.result.attachments;
        }, function (failResponse) {});
    }

    var updateValuesToModel = function () {
        $scope.expense.idProject = $scope.project.id;
        $scope.expense.idUser = $scope.businessUser.id;
        $scope.expense.idResponsible = $scope.financialUser.id;
        $scope.expense.idStatus = $scope.status.id;
        $scope.expense.idProvider = $scope.provider.id;
        $scope.expense.idDeliveryMode = $scope.delivery.id;
        $scope.expense.idExpenseType = $scope.type.id;
        $scope.expense.idContact = $scope.contact.id;
        $scope.expense.scope = scope;

        $scope.expense.plannedFullAmount = $scope.expense.plannedAmount * $scope.expense.taxPct / 100 + $scope.expense.plannedAmount * 1
        $scope.expense.realFullAmount = $scope.expense.realAmount * $scope.expense.taxPct / 100 + $scope.expense.realAmount * 1
    }

    $scope.update = function () {
        updateValuesToModel();
        if($scope.expense.id === null || $scope.expense.id === undefined){
            expenseService.insert($scope.expense, function (successResponse) {
                $scope.isExist = true;
                $scope.msgSuccess = "Expenses saved";
                $scope.expense.id = successResponse.data.result.id;
                $scope.loadAttachments($scope.expense.id);
            }, function (failResponse) {
                $scope.msgFail = "Expenses save unsuccessful";
            });
        }else{
            expenseService.update($scope.expense.id, $scope.expense, function (successResponse) {
                $scope.isExist = true;
                $scope.msgSuccess = "Expenses updated";
            }, function (failResponse) {
                $scope.msgFail = "Expenses update unsuccessful";
            });
        }
    }

    $scope.deleteAttachment = function(attachId){
        attachmentService.delete(attachId, function (successResponse) {
            $scope.loadAttachments($scope.expense.id);
        }, function (failResponse) {});
    }

    $scope.delete = function (tenderId) {
        expenseService.delete(scope, tenderId, function (successResponse) {
            window.location = $rootScope.MAIN_DOMAIN + "/expenses/project/list";
        }, function (failResponse) {});
    }

    $scope.clearModel = function(){
        $scope.expense = {};
        $scope.files = [];
        $scope.isExist = false;
    }


    // date-time-picker popup
    $scope.today = function() {
        $scope.expense.sendDate = new Date();
        $scope.expense.deliveryDate = new Date();
        $scope.expense.receptionDate = new Date();
        $scope.expense.expensePlannedDate = new Date();
        $scope.expense.expenseRealDate = new Date();
    };

    $scope.clear = function() {
        $scope.expense.sendDate = null;
        $scope.expense.deliveryDate = null;
        $scope.expense.receptionDate = null;
        $scope.expense.expensePlannedDate = null;
        $scope.expense.expenseRealDate = null;s
    };

    $scope.setDate = function(year, month, day) {
        $scope.expense.sendDate = new Date(year, month, day);
        $scope.expense.deliveryDate = new Date(year, month, day);
        $scope.expense.receptionDate = new Date(year, month, day);
        $scope.expense.expensePlannedDate = new Date(year, month, day);
        $scope.expense.expenseRealDate = new Date(year, month, day);
    };

    $scope.open = function(name){
        if(name === 'sendDate'){
            $scope.sendDate.opened = true;
        }else if(name === 'deliveryDate'){
            $scope.deliveryDate.opened = true;
        }else if(name === 'receptionDate'){
            $scope.receptionDate.opened = true;
        }else if(name === 'expensePlannedDate'){
            $scope.expensePlannedDate.opened = true;
        }else if(name === 'expenseRealDate'){
            $scope.expenseRealDate.opened = true;
        }
    }

    $scope.sendDate = {
        opened: false
    };
    $scope.deliveryDate = {
        opened: false
    };
    $scope.receptionDate = {
        opened: false
    };
    $scope.expensePlannedDate = {
        opened: false
    };
    $scope.expenseRealDate = {
        opened: false
    };


    $scope.inlineOptions = {
        customClass: getDayClass,
        minDate: new Date(),
        showWeeks: true
    };

    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2050, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };

    // Disable weekend selection
    function disabled(data) {
        var date = data.date,
            mode = data.mode;
        return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
    }

    $scope.toggleMin = function() {
        $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
        $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
    };

    $scope.toggleMin();

    $scope.formats = ['dd/MM/yyyy', 'yyyy-MM-dd', 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[0];
    $scope.altInputFormats = ['M!/d!/yyyy'];

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 1);
    $scope.events = [
        {
            date: tomorrow,
            status: 'full'
        },
        {
            date: afterTomorrow,
            status: 'partially'
        }
    ];

    function getDayClass(data) {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0,0,0,0);

            for (var i = 0; i < $scope.events.length; i++) {
                var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

                if (dayToCheck === currentDay) {
                    return $scope.events[i].status;
                }
            }
        }
        return '';
    }

    // Call init.
    $scope.initDetail($scope.$parent.tenderId);

}]);