app.controller("modalInstanceCtrl", ['$scope', '$uibModalInstance', 'expenseDetailService', 'expenseDetail', 'types', 'type', function($scope, $uibModalInstance, expenseDetailService, expenseDetail, types, type){
    $scope.expenseDetail = expenseDetail;
    $scope.types = types;
    $scope.type = type;

    //init expense detail
    if($scope.expenseDetail.id !== null) {
        $scope.expenseDetail.description = expenseDetail.detail;
        $scope.expenseDetail.expenseDate = new Date(expenseDetail.date);
    }

    $scope.ok = function (){
        $scope.expenseDetail.idExpenseDetailType = type.id;
        if($scope.expenseDetail && $scope.expenseDetail.id !== undefined){
            expenseDetailService.update($scope.expenseDetail.id, $scope.expenseDetail, function (successResponse) {
                "use strict";

            }, function (failResponse) {
                "use strict";

            })
        }else {
            expenseDetailService.insert($scope.expenseDetail, function (successResponse) {
                "use strict";

            }, function (failResponse) {
                "use strict";

            })
        }
        $uibModalInstance.dismiss("ok");
    };

    $scope.cancel = function (){
        $uibModalInstance.dismiss("cancel");
    }

    $scope.today = function() {
        $scope.expenseDetail.expenseDate = new Date();
    };

    $scope.clear = function() {
        $scope.expenseDetail.expenseDate = null;
    };

    $scope.setDate = function(year, month, day) {
        $scope.expenseDetail.expenseDate = new Date(year, month, day);
    };

    $scope.open = function(){
        $scope.expenseDate.opened = true;
    }

    $scope.expenseDate = {
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
}]);