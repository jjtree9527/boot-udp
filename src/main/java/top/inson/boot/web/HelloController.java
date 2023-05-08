package top.inson.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.inson.boot.service.IUdpSender;

/**
 * @author jingjitree
 * @description
 * @date 2022/5/25 18:09
 **/
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private IUdpSender udpSender;

    @Autowired
    private UnicastSendingMessageHandler unicastSendingMessageHandler;


    @PostMapping("/simpleSend")
    public String simpleSend(@RequestBody String message){

        udpSender.sendMessage(message);
        return "success";
    }

    @PostMapping("/springSender")
    public String springSender(@RequestBody String message){

        unicastSendingMessageHandler.handleMessage(MessageBuilder.withPayload(message).build());

        return "success";
    }

}
