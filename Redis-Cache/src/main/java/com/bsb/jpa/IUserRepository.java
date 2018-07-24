package com.bsb.jpa;

import com.bsb.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zeng
 * @Date: 2018/7/24 15:20
 */
public interface IUserRepository extends JpaRepository<User, Long> {

}
