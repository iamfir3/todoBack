package com.example.back.response;

public class ListResponse {
    private Integer listId;
    private String listName;
    private String message;
    private String statusCode;

    public long getListCount() {
        return listCount;
    }

    public void setListCount(long listCount) {
        this.listCount = listCount;
    }

    private long listCount;


    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public String getlistName() {
        return listName;
    }

    public void setlistName(String listName) {
        this.listName = listName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
