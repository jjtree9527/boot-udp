package top.inson.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import top.inson.boot.constant.UdpConfigConstant;
import top.inson.boot.service.IBusinessService;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author jingjitree
 * @description
 * @date 2022/5/21 18:47
 **/
@Slf4j
@Configuration
public class UdpServerConfiguration {
    @Autowired
    private UdpConfigConstant udpConfigConstant;
    @Autowired
    private IBusinessService businessService;

    /**
     * udp消息接收
     * @return
     */
    @Bean
    IntegrationFlow integrationFlow(){

        return IntegrationFlows.from(Udp.inboundAdapter(udpConfigConstant.getPort())).channel("udpChannel").get();
    }

    /**
     * 转换器
     */
    @Transformer(inputChannel = "udpChannel", outputChannel = "udpFilter")
    String transformer(@Payload byte[] payload, @Headers Map<String, Object> headers){
        String message = new String(payload, Charset.defaultCharset());
        log.info("transformer日志: message: {}, header:{}", message, headers);
        return message;
    }

    /**
     * 过滤器
     */
    @Filter(inputChannel = "udpFilter", outputChannel = "udpRouter")
    boolean filter(String message, @Headers Map<String, Object> headers){
        log.info("filter日志: ,message:{},头部headers内容:{}", message, headers);

        return true;
    }

    /**
     * 路由分发处理器
     */
    @Router(inputChannel = "udpRouter")
    String router(String message, @Headers Map<String, Object> headers){
        log.info("router日志, message: {}, headers: {}", message, headers);
        return "udpHandler";
    }

    /**
     * 处理器
     */
    @ServiceActivator(inputChannel = "udpHandler")
    void udpMessageHandler(String message){
        log.info("handler：，消息内容message：{}", message);
        businessService.udpHandlerMethod(message);
    }


}
