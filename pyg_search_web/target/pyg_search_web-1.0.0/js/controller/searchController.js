app.controller('searchController', function ($scope,$location, searchService) {

    //查询条件对象   构造查询对象
    $scope.searchMap = {
        'keywords': '',
        'category': '',
        'brand': '',
        'spec': {},
        'price': '',
        'pageNo': 1,
        'pageSize': 20,
        'sort': '',
        'sortField': ''
    };


    $scope.search = function () {
        //字符串转换为数字
        // 如果输入框跳转  那边传参是字符串  会后台转换错误 这边提前转换为数字
        $scope.searchMap.pageNo = parseInt($scope.searchMap.pageNo);
        //查询操作
        searchService.search($scope.searchMap).success(function (response) {
            $scope.resultMap = response;

            $scope.pageLabel = [];//将分页放在一个数组中
            //查询后显示第一页
            //$scope.searchMap.pageNo=1;
            //构建分页栏
            buildPageHelper();
        });
    }


    buildPageHelper = function () {
        //构建分页栏
        //console.log("获得总页数："+$scope.resultMap.totalPages);

        var firstPage = 1;
        var lastPage = $scope.resultMap.totalPages;

        $scope.firstDot = true;
        $scope.lastDot = true;

        //页码显示需求
        if ($scope.resultMap.totalPages > 5) {//大于五条
            //判断当前页的位置
            if ($scope.searchMap.pageNo <= 3) {
                lastPage = 5;
                $scope.firstDot = false;
            } else if ($scope.searchMap.pageNo >= $scope.resultMap.totalPages - 2) {
                firstPage = $scope.resultMap.totalPages - 4;
                $scope.lastDot = false;
            } else {
                lastPage = $scope.searchMap.pageNo + 2;
                firstPage = $scope.searchMap.pageNo - 2;
            }
        } else {
            $scope.firstDot = false;
            $scope.lastDot = false;
        }
        for (var i = firstPage; i <= lastPage; i++) {
            //数组中加入述职对象
            $scope.pageLabel.push(i);
        }
        console.log("获得页码集合：" + $scope.pageLabel);
    }


    //添加搜索项
    $scope.addSearchItem = function (key, value) {

        if (key == 'category' || key == 'brand' || key == 'price') {//分类 品牌
            $scope.searchMap[key] = value;
        } else {//规格  比较灵活
            $scope.searchMap.spec[key] = value;
        }
        $scope.search();
    }

    $scope.removeSearchItem = function (key) {
        if (key == 'category' || key == 'brand' || key == 'price') {//分类 品牌
            $scope.searchMap[key] = '';

        } else {//规格  比较灵活
            //删除key
            delete $scope.searchMap.spec[key];
        }
    }


    //分页查询
    $scope.queryByPage = function (pageNo) {
        if (pageNo < 1 || pageNo > $scope.resultMap.totalPages) {
            alert("页码异常！");
            return;
        }
        var obj = event.srcElement;  //event在ie中自带有，可以不用传入，其他少数浏览器需要传入
        //alert(obj.innerHTML);
        // obj.parent.class="active";
        $scope.searchMap.pageNo = pageNo;
        $scope.search();
    }

    //判断当前页是否为第一页
    $scope.isTopPage = function () {
        if ($scope.searchMap.pageNo == 1) {
            return true;
        } else {
            return false;
        }
    }

    //判断当前页是否为最后一页
    $scope.isEndPage = function () {
        if ($scope.searchMap.pageNo == $scope.resultMap.totalPages) {
            return true;
        } else {
            return false;
        }
    }

    //排序查询
    $scope.sortSearch = function (sortField, sort) {
        $scope.searchMap.sortField = sortField;
        $scope.searchMap.sort = sort;
        $scope.search();
    }

    //判断关键字是否为品牌
    $scope.keywordsIsBrand = function () {
        for (var i = 0; i < $scope.resultMap.brandList.length; i++) {
            //关键字是包含品牌 字符串
            if ($scope.searchMap.keywords.indexOf($scope.resultMap.brandList[i].text) != -1) {
                return true;
            }
        }
        return false;
    }

    //首页跳转搜索页  传递关键字参数
    $scope.loadKeyWords=function () {
        $scope.searchMap.keywords=$location.search()['keywords'];
        console.log("keywords:"+$scope.searchMap.keywords);
        $scope.search();
    }
    
    

})