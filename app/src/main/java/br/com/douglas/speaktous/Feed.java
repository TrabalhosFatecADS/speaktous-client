package br.com.douglas.speaktous;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Feed extends AppCompatActivity {

    Button btnPerfil, btnFeed;
    TextView txtFone, txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().hide(); //Tirar a Barra do Nome do Projeto

        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null){
                alert("Token....: "+params.getString("token"));
            }
        }

        btnPerfil = (Button)findViewById(R.id.btnPerfil);
        btnFeed = (Button)findViewById(R.id.btnFeed);
        txtFone = (TextView)findViewById(R.id.txtFone);
        txtLink = (TextView)findViewById(R.id.txtLink);

        // Botão Perfil -- Chama tela Minhas Publicações
        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(Feed.this, NovaPostagem.class);
                startActivity(chamaTela);
            }
        });


        // Botão Perfil -- Chama tela Minhas Publicações
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(Feed.this, Feed.class);
                startActivity(chamaTela);
            }
        });

        // Texto Telefones Úteis -- Chama tela Telefones Úteis
        txtFone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(Feed.this, TelefonesUteis.class);
                startActivity(chamaTela);
            }
        });

        // Texto Telefones Úteis -- Chama tela Telefones Úteis
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chamaTela = new Intent(Feed.this, LinksUteis.class);
                startActivity(chamaTela);
            }
        });



    }

    private void alert(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
