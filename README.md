# sofa-rpc-hystrix with spring-cloud-hystrix

在 Spring Boot（Sofa Boot） 项目中使用了 Sofa RPC Hystrix 后，通过 Spring Cloud Hystrix 展示 Dashboard 以及更改配置。

首先在本地安装 ScienJus/hystrix-integration 分支的 sofa-rpc

```
git clone git@github.com:ScienJus/sofa-rpc.git
git checkout hystrix-integration
mvn clean install -DskipTests -Dmodule.install.skip=false
```

然后分别启动该项目的 QuickStartServer 和 QuickStartClient

最后打开 `http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream` 查看 Dashboard：

![Dashboard](./dashboard.jpg)