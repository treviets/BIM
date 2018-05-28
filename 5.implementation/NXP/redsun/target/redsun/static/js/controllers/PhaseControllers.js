app.controller('phaseController', ['$scope','$http', function($scope,$http) {
    $scope.tab = 1;
	$scope.finish =2;
	$scope.error=false;
	$scope.sucess=false;
    $scope.isSet = function(tabNum){
		if($scope.tab === tabNum+1){
		$scope.tab === tabNum+1;
		return "active";
		}
		else if($scope.tab < tabNum+1){
		return "disabled";
		}
		
		else{
		return "complete";

		}
      
    };
	
	
	$scope.dataSet = [
        {name:"Requirement",index:0,document:"document1"},
        {name:"Architecture",index:1,document:"document2"},
        {name:"Implementation",index:2,document:"document3"},
		{name:"Test",index:3,document:"document3"},
	
    ],
	$scope.length=$scope.dataSet.length;
	$scope.name = [
        {lastname:"Cao Hong",firstname:"Vu",lastname:"Cao Hong",firstname:"Vu",lastname:"Cao Hong",firstname:"Vu"}
       
    ],
	
    $scope.current = $scope.dataSet[0],
    $scope.next = function(){

		$scope.back=true;
        var i = $scope.getIndex($scope.current.index, 1);
		$scope.tab = i+1;
		$scope.tab=$scope.finish;
		if($scope.tab===$scope.length){
			$scope.finish=$scope.tab+1;
		}
		else if($scope.tab===$scope.length+1){
			$scope.current = $scope.dataSet[$scope.length-1]; 
			
		}
		else{
		$scope.current = $scope.dataSet[i]; 	
		$scope.finish = i+2;
		}		
    },

	
    $scope.previous = function(){
        var i = $scope.getIndex($scope.current.index, -1);
		$scope.tab = i+1;
        $scope.current = $scope.dataSet[i];
		
    },
    $scope.getIndex = function(currentIndex, shift){
        var len = $scope.dataSet.length;
        return (((currentIndex + shift) + len) % len)
    }
    $scope.submitUser = function(myFile) {
    	var file = $scope.myFile;
    	/* console.log('file is ' );
    	console.dir(file);*/
    	var uploadUrl = "/redsun/upfile";
    	var fd = new FormData();
    	fd.append('file', file);
    	
    	$http.post(uploadUrl, fd, {
   
    	headers : {
    	'Content-Type' : undefined
    	}
    	}).success(function() {
    		$scope.success=true;
    	console.log('success');
    	}).error(function() {
    		$scope.error=true;
    	console.log('error');
    	
    	});
    	}
    	
}]);

	