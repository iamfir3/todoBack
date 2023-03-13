package com.example.back.sevice.impl;

import com.example.back.entity.ListEntity;
import com.example.back.entity.TodoEntity;
import com.example.back.repository.ListRepository;
import com.example.back.repository.TodoRepository;
import com.example.back.response.ErrorMessage;
import com.example.back.service.TodoService;
import com.example.back.shared.dto.TodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;
    @Autowired
    ListRepository listRepository;

    @Override
    public TodoDto createTodo(TodoDto todo) {
        TodoEntity todoEntity = new TodoEntity();
        BeanUtils.copyProperties(todo, todoEntity);
        Optional<ListEntity> listEntity = listRepository.findById(todo.getListId());
        if (listEntity.isPresent())
            todoEntity.setListEntity(listEntity.get());
        else throw new RuntimeException("Not exist list");
        TodoEntity storedTodoDetails = todoRepository.save(todoEntity);
        TodoDto returnValue = new TodoDto();

        BeanUtils.copyProperties(storedTodoDetails, returnValue);

        return returnValue;
    }


    @Override
    public List<TodoDto> findAllById(Integer listId,Integer page, Integer limit) {
        List<TodoDto> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Optional<ListEntity> listEntity=listRepository.findById(listId);
        Page<TodoEntity> todos=todoRepository.findAllByListEntity(listEntity.get(),pageableRequest);
        for (TodoEntity todo : todos) {
            if (todo.getListEntity().getListId() - listId == 0) {
                TodoDto todoModel = new TodoDto();
                BeanUtils.copyProperties(todo, todoModel);
                todoModel.setTodocount(todoRepository.countByListEntity(listEntity.get()));
                returnValue.add(todoModel);
            }
        }
        return returnValue;
    }

    @Override
    public List<TodoDto> findAll() {
        return null;
    }

    @Override
    public TodoDto updateTodo(TodoDto todo) {
        TodoDto returnValue = new TodoDto();
        Optional<TodoEntity> todoEntity = todoRepository.findById(todo.getTodoId());
        todoEntity.get().setContent(todo.getContent());
        todoEntity.get().setDone(todo.getDone());
        TodoEntity savedEntity = todoRepository.save(todoEntity.get());

        BeanUtils.copyProperties(savedEntity, returnValue);

        return returnValue;
    }

    @Override
    public void deleteTodo(Integer todoId) throws Exception {
        Optional<TodoEntity> todoEntity = todoRepository.findById(todoId);
        System.out.println(todoEntity.get().getTodoId());
        if(todoEntity.isPresent()==false){
            throw new Exception(ErrorMessage.NOT_FOUND_TODO.getErrorMessage());
        }

        todoRepository.deleteById(todoEntity.get().getTodoId());

    }
}
