package com.example.xml;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xml.R;
import com.example.xml.utils.Analisis;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Notas extends AppCompatActivity {

    private TextView txvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        txvInfo = findViewById(R.id.txvInfo);

        try {
            txvInfo.setText(Analisis.analizarNombres(this));
        } catch (Exception e) {

        }
    }


}
