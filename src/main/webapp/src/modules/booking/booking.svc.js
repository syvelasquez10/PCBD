(function (ng) {
    var mod = ng.module('bookingModule');

    mod.service('bookingService', ['$http', 'bookingContext', function ($http, context) {
            this.fetchBooksRecords = function () {
                return $http.get("/webresources/books");
            };

            this.fetchBooksRecord = function (id) {
                return $http.get("/webresources/books" + "/" + id);
            };
            this.fetchRecords = function () {
                return $http.get(context);
            };

            this.fetchRecord = function (id) {
                return $http.get(context + "/" + id);
            };
            this.saveRecord = function (currentRecord) {
                if (currentRecord.id) {
                    return $http.put(context + "/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post(context, currentRecord);
                }
            };
            this.deleteRecord = function (id) {
                return $http.delete(context + "/" + id);
            };
            this.reduceStock = function (id) {
                return $http.put("/webresources/books/reduce" + "/" + id);
            };
        }]);
})(window.angular);
