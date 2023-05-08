package top.inson.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import top.inson.boot.constant.UdpConfigConstant;

/**
 * @author jingjitree
 * @description
 * @date 2022/5/25 18:16
 **/
@Configuration
public class UdpIntegrationClientConfig {
    @Autowired
    private UdpConfigConstant udpConfigConstant;

    @Bean
    @ServiceActivator(inputChannel = "udpOut")
    UnicastSendingMessageHandler unicastSendingMessageHandler(){

        return new UnicastSendingMessageHandler(udpConfigConstant.getHostname(), udpConfigConstant.getPort());
    }

}
