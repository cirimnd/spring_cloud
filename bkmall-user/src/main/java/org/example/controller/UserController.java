package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.entity.Result;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //id查询用户

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") int id){
        User user= userService.lambdaQuery()
                .eq(User::getUserid,id).one();

        return Result.success(user);
    }

    //name查用户
    @GetMapping("/name")
    public Result getBooksByName(@RequestParam String name) {
       User user= userService.lambdaQuery()
               .eq(User::getUserName,name).one();
       return Result.success(user);
    }

    //增加用户
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        boolean isSaved = userService.save(user);
        if (isSaved) {
            return Result.success(user);
        } else {
            return Result.error("用户添加失败");
        }
    }



    //增加管理员
    @PostMapping("/addAdmin")
    public Result addAdmin(@RequestBody User user) {
        user.setRole(Role.ADMIN); // 设置角色为管理员
        boolean isSaved = userService.save(user);
        if (isSaved) {
            return Result.success( user);
        } else {
            return Result.error("管理员添加失败");
        }
    }


    //id删除用户
    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable("id") int id) {
        boolean isRemoved = userService.removeById(id);
        if (isRemoved) {
            return Result.success("用户删除成功");
        } else {
            return Result.error("用户删除失败");
        }
    }


    //id修改用户姓名
    @PutMapping("/{id}/name")
    public Result updateUserName(@PathVariable("id") int id, @RequestParam String newName) {
        User user = userService.getById(id);
        if (user != null) {
            user.setUserName(newName);
            boolean isUpdated = userService.updateById(user);
            if (isUpdated) {
                return Result.success(user);
            }
        }
        return Result.error("用户姓名更新失败");
    }


    //id修改用户密码
    @PutMapping("/{id}/password")
    public Result updateUserPassword(@PathVariable("id") int id, @RequestParam String newPassword) {
        User user = userService.getById(id);
        if (user != null) {
            user.setPassword(newPassword);
            boolean isUpdated = userService.updateById(user);
            if (isUpdated) {
                return Result.success(user);
            }
        }
        return Result.error("用户密码更新失败");
    }
    //




}
