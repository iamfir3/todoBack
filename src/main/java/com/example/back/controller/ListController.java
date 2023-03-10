package com.example.back.controller;

import com.example.back.entity.TodoEntity;
import com.example.back.model.ListRequestModel;
import com.example.back.response.*;
import com.example.back.service.ListService;
import com.example.back.service.TodoService;
import com.example.back.shared.dto.ListDto;
import com.example.back.shared.dto.TodoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("list")
public class ListController {

    @Autowired
    ListService listService;
    @Autowired
    TodoService todoService;

    @CrossOrigin
    @GetMapping()
    public ListGetAllResponse<List<ListResponse>> GetAllList(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit) {
        ListGetAllResponse returnValue = new ListGetAllResponse();

        try {
            List<ListDto> lists = listService.getAll(page, limit);
            if (lists.size() > 0) {
                returnValue.setListCount(lists.get(0).getTotalList());
            } else {
                returnValue.setListCount(0);
            }
            returnValue.setLists(lists);
            returnValue.setMessage("Success");
            returnValue.setStatus(0);

        } catch (Exception err) {
            returnValue.setMessage(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage());
            returnValue.setStatus(1);
        }
        return returnValue;
    }

    @CrossOrigin
    @GetMapping(path = "/{listId}")
    public ListResponse GetListById(@PathVariable Integer listId) throws Exception {
        ListResponse returnValue = new ListResponse();
        if (listId == null) {
            returnValue.setMessage(ErrorMessage.MISSING_LIST_ID.getErrorMessage());
            returnValue.setStatusCode("400");
            return returnValue;
        }
        try {
            ListDto list = listService.getListById(listId);
            BeanUtils.copyProperties(list, returnValue);
            return returnValue;
        } catch (Exception err) {
            returnValue.setMessage(ErrorMessage.NOT_FOUND_LIST.getErrorMessage());
            returnValue.setStatusCode("500");
            return returnValue;
        }


    }

    @CrossOrigin
    @PostMapping
    public ListResponse PostList(@RequestBody ListRequestModel listDetails) throws Exception {
        ListResponse returnValue = new ListResponse();

        try {
            if (listDetails.getListName().isEmpty()) {
                returnValue.setMessage(ErrorMessage.INVALID_INPUT.getErrorMessage());
                returnValue.setStatusCode("400");
                return returnValue;
            }
            ListDto listDto = new ListDto();
            BeanUtils.copyProperties(listDetails, listDto);
            ListDto createList = listService.createList(listDto);
            BeanUtils.copyProperties(createList, returnValue);
            returnValue.setMessage("Add list successfully");
            returnValue.setStatusCode("200");
            return returnValue;
        } catch (Exception err) {
            returnValue.setMessage(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage());
            returnValue.setStatusCode("500");
            return returnValue;
        }

    }

    @PutMapping
    public ListResponse UpdateList(@RequestBody ListRequestModel listDetails) throws Exception {
        ListResponse returnValue = new ListResponse();
        if (listDetails.getListName().isEmpty())
        {
            returnValue.setMessage(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());
            returnValue.setStatusCode("400");
            return  returnValue;
        }
        try{
            ListDto listDto = listService.updateListById(listDetails.getListId(), listDetails.getListName());
            BeanUtils.copyProperties(listDto, returnValue);
            return returnValue;
        }
        catch(Exception err){
            returnValue.setMessage(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage());
            returnValue.setStatusCode("500");
            return  returnValue;
        }

    }

    @CrossOrigin
    @DeleteMapping(path = "/{listId}")
    public OperationStatusModel DeleteList(@PathVariable Integer listId) throws Exception {
        OperationStatusModel returnValue = new OperationStatusModel();
        if(listId==null){
            returnValue.setOperationResult("ListId is null");

            returnValue.setStatusCode("400");
            return returnValue;
        }
        try{
            returnValue.setOperationName(RequestOperationName.DELETE.name());
            listService.deleteList(listId);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            return returnValue;
        }
        catch(Exception err){
            returnValue.setOperationResult("Not Valid ListId");
            returnValue.setStatusCode("400");
            return returnValue;
        }

    }

}
