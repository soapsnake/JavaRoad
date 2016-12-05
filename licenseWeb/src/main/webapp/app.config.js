'use strict';

angular.
module('licenseApp')
    .config(['$locationProvider', '$routeProvider',
        function config($locationProvider, $routeProvider) {
            $locationProvider.hashPrefix('!');

            $routeProvider.
            when('/users', {
                    template: '<user-list></user-list>'
                }).when('/users/:userID', {
                    template: '<user-detail></user-detail>'
                }).when('/login', {
                    template: '<login></login>'
                }).when('/license', {
                    template: '<license></license>'
                })
                //.otherwise('/users');
        }
    ]).run(['$rootScope', '$location', 'Auth', function($rootScope, $location, Auth) {
        $rootScope.$on('$routeChangeStart', function(event) {

            if (!Auth.isLoggedIn()) {
                console.log('DENY');
                // event.preventDefault();
                $location.path('/login');
            } else {
                console.log('ALLOW');
                // $location.path('/users');
            }
        });
    }]);
