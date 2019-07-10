package com.gmail.alexflanker89.Lesson_10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.domain.Genre;
import com.gmail.alexflanker89.Lesson_10.dto.AuthorDTO;
import com.gmail.alexflanker89.Lesson_10.dto.BookDTO;
import com.gmail.alexflanker89.Lesson_10.dto.criteria.RequestParams;
import com.gmail.alexflanker89.Lesson_10.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_10.repo.BookRepo;
import com.gmail.alexflanker89.Lesson_10.repo.GenreRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Проверка Book контроллера")
@ExtendWith(SpringExtension.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
    private AuthorRepo authorRepo;

    private AuthorDTO getAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Authors name");
        authorDTO.setLastname("Authors lastname");
        return authorDTO;
    }

    private BookDTO getBook(List<AuthorDTO> authorDTO) {
        BookDTO dto = new BookDTO();
        dto.setId("");
        dto.setTitle("Book");
        dto.setEdition("some edition");
        dto.setDescription("very long text");
        dto.setReleaseDate(LocalDate.now());
        dto.setAuthors(new ArrayList<AuthorDTO>(authorDTO));
        dto.setGenres(Collections.singletonList("Приключения"));
        return dto;
    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: заголовк")
    public void addBook1() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        bookDTO.setTitle("");
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"title_error\":\"название книги должно содержать от 2 до 100 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: слишком длинного заголовка")
    public void addBook2() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        String title = String.join("", Collections.nCopies(101, "a"));
        bookDTO.setTitle(title);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"title_error\":\"название книги должно содержать от 2 до 100 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: слишком длинного Издательства")
    public void addBook3() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        String edition = String.join("", Collections.nCopies(101, "a"));
        bookDTO.setEdition(edition);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"edition_error\":\"издательство должно содержать не более 100 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: слишком длинного Описания")
    public void addBook4() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        String description = String.join("", Collections.nCopies(5000, "a"));
        bookDTO.setDescription(description);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"description_error\":\"описание книги не должно привышать 4096 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: нет даты релиза")
    public void addBook5() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        String description = String.join("", Collections.nCopies(5000, "a"));
        bookDTO.setReleaseDate(null);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"releaseDate_error\":\"не указана дата релиза\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: нет жанров")
    public void addBook6() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        String description = String.join("", Collections.nCopies(5000, "a"));
        bookDTO.setGenres(null);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"genres_error\":\"не указан жанр\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Ошибка ввода: нет жанров")
    public void addBook7() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();
        String description = String.join("", Collections.nCopies(5000, "a"));
        bookDTO.setAuthors(null);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(content().string("{\"authors_error\":\"не указан автор\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления новой книги. Позитивный сценарий")
    public void addBoo8() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Book book = mapper.readValue(content, Book.class);
        bookRepo.delete(book);
    }
    @Disabled
    @Test
    @DisplayName("Тест обновления книги")
    public void addBook9() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        BookDTO bookDTO = getBook(Collections.singletonList(authorDTO));
        ObjectMapper mapper = new ObjectMapper();

        MvcResult mvcResult = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Book book = mapper.readValue(content, Book.class);
        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(bookDTO))
        ).andDo(print())
                .andExpect(status().isConflict())
                .andReturn();
        bookRepo.delete(book);
    }


    @Test
    @DisplayName("Тест загрузки по всем параметрам")
    public void loadByParamsTest() throws Exception{
        List<Genre> all = genreRepo.findAll();
        List<String> genres = all.stream().map(Genre::getId).collect(Collectors.toList());
        Genre фантастика = all.stream().filter(genre -> genre.getGenreName().equals("Фантастика")).findFirst().get();
        RequestParams params = new RequestParams();
        params.setGenres(Collections.singleton(фантастика.getId()));
        params.setAuthors(Collections.singleton("5d1baf7cd0989b565652f1b9"));
        params.setReleaseDate_begin(LocalDate.of(2001,12,1));
        params.setReleaseDate_end(LocalDate.of(2101,12,1));
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/book/params").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(params))).andExpect(status().isOk()).andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Book> books = mapper.readValue(content,mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        Assertions.assertTrue(books.size()>0);
    }

    @Test
    @DisplayName("Тест загрузки по датам")
    public void loadByDatesTest() throws Exception{
        List<Genre> all = genreRepo.findAll();
        List<String> genres = all.stream().map(Genre::getId).collect(Collectors.toList());
        Genre фантастика = all.stream().filter(genre -> genre.getGenreName().equals("Фантастика")).findFirst().get();
        RequestParams params = new RequestParams();
        params.setReleaseDate_begin(LocalDate.of(2001,12,1));
        params.setReleaseDate_end(LocalDate.of(2101,12,1));
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/book/params").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(params))).andExpect(status().isOk()).andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Book> books = mapper.readValue(content,mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        Assertions.assertTrue(books.size()>0);
    }

    @Test
    @DisplayName("Тест загрузки по жанрам")
    public void loadByAllGenresTest() throws Exception{
        List<Genre> all = genreRepo.findAll();
        List<String> genres = all.stream().map(Genre::getId).collect(Collectors.toList());
        Genre фантастика = all.stream().filter(genre -> genre.getGenreName().equals("Фантастика")).findFirst().get();
        RequestParams params = new RequestParams();
        params.setGenres(new HashSet<String>(genres));
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/book/params").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(params))).andExpect(status().isOk()).andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Book> books = mapper.readValue(content,mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        Assertions.assertTrue(books.size()>0);
    }

    @Test
    @DisplayName("Тест загрузки по авторам")
    public void loadByAllAuthorsTest() throws Exception{
        List<Author> authors = authorRepo.findAll();
        Set<String> authorsName = authors.stream().map(Author::getId).collect(Collectors.toSet());
        RequestParams params = new RequestParams();
        params.setAuthors(authorsName);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/book/params").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(params))).andExpect(status().isOk()).andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Book> books = mapper.readValue(content,mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        Assertions.assertTrue(books.size()>0);
    }
    @Test
    @DisplayName("Попытка прокинуть null")
    public void loadByNullTest() throws Exception{

        RequestParams params = new RequestParams();
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/book/params").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(params)))
                .andExpect(status().isOk())
                .andDo(print()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        List<Book> books = mapper.readValue(content,mapper.getTypeFactory().constructCollectionType(List.class,Book.class));
        Assertions.assertTrue(books.size()>0);
    }


}
