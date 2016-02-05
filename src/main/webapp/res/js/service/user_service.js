'use strict';

App.factory('UserService', ['$http', '$q','vcRecaptchaService', function($http, $q,vcRecaptchaService){

    var vm = this;
    vm.publicKey = "6LdbLBcTAAAAAM87WuYWWU1zrB_UW1IuAw4rnzaG";
    return {

        fetchAllUsers: function() {
            return $http.get('user/')
                .then(
                function(response){
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while fetching users');
                    return $q.reject(errResponse);
                }
            );
        },

        createUser: function(user){

            if(vcRecaptchaService.getResponse() === ""){ //if string is empty
                alert("Please resolve the captcha and submit!")
            }else {
                user.csrf_token = csrfToken;
                return $http.post('user/', user)
                    .then(
                    function (response) {
                    noty({dismissQueue: true, force: true, layout: 'top', theme: 'defaultTheme', text: 'User Has been created', type: 'success','timeout':2000});
                        vcRecaptchaService.reload();
                        return response.data;
                    },
                    function (errResponse) {
                    if(errResponse.status == 409){
                        //noty({dismissQueue: true, force: true, layout: 'top', theme: 'defaultTheme', text: 'First Name is duplicated', type: 'error'});

                        toastr.options = {
                            "closeButton": false,
                            "debug": false,
                            "newestOnTop": false,
                            "progressBar": false,
                            "positionClass": "toast-top-right",
                            "preventDuplicates": false,
                            "onclick": null,
                            "showDuration": "300",
                            "hideDuration": "1000",
                            "timeOut": "5000",
                            "extendedTimeOut": "1000",
                            "showEasing": "swing",
                            "hideEasing": "linear",
                            "showMethod": "fadeIn",
                            "hideMethod": "fadeOut"
                        }

                        toastr["error"](errResponse.data.message,"Application Error");
                        vcRecaptchaService.reload();
                    }
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    }
                );
            }
        },

        updateUser: function(user, id){

            if(vcRecaptchaService.getResponse() === ""){ //if string is empty
                alert("Please resolve the captcha and submit!")
            }else {
            return $http.put('user/'+id, user)
                .then(
                function(response){
                    vcRecaptchaService.reload();
                    return response.data;
                },
                function(errResponse){
                    console.error('Error while updating user');
                    vcRecaptchaService.reload();
                    return $q.reject(errResponse);
                }
            );

            }
        },

        deleteUser: function(id){
            if(vcRecaptchaService.getResponse() === ""){ //if string is empty
                alert("Please resolve the captcha and submit!")
            }else {
                return $http.delete('user/' + id)
                    .then(
                    function (response) {
                        vcRecaptchaService.reload();
                        return response.data;
                    },
                    function (errResponse) {
                        vcRecaptchaService.reload();
                        console.error('Error while deleting user');
                        return $q.reject(errResponse);
                    }
                );
            }
        }

    };

}]);