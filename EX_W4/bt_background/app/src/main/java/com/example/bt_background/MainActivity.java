package com.example.bt_background;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mainLayout;
    private Switch switchChangeBackground;
    private List<Integer> backgroundList;
    private int currentIndex = -1; // Vị trí hình nền hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.mainLayout);
        switchChangeBackground = findViewById(R.id.switchChangeBackground);

        // Tạo danh sách hình nền và xáo trộn
        initBackgroundList();
        changeBackground(); // Load hình nền ban đầu

        // Thay đổi hình nền khi bật/tắt Switch
        switchChangeBackground.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                changeBackground();
            }
        });
    }

    private void initBackgroundList() {
        backgroundList = new ArrayList<>();
        backgroundList.add(R.drawable.bg1);
        backgroundList.add(R.drawable.bg2);
        backgroundList.add(R.drawable.bg3);
        backgroundList.add(R.drawable.bg4);
        Collections.shuffle(backgroundList); // Xáo trộn danh sách
    }

    private void changeBackground() {
        currentIndex++; // Chuyển sang hình tiếp theo

        // Nếu đã hiển thị hết hình, xáo trộn lại
        if (currentIndex >= backgroundList.size()) {
            Collections.shuffle(backgroundList);
            currentIndex = 0;
        }

        Drawable background = getResources().getDrawable(backgroundList.get(currentIndex));
        mainLayout.setBackground(background);
    }
}
