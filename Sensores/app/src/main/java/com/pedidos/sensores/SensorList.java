package com.pedidos.sensores;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorList extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        TextView tv= (TextView) findViewById(R .id.textView);
// inicia un SensorManager
        SensorManager sensorManager=(SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allSensors =
                sensorManager.getSensorList(Sensor.TYPE_ALL);
        int size=allSensors.size ();
        tv.setText("\nNumero de sensores: "+size);
        for(int i=0;i<size;i++){
            Sensor sensor=allSensors.get(i);
            int tipo=sensor.getType();
            tv.append("\nTipo: "+tipo);
            String nombre=sensor.getName();
            tv.append("\n"+nombre);
            String vendedor=sensor.getVendor();
            tv.append("\nVendedor: "+vendedor);
            float power=sensor.getPower();
            tv.append("\nPower (mA): "+power);
            float resolucion=sensor.getResolution();
            tv.append("XnResolucion: "+resolucion);
            float rango=sensor.getMaximumRange();
            tv.append(" - Rango: "+rango);
        }
    }
}