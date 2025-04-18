package com.example.change_background;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[] backgrounds = {
            R.drawable.background1,
            R.drawable.background2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mainLayout = findViewById(R.id.main_layout);
        Switch backgroundSwitch = findViewById(R.id.background_switch);

        backgroundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mainLayout.setBackgroundResource(getRandomBackground());
            } else {
                mainLayout.setBackgroundResource(getRandomBackground());
            }
        });
    }

    private int getRandomBackground() {
        Random random = new Random();
        return backgrounds[random.nextInt(backgrounds.length)];
    }
}