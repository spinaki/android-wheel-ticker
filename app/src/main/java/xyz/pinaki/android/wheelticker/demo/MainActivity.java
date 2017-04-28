package xyz.pinaki.android.wheelticker.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import xyz.pinaki.android.wheelticker.Odometer;
import xyz.pinaki.android.wheelticker.OdometerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.animate_button);
        Odometer frequncyCounter = (Odometer) findViewById(R.id.frequency_counter);
        final RandomAdapter randomAdapter = new RandomAdapter();
        frequncyCounter.setAdapter(randomAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomAdapter.randomize();
            }
        });
    }

    public static class RandomAdapter extends OdometerAdapter {
        private int randomInt = 0;
        @Override
        public int getNumber() {
            return randomInt;
        }

        void randomize() {
            randomInt = (int)(Math.random() * 10000);
            notifyDataSetChanged();
        }
    }

}
