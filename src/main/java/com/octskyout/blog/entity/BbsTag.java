package com.octskyout.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
@Table(name = "bbs_tag", schema = "blog_schema")
public class BbsTag {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "tag_name", nullable = false, length = 30)
    private String tagName;

    @Column(name = "reg_date", nullable = false)
    private OffsetDateTime regDate;

    @Column(name = "update_date")
    private OffsetDateTime updateDate;

    @OneToMany(mappedBy = "bbsTag")
    private Set<BbsPostTag> tags = new LinkedHashSet<>();
}
