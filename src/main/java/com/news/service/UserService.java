package com.news.service;
import com.news.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
public interface UserService extends IService<User> {
    // 注册用户
    boolean register(User user) ;

    //查询所有用户
     List<User> selectAllUsers();
    // 通过用户名查询用户
    User selectByName(User user);
    // 删除用户
    boolean deleteUser(Integer id);
    // 设置用户为管理员
    boolean setAdmin(User user);
    //修改用户密码
    boolean updatePassword(User user);

}
