package com.gmail.alexflanker89.lesson11.handler;

import com.gmail.alexflanker89.lesson11.domain.Book;
import com.gmail.alexflanker89.lesson11.dto.BookDTO;
import com.gmail.alexflanker89.lesson11.dto.criteria.RequestParams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookHandlerTest {

    @Autowired
    private RouterFunction route;


    @Test
    @DisplayName("загрузка всех книг")
    public void getAllBook() {

        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        EntityExchangeResult<List<Book>> result = client.get()
                .uri("/book")
                .exchange()
                .expectStatus()
                .isOk().expectBodyList(Book.class).returnResult();
       Assertions.assertTrue(result.getResponseBody().size() > 0);
    }

    @Test
    @DisplayName("добавить новую книгу")
    public void addBook(){
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        BookDTO book = new BookDTO();
        book.setTitle("Test");
        book.setEdition("Test edition");
        book.setReleaseDate(LocalDate.now());
        Mono<BookDTO> mono = Mono.just(book);

        EntityExchangeResult<Book> bookEntityExchangeResult = client.post().uri("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, BookDTO.class).exchange().expectBody(Book.class).returnResult();
        Book responseBody = bookEntityExchangeResult.getResponseBody();
        Assertions.assertNotNull(responseBody);
    }

    @Test
    @DisplayName("загрузка по датам")
    public void getByParam(){
        RequestParams params = new RequestParams();
        params.setReleaseDate_begin(LocalDate.of(2001,12,1));
        params.setReleaseDate_end(LocalDate.of(2101,12,1));

        Mono<RequestParams> mono = Mono.just(params);


        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        EntityExchangeResult<List<Book>> result = client.post().uri("/book/params")
                .contentType(MediaType.APPLICATION_JSON)
                .body(mono, RequestParams.class).exchange().expectBodyList(Book.class).returnResult();
        List<Book> responseBody = result.getResponseBody();

        Assertions.assertTrue(responseBody.size() > 0);
    }


}
