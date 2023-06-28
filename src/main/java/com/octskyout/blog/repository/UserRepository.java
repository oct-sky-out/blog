package com.octskyout.blog.repository;

import com.octskyout.blog.entity.BbsUser;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<BbsUser, UUID> {
}
