create table comment
(
  id           int8    not null,
  created      timestamp,
   updated      timestamp,
  username  varchar(255),
  comment      varchar(255),
  is_deleted   boolean not null,
  book_id int8    not null,
  primary key (id)
);

alter table if exists comment
  add constraint comment_book foreign key (book_id) references book;