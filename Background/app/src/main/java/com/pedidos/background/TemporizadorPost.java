package com.pedidos.background;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TemporizadorPost extends Activity {
    TextView tv;
    int time = 0;
    int rate = 100;
    Timer timer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textview);
        timer = new Timer("Temporizador");
        Tarea tarea = new Tarea();
        timer.scheduleAtFixedRate(tarea, 0, rate);
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    class Tarea extends TimerTask {
        @Override
        public void run() {
            Runnable cambiaTexto = new CambiaTexto();
            tv.post(cambiaTexto);
        }
    }

    class CambiaTexto implements Runnable {
        @Override
        public void run() {
            time = time + rate;
            String texto
                    = "TEMPORIZADOR CAMBIANDO TEXTO CON POST\n rate= " + rate + "\n t = " + time;
            tv.setText(texto);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextSize(30);
        }
    }
}