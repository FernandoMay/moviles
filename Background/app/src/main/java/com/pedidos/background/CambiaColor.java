package com.pedidos.background;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class CambiaColor extends Activity {
    Handler handler = new Handler();
    TextView tv1, tv2, tv3;
    int time = 0;
    int rate = 100;
    Timer timer, timer2, timer3;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.main);
        tv1 = (TextView) findViewById(R.id.textview);
        tv2 = (TextView) findViewById(R.id.textview2);
        tv3 = (TextView) findViewById(R.id.textview3);
        int factorl = 1;
        int factor2 = 2;
        int factor3 = 3;
        timer = new Timer("Temporizador");
        Tarea tarea = new Tarea(tv1, factorl);
        timer.scheduleAtFixedRate(tarea, 0, rate);
        timer2 = new Timer("Temporizador2");
        Tarea2 tarea2 = new Tarea2(tv2, factor2);
        timer2.scheduleAtFixedRate(tarea2, 0, rate);
        timer3 = new Timer("Temporizador3");
        Tarea3 tarea3 = new Tarea3(tv3, factor3);
        timer3.scheduleAtFixedRate(tarea3, 0, rate);
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
        timer2.cancel();
        timer3.cancel();
    }

    class Tarea extends TimerTask {
        int factor;
        TextView textTarea;

        public Tarea(TextView textview, int fact) {
            textTarea = textview;
            factor = fact;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            runOnUiThread(cambiaTexto);
        }
    }

    class Tarea2 extends TimerTask {
        int factor;
        TextView textTarea;

        public Tarea2(TextView textView, int fact) {
            textTarea = textView;
            factor = fact;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            textTarea.post(cambiaTexto);
        }
    }

    class Tarea3 extends TimerTask {
        int factor;
        TextView textTarea;

        public Tarea3(TextView textView, int fact) {
            textTarea = textView;
            factor = fact;
        }

        @Override
        public void run() {
            Runnable cambiaTexto = new CambiaTexto(textTarea, factor);
            handler.post(cambiaTexto);
        }
    }

    class CambiaTexto implements Runnable {
        int red, green, blue, factor;
        TextView textCambia;

        public CambiaTexto(TextView textView, int fact) {
            textCambia = textView;
            factor = fact;
        }

        @Override

        public void run() {
            // TODO Auto-generated method stub
            time = time + rate;
            red = (time / factor) % 255;
            green = (int) ((0.75 * time / factor) % 255);
            blue = (int) ((0.60 * time / factor) % 255);
            String texto = "TEMP0RIZADOR\n rate= " + rate + "\n t = " + time;
            textCambia.setText(texto);
            textCambia.setTypeface(null, Typeface.BOLD);
            textCambia.setTextSize(30);
            textCambia.setTextColor(Color.rgb(red, green, blue));
        }
    }
}