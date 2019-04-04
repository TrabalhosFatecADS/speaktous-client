package br.com.douglas.speaktous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LinksUteis extends AppCompatActivity {

    ImageButton btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links_uteis);
        getSupportActionBar().hide(); //Tirar a Barra do Nome do Projeto

        btnSair = (ImageButton)findViewById(R.id.btnSair);

        //Botão Sair
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
