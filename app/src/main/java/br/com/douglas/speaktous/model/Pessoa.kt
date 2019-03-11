package br.com.douglas.speaktous.model

import java.io.Serializable
import java.util.*

class Pessoa: Serializable{

    var id: Long? = null
    var nome: String? = null
    var user: String? = null
    var passwd: String? = null
    var email: String? = null
    var dtcadastro: Date? = null

}