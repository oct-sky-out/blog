package com.octskyout.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bbs_post")
@Table(name = "bbs_post", schema = "blog_schema")
public class BbsPost {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "reg_date", nullable = false)
    private OffsetDateTime regDate;

    @Column(name = "update_date")
    private OffsetDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bbs_user_id")
    private BbsUser user;

    @OneToMany(mappedBy = "bbsPost")
    private Set<BbsComment> bbsComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bbsTag")
    private Set<BbsPostTag> tags = new LinkedHashSet<>();
}
