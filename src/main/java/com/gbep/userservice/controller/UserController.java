package com.gbep.userservice.controller;

import com.gbep.userservice.model.User;
import com.gbep.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/level/{userId}")
    public Long  getLevelById(@PathVariable Integer userId) {
        return userService.getLevelById(userId);
    }

    @GetMapping("/nog/{id}")
    public Long getNumbersOfGameById(@PathVariable("id") Integer userId) {
        return userService.getNumbersOfGameById(userId);
    }

    @PostMapping("/update")
    public void updateUserStatus(@RequestBody Integer userId) {
         userService.refreshUserStatus(userId);
    }

}
