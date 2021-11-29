var app = angular.module('app',[]);

app.controller('LonelyController', ['$scope', '$http', '$filter', function($scope, $http, $filter) {

    var url = 'http://localhost:8080/students';

    $scope.init = function() {
        $scope.getList();
        delete $scope.selectedNumber;
        delete $scope.selectedStudent;
        delete $scope.errorsMap;
        delete $scope.userMode;
    };

    $scope.getList = function() {
        var api = '/list';
        $http.get(url + api).then(function(response) {
            if (response.data.status === 200) {
                $scope.students = response.data.data;
            }
        })
    };

    $scope.createStudent = function(student) {
        var api = '/create';
        $http.post(url + api, student).then(function(response) {
            if (response.data.status === 200) {
                $scope.init()
            } else {
                $scope.errorsMap = response.data.errorsMap;
            }
        })
    };

    $scope.createStudentButton = function() {
        delete $scope.errorsMap;
        $scope.selectedStudent = {};
        $scope.userMode = 'new';
    };

    $scope.onRowSelect = function(student) {
        delete $scope.errorsMap;
        $scope.selectedNumber = student.number;
        $scope.selectedStudent = angular.copy(student);
        $scope.selectedStudent.birthDay = $scope.selectedStudent.birthDay && $scope.selectedStudent.birthDay.slice(0, 10);
        $scope.userMode = 'edit';
    };

    $scope.removeStudent = function(student) {
        var api = '/remove';
        $http.delete(url + api + '?number=' + student.number).then(function(response) {
            if (response.data.status === 200) {
                $scope.init();
            } else {
                $scope.errorsMap = response.data.errorsMap;
            }
        })
    }

    $scope.updateStudent = function(student) {
        var api = '/update';
        $http.put(url + api, student).then(function(response) {
            if (response.data.status === 200) {
                $scope.init();
            } else {
                $scope.errorsMap = response.data.errorsMap;
            }
        })
    };


}]);