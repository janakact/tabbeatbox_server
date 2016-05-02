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
            $http.get('/rest/data/'+id).success( function(data,status){
                $scope.showGraph = true;
                $scope.message = data;
               // $scope.oneData = data;
                $scope.allData = [];
                $scope.allData.push(data);

                var dataList = data.dataList;
                var  lables = [];
                var x = [],y=[],z=[];
                for(var i=0; i<dataList.length;i++)
                {
                    lables.push(dataList[i].time);
                    x.push(dataList[i].x);
                    y.push(dataList[i].y);
                    z.push(dataList[i].z);
                }

                var ctx = document.getElementById("myChart");
                var data = {
                    labels: lables,
                    datasets: [
                        {
                            label: "X-Axiz",
                            fill: false,
                            lineTension: 0.1,
                            backgroundColor: "rgba(75,192,192,0.4)",
                            borderColor: "rgba(75,192,192,1)",
                            borderCapStyle: 'butt',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "rgba(75,192,192,1)",
                            pointBackgroundColor: "#fff",
                            pointBorderWidth: 1,
                            pointHoverRadius: 5,
                            pointHoverBackgroundColor: "rgba(75,192,192,1)",
                            pointHoverBorderColor: "rgba(220,220,220,1)",
                            pointHoverBorderWidth: 2,
                            pointRadius: 1,
                            pointHitRadius: 10,
                            data: x,
                        },
                        {
                            label: "Y-Axiz",
                            fill: false,
                            lineTension: 0.1,
                            backgroundColor: "rgba(200,100,100,1)",
                            borderColor: "rgba(200,100,100,1)",
                            borderCapStyle: 'butt',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "rgba(200,100,100,1)",
                            pointBackgroundColor: "#fff",
                            pointBorderWidth: 1,
                            pointHoverRadius: 5,
                            pointHoverBackgroundColor: "rgba(200,100,100,1)",
                            pointHoverBorderColor: "rgba(200,100,100,1)",
                            pointHoverBorderWidth: 2,
                            pointRadius: 1,
                            pointHitRadius: 10,
                            data: y,
                        },
                        {
                            label: "Z-Axiz",
                            fill: false,
                            lineTension: 0.1,
                            backgroundColor: "rgba(100,200,100,1)",
                            borderColor: "rgba(100,200,100,1)",
                            borderCapStyle: 'butt',
                            borderDash: [],
                            borderDashOffset: 0.0,
                            borderJoinStyle: 'miter',
                            pointBorderColor: "rgba(100,200,100,1)",
                            pointBackgroundColor: "#fff",
                            pointBorderWidth: 1,
                            pointHoverRadius: 5,
                            pointHoverBackgroundColor: "rgba(100,200,100,1)",
                            pointHoverBorderColor: "rgba(100,200,100,1)",
                            pointHoverBorderWidth: 2,
                            pointRadius: 1,
                            pointHitRadius: 10,
                            data: z,
                        }
                    ]
                };


                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: data,
                    options:  {
                        responsive: true,
                        legend: {
                            position: 'bottom',
                        },
                        hover: {
                            mode: 'label'
                        },
                        scales: {
                            xAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Month'
                                }
                            }],
                            yAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Value'
                                }
                            }]
                        },
                        title: {
                            display: true,
                            text: 'Chart.js Line Chart - Legend'
                        }
                    }
                });


            });
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