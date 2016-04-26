System.register(["angular2/core", "angular2/router"], function(exports_1, context_1) {
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
    var core_1, router_1;
    var FeedBackService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            /**
             * @author chathura widanage
             */
            FeedBackService = (function () {
                function FeedBackService(router) {
                    var _this = this;
                    this.router = router;
                    router.subscribe(function (val) {
                        _this.dismissAll();
                    });
                }
                FeedBackService.prototype.dismissAll = function () {
                    this._error = null;
                    this._success = null;
                    this._warning = null;
                };
                Object.defineProperty(FeedBackService.prototype, "showPreloader", {
                    get: function () {
                        return this._showPreloader;
                    },
                    set: function (value) {
                        this._showPreloader = value;
                    },
                    enumerable: true,
                    configurable: true
                });
                Object.defineProperty(FeedBackService.prototype, "error", {
                    get: function () {
                        return this._error;
                    },
                    set: function (value) {
                        this._error = value;
                    },
                    enumerable: true,
                    configurable: true
                });
                Object.defineProperty(FeedBackService.prototype, "success", {
                    get: function () {
                        return this._success;
                    },
                    set: function (value) {
                        this._success = value;
                    },
                    enumerable: true,
                    configurable: true
                });
                Object.defineProperty(FeedBackService.prototype, "warning", {
                    get: function () {
                        return this._warning;
                    },
                    set: function (value) {
                        this._warning = value;
                    },
                    enumerable: true,
                    configurable: true
                });
                FeedBackService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [router_1.Router])
                ], FeedBackService);
                return FeedBackService;
            }());
            exports_1("FeedBackService", FeedBackService);
        }
    }
});
