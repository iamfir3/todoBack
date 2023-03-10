package com.example.back.sevice.impl;

import com.example.back.entity.ListEntity;
import com.example.back.repository.ListRepository;
import com.example.back.response.ErrorMessage;
import com.example.back.service.ListService;
import com.example.back.shared.dto.ListDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListServiceImpl implements ListService {
    @Autowired
    ListRepository listRepository;

    @Override
    public ListDto createList(ListDto list) throws Exception {
        ListEntity listEntity = new ListEntity();

        BeanUtils.copyProperties(list, listEntity);
        try {
            ListEntity storedList = listRepository.save(listEntity);
            ListDto returnValue = new ListDto();
            BeanUtils.copyProperties(storedList, returnValue);
            return returnValue;
        } catch (Exception err) {
            throw new Exception(ErrorMessage.INTERVAL_SERVER_ERROR.getErrorMessage());
        }
    }

    @Override
    public List<ListDto> getAll(Integer page, Integer limit) {
        List<ListDto> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ListEntity> listPage = listRepository.findAll(pageableRequest);
        List<ListEntity> lists = listPage.getContent();

        for (ListEntity listEntity : lists) {
            ListDto listDto = new ListDto();

            BeanUtils.copyProperties(listEntity, listDto);
            listDto.setTotalList(listRepository.count());
            returnValue.add(listDto);
        }
        return returnValue;
    }

    @Override
    public ListDto getListById(Integer listId) {
        ListDto returnValue = new ListDto();
        Optional<ListEntity> list = listRepository.findById(listId);
        BeanUtils.copyProperties(list.get(), returnValue);
        return returnValue;
    }

    @Override
    public ListDto updateListById(Integer listId, String listName) throws Exception {
        ListDto returnValue = new ListDto();
        Optional<ListEntity> listEntity = listRepository.findById(listId);
        if (listEntity.isPresent()) {
            listEntity.get().setListName(listName);
            ListEntity storedListEntity = listRepository.save(listEntity.get());
            BeanUtils.copyProperties(storedListEntity, returnValue);
        } else throw new Exception(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());
        return returnValue;
    }

    @Override
    public void deleteList(Integer listId) throws Exception {
        Optional<ListEntity> listEntity = listRepository.findById(listId);
        if (listEntity.isPresent()==false) {
            throw new Exception(ErrorMessage.NOT_FOUND_LIST.getErrorMessage());
        }

        listRepository.delete(listEntity.get());

    }


}
