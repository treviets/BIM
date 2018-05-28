app.controller('pmReExpenseCtrl', ['$scope', '$compile', '$rootScope', '$filter', '$translate', '$location', '$mdDialog', '$http', '$templateRequest', '$timeout', '$log',
                           		'$window', 'pmReExpenseService', function($scope, $compile, $rootScope, $filter, $translate, $location, $mdDialog, $http, $templateRequest, $timeout,$log,
		$window, pmReExpenseService) {

	$scope.initRSC = function(){
		var proId = -1;
		if($scope.projectId) {
			proId = $scope.projectId
		}
		
		$translate.use($location.search().lang);
		$scope.listCallAndTendert(proId, $scope.startDate, $scope.endDate);
		$scope.listBillAndPayment(proId, $scope.startDate, $scope.endDate);
		$scope.listQuotationAndOrder(proId, $scope.startDate, $scope.endDate);
	}
	
	$scope.changeToDate = function(toDate) {
	    $scope.endDate = $filter('date')(new Date(toDate), "yyyy-MM-dd");
	}
	$scope.changeFromDate = function(fromDate) {
	    $scope.startDate = $filter('date')(new Date(fromDate), "yyyy-MM-dd");
	}

	// listCallAndTendert
	$scope.listCallAndTendert = function(projectId, startDate, endDate) {
		var result = pmReExpenseService.listCallAndTendert(projectId, startDate, endDate)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1) {
				if(response.result.callAndTenders) {
					$scope.callAndTenders = response.result.callAndTenders;
					drawCallAndTenderChart();
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

	// listBillAndPayment
	$scope.listBillAndPayment = function(projectId, startDate, endDate) {
		var result = pmReExpenseService.listBillAndPayment(projectId, startDate, endDate)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1) {
				if(response.result.billAndPayments) {
					$scope.billAndPayments = response.result.billAndPayments;
					drawBillAndPaymentChart();
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

	// listQuotationAndOrder
	$scope.listQuotationAndOrder = function(projectId, startDate, endDate) {
		var result = pmReExpenseService.listQuotationAndOrder(projectId, startDate, endDate)
		// success.
		.success(function(response, status, headers, config) {
			response = angular.fromJson(response);
			if(response && response.status === 1) {
				if(response.result.quotationAndOrders) {
					$scope.quotationAndOrders = response.result.quotationAndOrders;
					drawQuotationAndOrderChart();
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
	
	//draw CallAndTenderChart
	var drawCallAndTenderChart = function(){
		$scope.labelCallAndTender = [];
		var totalPCallAndTender = [];
		var totalACallAndTender = [];
		for(var i=0; i<$scope.callAndTenders.length; i++){
			$scope.labelCallAndTender.push($scope.callAndTenders[i].projectName);
			totalPCallAndTender.push($scope.callAndTenders[i].callAmount);
			totalACallAndTender.push($scope.callAndTenders[i].tenderAmount);
		}
		$scope.seriesCallAndTender = ['Call for tender', 'Tender'];
		$scope.optionCallAndTender = { title: {display: true, text: "Expense Overview"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		$scope.colorCallAndTender = ['#678BC7', '#E78C07'];
		$scope.dataCallAndTender = [totalACallAndTender, totalPCallAndTender];
	}
	
	//draw BillAndPaymentChart
	var drawBillAndPaymentChart = function(){
		$scope.labelBillAndPayment = [];
		var totalPBillAndPayment = [];
		var totalABillAndPayment = [];
		for(var i=0; i<$scope.billAndPayments.length; i++){
			$scope.labelBillAndPayment.push($scope.billAndPayments[i].projectName);
			totalPBillAndPayment.push($scope.billAndPayments[i].billAmount);
			totalABillAndPayment.push($scope.billAndPayments[i].paymentAmount);
		}
		$scope.seriesBillAndPayment = ['Bill', 'Payment'];
		$scope.optionBillAndPayment = { title: {display: true, text: "Expense Overview"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		$scope.colorBillAndPayment = ['#678BC7', '#E78C07'];
		$scope.dataBillAndPayment = [totalABillAndPayment, totalPBillAndPayment];
	}
	
	//draw QuotationAndOrderChart
	var drawQuotationAndOrderChart = function(){
		$scope.labelQuotationAndOrder = [];
		var totalPQuotationAndOrder = [];
		var totalAQuotationAndOrder = [];
		for(var i=0; i<$scope.quotationAndOrders.length; i++){
			$scope.labelQuotationAndOrder.push($scope.quotationAndOrders[i].projectName);
			totalPQuotationAndOrder.push($scope.quotationAndOrders[i].fullAmount);
			totalAQuotationAndOrder.push($scope.quotationAndOrders[i].fullAmount);
		}
		$scope.seriesQuotationAndOrder = ['Quotation', 'Order'];
		$scope.optionQuotationAndOrder = { title: {display: true, text: "Expense Overview"}, legend: { display: true },  scales: { yAxes: [{scaleLabel: {display: true, labelString: "Chi phí" }}]}}; // show legend
		$scope.colorQuotationAndOrder = ['#678BC7', '#E78C07'];
		$scope.dataQuotationAndOrder = [totalAQuotationAndOrder, totalPQuotationAndOrder];
	}

}]);

