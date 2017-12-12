package com.example.xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xml.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Partes extends AppCompatActivity {

    public static final String TEXTO = "<texto><uno>Hello World!</uno><dos>Goodbye</dos></texto>";
    private TextView txvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partes);

        txvInfo = findViewById(R.id.txvInfo);

        try {
            txvInfo.setText(Analisis.analizar(TEXTO));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            txvInfo.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            txvInfo.setText(e.getMessage());
        }
    }
}
