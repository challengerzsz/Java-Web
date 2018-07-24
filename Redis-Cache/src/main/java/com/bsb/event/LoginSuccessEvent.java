package com.bsb.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: zeng
 * @Date: 2018/7/24 20:42
 */
public class LoginSuccessEvent extends ApplicationEvent {

    public LoginSuccessEvent(Object source) {
        super(source);
    }

}
