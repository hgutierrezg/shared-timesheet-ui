'use strict';

angular
    .module('sharedTimesheetApp')
    .service('timesheetService', timesheetService);

timesheetService.$inject = ['$http', '$log'];

function timesheetService($http, $log) {

    const HOST_URI = 'http://localhost:8080/';
    const REST_SERVICE_URI = HOST_URI + 'shared-timesheet/times/';

    return {
        getAllTimesheets: getAllTimesheets,
        createTimesheet: createTimesheet,
        updateTimesheet: updateTimesheet,
        deleteTimesheet: deleteTimesheet
    };

    function getAllTimesheets() {
        return $http.get(REST_SERVICE_URI)
            .then(resolveSuccess, resolveError);
    }

    function createTimesheet(timesheet) {
        return $http.post(REST_SERVICE_URI, timesheet)
            .then(resolveSuccess, resolveError);
    }

    function updateTimesheet(timesheet) {
        return $http.put(REST_SERVICE_URI, timesheet)
            .then(resolveSuccess, resolveError);
    }

    function deleteTimesheet(id) {
        return $http.delete(REST_SERVICE_URI + id)
            .then(resolveSuccess, resolveError);
    }


    function resolveSuccess(response) {
        return Promise.resolve(response.data);
    }

    function resolveError(response) {
        const error = 'Error while calling the end point ' + JSON.stringify(response.data);
        $log.error(error);
        throw new Error(error);
    }
}