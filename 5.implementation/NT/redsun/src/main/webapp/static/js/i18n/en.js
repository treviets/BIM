// Code goes here

angular.module('redsun')
.config(function($translateProvider) {
	$translateProvider
	.useStaticFilesLoader({
        prefix: '/redsun/static/js/i18n/locale-',
        suffix: '.json'
    });

	$translateProvider.preferredLanguage('en');
})
.config(function($mdDateLocaleProvider) {
  $mdDateLocaleProvider.formatDate = function(date) {
    return date ? moment(date).format('DD-MM-YYYY') : '';
  };
  
  $mdDateLocaleProvider.parseDate = function(dateString) {
    var m = moment(dateString, 'DD-MM-YYYY', true);
    return m.isValid() ? m.toDate() : new Date(NaN);
  };
});