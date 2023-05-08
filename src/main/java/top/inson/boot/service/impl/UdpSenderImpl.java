package top.inson.boot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.inson.boot.constant.UdpConfigConstant;
import top.inson.boot.service.IUdpSender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author jingjitree
 * @description
 * @date 2022/5/25 17:52
 **/
@Slf4j
@Service
public class UdpSenderImpl implements IUdpSender {
    @Autowired
    private UdpConfigConstant udpConfigConstant;


    @Override
    public void sendMessage(String message) {
        log.info("推送消息内容message：{}", message);
        InetSocketAddress socketAddress = new InetSocketAddress(udpConfigConstant.getHostname(), udpConfigConstant.getPort());
        byte[] bytes = message.getBytes();
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(bytes, bytes.length, socketAddress);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
