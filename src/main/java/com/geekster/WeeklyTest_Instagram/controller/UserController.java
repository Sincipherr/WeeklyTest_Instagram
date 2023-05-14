package com.geekster.WeeklyTest_Instagram.controller;

import com.geekster.WeeklyTest_Instagram.dto.SignInInput;
import com.geekster.WeeklyTest_Instagram.dto.SignUpInput;
import com.geekster.WeeklyTest_Instagram.model.User;
import com.geekster.WeeklyTest_Instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService us;

    @PostMapping(value = "/signup")
    public String signup(@RequestBody SignUpInput signupdto){
        return us.signup(signupdto);
    }

    @PostMapping(value = "/signin")
    public String signin(@RequestBody SignInInput signindto){
        return us.signin(signindto);
    }

    @PutMapping(value = "/update/{id}/{email}")
    public String updatedetails(@PathVariable Integer id,@PathVariable String email){
        return us.updatedetails(id,email);
    }
}
