<div style="width: 100%; overflow: hidden;">
		    <div style="width: 600px; float: left;"> 
				<textarea 
					style="display:none;"
					chart-json-edit
					view-model="chartViewModel"
					>
				</textarea>
			</div>
		    <div style="height:1000px;">
    			<button
    				ng-click="addNewNode()"
    				title="Add a new node to the chart"
    				>
    				Add Node
				</button>
    			<button
    				ng-click="addNewInputConnector()"
    				ng-disabled="chartViewModel.getSelectedNodes().length == 0"
    				title="Add a new input connector to the selected node"
    				>
    				Add Input Connector
				</button>
    			<button
    				ng-click="addNewOutputConnector()"
    				ng-disabled="chartViewModel.getSelectedNodes().length == 0"
    				title="Add a new output connector to the selected node"
    				>
    				Add Output Connector
				</button>
    			<button
    				ng-click="deleteSelected()"
    				ng-disabled="chartViewModel.getSelectedNodes().length == 0 && chartViewModel.getSelectedConnections().length == 0"
    				title="Delete selected nodes and connections"
    				>
    				Delete Selected
				</button>

				<!--
				This custom element defines the flowchart.
				-->
			    <flow-chart
		    		style="margin: 5px; width: 100%; height: 100%;"
			      	chart="chartViewModel"
			      	>
			    </flow-chart>
		    </div>
		</div>