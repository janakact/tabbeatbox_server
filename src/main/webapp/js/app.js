/**
 * Created by Janaka on 2016-04-30.
 */
// also include ngRoute for all our routing needs
var tapBeatBoxApp = angular.module('tapBeatBoxApp', ['ngRoute']);

// configure our routes
tapBeatBoxApp.config(function($routeProvider) {
    $routeProvider

    // route for the home page
        .state('/', {
            templateUrl : 'pages/home.html',
            controller  : 'mainCtrl'
        })

        // route for the about page
        .state('/login', {
            templateUrl : 'pages/login.html',
            controller  : 'loginCtrl'
        })
        .otherwise( { redirectTo: '/searchtable' } );


});

// create the controller and inject Angular's $scope
tapBeatBoxApp.controller('mainCtrl', function($scope) {
    // create a message to display in our view
    $scope.message = 'Everyone come and see how good I look!';
});

tapBeatBoxApp.controller('loginCtrl', function($scope) {
    $scope.username = "";
    $scope.message = "Starting Message";
    $scope.password = "";
    $scope.login = function()
    {
        var dataObj = {
            username : $scope.username,
            password : $scope.password,
        };

        $http.post('/rest/user/login/', dataObj).success( function(data,status){
            $scope.message = data;
        });
    }
});
