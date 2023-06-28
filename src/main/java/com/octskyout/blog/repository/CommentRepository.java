package com.octskyout.blog.repository;

import com.octskyout.blog.entity.BbsComment;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CommentRepository extends R2dbcRepository<BbsComment, UUID> {
}
