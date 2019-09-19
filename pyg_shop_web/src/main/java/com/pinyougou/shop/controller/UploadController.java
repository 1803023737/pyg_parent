package com.pinyougou.shop.controller;

import com.itpyg.util.FastDFSClient;
import entity.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    //上传文件
    @RequestMapping("/upload")
    public Result upload(MultipartFile file) {

        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //扩展名
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            //获得保存路径
            String url = fastDFSClient.uploadFile(file.getBytes(), extName);
            System.out.println("配置远程文件服务器IP："+FILE_SERVER_URL);
            //完整图片远程保存地址
            url=FILE_SERVER_URL+url;
            return new Result(true,url);
        } catch (Exception e) {
            return new Result(false,"上传失败");
        }


    }

}
