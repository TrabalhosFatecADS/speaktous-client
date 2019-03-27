package br.com.douglas.speaktous

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton


class TermosUso : AppCompatActivity() {

    internal var btnFechar: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_termos_uso)
        supportActionBar!!.hide() //Tirar a Barra do Nome do Projeto

        btnFechar = findViewById<View>(R.id.btnFechar) as ImageButton?


        //Botão Sair
        btnFechar!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                finish()
            }
        })
    }
}
