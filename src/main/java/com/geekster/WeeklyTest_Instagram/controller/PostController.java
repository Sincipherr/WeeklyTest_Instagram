package com.geekster.WeeklyTest_Instagram.controller;

import com.geekster.WeeklyTest_Instagram.model.Post;
import com.geekster.WeeklyTest_Instagram.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService ps;

    @PostMapping(value = "/save")
    public void savepost(@RequestBody Post post){
        ps.savepost(post);
    }

    @GetMapping(value = "/get")
    public List<Post> getpost(){
        return ps.getpost();
    }
}
