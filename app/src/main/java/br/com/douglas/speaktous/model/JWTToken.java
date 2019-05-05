package br.com.douglas.speaktous.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTToken {

    @SerializedName("token")
    @Expose

    private String token;
    private int status;
    private String message;

    public JWTToken(String token) {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
