package com.bsb.service.impls;

import com.alibaba.fastjson.JSON;
import com.bsb.dao.impls.UserDao;
import com.bsb.pojo.User;
import com.bsb.service.IUserService;
import com.bsb.utils.MatchUtil.MatchRegexUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService{
    private UserDao userDao = new UserDao();

    public UserService() throws IOException {
    }

    @Override
    public boolean login(String username, String password) throws SQLException {
        List<User> friends = userDao.login(username, password);

        if (friends != null) {
            System.out.println(JSON.toJSONString(friends));
            return true;
        }
        return false;
    }

    @Override
    public boolean register(String username, String password, String confirmedPassword, String email, String image)
            throws SQLException {

        if (password.equals(confirmedPassword) && MatchRegexUtil.checkEmail(email)) {
//            return userDao.register(username, password, email, image);
        }

        return false;
    }

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public User queryUser(String username) {
        return null;
    }

    @Override
    public List<User> queryAllUsers() {
        return null;
    }
}
