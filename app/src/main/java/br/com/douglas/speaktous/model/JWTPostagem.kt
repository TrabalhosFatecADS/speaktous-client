package br.com.douglas.speaktous.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JWTPostagem {

    @SerializedName("users")
    @Expose
    var status: Int = 0
    var message: String? = null
    var postagem: Postagem? = null
}
