<div class="row panel panel-success" ng-show="isExist">
	<div class="row panel-heading"
		style="margin-right: 0px; margin-left: 0px;">
		<div class="col-sm-6 text-left">
			<spring:message code="attachment.title"></spring:message>
		</div>
		<div class="col-sm-6 text-right">
			<div class="input-group">
				<label class="input-group-btn"> <span
					class="btn btn-primary"> <spring:message
							code="attachment.browse"></spring:message> <input id="fileUpload"
						type="file"
						onchange="angular.element(this).scope().fileNameChanged(this)"
						style="display: none;" multiple>
				</span>
				</label> <input type="text" class="form-control" readonly>
			</div>
		</div>
	</div>
	<div class="row panel-body" data-spy="scroll">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th class="text-align-center"><spring:message
							code="attachment.file.name"></spring:message></th>
					<th class="text-align-center"><spring:message
							code="attachment.description"></spring:message></th>
					<th class="text-align-center"><spring:message
							code="attachment.action"></spring:message></th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="attachment in attachments">
					<td class="text-align-center"><a class="accordion-toggle"
						ng-click="download(attachment.id, attachment.fileName)" href="">
							{{attachment.fileName | limitTo : 15}} </a></td>
					<td class="text-align-center">{{attachment.description}}</td>
					<td class="text-align-center">
						<button type="button" class="btn btn-info"
							ng-click="deleteAttachment(attachment.id)">
							<span class="glyphicon glyphicon-remove-sign"></span>
							<spring:message code="attachment.button.delete"></spring:message>
						</button>
					</td>
				</tr>
				<tr ng-repeat="file in files" class="danger">
					<td class="text-align-center"><a class="accordion-toggle"
						href="#"> {{file.name | limitTo : 15}} </a></td>
					<td class="text-align-center"><input type="text"
						class="form-control" ng-model="file.description"></td>
					<td class="text-align-center">
						<button type="button" class="btn btn-info"
							ng-click="deleteCacheFiles($index)">
							<span class="glyphicon glyphicon-remove-sign"></span>
							<spring:message code="attachment.button.delete"></spring:message>
						</button>
						<button type="button" class="btn btn-info"
							ng-click="saveCacheFiles($index)">
							<span class="glyphicon glyphicon-floppy-save"></span>
							<spring:message code="attachment.button.save"></spring:message>
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>