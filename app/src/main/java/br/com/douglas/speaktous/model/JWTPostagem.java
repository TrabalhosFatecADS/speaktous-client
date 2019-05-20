package br.com.douglas.speaktous.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JWTPostagem {

    @SerializedName("users")
    @Expose

    private int status;
    private String message;
    private Postagem postagem;

    public JWTPostagem() {
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

    public Postagem getPostagem() {
        return postagem;
    }

    public void setPostagem(Postagem postagem) {
        this.postagem = postagem;
    }
}
