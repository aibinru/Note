# SpringCloud Alibaba Nacos



​		

###### -使用nacos

引入依赖

```
        <!--注册发现服务-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
            <version>2.1.3.RELEASE</version>
        </dependency>
        <!--配置管理服务-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
            <version>2.1.0.RELEASE</version>
        </dependency>
```

​		下载nacos

```
进入可视化界面
http://localhost:8848/nacos
```

1. application.yml配置nacos地址

```yaml
spring:
#1.写应用程序名
  application:
    name: shopping-product
    #添加nacos地址
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
#3.添加
```



​		bootstrap.properties 配置nacos地址 
​		这个是干嘛用的

```properties
#应用程序名称
spring.application.name=shopping-coupon
#nacos地址
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
#
spring.cloud.nacos.config.namespace=11bf80d7-c635-4a9e-b274-5686c95c4aa8

```

2. 启动程序入口添加注解

```java
@EnableDiscoveryClient//服务注册发现中心
@SpringBootApplication
public class ShoppingCouponApplication {

}
```

注册完后启动nacos，可以在服务列表看到

![image-20231128213626617](E:\Code\Note\images\image-20231128213626617.png)

![image-20231128213302873](E:\Code\Note\images\image-20231128213302873.png)



###### 远程调用

引入依赖

```xml
 <!--远程调用服务-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>2.1.3.RELEASE</version>
</dependency>
```

引入依赖报错问题

```xml
Unresolved dependency: 'org.springframework.cloud:spring-cloud-starter-openfeign:jar:unknown'

<!--换旧一点的版本-->
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>

版本不对
```

在coupon服务下

```java
编写一个方法
@RequestMapping("/member/list")
    public R memberCoupons() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100减10。");
        return R.ok().put("coupons",Arrays.asList(couponEntity));
    }
```

在member服务下

```java
//启动程序引入openfeign
//开启远程调用功能  服务启动后，自动扫描这个包
@EnableFeignClients("com.shopping.member.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class ShoppingMemberApplication {
    
}

//在feign包下，编写调用远程方法的接口
@FeignClient("shopping-coupon")//远程的服务名称
public interface CouponFeignService {
    //相当于调用shopping-coupon服务下的 memberCoupons方法
    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();//和远程服务的名称保持一致
}
```

测试-MemberController类下

```java
 @RequestMapping("/coupons")//获取当前优惠卷信息
    public R test() {
        MemberEntity member = new MemberEntity();
        member.setNickname("abr");
        R membercoupons = couponFeignService.membercoupons();//远程调用
        return  R.ok().put("member",member).put("coupons",membercoupons.get("coupons"));
    }
```

启动报错

```
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2023-11-28 22:27:50.222 ERROR 24452 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

The bean 'nacosDiscoveryClient', defined in class path resource [com/alibaba/cloud/nacos/discovery/NacosDiscoveryClientConfiguration.class], could not be registered. A bean with that name has already been defined in class path resource [com/alibaba/cloud/nacos/discovery/NacosDiscoveryClientAutoConfiguration.class] and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true


--在yml文件下添加下面的配置
  main:
    allow-bean-definition-overriding: true
##当出现相同名字的类进行注册时，准许覆盖注册

```

总结

```
1. nacos的下载，yml文件配置，启动类添加注解，
2. 远程调用，依赖添加，主动调用的服务，启动程序添加feign注解，编写feign包及接口，直接使用被调用的一方的url
```



## 22集看完 2023-11-28 22:23



Git问题

```
本地项目和远程项目有冲突

https://blog.csdn.net/Hello_World_QWP/article/details/80872257

```

