package com.octskyout.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bbs_comment", schema = "blog_schema")
public class BbsComment {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_post_id")
    private BbsPost bbsPost;

    @Column(name = "comment_text", nullable = false, length = Integer.MAX_VALUE)
    private String commentText;

    @Column(name = "reply_comment_id")
    private UUID replyCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_user_id")
    private BbsUser user;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
