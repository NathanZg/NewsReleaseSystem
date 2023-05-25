package com.news.service.impl;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news.entity.NewsData;
import com.news.entity.User;
import com.news.mapper.UserMapper;
import com.news.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {this.userMapper = userMapper;}
    @Override
    public boolean register(User user)  {

            //检查用户是否已存在
            User existingUser = userMapper.selectByName(user.getName());
            if (existingUser != null) {
                System.out.println("用户名已经存在。");
                return false;
            }
            // 添加用户
            userMapper.insert(user);
        return userMapper.selectByName(user.getName()) != null;

    }

    @Override
    public List<User> selectAllUsers() {

        return userMapper.selectAll();
    }

    @Override
    public User selectByName(User user) {

       User user1=userMapper.selectByName(user.getName());
        if (user1==null){
            System.out.println("查询用户为空");
            return null;
        }
        return user1;


    }

    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteById(id) > 0;
    }


    @Override
    public boolean setAdmin( User user) {
        return userMapper.setAdmin(user.getName(),user.getRole());
    }

    @Override
    public boolean updatePassword(User user) {
       return userMapper.updateByPassword(user.getName(),user.getPassword());
    }
}
