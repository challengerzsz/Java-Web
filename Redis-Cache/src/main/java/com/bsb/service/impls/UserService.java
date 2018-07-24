package com.bsb.service.impls;

import com.bsb.common.ServerResponse;
import com.bsb.jpa.IUserRepository;
import com.bsb.mapper.IUserMapper;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zeng
 * @Date: 2018/7/24 10:48
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ServerResponse<String> insertUser(String username, String password) {

        if (username == null || password == null) {
            return ServerResponse.createBySuccessMsg("新建失败");
        }

        int result = userMapper.insert(new User(username, password));

        if (result == 0) {
            return ServerResponse.createByErrorMsg("新建用户失败");
        }

        return ServerResponse.createBySuccessMsg("创建用户成功");
    }

    @Override
    @Cacheable
    public ServerResponse<List<User>> listUsers() {

        List<User> userList = userRepository.findAll();
        if (userList.size() == 0) {
            return ServerResponse.createByErrorMsg("无信息");
        }

        return ServerResponse.createBySuccess("查询成功", userList);
    }
}
