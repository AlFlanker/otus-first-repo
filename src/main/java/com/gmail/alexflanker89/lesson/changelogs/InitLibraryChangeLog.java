package com.gmail.alexflanker89.lesson.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.gmail.alexflanker89.lesson.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@ChangeLog(order = "001")
public class InitLibraryChangeLog {

    private Map<String, Author> authorHashMap = new HashMap<>(5);
    private Map<String, Genre> genreMap = new HashMap<>(5);
    private List<Comment> comments = new ArrayList<>();

    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private void initGenre(MongoTemplate mongoTemplate) {
        Genre genre = new Genre();
        Map<String, Genre> genreMap_tmp = new HashMap<>(5);
        genre.setGenreName("Роман");
        genreMap.put("Роман", genre);
        genre = new Genre();
        genre.setGenreName("Наука и Техника");
        genreMap.put("Наука и Техника", genre);
        genre = new Genre();
        genre.setGenreName("Детектив");
        genreMap.put("Детектив", genre);
        genre = new Genre();
        genre.setGenreName("Приключения");
        genreMap.put("Приключения", genre);
        genre = new Genre();
        genre.setGenreName("Фантастика");
        genreMap.put("Фантастика", genre);
        genreMap.forEach((s, genre_) -> {
            Genre save = mongoTemplate.save(genre_);
            genreMap_tmp.put(s, save);
        });
        genreMap = genreMap_tmp;
    }

    private void initAuthor(MongoTemplate mongoTemplate) {
        Author author = new Author("Артур", "Хейли", LocalDate.of(1920, 4, 5));
        authorHashMap.put("Артур", author);

        author = new Author("Олег", "Дивов", LocalDate.of(1968, 10, 3));
        authorHashMap.put("Олег", author);

        author = new Author("Эмиль", "Золя", LocalDate.of(1840, 4, 2));
        authorHashMap.put("Эмиль", author);

        author = new Author("Макс", "Рублев", LocalDate.of(1975, 1, 1));
        authorHashMap.put("Макс", author);

        author = new Author("Владимир", "Решетников", LocalDate.of(1959, 12, 14));
        authorHashMap.put("Владимир", author);

        authorHashMap.forEach((s, a) -> {
            Author save = mongoTemplate.save(a);
            authorHashMap.put(s, save);
        });

    }

    private void createComment() {
        Comment comment = new Comment();
        comment.setUsername("Вася");
        comment.setComment("Бла бла бла хорошая книга");
        comment.setCreated(LocalDateTime.now());
        comment.setUpdated(LocalDateTime.now());
        comments.add(comment);

        comment = new Comment();
        comment.setUsername("Петя");
        comment.setComment("Бла бла бла ye ну очень хорошая книга");
        comment.setCreated(LocalDateTime.now());
        comment.setUpdated(LocalDateTime.now());
        comments.add(comment);

        comment = new Comment();
        comment.setUsername("Аноним");
        comment.setComment("Не читал, но осуждаю");
        comment.setCreated(LocalDateTime.now());
        comment.setUpdated(LocalDateTime.now());
        comments.add(comment);

    }

    @ChangeSet(order = "001", id = "addSomeBooks", author = "AlexFlanker")
    public void initBook(MongoTemplate mongoTemplate) {
        createComment();
        initAuthor(mongoTemplate);
        initGenre(mongoTemplate);
        Book book = new Book();
        book.setTitle("Отель");
        book.setEdition("Книга на все времена");
        book.setDescription("Почему романы Артура Хейли покорили весь мир? Что сделало их классикой мировой беллетристики?" +
                "Почему, стоило выйти в нашей стране `Отелю` и `Аэропорту`, их буквально сметали с прилавков, выкрадывали из библиотек, давали почитать друзьям `в очередь`?");
        book.setLanguages(Collections.singleton(Languages.RUS));
        book.setReleaseDate(LocalDate.of(2009, 1, 1));
        book.setCreated(LocalDate.now());
        book.setUpdated(LocalDate.now());
        book.setAuthors(Arrays.asList(authorHashMap.get("Артур"), authorHashMap.get("Эмиль")));
        book.setGenres(Collections.singletonList(genreMap.get("Роман")));
        book.setComments(comments);
        mongoTemplate.save(book);
        book = new Book();
        book.setTitle("Окончательный диагноз");
        book.setEdition("АСТ-Классика");
        book.setDescription("Это - больница. Больница, в которой кипит жизнь. Здесь лечат людей - и не просто лечат, но спасают. Однако - это ли главное в мире больницы? В мире, где интригуют и дружат, рискуют и предают, влюбляются - и теряют любовь. Изменяют. Сражаются. Попросту - живут. Потому что жизнь - это, как говорится, окончательный диагноз");
        book.setLanguages(Collections.singleton(Languages.RUS));
        book.setReleaseDate(LocalDate.of(2010, 1, 1));
        book.setCreated(LocalDate.now());
        book.setUpdated(LocalDate.now());
        book.setAuthors(Arrays.asList(authorHashMap.get("Владимир"), authorHashMap.get("Макс")));
        book.setGenres(Collections.singletonList(genreMap.get("Фантастика")));
        book.setComments(comments);
        mongoTemplate.save(book);
        book = new Book();
        book.setTitle("Чушь собачья");
        book.setEdition("Суровая правда жизни");
        book.setDescription("Быть или не пить? И бла-бла");
        book.setLanguages(Collections.singleton(Languages.RUS));
        book.setReleaseDate(LocalDate.of(2100, 1, 1));
        book.setCreated(LocalDate.now());
        book.setUpdated(LocalDate.now());
        book.setAuthors(Arrays.asList(authorHashMap.get("Артур")));
        book.setGenres(Collections.singletonList(genreMap.get("Фантастика")));
        book.setComments(comments);
        mongoTemplate.save(book);
    }





}
