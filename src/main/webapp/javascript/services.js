angular.module('basicMSDOSApp')

    // 'basicMSDOS' service manage the basicMSDOS functionallities
    .factory('basicMSDOS', function ($state, $http) {

        return {
            //get the registers
            initApp: function (callbackLoad, callbackSuccess, callbackError) {
                $http({
                    method: 'GET',
                    url: 'getRegisters'
                }).success(function (data) {
                    callbackLoad(true);
                    callbackSuccess(data.registers);
                }).error(function (data) {
                    callbackError(data);
                });
            },

            //get the list filtered by name
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

            //get the list filtered by tape
            tapeSearch: function (tape, callbackSuccess, callbackError) {
                $http({
                    method: 'GET',
                    url: 'filterByTape',
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
