var token = $("meta[name='_csrf_token']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");

var app = angular.module("redsun", ["ckeditor","ui.calendar", "ui.router","ngRoute", "ngMaterial", "angularUtils.directives.dirPagination", "pascalprecht.translate", "ui.bootstrap", "chart.js", "angularTreeview", "ngTableResize"]);

app.config(function($routeProvider, $stateProvider, $locationProvider, $httpProvider) {

    $locationProvider.html5Mode({
		  enabled: true,
		  requireBase: false
	}).hashPrefix('*');

    $httpProvider.defaults.headers.common[header] = token;
});