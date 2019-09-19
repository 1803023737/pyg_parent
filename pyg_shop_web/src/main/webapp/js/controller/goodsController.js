//控制层
app.controller('goodsController', function ($scope, $controller, $location, goodsService, uploadService, itemCatService, typeTemplateService) {

    //$location 是静态页面传参必须引入的服务
    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        goodsService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        goodsService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        goodsService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //查询实体
    $scope.findOneZDY = function () {
        //这个方法获得网址上所有参数  数组
        var id = $location.search()['id'];
        //alert(id);
        if (id == null) {
            return;
        }
        goodsService.findOneZDY(id).success(
            function (response) {
                $scope.entity = response;
                //将商品介绍赋值到富文本编辑器
                editor.html($scope.entity.tbGoodsDesc.introduction);
                //商品图片
                $scope.entity.tbGoodsDesc.itemImages = JSON.parse($scope.entity.tbGoodsDesc.itemImages);
                //扩展属性
                console.log("扩展属性：" + $scope.entity.tbGoodsDesc.customAttributeItems);
                $scope.entity.tbGoodsDesc.customAttributeItems = JSON.parse($scope.entity.tbGoodsDesc.customAttributeItems);
                console.log("扩展属性：" + $scope.entity.tbGoodsDesc.customAttributeItems);
                //规格属性
                console.log(typeof $scope.entity.tbGoodsDesc.specificationItems);//返回值是string
                $scope.entity.tbGoodsDesc.specificationItems=JSON.parse($scope.entity.tbGoodsDesc.specificationItems);
                //sku列表
               // $scope.entity.tbItemList=JSON.parse($scope.entity.tbItemList);//返回值是obj
                //console.log(typeof $scope.entity.tbItemList)
                var items=$scope.entity.tbItemList;
                //
                for (var i = 0; i < items.length; i++) {
                    var obj = items[i];
                    obj.spec=JSON.parse(obj.spec);
                }
            }
        );
    }


    //保存
    $scope.save = function () {
        $scope.entity.tbGoodsDesc.introduction = editor.html();
        var serviceObject;//服务层对象
        if ($scope.entity.tbGoods.id != null) {//如果有ID
            serviceObject = goodsService.update($scope.entity); //修改
        } else {
            serviceObject = goodsService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    alert("新增成功");
                    //$scope.entity = {};
                    //清空富文本编辑器
                   // $scope.entity.tbGoodsDesc.introduction = editor.html("");
                    //原生跳转
                    location.href="goods.html";
                } else {
                    alert(response.message);
                }
            }
        );
    }

    //保存
    $scope.add = function () {
        $scope.entity.tbGoodsDesc.introduction = editor.html();
        console.log("introduction:" + $scope.entity.tbGoodsDesc.introduction);
        var serviceObject;//服务层对象
        serviceObject = goodsService.add($scope.entity);
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    //重新查询
                    alert("新增成功");
                    $scope.entity = {};
                    //清空富文本编辑器
                    $scope.entity.tbGoodsDesc.introduction = editor.html("");
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除
    $scope.dele = function () {
        //获取选中的复选框
        goodsService.dele($scope.selectIds).success(
            function (response) {
                if (response.success) {
                    $scope.reloadList();//刷新列表
                    $scope.selectIds = [];
                }
            }
        );
    }

    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        goodsService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    $scope.entity_image = {};
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (response) {
            if (response.success) {
                console.log(response.message);
                $scope.entity_image.url = response.message;
            } else {
                alert(response.message);
            }
        })
    }

    //数组格式必须定义，不然会出现找不到对象的错误
    $scope.entity = {
        tbGoods: {},
        tbGoodsDesc: {itemImages: [], specificationItems: [], customAttributeItems: []}

    };

    //将当前上传的图片实体传入图片列表
    $scope.add_entity_image = function () {
        //将图片实体装入商品
        $scope.entity.tbGoodsDesc.itemImages.push($scope.entity_image);
    }


    $scope.remove_entity_image = function (index) {
        $scope.entity.tbGoodsDesc.itemImages.splice(index, 1);
    }


    //查询一级商品分类列表
    $scope.selectItemCatList1 = function () {
        itemCatService.findByParentId(0).success(function (response) {
            $scope.itemCatList1 = response;
        })
    }


