package com.nc.poc.demo.services;

import com.nc.poc.demo.model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoService {
    static List<Todo> todoList = new ArrayList<>(Arrays.asList(
            new Todo(1, "Java 8", new Date(), true),
            new Todo(2, "Java 11", new Date(), false),
            new Todo(3, "DS Algo", new Date(), true),
            new Todo(4, "System design", new Date(), true),
            new Todo(5, "Junit", new Date(), false)
    ));
    public List<Todo> getAllTodos(){
        return todoList;
    }

    public List<Todo> getCompletedList(){
        if (!CollectionUtils.isEmpty(todoList))
            todoList
                .removeIf(todo -> !todo.getIsDone());
        return todoList;
    }

    public List<Todo> getTodos(){
        if (!CollectionUtils.isEmpty(todoList))
            todoList
                    .removeIf(todo -> todo.getIsDone());
        return todoList;
    }

    public Todo createTodo(Todo todo){
        if (!ObjectUtils.isEmpty(todo)){
            System.out.println(todo.toString());
            todoList.add(todo);
            int x= 5;
        } else {
            System.out.println("Object is null");
        }
        return todo;
    }
}
