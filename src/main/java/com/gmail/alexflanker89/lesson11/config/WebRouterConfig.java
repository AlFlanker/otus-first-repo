package com.gmail.alexflanker89.lesson11.config;

import com.gmail.alexflanker89.lesson11.handler.AuthorsHandler;
import com.gmail.alexflanker89.lesson11.handler.BookHandler;
import com.gmail.alexflanker89.lesson11.handler.CommentHandler;
import com.gmail.alexflanker89.lesson11.handler.GenreHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(BookHandler bookHandler, AuthorsHandler authorsHandler,
                                                         GenreHandler genreHandler, CommentHandler commentHandler) {
        return   route()
                .GET("/book", accept(MediaType.APPLICATION_JSON_UTF8), bookHandler::getAllBook)
                .POST("/book", accept(MediaType.APPLICATION_JSON_UTF8), bookHandler::addBook)
                .POST("/book/params",accept(MediaType.APPLICATION_JSON_UTF8),bookHandler::getByParams)
                .PUT("/book/{id}",accept(MediaType.APPLICATION_JSON_UTF8),bookHandler::update)

                .GET("/authors",accept(MediaType.APPLICATION_JSON_UTF8),authorsHandler::getAuthors)
                .POST("/authors",accept(MediaType.APPLICATION_JSON_UTF8),authorsHandler::addAuthor)

                .GET("/genres",accept(MediaType.APPLICATION_JSON_UTF8),genreHandler::getAllGenres)

                .POST("/comment/{id}",accept(MediaType.APPLICATION_JSON_UTF8),commentHandler::addComment)
                .DELETE("/comment/{id}",accept(MediaType.APPLICATION_JSON_UTF8),commentHandler::removeComment)
                .build();
    }
}

