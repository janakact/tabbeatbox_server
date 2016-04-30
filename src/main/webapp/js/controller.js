/**
 * Created by Janaka on 2016-04-30.
 */
angular.module('tapBeatBoxApp', [])
    .controller('mainCtrl',function($scope, $http) {
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
        
        $scope.displayAllData = function () {
            $http.get('/rest/data/all/').success( function(data,status){
                $scope.allData = data;
            });
        }

        $scope.displayOne = function (id) {
            $http.get('/rest/data/'+id).success( function(data,status){
                $scope.oneData = data;
                $scope.allData = null;

                var dataList = data.dataList;
                var  lables = [];
                var values = [];
                for(var i=0; i<dataList.length;i++)
                {
                    lables.push(dataList[i].time);
                    values.push(dataList[i].value);
                }

                var ctx = document.getElementById("myChart");
                var data = {
                    labels: lables,
                    datasets: [
                        {
                            label: "My First dataset",
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
                            data: values,
                        }
                    ]
                };
                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: data,
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero:true
                                }
                            }]
                        }
                    }
                });


            });
        }


    });