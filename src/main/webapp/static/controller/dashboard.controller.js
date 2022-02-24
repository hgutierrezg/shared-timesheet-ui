'use strict';
angular.module('sharedTimesheetApp').controller('DashboardController',
    ['timesheetService', '$log', function (timesheetService, $log) {
    const dashboardController = this;
    dashboardController.userRole = 'employee';
    dashboardController.timesheets = [];
    dashboardController.timesheet = {startDate: '', endDate: '', client: ''};

    dashboardController.displayError = false;

    dashboardController.submit = submit;
    dashboardController.reset = reset;
    dashboardController.approve = approve;
    dashboardController.menuSelection = menuSelection;
    dashboardController.deleteTimesheet = deleteTimesheet;

    getAllTimesheets();

    function getAllTimesheets() {
        timesheetService.getAllTimesheets()
            .then(
                function (response) {
                    dashboardController.timesheets = response;
                },
                function () {
                    $log.debug('Error while reading timesheets with error ' + errResponse);
                }
            );
    }

        function resetErrors() {
            dashboardController.displayError = false;
        }

        function updateTimesheet(timesheet, id) {
        resetErrors();
        timesheetService.updateTimesheet(timesheet, id)
            .then(
                getAllTimesheets,
                function (errResponse) {
                    $log.debug('Error while updating timesheet with error ' + errResponse);
                }
            );
    }

    function approve(timesheet) {
        resetErrors();
        timesheet.approved = true;
        updateTimesheet(timesheet);
    }

    function deleteTimesheet(timesheet) {
        resetErrors();
        timesheetService.deleteTimesheet(timesheet.id)
            .then(
                getAllTimesheets,
                function (errResponse) {
                    $log.debug('Error while deleting timesheet with error ' + errResponse);
                }
            );
    }

    function menuSelection(selected) {
        dashboardController.userRole = selected;
    }

    function createTimesheet(timesheet) {
        resetErrors();
        timesheet.startDate = new Date(timesheet.startDate).toLocaleString("sv-SE");
        timesheet.endDate = new Date(timesheet.endDate).toLocaleString("sv-SE");
        timesheetService.createTimesheet(timesheet)
            .then(getAllTimesheets, displayError);
    }

    function submit() {
        createTimesheet(dashboardController.timesheet);
        reset();
    }

    function reset() {
        dashboardController.timesheet = {
            startDate: '',
            endDate: '',
            client: ''
        };
    }

    function displayError(response) {
        console.log('response ' + response)
        dashboardController.displayError = true;
    }
}]);