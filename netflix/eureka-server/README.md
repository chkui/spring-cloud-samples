**spring eureka 的配置众多，结构上分为四个部分** 

1. instance：对单个实例的控制，在客户端进行配置.
2. client:客户端的设置。在客户端进行配置.
3. server:服务端配置，与服务端提供的内容相关。在eureka服务器进行配置.
4. dashboard:可视化仪表盘相关的配置

**eureka的官方配置文件没有找到，以下内容来自于github的源码、wiki以及互联网**
1. 官方wiki关于配置的说明：https://github.com/Netflix/eureka/wiki/Configuring-Eureka.
2. eureka有独立的instance和client配置，但是没有找到服务端配置，文档的意思是服务端配置是根据client而独立服务的。
1. clientConfig源码：https://github.com/Netflix/eureka/blob/master/eureka-client/src/main/java/com/netflix/appinfo/EurekaInstanceConfig.java
1. instanceConfig源码码：https://github.com/Netflix/eureka/blob/master/eureka-client/src/main/java/com/netflix/discovery/EurekaClientConfig.java
1. spring eureka中文参考资料：https://www.cnblogs.com/tiancai/p/9593648.html