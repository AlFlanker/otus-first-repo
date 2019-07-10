package com.gmail.alexflanker89.Lesson_10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.alexflanker89.Lesson_10.domain.Book;
import com.gmail.alexflanker89.Lesson_10.domain.Comment;
import com.gmail.alexflanker89.Lesson_10.dto.CommentDTO;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Проверка Comment контроллера")
@ExtendWith(SpringExtension.class)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверка добавления нового комментария к книги")
    public void testAddComment() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(String.join("", Collections.nCopies(101, "a")));
        commentDTO.setUsername("Аноним");
        mockMvc.perform(post("/comment/5d1baf80d0989b565652f1c7").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(commentDTO)))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Добавления комментария к несуществующей книге")
    public void testAddCommentToNoExistBook() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(String.join("", Collections.nCopies(101, "a")));
        commentDTO.setUsername("Аноним");
        mockMvc.perform(post("/comment/5d1baf80d0989b565652fc7").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(commentDTO)))
                .andDo(print()).andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("Удаление комментария")
    public void testRemoveComment() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(String.join("", Collections.nCopies(101, "a")));
        commentDTO.setUsername("test_1");
        MvcResult mvcResult = mockMvc.perform(post("/comment/5d1baf80d0989b565652f1c7").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(commentDTO)))
                .andDo(print()).andExpect(status().isCreated()).andReturn();
        Book book = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);
        Comment comment = book.getComments().stream().filter(c -> c.getUsername().equals("test_1")).findFirst().get();

        mockMvc.perform(delete("/comment/5d1baf80d0989b565652f1c7").content(comment.getId())).andDo(print()).andExpect(status().isOk());
    }
}
