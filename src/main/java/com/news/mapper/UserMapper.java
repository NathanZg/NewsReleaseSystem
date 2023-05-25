package com.news.mapper;

import com.news.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name = #{name}")
    public User selectByName(String name);
    @Update("update user set role= #{role} where name= #{name}")
    public boolean setAdmin(String name,String role);
    @Update("update user set password=#{password} where name=#{name}")
    public boolean updateByPassword(String name,String password);

}
