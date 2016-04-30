/**
 * Created by Janaka on 2016-04-30.
 */
angular.module('App', [])
    .controller('AppCtrl',function($scope, $http) {
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