package com.example.back.response;

public enum ErrorMessage {
    MISSING_LIST_ID("Missing list id!"),
    NOT_FOUND_LIST("Not found list"),
    NOT_FOUND_TODO("Not found todo"),
    INVALID_INPUT("Invalid input"),
    INTERVAL_SERVER_ERROR("Interval server error"),
    MISSING_REQUIRED_FIELD("Missing required field")
    ;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    ErrorMessage(String errorMessage) {
        this.errorMessage=errorMessage;
    }
}
