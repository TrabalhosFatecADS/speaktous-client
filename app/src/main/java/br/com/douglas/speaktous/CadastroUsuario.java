package br.com.douglas.speaktous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import br.com.douglas.speaktous.model.JWTToken;
import br.com.douglas.speaktous.model.Pessoa;
import br.com.douglas.speaktous.remote.APICall;
import br.com.douglas.speaktous.remote.RetroClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroUsuario extends AppCompatActivity {

    private Button btnCadastrar;
    private TextView textTermosdeUso;
    private EditText edtEmail, edtSenha, edtCSenha;
    private CheckBox vcbTermos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        textTermosdeUso = (TextView) findViewById(R.id.textTermosdeUso);

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        edtCSenha = (EditText)findViewById(R.id.edtCSenha);
        vcbTermos = (CheckBox) findViewById(R.id.cbTermos);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            final APICall apiCall = RetroClass.getAPICall();

            @Override
            public void onClick(View v) {

                try {
                    if (edtEmail.getText().toString().isEmpty() || edtEmail.getText().toString().equals("")){
                        alert("Informe um email valido");
                        throw new Exception("o campo email não foi informado");
                    }

                    if (edtSenha.getText().toString().isEmpty() || edtSenha.getText().toString().equals("")){
                        alert("digite uma senha");
                        throw new Exception("Senha não informada");
                    }

                    if (!edtCSenha.getText().toString().equals(edtSenha.getText().toString())){
                        alert("Senhas não conferem");
                        throw new Exception("Senhas não conferem");
                    }

                    if (!vcbTermos.isChecked()){
                        alert("Confirme os termos de uso");
                        throw new Exception("Falta confirmação de termo de uso.");
                    }

                    Pessoa pessoa = new Pessoa();
                    pessoa.setEmail(edtEmail.getText().toString());
                    pessoa.setSenha(edtSenha.getText().toString());

                    Date todaysDate = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    //pessoa.setDtCadastro(df.format(todaysDate));

                    Call<JWTToken> jwtTokenCall = apiCall.salvaPessoa(pessoa);

                    jwtTokenCall.enqueue(new Callback<JWTToken>() {
                        @Override
                        public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {
                            alert("Salvou.................");
                        }

                        @Override
                        public void onFailure(Call<JWTToken> call, Throwable t) {
                            alert("Falhou.................");
                        }
                    });

                }catch (Exception ex){
                    ex.getMessage();
                }

                alert("Funfo...............");
            }
        });

        textTermosdeUso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(CadastroUsuario.this, TermosUso.class);
                startActivity(chamaTela);
            }


        });
    }

    //protocolo claro 409192882010798

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
