package top.inson.boot.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jingjitree
 * @description
 * @date 2022/5/21 19:02
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "udp-config")
public class UdpConfigConstant {

    private String hostname;
    private Integer port;

}
