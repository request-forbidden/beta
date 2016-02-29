/**
 * Auth controllers.
 */
define([], function() {
  'use strict';

/*

  var LoginCtrl = function($scope, $location, userService) {

    $scope.credentials = {};

    $scope.login = function(credentials) {
      userService.loginUser(credentials).then(function(user) {
        $location.path('/dashboard');
      });
    };
  };

  LoginCtrl.$inject = ['$scope'];

*/

  var UserListCtrl = function($scope, ngTableParams, tableService) { // function($filter, $sce, ) {

    console.log("hi from userListController");

    $scope.someFunctionser = function(){
       console.log("click table user list stuff");
    };

    var data = tableService.data;

    //Basic Example we need to add everything to scope
    $scope.tableBasic = new ngTableParams({
      page: 1,            // show first page
      count: 10           // count per page
    }, {
      total: data.length, // length of data
      getData: function ($defer, params) {
        $defer.resolve(data.slice((params.page() - 1) * params.count(), params.page() * params.count()));
      }
    });

    console.log(" this is user table list ! - check filters from stuff ! ");

  };

  UserListCtrl.$inject = ['$scope','ngTableParams','tableService'];

  var UserDetailsCtrl = function($scope, $routeParams){

    var data = {
      mobileNumber: " 606 973 805",
      emailAddress: "none@of.pl",
      skype: "skypenene",
      twitter: "213 tweety",
      addressSuite: "ul. Zapłakana 12",
      addressCity: "77-300 Coklwiek",
      addressCountry: "Polska"
    };

    $scope.pctrl = data;

    console.log("hello from user detail");

  };

  UserDetailsCtrl.$inject = ['$scope', '$routeParams'];

  return {
    UserListCtrl: UserListCtrl,
    UserDetailsCtrl: UserDetailsCtrl
  };

});
