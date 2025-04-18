package com.example.vidubaitap3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class NumbersActivity extends AppCompatActivity {

    private TextView txtSoN;
    private Button btnRnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generate_random_numbers);

        // Ánh xạ
        txtSoN = findViewById(R.id.textViewSo);
        btnRnd = findViewById(R.id.buttonRnd);

        // Viết code sinh ngẫu nhiên
        btnRnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo số ngẫu nhiên
                Random random = new Random();
                int number = random.nextInt(10); // Sinh số từ 0 đến 9
                txtSoN.setText(String.valueOf(number)); // Hiển thị số ngẫu nhiên
            }
        });
    }
}
