package demo1;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo06 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * 设置content-disposition响应头，让浏览器下载文件
         */
        //response.setHeader("content-disposition", "attachment;filename=1.txt");
        System.out.println("默认content-type："+response.getContentType()+response.getCharacterEncoding());
        response.setContentType("application/json; charset=utf-8");
        JSONObject result=new JSONObject();
        result.put("code", 1);
        result.put("message", "张三李四");
        response.getWriter().print(result.toJSONString());

}
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}