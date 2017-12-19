package com.example.xml;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xml.utils.Analisis;
import com.example.xml.utils.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

public class Noticias extends AppCompatActivity implements View.OnClickListener {
    public static final String RSS = "http://www.alejandrosuarez.es/feed/";
    //public static final String RSS = "http://10.0.2.2/feed/alejandro.xml";
    public static final String TEMPORAL = "alejandro.xml";
    Button btnObtener;
    TextView txvTitulares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        btnObtener = (Button) findViewById(R.id.btnObtener);
        btnObtener.setOnClickListener(this);
        txvTitulares = (TextView) findViewById(R.id.txvTitulares);
    }


    @Override
    public void onClick(View v) {
        if (v == btnObtener)
            descarga(RSS, TEMPORAL);
    }

    private void descarga(String rss, String temporal) {
        final ProgressDialog progreso = new ProgressDialog(this);
        File miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temporal);
        RestClient.get(rss, new FileAsyncHttpResponseHandler(miFichero) {

            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(false);
                progreso.show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progreso.dismiss();
                Toast.makeText(Noticias.this, "Fallo: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progreso.dismiss();
                Toast.makeText(Noticias.this, "Fichero descargado correctamente", Toast.LENGTH_SHORT).show();
                try {
                    txvTitulares.setText(Analisis.analizarRSS(file));
                } catch (Exception e) {
                    Toast.makeText(Noticias.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
