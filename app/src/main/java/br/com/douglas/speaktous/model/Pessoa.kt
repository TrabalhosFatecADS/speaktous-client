package br.com.douglas.speaktous.model

import java.io.Serializable


class Pessoa: Serializable{

    var id: Long? = null
    var nome: String? = null
    var usuario: String? = null
    var senha: String? = null
    var email: String? = null
    var dtCadastro: String? = null

    /*
     private int id;

    private String nome;

    private String usuario;

    private String senha;

    private String email;

    private Date dtCadastro;
     */


}