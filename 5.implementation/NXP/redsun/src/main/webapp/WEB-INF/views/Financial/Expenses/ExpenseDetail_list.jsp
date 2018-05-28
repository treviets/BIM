<div class="row panel-body" ng-show="isExist">
	<div class="row panel panel-success">
		<div class="row panel-heading"
			style="margin-right: 0px; margin-left: 0px;">
			<div class="col-sm-6 text-left">
				<spring:message code="expense.detail.title"></spring:message>
			</div>
			<div class="col-sm-6 text-right">
				<button type="button" class="btn btn-info" ng-click="updateDetail()">
					<span class="glyphicon glyphicon-file"></span>
					<spring:message code="expense.detail.button.new"></spring:message>
				</button>
			</div>
		</div>
		<div class="row panel-body" data-spy="scroll">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="text-align-center"><spring:message
								code="expense.detail.no"></spring:message></th>
						<th class="text-align-center"><spring:message
								code="expense.detail.date"></spring:message></th>
						<th class="text-align-center"><spring:message
								code="expense.detail.name"></spring:message></th>
						<th class="text-align-center"><spring:message
								code="expense.detail.type"></spring:message></th>
						<th class="text-align-center"><spring:message
								code="expense.detail.detail"></spring:message></th>
						<th class="text-align-center"><spring:message
								code="expense.detail.amount"></spring:message></th>
					</tr>
				</thead>
				<tbody>
					<tr
						dir-paginate="expense in expenseDetails | itemsPerPage:itemsPerPage"
						total-items="totalCount">
						<td class="text-align-center">{{$index + 1 + itemsPerPage *
							(pageNo-1)}}</td>
						<td class="text-align-center">{{expense.date}}</td>
						<td class="text-align-center">{{expense.name}}</td>
						<td class="text-align-center">{{expense.type}}</td>
						<td class="text-align-center">{{expense.detail}}</td>
						<td class="text-align-center">{{expense.amount}}</td>
						<td class="text-align-center">
							<button type="button" class="btn btn-info"
								ng-click="updateDetail(expense)">
								<span class="glyphicon glyphicon-eye-open"></span>
								<spring:message code="expense.detail.button.update"></spring:message>
							</button>
							<button type="button" class="btn btn-info"
								ng-click="deleteDetail(expense.id)">
								<span class="glyphicon glyphicon-remove-sign"></span>
								<spring:message code="expense.detail.button.delete"></spring:message>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row panel-body">
			<dir-pagination-controls max-size="5" direction-links="true"
				boundary-links="true"
				on-page-change="initForDetails(123, null, newPageNumber)">
			</dir-pagination-controls>
		</div>
	</div>
</div>