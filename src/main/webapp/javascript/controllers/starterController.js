angular.module('basicMSDOSApp')

    .controller('starterCtrl', ['$scope', '$state', 'basicMSDOS', function ($scope, $state, basicMSDOS) {

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
                basicMSDOS.nameSearch($scope.userInput, function (programs) {
                    $scope.programsList = programs;
                    sortCustom($scope.programsList);
                }, showError);
            } else {
                basicMSDOS.tapeSearch($scope.userInput, function (programs) {
                    $scope.programsList = programs;
                    sortCustom($scope.programsList);
                }, showError);
            }
        };

        // sort the [list] array alphabetically by register
        function sortCustom (list) {
            list.sort(function(a, b){
                return a.register - b.register;
                /*var x = a.register.toLowerCase();
                var y = b.register.toLowerCase();
                if (x < y) {return -1;}
                if (x > y) {return 1;}
                return 0;*/
            });
        }

        var activeProgress = function (active) {
            $scope.loaded = active;
        };

        $scope.getRegisters = function () {
            basicMSDOS.initApp(activeProgress, function (registers) {
                $scope.registers = registers;
            }, showError);
        };
        $scope.getRegisters()
    }]);
