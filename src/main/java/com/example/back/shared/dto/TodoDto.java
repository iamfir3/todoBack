package com.example.back.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class TodoDto implements Serializable {
    private static final long serialVersionUID=1l;
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

    private Integer todoId;
    private String content;
    private Integer isDone=0;
    private Date createDate;

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

    private Date modifyDate;

    private long Todocount;

    public long getTodocount() {
        return Todocount;
    }

    public void setTodocount(long todocount) {
        Todocount = todocount;
    }

    private Integer listId;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }
}
