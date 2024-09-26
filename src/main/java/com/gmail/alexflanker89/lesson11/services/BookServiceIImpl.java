package com.gmail.alexflanker89.lesson11.services;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.dto.BookDTO;
import com.gmail.alexflanker89.lesson11.dto.criteria.RequestParams;
import com.gmail.alexflanker89.lesson11.repo.BookRepo;
import com.gmail.alexflanker89.lesson11.services.interfaces.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.*;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceIImpl implements BookService {
    private final BookRepo bookRepo;
    private final ReactiveMongoOperations mongoOperations;



    @Override
    public Flux<Book> getAll() {
        return bookRepo.findAll();
    }


    @Override
    public Mono<Book> save(Book entry) {
        entry.setId(null);
        return  bookRepo.save(entry);

    }

    @Override
    public Mono<Book> update(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Mono<Book> getBookById(String id) {
        return bookRepo.findById(id);
    }


    private Book convertFromDTO(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setEdition(dto.getEdition());
        book.setReleaseDate(dto.getReleaseDate());
        book.setDescription(dto.getDescription());
        book.setAuthors(dto.getAuthors());
        book.setGenres(dto.getGenres());
        return book;
    }


    @Override
    public Mono<Book> updateBook(Mono<Book> book, Mono<BookDTO> bookDTO) {
       return bookDTO.map(this::convertFromDTO).map(dto->
           book.map(b->{
               b.setAuthors(dto.getAuthors());
               b.setGenres(dto.getGenres());
               b.setTitle(dto.getTitle());
               b.setDescription(dto.getDescription());
               b.setEdition(dto.getEdition());
               b.setReleaseDate(dto.getReleaseDate());
               b.setUpdated(LocalDate.now());
               return update(b);
           })).flatMap(t->t.flatMap(y->y));

    }

    @Override
    public Mono<Book> addBook(Mono<BookDTO> bookDTO) {
        return bookDTO.map(dto-> {
            Book book = this.convertFromDTO(dto);
            book.setCreated(LocalDate.now());
            return this.save(book);
        }).flatMap(b->b);
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
    public Flux<Book> getByParams(Mono<RequestParams> params) {
        return params.map(p->{
            Query query = new Query();
            Criteria criteria = getCriteria(p);
            if (Objects.isNull(criteria)) {
                return mongoOperations.findAll(Book.class);
            }
            query.addCriteria(criteria);
            return mongoOperations.find(query, Book.class);
        }).flatMapMany(Flux::from);


    }

}
