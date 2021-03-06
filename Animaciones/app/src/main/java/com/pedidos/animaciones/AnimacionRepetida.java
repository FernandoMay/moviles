package com.pedidos.animaciones;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AnimacionRepetida extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_two);

        TextView tv = (TextView) findViewById(R.id.texto);
        Animation td = AnimationUtils.loadAnimation(this,R.anim.animacion);
        td.setRepeatMode(Animation.RESTART);
        td.setRepeatCount(20);
        //td.setFillAfter(true);
        tv.startAnimation(td);
        tv.append("\n RepeatMode: "+td.getRepeatMode());
        tv.append("\n RepeatCount: "+td.getRepeatCount());
    }
}