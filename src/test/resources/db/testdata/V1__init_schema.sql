alter table if exists book_author
  drop constraint if exists FKbjqhp85wjv8vpr0beygh6jsgo;
alter table if exists book_author
  drop constraint if exists FKhwgu59n9o80xv75plf9ggj7xn;
alter table if exists book_genre
  drop constraint if exists FK8l6ops8exmjrlr89hmfow4mmo;
alter table if exists book_genre
  drop constraint if exists FK52evq6pdc5ypanf41bij5u218;
alter table if exists book_language
  drop constraint if exists FK4ygo019430b6wntdh7ulwmu1o;
drop table if exists author cascade;
drop table if exists book cascade;
drop table if exists book_author cascade;
drop table if exists book_genre cascade;
drop table if exists genre cascade;
drop table if exists book_language cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 30 increment 1;
create table author
(
  id            int8    not null,
  date_of_birth date,
  is_deleted    boolean not null,
  lastname      varchar(255),
  name          varchar(255),
  primary key (id)
);
create table book
(
  id           int8    not null,
  created      timestamp,
  description  varchar,
  edition      varchar(255),
  is_deleted   boolean not null,
  release_date date,
  title        varchar(255),
  updated      timestamp,
  primary key (id)
);
create table book_author
(
  book_id   int8 not null,
  author_id int8 not null,
  primary key (book_id, author_id)
);
create table book_genre
(
  book_id  int8 not null,
  genre_id int8 not null,
  primary key (book_id, genre_id)
);
create table genre
(
  id         int8    not null,
  genre_name varchar(255),
  is_deleted boolean not null,
  primary key (id)
);
create table book_language
(
  book_id   int8 not null,
  languages varchar(255)
);
alter table if exists book_author
  add constraint FKbjqhp85wjv8vpr0beygh6jsgo foreign key (author_id) references author;
alter table if exists book_author
  add constraint FKhwgu59n9o80xv75plf9ggj7xn foreign key (book_id) references book;
alter table if exists book_genre
  add constraint FK8l6ops8exmjrlr89hmfow4mmo foreign key (genre_id) references genre;
alter table if exists book_genre
  add constraint FK52evq6pdc5ypanf41bij5u218 foreign key (book_id) references book;
alter table if exists book_language
  add constraint FK4ygo019430b6wntdh7ulwmu1o foreign key (book_id) references book;