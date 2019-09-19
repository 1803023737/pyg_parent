app.controller('contentController',function ($scope,contentService) {

    $scope.contentList=[];

    $scope.findListByContentCategoryId=function (categoryId) {
        contentService.findListByContentCategoryId(categoryId).success(
            function (response) {
              $scope.contentList[categoryId]=response;
        })
    }

    $scope.search=function () {
        //alert(123);
        location.href="http://localhost:5180/search.html#?keywords="+$scope.keywords;
    }


})