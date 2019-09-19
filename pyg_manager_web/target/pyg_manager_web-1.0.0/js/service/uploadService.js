app.service("uploadService", function ($http) {

    this.uploadFile = function () {
       // alert(123);
        //上二进制数据
        var formData = new FormData();
        formData.append('file', file.files[0]);//file 文件上传框的name值
        //'Content-Type': undefined  制定上传文件类型    默认式json类型
        return $http({
            url: '../upload.do',
            method: 'post',
            data: formData,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        });
    }


})