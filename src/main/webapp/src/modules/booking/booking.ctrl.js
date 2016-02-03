(function (ng) {
    var mod = ng.module('bookingModule');

    mod.controller('bookingCtrl', ['$scope', 'bookingService', function ($scope, svc) {
            $scope.currentRecord = {};
            $scope.records = [];
            $scope.alerts = [];

            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            function showMessage(msg, type) {
                var types = ['info', 'danger', 'warning', 'success'];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, 'danger');
            };

            function responseError(response) {
                self.showError(response.data);
            }

            //Variables para el controlador
            this.readOnly = false;
            this.bookMode = false;

            var self = this;
            
            this.bookRecord = function (record) {
                this.bookMode = true;
                showMessage("LLene este formulario para reservar el libro: "+record.name+" de referencia "+record.id+ ". Le llegara un correo con información sobre donde y cuando debe recoger el libro solicitado.", 'info');
                this.booksRecord=record.id;
                $scope.currentRecord = {};
            };

            this.deleteBookRecord = function (record) {
                return svc.fetchRecord(record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    self.bookMode = true;
                    return response;
                }, responseError);
            };

            this.fetchRecords = function () {
                return svc.fetchRecords().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    self.bookMode = false;
                    return response;
                }, responseError);
            };
            this.fetchBooksRecords = function () {
                return svc.fetchBooksRecords().then(function (response) {
                    $scope.records = response.data;
                    $scope.currentRecord = {};
                    self.bookMode = false;
                    return response;
                }, responseError);
            };
            this.saveRecord = function () {
                $scope.currentRecord.timeStamp=new Date();
                svc.reduceStock(this.booksRecord);
                return svc.saveRecord($scope.currentRecord).then(function () {
                    self.fetchBooksRecords();
                }, responseError);
            };
            this.deleteRecord = function (record) {
                return svc.deleteRecord(record.id).then(function () {
                    self.fetchRecords();
                }, responseError);
            };
            this.fetchBooksRecords();
        }]);
})(window.angular);
