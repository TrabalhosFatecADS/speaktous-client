package br.com.douglas.speaktous;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TelefonesUteis extends AppCompatActivity {

    ImageButton btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefones_uteis);
        getSupportActionBar().hide(); //Tirar a Barra do Nome do Projeto

        btnFechar = (ImageButton)findViewById(R.id.btnFechar);

        //Botão Sair
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
