angular.module('musicPsApp')

    .controller('starterCtrl', ['$scope', '$state', 'taskService', function ($scope, $state, taskService) {

        // inputs visual variables
        $scope.registers = "";
        $scope.userInput = "";
        $scope.programsList = [];
        $scope.loaded = false;
        $scope.searchByName = true;

        // feedback handling variables
        $scope.error = false;
        $scope.errorMsg = "";

        // hide the error mensage
        $scope.hideError = function () {
            $scope.errorMsg = "";
            $scope.error = false;
        };
        // show the error mensage
        var showError = function (error) {
            $scope.errorMsg = error;
            $scope.error = true;
        };

        $scope.search = function () {
            if ($scope.searchByName) {
                taskService.nameSearch($scope.userInput, function (programs) {
                    $scope.programsList = programs;
                }, showError);
            } else {
                taskService.tapeSearch($scope.userInput, function (programs) {
                    $scope.programsList = programs;
                }, showError);
            }
        };

        var activeProgress = function (active) {
            $scope.loaded = active;
        };

        $scope.getRegisters = function () {
            taskService.initApp(activeProgress, function (registers) {
                $scope.registers = registers;
            }, showError);
        };
        $scope.getRegisters()
    }]);
