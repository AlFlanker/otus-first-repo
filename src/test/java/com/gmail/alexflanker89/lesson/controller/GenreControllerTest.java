package com.gmail.alexflanker89.lesson.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.alexflanker89.lesson.domain.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WithMockUser(username = "admin",authorities = {"ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Проверка Genre контроллера")
@ExtendWith(SpringExtension.class)
public class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("получение всех жанров")
    public void getAll() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String str = mockMvc.perform(get("/genres"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<Genre> list = objectMapper.readValue(str, objectMapper.getTypeFactory().constructCollectionType(List.class, Genre.class));
        Assertions.assertTrue(list.size()>0);
    }

}
