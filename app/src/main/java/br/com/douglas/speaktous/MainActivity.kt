package br.com.douglas.speaktous

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.douglas.speaktous.service.HTTPService
import android.os.AsyncTask.execute
import java.util.concurrent.ExecutionException


open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btLogin = findViewById<Button>(R.id.btLogin)

        btLogin.setOnClickListener {
            var tLogin = findViewById<EditText>(R.id.tLogin)
            var tSenha = findViewById<EditText>(R.id.tSenha)

            var login = tLogin.text
            var senha = tSenha.text

            val service = HTTPService(login.toString(), senha.toString())
            try {
                val retorno = service.execute().get()

                if(retorno != null && login.toString() == retorno.user && senha.toString() == retorno.passwd){
                    alert("Login Realizado com sucesso!!")
                }else{
                    alert("Usuario ou Senha não conferem")
                }

            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }



        }
    }

    fun alert(s: String){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }
}
