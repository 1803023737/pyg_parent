 //控制层 
app.controller('itemCatController' ,function($scope,$controller,typeTemplateService,itemCatService){
	
	$controller('baseController',{$scope:$scope});//继承

    //定义父级id
    $scope.parentId=0;

    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		itemCatService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		itemCatService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		itemCatService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	$scope.findOneZDY=function(id){
		itemCatService.findOneZDY(id).success(
			function(response){
				$scope.entity= response;
                console.log($scope.entity.id);
                console.log($scope.entity.name);
                $scope.entity.typeId=response.selectOptions;
			}
		);
	}


	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象
        $scope.entity.parentId=$scope.parentId;
		if ($scope.entity.typeId!=null){
            var type = $scope.entity.typeId;
            $scope.entity.typeId=type[0].id;
		}else{
            $scope.entity.typeId=0;
		}
		if($scope.entity.id!=null){//如果有ID
			serviceObject=itemCatService.update( $scope.entity ); //修改
		}else{
			serviceObject=itemCatService.add( $scope.entity  );//增加
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.findByParentId($scope.parentId);//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					console.lo
					$scope.findByParentId( $scope.parentId);//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}

    $scope.list=[];

    //搜索
    $scope.findByParentId=function(parentId){
				$scope.parentId=parentId;
        itemCatService.findByParentId(parentId).success(
            function(response){
            	console.log("parentId:"+parentId);
            	//查询后将parentId  赋值给当前要新增的类别的parentId
				//返回list展示
                $scope.list=response;
                //console.log($scope.list);
            }
        );
    }

    //定义级别
	$scope.grade=1;
    $scope.setGrade=function (value) {
        $scope.grade=value;
    }

    $scope.selectList=function (p_entity) {
		if($scope.grade==1){
            $scope.entity_1=null;
            $scope.entity_2=null;
		}
        if($scope.grade==2){
            $scope.entity_1=p_entity;
            $scope.entity_2=null;
        }
        if($scope.grade==3){
            $scope.entity_2=p_entity;
        }
        $scope.findByParentId(p_entity.id);
    }

    $scope.selectOptions={
        data:[]
    };

    $scope.selectOptionList=function () {
        typeTemplateService.selectOptionList().success(
			function (response) {
				console.log(response);
				$scope.selectOptions={data:response};
				console.log("==========================");
                console.log($scope.selectOptions);
        })
    }

    $scope.selectIds = [];//选中的ID集合

    //更新复选
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {//如果是被选中,则增加到数组
            $scope.selectIds.push(id);
        } else {//取消选中
            var idx = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(idx, 1);//删除
        }
    }



});	
