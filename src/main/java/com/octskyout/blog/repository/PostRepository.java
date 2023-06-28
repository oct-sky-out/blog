package com.octskyout.blog.repository;

import com.octskyout.blog.entity.BbsPost;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface PostRepository extends R2dbcRepository<BbsPost, UUID> {
}
