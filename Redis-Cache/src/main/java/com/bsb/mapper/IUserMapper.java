package com.bsb.mapper;

import com.bsb.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: zeng
 * @Date: 2018/7/24 10:39
 */
@Mapper
public interface IUserMapper {

    @Insert("INSERT INTO user_table VALUES(null, #{user.username}, #{user.password})")
    int insert(@Param("user") User user);

}
