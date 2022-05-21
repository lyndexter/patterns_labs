-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-05-02 19:00:20.748
drop database if exists wordpress_db;
create database wordpress_db;
use wordpress_db;

-- tables
-- Table: Comment
CREATE TABLE Comment (
    uid varchar(36) NOT NULL,
    message text NOT NULL,
    creation_date date NOT NULL,
    user varchar(36) NOT NULL,
    post_uid varchar(36) NOT NULL,
    CONSTRAINT Comment_pk PRIMARY KEY (uid)
);

-- Table: Post
CREATE TABLE Post (
    uid varchar(36) NOT NULL,
    is_page bool NOT NULL,
    title varchar(100) NOT NULL,
    creation_date date NOT NULL,
    expiratio_date date NULL,
    last_modification_date date NOT NULL,
    publish_status int NOT NULL,
    comment_count bigint NOT NULL,
    body text NULL,
    author varchar(36) NOT NULL,
    CONSTRAINT Post_pk PRIMARY KEY (uid)
);

-- Table: User
CREATE TABLE User (
    uid varchar(36) NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(16) NOT NULL,
    email varchar(30) NOT NULL,
    role varchar(20) NOT NULL,
    name varchar(50) NULL,
    CONSTRAINT User_pk PRIMARY KEY (uid)
);

-- foreign keys
-- Reference: Comment_Post (table: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_Post FOREIGN KEY Comment_Post (post_uid)
    REFERENCES Post (uid);

-- Reference: Comment_User (table: Comment)
ALTER TABLE Comment ADD CONSTRAINT Comment_User FOREIGN KEY Comment_User (user)
    REFERENCES User (uid);

-- Reference: Post_User (table: Post)
ALTER TABLE Post ADD CONSTRAINT Post_User FOREIGN KEY Post_User (author)
    REFERENCES User (uid);

-- End of file.

