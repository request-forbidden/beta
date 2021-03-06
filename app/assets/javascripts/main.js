// `main.js` is the file that sbt-web will use as an entry point
(function (requirejs) {
  'use strict';

  // -- RequireJS config --
  requirejs.config({
    //TODO use lazy load to load this !
    // Packages = top-level folders; loads a contained file named 'main.js"
    packages: ['common', 'home', 'user', 'dashboard', 'auth' ], //'home',
    shim: {
      'jsRoutes': {
        deps: [],
        // it's not a RequireJS module, so we have to tell it what var is returned
        exports: 'jsRoutes'
      },
      // Hopefully this all will not be necessary but can be fetched from WebJars in the future
      'angular': {
        deps: ['jquery'],
        exports: 'angular'
      },
      'angular-route': ['angular'],
      'angular-cookies': ['angular'],
      'bootstrap': ['jquery']
    },
    paths: {
      'requirejs': ['../lib/requirejs/require'],
      'jquery': ['../lib/jquery/jquery'],
      'angular': ['../lib/angularjs/angular'],
      'angular-route': ['../lib/angularjs/angular-route'],
      'angular-cookies': ['../lib/angularjs/angular-cookies'],
      'bootstrap': ['../lib/bootstrap/js/bootstrap'],
      'jsRoutes': ['/js/routes?'],
      'user': ['modules/user'],
      'material': ['modules/material'],
      'dashboard': ['modules/dashboard'],
      'auth': ['modules/auth'],
      'home': ['modules/home'],

      // we use webjars for those !
      //'angular-animate': ['/vendors/bower_components/angular-animate/angular-animate.min'],
      //'angular-resource': ['/vendors/bower_components/angular-resource/angular-resource.min'],
      //'angular-ui-router': ['/vendors/bower_components/angular-ui-router/release/angular-ui-router.min'],
      //'ui-bootstrap-tpls': ['/vendors/bower_components/angular-bootstrap/ui-bootstrap-tpls.min'],
      //  ------------------------

      'mCustomScrollbar': ['/assets/vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min'],
      'loading-bar': ['/assets/vendors/bower_components/angular-loading-bar/src/loading-bar'],
      'ocLazyLoad': ['/assets/vendors/bower_components/oclazyload/dist/ocLazyLoad.min'],
      'nouislider': ['/assets/vendors/bower_components/angular-nouislider/src/nouislider.min'],
      'ng-table': ['/assets/vendors/bower_components/ng-table/dist/ng-table.min'],
      'FileUploader': ['/vassets/javascripts/angular-file-upload/angular-file-upload.min']

    }

  });

  //'ui-bootstrap-tpls','loading-bar', 'ocLazyLoad','nouislider', 'ng-table'

  requirejs.onError = function (err) {
    console.log("[REQUIRE ERROR]", err);
  };

  // Load the app. This is kept minimal so it doesn't need much updating.
  require(['angular',
        'angular-cookies',
        'angular-route',
        'angular-animate',
        'angular-resource',
        'angular-ui-router',
        'ui-bootstrap-tpls',
        'jquery',
        'bootstrap',
        './app'],

      function (angular) {
        require(['loading-bar', 'ocLazyLoad','ng-table', 'nouislider', 'FileUploader', 'mCustomScrollbar'], function(){ //we need angular ready for those
          angular.bootstrap(document, ['materialAdmin']);
        });

    }
  );
})(requirejs);
