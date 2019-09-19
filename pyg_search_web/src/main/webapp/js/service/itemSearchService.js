app.service('searchService',function ($http) {

    //service方法
    this.search=function(searchMap){
        return $http.post('itemSearch/search.do',searchMap);
    }


})