/**
 * Created by Tahir Ali Khan on 1/26/2016.
 */
var App = angular.module('myApp', ['vcRecaptcha'])
    .config(function($httpProvider) {

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    });