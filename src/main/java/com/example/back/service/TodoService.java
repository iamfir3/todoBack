package com.example.back.service;

import com.example.back.shared.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto createTodo(TodoDto todo);
    List<TodoDto> findAllById(Integer listId,Integer page, Integer limit);

    List<TodoDto> findAll();
    TodoDto updateTodo(TodoDto todo);
    void deleteTodo(Integer todoId) throws Exception;
}
