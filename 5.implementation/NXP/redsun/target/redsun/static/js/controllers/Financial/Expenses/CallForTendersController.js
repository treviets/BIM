

/**
 * Controller for CallForTenders
 **/

app.controller('callForTenderController_List', ['$scope', '$compile', '$mdDialog', '$rootScope', '$templateRequest', '$window', 'callForTenderService', function($scope, $compile, $mdDialog, $rootScope, $templateRequest, $window, callForTenderService){

    $scope.itemsPerPage = 5;
    $scope.totalCount = 0;
    $scope.pageNo = 0;
    var refType = "CallForTender";

    $scope.init = function (clientId, pageNo) {
        $scope.pageNo = pageNo;
        // call service to get all tender
        callForTenderService.getAll(clientId, pageNo, $scope.itemsPerPage, function (successResponse) {
	        	if($scope.$parent.$parent.projectId) {
	                //$scope.tenders = successResponse.data.result.Tenders;
	                $scope.tenders = $.grep(successResponse.data.result.Tenders, function (element, index) {
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
        //window.location = $rootScope.makeGetURL("/call-for-tender/get?id=" + tenderId);
    	$templateRequest("/redsun/static/partials/financial/call-for-tenders-form.html").then(function(html){
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
				//$scope.init(tenderId);
			}, function() {
				$scope.init(1, 1);
			});
		});
    	
    }

    $scope.newTender = function(){
    	$templateRequest("/redsun/static/partials/financial/call-for-tenders-form.html").then(function(html){
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
        callForTenderService.delete(refType, tenderId, function (successResponse) {
            window.location = $rootScope.MAIN_DOMAIN + "/call-for-tender/list";
        }, function (failResponse) {});
    }

}]);

app.controller('callForTenderController_Detail', ['$scope', '$rootScope', '$location', '$http', '$window', 'callForTenderService', 'attachmentService', 'selectionsService', function($scope, $rootScope, $location, $http, $window, callForTenderService, attachmentService, selectionsService){

    $scope.tender = {};
    $scope.projects = {};
    $scope.project = {};
    $scope.types = {};
    $scope.type = {};
    $scope.statuses = {};
    $scope.status = {};
    $scope.users = {};
    $scope.user = {};
    $scope.attachments = {};
    $scope.files = [];
    $scope.modelAttachment = {};
    $scope.isExist = true;
    $scope.msgFail = null;
    $scope.msgSuccess = null;
    var refType = "CallForTender";

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
        callForTenderService.getById(123, tenderId,
            function (successResponse) {
                var data = successResponse.data.result.Tender;
                if(data.length > 0){
                    $scope.tender = data[0];
                    $scope.tender.expectedTenderDateTime = new Date(data[0].expectedTenderDateTime);
                    $scope.tender.sendDateTime = new Date(data[0].sendDateTime);
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
        selectionsService.getTypesCombobox("CallForTender", function (successResponse) {
            $scope.types = successResponse.data.result.types;
            for(var i = 0; i < $scope.types.length; i++){
                if($scope.types[i].id === $scope.tender.idCallForTenderType){
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
    }

    $scope.loadAttachments = function (refID) {
        attachmentService.getByRef(refType, refID, function (successResponse) {
            $scope.attachments = successResponse.data.result.attachments;
        }, function (failResponse) {});
    }

    var updateValuesToTenderModel = function () {
        $scope.tender.idCallForTenderType = $scope.type.id;
        $scope.tender.idProject = $scope.project.id;
        $scope.tender.idUser = $scope.user.id;
        $scope.tender.idStatus = $scope.status.id;
    }

    $scope.update = function () {
        updateValuesToTenderModel();
        if($scope.tender.id === null || $scope.tender.id === undefined){
            callForTenderService.insert($scope.tender, function (successResponse) {
                $scope.isExist = true;
                $scope.msgSuccess = "Call For Tender saved";
                $scope.tender.id = successResponse.data.result.insertCount;
                $scope.loadAttachments($scope.tender.id);
            }, function (failResponse) {
                $scope.msgFail = "Call For Tender save unsuccessful";
            });
        }else{
            callForTenderService.update($scope.tender.id, $scope.tender, function (successResponse) {
                $scope.isExist = true;
                $scope.msgSuccess = "Call For Tender updated";
            }, function (failResponse) {
                $scope.msgFail = "Call For Tender update unsuccessful";
            });
        }
    }

    $scope.deleteAttachment = function(attachId){
        attachmentService.delete(attachId, function (successResponse) {
            $scope.loadAttachments($scope.tender.id);
        }, function (failResponse) {});
    }

    $scope.back = function () {
        window.location = $rootScope.MAIN_DOMAIN + "/call-for-tender/list";
    }

    $scope.delete = function (tenderId) {
        callForTenderService.delete(refType, tenderId, function (successResponse) {
            window.location = $rootScope.MAIN_DOMAIN + "/call-for-tender/list";
        }, function (failResponse) {});
    }

    $scope.clearModel = function(){
        $scope.tender = {};
        $scope.files = [];
        $scope.isExist = false;
    }

    $scope.inlineOptions = {
        customClass: getDayClass,
        minDate: new Date(),
        showWeeks: true
    };

    $scope.dateOptions = {
        dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
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

    // date-time-picker popup
    $scope.today = function() {
        $scope.tender.expectedTenderDateTime = new Date();
        $scope.tender.sendDateTime = new Date();
        $scope.tender.handledDate = new Date();
        $scope.tender.doneDate = new Date();
        $scope.tender.idleDate = new Date();
    };

    $scope.clear = function() {
        $scope.tender.expectedTenderDateTime = null;
        $scope.tender.sendDateTime = null;
        $scope.tender.handledDate = null;
        $scope.tender.doneDate = null;
        $scope.tender.idleDate = null;
    };

    $scope.expectedTenderDateTime = {
        opened: false
    };
    $scope.sendDateTime = {
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

    $scope.open = function(name){
        if(name === 'expectedTenderDateTime'){
            $scope.expectedTenderDateTime.opened = true;
        }else if(name === 'sendDateTime'){
            $scope.sendDateTime.opened = true;
        }else if(name === 'handledDate'){
            $scope.handledDate.opened = true;
        }else if(name === 'doneDate'){
            $scope.doneDate.opened = true;
        }else if(name === 'idleDate'){
            $scope.idleDate.opened = true;
        }
    }

    $scope.setDate = function(year, month, day) {
        $scope.tender.expectedTenderDateTime = new Date(year, month, day);
        $scope.tender.sendDateTime = new Date(year, month, day);
        $scope.tender.handledDate = new Date(year, month, day);
        $scope.tender.doneDate = new Date(year, month, day);
        $scope.tender.idleDate = new Date(year, month, day);
    };

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