//变量监控方法  前面市监控的参数  后面的是变量变化后  执行的方法   变量不变化  不做任何操作
    //查询二级商品分类
    $scope.$watch('entity.tbGoods.category1Id', function (newValue, oldValue) {
        //
        // alert(123);
        itemCatService.findByParentId(newValue).success(function (response) {
            $scope.itemCatList2 = response;
        })

    });

    $scope.$watch('entity.tbGoods.category2Id', function (newValue, oldValue) {
        //
        // alert(123);
        itemCatService.findByParentId(newValue).success(function (response) {
            $scope.itemCatList3 = response;
        })

    });

    $scope.$watch('entity.tbGoods.category3Id', function (newValue, oldValue) {
        //
        // alert(123);
        itemCatService.findOne(newValue).success(function (response) {
            //alert(response);
            //console.log(response);
            $scope.entity.tbGoods.typeTemplateId = response.typeId;
        })
    });


    $scope.brandList = {
        data: []
    };


    $scope.specList = [];

//    获得品牌下拉列表
    $scope.$watch('entity.tbGoods.typeTemplateId', function (newValue, oldValue) {

        typeTemplateService.findOne($scope.entity.tbGoods.typeTemplateId).success(function (response) {

            $scope.typeTemplate = response;
            $scope.brandList = JSON.parse($scope.typeTemplate.brandIds);
            // if ($scope.entity.tbGoodsDesc.customAttributeItems==null){}
            // if ($scope.entity.tbGoodsDesc.customAttributeItems == null ||$scope.entity.tbGoodsDesc.customAttributeItems.length==0) {
            //     $scope.entity.tbGoodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
            // }
            if ($location.search()['id']==null) {//新增页面  这个扩展属性这样赋值  //与编辑页面不同
                $scope.entity.tbGoodsDesc.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
            }
            // console.log($scope.entity.tbGoodsDesc.customAttributeItems);
        });

        //查询相关规格，包括规格列表
        typeTemplateService.selectSpecList(newValue).success(function (response) {
            $scope.specList = response;
            console.log($scope.specList);
        })
    });


    //勾选规格  复选框可以提供 复选框的name，value，以及源$event
    $scope.updateSpecAttribute = function ($event, name, value) {
        alert(name + "==" + value);
        var obj = $scope.searchObjectByKey($scope.entity.tbGoodsDesc.specificationItems, "attributeName", name);
        if (obj != null) {
            if ($event.target.checked) {
                obj['attributeValue'].push(value);
            } else {
                obj['attributeValue'].splice(obj['attributeValue'].indexOf(value), 1);
            }
            //当所有的都取消了
            if (obj['attributeValue'].length == 0) {
                $scope.entity.tbGoodsDesc.specificationItems.splice($scope.entity.tbGoodsDesc.specificationItems.indexOf(obj), 1);
            }
        } else {
            $scope.entity.tbGoodsDesc.specificationItems.push({"attributeName": name, "attributeValue": [value]});
        }

    };


    //创建itemList列表
    $scope.createItemList = function () {

        $scope.entity.tbItemList = [{spec: {}, price: 0, num: 9999, status: '0', isDefault: '0'}];

        //便于操作  将值重新赋值   用户选择的集合
        var items = $scope.entity.tbGoodsDesc.specificationItems;
        //循环遍历
        // console.log("items:" + items.length);
        for (var i = 0; i < items.length; i++) {
            //对自己变身！
            $scope.entity.tbItemList = addColum($scope.entity.tbItemList, items[i]['attributeName'], items[i]['attributeValue']);
        }
    }

    //私有方法  页面不调用可以不写$scope
    addColum = function (list, columName, columValues) {
        var newList = [];
        // console.log(" list.length" + list.length);
        for (var i = 0; i < list.length; i++) {//循环
            //第一层循环  取出来
            var oldRow = list[i];
            //console.log("oldRow:"+oldRow);
            // console.log("length:" + columValues.length);
            for (var j = 0; j < columValues.length; j++) {
                //第二层循环  新的一行  深度克隆
                var newRow = JSON.parse(JSON.stringify(oldRow));
                //添加列名称
                newRow.spec[columName] = columValues[j];
                newList.push(newRow);
            }
        }
        return newList;
    }

    //状态展示
    $scope.status = ['未审核', '已审核', '审核未通过', '已关闭'];

//商品分类展示
    $scope.itemCatList = [];
    //查询商品类别
    $scope.findItemCatList = function () {
        itemCatService.findAll().success(function (response) {
            for (var i = 0; i < response.length; i++) {
                var obj = response[i];
                $scope.itemCatList[obj.id] = obj.name;
            }
            // console.log("分类集合:" + $scope.itemCatList);
        })
    }

    //校验是否勾选回显
    $scope.checkAttributeValue=function (specName,optionName) {

        var items=$scope.entity.tbGoodsDesc.specificationItems;
        // console.log("items:"+items);
        var obj= $scope.searchObjectByKey(items,"attributeName",specName);
        // console.log("obj:"+obj);
        if (obj==null){
            return false;
        }else{
            if (obj.attributeValue.indexOf(optionName)>=0){//代表勾选的规格存在
                return true;
            }else{
                return false;
            }
        }
    }
    
    

});
