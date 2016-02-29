/**
 * Configure routes of user module.
 */
define(['angular', './controllers', 'common'], function(angular, controllers) {
  'use strict';

  var mod = angular.module('auth.routes', ['auth.services', 'yourprefix.common']);

  mod.config(['$routeProvider', function($routeProvider) {

    var selfUrl = '/assets/javascripts/modules/auth/tpl';

    $routeProvider
      .when('/auth/user/list', {templateUrl: selfUrl+'/user-list.html', controller:controllers.UserListCtrl})
      .when('/auth/user/:id', {templateUrl: selfUrl+'/user-details.html', controller:controllers.UserDetailsCtrl});
      //.when('/users', {templateUrl:'/assets/templates/user/users.html', controller:controllers.UserCtrl})
      //.when('/users/:id', {templateUrl:'/assets/templates/user/editUser.html', controller:controllers.UserCtrl});
  }]);

  return mod;
});
