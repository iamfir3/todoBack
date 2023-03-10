package com.example.back.controller;

import com.example.back.entity.TodoEntity;
import com.example.back.model.TodoRequestModel;
import com.example.back.response.*;
import com.example.back.service.TodoService;
import com.example.back.shared.dto.TodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @CrossOrigin
    @GetMapping()
    public TodoGetAllResponse<List<TodoResponse>> GetListById(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit,@RequestParam(value = "listId", defaultValue = "0") Integer listId) throws Exception {
        TodoGetAllResponse returnValue=new TodoGetAllResponse();

        if(listId==null) throw new Exception(ErrorMessage.MISSING_LIST_ID.getErrorMessage());
        List<TodoDto> todos= todoService.findAllById(listId,page,limit);
        if(todos.size()>1){
            returnValue.setTodoCount(todos.get(0).getTodocount());
        }
        else returnValue.setTodoCount(0);
        returnValue.setTodos(todos);
        return returnValue;
    }

    @CrossOrigin
    @PostMapping
    public TodoResponse createTodo(@RequestBody TodoRequestModel todoDetail) {

        TodoResponse returnValue = new TodoResponse();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(todoDetail, todoDto);
        if(todoDetail.getContent().isEmpty()){
           returnValue.setMessage(ErrorMessage.INVALID_INPUT.getErrorMessage());
            return returnValue;
        }
        try {
            TodoDto createTodo = todoService.createTodo(todoDto);
            BeanUtils.copyProperties(createTodo, returnValue);
            returnValue.setMessage("Add to-do successfull");
            returnValue.setStatusCode("200");
        } catch (Exception err) {
            System.out.println(err);
        }

        return returnValue;
    }

    @CrossOrigin
    @PutMapping
    public TodoResponse updateTodo(@RequestBody TodoRequestModel todoDetails) {
        TodoResponse returnValue = new TodoResponse();
        TodoDto todoDto = new TodoDto();
        BeanUtils.copyProperties(todoDetails, todoDto);
        TodoDto updatedTodo = todoService.updateTodo(todoDto);
        BeanUtils.copyProperties(updatedTodo, returnValue);
        returnValue.setStatusCode("200");
        returnValue.setMessage("Updated");
        return returnValue;
    }

    @CrossOrigin
    @DeleteMapping(path="/{todoId}")
    public OperationStatusModel deleteTodo(@PathVariable Integer todoId) throws Exception {
        OperationStatusModel returnValue = new OperationStatusModel();
        if(todoId==null){
            returnValue.setOperationResult("TodoId is null");
            returnValue.setStatusCode("400");
            return returnValue;
        }
        try{
            returnValue.setOperationName(RequestOperationName.DELETE.name());
            todoService.deleteTodo(todoId);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            return returnValue;
        }
        catch(Exception err){
            returnValue.setOperationResult("Not valid TodoId");
            returnValue.setStatusCode("400");
            return returnValue;
        }

    }
}
