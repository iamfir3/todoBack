package com.example.back.response;

public class ListGetAllResponse <T> {

    private long listCount;
    T lists;

    public long getListCount() {
        return listCount;
    }
    private String message;
    private int status;

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

    public void setListCount(long listCount) {
        this.listCount = listCount;
    }

    public T getLists() {
        return lists;
    }

    public void setLists(T lists) {
        this.lists = lists;
    }
}
