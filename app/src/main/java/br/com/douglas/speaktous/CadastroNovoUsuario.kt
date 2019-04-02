package br.com.douglas.speaktous

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import br.com.douglas.speaktous.service.PessoaService
import br.com.douglas.speaktous.util.getMd5
import br.com.douglas.speaktous.util.validateEmailFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutionException


open class CadastroNovoUsuario : AppCompatActivity() {

   @RequiresApi(Build.VERSION_CODES.O)
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       setContentView(R.layout.activity_cadastro_novo_usuario)
       supportActionBar!!.hide() //Tirar a Barra do Nome do Projeto


       var btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
       var textTermosdeUso = findViewById<TextView>(R.id.textTermosdeUso)


        textTermosdeUso!!.setOnClickListener {
            val chamaTela = Intent(this@CadastroNovoUsuario, TermosUso::class.java)
            startActivity(chamaTela)
        }

        btnCadastrar!!.setOnClickListener {
            try {
                var edtEmail = findViewById<EditText>(R.id.editEmail)
                var edtSenha = findViewById<EditText>(R.id.edtSenha)
                var edtCSenha = findViewById<EditText>(R.id.edtCSenha)
                var vcbTermos = findViewById<View>(R.id.cbTermos) as CheckBox


                var txtEmail = edtEmail.text
                var txtSenha = edtSenha.text
                var txtCSenha = edtCSenha.text
                var chkTermo = vcbTermos.isChecked

                if (txtEmail.isBlank() || !validateEmailFormat(txtEmail.toString())){
                    alert("Informe um email valido")
                    throw Exception("o campo email não foi informado")
                }

                if (txtSenha.length < 4){
                    alert("A senha deve ter no minimo 4 letras ou numeros")
                    throw Exception("Senha nao informada corretamente")
                }

                if (txtSenha.toString() != txtCSenha.toString()){
                    alert("a confirmação da senha nao confere!")
                    throw Exception("Confirmação de senha invalida")
                }

                if (!chkTermo){
                    alert("Voce precisa concordar com os termos de uso")
                    throw Exception("não houve concordancia com o termo de uso")
                }

                val todaysDate = Date()
                val df = SimpleDateFormat("dd/MM/yyyy")
                val dtHoje = df.format(todaysDate)

                val poessoaService = PessoaService(null,"nome", "user", getMd5(txtSenha.toString()), txtEmail.toString(), dtHoje,1)

                poessoaService.execute()

                alert("Cadastro concluido com sucesso!")

                val chamaTela = Intent(this, MainActivity::class.java)
                startActivity(chamaTela)


            }catch (e: ExecutionException){
                e.printStackTrace()
            }
            catch(e: Exception){
                e.printStackTrace()
            }


        }
    }


    fun alert(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }
}
