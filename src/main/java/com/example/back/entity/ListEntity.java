package com.example.back.entity;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity(name = "List")
public class ListEntity implements Serializable {
    private static final long serialVersionUID=1L;
    @Column(nullable = false,length = 250)
    private String listName;
    @Id
    @GeneratedValue
    private Integer listId;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    @OneToMany(mappedBy = "listEntity" ,orphanRemoval = true)
    private List<TodoEntity> todoEntity = new ArrayList<>();

}
