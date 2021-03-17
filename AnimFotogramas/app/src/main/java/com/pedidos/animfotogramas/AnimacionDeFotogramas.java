package com.pedidos.animfotogramas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AnimacionDeFotogramas extends AppCompatActivity implements View.OnClickListener {
    AnimationDrawable animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView) findViewById(R.id.imagen);
        image.setVisibility(ImageView.VISIBLE);
        image.setBackgroundResource(
                R.drawable.fotogramas_animados);
        animation
                = (AnimationDrawable) image.getBackground();
        Button boton = (Button) findViewById(R.id.buttonl);
        boton.setOnClickListener(this);
        Button boton2 = (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonl) animation.start();
        else animation.stop();
    }
}