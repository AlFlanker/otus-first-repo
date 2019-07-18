package com.gmail.alexflanker89.lesson11.handler;

import com.gmail.alexflanker89.lesson11.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorHandlerTest {

    @Autowired
    private RouterFunction route;


    @Test
    @DisplayName("загрузка всех авторов")
    public void getAllAuthors() {
        Assertions.assertTrue(true);
        WebTestClient client = WebTestClient
                .bindToRouterFunction(route)
                .build();

        EntityExchangeResult<List<Author>> result = client.get()
                .uri("/authors")
                .exchange()
                .expectStatus()
                .isOk().expectBodyList(Author.class).returnResult();
       Assertions.assertTrue(result.getResponseBody().size() > 0);
    }


}
