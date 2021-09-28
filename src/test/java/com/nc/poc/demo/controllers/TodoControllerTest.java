package com.nc.poc.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.poc.demo.model.Todo;
import com.nc.poc.demo.services.TodoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TodoService todoService;

    static Todo todo;

    @BeforeEach
    void instantiateAll(){
        todo = new Todo(1, "Java 8", new Date(), true);
    }

    @Test
    void test_createTodo_expect_201() throws Exception {
        //Arrange
        String jsonRequest = objectMapper.writeValueAsString(new Todo());

        //Act
        Mockito.when(todoService.createTodo(Mockito.any()))
                .thenReturn(todo);
        MvcResult result = mockMvc.perform(post("/api/todo")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();

        //Assert
        Assertions.assertTrue(resultContent.contains("Java 8"));
    }

    @Test
    void test_getAllTodos_expect_200() throws Exception {
        //Arrange
        List<Todo> todoList = new ArrayList<>(Arrays.asList(
                new Todo(1, "Java 8", new Date(), true),
                new Todo(2, "Java 11", new Date(), false),
                new Todo(3, "DS Algo", new Date(), true)
        ));
        // Act
        Mockito.when(todoService.getAllTodos()).thenReturn(todoList);
        MvcResult result = mockMvc
                .perform(get("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<Todo> todos = Arrays.asList(objectMapper.readValue(resultContent, Todo[].class));
        //Assert
        Assertions.assertNotNull(resultContent);
        Assertions.assertEquals(3, todos.size());
    }

    @Test
    void test_getAllTodos_expect_403() throws Exception {
        //Arrange & Act & Assert
        MvcResult result = mockMvc
                .perform(patch("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
