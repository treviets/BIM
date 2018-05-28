
(function(BpmnModeler) {
	
	var url = window.location.href;
	var arr = url.split("/");
	var projectId = arr[arr.length - 1];
	
	var getURL = "/redsun/design/diagram/" + projectId;

	var activeStepFromPhaseItem = "1_1_1";
	
	// Get diagram
	var diagramXML = "";
	$.ajax({
  	type: "GET",
  	  url: getURL,
  	  async: false,
  	  contentType: "application/xml",
  	  success: function (data) {
  		diagramXML = data.result['phase-item'][0].contain;
  		activeStepFromPhaseItem = data.result['phase-item'][0].activeStep;
  	  },
  	  error: function (error) {
  		  console.log("Error in creating diagram");
  	  }
    });
	
	// Get phase item
	
	
	var bpmnModeler = new BpmnModeler({
		container: '#canvas'
	});


  // import function
  function importXML(xml) {

    // import diagram
    bpmnModeler.importXML(xml, function(err) {

      if (err) {
        return console.error('could not import BPMN 2.0 diagram', err);
      }

      var canvas = bpmnModeler.get('canvas');

      // zoom to fit full viewport
      canvas.zoom('fit-viewport');
    });


    // save diagram on button click
    var saveButton = document.querySelector('#save-button');

    saveButton.addEventListener('click', function() {

      // get the diagram contents
      bpmnModeler.saveXML({ format: true }, function(err, xml) {

        if (err) {
          console.error('diagram save failed', err);
        } else {
        	alert("Your process saved successfully!!!");
        	console.log(xml);
        	
        	var objDiagram = {};
        	objDiagram.idProject = projectId;
        	objDiagram.activeStep = activeStepFromPhaseItem;
        	objDiagram.contain = xml;
          
          	var CSRF_HEADER = $('meta[name="_csrf_header"]').attr('content');
  			var CSRF_TOKEN = $('meta[name="_csrf_token"]').attr('content');
  			var postURL = "/redsun/design/diagram"+ "?_csrf_header=" + CSRF_HEADER + "&_csrf=" + CSRF_TOKEN + "&format=xml";
  			$.ajax({
  				headers: { 
  			        'Accept': 'application/json',
  			        'Content-Type': 'application/json' 
  			    },
	        	  type: "POST",
	        	  url: postURL,
	        	  contentType: "application/xml",
	        	  data: JSON.stringify(objDiagram),
	        	  success: function (result) {
	        		  console.log("Create diagram successfully");
	        	  },
	        	  error: function (error) {
	        		  console.log("Error in creating diagram");
	        	  }
          });
          
        }

      });
    });
  }


  importXML(diagramXML);

})(window.BpmnJS);

