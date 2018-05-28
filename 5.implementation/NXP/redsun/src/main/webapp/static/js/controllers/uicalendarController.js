app.controller('CalendarCtrl',
['$scope', '$rootScope', '$timeout', '$compile', '$timeout', '$mdDialog', '$templateRequest', '$http', '$log', 'uiCalendarConfig', function ($scope, $rootScope, $timeout, $compile, $timeout, $mdDialog, $templateRequest, $http,$log, uiCalendarConfig) {
  
    $scope.calendars = [];
    $scope.events=[];
    
   
    $scope.myDate = new Date();
    $scope.minDay = new Date(    			
    		 $scope.myDate.getFullYear(),
    		 $scope.myDate.getMonth(),
    		 $scope.myDate.getDate()  		    
    		);
    $scope.onlyWeekendsPredicate = function(date) {
        var day = date.getDay();
        return day === 0 || day === 6;
     }
    
     var url =$rootScope.makePostURL("/calendars/list");
     $http.get(url).then(function(response) {
		if (response.data) {
			$scope.data = angular.fromJson(response.data);
			$scope.temp=$scope.data.result.calendars;
			   $timeout(function(){
				   for(var i=0;i<$scope.temp.length;i++){
					$scope.events.push({
						title:$scope.temp[i].title,
						start:$scope.temp[i].start,
						end:$scope.temp[i].end,
					});
				}
			   }, 500);
		}			
	});
     
    /* event source that calls a function on every view switch */
  $scope.eventsF = function (start, end, timezone, callback) {
        var s = new Date(start).getTime() / 1000;
        var e = new Date(end).getTime() / 1000;
        var m = new Date(start).getMonth();
        var events = [{
            title: 'Feed Me ' + m,
            start: s + (50000),
            end: s + (100000),
            allDay: false,
            className: ['customFeed']
        }];
        callback(events);
    };
    $scope.ev = {};
    /* alert on dayClick */
    $scope.alertOnDayClick = function (date) {
        $scope.alertMessage = ("OnDay Click:"+date.format('YYYY-MM-DD h:mm a'));
        $templateRequest("/redsun/static/partials/calendar-add.html").then(function(html){
			$compile(html)($scope);
			var addCalendarPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:true,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent
										// scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveCalendar = function(){
		            	$mdDialog.hide();
		            }
				}
			});
			$mdDialog.show(addCalendarPopup);
        });
        $scope.ev = {
                from: date.format('YYYY-MM-DD'),
                fromhour: date.format('YYYY-MM-DD h:mm a'),
                to: date.format('YYYY-MM-DD'),
                tohour: date.format('YYYY-MM-DD h:mm a'),
                title: '',
                allDay: true
            };   
    };
   /* Click to open form new */
    $scope.ClickToOpen = function(){
    	 ngDialog.open({ template: 'templateId' });
    }
    $scope.createEvent = function (ev) {
        if (!ev.from || !ev.to || !ev.title) { return; }
        // parte date again... or use watchers instead!
        $scope.events.push(ev);
    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function (date, jsEvent, view) {
        $scope.alertMessage = (date.title + ' was clicked ');
    };
    /* alert on Drop */
    $scope.alertOnDrop = function (event, delta, revertFunc, jsEvent, ui, view) {
        $scope.alertMessage = ('Event Dropped to make dayDelta ' + delta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function (event, delta, revertFunc, jsEvent, ui, view) {
        $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function (sources, source) {
        var canAdd = 0;
        angular.forEach(sources, function (value, key) {
            if (sources[key] === source) {
                sources.splice(key, 1);
                canAdd = 1;
            }
        });
        if (canAdd === 0) {
            sources.push(source);
        }
    };  
    // create
	$scope.create = function() {
		var urlServer = $rootScope.makePostURL("/calendars/add");
		$http.post(urlServer, $scope.calendar.data).then(function(response) {
			if(response.data){
				$scope.data = angular.fromJson(response.data);			
			}
		});
		return true;
	}
	//get project
	$scope.getProject = function(){
		var url = $rootScope.makeGetURL("/project/list");
		$http.get(url).then(function(response) {
			if (response.data) {
				$scope.data = angular.fromJson(response.data);
				$scope.projects = $scope.data.result.projects;
				console.log($scope.projects);
			}		
		});

		return true;
	};
    /* ADD EVENT */
    $scope.addEventForCalendar = function(){
    	$scope.calendar = {};
    	$scope.calendar.data={};
		$templateRequest("/redsun/static/partials/calendar-add.html").then(function(html){
			$compile(html)($scope);
			
			
			var addCalendarPopup = $mdDialog.confirm({
				template : html,
				clickOutsideToClose:true,
				scope: $scope,
				preserveScope: true,  // do not forget this if use parent
										// scope
				controller: function DialogController($scope, $mdDialog) {
		            $scope.closeDialog = function() {
		              $mdDialog.hide();
		            }
		            $scope.saveCalendar = function(attachFile){
		            	
		            	var file = $scope.attachFile;
	                	
	                	/*var uploadUrl = "/redsun/calendar/upfile/"+$scope.calendar.data.title;
	                	var fd = new FormData();
	                	fd.append('file', file);*/
	                	
	                	
	                	
		            	var hasNoError = true;
		            	
		        		if(!$scope.calendar.data.title){
		        			$scope.titleError = 'has-error';
		        			hasNoError = false;
		        		}
			            if(!$scope.calendar.data.description){
		        			$scope.descriptionError = 'has-error';
		        			hasNoError = false;
		        		}
			            if(hasNoError == true){	
			            	$scope.addEvent = function () {
			            		$scope.events.push({
			            			 title: $scope.calendar.data.title,
			            		     start: $scope.calendar.data.start,
			            		     end: $scope.calendar.data.end
		            	        });     		
		            	    };
		            	    
			            	var file = $scope.attachFile;
			            	
			            	var uploadUrl = "/redsun/calendars/upfile/"+$scope.calendar.data.title;
		                	var fd = new FormData();
		                	fd.append('file', file);
		                	
		                	console.log(fd);
		                	
		                	$http.post(uploadUrl, fd, {  
			                	headers : {
			                	'Content-Type' : undefined
			                	}
		                	}).success(function() {
			                		
			                	console.log('success');
			                	$scope.create();
			                	$scope.addEvent();
		                	}).error(function(e) {
			                	console.log('error');
			                	$scope.create();
				            	$scope.addEvent();
		                	});	
			            	
		            	    //console.log($scope.attachFile);
			            	/*$scope.create();
			            	$scope.addEvent();*/
			            	$mdDialog.hide();	
			            }			       
					}
				}
			});
			$mdDialog.show(addCalendarPopup);
		});
    };
    /* remove event */
    $scope.remove = function (index) {
        $scope.events.splice(index, 1);
    };
    /* Change View */
    $scope.changeView = function (view, calendar) {
        uiCalendarConfig.calendars[calendar].fullCalendar('changeView', view);
    };
    /* Change View */
    $scope.renderCalender = function (calendar) {
        $timeout(function () {
            if (uiCalendarConfig.calendars[calendar]) {
                uiCalendarConfig.calendars[calendar].fullCalendar('render');
            }
        });
    };
    /* Render Tooltip */
    $scope.eventRender = function (event, element, view ) {};
    /* config object */
    $scope.uiConfig = {
        calendar: {
            height: 450,
            editable: true,
            customButtons: {
                myCustomButton: {
                    text: 'custom!',
                    click: function () {
                        alert('clicked the custom button!');
                    }
                }
            },
            header: {
                left: 'title',
                center: 'myCustomButton',
                right: 'today prev,next'
            },
            dayClick: $scope.addEventForCalendar,
            eventClick: $scope.alertOnEventClick,
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
            eventRender: $scope.eventRender
        }
    };

    /* event sources array */
    $scope.eventSources = [$scope.events,$scope.eventsF];
    $scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.events];
}]);
