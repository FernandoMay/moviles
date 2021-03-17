package com.pedidos.animfotogramas;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    AnimationDrawable animation;
    ImageView image;
    TextView tv;
    double ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            image = (ImageView)
                    findViewById(R.id.imagen);
            image.setVisibility(ImageView.VISIBLE);
            animation = new AnimationDrawable();
            Resources resources = getResources();
            Drawable imagenl
                    = resources.getDrawable(R.drawable.unnamed);
            Drawable imagen2
                    = resources.getDrawable(R.drawable.chauvet);
            Drawable imagen3
                    = resources.getDrawable(R.drawable.minipitu);
            Drawable imagen4
                    = resources.getDrawable(R.drawable.train);
            Drawable imagen5
                    = resources.getDrawable(R.drawable.linda);
            Drawable imagen6
                    = resources.getDrawable(R.drawable.chau);
            animation.addFrame(imagenl, 3000);
            animation.addFrame(imagen2, 3000);
            animation.addFrame(imagen3, 3000);
            animation.addFrame(imagen4, 3000);
            animation.addFrame(imagen5, 3000);
            animation.addFrame(imagen6, 3000);
            animation.setOneShot(false);
            image.setImageDrawable(animation);
            int width = imagenl.getIntrinsicWidth();
            int height = imagenl.getIntrinsicHeight();
            ratio = height / (width + 0.01);
            tv = (TextView) findViewById(R.id.texto);
            tv.setText("imagenl width=" + width + " height="
                    + height + " ratio=" + ratio);
            width = image.getMeasuredWidth();
            height = image.getMeasuredHeight();
            tv.append("\nimage width=" + width + ", height=" + height);
            Button boton = (Button) findViewById(R.id.buttonl);
            boton.setOnClickListener(this);
            Button boton2 = (Button) findViewById(R.id.button2);
            boton2.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonl) {
            int width = image.getMeasuredWidth();
            int height = image.getMeasuredHeight();
            tv.append("\nonClick width=" + width
                    + " height=" + height);
            int newwidth = (int) (height / ratio);
            int padding = (width - newwidth) / 2;
            tv.append("\nnewwidth=”tnewwidth+” padding = " + padding);
            image.setPadding(padding, 0, padding, 0);
            animation.start();
        } else animation.stop();
    }
}
