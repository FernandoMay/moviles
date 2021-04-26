package com.pedidos.dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DialogoBasico extends Activity
        implements OnClickListener {
    TextView tv,tvd;
    EditText editText1,editText2;
    int id=0;
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedlnstanceState) {
    super.onCreate(savedlnstanceState);
    setContentView(R.layout.dialog2);
    tv=(TextView) findViewById(R.id.textView);
    Button botonl= (Button) findViewById(R.id.buttonl);
    botonl.setOnClickListener(this);
}
    @Override
    public void onClick(View v) {
        showDialog(id) ;
    }
@Override
    protected Dialog onCreateDialog(int id){
        Dialog dialogo = new Dialog(this);
        Window w = dialogo.getWindow();
//        flag para desenfocar el fondo
//        int flag = WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
//        flag para oscurecer el fondo
//        int flag = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        flag para mostrar el fondo del escritorio
        int flag =
                WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER;
        w.setFlags(flag,flag);
// creación de un diálogo personalizado
        dialogo.setTitle("Dialogo básico");
        dialogo.setContentView(R.layout.dialogo);
        tvd = (TextView)
                dialogo.findViewById(R.id.textViewDialogo);
        editText1=(EditText)
                dialogo.findViewById(R.id.editText1);
        editText2=(EditText)
                dialogo.findViewById(R.id.editText2);
        Button botonDialogo=(Button)
                dialogo.findViewById(R.id.buttonDialogo);
        botonDialogo.setOnClickListener(
                new AceptarListener());
        return dialogo;
    }
    class AceptarListener implements OnClickListener{
@Override
        public void onClick(View v) {
            String username= editText1.getText().toString().trim();
            String password= editText2.getText().toString().trim();
            if(username.matches("albert")&&
                    password.matches("einstein")){
                dismissDialog(id);
                tv.setText("Bienvenido, "+username);
            }
else
            tvd.setText("Incorrecto "+username
                    +" "+password
                    + "\n Identifiqúese de nuevo");
        }
    } // end AceptarListener
}
