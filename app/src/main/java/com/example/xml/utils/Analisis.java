package com.example.xml.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.example.xml.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by usuario on 12/12/17.
 */

public class Analisis {

    public static String analizar(String texto) throws XmlPullParserException, IOException {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Inicio . . . \n");
        while (eventType != XmlPullParser.END_DOCUMENT ) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
                cadena.append("START DOCUMENT\n");
            } else if (eventType == XmlPullParser.START_TAG) {
                cadena.append("START TAG: " + xpp.getName() + "\n");
            } else if (eventType == XmlPullParser.TEXT) {
                cadena.append("TEXT: " + xpp.getText() + "\n");
            } else if (eventType == XmlPullParser.END_TAG) {
                cadena.append("END TAG: " + xpp.getName() + "\n");
            }
            eventType = xpp.next();
        }
        //System.out.println("End document");
        cadena.append("End document" + "\n" + "Fin");
        return cadena.toString();
    }

    public static String analizarNombres(Context c) throws XmlPullParserException, IOException {
        boolean esNombre = false;
        boolean esNota = false;
        StringBuilder cadena = new StringBuilder();
        Double suma = 0.0;
        int contador = 0;
        XmlResourceParser xrp = c.getResources().getXml(R.xml.alumnos);
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser. END_DOCUMENT ) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xrp.getName().equals("nombre")) {
                        esNombre = true;
                    } else if (xrp.getName().equals("nota")) {
                        esNota = true;
                        for (int i = 0; i < xrp.getAttributeCount(); i++) {
                            cadena.append(xrp.getAttributeName(i)+ ": " + xrp.getAttributeValue(i) + "\n");
                        }
                    }
                    break;
                case XmlPullParser.TEXT:
                    if (esNombre) {
                        cadena.append("Alumno: " + xrp.getText() + "\n");
                    }
                    if (esNota) {
                        cadena.append("Nota: " + xrp.getText() + "\n");
                        suma += Double.parseDouble(xrp.getText());
                        contador++;
                    }

                    break;
                case XmlPullParser.END_TAG:
                    if (xrp.getName().equals("nombre")) {
                        esNombre = false;
                    } else if (xrp.getName().equals("nota")) {
                        esNota = false;
                    } else if (xrp.getName().equals("alumno")) {
                        cadena.append("\n");
                    }

                    break;
            }
            eventType = xrp.next();
        }
        cadena.append("Media: " + String.format("%.2f", suma/contador));
        return cadena.toString();
    }

    public static String analizarRSS(File file) throws NullPointerException, XmlPullParserException, IOException
    {
        boolean dentroItem = false;
        boolean dentroTitle = false;
        StringBuilder builder = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser. END_DOCUMENT ) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("item")) {
                        dentroItem = true;
                    }
                    if (dentroItem && xpp.getName().equals("title")) {
                        dentroTitle = true;
                    }

                    break;
                case XmlPullParser.TEXT:
                    if (dentroTitle)
                        builder.append(xpp.getText() + "\n");
                    break;
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("item")) {
                        dentroItem = false;
                    }
                    if (dentroItem && xpp.getName().equals("title")) {
                        dentroTitle = false;
                    }
                    break;
            }
            eventType = xpp.next();
        }
        return builder.toString();
    }
}
