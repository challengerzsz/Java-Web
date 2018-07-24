package com.bsb.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: zeng
 * @Date: 2018/7/24 21:03
 */
@Component
public class EventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publish(Object msg) {
        applicationContext.publishEvent(new LoginSuccessEvent(msg));
    }
}
