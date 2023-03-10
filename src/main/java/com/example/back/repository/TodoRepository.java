package com.example.back.repository;

import com.example.back.entity.ListEntity;
import com.example.back.entity.TodoEntity;
import com.example.back.shared.dto.TodoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<TodoEntity,Integer> {

    TodoEntity save(TodoEntity todoEntity);

    Optional<TodoEntity> findById(Integer todoId);
    Page<TodoEntity> findAllByListEntity(ListEntity listEntity,Pageable pageable);
    void deleteById(Integer todoId);

    long count();
    long countByListEntity(ListEntity listEntity);
}
