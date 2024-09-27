package com.gmail.alexflanker89.lesson.services;

import com.gmail.alexflanker89.lesson.domain.Author;
import com.gmail.alexflanker89.lesson.domain.Book;
import com.gmail.alexflanker89.lesson.domain.Genre;
import com.gmail.alexflanker89.lesson.dto.AuthorDTO;
import com.gmail.alexflanker89.lesson.dto.BookDTO;
import com.gmail.alexflanker89.lesson.dto.criteria.RequestParams;
import com.gmail.alexflanker89.lesson.exceptions.book.BookNotFoundExceptions;
import com.gmail.alexflanker89.lesson.repo.AuthorRepo;
import com.gmail.alexflanker89.lesson.repo.BookRepo;
import com.gmail.alexflanker89.lesson.repo.GenreRepo;
import com.gmail.alexflanker89.lesson.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceIImpl implements BookService {
    private final BookRepo bookRepo;
    private final GenreRepo genreRepo;
    private final AuthorRepo authorRepo;
    private final MongoOperations mongoOperations;

    @Override
    public List<Book> getAllByGenres(String line) {
        Set<String> genres;
        if (line.contains(",")) {
            genres = new HashSet<String>(Arrays.asList(line.split(",")));
            genres = genres.stream().map(String::trim).collect(Collectors.toSet());
        } else {
            genres = Collections.singleton(line);
        }
        Query byGenre = new Query();
        byGenre.addCriteria(Criteria.where("genres").elemMatch(Criteria.where("genreName").in(genres)));
        return mongoOperations.find(byGenre, Book.class);
    }

    @Override
    public List<Book> getAllByGenres(String[] genres) {
        Query byGenre = new Query();
        byGenre.addCriteria(Criteria.where("genres").elemMatch(Criteria.where("_id").in(genres)));
        return mongoOperations.find(byGenre, Book.class);
    }

    @Override
    public List<Book> getAllByAuthors(Set<Author> authors) {

        return bookRepo.findByAuthorsIn(authors);
    }

    @Override
    public List<Book> getAllByReleaseDateGreaterThan(LocalDate date) {
        return bookRepo.findByReleaseDateGreaterThan(date);
    }

    @Override
    public List<Book> getAllByReleaseDateLessThan(LocalDate date) {
        return bookRepo.findByReleaseDateBefore(date);
    }

    @Override
    public List<Book> getAllByReleaseDate(LocalDate date) {
        return bookRepo.findByReleaseDate(date);
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    @Override
    public void delete(Book entry) {
        bookRepo.delete(entry);
    }

    //BookRepo отдавал сущность без id!! fix
    @Override
    public Book save(Book entry) {
        entry.setId(null);
        return  bookRepo.save(entry);

    }

    @Override
    public Book update(Book book) {
        Book bookFromDb = mongoOperations.findOne(Query.query(Criteria.where("id").is(book.getId())), Book.class);
        if (Objects.nonNull(bookFromDb)) return bookRepo.save(book);
        return null;
    }

    @Override
    public List<Author> getByNameAndLastname(String name, String lastname) {
        return authorRepo.findByNameAndLastname(name, lastname);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepo.findAll();
    }


    @Override
    public List<Book> getAllByAuthorsNameAndLastname(String name, String lastname) {
        Query byAuthosNameAndLastname = new Query();
        byAuthosNameAndLastname.addCriteria(Criteria.where("authors").elemMatch(Criteria.where("name").is(name).and("lastname").is(lastname)));
        return mongoOperations.find(byAuthosNameAndLastname, Book.class);

    }

    @Override
    public List<Author> getByBookId(String id) {
        return mongoOperations.find(Query.query(Criteria.where("id").is(id)), Book.class).stream().flatMap(book -> book.getAuthors().stream()).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return bookRepo.findById(id);
    }

    @Override
    public Optional<Book> getByTitle(String title) {
        return bookRepo.findByTitle(title);
    }

    private List<Genre> getGenre(List<String> genres) {
        return genreRepo.findByGenreNameIn(new HashSet<>(genres));
    }

    private List<Author> getAuthor(List<AuthorDTO> authors) {
        return authorRepo.findByIdIn(authors.stream().map(AuthorDTO::getId).collect(Collectors.toSet()));
    }

    private Book convertFromDTO(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setEdition(dto.getEdition());
        book.setReleaseDate(dto.getReleaseDate());
        book.setDescription(dto.getDescription());
        book.setAuthors(getAuthor(dto.getAuthors()));
        book.setGenres(getGenre(dto.getGenres()));
        return book;
    }


    @Override
    public Book updateBook(Book book, BookDTO bookDTO) throws BookNotFoundExceptions {
        Book fromDTO = this.convertFromDTO(bookDTO);
        book.setAuthors(fromDTO.getAuthors());
        book.setGenres(fromDTO.getGenres());
        book.setTitle(fromDTO.getTitle());
        book.setDescription(fromDTO.getDescription());
        book.setEdition(fromDTO.getEdition());
        book.setReleaseDate(fromDTO.getReleaseDate());
        book.setUpdated(LocalDate.now());
        return update(book);
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Book fromDTO = this.convertFromDTO(bookDTO);
        fromDTO.setCreated(LocalDate.now());
        return save(fromDTO);
    }

    private Criteria getCriteria(RequestParams params) {

        Criteria criteria = null;
        if (Objects.nonNull(params.getGenres())) {
            if (!params.getGenres().isEmpty()) {
                criteria = Criteria.where("genres").in(params.getGenres());
            }
        }
        if (Objects.nonNull(params.getAuthors())) {
            if (!params.getAuthors().isEmpty()) {
                if (Objects.isNull(criteria)) {
                    criteria = Criteria.where("authors").in(params.getAuthors());
                } else {
                    criteria.and("authors").in(params.getAuthors());
                }
            }
        }

        if (Objects.nonNull(params.getReleaseDate_begin())
                && Objects.nonNull(params.getReleaseDate_end())) {
            Instant begin = LocalDateTime.of(params.getReleaseDate_begin(), LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC);
            Instant end = LocalDateTime.of(params.getReleaseDate_end(), LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC);

            if (Objects.isNull(criteria)) {
                criteria = Criteria.where("releaseDate").gte(begin).lt(end);
            } else {
                criteria.and("releaseDate").gte(begin).lt(end);
            }
        } else if (Objects.nonNull(params.getReleaseDate_begin())) {
            Instant begin = LocalDateTime.of(params.getReleaseDate_begin(), LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC);

            if (Objects.isNull(criteria)) {
                criteria = Criteria.where("releaseDate").gte(begin);
            } else {
                criteria.and("releaseDate").gte(begin);
            }

        } else if (Objects.nonNull(params.getReleaseDate_end())) {
            Instant end = LocalDateTime.of(params.getReleaseDate_end(), LocalTime.MIDNIGHT).toInstant(ZoneOffset.UTC);

            if (Objects.isNull(criteria)) {
                criteria = Criteria.where("releaseDate").lt(end);
            } else {
                criteria.and("releaseDate").lt(end);
            }
        }

        return criteria;
    }

    @Override
    public List<Book> getByParams(RequestParams params) {
        Query query = new Query();
        Criteria criteria = getCriteria(params);
        if (Objects.isNull(criteria)) {
            return mongoOperations.findAll(Book.class);
        }
        query.addCriteria(criteria);
        return mongoOperations.find(query, Book.class);
    }

    @Override
    public long count() {
        return bookRepo.count();
    }
}
