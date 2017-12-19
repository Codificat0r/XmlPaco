package com.example.xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPartes;
    private Button btnNotas;
    private Button btnTitulares;
    private Button btnNoticias;
    private Button btnCreacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPartes = (Button)findViewById(R.id.btnPartes);
        btnPartes.setOnClickListener(this);
        btnNotas = (Button)findViewById(R.id.btnNotas);
        btnNotas.setOnClickListener(this);
        btnTitulares = (Button)findViewById(R.id.btnTitulares);
        btnTitulares.setOnClickListener(this);
        btnNoticias = (Button)findViewById(R.id.btnNoticias);
        btnNoticias.setOnClickListener(this);
        btnCreacion = (Button)findViewById(R.id.btnCreacion);
        btnCreacion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.btnPartes:
                i = new Intent(this, Partes.class);
                startActivity(i);
                break;
            case R.id.btnNotas:
                i = new Intent(this, Notas.class);
                startActivity(i);
                break;
            case R.id.btnTitulares:
                i = new Intent(this, Titulares.class);
                startActivity(i);
                break;
            case R.id.btnNoticias:
                i = new Intent(this, Noticias.class);
                startActivity(i);
                break;
            case R.id.btnCreacion:
                i = new Intent(this, Creacion.class);
                startActivity(i);
                break;
        }
    }
}
