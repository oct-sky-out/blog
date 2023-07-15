/**
  DDL START
 */
create schema blog_schema;

-- blog_schema.bbs_user
create table blog_schema.bbs_user
(
    id       uuid                     default gen_random_uuid() not null,
    email    varchar(255)                                       not null,
    nickname varchar(100)                                       not null,
    reg_date timestamp with time zone default now()             not null
);

alter table blog_schema.bbs_user
    owner to root;

alter table blog_schema.bbs_user
    add constraint bbs_user_pk
        primary key (id);

-- blog_schema.bbs_post

create table blog_schema.bbs_post
(
    id          uuid                     default gen_random_uuid() not null,
    title       varchar                                            not null,
    content     text                     default ''::text          not null,
    reg_date    timestamp with time zone default now()             not null,
    update_date timestamp with time zone,
    bbs_user_id uuid                                               not null
);


alter table blog_schema.bbs_post
    owner to root;

alter table blog_schema.bbs_post
    add constraint bbs_post_pk
        primary key (id);

alter table blog_schema.bbs_post
    add constraint bbs_post_bbs_user_id_fk
        foreign key (bbs_user_id) references blog_schema.bbs_user;


-- blog_schema.bbs_comment
create table blog_schema.bbs_comment
(
    id               uuid                     default gen_random_uuid() not null,
    comment_text     text                                               not null,
    reply_comment_id uuid,
    bbs_post_id      uuid                                               not null,
    bbs_user_id      uuid,
    reg_date         timestamp with time zone default now()             not null,
    update_date      timestamp with time zone
);
comment on column blog_schema.bbs_comment.reply_comment_id is '대댓글(부모댓글) id';

alter table blog_schema.bbs_comment
    owner to root;

alter table blog_schema.bbs_comment
    add constraint bbs_comment_pk
        primary key (id);


alter table blog_schema.bbs_comment
    add constraint bbs_comment_bbs_post_id_fk
        foreign key (bbs_post_id) references blog_schema.bbs_post
            on update cascade on delete cascade;

alter table blog_schema.bbs_comment
    add constraint bbs_comment_bbs_user_id_fk
        foreign key (bbs_user_id) references blog_schema.bbs_user;

-- blog_schema.bbs_tag
create table blog_schema.bbs_tag
(
    id          uuid                     default gen_random_uuid() not null,
    tag_name    varchar(30)                                        not null,
    reg_date    timestamp with time zone default now()             not null,
    update_date timestamp with time zone
);

alter table blog_schema.bbs_tag
    owner to root;

alter table blog_schema.bbs_tag
    add constraint bbs_tag_pk
        primary key (id);



create table blog_schema.bbs_post_tag
(
    id          uuid                     default gen_random_uuid() not null,
    bbs_post_id uuid                                               not null,
    bbs_tag_id  uuid                                               not null,
    reg_date    timestamp with time zone default now()             not null
);
alter table blog_schema.bbs_post_tag
    owner to root;

alter table blog_schema.bbs_post_tag
    add constraint bbs_post_tag_pk
        primary key (id);

alter table blog_schema.bbs_post_tag
    add constraint bbs_post_tag_bbs_post_id_fk
        foreign key (bbs_post_id) references blog_schema.bbs_post;

alter table blog_schema.bbs_post_tag
    add constraint bbs_post_tag_bbs_tag_id_fk
        foreign key (bbs_tag_id) references blog_schema.bbs_tag;
/**
DDL END
*/


/**
DML START
 */
-- blog_schema.bbs_user
INSERT INTO blog_schema.bbs_user (id, email, nickname, reg_date) VALUES ('9f2c4e18-5764-4fe3-bf23-065419138c7a', 'abc@abc.com', 'abc', '2023-06-28 15:39:36.148592 +00:00');

-- blog_schema.bbs_post
INSERT INTO blog_schema.bbs_post (id, title, content, reg_date, update_date, bbs_user_id) VALUES ('41844410-6f6c-467f-afea-0ee629cae555', 'example2', 'content url', '2023-06-28 15:36:55.018825 +00:00', null, '9f2c4e18-5764-4fe3-bf23-065419138c7a');
INSERT INTO blog_schema.bbs_post (id, title, content, reg_date, update_date, bbs_user_id) VALUES ('e5601f73-b3cc-4d31-82cc-3a2c34ef0193', 'example', 'content url', '2023-06-28 15:20:06.000000 +00:00', null, '9f2c4e18-5764-4fe3-bf23-065419138c7a');

-- blog_schema.bbs_comment
INSERT INTO blog_schema.bbs_comment (id, comment_text, reply_comment_id, bbs_post_id, bbs_user_id, reg_date, update_date) VALUES ('032d4dec-ad8f-4d96-acfb-fa6b28e66777', 'goood', null, 'e5601f73-b3cc-4d31-82cc-3a2c34ef0193', '9f2c4e18-5764-4fe3-bf23-065419138c7a', '2023-06-28 15:39:44.980586 +00:00', null);

/**
DML END
 */
