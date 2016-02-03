(function (ng) {

    var mainApp = ng.module('mainApp', [
        'bookModule',
        'bookingModule',
        'ngRoute'
    ]);

    mainApp.config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/ATR', {
                    templateUrl: 'src/modules/book/book.tpl.html',
                    controller: 'bookCtrl',
                    controllerAs: 'ctrl'
                })
                .when('/booking', {
                    templateUrl: 'src/modules/booking/booking.tpl.html',
                    controller: 'bookingCtrl',
                    controllerAs: 'ctrl'
                })
                .otherwise('/');
        }]);
})(window.angular);
