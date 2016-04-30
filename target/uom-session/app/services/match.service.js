System.register(["./rest.service", "angular2/core", "../models/student.model", 'rxjs/Rx', "../models/pair.model"], function(exports_1, context_1) {
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
    var rest_service_1, core_1, student_model_1, pair_model_1;
    var MatchService;
    return {
        setters:[
            function (rest_service_1_1) {
                rest_service_1 = rest_service_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (student_model_1_1) {
                student_model_1 = student_model_1_1;
            },
            function (_1) {},
            function (pair_model_1_1) {
                pair_model_1 = pair_model_1_1;
            }],
        execute: function() {
            MatchService = (function () {
                function MatchService(restService) {
                    this.restService = restService;
                }
                MatchService.prototype.getStudents = function () {
                    return this.restService.get(this.restService.getEndpointUrl("student"))
                        .map(function (res) {
                        return res.json();
                    }).map(function (students) {
                        var studentsList = [];
                        if (students) {
                            students.forEach(function (student) {
                                studentsList.push(new student_model_1.Student(student.id, student.indexNumber, student.firstName + " " + student.lastName, student.avatarURL));
                            });
                        }
                        return studentsList;
                    });
                };
                MatchService.prototype.deletePair = function (pairId) {
                    return this.restService.delete(this.restService.getEndpointUrl("couple/" + pairId))
                        .map(function (res) {
                        return res.json();
                    });
                };
                MatchService.prototype.getMatches = function () {
                    return this.restService.get(this.restService.getEndpointUrl("couple"))
                        .map(function (res) {
                        return res.json();
                    }).map(function (matches) {
                        var matchesList = [];
                        if (matches) {
                            matches.forEach(function (match) {
                                var studentOne = new student_model_1.Student(match.studentOne.id, match.studentOne.indexNumber, match.studentOne.firstName + " " + match.studentOne.lastName, match.studentOne.avatarURL);
                                var studentOTwo = new student_model_1.Student(match.studentTwo.id, match.studentTwo.indexNumber, match.studentTwo.firstName + " " + match.studentTwo.lastName, match.studentTwo.avatarURL);
                                matchesList.push(new pair_model_1.MatchObj(match.pairId, studentOne, studentOTwo));
                            });
                        }
                        return matchesList;
                    });
                };
                MatchService.prototype.doVote = function (match) {
                    console.log(match);
                    return this.restService.post(this.restService.getEndpointUrl("couple"), JSON.stringify(match))
                        .map(function (res) {
                        console.log(res);
                        return res.json();
                    });
                };
                MatchService.prototype.authenticate = function () {
                    return this.restService.get(this.restService.getEndpointUrl("auth/")).map(function (res) {
                        return res.json();
                    }).map(function (student) {
                        console.log(student);
                        var studentObj = new student_model_1.Student(student.id, student.indexNumber, student.firstName + " " + student.lastName, student.avatarURL);
                        console.log(studentObj);
                        return studentObj;
                    });
                };
                MatchService = __decorate([
                    core_1.Injectable(),
                    __param(0, core_1.Inject(rest_service_1.RestService)), 
                    __metadata('design:paramtypes', [rest_service_1.RestService])
                ], MatchService);
                return MatchService;
            }());
            exports_1("MatchService", MatchService);
        }
    }
});
