/**
 * Created by Tahir Ali Khan on 1/26/2016.
 */

App.controller('myController', myController);

function myController ($scope, MyService){
    initialize();

    function initialize() {

        getUsers();

    }
    function getUsers(){
        return MyService.get().then(function(data){
            $scope.users = data;
        });
    }
}
