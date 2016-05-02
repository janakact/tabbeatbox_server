System.register(['angular2/platform/browser', './components/app.component', "./services/feedback.service", "angular2/router", "angular2/core", "./services/rest.service", "angular2/http", "./modules/alert/alert.service"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var browser_1, app_component_1, feedback_service_1, router_1, core_1, rest_service_1, http_1, alert_service_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (app_component_1_1) {
                app_component_1 = app_component_1_1;
            },
            function (feedback_service_1_1) {
                feedback_service_1 = feedback_service_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (rest_service_1_1) {
                rest_service_1 = rest_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (alert_service_1_1) {
                alert_service_1 = alert_service_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(app_component_1.AppComponent, [feedback_service_1.FeedBackService, rest_service_1.RestService, alert_service_1.AlertService, http_1.HTTP_PROVIDERS, router_1.ROUTER_PROVIDERS, router_1.ROUTER_DIRECTIVES,
                core_1.provide(router_1.LocationStrategy, { useClass: router_1.HashLocationStrategy })]);
        }
    }
});
