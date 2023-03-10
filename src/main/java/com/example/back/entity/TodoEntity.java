package com.example.back.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name="Todo")
public class TodoEntity implements Serializable {

    private static final long serialVersionUID=1L;

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDone() {
        return isDone;
    }

    public void setDone(Integer done) {
        isDone = done;
    }

    @Id
    @GeneratedValue
    private Integer todoId;

    private String content;

    @Column(nullable = false)
    private Integer isDone=0;




    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            @JoinColumn(name="listId",referencedColumnName = "listId",nullable = false)
    private ListEntity listEntity;

    public ListEntity getListEntity() {
        return listEntity;
    }

    public void setListEntity(ListEntity listEntity) {
        this.listEntity = listEntity;
    }
}
