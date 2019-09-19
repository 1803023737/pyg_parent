package com.cloud.customer.controller;

import com.cloud.customer.client.UserClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("customer")
//@DefaultProperties(defaultFallback = "defaultFallback")//可以在方法和类上
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Resource
    private UserClient userClient;

    //负载均衡
    //@Autowired
    //private RibbonLoadBalancerClient client;

    //@RequestMapping("{id}")
    //@HystrixCommand(fallbackMethod = "queryFallback")//降级逻辑  使用该特有的降级方法
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "4000")
    //})//降级逻辑  使用统一的降级方法
    //@HystrixCommand
    //public String queryUserById(@PathVariable("id") Long id) {
    //
    //    //获取服务实例  根据服务名称获得服务
    //    //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
    //    ////从实例中获取ip和端口
    //    //ServiceInstance serviceInstance = instances.get(0);
    //    ////主机ip
    //    //String host = serviceInstance.getHost();
    //    ////端口号
    //    //int port = serviceInstance.getPort();
    //    //log.info("服务的ip为："+host+",服务的端口号为："+port);
    //    //String url="http://"+host+":"+port+"/user/"+id;
    //    //log.info("服务的url为："+url);
    //
    //    String url = "http://user-service/user/" + id;
    //    String user = restTemplate.getForObject(url, String.class);
    //    return user;
    //
    //}

    //调用失败 回调方法  返回值参数与上面调用方法一致  针对某个方法单独的降级方法
    public String queryFallback(Long id) {
        return "服务器正忙";
    }

    //统一的降级方法
    public String defaultFallback() {
        return "服务器正忙1";
    }

    //@RequestMapping("{id}")
    //public User queryUserById(@PathVariable("id") Long id){
    //
    //    //获取服务实例  根据服务名称获得服务
    //    List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
    //    //从实例中获取ip和端口
    //    ServiceInstance serviceInstance = instances.get(0);
    //    //主机ip
    //    String host = serviceInstance.getHost();
    //    //端口号
    //    int port = serviceInstance.getPort();
    //    //log.info("服务的ip为："+host+",服务的端口号为："+port);
    //    String url="http://"+host+":"+port+"/user/"+id;
    //    User user = restTemplate.getForObject(url, User.class);
    //    return user;
    //}

    //测试  hystrix 服务熔断器模拟
    //@RequestMapping("/id/{id}")
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
    //        @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//测试调用次数  10次开始记录百分比
    //        @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//close状态转化为half close状态时间 10s
    //        @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50"),//失败占用百分比
    //})
    //public String queryUserById2(@PathVariable("id") Long id){
    //    //测试熔断
    //    if (id%2==0){
    //        throw new RuntimeException("运行期异常！！");
    //    }else {
    //        //ServiceInstance serviceInstance = client.choose("user-service");
    //        //获取服务实例  根据服务名称获得服务
    //        //List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
    //        ////从实例中获取ip和端口
    //        //ServiceInstance serviceInstance = instances.get(0);
    //        ////主机ip
    //        //String host = serviceInstance.getHost();
    //        ////端口号
    //        //int port = serviceInstance.getPort();
    //        //log.info("服务的ip为："+host+",服务的端口号为："+port);
    //        //String url="http://"+host+":"+port+"/user/"+id;
    //        String url="http://user-service/user/"+id;
    //        String user = restTemplate.getForObject(url, String.class);
    //        return user;
    //    }
    //}

    //@RequestMapping("/id/{id}")
    //@HystrixCommand(commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
    //        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//测试调用次数  10次开始记录百分比
    //        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//close状态转化为half close状态时间 10s
    //        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),//失败占用百分比
    //})
    //public String queryUserById2(@PathVariable("id") Long id) {
    //
    //    //String url = "http://user-service/user/" + id;
    //    //String user = restTemplate.getForObject(url, String.class);
    //
    //    //使用feign
    //    String user = userClient.queryUserById2(id);
    //    return user;
    //}

    @RequestMapping("/id/{id}")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//测试调用次数  10次开始记录百分比
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//close状态转化为half close状态时间 10s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),//失败占用百分比
    })
    public String queryUserById2(@PathVariable("id") Long id) {

        //String url = "http://user-service/user/" + id;
        //String user = restTemplate.getForObject(url, String.class);

        //使用feign
        String user = userClient.queryUserById2(id);
        return user;
    }





}
