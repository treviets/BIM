<script type="text/ng-template" id="expenseDetail.html">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" data-ng-click="cancel()"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" id="build-model-title">
            <span><spring:message code="expense.detail.popup.title"></spring:message></span>
        </h3>
    </div>
    <div class="modal-body">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-4"><spring:message code="expense.detail.popup.date"></spring:message></label>
                <div class="col-sm-8">
                    <p class="input-group">
                        <input type="text" class="form-control" uib-datepicker-popup="{{format}}"
                               ng-model="expenseDetail.expenseDate"
                               is-open="expenseDate.opened" datepicker-options="dateOptions" ng-required="true"
                               close-text="Close" alt-input-formats="altInputFormats"/>
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default" ng-click="open()">
                            <i class="glyphicon glyphicon-calendar"></i>
                        </button>
                    </span>
                    </p>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" ><spring:message code="expense.detail.popup.name"></spring:message></label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="expenseDetail.name"
                           ng-model="expenseDetail.name" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" ><spring:message code="expense.detail.popup.type"></spring:message></label>
                <div class="col-sm-8">
                    <select class="form-control" ng-model="type"
                            ng-options="t.name for t in types"></select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4" ><spring:message code="expense.detail.popup.detail"></spring:message></label>
                <div class="col-sm-8">
                    <textarea class="form-control" rows="5" ng-model="expenseDetail.description" maxlength="200"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-4"><spring:message code="expense.detail.popup.amount"></spring:message></label>
                <div class="col-sm-4">
                    <input type="number" class="form-control" id="expenseDetail.amount"
                           ng-model="expenseDetail.amount" maxlength="10">
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" data-ng-click="ok()"><spring:message code="expense.detail.popup.button.save"></spring:message></button>
        <button type="reset" class="btn btn-default" data-ng-click="cancel()"><spring:message code="expense.detail.popup.button.cancel"></spring:message></button>
    </div>
</script>
