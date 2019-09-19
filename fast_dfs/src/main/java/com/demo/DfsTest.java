package com.demo;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class DfsTest {

    public static void main(String[] args) throws IOException, MyException {

        //加载配置文件
        ClientGlobal.init("F:\\work_idea\\boot\\pyg_parent\\fast_dfs\\src\\main\\resources\\fdfs_client.conf");
        //构建TrackerClient  管理者客户端
        TrackerClient client = new TrackerClient();
        //管理者服务端
        TrackerServer trackerServer = client.getConnection();

        //声明构建存储的服务端
        StorageServer storageServer=null;
        //创建storageclient
        StorageClient storageClient=new StorageClient(trackerServer,storageServer);
        //上传文件
        String[] jpgs = storageClient.upload_file("C:\\Users\\dell\\Desktop\\QQ截图20190218172810.jpg", "jpg", null);
        for (String jpg : jpgs) {
            System.out.println(jpg);
            //返回值  第一个值式组  第二个式地址
            //group1
            //M00/00/00/wKgZhVyD_7iAF8XJAABWAe2wIRc451.jpg

            //group1/M00/00/00/wKgZhVyD_7iAF8XJAABWAe2wIRc451.jpg
        }
    }

}
