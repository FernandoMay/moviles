package com.pedidos.animaciones;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Animacion extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        TextView tv = (TextView) findViewById(R.id.texto);
        Animation td = AnimationUtils.loadAnimation(this,R.anim.traslacion_derecha);
        td.setFillAfter(true);
        tv.startAnimation(td);
        tv.append("\n Texto añadido");
    }
}