package com.example.back.model;

public class TodoRequestModel {

    private String content;
    private Integer todoId;
    private Integer isDone;
    private Integer listId;

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

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
}
