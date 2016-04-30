System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var MatchObj;
    return {
        setters:[],
        execute: function() {
            /**
             * @author Chathura Widanage
             */
            MatchObj = (function () {
                function MatchObj(pairId, studentOne, studentTwo) {
                    this.pairId = pairId;
                    this.studentOne = studentOne;
                    this.studentTwo = studentTwo;
                }
                return MatchObj;
            }());
            exports_1("MatchObj", MatchObj);
        }
    }
});
