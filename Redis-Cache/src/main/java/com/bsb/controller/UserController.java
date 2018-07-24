package com.bsb.controller;

import com.bsb.common.ServerResponse;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: zeng
 * @Date: 2018/7/24 10:34
 */
@RestController
@RequestMapping("/user")
@EnableRedisHttpSession
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService userService;


    @PostMapping("/register")
    public ServerResponse<String> register(String username, String password) {

        return userService.insertUser(username, password);
    }


    @GetMapping("/hello")
    public ResponseEntity<?> hello(HttpSession session) {
        if (session.isNew()) {
            logger.info("Successfully creates a session ，the id of session ：" + session.getId());
            session.setAttribute("key", "hello");
        } else {
            logger.info("session already exists in the server, the id of session ："+ session.getId());
            logger.info(session.getAttribute("key").toString());
        }
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/list")
    public ServerResponse<List<User>> listUsers() {

        return userService.listUsers();
    }
}
