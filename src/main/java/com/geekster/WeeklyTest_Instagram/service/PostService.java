package com.geekster.WeeklyTest_Instagram.service;

import com.geekster.WeeklyTest_Instagram.Repository.IPostRepo;
import com.geekster.WeeklyTest_Instagram.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    IPostRepo pr;

    public void savepost(Post post) {
        pr.save(post);
    }

    public List<Post> getpost() {
       return (List<Post>) pr.findAll();
    }
}
