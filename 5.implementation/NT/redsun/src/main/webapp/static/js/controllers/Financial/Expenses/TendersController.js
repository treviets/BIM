

/**
 * Controller for Tender
 **/

app.controller('tenderController_List', ['$scope', '$compile', '$mdDialog', '$rootScope', '$templateRequest', '$window', 'tenderService', function($scope, $compile, $mdDialog, $rootScope, $templateRequest, $window, tenderService){

    $scope.itemsPerPage = 5;
    $scope.totalCount = 0;
    $scope.pageNo = 0;
    var refType = "Tender";

    $scope.init = function (clientId, pageNo) {
        $scope.pageNo = pageNo;
        tenderService.getsForPageAndFilter(clientId, pageNo, $scope.itemsPerPage, function (successResponse) {
/*        	
                $scope.tenders = successResponse.data.result.tenders;
                if($scope.tenders.length > 0) {
                    $scope.totalCount = $scope.tenders[0].extColCount;
                }
*/                
	        	if($scope.$parent.$parent.projectId) {
	                $scope.tenders = $.grep(successResponse.data.result.tenders, function (element, index) {
	                    return element.projectId == $scope.$parent.$parent.projectId;
	                });
	                if($scope.tenders.length > 0) {
	                    $scope.totalCount = $scope.tenders[0].extColCount;
	                }
	        	}
                
            },
            function (failResponse) {}
        );
    }

    $scope.viewTenderDetail = function(tenderId){
        //window.location = $rootScope.makeGetURL("/tender/get?id=" + tenderId);
    	$templateRequest("/redsun/static/partials/financial/tenders-form.html").then(function(html){
    		$scope.tenderId = tenderId;
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

    $scope.newTender = function(){
    	$templateRequest("/redsun/static/partials/financial/tenders-form.html").then(function(html){
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

    $scope.delete = function (tenderId) {
        tenderService.delete(refType, tenderId, function (successResponse) {
            window.location = $rootScope.MAIN_DOMAIN + "/tender/list";
        }, function (failResponse) {});
    }

}]);

app.controller('tenderController_Detail', ['$scope', '$rootScope', '$location', '$http', '$window', 'tenderService', 'attachmentService', 'selectionsService', function($scope, $rootScope, $location, $http, $window, tenderService, attachmentService, selectionsService){

    $scope.projects = {};
    $scope.project = {};
    $scope.types = {};
    $scope.type = {};
    $scope.statuses = {};
    $scope.status = {};
    $scope.users = {};
    $scope.user = {};
    $scope.callfortenders = {};
    $scope.callfortender = {};
    $scope.providers = {};
    $scope.provider = {};
    $scope.contacts = {};
    $scope.contact = {};

    $scope.tender = {};
    $scope.attachments = {};
    $scope.files = [];
    $scope.modelAttachment = {};

    $scope.isExist = true;
    $scope.msgFail = null;
    $scope.msgSuccess = null;

    var refType = "Tender";
    
    $scope.idProject = $rootScope.projectId;
    
    // CKEditor options.
    CKEDITOR.config.extraPlugins = "base64image";
    $scope.options = {
        language: 'en',
        allowedContent: true,
        entities: false
    };
    $scope.downloadAttachmentsLink = $rootScope.MAIN_DOMAIN +"/restful-attachments/download/";

    /*
    * Ref:  http://luxiyalu.com/angular-all-about-inputfile/
    * */
    $scope.fileNameChanged = function (file) {
        var files = file.files;
        for (var i = 0; i < files.length; i++) {
            $scope.files.push(files[i]);
        }
        $scope.loadAttachments($scope.tender.id);
    }

    $scope.deleteCacheFiles = function (index) {
        $scope.files.splice(index, 1);
        $scope.loadAttachments($scope.tender.id);
    }

    $scope.download = function(id, fileName){
        window.location = $scope.downloadAttachmentsLink + id + '/' + fileName + '/';
    }

    $scope.back = function(){
        window.location = $rootScope.MAIN_DOMAIN + "/tender/list";
    }

    $scope.saveCacheFiles = function (index) {
        var realFile = $scope.files[index];
        $scope.modelAttachment.refId = $scope.tender.id;
        $scope.modelAttachment.refType = refType;
        $scope.modelAttachment.fileName = realFile.name;
        $scope.modelAttachment.fileSize = realFile.size;
        $scope.modelAttachment.mimeType = realFile.type;
        $scope.modelAttachment.creationDate = realFile.lastModifiedDate;
        $scope.modelAttachment.description = realFile.description;
        attachmentService.upload(realFile, $scope.modelAttachment, function (successResponse) {
            $scope.attachments = {};
            $scope.files.splice(index, 1);
            $scope.loadAttachments($scope.tender.id);
        }, function (failResponse) {});
    }

    $scope.initDetail = function(tenderId){

        $scope.isExist = true;
        $scope.msgFail = null;
        $scope.msgSuccess = null;

        //var tenderId = $location.search().id;
        if(tenderId === undefined || tenderId === null){
            $scope.isExist = false;
            $scope.loadAllCombobox();
            return;
        }

        // call service to get tender by id
        tenderService.getById(tenderId,
            function (successResponse) {
                var data = successResponse.data.result.tenders;
                if(data.length > 0){
                    $scope.tender = data[0];
                    $scope.tender.requestDateTime = new Date(data[0].requestDateTime);
                    $scope.tender.expectedTenderDateTime = new Date(data[0].expectedTenderDateTime);
                    $scope.tender.receptionDateTime = new Date(data[0].receptionDateTime);
                    $scope.tender.offerValidityEndDate = new Date(data[0].offerValidityEndDate);
                    $scope.tender.deliveryDate = new Date(data[0].deliveryDate);
                    $scope.tender.handledDate = new Date(data[0].handledDate);
                    $scope.tender.doneDate = new Date(data[0].doneDate);
                    $scope.tender.idleDate = new Date(data[0].idleDate);
                    $scope.loadAttachments(tenderId);
                    $scope.loadAllCombobox();
                }
            },
            function (failResponse) {}
        );
    }

    $scope.loadAllCombobox = function(){
        // call service to get id, name of project
        selectionsService.getProjectCombobox(function (successResponse) {
            $scope.projects = successResponse.data.result.projects;
            
            $scope.tender.idProject = $scope.idProject;

            for(var i = 0; i < $scope.projects.length; i++){
                if($scope.projects[i].id === $scope.tender.idProject){
                    $scope.project = $scope.projects[i];
                    break;
                }
            }
        }, function (failResponse) {});

        // call service to get id, name of status
        selectionsService.getStatusCombobox('Expense', function (successResponse) {
            $scope.statuses = successResponse.data.result.statuses;
            for(var i = 0; i < $scope.statuses.length; i++){
                if($scope.statuses[i].id === $scope.tender.idStatus){
                    $scope.status = $scope.statuses[i];
                    break;
                }
            }
        }, function (failResponse) {});

        // call service to get id, name of type
        selectionsService.getTypesCombobox("Tender", function (successResponse) {
            $scope.types = successResponse.data.result.types;
            for(var i = 0; i < $scope.types.length; i++){
                if($scope.types[i].id === $scope.tender.idTenderType){
                    $scope.type = $scope.types[i];
                    break;
                }
            }
        }, function (failResponse) {});

        // call service to get id, name of users
        selectionsService.getUsersCombobox(function (successResponse) {
            $scope.users = successResponse.data.result.users;
            for(var i = 0; i < $scope.users.length; i++){
                if($scope.users[i].id === $scope.tender.idUser){
                    $scope.user = $scope.users[i];
                    break;
                }
            }
        }, function (failResponse) {});


        selectionsService.getCallForTendersCombobox(function (successResponse) {
            $scope.callfortenders = successResponse.data.result.callfortenders;
            for(var i = 0; i < $scope.callfortenders.length; i++){
                if($scope.callfortenders[i].id === $scope.tender.idCallForTender){
                    $scope.callfortender = $scope.callfortenders[i];
                    break;
                }
            }
        }, function (failResponse) {});

/*
        selectionsService.getProvidersCombobox(function (successResponse) {
            $scope.providers = successResponse.data.result.providers;
            for(var i = 0; i < $scope.providers.length; i++){
                if($scope.providers[i].id === $scope.tender.idProvider){
                    $scope.provider = $scope.providers[i];
                    break;
                }
            }
        }, function (failResponse) {});
*/

        selectionsService.getContactsCombobox(function (successResponse) {
            $scope.contacts = successResponse.data.result.contacts;
            for(var i = 0; i < $scope.contacts.length; i++){
                if($scope.contacts[i].id === $scope.tender.idContact){
                    $scope.contact = $scope.contacts[i];
                    break;
                }
            }
        }, function (failResponse) {});
    }

    $scope.loadAttachments = function (refID) {
        attachmentService.getByRef(refType, refID, function (successResponse) {
            $scope.attachments = successResponse.data.result.attachments;
        }, function (failResponse) {});
    }

    var updateValuesToTenderModel = function () {
        $scope.tender.idTenderType = $scope.type.id;
        $scope.tender.idProject = $scope.project.id;
        $scope.tender.idUser = $scope.user.id;
        $scope.tender.idStatus = $scope.status.id;
        $scope.tender.idCallForTender = $scope.callfortender.id;
        $scope.tender.idProvider = $scope.provider.id;
        $scope.tender.idContact = $scope.contact.id;
        $scope.tender.initialTaxAmount = $scope.tender.initialAmount * $scope.tender.taxPct / 100;
        $scope.tender.initialFullAmount = $scope.tender.initialAmount + $scope.tender.initialTaxAmount;
        $scope.tender.plannedFullAmount = $scope.tender.plannedTaxAmount * $scope.tender.taxPct / 100 + $scope.tender.plannedTaxAmount * 1;
    }

    $scope.update = function () {
        updateValuesToTenderModel();
        if($scope.tender.id === null || $scope.tender.id === undefined){
            tenderService.insert($scope.tender, function (successResponse) {
                $scope.isExist = true;
                $scope.msgSuccess = "Tender saved";
                $scope.tender.id = successResponse.data.result.id;
                $scope.loadAttachments($scope.tender.id);
            }, function (failResponse) {
                $scope.msgFail = "Tender save unsuccessful";
            });
        }else{
            tenderService.update($scope.tender.id, $scope.tender, function (successResponse) {
                $scope.isExist = true;
                $scope.msgSuccess = "Tender updated";
            }, function (failResponse) {
                $scope.msgFail = "Tender update unsuccessful";
            });
        }
    }

    $scope.deleteAttachment = function(attachId){
        attachmentService.delete(attachId, function (successResponse) {
            $scope.loadAttachments($scope.tender.id);
        }, function (failResponse) {});
    }

    $scope.delete = function (tenderId) {
        tenderService.delete(refType, tenderId, function (successResponse) {
            window.location = $rootScope.MAIN_DOMAIN + "/tender/list";
        }, function (failResponse) {});
    }

    $scope.clearModel = function(){
        $scope.tender = {};
        $scope.files = [];
        $scope.isExist = false;
    }


    // date-time-picker popup
    $scope.today = function() {
        $scope.tender.requestDateTime = new Date();
        $scope.tender.expectedTenderDateTime = new Date();
        $scope.tender.receptionDateTime = new Date();
        $scope.tender.offerValidityEndDate = new Date();
        $scope.tender.deliveryDate = new Date();
        $scope.tender.handledDate = new Date();
        $scope.tender.doneDate = new Date();
        $scope.tender.idleDate = new Date();
    };

    $scope.clear = function() {
        $scope.tender.requestDateTime = null;
        $scope.tender.expectedTenderDateTime = null;
        $scope.tender.receptionDateTime = null;
        $scope.tender.offerValidityEndDate = null;
        $scope.tender.deliveryDate = null;
        $scope.tender.handledDate = null;
        $scope.tender.doneDate = null;
        $scope.tender.idleDate = null;
    };

    $scope.setDate = function(year, month, day) {
        $scope.tender.requestDateTime = new Date(year, month, day);
        $scope.tender.expectedTenderDateTime = new Date(year, month, day);
        $scope.tender.receptionDateTime = new Date(year, month, day);
        $scope.tender.offerValidityEndDate = new Date(year, month, day);
        $scope.tender.deliveryDate = new Date(year, month, day);
        $scope.tender.handledDate = new Date(year, month, day);
        $scope.tender.doneDate = new Date(year, month, day);
        $scope.tender.idleDate = new Date(year, month, day);
    };

    $scope.open = function(name){
        if(name === 'requestDateTime'){
            $scope.requestDateTime.opened = true;
        }else if(name === 'expectedTenderDateTime'){
            $scope.expectedTenderDateTime.opened = true;
        }else if(name === 'receptionDateTime'){
            $scope.receptionDateTime.opened = true;
        }else if(name === 'offerValidityEndDate'){
            $scope.offerValidityEndDate.opened = true;
        }else if(name === 'deliveryDate'){
            $scope.deliveryDate.opened = true;
        }else if(name === 'handledDate'){
            $scope.handledDate.opened = true;
        }else if(name === 'doneDate'){
            $scope.doneDate.opened = true;
        }else if(name === 'idleDate'){
            $scope.idleDate.opened = true;
        }
    }

    $scope.requestDateTime = {
        opened: false
    };
    $scope.expectedTenderDateTime = {
        opened: false
    };
    $scope.receptionDateTime = {
        opened: false
    };
    $scope.offerValidityEndDate = {
        opened: false
    };
    $scope.deliveryDate = {
        opened: false
    };
    $scope.handledDate = {
        opened: false
    };
    $scope.doneDate = {
        opened: false
    };
    $scope.idleDate = {
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