package br.com.douglas.speaktous.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTUsers {

    @SerializedName("users")
    @Expose

    private String message;
    private int status;
    private Object result;

    public JWTUsers() {
    }

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
