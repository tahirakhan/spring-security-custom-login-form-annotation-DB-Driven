/**
 * Created by Tahir Ali Khan on 1/26/2016.
 */

App.factory('MyService', MyService);

function MyService($http, $q) {
    var API = {
        create: create, // Create an Approval Group
        get: get, // Get multiple Approval Groups
        getById: getById, // Get an Approval Group by Id
        remove: remove, // Delete an Approval Group
        save: save // Update an Approval Group
    };

    return API;

    /**
     * Create an Approval Group
     * @param {object} group
     */
    function create(data) {
        var defer = $q.defer();

        $http.post('/api/users', data)
            .success(function(response) {
                         defer.resolve(response);
                     })
            .error(function() {
                       defer.reject('Error Getting Users');
                   });

        return defer.promise;
    }

    /**
     * Get multiple Approval Groups
     * @param {object} config
     */
    function get(config) {
        var defer = $q.defer();

        config = config || {};

        $http({
                  method: 'GET',
                  url: 'api/users',
                  params: config
              }).success(function(response) {
            defer.resolve(response);
        }).error(function () {
            defer.reject('Error getting multiple Approval Groups');
        });

        return defer.promise;
    }



    /**
     * Get an Approval Group by Id
     * @param {string} id
     */
    function getById(id) {
        var defer = $q.defer();

        $http.get('/api/users/' + id)
            .success(function(response) {
                         defer.resolve(response);
                     })
            .error(function() {
                       defer.reject('Error getting Approval Group by Id');
                   });

        return defer.promise;
    }

    /**
     * Remove an Approval Group
     * @param {string} id
     */
    function remove(id) {
        var defer = $q.defer();

        $http.delete('/api/users/' + id)
            .success(function(response) {
                         defer.resolve(response);
                     })
            .error(function() {
                       defer.reject('Error removing Approval Group');
                   });

        return defer.promise;
    }

    /**
     * Update an Approval Group
     * @param {object} group
     */
    function save(group) {
        var defer = $q.defer();

        $http.put('/api/users/' + group.id, group)
            .success(function(response) {
                         defer.resolve(response);
                     })
            .error(function() {
                       defer.reject('Error updating Approval Group');
                   });

        return defer.promise;
    }
}