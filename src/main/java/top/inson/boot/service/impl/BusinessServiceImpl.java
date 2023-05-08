package top.inson.boot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.inson.boot.service.IBusinessService;

/**
 * @author jingjitree
 * @description
 * @date 2022/5/21 18:52
 **/
@Slf4j
@Service
public class BusinessServiceImpl implements IBusinessService {

    @Override
    @Async("threadPoolTaskExecutor")
    public void udpHandlerMethod(String message) {
        log.info("开始处理业务");


    }

}
