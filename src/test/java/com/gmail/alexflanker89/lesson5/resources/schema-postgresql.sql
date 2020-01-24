CREATE TABLE authors
(
  id            serial PRIMARY KEY,
  name          varchar(255) not null,
  lastname      varchar(255) not null,
  date_of_birth timestamp,
  is_deleted    boolean      not null
);

CREATE TABLE genres
(
  id         serial PRIMARY KEY,
  kind_of    varchar(255) not null,
  is_deleted boolean      not null
);

CREATE TABLE books
(
  id          serial PRIMARY KEY,
  title       varchar(255) not null,
  edition     varchar(255) not null,
  description varchar(2048),
  releaseDate timestamp,
  is_deleted  boolean      not null
);

CREATE TABLE language
(
  id serial PRIMARY KEY ,
  language  varchar(10) not null,
  is_deleted boolean    not null
);

CREATE TABLE language_ref_book
(
  id serial PRIMARY KEY ,
  language_id INTEGER NOT NULL,
  book_id INTEGER NOT NULL,
    CONSTRAINT "FK_language_book_id" FOREIGN KEY ("book_id")
    REFERENCES "books" ("id") ON DELETE CASCADE ,
  CONSTRAINT "FK_language_author_id" FOREIGN KEY ("language_id")
    REFERENCES "language" ("id") ON DELETE CASCADE
);

CREATE UNIQUE INDEX "UI_book_ref_language_language_ref_book"
  ON "language_ref_book"
    USING btree
    ("book_id", "language_id");


CREATE TABLE book_ref_author
(
  id        serial primary key,
  book_id   INTEGER NOT NULL,
  author_id INTEGER NOT NULL,
  CONSTRAINT "FK_book_id" FOREIGN KEY ("book_id")
    REFERENCES "books" ("id") ON DELETE CASCADE,
  CONSTRAINT "FK_author_id" FOREIGN KEY ("author_id")
    REFERENCES "authors" ("id") ON DELETE CASCADE
);

CREATE UNIQUE INDEX "UI_book_ref_author_author_ref_book"
  ON "book_ref_author"
    USING btree
    ("book_id", "author_id");

CREATE TABLE book_ref_genre
(
  id       serial primary key,
  book_id  INTEGER NOT NULL,
  genre_id INTEGER NOT NULL,
  CONSTRAINT "FK_book_id" FOREIGN KEY ("book_id")
    REFERENCES "books" ("id") ON DELETE CASCADE,
  CONSTRAINT "FK_genre_id" FOREIGN KEY ("genre_id")
    REFERENCES "genres" ("id") ON DELETE CASCADE
);

CREATE UNIQUE INDEX "UI_book_ref_genre_genre_ref_book"
  ON "book_ref_genre"
    USING btree
    ("book_id", "genre_id");