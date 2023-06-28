package com.octskyout.blog.repository;

import com.octskyout.blog.entity.BbsTag;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TagRepository extends R2dbcRepository<BbsTag, UUID> {
}
