package com.example.back.shared.dto;

public class ListDto {
    private static final long serialVersionUID=1l;
    private String listName;
    private Integer listId;
    private long totalList;

    public long getTotalList() {
        return totalList;
    }

    public void setTotalList(long totalList) {
        this.totalList = totalList;
    }

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
}

