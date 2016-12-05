'use strict';

// Register `userDetail` component, along with its associated controller and template
angular.
module('login', ['ngRoute', 'ui.bootstrap']).
component('login', {
    templateUrl: 'login/login.template.html',
    controller: ['$routeParams', '$http', '$q', '$location', 'Auth', '$route',
        function loginController($routeParams, $http, $q, $location, Auth, $route) {
            var self = this;

            var username = '';
            var password = '';
            var person = {};
            var defer = $q.defer();
            self.username = username;
            self.password = password;

            //模版HTML页面调用的函数前必须添加self.
            self.showActive = function(vm) {
                //alert("sd");
            };

            //刷新页面
            function reloadRoute() {
                $route.reload();
            }

            self.userlogin = function() {
                person.username = $('#login-username').val();
                person.password = $('#login-password').val();
                //console.log(person);

                var url = 'http://172.16.25.17:8080/license/user/login';
                $http.post(url, JSON.stringify(person)).success(function(data, status, headers, config) {
                    defer.resolve(data);
                    defer.promise.then(function(data) {
                        console.log(data);

                        if (data != -1) {
                            console.log(JSON.stringify(person));
                            Auth.setUser(JSON.stringify(person));

                            $location.path('/users');
                            reloadRoute(); //如果不刷新页面,导航栏用户名会为空!!!
                            var curl = $location.absUrl();
                            console.log(curl);
                        } else {
                            alert("用户名或密码错误!")
                            $location.path('/login');
                            reloadRoute(); //如果不刷新页面,用户输入正确用户名密码也无法登陆
                            var curl = $location.absUrl();
                            console.log(curl);
                        }

                    }); //返回的数据可以在这里进行处理
                });
            }

        }
    ]
});
