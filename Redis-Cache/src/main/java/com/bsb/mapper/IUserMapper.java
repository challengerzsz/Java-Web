package com.bsb.mapper;

import com.bsb.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Author: zeng
 * @Date: 2018/7/24 10:39
 */
@Mapper
@CacheConfig(cacheNames = "users")
public interface IUserMapper {

    @Insert("INSERT INTO user_table VALUES(null, #{user.username}, #{user.password})")
    int insert(@Param("user") User user);

    @Select("SELECT * FROM user_table WHERE username = #{username}")
    @Cacheable(key = "#p0")
    User find(String username);

}
