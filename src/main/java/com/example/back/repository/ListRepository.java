package com.example.back.repository;

import com.example.back.entity.ListEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListRepository extends PagingAndSortingRepository<ListEntity,Integer> {
    Optional<ListEntity> findById(Integer listId);

    ListEntity save(ListEntity listEntity);

    void delete(ListEntity listEntity);

    long count();
}
