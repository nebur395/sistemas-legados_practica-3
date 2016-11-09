angular.module('musicPsApp')

    // 'exerciseService' service manage the exercise home functions of the page with the server
    .factory('taskService', function ($state, $http) {

        return {
            //get the general list
            initApp: function (callbackSuccess, callbackError) {
                $http({
                    method: 'GET',
                    url: 'getRegisters'
                }).success(function (data) {
                    callbackSuccess(data.registers);
                }).error(function (data) {
                    callbackError(data);
                });
            },

            //get the general list
            nameSearch: function (name, callbackSuccess, callbackError) {
                $http({
                    method: 'GET',
                    url: 'filterByName',
                    headers: {
                        "name": name
                    }
                }).success(function (data) {
                    callbackSuccess(data.programs);
                }).error(function (data) {
                    callbackError(data);
                });
            },

            //get the general list
            tapeSearch: function (tape, callbackSuccess, callbackError) {
                $http({
                    method: 'GET',
                    url: 'filterByName',
                    headers: {
                        "tape": tape
                    }
                }).success(function (data) {
                    callbackSuccess(data.programs);
                }).error(function (data) {
                    callbackError(data);
                });
            }
        };
    });
