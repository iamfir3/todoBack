package com.example.back.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;


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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

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
