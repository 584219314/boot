package boot;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistor implements ApplicationRunner {

    @Value("${zookeeper.address}")
    private String registerAddress;

    @Value("${service.address}")
    private String serviceAddress;

    @Value("${service.port}")
    private Integer servicePort;

    public void run(ApplicationArguments args) throws Exception {
        //注册中心
        CuratorFramework client = CuratorFrameworkFactory.newClient(registerAddress, new RetryOneTime(1000));
        client.start();
        client.blockUntilConnected();

        //注册服务
        ServiceInstance <Object> instance = ServiceInstance.builder().name("contract").address(serviceAddress).port(servicePort).build();

        ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class).client(client).basePath("/sstps").build();

        serviceDiscovery.registerService(instance);
        serviceDiscovery.start();
        System.out.println("Registe successed");



    }

}
