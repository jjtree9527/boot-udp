package top.inson.boot.service;

/**
 * @author jingjitree
 * @date 2022/5/25 17:51
 */
public interface IUdpSender {

    /**
     * 发送消息
     * @param message
     */
    void sendMessage(String message);


}
