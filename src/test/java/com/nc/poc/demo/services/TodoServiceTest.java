package com.nc.poc.demo.services;


import com.nc.poc.demo.model.Todo;
import org.junit.jupiter.api.*;

public class TodoServiceTest {
    final TodoService todoService = new TodoService();

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }

    @Test
    void test_getAllTodos_Dev()
    {
        System.out.println("======TEST test_getAllTodos_Dev EXECUTED=======");
        Assertions.assertNotNull(todoService.getAllTodos());
        Assertions.assertNull(todoService.createTodo(null));
    }

    @Test
//    @Disabled
    void test_getTodos()
    {
        System.out.println("======TEST test_getTodos EXECUTED=======");
        Assertions.assertNotNull(todoService.getTodos());
        Assertions.assertEquals(2, todoService.getTodos().size());
    }

    @Test
    void test_getCompletedList()
    {
        System.out.println("======TEST test_getCompletedList EXECUTED=======");
        Assertions.assertNotNull(todoService.getCompletedList());
        Assertions.assertEquals(3, todoService.getCompletedList().size());

    }

    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }

}
