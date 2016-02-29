/**
 * Home routes.
 */
define(['angular', './controllers', 'common'], function(angular, controllers) {
  'use strict';

  var mod = angular.module('home.routes', ['yourprefix.common']);
  mod.config(['$routeProvider', function($routeProvider) {

    var self = '/assets/javascripts/modules/home/';

    $routeProvider
      .when('/',  {templateUrl: self+'home.html', controller:controllers.HomeCtrl})
      .otherwise( {templateUrl: '/assets/javascripts/modules/home/notFound.html'});
  }]);
  return mod;
});
