/**
 * Configure routes of user module.
 */
define(['angular', './controllers', 'common'], function(angular, controllers) {
  'use strict';

  var mod = angular.module('storehouse.routes', ['storehouse.services', 'yourprefix.common']);

  mod.config(['$routeProvider', function($routeProvider) {

    var selfUrl = '/assets/javascripts/modules/storehouse/tpl';

    $routeProvider
      .when('/storehouse/user/list', {templateUrl: selfUrl+'/user-list.html', controller:controllers.StorehouseListCtrl})
      .when('/storehouse/user/:id', {templateUrl: selfUrl+'/user-details.html', controller:controllers.StorehouseDetailsCtrl});
      //.when('/users', {templateUrl:'/assets/templates/user/users.html', controller:controllers.StorehouseCtrl})
      //.when('/users/:id', {templateUrl:'/assets/templates/user/editStorehouse.html', controller:controllers.StorehouseCtrl});
  }]);

  return mod;
});
