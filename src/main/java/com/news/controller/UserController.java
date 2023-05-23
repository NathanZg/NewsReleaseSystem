package com.news.controller;
import com.news.entity.User;
import com.news.service.UserService;
import com.news.utils.Response;
import com.news.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService){this.userService=userService;}
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Response<User> login(@RequestBody User user) {
        User user1= userService.selectByName(user);
        if (Objects.equals(user1.getPassword(), user.getPassword())){
            return ResponseUtils.success(user1);
        }else {
            return ResponseUtils.fail("登陆失败");
        }
    }


    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Response<String> createUser(@RequestBody User user){
        // 获取请求体中的用户信息，并进行注册
        boolean success = userService.register(user);

        // 根据注册结果返回相应的响应码
        if (success) {
            return  ResponseUtils.success("注册成功");
        } else {
            return  ResponseUtils.fail("注册失败");
        }
    }

    /**
     * 注销用户
     */
    @DeleteMapping("/delete/{id}")
    public Response<String> deleteUser(@PathVariable Integer id) {
        boolean flag= userService.deleteUser(id);
        if (flag){
            return ResponseUtils.success("注销成功");
        }else {
            return ResponseUtils.fail("注销失败");
        }

    }
    @PutMapping
    public Response<User> updatePassword(@RequestBody User user){
        boolean flag=userService.updatePassword(user);
        if (flag){
            return ResponseUtils.success(user);
        }else {
            return ResponseUtils.fail("密码修改失败");
        }
    }
    @PostMapping("/setAdmin")
    public Response<User> setAdmin(@RequestBody User user){
        boolean flag=userService.setAdmin(user);
        if (flag){
            return ResponseUtils.success(user);
        }else {
            return ResponseUtils.fail("管理员设置失败");
        }
    }
}
