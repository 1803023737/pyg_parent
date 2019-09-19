var app=angular.module('pinyougou',[]);

//定义过滤器
app.filter('trustHtml',['$sce',function ($sce) {

    return function (data) {//data为未过滤内容
         return $sce.trustAsHtml(data);//返回的是过滤后的内容  html转换
    }
}]);