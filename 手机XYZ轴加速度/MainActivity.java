package com.example.ykai.altitudetest;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.hardware.SensorListener;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorListener
{
    SensorManager sm = null;
    TextView acx = null;
    TextView acy = null;
    TextView o = null;
    TextView Sum = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get reference to SensorManager
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        setContentView(R.layout.activity_main);

        acx = (TextView) findViewById(R.id.xbox);
        acy = (TextView) findViewById(R.id.ybox);
        o = (TextView) findViewById(R.id.obox);
        Sum =(TextView) findViewById(R.id.oSum);
    }
    public void onSensorChanged(int sensor, float[] values) {
        synchronized (this) {


            acx.setText("a X: " + values[0]);
            acy.setText("a Y: " + values[1]);
            o.setText("Orientation : " + values[2]);
            Sum.setText("Sum =  "+Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]));
            if(Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2])<1)
            {
                Toast.makeText(getApplicationContext(), "默认Toast样式",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onAccuracyChanged(int sensor, int accuracy) {
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and accelerometer sensors
        sm.registerListener(this,
                SensorManager.SENSOR_ORIENTATION |SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onStop() {
        // unregister listener
        sm.unregisterListener(this);
        super.onStop();
    }
}
