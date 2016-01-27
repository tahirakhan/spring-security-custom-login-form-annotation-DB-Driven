<%--
  Created by IntelliJ IDEA.
  User: Tahir Ali Khan
  Date: 1/26/2016
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">


</head>
<body ng-app="myApp">

<div class="container-fluid" ng-controller="myController">
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        #
                    </th>
                    <th>
                        Username
                    </th>
                    <th>
                        Password
                    </th>
                    <th>
                        Status
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr  ng-repeat="user in users">
                    <td>
                        1
                    </td>
                    <td>
                        {{user.username}}
                    </td>
                    <td>
                        {{user.password}}
                    </td>
                    <td>
                        {{user.enable}}
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<!-- AngularJS -->
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<spring:url value="/res/js/app.js" var="appJS" />
<spring:url value="/res/js/service/service.js" var="controllerJS" />
<spring:url value="/res/js/controller/controller.js" var="serviceJS" />

<script src="${appJS}"></script>
<script src="${controllerJS}"></script>
<script src="${serviceJS}"></script>

</html>
