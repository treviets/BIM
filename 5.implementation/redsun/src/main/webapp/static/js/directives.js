
app.directive("uiDraggable", [
        '$parse',
        '$rootScope',
        function ($parse, $rootScope) {
            return function (scope, element, attrs) {
                if (window.jQuery && !window.jQuery.event.props.dataTransfer) {
                    window.jQuery.event.props.push('dataTransfer');
                }
                element.attr("draggable", false);
                attrs.$observe("uiDraggable", function (newValue) {
                    element.attr("draggable", newValue);
                });
                var dragData = "";
                scope.$watch(attrs.drag, function (newValue) {
                    dragData = newValue;
                });
                element.bind("dragstart", function (e) {
                    var sendData = angular.toJson(dragData);
                    var sendChannel = attrs.dragChannel || "defaultchannel";
                    e.dataTransfer.setData("Text", sendData);
                    $rootScope.$broadcast("ANGULAR_DRAG_START", sendChannel);

                });

                element.bind("dragend", function (e) {
                    var sendChannel = attrs.dragChannel || "defaultchannel";
                    $rootScope.$broadcast("ANGULAR_DRAG_END", sendChannel);
                    if (e.dataTransfer && e.dataTransfer.dropEffect !== "none") {
                        if (attrs.onDropSuccess) {
                            var fn = $parse(attrs.onDropSuccess);
                            scope.$apply(function () {
                                fn(scope, {$event: e});
                            });
                        }
                    }
                });


            };
        }
    ]);
app.directive("uiOnDrop", [
        '$parse',
        '$rootScope',
        function ($parse, $rootScope) {
            return function (scope, element, attr) {
                var dropChannel = "defaultchannel";
                var dragChannel = "";
                var dragEnterClass = attr.dragEnterClass || "on-drag-enter";
                var dragHoverClass = attr.dragHoverClass || "on-drag-hover";

                function onDragOver(e) {

                    if (e.preventDefault) {
                        e.preventDefault(); // Necessary. Allows us to drop.
                    }

                    if (e.stopPropagation) {
                        e.stopPropagation();
                    }
                    e.dataTransfer.dropEffect = 'move';
                    return false;
                }

                function onDragEnter(e) {
                    $rootScope.$broadcast("ANGULAR_HOVER", dropChannel);
                    element.addClass(dragHoverClass);
                }

                function onDrop(e) {
                    if (e.preventDefault) {
                        e.preventDefault(); // Necessary. Allows us to drop.
                    }
                    if (e.stopPropagation) {
                        e.stopPropagation(); // Necessary. Allows us to drop.
                    }
                    var data = e.dataTransfer.getData("Text");
                    data = angular.fromJson(data);
                    var fn = $parse(attr.uiOnDrop);
                    scope.$apply(function () {
                        fn(scope, {$data: data, $event: e});
                    });
                    element.removeClass(dragEnterClass);
                }


                $rootScope.$on("ANGULAR_DRAG_START", function (event, channel) {
                    dragChannel = channel;
                    if (dropChannel === channel) {

                        element.bind("dragover", onDragOver);
                        element.bind("dragenter", onDragEnter);

                        element.bind("drop", onDrop);
                        element.addClass(dragEnterClass);
                    }

                });



                $rootScope.$on("ANGULAR_DRAG_END", function (e, channel) {
                    dragChannel = "";
                    if (dropChannel === channel) {

                        element.unbind("dragover", onDragOver);
                        element.unbind("dragenter", onDragEnter);

                        element.unbind("drop", onDrop);
                        element.removeClass(dragHoverClass);
                        element.removeClass(dragEnterClass);
                    }
                });


                $rootScope.$on("ANGULAR_HOVER", function (e, channel) {
                    if (dropChannel === channel) {
                      element.removeClass(dragHoverClass);
                    }
                });


                attr.$observe('dropChannel', function (value) {
                    if (value) {
                        dropChannel = value;
                    }
                });


            };
        }
	]);

app.directive("drawing", function(){
	  return {
	    restrict: "A",
	    link: function(scope, element){
	      var ctx = element[0].getContext('2d');

	      // variable that decides if something should be drawn on mousemove
	      var drawing = false;

	      // the last coordinates before the current move
	      var beginX;
	      var beginY;

	      element.bind('mousedown', function(event){
	        if(event.offsetX!==undefined){
	        	beginX = event.offsetX;
	        	beginY = event.offsetY;
	        } else { // Firefox compatibility
	        	beginX = event.layerX - event.currentTarget.offsetLeft;
	        	beginY = event.layerY - event.currentTarget.offsetTop;
	        }
	        // begins new line
		    ctx.beginPath();
	        drawing = true;
	      });
	      element.bind('mousemove', function(event){
	        if(drawing){
	          // get current mouse position
	          if(event.offsetX!==undefined){
	        	  currentX = event.offsetX;
	        	  currentY = event.offsetY;
	          } else {
	        	  currentX = event.layerX - event.currentTarget.offsetLeft;
	        	  currentY = event.layerY - event.currentTarget.offsetTop;
	          }
	        }
	        draw(beginX, beginY, currentX, currentY);

	      });
	      element.bind('mouseup', function(event){
	    	  // stop drawing
	    	  drawing = false;
	    	  draw(beginX, beginY, currentX, currentY);
	      });

	      // canvas reset
	      function reset(){
	       element[0].width = element[0].width; 
	      }

	      function draw(lX, lY, cX, cY){

	    	  ctx.moveTo(lX, lY);
	    	  ctx.lineTo(cX, cY);
	    	  ctx.stroke();
	      
	      }
	    }
	  };
	});
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
app.directive('validFile', function () {
    return {
        require: 'ngModel',
        link: function (scope, el, attrs, ngModel) {
            ngModel.$render = function () {
                ngModel.$setViewValue(el.val());
            };

            el.bind('change', function () {
                scope.$apply(function () {
                    ngModel.$render();
                });
            });
        }
    };
});
app.directive('compile', ['$compile', function ($compile) {
    return function(scope, element, attrs) {
        scope.$watch(
            function(scope) {
                // watch the 'compile' expression for changes
                return scope.$eval(attrs.compile);
            },
            function(value) {
                // when the 'compile' expression changes
                // assign it into the current DOM
                element.html(value);

                // compile the new DOM and link it to the current
                // scope.
                // NOTE: we only compile .childNodes so that
                // we don't get into infinite loop compiling ourselves
                $compile(element.contents())(scope);
            }
        );
    };
}]);