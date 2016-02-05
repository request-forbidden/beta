/**
 * Home routes.
 */
define(['angular', './controllers', 'common'], function(angular, controllers) {
  'use strict';

  var mod = angular.module('material.routes', ['yourprefix.common']);

  mod.config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',  {templateUrl: '/assets/javascripts/modules/material/material.html', controller:controllers.HomeCtrl})
      .otherwise( {templateUrl: '/assets/javascripts/modules/material/notFound.html'});
  }]);

  return mod;
});
