package com.bsb.listener;

import com.bsb.event.LoginSuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zeng
 * @Date: 2018/7/24 20:45
 */
@Component
public class BookApplicationListener implements ApplicationListener<LoginSuccessEvent> {

    @Override
    public void onApplicationEvent(LoginSuccessEvent loginSuccessEvent) {
        System.out.println(loginSuccessEvent.getSource().toString());
    }
}
