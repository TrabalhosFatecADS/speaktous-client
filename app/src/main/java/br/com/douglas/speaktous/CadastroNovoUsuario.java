package br.com.douglas.speaktous;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import br.com.douglas.speaktous.model.JWTUsers;
import br.com.douglas.speaktous.remote.APICall;
import br.com.douglas.speaktous.remote.RetroClass;
import br.com.douglas.speaktous.service.PessoaService;
//import br.com.douglas.speaktous.util.getMd5;
//import br.com.douglas.speaktous.util.validateEmailFormat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class CadastroNovoUsuario extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //Tirar a Barra do Nome do Projeto

/*
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        textTermosdeUso = (TextView) findViewById(R.id.textTermosdeUso);


        textTermosdeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(CadastroNovoUsuario.this, CadastroNovoUsuario.class);
                startActivity(chamaTela);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert("Ok-----------------------------------------------------------");
            }
        });
*/
        /*
        btnCadastrar.setOnClickListener((view) {
            try {
                var edtEmail = findViewById<EditText>(R.id.edtEmail)
                var edtSenha = findViewById<EditText>(R.id.edtSenha)
                var edtCSenha = findViewById<EditText>(R.id.edtCSenha)
                var vcbTermos = findViewById<View>(R.id.cbTermos) as CheckBox


                var txtEmail = edtEmail.text
                var txtSenha = edtSenha.text
                var txtCSenha = edtCSenha.text
                var chkTermo = vcbTermos.isChecked

                if (txtEmail.isBlank() || !validateEmailFormat(txtEmail.toString())) {
                    alert("Informe um email valido")
                    throw Exception("o campo email não foi informado")
                }

                if (txtSenha.length < 4) {
                    alert("A senha deve ter no minimo 4 letras ou numeros")
                    throw Exception("Senha nao informada corretamente")
                }

                if (txtSenha.toString() != txtCSenha.toString()) {
                    alert("a confirmação da senha nao confere!")
                    throw Exception("Confirmação de senha invalida")
                }

                if (!chkTermo) {
                    alert("Voce precisa concordar com os termos de uso")
                    throw Exception("não houve concordancia com o termo de uso")
                }

                val todaysDate = Date()
                val df = SimpleDateFormat("dd/MM/yyyy")
                val dtHoje = df.format(todaysDate)

                //val poessoaService =
                //    PessoaService(null, "nome", "user", getMd5(txtSenha.toString()), txtEmail.toString(), dtHoje, 1)

                //poessoaService.execute()

                //val call = RetrofitInitializer().noteService().list()
                val apiCall = RetroClass.getAPICall()

                val jwtUsersCall = apiCall.getUser()
                //Call<JWTUsers> jwtUsersCall = apiCall.getUser("Bearer "+auth);

                //call.enqueue(object: Callback<List<Note>?> {
                jwtUsersCall.enqueue(apiCall: Callback<JWTUsers> {
                    @Override
                    public void onResponse(Call<JWTUsers> call, Response<JWTUsers> response) {
                        alert(String.valueOf(response.body().getStatus()));
                    }

                    @Override
                    public void onFailure(Call<JWTUsers> call, Throwable t) {
                        alert("Faio!!!!!!!!!");
                    }
                });


                alert("Cadastro concluido com sucesso!")

                val chamaTela = Intent(this, MainActivity::class.java)
                startActivity(chamaTela)


            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

*/
    }
    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}