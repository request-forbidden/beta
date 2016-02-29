/**
 * The app module, as both AngularJS as well as RequireJS module.
 * Splitting an app in several Angular modules serves no real purpose in Angular 1.2.
 * (Hopefully this will change in the near future.)
 * Splitting it into several RequireJS modules allows async loading. We cannot take full advantage
 * of RequireJS and lazy-load stuff because the angular modules have their own dependency system.
 */
define(['angular', 'controllers', 'home', 'user', 'dashboard', 'auth'], function(angular, controllers) {
    'use strict';

    //TODO create direvtives ! and copy from template stuff ! nanan

    // =========================================================================
    // Place for material header/ footer/ leftMenuSideBar/ rightChatBar/
    // - we need to arrange html and move stuff - then go back to home !
    // - serverside auth [token eventualy] + move to auth module
    // - it will be phun (:
    // -- yourprefix - what the fuck ?
    // FIX LAZY LOADING SOMEHOW !
    // =========================================================================

    /*
        App.controller('HeaderCtrl', controllers.HeaderCtrl); // make it auto ! ? - later maybe
    */

      /*

       When using it in a controller you have to use $state:
       angular.module("appModule", ['ui.router']).controller('appController', ['$scope', '$state', function ($scope, $state) {
       $scope.date = new Date();
       }]);
       */

    // We must already declare most dependencies here (except for common), or the submodules' routes
    // will not be resolved
    var App = angular.module('materialAdmin', ['yourprefix.auth','yourprefix.home', 'yourprefix.user', 'yourprefix.dashboard'  ,'ngAnimate',
        'ngResource',
        'ui.router',
        'ui.bootstrap',
        'angular-loading-bar',
        'oc.lazyLoad',
        'nouislider',
        'ngTable',
        'angularFileUpload'
    ]);

    // =========================================================================
    // STATE PROVIDER
    // =========================================================================


    App.config(['$stateProvider', function($stateProvider){

      //  $stateProvider//

    }]);


    // =========================================================================
    // DIRECTIVES
    // =========================================================================


    // =========================================================================
    // LAYOUT
    // =========================================================================
    App.directive('changeLayout', function(){

        return {
            restrict: 'A',
            scope: {
                changeLayout: '='
            },

            link: function(scope, element, attr) {

                //Default State
                if(scope.changeLayout === '1') {
                    element.prop('checked', true);
                }

                //Change State
                element.on('change', function(){
                    if(element.is(':checked')) {
                        localStorage.setItem('ma-layout-status', 1);
                        scope.$apply(function(){
                            scope.changeLayout = '1';
                        })
                    }
                    else {
                        localStorage.setItem('ma-layout-status', 0);
                        scope.$apply(function(){
                            scope.changeLayout = '0';
                        })
                    }
                })
            }
        }
    })

    // =========================================================================
    // MAINMENU COLLAPSE
    // =========================================================================
    .directive('toggleSidebar', function(){

        return {
            restrict: 'A',
            scope: {
                modelLeft: '=',
                modelRight: '='
            },

            link: function(scope, element, attr) {
                element.on('click', function(){

                    if (element.data('target') === 'mainmenu') {
                        if (scope.modelLeft === false) {
                            scope.$apply(function(){
                                scope.modelLeft = true;
                            })
                        }
                        else {
                            scope.$apply(function(){
                                scope.modelLeft = false;
                            })
                        }
                    }

                    if (element.data('target') === 'chat') {
                        if (scope.modelRight === false) {
                            scope.$apply(function(){
                                scope.modelRight = true;
                            })
                        }
                        else {
                            scope.$apply(function(){
                                scope.modelRight = false;
                            })
                        }

                    }
                })
            }
        }

    })
    // =========================================================================
    // SUBMENU TOGGLE
    // =========================================================================

    .directive('toggleSubmenu', function(){

        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                element.click(function(){
                    element.next().slideToggle(200);
                    element.parent().toggleClass('toggled');
                });
            }
        }
    })

    // =========================================================================
    // STOP PROPAGATION
    // =========================================================================

    .directive('stopPropagate', function(){
        return {
            restrict: 'C',
            link: function(scope, element) {
                element.on('click', function(event){
                    event.stopPropagation();
                });
            }
        }
    })

    .directive('aPrevent', function(){
        return {
            restrict: 'C',
            link: function(scope, element) {
                element.on('click', function(event){
                    event.preventDefault();
                });
            }
        }
    })


    // =========================================================================
    // PRINT
    // =========================================================================

    .directive('print', function(){
        return {
            restrict: 'A',
            link: function(scope, element){
                element.click(function(){
                    window.print();
                })
            }
        }
    })
    // =========================================================================
    // MALIHU SCROLL
    // =========================================================================

    //On Custom Class
    .directive('cOverflow', ['scrollService', function(scrollService){
        return {
            restrict: 'C',
            link: function(scope, element) {

                if (!$('html').hasClass('ismobile')) {
                    scrollService.malihuScroll(element, 'minimal-dark', 'y');
                }
            }
        }
    }])

    // =========================================================================
    // WAVES
    // =========================================================================

    // For .btn classes
    .directive('btn', function(){
        return {
            restrict: 'C',
            link: function(scope, element) {
                if(element.hasClass('btn-icon') || element.hasClass('btn-float')) {
                    //Waves.attach(element, ['waves-circle']);
                }

                else if(element.hasClass('btn-light')) {
                    //Waves.attach(element, ['waves-light']);
                }

                else {
                    //Waves.attach(element);
                }

                //Waves.init();
            }
        }
    })

    // =========================================================================
    // SERVICES
    // =========================================================================

    // =========================================================================
    // Malihu Scroll - Custom Scroll bars
    // =========================================================================
    .service('scrollService', function() {
        var ss = {};
        ss.malihuScroll = function scrollBar(selector, theme, mousewheelaxis) {
            $(selector).mCustomScrollbar({
                theme: theme,
                scrollInertia: 100,
                axis:'yx',
                mouseWheel: {
                    enable: true,
                    axis: mousewheelaxis,
                    preventDefault: true
                }
            });
        }

        return ss;
    });


    /* controller loader */

    App.controller('materialCtrl', controllers.AppCtrl);
    App.controller('HeaderCtrl', controllers.HeaderCtrl);



    console.log("App is loaded ");

  return App;
});
