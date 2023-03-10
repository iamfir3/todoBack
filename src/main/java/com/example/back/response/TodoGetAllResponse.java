package com.example.back.response;

public class TodoGetAllResponse<T> {
    private long todoCount;
    T todos;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTodoCount() {
        return todoCount;
    }
    private String message;
    private int status;

    public void setTodoCount(long todoCount) {
        this.todoCount = todoCount;
    }

    public T getTodos() {
        return todos;
    }

    public void setTodos(T todos) {
        this.todos = todos;
    }
}

