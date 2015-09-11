<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>通话记录</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container-fluid" ng-app="${className_x}App" ng-controller="${className_x}Controller">
        <h1>通话记录</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                   <th>序号</th>
                  <#list tableCarrays as tableCarray>
				   <th>${tableCarray.remark}</th>
				 </#list>
                </tr>
            </thead>
            <tbody ng-repeat="a in list">
                <tr>
                    <td ng-bind="$index+1"></td>
                       <#list tableCarrays as tableCarray>
							    <td>{{a.${tableCarray.carrayName_x}}}</td>
				      </#list>
                </tr>
            </tbody>
        </table>
        <nav>
            <ul class="pagination pagination-lg" ng-repeat="pageNo in currentPage">
                <li ng-show="$index==0" ><a href aria-label="Previous"   data="{{page.page.currentPage-1}}" ng-click="GoPage($event.target)"><span aria-hidden="true">«</span></a></li>
                <li><a href data="{{pageNo}}" ng-click="GoPage($event.target)">{{pageNo}}</a></li>
                <li ng-show="$index==4"> <a href aria-label="Next" data="{{page.page.currentPage+1}}" ng-click="GoPage($event.target)" ><span aria-hidden="true">»</span></a></li>
            </ul>
        </nav>
    </div>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/angular-1.0.1.min.js"></script>
    <script type="text/javascript">
    var ${className_x}App = angular.module('${className_x}App', []);



    ${className_x}App.controller('${className_x}Controller', ['$scope', '$http', function($scope, $http) {
        var myUrl = "/cc-server/${className_x}/list";
        $scope.page1 = [1, 2, 3, 4, 5];
        $http.get(myUrl, $scope.page).success(
            function(data) {
                $scope.list = data.list;
                $scope.page = data.page;
                var currentPage = data.page.page.currentPage;
                if (currentPage <= 5) {
                    $scope.currentPage = [1, 2, 3, 4, 5];
                } else {
                    $scope.currentPage = [currentPage - 2, currentPage - 1, currentPage, currentPage + 1, currentPage + 2];
                }

            }).error(function(status, response) {
            alert("服务器未响应");

        });
        $scope.GoPage = function(target) {

            $http.get(myUrl + "?page.currentPage=" + target.getAttribute('data'), $scope.page).success(
                function(data) {
                    $scope.list = data.list;
                    $scope.page = data.page;
                    var currentPage = data.page.page.currentPage;
                    if (currentPage <= 5) {
                        $scope.currentPage = [1, 2, 3, 4, 5];
                    } else {
                        $scope.currentPage = [currentPage - 2, currentPage - 1, currentPage, currentPage + 1, currentPage + 2];
                    }
                }).error(function(status, response) {});
        }


    }])
    </script>
</body>

</html>
