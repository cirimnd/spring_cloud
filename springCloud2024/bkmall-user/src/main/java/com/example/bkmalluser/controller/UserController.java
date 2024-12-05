package com.example.bkmalluser.controller;


import com.example.bkmallcommon.domain.dto.UserDto;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import com.example.bkmalluser.entity.Result;
import com.example.bkmalluser.entity.Role;
import com.example.bkmalluser.entity.User;
import com.example.bkmalluser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //id查询用户

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Long id){
        User user= userService.lambdaQuery()
                .eq(User::getUserid,id).one();

        return Result.success(user);
    }

    @GetMapping("/dto/{id}")
    public UserDto getUserDtoById(@PathVariable("id") Long id){
        User user= userService.lambdaQuery()
                .eq(User::getUserid,id).one();
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
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

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User userIn = userService.lambdaQuery().eq(User::getUserName, user.getUserName()).one();
        if (userIn == null) {
            return Result.error("用户不存在");
        }
        else if(userIn.getPassword().equals(user.getPassword())){
            if(userIn.getRole()==Role.USER)
                return Result.success("登录成功！");
            else
                return Result.error("您是管理员，请在管理员页面登录！");
        }
        else{
            return Result.error("密码错误！");
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
    public Result deleteUserById(@PathVariable("id") Long id) {
        boolean isRemoved = userService.removeById(id);
        if (isRemoved) {
            return Result.success("用户删除成功");
        } else {
            return Result.error("用户删除失败");
        }
    }


    //id修改用户姓名
    @PutMapping("/{id}/name")
    public Result updateUserName(@PathVariable("id") Long id, @RequestParam String newName) {
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
    public Result updateUserPassword(@PathVariable("id") Long id, @RequestParam String newPassword) {
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
