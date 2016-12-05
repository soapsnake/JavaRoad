'use strict';

angular.
module('Auth', ['ngResource'])
    .factory('Auth', ["$cookies", function($cookies) {
        var userName = '';
        var user = {};
        var expireDate = new Date();
        expireDate.setMinutes(expireDate.getMinutes() + 20);
        console.log(expireDate);

        return {
            setUser: function(aUser) {
                user = JSON.parse(aUser);
                userName = user.username;
                //user.password = aUser.password;
                $cookies.put('userName', userName, { 'expires': expireDate });
            },
            isLoggedIn: function() {
                userName = $cookies.get('userName');
                console.log('cookie: ' + $cookies.get('userName'))
                    //console.log('get from cookie: ' + (user));
                    // return (userName) ? userName : false;
                return userName;
            },
            clearUser: function() {
                userName = '';
                $cookies.remove('userName');
            }
        }
    }])
