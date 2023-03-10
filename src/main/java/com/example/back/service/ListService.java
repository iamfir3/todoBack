package com.example.back.service;

import com.example.back.shared.dto.ListDto;

import java.util.List;

public interface ListService {

    ListDto createList(ListDto listDto) throws Exception;

    List<ListDto> getAll(Integer page, Integer limit);
    ListDto getListById(Integer listId);
    ListDto updateListById(Integer listId,String listName) throws Exception;

    void deleteList(Integer listId) throws Exception;
}
