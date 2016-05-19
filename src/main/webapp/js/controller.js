/**
 * Created by Janaka on 2016-04-30.
 */
angular.module('tapBeatBoxApp', [])
    .controller('mainCtrl',function($scope, $http) {

        $scope.username = "";
        $scope.password = "";

        $scope.signup_username = "";
        $scope.signup_password = "";
        $scope.signup_name = "";
        $scope.signup_password_copy = "";

        $scope.message = "Starting Message";
        $scope.showMessage = false;

        $scope.loggedIn = false;
        $scope.showGraph = false;
        $scope.login = function()
        {
            var dataObj = {
                username : $scope.username,
                password : $scope.password,
            };

            $http.post('/rest/user/login/', dataObj).then( function(data,status){
                data = data.data;
                if(data)
                {
                    if(data.username) {
                        $scope.username = data.username;
                        $scope.name = data.name;
                        $scope.loggedIn = true;
                    }
                    else {
                        $scope.message = data.msg;
                        $scope.showMessage = true;
                    }
                }
                else {
                    $scope.message = "Server Error";
                    $scope.showMessage = true;
                }
            },
                function()
                {
                    $scope.message = "Network Error";
                    $scope.showMessage = true;
                }
            );
        }


        $scope.signUp = function()
        {
            if($scope.signup_password_copy!=$scope.signup_password)
            {
                alert("Password do not match!");
                return;
            }

            var dataObj = {
                username : $scope.signup_username,
                password : $scope.signup_password,
                name:$scope.signup_name
            };

            $http.post('/rest/user/signup/', dataObj).then( function(data,status){
                    data = data.data;
                    if(data)
                    {
                            $scope.message = data.msg;
                            $scope.showMessage = true;
                    }
                    else {
                        $scope.message = "Server Error";
                        $scope.showMessage = true;
                    }
                },
                function()
                {
                    $scope.message = "Network Error";
                    $scope.showMessage = true;
                }
            );
        }
        
        $scope.displayAllData = function () {
            $scope.showGraph = false;
            $http.get('/rest/data/all/').success( function(data,status){
                $scope.allData = data;
            });
        }



        $scope.displayOne = function (id) {
                $scope.showGraph = true;
                var data = $scope.allData[id];
               // $scope.oneData = data;
                $scope.allData = [];
                $scope.allData.push(data);
                $scope.message = data;
                var dataList = data.dataList;
                var  lables = [];
                var x = [],y=[],z=[];
                for(var i=0; i<dataList.length;i++)
                {
                    lables.push(dataList[i].time);
                    x.push([dataList[i].time,dataList[i].x]);
                    y.push([dataList[i].time,dataList[i].y]);
                    z.push([dataList[i].time,dataList[i].z]);
                }

                window.setTimeout(function() {
                    $.jqplot('chartdiv',  [x,y,z],{ title:'Data',
                        axes:{
                            xaxis:{
                                min:0,
                                label:'Time(ms)'
                            },
                            yaxis:{
                                label:'Magnitude(ms-2)'
                            }},
// Set default options on all series, turn on smoothing.
                        seriesDefaults: {
                            rendererOptions: {
                                smooth: true
                            }
                        },
                        series:[{color:"RED",
                                // Change our line width and use a diamond shaped marker.
                                lineWidth:2,
                                markerOptions: { size: 2, style:"x" }
                            },
                            {color:'Green',
                            // Change our line width and use a diamond shaped marker.
                            lineWidth:2,
                                markerOptions: { size: 2, style:"o" }
                            },
                            {color:'Blue',
                            // Change our line width and use a diamond shaped marker.
                            lineWidth:2,
                           // showMarker: false
                                markerOptions: { size: 2, style:"o" }
                            }]
                    });
                }, 100);

        }

        $scope.displayAllData();

        $scope.delete = function(id)
        {
            var x;
            if (confirm("Are you sure that you want to delete the data set "+id) == false) {
                return;
            }
            $http.post('/rest/data/delete/'+id).then( function(data,status) {
                data = data.data;
                if (data) {
                    $scope.message = data.msg;
                    $scope.showMessage = true;
                    $scope.displayAllData();
                }
            },
                function()
                {
                    $scope.message = "Network Error";
                    $scope.showMessage = true;
                }
            );
        }


    });