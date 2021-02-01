package com.oauth.login.controller;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String error;
    private String errorText;
    private List<String> errorItems;

    static final ThreadLocal<String> threadConversationId = new ThreadLocal();

    public ErrorResponse() { super(); }

    public ErrorResponse(String error, String errorText, List<String> errorItems) {
        super();
        this.error = error;
        this.error = errorText;
        this.errorItems = errorItems;
    }
}
