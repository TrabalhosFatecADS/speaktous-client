package br.com.douglas.speaktous

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView


class CadastroNovoUsuario : AppCompatActivity() {

    var edtLogin: EditText? = null
    internal var edtSenha: EditText? = null
    internal var edtCSenha: EditText? = null
    internal var btnCadastrar: Button? = null
    internal var textTermosdeUso: TextView? = null
    internal var cbTermos: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_novo_usuario)
        supportActionBar!!.hide() //Tirar a Barra do Nome do Projeto

        edtLogin = findViewById<View>(R.id.edtLogin) as? EditText
        edtSenha = findViewById<View>(R.id.edtSenha) as? EditText
        edtCSenha = findViewById<View>(R.id.edtCSenha) as? EditText

        btnCadastrar = findViewById<View>(R.id.btnCadastrar) as Button

        textTermosdeUso = findViewById<View>(R.id.textTermosdeUso) as TextView

        cbTermos = findViewById<View>(R.id.cbTermos) as CheckBox


        // Texto Clique Aqui! -- Chama tela CadastroNovoUsuario
        textTermosdeUso!!.setOnClickListener {
            val chamaTela = Intent(this@CadastroNovoUsuario, TermosUso::class.java)
            startActivity(chamaTela)
        }
    }
}
