package com.pedidos.anims;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Rotacion extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = (TextView) findViewById(R.id.texto1);
        tv1.setText("Animaciones: rotacion");
        TextView tv = (TextView) findViewById(R.id.texto);
        tv1.setText("TEXTO GIRANDO");
        Animation td = AnimationUtils.loadAnimation(this,R.anim.rotacion);
        td.setRepeatMode(Animation.RESTART);
        td.setRepeatCount(20);
        tv.startAnimation(td);
    }
}