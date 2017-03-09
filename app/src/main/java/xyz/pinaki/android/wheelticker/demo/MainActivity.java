package xyz.pinaki.android.wheelticker.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import xyz.pinaki.android.wheelticker.Odometer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.animate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Odometer frequncyCounter = (Odometer) findViewById(R.id.frequency_counter);
                frequncyCounter.spinTo((int)(Math.random() * 10000));
            }
        });
    }
}
