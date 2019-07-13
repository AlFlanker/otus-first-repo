package com.gmail.alexflanker89.Lesson_10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.dto.AuthorDTO;
import com.gmail.alexflanker89.Lesson_10.repo.AuthorRepo;
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
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Проверка Book контроллера")
@ExtendWith(SpringExtension.class)
public class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthorRepo authorRepo;

    private AuthorDTO getAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Authors name");
        authorDTO.setLastname("Authors lastname");
        authorDTO.setDateOfBirth(LocalDate.of(1980,7,12));
        return authorDTO;
    }



    @Test
    @DisplayName("Тест добавления нового автора. Ошибка ввода: нет имени")
    public void addAuthor1() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        authorDTO.setName("");
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(authorDTO))
        ).andDo(print())
                .andExpect(content().string("{\"name_error\":\"имя автора должно содержать от 2 до 50 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления нового автора. Ошибка ввода: длинное именя")
    public void addAuthor2() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        authorDTO.setName(String.join("",Collections.nCopies(51,"1")));
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(authorDTO))
        ).andDo(print())
                .andExpect(content().string("{\"name_error\":\"имя автора должно содержать от 2 до 50 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления нового автора. Ошибка ввода: нет фамилии")
    public void addAuthor3() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        authorDTO.setLastname("");
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(authorDTO))
        ).andDo(print())
                .andExpect(content().string("{\"lastname_error\":\"фамилия автора должно содержать от 2 до 50 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления нового автора. Ошибка ввода: длинная фамилии")
    public void addAuthor4() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        authorDTO.setLastname(String.join("",Collections.nCopies(51,"1")));
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(authorDTO))
        ).andDo(print())
                .andExpect(content().string("{\"lastname_error\":\"фамилия автора должно содержать от 2 до 50 символов!\"}"))
                .andReturn();

    }

    @Test
    @DisplayName("Тест добавления нового автора. Позитивный сценарий")
    public void addAuthor5() throws Exception {
        AuthorDTO authorDTO = getAuthor();
        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(authorDTO))
        ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Author author = mapper.readValue(content, Author.class);
        authorRepo.delete(author);

    }

    @Test
    @DisplayName("Тест добавления нового автора.Тест на дублирование")
    public void addAuthor6() throws Exception {

        AuthorDTO authorDTO = getAuthor();
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setLastname(authorDTO.getLastname());
        author.setDateOfBirth(authorDTO.getDateOfBirth());
        Author save = authorRepo.save(author);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsString(authorDTO))
        ).andDo(print())
                .andExpect(status().isConflict())
                .andReturn();

        authorRepo.delete(save);

    }





}
