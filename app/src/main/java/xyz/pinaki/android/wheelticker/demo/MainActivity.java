package xyz.pinaki.android.wheelticker.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xyz.pinaki.android.wheelticker.Odometer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        Odometer frequncyCounter = (Odometer) findViewById(R.id.frequency_counter);
        frequncyCounter.spinTo((int)(Math.random() * 1000));
    }
}
