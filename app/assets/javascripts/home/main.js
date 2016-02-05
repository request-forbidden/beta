/**
 * Main, shows the start page and provides controllers for the header and the footer.
 * This the entry module which serves as an entry point so other modules only have to include a
 * single module.
 */
define(['angular', './routes', './controllers'], function(angular, routes, controllers) {
  'use strict';

  var mod = angular.module('yourprefix.home', ['ngRoute', 'home.routes']);

  mod.controller('HeaderCtrl', controllers.HeaderCtrl);
  mod.controller('FooterCtrl', controllers.FooterCtrl);

  /**
   * The ng-thumb directive
   * @author: nerv
   * @version: 0.1.2, 2014-01-09
   */
  mod.directive('ngThumb', ['$window', function($window) {
        var helper = {
          support: !!($window.FileReader && $window.CanvasRenderingContext2D),
          isFile: function(item) {
            return angular.isObject(item) && item instanceof $window.File;
          },
          isImage: function(file) {
            var type =  '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
          }
        };

        return {
          restrict: 'A',
          template: '<canvas/>',
          link: function(scope, element, attributes) {
            if (!helper.support) return;

            var params = scope.$eval(attributes.ngThumb);

            if (!helper.isFile(params.file)) return;
            if (!helper.isImage(params.file)) return;

            var canvas = element.find('canvas');
            var reader = new FileReader();

            reader.onload = onLoadFile;
            reader.readAsDataURL(params.file);

            function onLoadFile(event) {
              var img = new Image();
              img.onload = onLoadImage;
              img.src = event.target.result;
            }

            function onLoadImage() {

              //var width = (params.width!==undefined) ? params.width : this.width / this.height * params.height;

              /*jshint validthis:true */
              var width = params.width || this.width / this.height * params.height;
              var height = params.height || this.height / this.width * params.width;
              canvas.attr({ width: width, height: height });
              canvas[0].getContext('2d').drawImage(this, 0, 0, width, height);
            }
          }
        };
      }]);


  return mod;
});
