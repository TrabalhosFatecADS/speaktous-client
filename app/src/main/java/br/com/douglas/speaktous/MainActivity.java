package br.com.douglas.speaktous;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.douglas.speaktous.model.JWTToken;
import br.com.douglas.speaktous.model.JWTUsers;
import br.com.douglas.speaktous.model.Pessoa;
import br.com.douglas.speaktous.model.User;
import br.com.douglas.speaktous.remote.APICall;
import br.com.douglas.speaktous.remote.RetroClass;
import br.com.douglas.speaktous.service.PessoaService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class MainActivity extends AppCompatActivity {

    //Declaração de Variáveis
    private EditText edtLogin, edtSenha;
    private Button btnEntrar;
    private TextView txtCadastrar;
    private String auth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); //Tirar a Barra do Nome do Projeto

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        txtCadastrar = (TextView) findViewById(R.id.txtCadastrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final APICall apiCall = RetroClass.getAPICall();

                User user = new User();
                user.setUsername(edtLogin.getText().toString());
                user.setPassword(edtSenha.getText().toString());

                Call<JWTToken> jwtTokenCall = apiCall.userLogin(user);

                jwtTokenCall.enqueue(new Callback<JWTToken>() {
                    @Override
                    public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {

                        JWTToken token = response.body();

                        if (token != null) {
                            alert(token.getToken());

                            Intent intent = new Intent(MainActivity.this, Feed.class);
                            startActivity(intent);

                            auth = token.getToken();

                            Call<JWTUsers> jwtUsersCall = apiCall.getUser("Bearer "+auth);

                            jwtUsersCall.enqueue(new Callback<JWTUsers>() {
                                @Override
                                public void onResponse(Call<JWTUsers> call, Response<JWTUsers> response) {
                                    alert(String.valueOf(response.body().getStatus()));
                                }

                                @Override
                                public void onFailure(Call<JWTUsers> call, Throwable t) {
                                    alert("Faio!!!!!!!!!");
                                }
                            });


                        } else {
                            alert("Token invalido!");
                        }
                    }

                    @Override
                    public void onFailure(Call<JWTToken> call, Throwable t) {
                        alert(t.getMessage());
                    }
                });
            }
        });

        // Texto Clique Aqui! -- Chama tela CadastroNovoUsuario
        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(MainActivity.this, CadastroNovoUsuario.class);
                startActivity(chamaTela);
            }
        });

    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}