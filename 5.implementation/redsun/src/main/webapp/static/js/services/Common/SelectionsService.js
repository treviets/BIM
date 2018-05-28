/**
 * Created by HauLL on 3/20/2017.
 */
app.factory('selectionsService', function($http,$rootScope) {
    return {
        getProjectCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-projects/");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getStatusCombobox: function (scope, successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-statuses/" + scope);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getTypesCombobox: function (scope, successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-types/" + scope);
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getUsersCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-users");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getContactsCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-contacts");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getProvidersCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-providers");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getCallForTendersCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-callfortenders");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getExpenseTypesCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-expensetypes");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getResourceCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-resources");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        },

        getDeliveryModeCombobox: function (successFunc, failFunc) {
            var url = $rootScope.makeGetURL("/restful-selection/list-deliverymodes");
            return $http.get(url).then(function (responseSuccess) {
                successFunc(responseSuccess);
            }, function (responseFail) {
                failFunc(responseFail);
            });
        }
    }
});