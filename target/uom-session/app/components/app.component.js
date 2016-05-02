System.register(["angular2/core", "./feedback.component", "../services/feedback.service", "../services/match.service", "../models/student.model", "../models/match.model", "../modules/alert/alert.component", "../modules/alert/alert.service", "../services/rest.service"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var __param = (this && this.__param) || function (paramIndex, decorator) {
        return function (target, key) { decorator(target, key, paramIndex); }
    };
    var core_1, feedback_component_1, feedback_service_1, match_service_1, student_model_1, match_model_1, alert_component_1, alert_service_1, rest_service_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (feedback_component_1_1) {
                feedback_component_1 = feedback_component_1_1;
            },
            function (feedback_service_1_1) {
                feedback_service_1 = feedback_service_1_1;
            },
            function (match_service_1_1) {
                match_service_1 = match_service_1_1;
            },
            function (student_model_1_1) {
                student_model_1 = student_model_1_1;
            },
            function (match_model_1_1) {
                match_model_1 = match_model_1_1;
            },
            function (alert_component_1_1) {
                alert_component_1 = alert_component_1_1;
            },
            function (alert_service_1_1) {
                alert_service_1 = alert_service_1_1;
            },
            function (rest_service_1_1) {
                rest_service_1 = rest_service_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent(feedbackService, matchService, alertService, restService) {
                    this.feedbackService = feedbackService;
                    this.matchService = matchService;
                    this.alertService = alertService;
                    this.restService = restService;
                    this.studentsList = [];
                    this.matches = [];
                    this.authorized = false;
                    this.authLoading = false;
                    //this.loadStudents();
                    //this.loadMatches();
                    this.student = new student_model_1.Student(0, "", "Guest", "resources/img/empty_profile.png");
                }
                AppComponent.prototype.authenticate = function () {
                    var _this = this;
                    console.log('auth called');
                    this.authLoading = true;
                    this.matchService.authenticate().subscribe(function (data) {
                        console.log('login successful');
                        _this.student = data;
                        //waiting for image to load
                        var image = new Image();
                        image.onload = function () {
                            _this.loadContent();
                        };
                        image.onerror = function () {
                            _this.loadContent();
                        };
                        image.src = _this.student.avatarURL;
                    }, function (err) {
                        _this.authLoading = false;
                        _this.feedbackService.error = "Failed to authenticate. Invalid Student auth key.";
                    });
                };
                AppComponent.prototype.loadContent = function () {
                    var _this = this;
                    setTimeout(function () {
                        _this.authorized = true;
                        _this.loadStudents();
                        _this.authLoading = false;
                    }, 2000);
                };
                /**
                 * Loads student data from the server
                 */
                AppComponent.prototype.loadStudents = function () {
                    var _this = this;
                    this.matchService.getStudents().subscribe(function (data) {
                        _this.studentsList = data;
                        _this.studentsList.unshift(new student_model_1.Student(-1, "", "Select a Student", "http://www.uomcse13.avix.lk/img/user.png"));
                        _this.studentOne = _this.studentsList[0];
                        _this.studentTwo = _this.studentsList[0];
                        //loading matches only if this end point is working
                        _this.loadMatches();
                    }, function (err) {
                        _this.feedbackService.error = "The end point to fetch student data is not implemented or not working as expected. Please check the functionality of StudentController@GET method. Note that this end point is essential for the functionality of the application.";
                    });
                };
                /**
                 * load current matches from the server
                 */
                AppComponent.prototype.loadMatches = function () {
                    var _this = this;
                    this.matchService.getMatches().subscribe(function (data) {
                        _this.matches = data;
                    }, function (err) {
                        _this.feedbackService.error = "The end point to fetch your previous matches is not implemented or not working as expected. Please check the functionality of CoupleController@GET method.How ever, this end point is not essential for the functionality of the application.";
                    });
                };
                /**
                 * deletes a already atched pair
                 */
                AppComponent.prototype.deletePair = function (index) {
                    var _this = this;
                    this.alertService.showAlert(new alert_component_1.AlertConfiguration("Confirm your action.", "Do you really want to delete this match?", function () {
                        if (index < _this.matches.length) {
                            var matching = _this.matches[index];
                            _this.matchService.deletePair(matching.pairId).subscribe(function () {
                                _this.feedbackService.success = "Successfully deleted the matching.";
                                _this.loadMatches();
                            }, function (err) {
                                try {
                                    _this.feedbackService.error = err.json().msg;
                                }
                                catch (e) {
                                    _this.feedbackService.error = "The end point to delete a match is not implemented or not working as expected. Please check the functionality of CoupleController@DELETE method.How ever, this end point is not essential for the functionality of the application.";
                                }
                            });
                        }
                    }));
                };
                AppComponent.prototype.vote = function () {
                    var _this = this;
                    if (this.studentOne.id === this.studentTwo.id) {
                        this.feedbackService.warning = "You can't match the same person";
                        return;
                    }
                    if (this.studentOne.id === -1 || this.studentTwo.id == -1) {
                        this.feedbackService.warning = "You have to select two students";
                        return;
                    }
                    var match = new match_model_1.Match();
                    match.studentOne = this.studentOne.id;
                    match.studentTwo = this.studentTwo.id;
                    this.matchService.doVote(match).subscribe(function (data) {
                        console.log(data);
                        _this.feedbackService.success = data.msg;
                    }, function (err) {
                        try {
                            _this.feedbackService.error = err.json().msg;
                        }
                        catch (e) {
                            _this.feedbackService.error = "The end point to do a matching is not implemented or not working as expected. Please check the functionality of CoupleController@POST method. Note that this end point is essential for the functionality of the application.";
                        }
                    }, function () {
                        _this.feedbackService.success = "Successfully created the couple";
                        _this.loadMatches();
                    });
                };
                AppComponent.prototype.setStudentOne = function (index) {
                    this.studentOne = this.studentsList[index];
                };
                AppComponent.prototype.setStudentTwo = function (index) {
                    this.studentTwo = this.studentsList[index];
                };
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'uom-app',
                        templateUrl: 'resources/templates/app.html',
                        directives: [feedback_component_1.FeedbackComponent, alert_component_1.AlertComponent],
                        providers: [match_service_1.MatchService]
                    }),
                    __param(0, core_1.Inject(feedback_service_1.FeedBackService)),
                    __param(2, core_1.Inject(alert_service_1.AlertService)),
                    __param(3, core_1.Inject(rest_service_1.RestService)), 
                    __metadata('design:paramtypes', [feedback_service_1.FeedBackService, match_service_1.MatchService, alert_service_1.AlertService, rest_service_1.RestService])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
