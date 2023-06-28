package com.octskyout.blog.repository;

import com.octskyout.blog.entity.BbsPostTag;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostTagRepository extends R2dbcRepository<BbsPostTag, UUID> {
}
