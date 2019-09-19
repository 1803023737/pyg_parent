app.controller('itemController',function($scope){
	  
	$scope.num=1;
	//数量加减
	$scope.addNum=function(x){
		//alert(123);
		$scope.num=$scope.num+x;
		if($scope.num<=1){
		$scope.num=1;
		}			
	}
	

//选中规格变量
$scope.specificationItems={};
	
//选中规格方法
$scope.selectspecificationItems=function(key,value){
$scope.specificationItems[key]=value;
//动态规格变，页面价格名称跟着变
searchSku();
}

$scope.isSelecte=false;

//判断规格是否被选中
$scope.isSelectedSpec=function(key,value){
	
	if($scope.specificationItems[key]==value){
		return true;
	}else{
		return false;
	}	
}

//当前选中的sku
$scope.sku={};
$scope.loadSku=function(){
//加载默认sku  第一个商品
$scope.sku=skuList[0];
//浅克隆 引用地址一样  不建议
//$scope.specificationItems=$scope.sku.spec;

//深克隆
$scope.specificationItems=JSON.parse(JSON.stringify($scope.sku.spec));

}

//匹配规格对象是否相等
matchObj=function(map1,map2){
	
	//保证双向相同
	for(var k in map1){
		if(map1[k]!=map2[k]){
			return false;
		}	
	}
	
	for(var k in map2){
		if(map1[k]!=map2[k]){
			return false;
		}	
	}
	return true; 
}

//查找sku
searchSku=function(){

   for(var i=0;i<skuList.length;i++){
       if(matchObj(skuList[i].spec,$scope.specificationItems)){
	     $scope.sku=skuList[i];
		 return;
	   }
   }
$scope.sku={'id':1,'title':'-----','price':0};
}


    
});	
