package br.com.douglas.speaktous.model;

import android.app.Application;

public class LoginGlobal extends Application {

    private Long id;
    private String nome;
    private String user;
    private String email;
    private String passwd;
    private String dtcadastro;

    public LoginGlobal(Long id, String nome, String user, String email, String passwd, String dtcadastro) {
        this.id = id;
        this.nome = nome;
        this.user = user;
        this.email = email;
        this.passwd = passwd;
        this.dtcadastro = dtcadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(String dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    @Override
    public String toString() {
        return "LoginGlobal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", dtcadastro='" + dtcadastro + '\'' +
                '}';
    }
}
