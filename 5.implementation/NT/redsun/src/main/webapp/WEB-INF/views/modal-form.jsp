<div class="modal-header">
    <h3>Create A New Task!</h3>
</div>
<form name="form.userForm" ng-submit="submitForm()" novalidate>
    <div class="modal-body">
        <!-- Task Name -->
        <div class="form-group">
            <label>Task Name</label>
            <input type="text" name="taskname" class="form-control" ng-model="taskname" required>
            <p ng-show="form.userForm.name.$invalid && !form.userForm.name.$pristine" class="help-block">You name is required.</p>
        </div>

        <!-- Status -->
        <div class="form-group">
            <label>Status</label>
            <input type="text" name="status" class="form-control" ng-model="status" ng-minlength="3" ng-maxlength="8" required>
            <p ng-show="form.userForm.username.$error.minlength" class="help-block">Username is too short.</p>
            <p ng-show="form.userForm.username.$error.maxlength" class="help-block">Username is too long.</p>
        </div>

   
      

    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" ng-disabled="form.userForm.$invalid">OK</button>
        <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
</form>