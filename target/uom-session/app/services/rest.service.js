System.register(["angular2/core", "angular2/http"], function(exports_1, context_1) {
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
    var core_1, http_1;
    var RestService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            RestService = (function () {
                function RestService(http) {
                    this.http = http;
                    this.baseUrl = "rest/";
                    this.token = "";
                }
                RestService.prototype.getEndpointUrl = function (model) {
                    return this.baseUrl + model;
                };
                RestService.prototype.get = function (url, headers) {
                    if (headers === void 0) { headers = new http_1.Headers(); }
                    this.createHeader(headers);
                    return this.http.get(url, {
                        headers: headers
                    });
                };
                RestService.prototype.post = function (url, body, headers) {
                    if (headers === void 0) { headers = new http_1.Headers(); }
                    this.createHeader(headers);
                    return this.http.post(url, body, {
                        headers: headers
                    });
                };
                RestService.prototype.put = function (url, body, headers) {
                    if (headers === void 0) { headers = new http_1.Headers(); }
                    this.createHeader(headers);
                    return this.http.put(url, body, {
                        headers: headers
                    });
                };
                RestService.prototype.delete = function (url, headers) {
                    if (headers === void 0) { headers = new http_1.Headers(); }
                    this.createHeader(headers);
                    return this.http.delete(url, {
                        headers: headers
                    });
                };
                RestService.prototype.createHeader = function (headers) {
                    if (!headers.has("Content-Type")) {
                        headers.append("Content-Type", "application/json");
                        headers.append("Authorization", this.token);
                    }
                };
                RestService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], RestService);
                return RestService;
            }());
            exports_1("RestService", RestService);
        }
    }
});
