package com.octskyout.blog.controller;

import com.octskyout.blog.entity.BbsPost;
import com.octskyout.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepository;


    @QueryMapping(name = "findAllPosts")
    public Flux<BbsPost> findAllPosts() {
        return postRepository.findAll();
    }
}
