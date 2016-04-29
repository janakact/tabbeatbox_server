/**
 * @author Chathura Widanage
 */
System.register([], function(exports_1, context_1) {
    
    var __moduleName = context_1 && context_1.id;
    var Student;
    return {
        setters:[],
        execute: function() {
            Student = (function () {
                function Student(id, indexNumber, name, avatarURL) {
                    this.id = id;
                    this.indexNumber = indexNumber;
                    this.name = name;
                    this.avatarURL = avatarURL;
                }
                return Student;
            }());
            exports_1("Student", Student);
        }
    }
});
