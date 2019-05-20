package br.com.douglas.speaktous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import br.com.douglas.speaktous.model.JWTPostagem;
import br.com.douglas.speaktous.model.JWTToken;
import br.com.douglas.speaktous.model.JWTUsers;
import br.com.douglas.speaktous.model.Postagem;
import br.com.douglas.speaktous.remote.APICall;
import br.com.douglas.speaktous.remote.RetroClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovaPostagem extends AppCompatActivity {

    private ImageButton btnFechar;
    private Button btnPublicar;
    private EditText edtTitulo, edtTexto;

    private final Boolean varBoll = false;
    private Bundle params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_postagem);

        Intent intent = getIntent();
        params = intent.getExtras();;
        if (intent != null) {

            if (params != null){
               // alert("Segue Token....: "+params.getString("token"));
                //alert("Token....: "+params.getString("token"));
                alert("Usuario...."+params.getString("user"));
            }
        }

        edtTitulo = (EditText)findViewById(R.id.edtTitulo);
        edtTexto = (EditText)findViewById(R.id.edtTexto);
        btnFechar = (ImageButton)findViewById(R.id.btnFechar);
        btnPublicar = (Button)findViewById(R.id.btnPublicar);

        //Botão Sair
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Botão Publicar
        btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final APICall apiCall = RetroClass.getAPICall();
                //alert("Botão publicar");
                try {
                    if (edtTitulo.getText().toString().isEmpty() || edtTitulo.getText().toString().equals("")){
                        alert("Informe um titulo para sua publicação");
                        throw new Exception("o campo titulo não foi informado");
                    }
                    if (edtTexto.getText().toString().isEmpty() || edtTexto.getText().toString().equals("")){
                        alert("Sua publicação deve ter um conteudo");
                        throw new Exception("publicação sem conteudo");
                    }
                    Postagem postagem = new Postagem();

                    postagem.setTitulo(edtTitulo.getText().toString());
                    postagem.setMensagem(edtTexto.getText().toString());
                    postagem.setAprovado(varBoll);
                    postagem.setUsuario(params.getString("user"));
                    postagem.setCurtidas(0);

                    Call<JWTPostagem> jwtTokenCall = apiCall.salvaPostagem(postagem);

                    jwtTokenCall.enqueue(new Callback<JWTPostagem>() {
                        @Override
                        public void onResponse(Call<JWTPostagem> call, Response<JWTPostagem> response) {
                            alert("Salvou postagem");
                        }

                        @Override
                        public void onFailure(Call<JWTPostagem> call, Throwable t) {
                            t.getStackTrace();
                            alert("falha ao salvar");
                        }
                    });


                }catch (Exception ex){
                    ex.getMessage();
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
