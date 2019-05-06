# Feign用于Service接口管理

## 接口维护问题

Feign通过一组注解快速实现接口调用。通常情况下接口的维护涉及2个问题：
1. 接口演进
2. 接口契约（contract）

以下针对这2个问题说明Feign的使用。

## Feign定义
我们通过以下方式定义一个Feign接口。

```Java
@FeignClient("evolution-consumer")
public interface Order {
	/**
	 * 订单id
	 * @param id 要获取的订单对应的id
	 * @return
	 */
	@RequestMapping(value = "/transaction/getOrder/{id}",method = RequestMethod.GET)
	OrderBase getOrder(@PathVariable("id")Long id);
}
```

实际上在Feign包的底层就是调用RestTemplate实现的

## 接口演进及契约管理

如果使用`RestTemplate`的方式调用下游接口，需要对下游接口的文档和演进过程有非常清晰的认识。现在可以用Feign将Vo（ViewObject）和接口（Interface）抽离出来为上游提供接口服务。

基于以上理由单独创建了`evolution-service`工程用于独立接口和实体。当某个微服务需要使用另外一个微服务的结构时，引入类似于`evolution-service`这样通过Feign标注的项目即可。

在使用了`Feign`之后我们可以围绕着`Feign`来控制接口演进和接口契约：
1. 演进基于实体的扩展和接口的重载。
2. 契约就是基于类似于`evolution-service`这种定义了`Feign`的工程。