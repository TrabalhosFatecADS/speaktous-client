package br.com.douglas.speaktous

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.douglas.speaktous.model.Pessoa
import br.com.douglas.speaktous.service.PessoaService
import br.com.douglas.speaktous.util.getMd5
import br.com.douglas.speaktous.util.validateEmailFormat


class MainActivity : AppCompatActivity() {

    //Declaração de Variáveis
    internal var edtLogin: EditText? = null
    internal var edtSenha: EditText? = null
    internal var btnEntrar: Button? = null
    internal var txtCadastrar: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide() //Tirar a Barra do Nome do Projeto

        edtLogin = findViewById<View>(R.id.edtLogin) as EditText
        edtSenha = findViewById<View>(R.id.edtSenha) as EditText
        btnEntrar = findViewById<View>(R.id.btnEntrar) as Button
        txtCadastrar = findViewById<View>(R.id.txtCadastrar) as TextView


        // Texto Clique Aqui! -- Chama tela CadastroNovoUsuario
        txtCadastrar!!.setOnClickListener {
            val chamaTela = Intent(this, CadastroNovoUsuario::class.java)
            startActivity(chamaTela)
        }

        this.btnEntrar!!.setOnClickListener {

            var txtEmail = edtLogin!!.text
            var txtSenha = edtSenha!!.text

            try {
                if (txtEmail.isBlank() || !validateEmailFormat(txtEmail.toString())){
                    alert("Informe um email valido")
                    throw Exception("o campo email não foi informado")
                }
                if (txtSenha.length < 4){
                    alert("A senha deve ter no minimo 4 letras ou numeros")
                    throw Exception("Senha nao informada corretamente")
                }

                val pessoaService = PessoaService(null,null, null, getMd5(txtSenha.toString()), txtEmail.toString(), null,2)

                var xtpo: Pessoa? = null
                xtpo = pessoaService.execute().get()

                if (xtpo.id == null) {
                    alert("usuario ou senha incorretos!")
                    throw Exception("usuario ou senha incorretos!")
                }
                alert("Clicou entrar")
            }catch (e: Exception){
                e.printStackTrace()
            }




        }
    }

    fun alert(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }
}


// open class MainActivity : AppCompatActivity() {
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// setContentView(R.layout.activity_main)
//
// var btLogin = findViewById<Button>(R.id.btLogin)
//
// btLogin.setOnClickListener {
// var tLogin = findViewById<EditText>(R.id.tLogin)
// var tSenha = findViewById<EditText>(R.id.tSenha)
//
// var login = tLogin.text
// var senha = tSenha.text
//
// val service = HTTPService(login.toString(), senha.toString())
// try {
// val retorno = service.execute().get()
//
// if(retorno != null && login.toString() == retorno.user && senha.toString() == retorno.passwd){
// alert("Login Realizado com sucesso!!")
// val secondActivity = Intent(this, Master::class.java)
// startActivity(secondActivity)
// }else{
// alert("Usuario ou Senha não conferem")
// }
//
// } catch (e: ExecutionException) {
// e.printStackTrace()
// } catch (e: InterruptedException) {
// e.printStackTrace()
// }
//
//
//
// }
// }
//
// fun alert(s: String){
// Toast.makeText(this,s,Toast.LENGTH_LONG).show()
// }
// }