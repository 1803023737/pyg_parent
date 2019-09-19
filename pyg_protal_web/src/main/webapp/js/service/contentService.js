app.service('contentService',function ($http) {
 
    this.findListByContentCategoryId=function (categoryId) {
        return $http.get('content/findListByContentCategoryId.do?id='+categoryId);
    }


})